package com.wandy.api.commands;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wandy.api.chat.ChatCommand;
import com.wandy.api.chat.ChatMenu;
import com.wandy.api.listeners.CombatLogListener;

public class ChatFakeCommand implements CommandExecutor {
	
	public static ArrayList<String> chat = new ArrayList<String>();
	public static HashMap<String, String> parecida = new HashMap<String, String>();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("chatfake")) || (!sender.hasPermission("whyze.chatfake"))) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage("§cUtilize /chatfake <nick> <mensagem> enviar uma mensagem fake para os usuários.");
			return true;
		}
		if (args.length == 1) {
			sender.sendMessage("§cUtilize /chatfake <nick> <mensagem> enviar uma mensagem fake para os usuários.");
			return true;
		}
		if (args.length > 1) {
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
			String nick = args[0];
			if (nick.equalsIgnoreCase("yleoosz")) {
				sender.sendMessage("§cVocê acha mesmo que vai conseguir da este comando no Leo?!");
				return true;
			}
			if (nick.equalsIgnoreCase("iflastbr")) {
				sender.sendMessage("§cVocê acha mesmo que vai conseguir da este comando na Leo?!");
				return true;
			}
			if (Bukkit.getPlayer(nick) == null) {
				sender.sendMessage("§cEste usuário não está on-line.");
				return true;
			}
			Player p1 = Bukkit.getPlayer(nick);
			if (ChatMenu.check.contains("GLOBAL")) {
				if (!p1.hasPermission("wandy.falarchat")) {
					sender.sendMessage("§cO chat está desativado e este usuário não tem permissão.");
					return true;
				}
			}
			if (CombatLogListener.combate.containsKey(p1.getName())) {
				sender.sendMessage("§cEste usuário está em combate.");
				return true;
			}
			if (ChatCommand.parecida.containsKey(p1.getName())) {
				if (!p1.hasPermission("wandy.falarchat")) {
					if (ChatCommand.parecida.get(p1.getName()).equalsIgnoreCase(message)) {
						sender.sendMessage("§cEstá mensagem está muito parecida com a anterior.");
						return true;
					}
				}
			}
			int fora = nick.length() + 1;
			String mensagem = message.replace("&", "§").substring(fora);
			Bukkit.dispatchCommand(p1, "g " + mensagem);
			return true;
		}
		return false;
	}
}
