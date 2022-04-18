package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvseeCommand implements CommandExecutor {
	
	@SuppressWarnings("null")
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if ((cmd.getName().equalsIgnoreCase("invsee")) && ((sender instanceof Player))) {
			Player p = (Player) sender;
			if (p.hasPermission("wandy.invsee")) {
				if (args.length < 1) {
					p.sendMessage("�cUtilize /invsee <usu�rio>.");
				}
				if (args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);
					if (target != null) {
						p.openInventory(target.getInventory());
					} else {
						p.sendMessage("�cN�o foi poss�vel abrir o invent�rio do usu�rio " + target.getName() + "�c! Pois ele est� off-line.");
					}
				}
			} else {
				p.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
			}
		}
		return false;
	}
}
