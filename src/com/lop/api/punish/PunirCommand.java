package com.wandy.api.punish;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PunirCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("punir")) || (!(sender instanceof Player))) {
			sender.sendMessage("�cApenas usu�rios in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("wandy.punir")) {
			p.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("�cUtilize /punir <usu�rio> para exibir os tipos de infra��es.");
			return true;
		}
		if (args.length == 1) {
			String nick = args[0];
			PunirS.enviarJogador(p, nick);
			return true;
		}
		if (args.length > 1) {
			p.sendMessage("�cUtilize /punir <usu�rio> para exibir os tipos de infra��es.");
			return true;
		}
		return false;
	}
}
