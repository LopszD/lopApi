package com.wandy.api.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wandy.api.effects.menu.SelectEffectMenu;

public class EfeitosCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("efeitos")) {
			if (!(sender instanceof Player)) return true;
			Player p = (Player) sender;
			SelectEffectMenu.open(p);
		}
		return false;
	}
}