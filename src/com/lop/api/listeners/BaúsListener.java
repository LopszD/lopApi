package com.wandy.api.listeners;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.entity.TemporaryBoard;
import com.massivecraft.factions.event.EventFactionsChunksChange;
import com.massivecraft.factions.event.EventFactionsDisband;
import com.massivecraft.massivecore.ps.PS;
import com.wandy.api.caixas.spawners.Conteúdo;
import com.wandy.api.commands.FlyCommand;
import com.wandy.api.mobspawner.Mobs;
import com.wandy.api.utils.FactionUtil;

public class BaúsListener implements Listener {

	@EventHandler
	public static void aoDestruir(BlockBreakEvent e) {
		if (!e.isCancelled()) {
			if (e.getBlock() != null) {
				e.getBlock().getType().equals(Material.AIR);
			}
		}
	}

	@EventHandler
	public static void aoColocar(BlockPlaceEvent e) {
		if (e.getBlock().getType() == Material.CHEST) {
			if (!e.isCancelled()) {
				if (e.getPlayer().getWorld().getName().equalsIgnoreCase("minas")) {
					if (!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
						e.setCancelled(true);
						e.getPlayer().sendMessage("§cVocê não pode colocar baús no mundo de mineração.");
						return;
					}
				}
				MPlayer m = MPlayer.get(e.getPlayer().getName());
				PS ps = PS.valueOf(e.getBlock().getLocation());
				if (!m.isOverriding()) {
					if (BoardColl.get().getFactionAt(ps).isNone()) {
						e.setCancelled(true);
						e.getPlayer().sendMessage("§cVocê não pode colocar baús na área livre.");
						return;
					}
					if (TemporaryBoard.get().isTemporary(ps)) {
						e.setCancelled(true);
						e.getPlayer().sendMessage("§cVocê não pode colocar baús no terreno temporário.");
						return;
					}
				}
			}
		}
		if (e.getBlock().getType() == Material.TRAPPED_CHEST) {
			if (!e.isCancelled()) {
				if (e.getPlayer().getWorld().getName().equalsIgnoreCase("minas")) {
					if (!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
						e.setCancelled(true);
						e.getPlayer().sendMessage("§cVocê não pode colocar baús no mundo de mineração.");
						return;
					}
				}
				MPlayer m = MPlayer.get(e.getPlayer().getName());
				PS ps = PS.valueOf(e.getBlock().getLocation());
				if (!m.isOverriding()) {
					if (BoardColl.get().getFactionAt(ps).isNone()) {
						e.setCancelled(true);
						e.getPlayer().sendMessage("§cVocê não pode colocar baús na área livre.");
						return;
					}
					if (TemporaryBoard.get().isTemporary(ps)) {
						e.setCancelled(true);
						e.getPlayer().sendMessage("§cVocê não pode colocar baús no terreno temporário.");
					}
				}
			}
		}
	}

	@EventHandler
	public static void aoPistonar1(BlockPistonExtendEvent e) {
		for (Block b : e.getBlocks()) {
			if (b.getType() == Material.CHEST) {
				e.setCancelled(true);
			}
			if (b.getType() == Material.TRAPPED_CHEST) {
				e.setCancelled(true);
			}
			if (b.getType() == Material.MOB_SPAWNER) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public static void aoPistonar2(BlockPistonRetractEvent e) {
		for (Block b : e.getBlocks()) {
			if (b.getType() == Material.CHEST) {
				e.setCancelled(true);
			}
			if (b.getType() == Material.TRAPPED_CHEST) {
				e.setCancelled(true);
			}
			if (b.getType() == Material.MOB_SPAWNER) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public static void aoDesbandar(EventFactionsDisband e) {
		Player p = (Player) e.getSender();
		MPlayer mp = MPlayer.get(p);
		Faction f = mp.getFaction();
		int ch = 0;
		int m = 0;
		for (PS hs : BoardColl.get().getChunks(f)) {
			BlockState[] tileEntities;
			for (int length = (tileEntities = hs.asBukkitChunk().getTileEntities()).length, i = 0; i < length; ++i) {
				BlockState bs = tileEntities[i];
				if (bs.getBlock().getType() == Material.CHEST) {
					++ch;
				}
				if (bs.getBlock().getType() == Material.TRAPPED_CHEST) {
					++ch;
				}
				if (bs.getBlock().getType() == Material.MOB_SPAWNER) {
					++m;
				}
			}
		}
		if (m > 0) {
			e.setCancelled(true);
			p.sendMessage("§cVocê não pode abandonar sua facção com mobspawners colocados nas suas terras.");
			return;
		}
		if (ch > 0) {
			e.setCancelled(true);
			p.sendMessage("§cVocê não pode abandonar sua facção com baús colocados nas suas terras.");
			return;
		}
		for (Player ps : e.getFaction().getOnlinePlayers()) {
			if (ps.getGameMode() == GameMode.SURVIVAL && !FlyCommand.voando.contains(ps.getName())) {
				ps.setAllowFlight(false);
			}
		}
	}

	@EventHandler
	public static void aoUnclaimar(EventFactionsChunksChange e) {
		MPlayer mp = e.getMPlayer();
		Player p = (Player) e.getSender();
		if (e.getNewFaction().getName().equals("§2Área livre")) {
			FactionUtil util = new FactionUtil();
			Faction f2 = util.getFaction(p.getLocation());
			int is = 0;
			int ch = 0;
			ArrayList<Block> mobs = new ArrayList<Block>();
			ArrayList<Chest> chest = new ArrayList<Chest>();
			for (PS hs : e.getChunks()) {
				BlockState[] tileEntities;
				for (int length = (tileEntities = hs.asBukkitChunk().getTileEntities()).length, i = 0; i < length; ++i) {
					BlockState bs = tileEntities[i];
					if (bs.getBlock().getType() == Material.MOB_SPAWNER) {
						++is;
						mobs.add(bs.getBlock());
					}
					if (bs.getBlock().getType() == Material.CHEST) {
						++ch;
						Chest c = (Chest) bs.getBlock().getState();
						chest.add(c);
					}
					if (bs.getBlock().getType() == Material.TRAPPED_CHEST) {
						++ch;
						Chest c = (Chest) bs.getBlock().getState();
						chest.add(c);
					}
				}
				if (is > 0) {
					for (Block bl : mobs) {
						CreatureSpawner spawner = (CreatureSpawner) bl.getState();
						String a = spawner.getCreatureTypeName().replace("VillagerGolem", "Iron_golem").replace("WitherBoss", "Wither").replace("CaveSpider", "Cave_spider").replace("PigZombie", "Pig_zombie").replace("LavaSlime", "Magma");
						ItemStack m = Conteúdo.spawnerCreate(a);
						bl.getLocation().getWorld().dropItem(bl.getLocation(), m);
						if (Mobs.existeMob(bl)) {
							Mobs mob = Mobs.getMobs(bl);
							mob.deletarMob();
						}
						bl.setType(Material.AIR);
					}
					if (mp.hasFaction()) {
						mp.getFaction().msg("§a" + is + " mobspawners foram encontrados e dropados no terreno que sua facção desprotegeu.");
					}
					f2.msg("§a" + is + " mobspawners foram encontrados e dropados no terreno que sua facção perdeu.");
				}
				if (ch > 0) {
					for (Chest c2 : chest) {
						c2.getBlock().setType(Material.AIR);
					}
					if (mp.hasFaction()) {
						mp.getFaction().msg("§a" + is + " baús foram encontrados e dropados no terreno que sua facção desprotegeu.");
					}
					f2.msg("§a" + is + " baús foram encontrados e dropados no terreno que sua facção perdeu.");
				}
			}
			PS ps = PS.valueOf(p.getLocation());
			for (Player pss : BoardColl.get().getFactionAt(ps).getOnlinePlayers()) {
				if (pss.getGameMode() == GameMode.SURVIVAL && !FlyCommand.voando.contains(pss.getName())) {
					pss.setAllowFlight(false);
				}
			}
			return;
		}
		if (e.getNewFaction().getName().equals("§2Área livre") && mp.hasFaction()) {
			Faction f3 = mp.getFaction();
			FactionUtil util2 = new FactionUtil();
			Faction f4 = util2.getFaction(p.getLocation());
			if (f3.getName().equals(f4.getName())) {
				int is2 = 0;
				int ch2 = 0;
				for (PS hs2 : e.getChunks()) {
					BlockState[] tileEntities2;
					for (int length2 = (tileEntities2 = hs2.asBukkitChunk().getTileEntities()).length, j = 0; j < length2; ++j) {
						BlockState bs2 = tileEntities2[j];
						if (bs2.getBlock().getType() == Material.MOB_SPAWNER) {
							++is2;
						}
						if (bs2.getBlock().getType() == Material.CHEST) {
							++ch2;
						}
						if (bs2.getBlock().getType() == Material.TRAPPED_CHEST) {
							++ch2;
						}
					}
				}
				if (is2 > 0) {
					e.setCancelled(true);
					p.sendMessage("§cVocê não pode abandonar uma terra com mobspawners colocados.");
					return;
				}
				if (ch2 > 0) {
					e.setCancelled(true);
					p.sendMessage("§cVocê não pode abandonar uma terra com baús colocados.");
					return;
				}
			}
		}
		PS ps = PS.valueOf(p.getLocation());
		for (Player pss : BoardColl.get().getFactionAt(ps).getOnlinePlayers()) {
			if (pss.getGameMode() == GameMode.SURVIVAL && !FlyCommand.voando.contains(pss.getName())) {
				pss.setAllowFlight(false);
			}
		}
	}

	@EventHandler
	public static void aoQuebrar(BlockBreakEvent e) {
		if (e.getBlock().getType() == Material.MOB_SPAWNER) {
			Player p = e.getPlayer();
			MPlayer mp = MPlayer.get(p);
			if (mp.hasFaction()) {
				Faction f = mp.getFaction();
				FactionUtil fu = new FactionUtil();
				Faction f2 = fu.getFaction(e.getBlock().getLocation());
				if (!f2.getName().equals(f.getName())) {
					if (f2.isInAttack() && f.getRelationWish(f2) == Rel.ALLY && p.getItemInHand() != null && p.getGameMode() == GameMode.SURVIVAL && !p.getItemInHand().getType().equals(Material.AIR) && p.getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
						e.setCancelled(true);
						p.sendMessage("§cEstá facção está em ataque! Você não pode quebrar este bloco com toque suave!");
					}
					return;
				} else if (f.isInAttack() && p.getItemInHand() != null && p.getGameMode() == GameMode.SURVIVAL && !p.getItemInHand().getType().equals(Material.AIR) && p.getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH) && f2.getName() == f.getName()) {
					e.setCancelled(true);
					p.sendMessage("§cSua facção está em ataque! Você não pode quebrar este bloco com toque suave!");
				}
			}
		}
		if (e.getBlock().getType() == Material.CHEST) {
			Player p = e.getPlayer();
			MPlayer mp = MPlayer.get(p);
			if (mp.hasFaction()) {
				Faction f = mp.getFaction();
				FactionUtil fu = new FactionUtil();
				Faction f2 = fu.getFaction(e.getBlock().getLocation());
				if (!f2.getName().equals(f.getName())) {
					if (f2.isInAttack() && f.getRelationWish(f2) == Rel.ALLY && p.getGameMode() == GameMode.SURVIVAL) {
						e.setCancelled(true);
						p.sendMessage("§cEstá facção está em ataque! Você não pode quebrar este bloco!");
					}
				} else if (f.isInAttack() && p.getGameMode() == GameMode.SURVIVAL) {
					e.setCancelled(true);
					p.sendMessage("§cEstá facção está em ataque! Você não pode quebrar este bloco!");
				}
			}
		}
	}

	@EventHandler
	public static void aoClicar(InventoryClickEvent e) {
		if (e.getWhoClicked().getOpenInventory().getTopInventory() == null) {
			return;
		}
		if (e.getWhoClicked().getOpenInventory().getTopInventory().getType().equals(InventoryType.ANVIL)) {
			if (e.getCurrentItem() != null) {
				if (e.getClick().equals(ClickType.NUMBER_KEY)) {
					e.setCancelled(true);
					return;
				}
				if (!e.getCurrentItem().getType().equals(Material.AIR)) {
					if (e.getCurrentItem().hasItemMeta()) {
						if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
							if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§")) {
								e.setCancelled(true);
							}
						}
					}
				}
			}
		}
		if (e.getWhoClicked().getOpenInventory().getTopInventory().getType().equals(InventoryType.DROPPER)) {
			if (e.getCurrentItem() != null) {
				if (e.getClick().equals(ClickType.NUMBER_KEY)) {
					e.setCancelled(true);
					return;
				}
				if (!e.getCurrentItem().getType().equals(Material.AIR)) {
					if (!e.getCurrentItem().getType().equals(Material.TNT)) {
						e.setCancelled(true);
					}
				}
			}
		}
		if (e.getWhoClicked().getOpenInventory().getTopInventory().getType().equals(InventoryType.DISPENSER)) {
			if (e.getCurrentItem() != null) {
				if (e.getClick().equals(ClickType.NUMBER_KEY)) {
					e.setCancelled(true);
					return;
				}
				if (!e.getCurrentItem().getType().equals(Material.AIR)) {
					if (!e.getCurrentItem().getType().equals(Material.TNT)) {
						e.setCancelled(true);
					}
				}
			}
		}
		if (e.getWhoClicked().getOpenInventory().getTopInventory().getType().equals(InventoryType.FURNACE)) {
			if (e.getCurrentItem() != null) {
				if (e.getClick().equals(ClickType.NUMBER_KEY)) {
					e.setCancelled(true);
					return;
				}
				if (!e.getCurrentItem().getType().equals(Material.AIR)) {
					int i = 0;
					if (e.getCurrentItem().getType().toString().contains("ORE")) {
						i++;
					}
					if (e.getCurrentItem().getType().toString().contains("WOOD")) {
						i++;
					}
					if (e.getCurrentItem().getType().toString().contains("LOG")) {
						i++;
					}
					if (e.getCurrentItem().getType() == Material.COAL) {
						i++;
					}
					if (e.getCurrentItem().getType() == Material.DIAMOND) {
						i++;
					}
					if (e.getCurrentItem().getType() == Material.GOLD_INGOT) {
						i++;
					}
					if (e.getCurrentItem().getType() == Material.IRON_INGOT) {
						i++;
					}
					if (e.getCurrentItem().getType() == Material.EMERALD) {
						i++;
					}
					if (e.getCurrentItem().getType() == Material.COAL_BLOCK) {
						i++;
					}
					if (e.getCurrentItem().getType() == Material.BLAZE_ROD) {
						i++;
					}
					if (e.getCurrentItem().getType() == Material.BLAZE_POWDER) {
						i++;
					}
					if (e.getCurrentItem().getType().toString().contains("SAND")) {
						i++;
					}
					if (i == 0) {
						e.setCancelled(true);
						return;
					}
				}
			}
		}
	}
}
