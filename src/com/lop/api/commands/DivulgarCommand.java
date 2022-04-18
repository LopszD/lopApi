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

public class DivulgarCommand implements CommandExecutor {

	private static HashMap<Player, Long> cooldown = new HashMap<>();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("divulgar")) || (!sender.hasPermission("wandy.youtuber"))) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (!sender.hasPermission("wandy.youtuber")) {
			sender.sendMessage("§cVocê não tem permisão para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage("§cUtilize /divulgar <vídeo>.");
			return true;
		}
		String message = args[0];
		if (!message.contains("https://")) {
			sender.sendMessage("§cUtilize apenas links.");
			return true;
		}
		Player p = (Player) sender;
		if (!cooldown.containsKey(p)) {
			cooldown.put(p, System.currentTimeMillis());
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage(" §c§l[DIVULGAÇãO] §fO " + PermissionsAPI.getPrefix(sender.getName()) + sender.getName() + " §festá divulgando:");
			Bukkit.broadcastMessage("  §f" + getMessage(args));
			Bukkit.broadcastMessage("");
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

	private String getMessage(String[] args) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			sb.append(String.valueOf(args[i]) + " ");
		}
		return sb.toString();
	}
}
