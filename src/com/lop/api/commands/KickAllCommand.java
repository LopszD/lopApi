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
					p.sendMessage("§cUtlize /kickall <motivo>.");
				}
				if (args.length == 1) {
					String motivo = args[0].replace("&", "§");
					for (Player connected : Bukkit.getOnlinePlayers()) {
						if (!connected.hasPermission("wandy.equipe")) {
							connected.kickPlayer("§c§lREDE WANDY\n§c\n§cVocê foi kickado pelo usuário '" + p.getName() + "'.\n§c\nMotivo: " + motivo);
						}
					}
					p.sendMessage("§aTodos os usuários foram desconectados menos os membros da equipe.");
				}
			} else {
				p.sendMessage("§cVocê não tem permissão para executar este comando.");
			}
		}
		return false;
	}
}
