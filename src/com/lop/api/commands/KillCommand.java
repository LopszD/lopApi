package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("kill")) || (!sender.hasPermission("wandy.kill"))) {
			sender.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			Player p = (Player) sender;
			p.setHealth(0.0D);
			p.sendMessage("�aVoc� se matou!");
			return true;
		}
		if (args.length == 1) {
			Player p = (Player) sender;
			Player p1 = Bukkit.getServer().getPlayer(args[0]);
			if (!p.hasPermission("wandy.kill.others")) {
				if (p1 == null) {
					p.sendMessage("�cEste usu�rio n�o est� on-line.");
					return true;
				}
				p1.setHealth(0.0D);
				p.sendMessage("�a" + p1.getName() + " morreu!");
			}
		}
		return false;
	}
}
