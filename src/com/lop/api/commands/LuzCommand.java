package com.wandy.api.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.wandy.api.Main;

public class LuzCommand implements CommandExecutor {

	public LuzCommand(Main plugin) {
		plugin.getCommand("luz").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
			player.removePotionEffect(PotionEffectType.NIGHT_VISION);
			player.sendMessage("§cSua luz foi desligada com sucesso.");
			player.playSound(player.getLocation(), Sound.CLICK, 1.0F, 1.0F);
		} else {
			player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1));
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
			player.sendMessage("§aSua luz foi ligada com sucesso.");
		}
		return false;
	}
}
