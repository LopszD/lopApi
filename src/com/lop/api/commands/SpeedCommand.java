package com.wandy.api.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SpeedCommand implements CommandExecutor, Listener {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("speed")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("wandy.speed")) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if ((args.length == 0) || (args.length > 2)) {
			sender.sendMessage("§cUtilize /speed <quantidade> para alterar sua velocidade ao voar.");
			return true;
		}
		if (args.length == 1) {
			if (isInt(args[0])) {
				int quant = Integer.valueOf(args[0]).intValue();
				if (quant > 10) {
					p.sendMessage("§cNúmero muito alto, utilize no máximo 10.");
					return true;
				}
				if (quant < 1) {
					p.sendMessage("§cNúmero muito baixo, utilize no mínimo 1.");
					return true;
				}
				if (quant == 1) {
					float a = 0.1F;
					p.setFlySpeed(a);
					p.sendMessage("§aVocê alterou o speed ao voar para " + quant + ".");
					return true;
				}
				if (quant == 2) {
					float a = 0.2F;
					p.setFlySpeed(a);
					p.sendMessage("§aVocê alterou o speed ao voar para " + quant + ".");
					return true;
				}
				if (quant == 3) {
					float a = 0.3F;
					p.setFlySpeed(a);
					p.sendMessage("§aVocê alterou o speed ao voar para " + quant + ".");
					return true;
				}
				if (quant == 4) {
					float a = 0.4F;
					p.setFlySpeed(a);
					p.sendMessage("§aVocê alterou o speed ao voar para " + quant + ".");
					return true;
				}
				if (quant == 5) {
					float a = 0.5F;
					p.setFlySpeed(a);
					p.sendMessage("§aVocê alterou o speed ao voar para " + quant + ".");
					return true;
				}
				if (quant == 6) {
					float a = 0.6F;
					p.setFlySpeed(a);
					p.sendMessage("§aVocê alterou o speed ao voar para " + quant + ".");
					return true;
				}
				if (quant == 7) {
					float a = 0.7F;
					p.setFlySpeed(a);
					p.sendMessage("§aVocê alterou o speed ao voar para " + quant + ".");
					return true;
				}
				if (quant == 8) {
					float a = 0.8F;
					p.setFlySpeed(a);
					p.sendMessage("§aVocê alterou o speed ao voar para " + quant + ".");
					return true;
				}
				if (quant == 9) {
					float a = 0.9F;
					p.setFlySpeed(a);
					p.sendMessage("§aVocê alterou o speed ao voar para " + quant + ".");
					return true;
				}
				if (quant == 10) {
					float a = 0.9F;
					p.setFlySpeed(a);
					p.sendMessage("§aVocê alterou o speed ao voar para " + quant + ".");
					return true;
				}
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

	@EventHandler
	public static void aoEntrar(PlayerJoinEvent e) {
		float a = 0.1F;
		e.getPlayer().setFlySpeed(a);
	}
}
