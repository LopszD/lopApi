package com.wandy.api.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.wandy.api.listeners.RedstoneListener;

public class RedstoneCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender p, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("redstone")) || (!p.hasPermission("wandy.redstone"))) {
			p.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("§cUtilize /redstone <on/off> para gerenciar a redstone.");
			return true;
		}
		if (args.length >= 1) {
			String util = args[0];
			if (util.equalsIgnoreCase("on")) {
				if (!RedstoneListener.taDesativada()) {
					p.sendMessage("§cRedstone já está ativada.");
					return true;
				}
				p.sendMessage("§aVocê ativou a Redstone.");
				RedstoneListener.ativarRed();
				return true;
			}
			if (util.equalsIgnoreCase("off")) {
				if (RedstoneListener.taDesativada()) {
					p.sendMessage("§cRedstone já está desativada.");
					return true;
				}
				p.sendMessage("§aVocê desativou a Redstone.");
				RedstoneListener.desativarRed();
				return true;
			}
			p.sendMessage("§cOpção '" + util + "' não encontrada.");
			return true;
		}
		return false;
	}
}
