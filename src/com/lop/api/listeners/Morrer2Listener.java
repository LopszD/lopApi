package com.wandy.api.listeners;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;

import com.wandy.api.Main;
import com.wandy.api.commands.PotionsCommand;
import com.wandy.api.sql.Manager;

public class Morrer2Listener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoRenascer(PlayerRespawnEvent e) {
		if (PotionsCommand.efeitos.size() >= 1) {
			for (String nome : PotionsCommand.efeitos) {
				int i = 1;
				if (nome.contains("II")) {
					i = 2;
				}
				e.getPlayer().addPotionEffect(new PotionEffect(PotionsCommand.getTipo(nome), 90000, i));
			}
		}
		if (e.getPlayer().hasPermission("wandy.vip")) {
			if (Manager.checarLocExiste("VIP")) {
				Random r = new Random();
				int rn = r.nextInt(4);
				if (rn == 1) {
					Location loc = Manager.pegarLocation("VIP").clone().subtract(Main.spawn.intValue(), 0.0D, 0.0D);
					e.setRespawnLocation(loc);
				}
				if (rn == 2) {
					Location loc = Manager.pegarLocation("VIP").clone().subtract(0.0D, 0.0D, Main.spawn.intValue());
					e.setRespawnLocation(loc);
				}
				if (rn == 3) {
					Location loc = Manager.pegarLocation("VIP").clone().add(Main.spawn.intValue(), 0.0D, 0.0D);
					e.setRespawnLocation(loc);
				}
				if (rn == 4) {
					Location loc = Manager.pegarLocation("VIP").clone().add(0.0D, 0.0D, Main.spawn.intValue());
					e.setRespawnLocation(loc);
				}
				if (rn == 0) {
					Location loc = Manager.pegarLocation("VIP").clone().add(0.0D, 0.0D, Main.spawn.intValue());
					e.setRespawnLocation(loc);
				}
			}
			return;
		}
		if (Manager.checarLocExiste("SPAWN")) {
			Random r = new Random();
			int rn = r.nextInt(4);
			if (rn == 1) {
				Location loc = Manager.pegarLocation("SPAWN").clone().subtract(Main.spawn.intValue(), 0.0D, 0.0D);
				e.setRespawnLocation(loc);
			}
			if (rn == 2) {
				Location loc = Manager.pegarLocation("SPAWN").clone().subtract(0.0D, 0.0D, Main.spawn.intValue());
				e.setRespawnLocation(loc);
			}
			if (rn == 3) {
				Location loc = Manager.pegarLocation("SPAWN").clone().add(Main.spawn.intValue(), 0.0D, 0.0D);
				e.setRespawnLocation(loc);
			}
			if (rn == 4) {
				Location loc = Manager.pegarLocation("SPAWN").clone().add(0.0D, 0.0D, Main.spawn.intValue());
				e.setRespawnLocation(loc);
			}
			if (rn == 0) {
				Location loc = Manager.pegarLocation("SPAWN").clone().add(0.0D, 0.0D, Main.spawn.intValue());
				e.setRespawnLocation(loc);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoMorrer(PlayerDeathEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		e.setDeathMessage("");
		Player p = e.getEntity();
		if (p.hasPermission("wandy.vip")) {
			e.setDroppedExp(0);
			e.setDeathMessage("");
			e.setKeepLevel(true);
			return;
		}
		e.setDeathMessage("");
	}
}
