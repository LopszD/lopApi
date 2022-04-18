package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wandy.api.Main;

public class SairComEstiloCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("saircomestilo")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		if (!sender.hasPermission("wandy.saircomestilo")) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		final Player p = (Player) sender;
		for (final Player todos : Bukkit.getOnlinePlayers()) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
				@SuppressWarnings("deprecation")
				public void run() {
					todos.sendTitle("§f§l3", "");
				}
			}, 20L);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
				@SuppressWarnings("deprecation")
				public void run() {
					todos.sendTitle("§f§l2", "");
				}
			}, 40L);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
				@SuppressWarnings("deprecation")
				public void run() {
					todos.sendTitle("§f§l1", "");
				}
			}, 60L);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
				@SuppressWarnings("deprecation")
				public void run() {
					p.kickPlayer("§cVocê saiu com estilo!");
					todos.sendTitle("§c§l" + p.getName(), "§csaiu do servidor.");
					Bukkit.getWorld(p.getWorld().getName()).strikeLightning(p.getLocation());
				}
			}, 80L);
		}
		return false;
	}
}
