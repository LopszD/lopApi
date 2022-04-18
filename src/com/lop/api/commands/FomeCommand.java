package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FomeCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("feed")) || (!(sender instanceof Player))) {
			sender.sendMessage("�cApenas usu�rios in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("wandy.feed")) {
			sender.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			p.setFoodLevel(20);
			p.sendMessage("�aFome saciada!");
			return true;
		}
		if (args.length == 1) {
			Player p1 = Bukkit.getPlayerExact(args[0]);
			if (!p.hasPermission("wandy.feed.others")) {
				if (p1 == null) {
					p.sendMessage("�cEste usu�rio n�o est� on-line.");
					return true;
				}
				p1.setFoodLevel(20);
				p.sendMessage("�aVoc� saciou a fome do usu�rio " + p1.getName() + ".");
			}
		}
		return false;
	}
}
