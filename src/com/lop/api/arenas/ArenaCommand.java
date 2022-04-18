package com.wandy.api.arenas;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("arena")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		ArenaMenu.abrirMenu(p);
		return false;
	}
}