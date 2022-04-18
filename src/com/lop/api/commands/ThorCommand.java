package com.wandy.api.commands;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class ThorCommand implements CommandExecutor {
	
	@EventHandler
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("thor")) {
			if (!p.hasPermission("wandy.thor")) {
				sender.sendMessage("§cVocê não tem permissão para executar este comando.");
				return true;
			}
			if (args.length == 0) {
				Location loc = p.getTargetBlock((Set<Material>) null, 20).getLocation();
				Bukkit.getWorld(p.getWorld().getName()).strikeLightning(loc);
				return true;
			}
			if (args.length == 1) {
				Player p1 = Bukkit.getPlayer(args[0]);
				if (p1 == null) {
					p.sendMessage("§cEste usuário não está on-line.");
					return true;
				}
				Bukkit.getWorld(p1.getWorld().getName()).strikeLightning(p1.getLocation());
			}
		}
		return false;
	}
}
