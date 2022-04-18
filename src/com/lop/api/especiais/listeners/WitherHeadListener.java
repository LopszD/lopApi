package com.wandy.api.especiais.listeners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.massivecore.ps.PS;
import com.wandy.api.Main;
import com.wandy.api.utils.Proteção;

public class WitherHeadListener implements Listener {

	public List<Block> g(Block b, int a) {
		ArrayList<Block> list = new ArrayList<Block>();
		int n2;
		for (int i = n2 = -a; i <= a; i = n2) {
			int n3;
			for (int j = n3 = -a; j <= a; j = n3) {
				int n4;
				int k = n4 = -a;
				while (k <= a) {
					list.add(b.getRelative(n2, n3, n4++));
					k = n4;
				}
				n3++;
			}
			n2++;
		}
		ArrayList<Block> list2 = list;
		list2.remove(a);
		return list2;
	}

	@EventHandler
	public void explosion(EntityExplodeEvent e) {
		if (((e.getEntity() instanceof Fireball)) && (e.getEntity().hasMetadata("specialskull"))) {
			Iterator<Block> blocks = g(e.getLocation().getBlock(), 5).iterator();
			while (blocks.hasNext()) {
				Block block = (Block) blocks.next();
					Vector direction = block.getLocation().getDirection().multiply(0.5D);
					Projectile projectile = (Projectile) block.getWorld().spawn(block.getLocation().add(direction.getX(), direction.getY(), direction.getZ()), LargeFireball.class);
					projectile.setShooter((ProjectileSource) block);
				    projectile.setVelocity(direction);
				}
			}
		}

	@EventHandler
	public void throwWitherHead(PlayerInteractEvent e) {
		if (e.getItem() != null) {
			Player p = e.getPlayer();
			if ((!e.getItem().getType().isTransparent()) && (e.getItem().hasItemMeta()) && (e.getItem().getItemMeta().hasDisplayName()) && (e.getItem().getItemMeta().hasLore()) && (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Cabeça de Wither"))) {
				if (BoardColl.get().getFactionAt(PS.valueOf(p.getLocation())).getId().equalsIgnoreCase("safezone")) {
					e.setCancelled(true);
					return;
				}
				if (BoardColl.get().getFactionAt(PS.valueOf(p.getLocation())).getId().equalsIgnoreCase("warzone")) {
					e.setCancelled(true);
					return;
				}
				if (Proteção.getArea(p).equals("spawn")) {
					e.setCancelled(true);
					return;
				}
				if (Proteção.getArea(p).equals("__global__")) {
					e.setCancelled(true);
					return;
				}
				if (p.getWorld().getName().equals("vip")) {
					e.setCancelled(true);
					return;
				}
				if (p.getWorld().getName().equals("minas")) {
					e.setCancelled(true);
					return;
				}
				if (p.getWorld().getName().equals("world_arenas")) {
					e.setCancelled(true);
					return;
				}
				e.setCancelled(true);
				WitherSkull wskull = (WitherSkull) p.launchProjectile(WitherSkull.class);
				wskull.setMetadata("specialskull", new FixedMetadataValue(Main.getInstance(), Integer.valueOf(3)));
				if (p.getInventory().getItemInHand().getAmount() > 1) {
					p.getInventory().getItemInHand().setAmount(p.getInventory().getItemInHand().getAmount() - 1);
				} else {
					p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
				}
			}
		}
	}
}
