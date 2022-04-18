package com.wandy.api.commands.tpa;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String CL, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				p.sendMessage("�cUtilize /tpa <usu�rio> para enviar um pedido de teletransporte.");
				return true;
			}
			new MethodsTpa().sendTpa(p, args[0]);
			return true;
		}
		sender.sendMessage("�cApenas usu�rios in-game podem executar este comando.");
		return false;
	}
}
