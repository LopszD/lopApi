package com.wandy.api.stattrack;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.wandy.api.sql.Manager;

public class StatCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("maldicao")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (args.length == 0) {
			p.sendMessage("§1");
			p.sendMessage("§e Neste sistema você poderá adicionar uma contagem de §cmaldições");
			p.sendMessage("§e na sua arma!");
			p.sendMessage("§e Tornando o item cada vez mais §cvalioso§e.");
			p.sendMessage("§3");
			p.sendMessage("§e Para adicioná-lo á sua arma, vá até o Spawn e procure ");
			p.sendMessage("§e o §cMacumbeiro§e.");
			p.sendMessage("§2");
			return true;
		}
		if (args.length >= 2) {
			if (!p.hasPermission("wandy.maldicao")) {
				p.sendMessage("§cUtilize /maldicao set <quantidade>.");
				return true;
			}
			if (args[0].equalsIgnoreCase("set")) {
				if (p.getItemInHand() == null) {
					p.closeInventory();
					p.sendMessage("§cVocê precisa de um item na sua mão.");
					return true;
				}
				if (p.getItemInHand().getType().equals(Material.AIR)) {
					p.closeInventory();
					p.sendMessage("§cVocê precisa de um item na sua mão.");
					return true;
				}
				if (!MagaiverListener.checarItem(p.getItemInHand())) {
					p.closeInventory();
					p.sendMessage("§cTipo inválido de item.");
					return true;
				}
				if (!MagaiverListener.temStat(p.getItemInHand())) {
					p.closeInventory();
					p.sendMessage("§cEste item não possui o sistema de maldição.");
					return true;
				}
				int nm = Integer.valueOf(args[1]).intValue();
				ItemStack al = MagaiverListener.setarConta(p.getItemInHand(), nm);
				p.setItemInHand(al);
				p.sendMessage("§aAbates setados com sucesso.");
			}
		}
		if (args.length == 1) {
			if (!p.hasPermission("wandy.maldicao")) {
				p.sendMessage("§cUtilize /maldicao <spawn/setar/remover>.");
				return true;
			}
			if (args[0].equalsIgnoreCase("spawn")) {
				MagaiverListener.carregarMaga();
				return true;
			}
			if (args[0].equalsIgnoreCase("remover")) {
				if (p.hasPermission("whyze.maldicao")) {
					if (Manager.checarLocExiste("STATTRAK")) {
						Manager.deletarLocation("STATTRAK");
						p.sendMessage("§aLocal removido com sucesso.");
					}
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("setar")) {
				if (p.hasPermission("wandy.maldicao")) {
					if (Manager.checarLocExiste("STATTRAK")) {
						Manager.deletarLocation("STATTRAK");
					}
					Location loc = p.getLocation();
					Manager.setarLocation("STATTRAK", loc);
					p.sendMessage("§aLocal setado com sucesso.");
					return true;
				}
			}
			p.sendMessage("§1");
			p.sendMessage("§e Neste sistema você poderá adicionar uma contagem de §cmaldições");
			p.sendMessage("§e na sua arma!");
			p.sendMessage("§e Tornando o item cada vez mais §cvalioso§e.");
			p.sendMessage("§3");
			p.sendMessage("§e Para adicioná-lo á sua arma, vá até o Spawn e procure ");
			p.sendMessage("§e o §cMacumbeiro§e.");
			p.sendMessage("§2");
			return true;
		}
		return false;
	}
}
