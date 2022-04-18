package com.wandy.api.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.wandy.api.utils.ItemBuilder;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class DropHeadListener implements Listener {

	@EventHandler
	public void playerDeath(PlayerDeathEvent e) {
		if ((e.getEntity() instanceof Player)) {
			Player k = e.getEntity();
			if ((k.getKiller() instanceof Player)) {
				Player killer = k.getKiller();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = new Date();
				if (killer.hasPermission("wandy.vip")) {
					if (percentChance(2.3D)) {
						killer.getWorld().dropItemNaturally(killer.getLocation(), new ItemBuilder(Material.SKULL_ITEM).setName("§7Cabeça de " + PermissionsEx.getUser(k.getName()).getPrefix().replace("&", "§") + k.getName()).setDurability((short) 3).setSkullOwner(k.getName()).setLore(new String[] { "§7Morto em: §f" + formatter.format(date) }).toItemStack());
					}
				} else if (percentChance(1.0D)) {
					killer.getWorld().dropItemNaturally(killer.getLocation(), new ItemBuilder(Material.SKULL_ITEM).setName("§7Cabeça de " + PermissionsEx.getUser(k.getName()).getPrefix().replace("&", "§") + k.getName()).setDurability((short) 3).setSkullOwner(k.getName()).setLore(new String[] { "§7Morto em: §f" + formatter.format(date) }).toItemStack());
				}
			}
		}
	}

	public static boolean percentChance(double percent) {
		if ((percent > 100.0D) || (percent < 0.0D)) {
			throw new IllegalArgumentException("Percentage cannot be greater than 100 or less than 0!");
		}
		double result = new Random().nextDouble() * 100.0D;
		return result <= percent;
	}
}
