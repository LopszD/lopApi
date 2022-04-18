package com.wandy.api.commands;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
	
	public static ArrayList<String> voando = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("fly")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("wandy.fly")) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			if (voando.contains(p.getName())) {
				p.setAllowFlight(false);
				p.sendMessage("§cModo de voo desligado!");
				voando.remove(p.getName());
				return true;
			}
			p.setAllowFlight(true);
			p.sendMessage("§aModo de voo ativado!");
			voando.add(p.getName());
			return true;
		}
		if (args.length >= 1) {
			if (!p.hasPermission("wandy.fly.others")) {
				if (voando.contains(p.getName())) {
					p.setAllowFlight(false);
					p.sendMessage("§cModo de voo desligado!");
					voando.remove(p.getName());
					return true;
				}
				p.setAllowFlight(true);
				p.sendMessage("§aModo de voo ativado!");
				voando.add(p.getName());
				return true;
			}
			if (Bukkit.getServer().getPlayer(args[0]) == null) {
				p.sendMessage("§cEste usuário não está on-line.");
				return true;
			}
			Player p1 = Bukkit.getServer().getPlayer(args[0]);
			if (voando.contains(p1.getName())) {
				p1.setAllowFlight(false);
				p.sendMessage("§cModo de voo do usuário " + p1.getName() + " foi desligado!");
				p1.sendMessage("§cModo de voo desligado!");
				voando.remove(p1.getName());
				return true;
			}
			p1.setAllowFlight(true);
			p1.sendMessage("§aModo de voo ativado!");
			voando.add(p1.getName());
			return true;
		}
		return false;
	}
}
