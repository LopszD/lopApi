package com.wandy.api.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.Repairable;

import com.wandy.api.listeners.RepararListener;

public class RepararCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("reparar")) {
			Player p = (Player) sender;
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
				return true;
			}
			if (p.getItemInHand() == null) {
				p.sendMessage("§cVocê precisa de um item em sua mão.");
				return true;
			}
			if (p.getItemInHand().getType().equals(Material.AIR)) {
				p.sendMessage("§cVocê precisa de um item em sua mão.");
				return true;
			}
			if (!(p.getItemInHand().getItemMeta() instanceof Repairable)) {
				p.sendMessage("§cTipo inválido de item.");
				return true;
			}
			if (p.getItemInHand().getDurability() < 0) {
				p.sendMessage("§cEste item não pode ser reparado.");
				return true;
			}
			if (p.getItemInHand().getType().isBlock()) {
				p.sendMessage("§cEste item não pode ser reparado.");
				return true;
			}
			if (p.getItemInHand().getDurability() == 0) {
				p.sendMessage("§cEste item não pode ser reparado.");
				return true;
			}
			RepararListener.abrirMenu(p);
		}
		return false;
	}
}
