package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("tp")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("wandy.tp")) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage("§cUtilize /tp <usuário> para teleportar à um usuário.");
		}
		if (args.length == 1) {
			Player p1 = Bukkit.getPlayer(args[0]);
			if (p1 == null) {
				sender.sendMessage("§cEste usuário não está on-line.");
				return true;
			}
			if (p1.getName() == p.getName()) {
				p.sendMessage("§cVocê não pode se teleportar para si mesmo.");
				return true;
			}
			if (p1.hasPermission("wandy.master")) {
				if (!p.hasPermission("wandy.master")) {
					p.sendMessage("§cVocê não pode teleportar este usuário.");
					return true;
				}
			}
			p.teleport(p1);
			p.sendMessage("§a" + p.getName() + " foi teleportado para " + p1.getName() + "!");
			return true;
		}
		if (args.length == 2) {
			Player p1 = Bukkit.getPlayer(args[0]);
			if (p1 == null) {
				sender.sendMessage("§cEste usuário não está on-line.");
				return true;
			}
			Player p2 = Bukkit.getPlayer(args[1]);
			if (p2 == null) {
				sender.sendMessage("§cEste usuário não está on-line.");
				return true;
			}
			if (p1.getName() == p2.getName()) {
				p.sendMessage("§cVocê não pode se teleportar para si mesmo.");
				return true;
			}
			Player send = (Player) sender;
			if (p1.hasPermission("wandy.master")) {
				if (!p.hasPermission("wandy.master")) {
					p.sendMessage("§cVocê não pode teleportar este usuário.");
					return true;
				}
			}
			if (p2.hasPermission("wandy.master")) {
				if (!p.hasPermission("wandy.master")) {
					p.sendMessage("§cVocê não pode teleportar este usuário.");
					return true;
				}
			}
			String player1 = p1.getName();
			String player2 = p2.getName();
			p1.teleport(p2);
			if (p2.hasPermission("wandy.master")) {
				if (!p.hasPermission("wandy.master")) {
					p.sendMessage("§cVocê não pode teleportar este usuário.");
					return true;
				}
			}
			if (p1.hasPermission("wandy.master")) {
				if (!p.hasPermission("wandy.master")) {
					p.sendMessage("§cVocê não pode teleportar este usuário.");
					return true;
				}
			}
			if (VanishCommand.vanish.contains(p2.getName())) {
				send.sendMessage("§a" + player1 + " foi teleportado para " + player2 + "!");
				return true;
			}
			send.sendMessage("§a" + player1 + " foi teleportado para " + player2 + "!");
			p1.sendMessage("§aTeleportado para " + p2.getName() + "!");
		}
		return false;
	}
}
