package com.wandy.api.commands.tpa;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpAceitarCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String CL, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			new MethodsTpa().acceptTpa(p, args);
			return true;
		}
		sender.sendMessage("�cApenas usu�rios in-game podem executar este comando.");
		return false;
	}
}
