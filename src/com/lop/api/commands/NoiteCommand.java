package com.wandy.api.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NoiteCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("noite")) || (!sender.hasPermission("wandy.noite"))) {
			sender.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		World world = p.getWorld();
		world.setTime(19000L);
		p.sendMessage("�aVoc� alterou o tempo deste mundo para: NOITE.");
		return false;
	}
}
