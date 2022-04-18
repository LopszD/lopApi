package com.wandy.api.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class RocketCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("rocket")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		if (!sender.hasPermission("wandy.rocket")) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (args.length == 0) {
			p.sendMessage("§cUtilize /rocket <raio> para lançar os usuários pra cima.");
			return true;
		}
		if (args.length == 1) {
			if (isInt(args[0])) {
				int raio = Integer.parseInt(args[0]);
				if (raio > 100) {
					p.sendMessage("§cVocê não pode lançar esse raio, utilize no máximo 100.");
					return true;
				}
				if (raio < 8) {
					p.sendMessage("§cVocê não pode lançar esse raio, utilize no mínimo 8.");
					return true;
				}
				for (Entity player : p.getNearbyEntities(raio, raio, raio)) {
					if ((player instanceof Player)) {
						if (!player.getName().equals(p.getName())) {
							player.sendMessage("§aVocê foi lançado pelo usuário " + p.getName() + ".");
							player.teleport(player.getLocation().clone().add(0.0D, 10.0D, 0.0D));
						}
					}
				}
				p.sendMessage("§aVocê lançou os usuários em um raio de " + raio + " blocos.");
				return true;
			}
			p.sendMessage("§cVocê inseriu um número inválido.");
		}
		return false;
	}

	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
