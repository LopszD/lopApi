package com.wandy.api.effects.listeners;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.wandy.api.effects.EffectType;
import com.wandy.api.effects.manager.EffectManager;

public class PlayerDamangeListener implements Listener {

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		if (e.isCancelled()) {
		} else {
			if (!e.isCancelled()){
				if (e.getEntity() instanceof Player){
					if (e.getDamager() instanceof Player){
						Player p = (Player) e.getEntity();
						Player damager = (Player) e.getDamager();
						if (EffectManager.effects.containsKey(damager)){
							if (EffectManager.effects.get(damager) == EffectType.ZEUS) {
								if (percentChance(3.2D)){
									p.getWorld().strikeLightning(p.getLocation());
								}
							}
							if (EffectManager.effects.get(damager) == EffectType.POWER){
								if (percentChance(3.4D)){
									damager.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 60, 2));
								}
							}
						}
						if (EffectManager.effects.containsKey(p)){
							if (EffectManager.effects.get(p) == EffectType.HEALTH){
								if (percentChance(3.7D)){
									p.setHealth(p.getHealth()+2);
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
