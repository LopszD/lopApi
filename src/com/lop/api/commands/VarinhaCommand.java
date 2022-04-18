package com.wandy.api.commands;

import java.util.ArrayList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VarinhaCommand implements CommandExecutor {
	
	public static ArrayList<String> vari = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("varinha")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("wandy.varinha")) {
			p.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (vari.contains(p.getName())) {
			p.sendMessage("§cBruxo, você desativou o modo VARINHA.");
			vari.remove(p.getName());
			return true;
		}
		p.sendMessage("§aBruxo, você ativou o modo VARINHA.");
		vari.add(p.getName());
		return false;
	}
}
