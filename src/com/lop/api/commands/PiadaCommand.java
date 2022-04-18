package com.wandy.api.commands;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wandy.api.utils.fanciful.FancyMessage;

public class PiadaCommand implements CommandExecutor {
	
	public static ArrayList<String> chat = new ArrayList<String>();
	public static HashMap<String, String> parecida = new HashMap<String, String>();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("piada")) || (!sender.hasPermission("wandy.piada"))) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage("§cUtilize /piada <mensagem> enviar uma piada para os usuários.");
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
			for (Player todos : Bukkit.getOnlinePlayers()) {
				todos.sendMessage("§1");
				new FancyMessage("").then(" §a" + message).tooltip("§aEnviado por " + sender.getName()).color(ChatColor.GREEN).send(todos);
				todos.sendMessage("§2");
			}
			return true;
		}
		return false;
	}
}
