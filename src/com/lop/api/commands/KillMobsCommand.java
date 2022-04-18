package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wandy.api.Main;

public class KillMobsCommand implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("killmobs")) || (!sender.hasPermission("wandy.killmobs"))) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		World world = p.getWorld();
		world.setDifficulty(Difficulty.PEACEFUL);
		p.sendMessage("§aTodos os mobs deste mundo foram mortos.");
		Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getInstance(), new Runnable() {
			public void run() {
				world.setDifficulty(Difficulty.NORMAL);
			}
		}, 20L);
		return false;
	}
}
