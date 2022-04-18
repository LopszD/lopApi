package com.wandy.api.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiaCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("dia")) || (!sender.hasPermission("wandy.dia"))) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		World world = p.getWorld();
		world.setTime(0L);
		p.sendMessage("§aVocê alterou o tempo deste mundo para: DIA.");
		return false;
	}
}
