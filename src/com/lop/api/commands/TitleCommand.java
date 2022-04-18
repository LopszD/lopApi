package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TitleCommand implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("title")) || (!sender.hasPermission("wandy.title"))) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage("§cUtilize /title <título> [subtítulo] para enviar uma mensagem para todos.");
			return true;
		}
		if (args.length == 1) {
			for (Player todos : Bukkit.getOnlinePlayers()) {
				todos.sendTitle(args[0].replace("&", "§").replace("%", " "), "");
			}
			return true;
		}
		if (args.length == 2) {
			for (Player todos : Bukkit.getOnlinePlayers()) {
				todos.sendTitle(args[0].replace("&", "§").replace("%", " "), args[1].replace("&", "§").replace("%", " "));
			}
			return true;
		}
		return false;
	}
}
