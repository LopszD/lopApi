package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpHereCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("tphere")) || (!(sender instanceof Player))) {
			sender.sendMessage("�cApenas usu�rios in-game podem executar este comando.");
			return true;
		}
		if (!sender.hasPermission("wandy.tphere")) {
			sender.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (args.length == 0) {
			p.sendMessage("�cUtilize /tphere <usu�rio> para teleportar um usu�rio at� voc�.");
			return true;
		}
		if (args.length == 1) {
			Player p1 = Bukkit.getPlayer(args[0]);
			if (args[0].equalsIgnoreCase(p.getName())) {
				p.sendMessage("�cVoc� n�o pode teleportar voc� mesmo.");
				return true;
			}
			if (p1 == null) {
				p.sendMessage("�cEste usu�rio n�o est� on-line.");
				return true;
			}
			if (p1.hasPermission("wandy.master")) {
				if (!p.hasPermission("wandy.master")) {
					p.sendMessage("�cVoc� n�o pode teleportar este usu�rio.");
					return true;
				}
			}
			if (VanishCommand.vanish.contains(p.getName())) {
				p1.teleport(p);
				p.sendMessage("�a" + p1.getName() + " foi teleportado para " + p.getName() + "!");
				return true;
			}
			p1.teleport(p);
			p.sendMessage("�a" + p1.getName() + " foi teleportado para " + p.getName() + "!");
			p1.sendMessage("�aTeleportado para " + p.getName() + "!");
			return true;
		}
		return false;
	}
}
