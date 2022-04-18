package com.wandy.api.commands;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpAllCommand implements CommandExecutor {

	ArrayList<String> lista = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tpall")) {
			if ((sender instanceof Player)) {
				Player p = (Player) sender;
				if (p.hasPermission("wandy.tpall")) {
					if (this.lista.contains(p.getName())) {
						for (Player p3 : Bukkit.getOnlinePlayers()) {
							p3.teleport(p.getLocation());
						}
						this.lista.remove(p.getName());
						p.sendMessage("§aTodos os usuários conectados no servidor foram teleportados para sua localização.");
					} else {
						p.sendMessage("§eVocê tem certeza que deseja teleportar todos os usuários conectados no servidor para sua localização?");
						p.sendMessage("§eCaso tenha certeza digite '/tpall' novamente.");
						this.lista.add(p.getName());
					}
				} else {
					p.sendMessage("§cVocê não tem permissão para executar este comando.");
				}
			}
		}
		return false;
	}
}
