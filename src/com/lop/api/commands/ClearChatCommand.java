package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ClearChatCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("limparchat")) || (!sender.hasPermission("wandy.limparchat"))) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		for (int i = 0; i < 150; i++) {
			Bukkit.broadcastMessage("§r");
		}
		return false;
	}
}
