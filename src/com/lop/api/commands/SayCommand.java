package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SayCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("say")) || (!sender.hasPermission("wandy.say"))) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (!(sender instanceof Player)) {
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage("§cUtilize /say <mensagem> para enviar um alerta para todos os usuários.");
			return true;
		}
		if (args.length > 0) {
			String message = "";
			String[] arrayOfString;
			int j = (arrayOfString = args).length;
			for (int i = 0; i < j; i++) {
				String part = arrayOfString[i];
				if (message != "") {
					message = message + " ";
				}
				message = message + part;
			}
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage(" §6§lRede§e§lWandy §7>> §f" + message);
			Bukkit.broadcastMessage("");
		}
		return false;
	}
}
