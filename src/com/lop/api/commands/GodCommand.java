package com.wandy.api.commands;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodCommand implements CommandExecutor {
	
	public static ArrayList<String> godmode = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("god")) {
		}
		Player p = (Player) sender;
		if (!p.hasPermission("wandy.god")) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			if (!godmode.contains(p.getName())) {
				godmode.add(p.getName());
				p.sendMessage("§aModo deus ativado!");
			} else {
				godmode.remove(p.getName());
				p.sendMessage("§cModo deus desligado!");
				return true;
			}
			return true;
		}
		if (args.length >= 1) {
			if (!p.hasPermission("wandy.god.others")) {
				if (!godmode.contains(p.getName())) {
					godmode.add(p.getName());
					p.sendMessage("§aModo deus ativado!");
				} else {
					godmode.remove(p.getName());
					p.sendMessage("§cModo deus desligado!");
					return true;
				}
				return true;
			}
			Player p1 = Bukkit.getServer().getPlayer(args[0]);
			if (p1 == null) {
				p.sendMessage("§cEste usuário não está on-line.");
				return true;
			}
			if (!godmode.contains(p1.getName())) {
				godmode.add(p1.getName());
				p.sendMessage("§aModo deus do usuário " + p1.getName() + " foi ativado!");
				p1.sendMessage("§aModo deus ativado!");
			} else {
				godmode.remove(p1.getName());
				p.sendMessage("§cModo deus do usuário " + p1.getName() + " foi desligado!");
				p1.sendMessage("§cModo deus desligado!");
			}
		}
		return false;
	}
}
