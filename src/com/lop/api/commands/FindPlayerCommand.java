package com.wandy.api.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wandy.api.listeners.FindPlayerListener;

public class FindPlayerCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("findplayer")) || (!(sender instanceof Player))) {
			sender.sendMessage("�cApenas usu�rios in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("wandy.findplayer")) {
			p.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("�cUtilize /findplayer <raio> para encontrar usu�rios com um determinado raio.");
			return true;
		}
		if (args.length == 1) {
			if (isInt(args[0])) {
				int raio = Integer.valueOf(args[0]).intValue();
				FindPlayerListener.abrirMenu(p, Integer.valueOf(raio));
				return true;
			}
			p.sendMessage("�cVoc� inseriu um raio inv�lido.");
			return true;
		}
		if (args.length > 1) {
			p.sendMessage("�cUtilize /findplayer <raio> para encontrar usu�rios com um determinado raio.");
			return true;
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
