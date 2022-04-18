package com.wandy.api.mana;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.wandy.api.Main;
import com.wandy.api.effects.database.EffectSQL;

public class ManaListener implements Listener {

	@EventHandler
	public void onjoin(PlayerJoinEvent e) {
		Player joiner = e.getPlayer();
		new BukkitRunnable() {
			@Override
			public void run() {
				if (SQLMana.hasAccount(joiner.getName().toLowerCase())) {
					SQLMana.loadAccount(joiner.getName());
				} else {
					SQLMana.createAccount(joiner.getName(), 0);
				}
				if (EffectSQL.hasAccount(joiner.getName().toLowerCase())) {
					EffectSQL.loadAccount(joiner.getName().toLowerCase());
				} else {
					EffectSQL.createAccount(joiner.getName().toLowerCase());
				}
			}
		}.runTaskAsynchronously(Main.getInstance());
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		new BukkitRunnable() {
			@Override
			public void run() {
				if (Main.getInstance().maniacs.containsKey(p.getName())) {
					ManaModel model = Main.getInstance().maniacs.get(p.getName());
					SQLMana.updateAccount(p.getName(), model.getMana());
				}
				if (Main.getInstance().effects.containsKey(p.getName().toLowerCase())) {
					EffectSQL.updateAccount(p.getName().toLowerCase());
				}
			}
		}.runTaskAsynchronously(Main.getInstance());
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity().getKiller();
		ManaModel mana = Main.getInstance().maniacs.get(p.getName());
		if (mana != null) {
			mana.addMana(1250);
		}
	}
}
