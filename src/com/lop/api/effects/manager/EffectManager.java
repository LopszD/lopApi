package com.wandy.api.effects.manager;

import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

import com.wandy.api.effects.EffectType;

public class EffectManager {

	public static HashMap<Player, EffectType> effects = new HashMap<>();
	
	private String username;
	private List<EffectType> user_effects;
	
	public EffectManager(String username, List<EffectType> effects) {
		this.username = username;
		this.user_effects = effects;
	}
	
	public String getName() {
		return this.username;
	}
	
	public List<EffectType> getEffects(){
		return this.user_effects;
	}
	
	public void addEffect(EffectType effect) {
		this.user_effects.add(effect);
	}
	
	public boolean hasEffect(EffectType effect) {
		if(this.user_effects == null) return false;
		
		return this.user_effects.contains(effect);
	}
}
