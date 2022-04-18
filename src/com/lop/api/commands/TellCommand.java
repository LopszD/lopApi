package com.wandy.api.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wandy.api.listeners.ToggleListener;
import com.wandy.api.utils.fanciful.FancyMessage;

public class TellCommand implements CommandExecutor {
	
	public static HashMap<String, String> tell = new HashMap<String, String>();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("tell")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage("§cUtilize /tell <usuário> <mensagem> para enviar uma mensagem privada.");
			return true;
		}
		if (args.length == 1) {
			sender.sendMessage("§cUtilize /tell <usuário> <mensagem> para enviar uma mensagem privada.");
			return true;
		}
		if (args.length > 1) {
			Player p1 = Bukkit.getPlayer(args[0]);
			if (p1 == null) {
				sender.sendMessage("§cEste usuário não está on-line.");
				return true;
			}
			Player p = (Player) sender;
			if (args[0].equalsIgnoreCase(p.getName())) {
				p.sendMessage("§cEvite enviar mensagens para si mesmo, porque você não vai conhecer novas pessoas? =P");
				return true;
			}
			if (VanishCommand.vanish.contains(p1.getName())) {
				sender.sendMessage("§cEste usuário não está on-line.");
				return true;
			}
			if (ToggleListener.canceltell.contains(p1.getName())) {
				p.sendMessage("§cEste usuário desativou o recebimento de mensagens privadas.");
				return true;
			}
			tell.put(p.getName(), p1.getName());
			tell.put(p1.getName(), p.getName());
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
			new FancyMessage("§8[Mensagem de §7").then(p.getName()).tooltip("§6Clique para responder").suggest("/tell " + p.getName() + " ").color(ChatColor.DARK_GRAY).then("§8]:§6").then(message.replace(args[0], "")).color(ChatColor.GOLD).send(p1);
			new FancyMessage("§8[Mensagem para §7").then(p1.getName()).tooltip("§6Clique para responder").suggest("/tell " + p1.getName() + " ").color(ChatColor.DARK_GRAY).then("§8]:§6").then(message.replace(args[0], "")).color(ChatColor.GOLD).send(p);
			return true;
		}
		return false;
	}
}
