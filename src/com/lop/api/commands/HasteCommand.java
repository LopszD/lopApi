package com.wandy.api.commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.wandy.api.Main;
import com.wandy.api.utils.TimeUtils;

public class HasteCommand implements CommandExecutor {

	private static HashMap<Player, Long> cooldown = new HashMap<>();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("haste")) || (!sender.hasPermission("wandy.haste"))) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (!cooldown.containsKey(p)) {
				cooldown.put(p, System.currentTimeMillis());
				p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 60 * 5, 2));
				p.sendMessage("§aVocê ativou haste por 5 minutos!");
				new BukkitRunnable() {
					@Override
					public void run() {
						if (cooldown.containsKey(p))
							cooldown.remove(p);
					}
				}.runTaskLaterAsynchronously(Main.getInstance(), 20L * 60 * 10);
				return true;
			}
			p.sendMessage("§cAguarde " + TimeUtils.formatTimeExtense((int) (600L - (System.currentTimeMillis() - cooldown.get(p)) / 1000L)) + "§c para utilizar este comando novamente.");
			return true;
		}
		return false;
	}

	public static HashMap<Player, Long> getCooldown() {
		return cooldown;
	}
}
