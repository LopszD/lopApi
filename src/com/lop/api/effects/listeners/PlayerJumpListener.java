package com.wandy.api.effects.listeners;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import com.wandy.api.effects.EffectType;
import com.wandy.api.effects.manager.EffectManager;

public class PlayerJumpListener implements Listener {

	@EventHandler
	public void onJump(PlayerMoveEvent e){
		if (e.getFrom().getY() < (e.getTo().getY() + 0.1)){
			Player p = e.getPlayer();
			if (EffectManager.effects.containsKey(p)){
				if (EffectManager.effects.get(p) == EffectType.JUMP){
					if (percentChance(4.0D)) {
						p.setFallDistance(-5.0F);
						Vector direction;
						direction = p.getEyeLocation().getDirection().multiply(0.1);
						direction.setY(0.5);
						p.setVelocity(direction);
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
