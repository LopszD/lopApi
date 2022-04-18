package com.wandy.api.chat;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatCommand implements CommandExecutor {
	
	public static ArrayList<String> chat = new ArrayList<String>();
	public static HashMap<String, String> parecida = new HashMap<String, String>();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("chat")) || (!sender.hasPermission("wandy.chat"))) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		ChatMenu.abrirMenu(p);
		return false;
	}
}
