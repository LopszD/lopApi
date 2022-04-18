package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("�cApenas usu�rios in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("clear")) {
			if (args.length == 1) {
				if (!p.hasPermission("wandy.clear")) {
					sender.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
					return true;
				}
				Player p1 = Bukkit.getPlayer(args[0]);
				if (p1 == null) {
					p.sendMessage("�cEste usu�rio n�o est� on-line.");
					return true;
				}
				if (p1.hasPermission("wandy.clear.others")) {
					p.sendMessage("�cVoc� n�o tem permiss�o para limpar o invent�rio este usu�rio.");
					return true;
				}
				p1.getInventory().clear();
				p1.getInventory().setBoots(null);
				p1.getInventory().setChestplate(null);
				p1.getInventory().setHelmet(null);
				p1.getInventory().setLeggings(null);
				p.sendMessage("�aVoc� limpou todo o invent�rio do usu�rio " + p1.getName() + ".");
			} else {
				if (args.length == 0) {
					if (!p.hasPermission("wandy.clear")) {
						p.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
						return true;
					}
					p.getInventory().clear();
					p.getInventory().setBoots(null);
					p.getInventory().setChestplate(null);
					p.getInventory().setHelmet(null);
					p.getInventory().setLeggings(null);
					p.sendMessage("�aVoc� limpou todo o seu invent�rio com sucesso.");
				}
				if (args.length > 1) {
					p.sendMessage("�cUtilize /clear <usu�rio>.");
					return true;
				}
			}
		}
		return false;
	}
}
