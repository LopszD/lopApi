package com.wandy.api.caixas.basica;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Firework {
	
	public static void setarFirework(Player p) {
		p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
	}
}
