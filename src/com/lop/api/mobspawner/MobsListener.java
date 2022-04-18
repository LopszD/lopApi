package com.wandy.api.mobspawner;

import java.util.HashMap;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import com.wandy.api.caixas.spawners.Conteúdo;

public class MobsListener implements Listener {
	
	public static HashMap<Block, Integer> dal = new HashMap<Block, Integer>();

	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockEat(EntityChangeBlockEvent eat) {
		if (eat.getEntity().getType() == EntityType.WITHER) {
			eat.setCancelled(true);
			return;
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void BlockPlaceEvent(BlockPlaceEvent e) {
		if (e.getBlock().getType() == Material.MOB_SPAWNER) {
			if (e.getPlayer().getWorld().getName().equalsIgnoreCase("minas") && !(e.getPlayer().getGameMode() == GameMode.CREATIVE)) {
				e.setCancelled(true);
				e.getPlayer().sendMessage("§cVocê não pode colocar mobspawners no mundo de mineração.");
				return;
			}
			/*
			 * Player p = e.getPlayer(); MPlayer mp = MPlayer.get(p.getName()); if
			 * (!mp.isOverriding() &&
			 * !BoardColl.get().getFactionAt(PS.valueOf(e.getBlock())).isNone()) { if
			 * (!mp.hasFaction()) { e.setCancelled(true); return; } Faction f2 =
			 * mp.getFaction(); if
			 * (!BoardColl.get().getFactionAt(PS.valueOf(e.getBlock())).equals(f2)) {
			 * e.setCancelled(true); return; } }
			 */
			ItemMeta meta = e.getPlayer().getItemInHand().getItemMeta();
			if (meta.hasLore()) {
				String linha = meta.getLore().get(0);
				String mob = linha.replace("§7Tipo: §f", "");
				EntityType type;
				if (mob.equals("Magma")) {
					type = EntityType.MAGMA_CUBE;
				} else {
					type = EntityType.valueOf(mob.toUpperCase());
				}
				/*
				 * if (!mp.isOverriding() &&
				 * BoardColl.get().getFactionAt(PS.valueOf(e.getBlock())).isNone()) {
				 * e.setCancelled(true);
				 * p.sendMessage("§cVocê não pode colocar um mobspawner na área livre.");
				 * return; } if
				 * (!BoardColl.get().getFactionAt(PS.valueOf(e.getBlock())).isNone() &&
				 * temProtecaoLado(e.getBlock(), p)) { e.setCancelled(true); e.setBuild(false);
				 * return; }
				 */
				setarTipo(e.getBlock(), type);
				/*
				 * Random r = new Random(); String ranb = new
				 * StringBuilder().append(r.nextInt(9)).append(r.nextInt(9)).append(r.nextInt(9)
				 * ).append(r.nextInt(9)).append(r.nextInt(9)).append(r.nextInt(9)).toString();
				 * int ran = Integer.valueOf(ranb);
				 * Bukkit.getScheduler().scheduleAsyncDelayedTask((Plugin)Main.plugin,
				 * (Runnable)new Runnable() {
				 * 
				 * @Override public void run() { if (MobsListener.dal.containsValue(ran) &&
				 * e.getBlock() != null &&
				 * e.getBlock().getType().equals((Object)Material.MOB_SPAWNER)) {
				 * Mobs.criarMob(e.getBlock()); } } }, 1200L);
				 * MobsListener.dal.put(e.getBlock(), ran); p.
				 * sendMessage("§eEm 1 minuto, este gerador ficará bloqueado para ser retirado."
				 * );
				 */
			}
		}
	}

	public static boolean temProtecaoLado(Block b, Player p) {
		if (b.getType() == Material.BEDROCK) {
			int i = 0;
			if (b.getRelative(BlockFace.WEST).getType() == Material.MOB_SPAWNER) {
				i++;
			}
			if (b.getRelative(BlockFace.EAST).getType() == Material.MOB_SPAWNER) {
				i++;
			}
			if (b.getRelative(BlockFace.NORTH).getType() == Material.MOB_SPAWNER) {
				i++;
			}
			if (b.getRelative(BlockFace.SOUTH).getType() == Material.MOB_SPAWNER) {
				i++;
			}
			if (b.getRelative(BlockFace.DOWN).getType() == Material.MOB_SPAWNER) {
				i++;
			}
			if (b.getRelative(BlockFace.UP).getType() == Material.MOB_SPAWNER) {
				i++;
			}
			if (b.getRelative(BlockFace.SELF).getType() == Material.MOB_SPAWNER) {
				i++;
			}
			if (i > 0) {
				p.sendMessage("§cVocê não pode colocar um bloco de proteção do lado de um mobspawner.");
				return true;
			}
		}
		if (b.getType() == Material.OBSIDIAN) {
			int i = 0;
			if (b.getRelative(BlockFace.WEST).getType() == Material.MOB_SPAWNER) {
				i++;
			}
			if (b.getRelative(BlockFace.EAST).getType() == Material.MOB_SPAWNER) {
				i++;
			}
			if (b.getRelative(BlockFace.NORTH).getType() == Material.MOB_SPAWNER) {
				i++;
			}
			if (b.getRelative(BlockFace.SOUTH).getType() == Material.MOB_SPAWNER) {
				i++;
			}
			if (b.getRelative(BlockFace.DOWN).getType() == Material.MOB_SPAWNER) {
				i++;
			}
			if (b.getRelative(BlockFace.UP).getType() == Material.MOB_SPAWNER) {
				i++;
			}
			if (b.getRelative(BlockFace.SELF).getType() == Material.MOB_SPAWNER) {
				i++;
			}
			if (i > 0) {
				p.sendMessage("§cVocê não pode colocar um bloco de proteção do lado de um mobspawner.");
				return true;
			}
		}
		if (b.getType() == Material.MOB_SPAWNER) {
			int i = 0;
			if (b.getRelative(BlockFace.WEST).getType() == Material.OBSIDIAN) {
				i++;
			}
			if (b.getRelative(BlockFace.EAST).getType() == Material.OBSIDIAN) {
				i++;
			}
			if (b.getRelative(BlockFace.NORTH).getType() == Material.OBSIDIAN) {
				i++;
			}
			if (b.getRelative(BlockFace.SOUTH).getType() == Material.OBSIDIAN) {
				i++;
			}
			if (b.getRelative(BlockFace.DOWN).getType() == Material.OBSIDIAN) {
				i++;
			}
			if (b.getRelative(BlockFace.UP).getType() == Material.OBSIDIAN) {
				i++;
			}
			if (b.getRelative(BlockFace.SELF).getType() == Material.OBSIDIAN) {
				i++;
			}
			if (b.getRelative(BlockFace.WEST).getType() == Material.BEDROCK) {
				i++;
			}
			if (b.getRelative(BlockFace.EAST).getType() == Material.BEDROCK) {
				i++;
			}
			if (b.getRelative(BlockFace.NORTH).getType() == Material.BEDROCK) {
				i++;
			}
			if (b.getRelative(BlockFace.SOUTH).getType() == Material.BEDROCK) {
				i++;
			}
			if (b.getRelative(BlockFace.DOWN).getType() == Material.BEDROCK) {
				i++;
			}
			if (b.getRelative(BlockFace.UP).getType() == Material.BEDROCK) {
				i++;
			}
			if (b.getRelative(BlockFace.SELF).getType() == Material.BEDROCK) {
				i++;
			}
			if (i > 0) {
				p.getPlayer().sendMessage("§cVocê não pode colocar um mobspawner do lado de um bloco de proteção.");
				return true;
			}
		}
		return false;
	}

	public static void setarTipo(Block b, EntityType type) {
		BlockState blockstate = b.getState();
		CreatureSpawner spawner = (CreatureSpawner) blockstate;
		spawner.setSpawnedType(type);
		blockstate.update();
	}

	public static boolean checarInv(Player p, Integer i) {
		int antes = 36 - i;
		int v = 0;
		ItemStack[] contents;
		for (int length = (contents = p.getInventory().getContents()).length, j = 0; j < length; ++j) {
			ItemStack itens = contents[j];
			if (itens != null && !(itens.getType() == Material.AIR)) {
				++v;
			}
		}
		return v <= antes;
	}

	@EventHandler
	public void aoQuebrar(BlockBreakEvent e) {
		if (e.getBlock().getType() == Material.MOB_SPAWNER) {
			if (!e.isCancelled()) {
				if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
					e.setCancelled(false);
					if (dal.containsKey(e.getBlock())) {
						dal.remove(e.getBlock());
					}
					if (Mobs.existeMob(e.getBlock())) {
						Mobs m = Mobs.getMobs(e.getBlock());
						m.deletarMob();
					}
					return;
				}
				if (e.getPlayer().getWorld().getName().equalsIgnoreCase("minas")) {
					e.setCancelled(true);
					e.getPlayer().sendMessage("§cVocê não pode quebrar mobspawners no mundo de mineração.");
					return;
				}
				MPlayer mp = MPlayer.get(e.getPlayer().getName());
				if (!mp.isOverriding()) {
					if (BoardColl.get().getFactionAt(PS.valueOf(e.getBlock())).getId().equalsIgnoreCase("safezone")) {
						e.setCancelled(true);
						return;
					}
					if (BoardColl.get().getFactionAt(PS.valueOf(e.getBlock())).getId().equalsIgnoreCase("warzone")) {
						e.setCancelled(true);
						return;
					}
					/*
					 * if (!BoardColl.get().getFactionAt(PS.valueOf(e.getBlock())).isNone()) { if
					 * (!mp.hasFaction()) { e.setCancelled(true);
					 * 
					 * return; } Faction f2 = mp.getFaction(); if
					 * (!BoardColl.get().getFactionAt(PS.valueOf(e.getBlock())).equals(f2)) {
					 * e.setCancelled(true);
					 * 
					 * return; } }
					 */
				}
				Block block = e.getBlock();
				CreatureSpawner spawner = (CreatureSpawner) block.getState();
				if (dal.containsKey(e.getBlock())) {
					dal.remove(e.getBlock());
				}
				/*
				 * if (Mobs.existeMob(block)) { Mobs mob = Mobs.getMobs(block); if
				 * (!mob.podeTirar()) { e.setCancelled(true);
				 * e.getPlayer().sendMessage("§cVocê poderá tirar este mobspawner em " +
				 * mob.getDataF().replace(" ", " às ") + "."); return; } }
				 */
				if (!e.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
					if (dal.containsKey(e.getBlock())) {
						dal.remove(e.getBlock());
					}
					if (Mobs.existeMob(block)) {
						Mobs mob = Mobs.getMobs(block);
						mob.deletarMob();
					}
					return;
				}
				if (e.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
					if (!checarInv(e.getPlayer(), 1)) {
						e.setCancelled(true);
						e.getPlayer().sendMessage("§cSeu inventário está cheio! Esvazie ele para poder pegar este mobspawner.");
						return;
					}
					block.getDrops().clear();
					String a = spawner.getCreatureTypeName().replace("VillagerGolem", "Iron_golem").replace("WitherBoss", "Wither").replace("CaveSpider", "Cave_spider").replace("PigZombie", "Pig_zombie").replace("LavaSlime", "Magma");
					ItemStack m = Conteúdo.spawnerCreate(a);
					block.setType(Material.AIR);
					e.getPlayer().getInventory().addItem(m);
					if (dal.containsKey(e.getBlock())) {
						dal.remove(e.getBlock());
					}
					if (Mobs.existeMob(block)) {
						Mobs mob = Mobs.getMobs(block);
						mob.deletarMob();
					}
					return;
				}
			}
		}
	}
}
