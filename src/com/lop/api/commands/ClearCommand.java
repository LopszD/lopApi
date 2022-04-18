package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("clear")) {
			if (args.length == 1) {
				if (!p.hasPermission("wandy.clear")) {
					sender.sendMessage("§cVocê não tem permissão para executar este comando.");
					return true;
				}
				Player p1 = Bukkit.getPlayer(args[0]);
				if (p1 == null) {
					p.sendMessage("§cEste usuário não está on-line.");
					return true;
				}
				if (p1.hasPermission("wandy.clear.others")) {
					p.sendMessage("§cVocê não tem permissão para limpar o inventário este usuário.");
					return true;
				}
				p1.getInventory().clear();
				p1.getInventory().setBoots(null);
				p1.getInventory().setChestplate(null);
				p1.getInventory().setHelmet(null);
				p1.getInventory().setLeggings(null);
				p.sendMessage("§aVocê limpou todo o inventário do usuário " + p1.getName() + ".");
			} else {
				if (args.length == 0) {
					if (!p.hasPermission("wandy.clear")) {
						p.sendMessage("§cVocê não tem permissão para executar este comando.");
						return true;
					}
					p.getInventory().clear();
					p.getInventory().setBoots(null);
					p.getInventory().setChestplate(null);
					p.getInventory().setHelmet(null);
					p.getInventory().setLeggings(null);
					p.sendMessage("§aVocê limpou todo o seu inventário com sucesso.");
				}
				if (args.length > 1) {
					p.sendMessage("§cUtilize /clear <usuário>.");
					return true;
				}
			}
		}
		return false;
	}
}
