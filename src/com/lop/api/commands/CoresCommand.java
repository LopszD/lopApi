package com.wandy.api.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoresCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("cores")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		p.sendMessage("§1Azul §f- & + 1");
		p.sendMessage("§2Verde escuro §f- & + 2");
		p.sendMessage("§3Ciano §f- & + 3");
		p.sendMessage("§4Vermelho escuro §f- & + 4");
		p.sendMessage("§5Roxo §f- & + 5");
		p.sendMessage("§6Dourado §f- & + 6");
		p.sendMessage("§7Cinza §f- & + 7");
		p.sendMessage("§8Cinza escuro §f- & + 8");
		p.sendMessage("§9Azul escuro §f- & + 9");
		p.sendMessage("§aVerde claro §f- & + a");
		p.sendMessage("§bAzul claro §f- & + b");
		p.sendMessage("§cVermelho claro §f- & + c");
		p.sendMessage("§dRosa §f- & + d");
		p.sendMessage("§eAmarelo §f- & + e");
		p.sendMessage("§fBranco §f- & + f");
		return false;
	}
}
