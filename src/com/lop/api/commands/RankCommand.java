package com.wandy.api.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wandy.api.sql.Manager;

public class RankCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("rank")) || (!(sender instanceof Player))) {
			sender.sendMessage("�cApenas usu�rios in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("wandy.rank")) {
			p.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("�cUtilize /rank <setar/deletar> [local]");
			return true;
		}
		if (args.length == 1) {
			p.sendMessage("�cUtilize /rank <setar/deletar> [local]");
			return true;
		}
		if (args.length >= 2) {
			if (args[0].equalsIgnoreCase("setar")) {
				Location loc = p.getLocation();
				String loca = args[1].toUpperCase();
				if (Manager.checarLocExiste(loca)) {
					Manager.updateLocation(loca, loc);
					p.sendMessage("�aLocaliza��o atualizada!");
					return true;
				}
				Manager.setarLocation(loca, loc);
				p.sendMessage("�aLocaliza��o setada!");
				return true;
			}
			if (args[0].equalsIgnoreCase("deletar")) {
				String loca = args[1].toUpperCase();
				if (!Manager.checarLocExiste(loca)) {
					p.sendMessage("�cEst� localiza��o n�o existe.");
					return true;
				}
				Manager.deletarLocation(loca);
				p.sendMessage("�aLocaliza��o deletada!");
				return true;
			}
			p.sendMessage("�cUtilize /rank <setar/deletar> [local]");
			return true;
		}
		return false;
	}
}
