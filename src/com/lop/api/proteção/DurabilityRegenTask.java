package com.wandy.api.proteção;

import org.bukkit.scheduler.BukkitRunnable;

public class DurabilityRegenTask extends BukkitRunnable {
	
	public void run() {
		BlockStatus.getBlockStatus.clear();
	}
}
