package com.wandy.api.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HatCommand implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("hat")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("wandy.hat")) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (p.hasPermission("wandy.gerente")) {
			if (args.length == 1) {
				int v = Integer.valueOf(args[0]).intValue();
				int p0 = 10;
				int a = v / p0;
				int f = v - a;
				p.sendMessage("§a" + v + " %" + p0 + " = " + f);
				return true;
			}
			if (args.length == 2) {
				int v = Integer.valueOf(args[0]).intValue();
				int p0 = 10;
				int a = v / p0;
				int f = v + a;
				p.sendMessage("§a" + v + " %" + p0 + " = " + f);
				return true;
			}
		}
		if ((p.getItemInHand() != null) && (!p.getItemInHand().getType().equals(Material.AIR))) {
			String tipo = new StringBuilder().append(p.getItemInHand().getType()).toString();
			if ((tipo.contains("CHESTPLATE")) || (tipo.contains("LEGGINGS")) || (tipo.contains("BOOTS"))) {
				p.sendMessage("§cVocê não pode colocar este item na sua cabeça.");
				return true;
			}
			String check = "S";
			if ((p.getInventory().getHelmet() != null) && (!p.getInventory().getHelmet().getType().equals(Material.AIR))) {
				check = "N";
			}
			ItemStack cab = null;
			if (check.equals("N")) {
				cab = p.getInventory().getHelmet();
			}
			ItemStack itemStack = p.getItemInHand();
			p.getInventory().setHelmet(itemStack);
			p.setItemInHand(new ItemStack(Material.AIR));
			p.sendMessage("§aAproveite o seu novo chapéu!");
			if (check.equals("N")) {
				p.setItemInHand(cab);
			}
			return true;
		}
		p.sendMessage("§cVocê precisa de um item em sua mão!");
		return false;
	}
}
