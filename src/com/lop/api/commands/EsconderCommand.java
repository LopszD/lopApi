package com.wandy.api.commands;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EsconderCommand implements CommandExecutor {
	
	public static ArrayList<String> esnd = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("esconder")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("wandy.esconder")) {
			p.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (esnd.contains(p.getName())) {
			esnd.remove(p.getName());
			p.sendMessage("§aTodos os usuários estão visíveis agora.");
			for (Player todos : Bukkit.getOnlinePlayers()) {
				p.showPlayer(todos);
			}
			return true;
		}
		esnd.add(p.getName());
		p.sendMessage("§aTodos os usuários estão invisíveis.");
		for (Player todos : Bukkit.getOnlinePlayers()) {
			if (!todos.hasPermission("wandy.equipe")) {
				p.hidePlayer(todos);
			}
		}
		return false;
	}
}
