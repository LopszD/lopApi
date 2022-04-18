package com.wandy.api.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.wandy.api.Main;
import com.wandy.api.utils.PermissionsAPI;
import com.wandy.api.utils.TimeUtils;

public class GravarCommand implements CommandExecutor {

	private static HashMap<Player, Long> cooldown = new HashMap<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String CL, String[] args) {
		if ((!cmd.getName().equalsIgnoreCase("gravar")) || (!sender.hasPermission("wandy.youtuber"))) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (!cooldown.containsKey(p)) {
			cooldown.put(p, System.currentTimeMillis());
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" §c§l[GRAVANDO] §fO " + PermissionsAPI.getPrefix(p.getName()) + p.getName() + " §festá");
			Bukkit.broadcastMessage("  §fgravando, mandem salve!");
			Bukkit.broadcastMessage(" ");
			for (Player x : Bukkit.getOnlinePlayers()) {
				x.playSound(x.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
			}
			new BukkitRunnable() {
				@Override
				public void run() {
					if (cooldown.containsKey(p))
						cooldown.remove(p);
				}
			}.runTaskLaterAsynchronously(Main.getInstance(), 20L * 60 * 10);
			return true;
		}
		p.sendMessage("§cEspere " + TimeUtils.formatTimeExtense((int) (600L - (System.currentTimeMillis() - cooldown.get(p)) / 1000L)) + " para executar novamente.");
		return false;
	}

	public static HashMap<Player, Long> getCooldown() {
		return cooldown;
	}
}
