package com.wandy.api.commands;

import java.util.ArrayList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class DropsCommand implements CommandExecutor, Listener {
	
	public static ArrayList<String> drops = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("drops")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		if (!sender.hasPermission("wandy.drops")) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (drops.contains(p.getName())) {
			drops.remove(p.getName());
			p.sendMessage("§cVocê desativou as informações dos drops.");
		} else {
			drops.add(p.getName());
			p.sendMessage("§aVocê ativou as informações dos drops.");
		}
		return false;
	}
}
