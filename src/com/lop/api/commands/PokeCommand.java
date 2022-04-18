package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PokeCommand implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender p, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("poke")) || (!p.hasPermission("wandy.poke"))) {
			p.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("�cUtilize /poke <usu�rio> <t�tulo> [subt�tulo] para alertar o usu�rio.");
			return true;
		}
		if (args.length == 1) {
			p.sendMessage("�cUtilize /poke <usu�rio> <t�tulo> [subt�tulo] para alertar o usu�rio.");
			return true;
		}
		if (args.length == 2) {
			String pl = args[0];
			if (Bukkit.getPlayer(pl) == null) {
				p.sendMessage("�cEste usu�rio n�o est� on-line.");
				return true;
			}
			String ti = args[1].replace("&", "�").replace("*", " ");
			Player p1 = Bukkit.getPlayer(pl);
			p1.sendTitle(ti, "�f");
			p.sendMessage("�aVoc� alertou " + p1.getName() + "!");
			if (!VanishCommand.vanish.contains(p.getName())) {
				p1.sendMessage("�aVoc� foi alertado por " + p.getName() + "!");
				p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
			}
			return true;
		}
		if (args.length == 3) {
			String pl = args[0];
			if (Bukkit.getPlayer(pl) == null) {
				p.sendMessage("�cEste usu�rio n�o est� on-line.");
				return true;
			}
			String ti = args[1].replace("&", "�").replace("%", " ");
			String su = args[2].replace("&", "�").replace("%", " ");
			Player p1 = Bukkit.getPlayer(pl);
			p1.sendTitle(ti, su);
			p.sendMessage("�aVoc� alertou " + p1.getName() + "!");
			if (!VanishCommand.vanish.contains(p.getName())) {
				p1.sendMessage("�aVoc� foi alertado por " + p.getName() + "!");
				p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
			}
			return true;
		}
		return false;
	}
}
