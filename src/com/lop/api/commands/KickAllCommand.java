package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickAllCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if ((cmd.getName().equalsIgnoreCase("kickall")) && ((sender instanceof Player))) {
			Player p = (Player) sender;
			if (p.hasPermission("wandy.kickall")) {
				if (args.length < 1) {
					p.sendMessage("�cUtlize /kickall <motivo>.");
				}
				if (args.length == 1) {
					String motivo = args[0].replace("&", "�");
					for (Player connected : Bukkit.getOnlinePlayers()) {
						if (!connected.hasPermission("wandy.equipe")) {
							connected.kickPlayer("�c�lREDE WANDY\n�c\n�cVoc� foi kickado pelo usu�rio '" + p.getName() + "'.\n�c\nMotivo: " + motivo);
						}
					}
					p.sendMessage("�aTodos os usu�rios foram desconectados menos os membros da equipe.");
				}
			} else {
				p.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
			}
		}
		return false;
	}
}
