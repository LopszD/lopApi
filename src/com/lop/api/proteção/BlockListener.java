package com.wandy.api.proteção;

import java.util.Iterator;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;

import com.wandy.api.caixas.spawners.Conteúdo;
import com.wandy.api.especiais.listeners.ImpulsaoListener;
import com.wandy.api.mobspawner.Mobs;

public class BlockListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityExplode(EntityExplodeEvent e) {
		if (e.getEntity() instanceof WitherSkull) {
			return;
		}
		if (e.getEntity().getType() == EntityType.WITHER_SKULL) {
			return;
		}
		if (e.getEntity().getType() == EntityType.WITHER) {
			return;
		}
		if (!e.isCancelled()) {
			if (e.getEntity() instanceof TNTPrimed) {
				if (e.getEntity().hasMetadata(ImpulsaoListener.meta)) {
					e.blockList().clear();
					return;
				}
				if (e.getEntity().getCustomName() != null) {
					if (e.getEntity().getCustomName().equals("ALAHU AKBAR")) {
						e.blockList().clear();
						return;
					}
				}
			}
			Iterator<Block> it = e.blockList().iterator();
			while (it.hasNext()) {
				Block bloco = it.next();
				if (BlockStatus.isValidBlock(bloco)) {
					it.remove();
				}
			}
		}
		if (!e.isCancelled() && e.getEntity().getLocation().getBlock() != null && (e.getEntity().getLocation().getBlock().getType() == Material.STATIONARY_WATER) || e.getEntity().getLocation().getBlock().getType() == Material.STATIONARY_LAVA) {
			int radius = (int) Math.ceil(2.0);
			Location detLoc = e.getLocation();
			for (int x = -radius; x <= radius; ++x) {
				for (int y = -radius; y <= radius; ++y) {
					for (int z = -radius; z <= radius; ++z) {
						Location targetLoc = new Location(detLoc.getWorld(), detLoc.getX() + x, detLoc.getY() + y, detLoc.getZ() + z);
						if (detLoc.distance(targetLoc) <= 2.0) {
							Block b = e.getLocation().getWorld().getBlockAt(targetLoc);
							if (!BlockStatus.isValidBlock(b) && !(b.getType() == Material.MOB_SPAWNER) && !(b.getType() == Material.STATIONARY_WATER) && !(b.getType() == Material.STATIONARY_LAVA)) {
								b.setType(Material.AIR);
							}
						}
					}
				}
			}
		}
		if (e.getEntity() instanceof LargeFireball) {
			int radius = (int) Math.ceil(2.0);
			Location detLoc = e.getLocation();
			for (int x = -radius; x <= radius; ++x) {
				for (int y = -radius; y <= radius; ++y) {
					for (int z = -radius; z <= radius; ++z) {
						Location targetLoc = new Location(detLoc.getWorld(), detLoc.getX() + x, detLoc.getY() + y, detLoc.getZ() + z);
						if (detLoc.distance(targetLoc) <= 2.0) {
							Block b = e.getLocation().getWorld().getBlockAt(targetLoc);
							if (b.getType() == Material.STONE) {
								b.setType(Material.AIR);
							}
							int i = 0;
							if (b.getY() >= 127 && e.getLocation().getWorld().getName().equals("world_nether")) {
								++i;
							}
							if (b.getY() == 0) {
								++i;
							}
							if (i == 0) {
								BlockStatus.setBlockDurabilitySC(b);
							}
							if (b.getType() == Material.MOB_SPAWNER) {
								Block block = b;
								CreatureSpawner spawner = (CreatureSpawner) block.getState();
								if (spawner.getSpawnedType() == EntityType.ZOMBIE) {
									e.setCancelled(true);
									if (Mobs.existeMob(block)) {
										Mobs mob = Mobs.getMobs(block);
										mob.deletarMob();
									}
									block.setType(Material.AIR);
									return;
								}
								if (spawner.getSpawnedType() == EntityType.CAVE_SPIDER) {
									e.setCancelled(true);
									if (Mobs.existeMob(block)) {
										Mobs mob = Mobs.getMobs(block);
										mob.deletarMob();
									}
									block.setType(Material.AIR);
									return;
								}
								Random r = new Random();
								int nes = r.nextInt(2);
								if (Mobs.existeMob(b)) {
									Mobs m = Mobs.getMobs(b);
									m.deletarMob();
								}
								if (nes == 1) {
									b.getDrops().clear();
									String a = spawner.getCreatureTypeName().replace("VillagerGolem", "Iron_golem").replace("CaveSpider", "Cave_spider").replace("PigZombie", "Pig_zombie").replace("WitherBoss", "Wither").replace("LavaSlime", "Magma");
									ItemStack j = Conteúdo.spawnerCreate(a);
									b.setType(Material.AIR);
									b.getWorld().dropItemNaturally(b.getLocation(), j);
								} else {
									b.getDrops().clear();
									b.setType(Material.AIR);
								}
							}
						}
					}
				}
			}
			return;
		}
		if (e.getEntity().getType() == EntityType.CREEPER && e.getEntity().hasMetadata("SUPER")) {
			int radius = (int) Math.ceil(2.0);
			Location detLoc = e.getLocation();
			for (int x = -radius; x <= radius; ++x) {
				for (int y = -radius; y <= radius; ++y) {
					for (int z = -radius; z <= radius; ++z) {
						Location targetLoc = new Location(detLoc.getWorld(), detLoc.getX() + x, detLoc.getY() + y, detLoc.getZ() + z);
						if (detLoc.distance(targetLoc) <= 2.0) {
							Block b = e.getLocation().getWorld().getBlockAt(targetLoc);
							int i = 0;
							if (e.getLocation().getWorld().getName().equals("world_nether")) {
								++i;
							}
							if (b.getY() == 0) {
								++i;
							}
							if (i == 0) {
								BlockStatus.setBlockDurabilitySC(b);
							}
							if (b.getType() == Material.MOB_SPAWNER) {
								Block block = b;
								CreatureSpawner spawner = (CreatureSpawner) block.getState();
								if (spawner.getSpawnedType() == EntityType.ZOMBIE) {
									e.setCancelled(true);
									if (Mobs.existeMob(block)) {
										Mobs mob = Mobs.getMobs(block);
										mob.deletarMob();
									}
									block.setType(Material.AIR);
									return;
								}
								if (spawner.getSpawnedType() == EntityType.CAVE_SPIDER) {
									e.setCancelled(true);
									if (Mobs.existeMob(block)) {
										Mobs mob = Mobs.getMobs(block);
										mob.deletarMob();
									}
									block.setType(Material.AIR);
									return;
								}
								Random r = new Random();
								int nes = r.nextInt(2);
								if (Mobs.existeMob(b)) {
									Mobs m = Mobs.getMobs(b);
									m.deletarMob();
								}
								if (nes == 1) {
									b.getDrops().clear();
									String a = spawner.getCreatureTypeName().replace("VillagerGolem", "Iron_golem").replace("CaveSpider", "Cave_spider").replace("PigZombie", "Pig_zombie").replace("WitherBoss", "Wither").replace("LavaSlime", "Magma");
									ItemStack j = Conteúdo.spawnerCreate(a);
									b.setType(Material.AIR);
									b.getWorld().dropItemNaturally(b.getLocation(), j);
								} else {
									b.getDrops().clear();
									b.setType(Material.AIR);
								}
							}
						}
					}
				}
			}
			return;
		}
		int radius = (int) Math.ceil(2.0);
		Location detLoc = e.getLocation();
		for (int x = -radius; x <= radius; ++x) {
			for (int y = -radius; y <= radius; ++y) {
				for (int z = -radius; z <= radius; ++z) {
					Location targetLoc = new Location(detLoc.getWorld(), detLoc.getX() + x, detLoc.getY() + y, detLoc.getZ() + z);
					if (detLoc.distance(targetLoc) <= 2.0) {
						Block b = e.getLocation().getWorld().getBlockAt(targetLoc);
						int i = 0;
						if (e.getLocation().getWorld().getName().equals("world_nether")) {
							++i;
						}
						if (b.getY() == 0) {
							++i;
						}
						if (i == 0) {
							BlockStatus.setBlockDurability(b);
						}
						if (e.getEntity() instanceof Creeper && b.getType() == Material.MOB_SPAWNER) {
							Block block = b;
							CreatureSpawner spawner = (CreatureSpawner) block.getState();
							if (spawner.getSpawnedType() == EntityType.ZOMBIE) {
								e.setCancelled(true);
								if (Mobs.existeMob(block)) {
									Mobs mob = Mobs.getMobs(block);
									mob.deletarMob();
								}
								block.setType(Material.AIR);
								return;
							}
							if (spawner.getSpawnedType() == EntityType.CAVE_SPIDER) {
								e.setCancelled(true);
								if (Mobs.existeMob(block)) {
									Mobs mob = Mobs.getMobs(block);
									mob.deletarMob();
								}
								block.setType(Material.AIR);
								return;
							}
							Random r = new Random();
							int nes = r.nextInt(2);
							if (Mobs.existeMob(b)) {
								Mobs m = Mobs.getMobs(b);
								m.deletarMob();
							}
							if (nes == 1) {
								b.getDrops().clear();
								String a = spawner.getCreatureTypeName().replace("VillagerGolem", "Iron_golem").replace("CaveSpider", "Cave_spider").replace("PigZombie", "Pig_zombie").replace("WitherBoss", "Wither").replace("LavaSlime", "Magma");
								ItemStack j = Conteúdo.spawnerCreate(a);
								b.setType(Material.AIR);
								b.getWorld().dropItemNaturally(b.getLocation(), j);
							} else {
								b.getDrops().clear();
								b.setType(Material.AIR);
							}
						}
						if (e.getEntity() instanceof TNTPrimed && b.getType() == Material.MOB_SPAWNER) {
							Block block = b;
							CreatureSpawner spawner = (CreatureSpawner) block.getState();
							if (spawner.getSpawnedType() == EntityType.ZOMBIE) {
								e.setCancelled(true);
								if (Mobs.existeMob(block)) {
									Mobs mob = Mobs.getMobs(block);
									mob.deletarMob();
								}
								block.setType(Material.AIR);
								return;
							}
							if (spawner.getSpawnedType() == EntityType.CAVE_SPIDER) {
								e.setCancelled(true);
								if (Mobs.existeMob(block)) {
									Mobs mob = Mobs.getMobs(block);
									mob.deletarMob();
								}
								block.setType(Material.AIR);
								return;
							}
							Random r = new Random();
							int nes = r.nextInt(3);
							if (Mobs.existeMob(b)) {
								Mobs m = Mobs.getMobs(b);
								m.deletarMob();
							}
							if (nes == 1) {
								b.getDrops().clear();
								String a = spawner.getCreatureTypeName().replace("VillagerGolem", "Iron_golem").replace("CaveSpider", "Cave_spider").replace("PigZombie", "Pig_zombie").replace("WitherBoss", "Wither").replace("LavaSlime", "Magma");
								ItemStack j = Conteúdo.spawnerCreate(a);
								b.setType(Material.AIR);
								b.getWorld().dropItemNaturally(b.getLocation(), j);
							} else {
								b.getDrops().clear();
								b.setType(Material.AIR);
							}
						}
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void blockBreak(BlockBreakEvent e) {
		if ((!e.isCancelled()) && (BlockStatus.isHashedBlock(e.getBlock()))) {
			BlockStatus.getBlockStatus.remove(e.getBlock().getLocation());
		}
	}
}
