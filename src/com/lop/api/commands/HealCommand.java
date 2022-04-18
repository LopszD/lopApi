package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("heal")) || (!(sender instanceof Player))) {
			sender.sendMessage("�cApenas usu�rios in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("wandy.heal")) {
			sender.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			if (p.getMaxHealth() == 26.0D) {
				p.setHealth(26.0D);
				p.sendMessage("�aVoc� foi curado!");
				return true;
			}
			p.setHealth(20.0D);
			p.sendMessage("�aVoc� foi curado!");
			return true;
		}
		if (args.length == 1) {
			if (!p.hasPermission("wandy.heal.others")) {
				Player p1 = Bukkit.getPlayerExact(args[0]);
				if (p1 == null) {
					p.sendMessage("�cEste usu�rio n�o est� on-line.");
					return true;
				}
				if (p1.getMaxHealth() == 26.0D) {
					p1.setHealth(26.0D);
					p.sendMessage("�aVoc� curou o usu�rio " + p1.getName() + ".");
					return true;
				}
				p1.setHealth(20.0D);
				p.sendMessage("�aVoc� curou o usu�rio " + p1.getName() + ".");
			}
		}
		return false;
	}
}
