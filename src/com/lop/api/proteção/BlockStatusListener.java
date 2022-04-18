package com.wandy.api.proteção;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockStatusListener implements Listener {
	
	@EventHandler
	public void onClickStick(PlayerInteractEvent e) {
		if ((e.getItem() != null) && (e.getClickedBlock() != null) && (e.getItem().getType() == Material.STICK)) {
			if (e.getItem().getItemMeta().hasDisplayName()) {
				if (e.getItem().getItemMeta().getDisplayName().contains("§eFuradeira")) {
					return;
				}
			}
			Block b = e.getClickedBlock();
			Player p = e.getPlayer();
			if (b.getLocation().getY() == 127.0D) {
				p.sendMessage("§cMeu jovem, você quer ir aonde?!");
				return;
			}
			if (b.getLocation().getY() == 0.0D) {
				p.sendMessage("§cMeu jovem, você quer ir aonde?!");
				return;
			}
			BlockStatus.sendDurability(b, p);
		}
	}
}
