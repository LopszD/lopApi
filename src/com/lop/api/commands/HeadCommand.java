package com.wandy.api.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wandy.api.utils.ItemBuilder;

public class HeadCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("head")) {
			if (sender.hasPermission("wandy.head")) {
				if (!(sender instanceof Player)) {
					sender.sendMessage("§cApenas jogadores in-game!");
					return true;
				}
				Player p = (Player) sender;
				if (args.length == 0) {
					p.getInventory().addItem(new ItemBuilder(Material.SKULL_ITEM)
							.durability(3)
							.name("§eCabeça de " + p.getName())
							.owner(p.getName())
							.build());
					p.sendMessage("§eRecebida a sua cabeça.");
					return true;
				} else if (args.length == 1) {
					String owner = args[0];
					p.getInventory().addItem(new ItemBuilder(Material.SKULL_ITEM)
							.durability(3)
							.name("§eCabeça de " + owner)
							.owner(owner)
							.build());
					p.sendMessage("§eRecebida a cabeça de '" + owner + "'.");
					return true;
				}
				return true;
			} else {
				sender.sendMessage("§cVocê não tem permissão para execultar esse comando.");
				return true;
			}
		}
		return false;
	}

}
