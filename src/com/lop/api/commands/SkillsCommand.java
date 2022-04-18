package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wandy.api.listeners.SkillsListener;

public class SkillsCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("skills")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (args.length == 0) {
			SkillsListener.abrirMenu(p);
		}
		if (args.length > 0) {
			String nome = args[0];
			if (nome.equalsIgnoreCase("top")) {
				SkillsListener.abrirTopRank(p);
				return true;
			}
			if (Bukkit.getPlayer(nome) == null) {
				p.sendMessage("§cEste usuário não está on-line.");
				return true;
			}
			Player p1 = Bukkit.getPlayer(nome);
			SkillsListener.abrirMenuOutro(p, p1);
			return true;
		}
		return false;
	}
}
