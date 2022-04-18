package com.wandy.api.effects.listeners;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.wandy.api.effects.EffectType;
import com.wandy.api.effects.manager.EffectManager;

public class PlayerDeathListener implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		Player p = (Player)e.getEntity();
		if (EffectManager.effects.containsKey(p)){
			if (EffectManager.effects.get(p) == EffectType.DEATH){
				if (percentChance(7.0D)){
					p.getWorld().createExplosion(p.getLocation(), 2.0F);
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
