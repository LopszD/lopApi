package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("kick")) || (!sender.hasPermission("wandy.kick"))) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (args.length == 0) {
			p.sendMessage("§cUtilize /kick <usuário> <motivo> para kickar um usuário.");
			return true;
		}
		if (args.length == 1) {
			Player p1 = Bukkit.getServer().getPlayer(args[0]);
			if (p1 == null) {
				sender.sendMessage("§cEste usuário não está on-line.");
				return true;
			}
			p.sendMessage("§aVocê expulsou o usuário " + p1.getName() + " com sucesso!");
			p1.kickPlayer("§c§lREDE WANDY\n§c\n§cVocê foi kickado pelo usuário '" + p.getName() + "'.");
			return true;
		}
		if (args.length == 2) {
			Player p1 = Bukkit.getServer().getPlayer(args[0]);
			if (p1 == null) {
				sender.sendMessage("§cEste usuário não está on-line!");
				return true;
			}
			if (args[1].equalsIgnoreCase("afk")) {
				p.sendMessage("§aVocê expulsou o usuário " + p1.getName() + " com sucesso!");
				p1.kickPlayer("§c§lREDE WANDY\n§c\n§cVocê ficou inativo e foi kickado do servidor.");
				return true;
			}
			if (args[1].equalsIgnoreCase("conexao")) {
				p.sendMessage("§aVocê expulsou o usuário " + p1.getName() + " com sucesso!");
				p1.kickPlayer("§c§lREDE WANDY\n§c\n§cSua conexão foi perdida com o servidor.");
				return true;
			}
			if (args[1].equalsIgnoreCase("disconnect")) {
				p.sendMessage("§aVocê expulsou o usuário " + p1.getName() + " com sucesso!");
				p1.kickPlayer("§c§lREDE WANDY\n§c\n§cVocê foi desconectado do servidor.");
				return true;
			}
			p.sendMessage("§aVocê expulsou o usuário " + p1.getName() + " com sucesso!");
			p1.kickPlayer("§c§lREDE WANDY\n§c\n§cVocê foi kickado pelo usuário '" + p.getName() + "'.");
			return true;
		}
		return false;
	}
}
