package com.wandy.api.effects.listeners;

import java.util.Random;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.wandy.api.effects.EffectType;
import com.wandy.api.effects.manager.EffectManager;

public class EnderPearlDamageListener implements Listener {

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		if (e.isCancelled()){
			return;
		} else {
			if (!e.isCancelled()){
				if (e.getEntity() instanceof Player){
					Player p = (Player) e.getEntity();
					if (e.getDamager().getType() == EntityType.ENDER_PEARL){ 
						if (EffectManager.effects.containsKey(p)){
							if (EffectManager.effects.get(p) == EffectType.ENDER_PEARL){
								if (percentChance(2.9D)){
									e.setCancelled(true);
									return;
								}
							}
						}
					}
				}
			}
		}
	}

	protected Random random = new Random();
	protected final boolean percentChance(double percent) {
		if (percent < 0 || percent > 100)
			throw new IllegalArgumentException("A percentagem nao pode ser maior do que 100 nem menor do que 0");
		double result = random.nextDouble() * 100;
		return result <= percent;
	}
}
