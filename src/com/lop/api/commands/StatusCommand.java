package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StatusCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("status")) || (!sender.hasPermission("wandy.status"))) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		sender.sendMessage("");
		sender.sendMessage("§f Informações de momento sobre o servidor:");
		sender.sendMessage("");
		sender.sendMessage("§f Status: §aOn-line");
		sender.sendMessage("§f RAM: §7" + getRamUsage());
		sender.sendMessage("§f Players: §7" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
		sender.sendMessage("§f Versão: §7" + Bukkit.getBukkitVersion());
		sender.sendMessage("§f Processadores CPU: §7" + Runtime.getRuntime().availableProcessors());
		sender.sendMessage("§f Sistema operacional: §3" + System.getProperty("os.name"));
		sender.sendMessage("");
		sender.sendMessage("§a " + getRamUsageBar());
		sender.sendMessage("");
		return false;
	}

	private static String getRamUsageBar() {
		Runtime runtime = Runtime.getRuntime();
		long freemem = runtime.freeMemory() / 1048576L;
		long maxmem = runtime.maxMemory() / 1048576L;
		long usedmem = maxmem - freemem;
		float percent = (float) usedmem / (float) maxmem * 100.0F;
		int steps = (int) (percent / 5.0F);
		String membar = "§r§2";
		char color = 'c';
		for (int i = 0; i <= 30; i++) {
			if (i >= steps) {
				color = '7';
			}
			membar = membar + "§" + color + "|";
		}
		return membar;
	}

	public static String getRamUsage() {
		Runtime runtime = Runtime.getRuntime();
		long freemem = runtime.freeMemory() / 1048576L;
		long maxmem = runtime.maxMemory() / 1048576L;
		long usedmem = maxmem - freemem;
		float percent = (float) usedmem / (float) maxmem * 100.0F;
		String ret = "§7[" + usedmem + "MB/" + maxmem + "MB] §a(" + Math.floor(percent) + "%)";
		return ret;
	}
}
