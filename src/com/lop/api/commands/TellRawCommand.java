package com.wandy.api.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TellRawCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("tellraw")) || (args.length >= 1)) {
			String nick = args[0];
			if (nick.equalsIgnoreCase(sender.getName())) {
				sender.sendMessage("§cCancelado!");
				return true;
			}
		}
		return false;
	}
}