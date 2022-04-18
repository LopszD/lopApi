package com.wandy.api.commands;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class VanishCommand implements CommandExecutor, Listener {

	public static ArrayList<String> vanish = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("vanish")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		if (!sender.hasPermission("wandy.vanish")) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (FakeCommand.temFake(p.getName())) {
			p.sendMessage("§cVocê não pode utilizar este comando enquanto estiver de fake.");
			return true;
		}
		if (vanish.contains(p.getName())) {
			vanish.remove(p.getName());
			for (Player todos : Bukkit.getOnlinePlayers()) {
				todos.showPlayer(p);
			}
			p.sendMessage("§cModo invisível desligado!");
			return true;
		}
		vanish.add(p.getName());
		for (Player todos : Bukkit.getOnlinePlayers()) {
			if (!todos.hasPermission("wandy.vanish")) {
				todos.hidePlayer(p);
			}
			if (todos.hasPermission("wandy.vanish")) {
				todos.showPlayer(p);
			}
		}
		p.sendMessage("§aModo invisível ativado!");
		return false;
	}

	@EventHandler
	public static void aoSair(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (vanish.contains(p.getName())) {
			vanish.remove(p.getName());
		}
	}
}
