package com.wandy.api.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class RocketCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("rocket")) || (!(sender instanceof Player))) {
			sender.sendMessage("�cApenas usu�rios in-game podem executar este comando.");
			return true;
		}
		if (!sender.hasPermission("wandy.rocket")) {
			sender.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (args.length == 0) {
			p.sendMessage("�cUtilize /rocket <raio> para lan�ar os usu�rios pra cima.");
			return true;
		}
		if (args.length == 1) {
			if (isInt(args[0])) {
				int raio = Integer.parseInt(args[0]);
				if (raio > 100) {
					p.sendMessage("�cVoc� n�o pode lan�ar esse raio, utilize no m�ximo 100.");
					return true;
				}
				if (raio < 8) {
					p.sendMessage("�cVoc� n�o pode lan�ar esse raio, utilize no m�nimo 8.");
					return true;
				}
				for (Entity player : p.getNearbyEntities(raio, raio, raio)) {
					if ((player instanceof Player)) {
						if (!player.getName().equals(p.getName())) {
							player.sendMessage("�aVoc� foi lan�ado pelo usu�rio " + p.getName() + ".");
							player.teleport(player.getLocation().clone().add(0.0D, 10.0D, 0.0D));
						}
					}
				}
				p.sendMessage("�aVoc� lan�ou os usu�rios em um raio de " + raio + " blocos.");
				return true;
			}
			p.sendMessage("�cVoc� inseriu um n�mero inv�lido.");
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
