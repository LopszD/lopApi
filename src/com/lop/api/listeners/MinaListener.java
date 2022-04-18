package com.wandy.api.listeners;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.events.experience.McMMOPlayerXpGainEvent;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.massivecore.ps.PS;
import com.wandy.api.Main;
import com.wandy.api.mana.ManaModel;

public class MinaListener implements Listener {

	public static NumberFormat numberFormat;
	public static HashMap<Player, Integer> blockBreak = new HashMap<>();

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoQuebrar(BlockBreakEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (!BoardColl.get().getFactionAt(PS.valueOf(e.getBlock())).isNone()) {
			return;
		}
		if (e.getPlayer().getWorld().getName().equalsIgnoreCase("minas")) {
			if (e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
				if (e.getPlayer().getItemInHand() != null) {
					if (!(e.getPlayer().getItemInHand().getType() == Material.AIR)) {
						String ty = new StringBuilder().append(e.getPlayer().getItemInHand().getType()).toString();
						if (ty.contains("PICKAXE")) {
							ItemStack i = e.getPlayer().getItemInHand();
							if (i.hasItemMeta()) {
								if (i.getItemMeta().hasLore()) {
									if (i.getItemMeta().getLore().contains("§7Derretimento I")) {
										if (e.getBlock() != null) {
											if (!(e.getBlock().getType() == Material.AIR)) {
												int ta = 1;
												if (i.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
													int tes = i.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1;
													ta = 1 * tes;
												}
												if (e.getBlock().getType() == Material.IRON_ORE) {
													e.setCancelled(true);
													e.getBlock().setType(Material.AIR);
													e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT, ta));
												}
												if (e.getBlock().getType() == Material.COAL_ORE) {
													e.setCancelled(true);
													e.getBlock().setType(Material.AIR);
													e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.COAL, ta));
												}
												if (e.getBlock().getType() == Material.GOLD_ORE) {
													e.setCancelled(true);
													e.getBlock().setType(Material.AIR);
													e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT, ta));
												}
												if (e.getBlock().getType() == Material.DIAMOND_ORE) {
													e.setCancelled(true);
													e.getBlock().setType(Material.AIR);
												    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND, ta));
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public static void aoBater(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getWorld().getName().equals("world")) {
			if (e.getClickedBlock() == null) {
				return;
			}
			if (e.getClickedBlock().getType() == Material.AIR) {
				return;
			}
			if (p.getGameMode() == GameMode.SURVIVAL) {
				if ((e.getClickedBlock().getType() == Material.DIAMOND_ORE) || (e.getClickedBlock().getType() == Material.EMERALD_ORE) || (e.getClickedBlock().getType() == Material.IRON_ORE) || (e.getClickedBlock().getType() == Material.GOLD_ORE) || (e.getClickedBlock().getType() == Material.COAL_ORE) || (e.getClickedBlock().getType() == Material.LAPIS_ORE) || (e.getClickedBlock().getType() == Material.REDSTONE_ORE)) {
					e.getClickedBlock().setType(Material.STONE);
				}
			}
			return;
		}
		if (p.getWorld().getName().equalsIgnoreCase("minas")) {
			if (e.getClickedBlock() == null) {
				return;
			}
			if (e.getClickedBlock().getType() == Material.AIR) {
				return;
			}
			if (p.getGameMode() == GameMode.SURVIVAL) {
				if ((e.getClickedBlock().getType() == Material.DIAMOND_ORE) || (e.getClickedBlock().getType() == Material.EMERALD_ORE) || (e.getClickedBlock().getType() == Material.IRON_ORE) || (e.getClickedBlock().getType() == Material.GOLD_ORE) || (e.getClickedBlock().getType() == Material.COAL_ORE) || (e.getClickedBlock().getType() == Material.LAPIS_ORE) || (e.getClickedBlock().getType() == Material.REDSTONE_ORE)) {
					e.getClickedBlock().setType(Material.STONE);
				}
			}
		}
	}

	@EventHandler
	public void onBreakM(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (e.getBlock().getLocation().getWorld().getName().equalsIgnoreCase("minas")) {
			if (e.getBlock().getType() == Material.STONE) {
				if (Main.getInstance().maniacs.containsKey(p.getName())) {
					ManaModel model = Main.getInstance().maniacs.get(p.getName());
					model.addMana(5);
				}
			}
		}
	}

	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent event) {
		Player player = event.getPlayer();
		World world = player.getWorld();
		if (world.getName().equalsIgnoreCase("minas")) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 999999, 3));
		} else if (player.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
			player.removePotionEffect(PotionEffectType.FAST_DIGGING);
		}
	}

	@EventHandler
	void event(BlockPlaceEvent e) {
		if ((e.getBlock().getWorld().getName().equalsIgnoreCase("minas")) && (e.getBlock().getType() == Material.MOB_SPAWNER)) {
			e.setBuild(false);
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void BlockPlaceEvent(BlockPlaceEvent e) {
	if (e.getBlock().getType() == Material.BEACON) {
		if (e.getPlayer().getWorld().getName().equalsIgnoreCase("minas") && !(e.getPlayer().getGameMode() == GameMode.CREATIVE)) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cVocê não pode colocar sinalizador no mundo de mineração.");
		}
	}
}

	@EventHandler
	public void onSpawnItem(ItemSpawnEvent e) {
		Material itemType = e.getEntity().getItemStack().getType();
		if ((itemType == Material.COBBLESTONE) || (itemType == Material.GRAVEL) || (itemType == Material.DIRT) || (itemType == Material.STONE)) {
			if (e.getLocation().getWorld().getName().equalsIgnoreCase("minas")) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void claim(PlayerCommandPreprocessEvent e) {
		if (((e.getMessage().contains("/f proteger")) || (e.getMessage().contains("/f dominar")) || (e.getMessage().contains("/f claim"))) && (e.getPlayer().getWorld().getName().equalsIgnoreCase("minas"))) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cVocê não pode executar este comando no mundo de mineração.");
		}
	}

	@EventHandler
	public void onAntiLogDmg(EntityDamageByEntityEvent event) {
		if (((event.getDamager() instanceof Player)) && ((event.getEntity() instanceof Player))) {
			Player damager = (Player) event.getDamager();
			if (damager.getWorld().getName().equalsIgnoreCase("minas")) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamageEvent(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getEntity();
		if (((e.getCause() == EntityDamageEvent.DamageCause.FIRE) || (e.getCause() == EntityDamageEvent.DamageCause.FALL) || (e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) || (e.getCause() == EntityDamageEvent.DamageCause.LAVA)) && (p.getWorld().getName().equalsIgnoreCase("minas"))) {
			e.setCancelled(true);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void gainXP(McMMOPlayerXpGainEvent event) {
		if (((event.getPlayer().getWorld().getName().equalsIgnoreCase("minas")) || (event.getPlayer().getWorld().getName().equalsIgnoreCase("vip"))) && (event.getSkill() == SkillType.ACROBATICS)) {
			event.setXpGained(0);
			event.setRawXpGained(0.0F);
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand().getType().name().contains("_PICKAXE") && e.getBlock().getLocation().getWorld().getName().equalsIgnoreCase("minas")) {
			if (e.getBlock().getType() == Material.STONE) {
				putPlayerHasmMap(p);
				sendMessagesReceiveBox(p);
			}
		}
	}

	private void putPlayerHasmMap(Player p) {
		int i = 1;
		if (blockBreak.containsKey(p)) {
			i += blockBreak.get(p);
			blockBreak.put(p, i);
		} else {
			blockBreak.put(p, i);
		}
	}

	@EventHandler
	public void onCmd(PlayerCommandPreprocessEvent event) {
		Player p = event.getPlayer();
		if (event.getMessage().toLowerCase().startsWith("/blocos")) {
			event.setCancelled(true);
			p.sendMessage("§aVocê quebrou " + format(blockBreak.get(p)) + " blocos na mina.");
			return;
		}
	}

	private void sendMessagesReceiveBox(Player p) {
			if (blockBreak.get(p) == 1500) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "especiais " + p.getName() + " básica 1");
			} else if (blockBreak.get(p) == 5000) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "especiais " + p.getName() + " platina 1");
			} else if (blockBreak.get(p) == 7500) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "especiais " + p.getName() + " lendária 1");
			} else if (blockBreak.get(p) == 10000) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "especiais" + p.getName() + " lendária 1");
		}
	}

	public static String format(double value) {
		numberFormat = NumberFormat.getNumberInstance(Locale.forLanguageTag("en-US"));
		if (value <= 1.0D) {
			return numberFormat.format(value).concat(" ").concat("");
		}
		return numberFormat.format(value).concat(" ").concat("").replace(",", ".").replace(" ", "");
	}
}
