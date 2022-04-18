package com.wandy.api.especiais.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.wandy.api.utils.FireworkEffectPlayer;

public class PaperListener implements Listener {

	FireworkEffectPlayer fplayer = new FireworkEffectPlayer();

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onInteractt(PlayerInteractEvent e) throws Exception {
		Player p = e.getPlayer();
		ItemStack item = p.getItemInHand();
		if (item != null) {
			if (item.getType() == Material.PAPER) {
				if (p.getItemInHand().getItemMeta().getDisplayName().equals("§eVIP §9Extra")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ativarvip " + p.getName() + " betther 7");
					fplayer.playFirework(p.getWorld(), p.getLocation(), getRandomEffect(Color.BLUE));
					removeitem(p, 1);
					return;
				}
			}
		}
		if (item.getType() == Material.PAPER) {
			if (p.getItemInHand().getItemMeta().getDisplayName().equals("§eVIP §2Extra")) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ativarvip " + p.getName() + " ghuenon 7");
				fplayer.playFirework(p.getWorld(), p.getLocation(), getRandomEffect(Color.GREEN));
				removeitem(p, 1);
				return;
			}
		}
	}

	public void removeitem(Player p, int quantia) {
		if (p.getItemInHand().getAmount() > quantia) {
			p.getItemInHand().setAmount(p.getItemInHand().getAmount() - quantia);
			p.updateInventory();
		} else {
			p.setItemInHand(null);
			p.updateInventory();
		}
	}

	public static FireworkEffect getRandomEffect(Color color) {
		return FireworkEffect.builder().with(org.bukkit.FireworkEffect.Type.BALL).withColor(color).build();
	}
}
