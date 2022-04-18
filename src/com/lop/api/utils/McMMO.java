package com.wandy.api.utils;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.gmail.nossr50.datatypes.skills.AbilityType;
import com.gmail.nossr50.events.skills.abilities.McMMOPlayerAbilityActivateEvent;

public class McMMO implements Listener {
	
	@EventHandler
	public void aoAtivarSkill(McMMOPlayerAbilityActivateEvent e) {
		if (e.getAbility() == AbilityType.SUPER_BREAKER) {
			if (e.getPlayer().getItemInHand().getItemMeta().equals("")) {
				e.setCancelled(true);
			}
		}
	}
	
}