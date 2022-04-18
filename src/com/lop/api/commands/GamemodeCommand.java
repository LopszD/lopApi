package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("gamemode")) {
			if (!p.hasPermission("wandy.gamemode")) {
				sender.sendMessage("§cVocê não tem permissão para executar este comando.");
				return true;
			}
			if (args.length == 0) {
				p.sendMessage("§cUtilize /gamemode <modo> para alterar seu modo de jogo.");
				return true;
			}
			if (args.length >= 1) {
				String gm = args[0];
				if ((gm.equalsIgnoreCase("0")) || (gm.equalsIgnoreCase("survival"))) {
					if (args.length == 2) {
						if (!p.hasPermission("wandy.gamemode.others")) {
							p.sendMessage("§cVocê não tem permissão para setar este modo de jogo.");
							return true;
						}
						Player p1 = Bukkit.getPlayer(args[1]);
						if (p1 == null) {
							p.sendMessage("§cEste usuário não está on-line.");
							return true;
						}
						p1.setGameMode(GameMode.SURVIVAL);
						p1.sendMessage("§aSeu gamemode foi alterado para: " + p1.getGameMode() + "!");
						p.sendMessage("§aO gamemode do usuário " + p1.getName() + " foi alterado para " + p1.getGameMode() + "!");
					} else {
						p.setGameMode(GameMode.SURVIVAL);
						p.sendMessage("§aSeu gamemode foi alterado para: " + p.getGameMode() + "!");
					}
				}
				if ((gm.equalsIgnoreCase("1")) || (gm.equalsIgnoreCase("criativo"))) {
					if (!p.hasPermission("wandy.gamemode.others")) {
						p.sendMessage("§cVocê não tem permissão para setar este modo de jogo.");
						return true;
					}
					if (args.length == 2) {
						Player p1 = Bukkit.getPlayer(args[1]);
						if (p1 == null) {
							p.sendMessage("§cEste usuário não está on-line.");
							return true;
						}
						p1.setGameMode(GameMode.CREATIVE);
						p1.sendMessage("§aSeu gamemode foi alterado para: " + p1.getGameMode() + "!");
						p.sendMessage("§aO gamemode do usuário " + p1.getName() + " foi alterado para " + p1.getGameMode() + "!");
					} else {
						p.setGameMode(GameMode.CREATIVE);
						p.sendMessage("§aSeu gamemode foi alterado para: " + p.getGameMode() + "!");
					}
				}
				if ((gm.equalsIgnoreCase("2")) || (gm.equalsIgnoreCase("adventure"))) {
					if (!p.hasPermission("wandy.gamemode.others")) {
						p.sendMessage("§cVocê não tem permissão para setar este modo de jogo.");
						return true;
					}
					if (args.length == 2) {
						Player p1 = Bukkit.getPlayer(args[1]);
						if (p1 == null) {
							p.sendMessage("§cEste usuário não está on-line.");
							return true;
						}
						p1.setGameMode(GameMode.ADVENTURE);
						p1.sendMessage("§aSeu gamemode foi alterado para: " + p1.getGameMode() + "!");
						p.sendMessage("§aO gamemode do usuário " + p1.getName() + " foi alterado para " + p1.getGameMode() + "!");
					} else {
						p.setGameMode(GameMode.ADVENTURE);
						p.sendMessage("§aSeu gamemode foi alterado para: " + p.getGameMode() + "!");
					}
				}
				if ((gm.equalsIgnoreCase("3")) || (gm.equalsIgnoreCase("spectator"))) {
					if (args.length == 2) {
						if (!p.hasPermission("wandy.gamemode.others")) {
							p.sendMessage("§cVocê não tem permissão para setar este modo de jogo.");
							return true;
						}
						Player p1 = Bukkit.getPlayer(args[1]);
						if (p1 == null) {
							p.sendMessage("§cEste usuário não está on-line.");
							return true;
						}
						p1.setGameMode(GameMode.SPECTATOR);
						p1.sendMessage("§aSeu gamemode foi alterado para: " + p1.getGameMode() + "!");
						p.sendMessage("§aO gamemode do usuário " + p1.getName() + " foi alterado para " + p1.getGameMode() + "!");
					} else {
						p.setGameMode(GameMode.SPECTATOR);
						p.sendMessage("§aSeu gamemode foi alterado para: " + p.getGameMode() + "!");
					}
				}
			}
		}
		return false;
	}
}
