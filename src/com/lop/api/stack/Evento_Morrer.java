package com.wandy.api.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import com.wandy.api.Main;
import com.wandy.api.commands.DropsCommand;
import com.wandy.api.especiais.listeners.BoosterExpListener;
import com.wandy.api.listeners.EntrarListener;
import com.wandy.api.utils.fanciful.FancyMessage;

import ru.tehkode.permissions.bukkit.PermissionsEx;
@SuppressWarnings("all")
public class Evento_Morrer implements Listener {
	@EventHandler
	public void aoMorrer(EntityDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			return;
		}
		int a = e.getDrops().size();
		int ch = 0;
		for (Block bl : getNearbyBlocks(e.getEntity().getLocation(), 16)) {
			if (bl.getType().equals(Material.MOB_SPAWNER)
					&& bl.getLocation().getY() < e.getEntity().getLocation().getY()
					&& getTipo(bl).equals(e.getEntity().getType())) {
				++ch;
			}
		}
		if (ch > 0) {
			if (e.getEntity().hasMetadata("qnt")) {
				int entqnt = e.getEntity().getMetadata("qnt").get(0).asInt();
				int cha = entqnt - 1;
				dropSuck((Entity) e.getEntity(), e.getDrops().size(), e.getEntity().getKiller(), true, 0);
				e.getDrops().clear();
				if (cha == 1) {
					e.getEntity().remove();
					Entity dps = e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), e.getEntityType());
					if (dps.getType().equals(EntityType.WITHER)) {
						Evento_Spawn.freezeNPC(dps);
					}
					if (dps.getType().equals(EntityType.SLIME)) {
						Slime sl = (Slime) dps;
						sl.setSize(1);
					}
					if (dps.getType().equals(EntityType.MAGMA_CUBE)) {
						MagmaCube sl2 = (MagmaCube) dps;
						sl2.setSize(1);
					}
					dps.setMetadata("qnt", new FixedMetadataValue(Main.plugin, cha));
					if (e.getEntity().getKiller() instanceof Player) {
						e.getEntity().getKiller().giveExp(e.getDroppedExp());
					}
					return;
				}
				if (cha > 1) {
					e.getEntity().remove();
					Entity dps = e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), e.getEntityType());
					if (dps.getType().equals(EntityType.WITHER)) {
						Evento_Spawn.freezeNPC(dps);
					}
					if (dps.getType().equals(EntityType.SLIME)) {
						Slime sl = (Slime) dps;
						sl.setSize(1);
					}
					if (dps.getType().equals(EntityType.MAGMA_CUBE)) {
						MagmaCube sl2 = (MagmaCube) dps;
						sl2.setSize(1);
					}
					dps.setMetadata("qnt", new FixedMetadataValue(Main.plugin, cha));
					dps.setCustomName("§e" + entqnt + "x " + Evento_Spawn.translateMob(dps.getType().getName()));
					if (e.getEntity().getKiller() instanceof Player) {
						e.getEntity().getKiller().giveExp(e.getDroppedExp());
					}
				}
			}
			return;
		}
		if (e.getEntity().getKiller() != null && e.getEntity().getKiller() instanceof Player) {
			dropSuck((Entity) e.getEntity(), e.getDrops().size(), e.getEntity().getKiller(), false, e.getDroppedExp());
			e.setDroppedExp(0);
		}
		e.getDrops().clear();
	}

	public static EntityType getTipo(Block b) {
		CreatureSpawner spawner = (CreatureSpawner) b.getState();
		return spawner.getSpawnedType();
	}

	@EventHandler
	public static void aoExplodir(EntityExplodeEvent e) {
		if (e.getEntity() instanceof WitherSkull) {
			e.setCancelled(true);
		}
		if (e.getEntity().getType().equals(EntityType.WITHER_SKULL)) {
			e.setCancelled(true);
		}
		if (e.getEntity().getType().equals(EntityType.WITHER)) {
			e.setCancelled(true);
		}
	}

	public static void dropSuck(Entity e, int dro, Player p, boolean one, int gxp) {
		int drop = 0;
		int i = 0;
		if (e.getType().equals(EntityType.COW)) {
			Random r = new Random();
			drop = r.nextInt(2) + 1;
			++i;
		}
		if (e.getType().equals(EntityType.SPIDER)) {
			Random r = new Random();
			drop = r.nextInt(2) + 2;
			++i;
		}
		if (e.getType().equals(EntityType.SLIME)) {
			Random r = new Random();
			drop = r.nextInt(2) + 2;
			++i;
		}
		if (e.getType().equals(EntityType.MAGMA_CUBE)) {
			Random r = new Random();
			drop = r.nextInt(1) + 2;
			++i;
		}
		if (e.getType().equals(EntityType.PIG_ZOMBIE)) {
			Random r = new Random();
			drop = r.nextInt(2) + 2;
			++i;
		}
		if (e.getType().equals(EntityType.IRON_GOLEM)) {
			Random r = new Random();
			drop = r.nextInt(2) + 3;
			++i;
		}
		int vanilla;
		int dps = vanilla = drop;
		try {
			if (BoosterExpListener.bos.containsKey(p.getName()) && BoosterExpListener.bos.get(p.getName()).getName().equals(p.getName())) {
				dps *= 2;
			}
		} catch (NullPointerException ex) {
		}
		int spawners = 0;
		if (!one) {
			int add = getAdd(e);
			int curr = drop;
			dps = (spawners = curr * add);
			int xp = 2 * add;
			int exp = gxp * xp;
			p.giveExp(exp);
		}
		int looting = 0;
		if (p.getItemInHand() != null && !p.getItemInHand().getType().equals(Material.AIR)
				&& p.getInventory().getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_MOBS)) {
			int lvl = p.getInventory().getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS);
			if (lvl != 1) {
				if (lvl >= 4) {
					dps += dps;
				}
				if (lvl == 2) {
					int loot = dps / 4;
					dps += loot;
				}
				if (lvl == 3) {
					int loot = dps / 2;
					dps += loot;
				}
				looting = dps;
			}
		}
		if (i > 0 && DropsCommand.drops.contains(p.getName())) {
			String msg = "§fPadrão: §7" + vanilla;
			if (spawners > 0) {
				msg = String.valueOf(msg) + " §fSpawners: §7" + spawners;
			}
			if (looting > 0) {
				msg = String.valueOf(msg) + " §fPilhagem: §7" + looting;
			}
			EntrarListener.mandarAction(p, msg);
		}
		if (e.getType() == EntityType.IRON_GOLEM && e.hasMetadata("qnt")) {
			ItemStack d = new ItemStack(Material.IRON_INGOT, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d);
		}
		if (e.getType() == EntityType.CREEPER && e.hasMetadata("qnt")) {
			Random r2 = new Random();
			int ia = r2.nextInt(100);
			if (ia == 35) {
				e.getWorld().dropItemNaturally(e.getLocation(), new ItemStack(Material.MONSTER_EGG, 1, (short) 50));
			}
			ItemStack d2 = new ItemStack(Material.SULPHUR, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d2);
		}
		if (e.getType() == EntityType.SKELETON && e.hasMetadata("qnt")) {
			ItemStack d = new ItemStack(Material.BONE, dps);
			ItemStack d3 = new ItemStack(Material.ARROW, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d3);
		}
		if (e.getType() == EntityType.WITHER && e.hasMetadata("qnt")) {
			ItemStack d = new ItemStack(Material.NETHER_STAR, 1);
			Random r3 = new Random();
			int ia2 = r3.nextInt(100);
			if (ia2 == 65) {
				String prefix = PermissionsEx.getUser(p).getGroups()[0].getPrefix();
				if (!prefix.equals("")) {
					prefix = new StringBuilder(String.valueOf(prefix.charAt(1))).toString();
				}
				if (prefix.equals("")) {
					prefix = "7";
				}
				ItemStack a1 = new ItemStack(Material.NETHER_STAR);
				ItemMeta am = a1.getItemMeta();
				am.setDisplayName("§6+1 de Poder Máximo");
				List<String> lore = new ArrayList<String>();
				lore.add("§fAtivando este item você aumenta");
				lore.add("§f 1 ponto em seu poder máximo.");
				lore.add("§1");
				lore.add("§f* Limite de poder máximo: 5");
				am.setLore(lore);
				a1.setItemMeta(am);
				p.getLocation().getWorld().dropItemNaturally(e.getLocation(), a1);
				String bruto = "§4§lBOLADAMENTE BOLADO";
				ChatColor c = ChatColor.DARK_RED;
				FancyMessage fa = new FancyMessage("").then("§" + prefix + p.getName()).color(ChatColor.GRAY)
						.itemTooltip(a1).then(" §econseguiu um item ").color(ChatColor.YELLOW).itemTooltip(a1)
						.then(bruto).color(c).itemTooltip(a1).then(" §ematando um Wither!").color(ChatColor.YELLOW)
						.itemTooltip(a1);
				for (Player todos : Bukkit.getOnlinePlayers()) {
					fa.send(todos);
				}
				p.getWorld().strikeLightning(p.getLocation().clone().add(0.0, 5.0, 0.0));
			}
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d);
		}
		if (e.getType() == EntityType.WITHER_SKULL && e.hasMetadata("qnt")) {
			ItemStack d = new ItemStack(Material.BONE, dps);
			ItemStack d3 = new ItemStack(Material.COAL, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d3);
		}
		if (e.getType() == EntityType.SPIDER && e.hasMetadata("qnt")) {
			ItemStack d = new ItemStack(Material.STRING, dps);
			ItemStack d3 = new ItemStack(Material.FERMENTED_SPIDER_EYE, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d3);
		}
		if (e.getType() == EntityType.CAVE_SPIDER && e.hasMetadata("qnt")) {
			ItemStack d = new ItemStack(Material.STRING, dps);
			ItemStack d3 = new ItemStack(Material.FERMENTED_SPIDER_EYE, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d3);
		}
		if (e.getType() == EntityType.GHAST && e.hasMetadata("qnt")) {
			ItemStack d = new ItemStack(Material.GHAST_TEAR, dps);
			ItemStack d3 = new ItemStack(Material.FIREBALL, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d3);
		}
		if (e.getType() == EntityType.ENDERMAN && e.hasMetadata("qnt")) {
			ItemStack d = new ItemStack(Material.ENDER_PEARL, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d);
		}
		if (e.getType() == EntityType.ENDER_DRAGON && e.hasMetadata("qnt")) {
			ItemStack d = new ItemStack(Material.ENDER_PORTAL, dps);
			ItemStack d3 = new ItemStack(Material.ENDER_PEARL, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d3);
		}
		if (e.getType() == EntityType.BLAZE && e.hasMetadata("qnt")) {
			Random r2 = new Random();
			int ia = r2.nextInt(100);
			if (ia == 35) {
				ItemStack a2 = new ItemStack(Material.BLAZE_ROD);
				a2.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
				ItemMeta am2 = a2.getItemMeta();
				am2.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
				am2.setDisplayName("§6Raio Mestre");
				am2.setLore(Arrays.asList("§7Use para lançar um raio aonde você", "§7estiver olhando."));
				a2.setItemMeta(am2);
				e.getWorld().dropItemNaturally(e.getLocation(), a2);
			}
			ItemStack d2 = new ItemStack(Material.BLAZE_ROD, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d2);
		}
		if (e.getType() == EntityType.MAGMA_CUBE && e.hasMetadata("qnt")) {
			ItemStack d = new ItemStack(Material.MAGMA_CREAM, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d);
		}
		if (e.getType() == EntityType.SLIME && e.hasMetadata("qnt")) {
			ItemStack d = new ItemStack(Material.SLIME_BALL, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d);
		}
		if (e.getType() == EntityType.ZOMBIE && e.hasMetadata("qnt")) {
			ItemStack d = new ItemStack(Material.ROTTEN_FLESH, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d);
		}
		if (e.getType() == EntityType.PIG_ZOMBIE && e.hasMetadata("qnt")) {
			Random r2 = new Random();
			int ia = r2.nextInt(100);
			if (ia >= 35 && ia <= 37) {
				e.getWorld().dropItemNaturally(e.getLocation(), new ItemStack(Material.GOLD_INGOT, 2));
			}
			ItemStack d2 = new ItemStack(Material.ROTTEN_FLESH, dps);
			ItemStack d4 = new ItemStack(Material.GOLD_NUGGET, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d2);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d4);
		}
		if (e.getType() == EntityType.WITCH && e.hasMetadata("qnt")) {
			ItemStack d = new ItemStack(Material.REDSTONE, dps);
			ItemStack d3 = new ItemStack(Material.STICK, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d3);
		}
		if (e.getType() == EntityType.PIG && e.hasMetadata("qnt")) {
			ItemStack d = new ItemStack(Material.PORK, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d);
		}
		if (e.getType() == EntityType.SHEEP && e.hasMetadata("qnt")) {
			ItemStack d = new ItemStack(Material.WOOL, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d);
		}
		if (e.getType() == EntityType.COW && e.hasMetadata("qnt")) {
			ItemStack d = new ItemStack(Material.RAW_BEEF, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d);
			ItemStack d3 = new ItemStack(Material.LEATHER, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d3);
		}
		if (e.getType() == EntityType.MUSHROOM_COW && e.hasMetadata("qnt")) {
			ItemStack d = new ItemStack(Material.RAW_BEEF, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d);
		}
		if (e.getType() == EntityType.CHICKEN && e.hasMetadata("qnt")) {
			ItemStack d = new ItemStack(Material.RAW_CHICKEN, dps);
			ItemStack d3 = new ItemStack(Material.FEATHER, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d3);
		}
		if (e.getType() == EntityType.GIANT && e.hasMetadata("qnt")) {
			ItemStack d = new ItemStack(Material.ROTTEN_FLESH, dps);
			e.getLocation().getWorld().dropItemNaturally(e.getLocation(), d);
		}
	}

	public static int getAdd(Entity e) {
		int i = 0;
		int xx = 16;
		int yy = 16;
		int zz = 16;
		for (int x = -xx; x <= xx; ++x) {
			for (int y = -yy; y <= yy; ++y) {
				for (int z = -zz; z <= zz; ++z) {
					BlockState blockstate = e.getLocation().clone().add((double) x, (double) y, (double) z).getBlock()
							.getState();
					if (blockstate.getType().equals(Material.MOB_SPAWNER)) {
						CreatureSpawner spawner = (CreatureSpawner) blockstate;
						if (spawner.getSpawnedType().equals(e.getType())) {
							++i;
						}
					}
				}
			}
		}
		return i;
	}

	public static Integer getNumeroSpawners(Entity e) {
		int i = 0;
		int xx = 16;
		int yy = 16;
		int zz = 16;
		for (int x = -xx; x <= xx; ++x) {
			for (int y = -yy; y <= yy; ++y) {
				for (int z = -zz; z <= zz; ++z) {
					BlockState blockstate = e.getLocation().clone().add((double) x, (double) y, (double) z).getBlock()
							.getState();
					if (blockstate.getType().equals(Material.MOB_SPAWNER)) {
						CreatureSpawner spawner = (CreatureSpawner) blockstate;
						if (spawner.getSpawnedType().equals(e.getType())) {
							++i;
						}
					}
				}
			}
		}
		return i;
	}

	public int getRandomNumer(int min, int max) {
		int val = new Random().nextInt(max);
		while (val < min) {
			val = new Random().nextInt(max);
			if (val > min) {
				break;
			}
		}
		return val;
	}

	public static List<Block> getNearbyBlocks(Location location, int radius) {
		List<Block> blocks = new ArrayList<Block>();
		for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; ++x) {
			for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; ++y) {
				for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; ++z) {
					blocks.add(location.getWorld().getBlockAt(x, y, z));
				}
			}
		}
		return blocks;
	}
}
