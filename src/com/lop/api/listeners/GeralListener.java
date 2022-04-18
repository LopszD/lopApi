package com.wandy.api.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.event.entity.EntityMountEvent;

import com.wandy.api.Main;
import com.wandy.api.commands.BackCommand;
import com.wandy.api.commands.ColetarCommand;
import com.wandy.api.commands.FlyCommand;
import com.wandy.api.commands.InventarioCommand;
import com.wandy.api.utils.Proteção;
import com.wandy.api.utils.partículas.Particles;
import com.wandy.godeye.listeners.GodEyesListener;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.EnumParticle;

public class GeralListener implements Listener {
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void eventts(PlayerTeleportEvent e) {
		if (e.isCancelled()) {
			return;
		}
		final Player player = e.getPlayer();
		Location from = e.getFrom();
		if (player.hasPermission("wandy.vip")) {
			if (GodEyesListener.getGodeye().contains(player)) {
				GodEyesListener.getGodeye().remove(player);
				return;
			}
			if (from.equals(Bukkit.getWorld("world").getSpawnLocation())) {
				return;
			}
			if (BackCommand.backscooldown.contains(player.getName())) {
				return;
			}
			if ((e.getTo().getWorld().equals(e.getFrom().getWorld())) && (e.getTo().distance(e.getFrom()) < 5.0D)) {
				return;
			}
			BackCommand.backs.remove(player.getName());
			BackCommand.backscooldown.add(player.getName());
			BackCommand.backs.put(player.getName(), from);
			new BukkitRunnable() {
				public void run() {
					BackCommand.backscooldown.remove(player.getName());
				}
			}.runTaskTimerAsynchronously(Main.getInstance(), 0L, 21L);
			return;
		}
	}
	
	@EventHandler
	void event(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		e.setQuitMessage(null);
		if (BackCommand.backs.containsKey(player.getName())) {
			BackCommand.backs.remove(player.getName());
		}
		if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
			player.removePotionEffect(PotionEffectType.NIGHT_VISION);
		}
		if (player.hasPotionEffect(PotionEffectType.JUMP)) {
			player.removePotionEffect(PotionEffectType.JUMP);
		}
	}
	
	@EventHandler
	void event(PlayerDeathEvent e) {
		Player player = e.getEntity();
		Player killer = e.getEntity().getKiller();
		if ((!(player instanceof Player)) && (!(killer instanceof Player))) {
			return;
		}
		e.setDeathMessage(null);
		if (player.hasPermission("wandy.vip")) {
			e.setDroppedExp(0);
			e.setKeepLevel(true);
			BackCommand.backs.remove(player.getName());
			BackCommand.backscooldown.add(player.getName());
			BackCommand.backs.put(player.getName(), player.getLocation());
			new BukkitRunnable() {
				public void run() {
					BackCommand.backscooldown.remove(player.getName());
				}
			}.runTaskTimerAsynchronously(Main.getInstance(), 0L, 21L);
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = false)
	public static void aoQuebrar(BlockBreakEvent e) {
		if (!e.isCancelled()) {
			if (e.getBlock() != null) {
				if (e.getBlock().getType().equals(Material.ENDER_CHEST)) {
					if (e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
						e.setCancelled(true);
						e.getBlock().setType(Material.AIR);
						e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.ENDER_CHEST));
					}
				}
			}
		}
	}
	
	@EventHandler
	public static void aoGamemodar(PlayerGameModeChangeEvent e) {
		Player p = e.getPlayer();
		if (FlyCommand.voando.contains(p.getName())) {
			FlyCommand.voando.remove(p.getName());
		}
	}
	
	@EventHandler
	public static void aoCairr(EntityChangeBlockEvent e) {
		if (e.getEntity().getType().equals(EntityType.FALLING_BLOCK)) {
			e.getBlock().getState().update();
			e.getEntity().remove();
			e.getBlock().getState().update();
			e.setCancelled(true);
			e.getBlock().getState().update();
		}
	}

	@EventHandler
	public static void aoCair(BlockPhysicsEvent e) {
		if ((e.getBlock().getType().equals(Material.SAND)) || (e.getBlock().getType().equals(Material.ANVIL)) || (e.getBlock().getType().equals(Material.GRAVEL))) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public static void aoExplodir(EntityExplodeEvent e) {
		if (e.getEntity().getType().equals(EntityType.PRIMED_TNT)) {
			for (Block b : e.blockList()) {
				if (b.getType().equals(Material.TNT)) {
					b.setType(Material.AIR);
					b.getLocation().getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.TNT));
				}
			}
		}
	}
	
	@EventHandler
	public static void aoGlassar(PlayerPortalEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public static void aoGlassar(InventoryClickEvent e) {
		if (e.getInventory().getType().equals(InventoryType.FURNACE)) {
			if (e.getCurrentItem() == null) {
				return;
			}
			if (e.getCurrentItem().getType().equals(Material.SAND)) {
				e.setCancelled(true);
				e.getWhoClicked().sendMessage("§cA criação do vidro foi bloqueada! Para obter este item, adquira na loja.");
			}
		}
	}
	
	@EventHandler
	public void ping(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (e.getMessage().toLowerCase().startsWith("/ping")) {
			e.setCancelled(true);
			p.sendMessage("§aVocê está com " + getPing(p) + "ms.");
			return;
		}
	}

	@EventHandler
	public void onVoidDamage(EntityDamageEvent event) {
		if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
			Entity ent = event.getEntity();
			if (ent == null) {
				return;
			}
			if (ent.getType() != EntityType.PLAYER) {
				return;
			}
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		Player p = Bukkit.getPlayerExact(e.getPlayer().getName());
		if (p != null) {
			e.disallow(Result.KICK_OTHER, "§c§lREDE WANDY\n§1\n§cSua conta já está logada!");
		}
	}

	@EventHandler
	public void onPickup(EntityChangeBlockEvent e) {
		if (e.getEntity().hasMetadata("qnt")) {
			e.setCancelled(true);
			return;
		}
	}

	@EventHandler
	public void onInteract(BlockPlaceEvent e) {
		if (e.getBlock().getType().name().toLowerCase().contains("piston")) {
			e.setCancelled(true);
			return;
		}
	}

	@EventHandler
	void event(FoodLevelChangeEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		e.setCancelled(true);
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && !e.getPlayer().hasPermission("blockburied.bypass") && e.getPlayer().getItemInHand().getType() == Material.MONSTER_EGG) {
			World world = Bukkit.getWorld(e.getClickedBlock().getLocation().getWorld().getName());
			double x = e.getClickedBlock().getLocation().getX();
			double y = e.getClickedBlock().getLocation().getY() + 2.0;
			double z = e.getClickedBlock().getLocation().getZ();
			Location l = new Location(world, x, y, z);
			if (l.getBlock().getType() != Material.AIR) {
				e.setCancelled(true);
				e.getPlayer().sendMessage("§cEste bloco está soterrado.");
			}
		}
	}

	@EventHandler
	public void onTeleport(PlayerTeleportEvent e) {
		if (!e.getPlayer().hasPermission("blockburied.bypass")) {
			Material b = e.getTo().getBlock().getType();
			if (b.equals(Material.STEP) || b.equals(Material.WOOD_STEP) || b.equals(Material.STONE_SLAB2)
					|| b.equals(Material.ACACIA_STAIRS) || b.equals(Material.SPRUCE_WOOD_STAIRS)
					|| b.equals(Material.BIRCH_WOOD_STAIRS) || b.equals(Material.JUNGLE_WOOD_STAIRS)
					|| b.equals(Material.SANDSTONE_STAIRS) || b.equals(Material.NETHER_BRICK_STAIRS)
					|| b.equals(Material.RED_SANDSTONE_STAIRS) || b.equals(Material.COBBLE_WALL)
					|| b.equals(Material.BRICK_STAIRS) || b.equals(Material.QUARTZ_STAIRS)
					|| b.equals(Material.SMOOTH_STAIRS) || b.equals(Material.WOOD_STAIRS) || b.equals(Material.LADDER)
					|| b.equals(Material.FENCE) || b.equals(Material.NETHER_FENCE) || b.equals(Material.SPRUCE_FENCE)
					|| b.equals(Material.BIRCH_FENCE) || b.equals(Material.JUNGLE_FENCE)
					|| b.equals(Material.DARK_OAK_FENCE) || b.equals(Material.ACACIA_FENCE) || b.equals(Material.VINE)
					|| b.equals(Material.SIGN) || b.equals(Material.IRON_FENCE) || b.equals(Material.ANVIL)
					|| b.equals(Material.BED) || b.equals(Material.STONE_PLATE) || b.equals(Material.WOOD_PLATE)
					|| b.equals(Material.LEVER) || b.equals(Material.STONE_BUTTON) || b.equals(Material.TRAP_DOOR)
					|| b.equals(Material.STONE_PLATE) || b.equals(Material.FENCE_GATE)
					|| b.equals(Material.REDSTONE_TORCH_ON) || b.equals(Material.REDSTONE_TORCH_OFF)
					|| b.equals(Material.WOOD_BUTTON) || b.equals(Material.GOLD_PLATE) || b.equals(Material.IRON_PLATE)
					|| b.equals(Material.DAYLIGHT_DETECTOR) || b.equals(Material.HOPPER)
					|| b.equals(Material.IRON_TRAPDOOR) || b.equals(Material.SPRUCE_FENCE_GATE)
					|| b.equals(Material.BIRCH_FENCE_GATE) || b.equals(Material.JUNGLE_FENCE_GATE)
					|| b.equals(Material.DARK_OAK_FENCE_GATE) || b.equals(Material.ACACIA_FENCE_GATE)
					|| b.equals(Material.DIODE) || b.equals(Material.POWERED_RAIL)
					|| b.equals(Material.REDSTONE_COMPARATOR) || b.equals(Material.RAILS)
					|| b.equals(Material.DETECTOR_RAIL) || b.equals(Material.CAULDRON)
					|| b.equals(Material.ACTIVATOR_RAIL) || b.equals(Material.BREWING_STAND)) {
				e.setCancelled(true);

				if (e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
					e.getPlayer().getInventory().addItem(new ItemStack[] { new ItemStack(Material.ENDER_PEARL) });
				}
				e.getPlayer().sendMessage("§cEste bloco está soterrado.");
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockFromToEvent(BlockFromToEvent e) {
		if (e.isCancelled())
			return;

		if (e.getToBlock().getLocation().getY() >= 1.0 && e.getToBlock().getLocation().getY() <= 256.0
				&& e.getBlock().getData() >= 8) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onOpen(InventoryOpenEvent e) {
		if (e.getInventory() == null) {
			e.getPlayer().sendMessage("§cVocê tentou abrir um invetário nulo.");
			e.getPlayer().closeInventory();
			e.setCancelled(true);
		}

		if (e.getInventory().getType().equals(InventoryType.ENDER_CHEST)) {
			e.getPlayer().sendMessage("§cVocê tentou abrir um invetário nulo. Tente novamente.");
			e.getPlayer().closeInventory();
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Block b = e.getBlock();
		if (b.getType().equals(Material.REDSTONE_COMPARATOR_OFF) || b.getType().equals(Material.REDSTONE_COMPARATOR_ON)
				|| b.getType().equals(Material.DIODE_BLOCK_OFF) || b.getType().equals(Material.DIODE_BLOCK_ON)
				|| b.getType().equals(Material.REDSTONE_WIRE) || b.getType().equals(Material.REDSTONE_BLOCK)
				|| b.getType().equals(Material.REDSTONE_TORCH_OFF) || b.getType().equals(Material.REDSTONE_TORCH_ON)) {
			for (Block block : getNearbyBlocks(b.getLocation(), 8)) {
				if (block.getType().equals(Material.BEACON)) {
					e.setCancelled(true);
					e.getPlayer().sendMessage("§cVocê não pode criar sistemas de redstone perto de um beacon.");
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onItemPuckup(PlayerPickupItemEvent e) {
		if (e.getItem().getItemStack().getAmount() < 1) {
			e.getItem().remove();
			return;
		}
		Player p = e.getPlayer();
		if (InventarioCommand.inventory.contains(p.getName())) {
			e.setCancelled(true);
			return;
		}
		if (ColetarCommand.collectores.containsKey(p.getName())) {
			List<Integer> items = ColetarCommand.collectores.get(p.getName());
			if (!(items.contains(e.getItem().getItemStack().getTypeId()))) {
				e.setCancelled(true);
				return;
			}
		}
	}

	@EventHandler
	public void onGainXP(PlayerInteractEvent e) {
		if (e.hasItem()) {
			Player p = e.getPlayer();
			ItemStack item = p.getItemInHand();
			if (item.getType() == Material.EXP_BOTTLE && !item.hasItemMeta()) {
				if (!e.isCancelled()) {
					e.setCancelled(true);
					if (p.isSneaking()) {
						int multiplier = item.getAmount();
						p.giveExp(19 * multiplier);
						p.setItemInHand(null);
						return;
					} else {
						p.giveExp(19);
						if (item.getAmount() > 1) {
							item.setAmount(item.getAmount() - 1);
							return;
						} else {
							p.setItemInHand(null);
							return;
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (p.getOpenInventory().getType().equals(InventoryType.ANVIL)) {
			InventoryView inv = p.getOpenInventory();
			if (inv.getItem(0) != null) {
				inv.getItem(0).setType(Material.AIR);
				return;
			}
		}
	}
	
	@EventHandler
	private void aoSair(PlayerQuitEvent e) {
		e.setQuitMessage("");
	}

	@EventHandler
	public void onAnvil(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getType() == InventoryType.ANVIL) {
			if (e.getClick() == ClickType.NUMBER_KEY) {
				e.setCancelled(true);
				return;
			}
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.MOB_SPAWNER) {
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage("§cVocê não pode colocar 'Geradores de Monstros' na bigorna.");
			}
			if (e.getRawSlot() == 2) {
				for (ItemStack is : e.getInventory().getContents()) {
					if (is == null) {
						continue;
					}
					if (is.getType() == Material.MOB_SPAWNER) {
						e.setCancelled(true);
						p.closeInventory();
						p.sendMessage("§cVocê não pode colocar 'Geradores de Monstros' na bigorna.");
						break;
					} else if (is.getType() == Material.CHEST) {
						e.setCancelled(true);
						p.closeInventory();
						p.sendMessage("§cVocê não pode colocar 'Baús' na bigorna.");
						break;
					} else if (is.getType() == Material.ENDER_CHEST) {
						e.setCancelled(true);
						p.closeInventory();
						p.sendMessage("§cVocê não pode colocar 'EnderChest' na bigorna.");
						break;
					} else if (is.getType() == Material.MONSTER_EGG) {
						e.setCancelled(true);
						p.closeInventory();
						p.sendMessage("§cVocê não pode colocar 'Ovos' na bigorna.");
						break;
					} else if (is.getType() == Material.BLAZE_ROD) {
						e.setCancelled(true);
						p.closeInventory();
						p.sendMessage("§cVocê não pode colocar 'Vara Incandescente' na bigorna.");
						break;
					} else if (is.getType() == Material.FIREBALL) {
						e.setCancelled(true);
						p.closeInventory();
						p.sendMessage("§cVocê não pode colocar 'Bola de Fogo' na bigorna.");
						break;
					} else if (is.getType() == Material.NETHER_STAR) {
						e.setCancelled(true);
						p.closeInventory();
						p.sendMessage("§cVocê não pode colocar 'Estrela do Nether' na bigorna.");
						break;
					} else if (is.getType() == Material.FIREWORK) {
						e.setCancelled(true);
						p.closeInventory();
						p.sendMessage("§cVocê não pode colocar 'Fogos de Artifício' na bigorna.");
						break;
					} else if (is.getType() == Material.HOPPER) {
						e.setCancelled(true);
						p.closeInventory();
						p.sendMessage("§cVocê não pode colocar 'Funil' na bigorna.");
						break;
					} else if (is.getType() == Material.LAVA_BUCKET) {
						e.setCancelled(true);
						p.closeInventory();
						p.sendMessage("§cVocê não pode colocar 'Balde de Lava' na bigorna.");
						break;
					} else if (is.getType() == Material.WATER_BUCKET) {
						e.setCancelled(true);
						p.closeInventory();
						p.sendMessage("§cVocê não pode colocar 'Balde de Água' na bigorna.");
						break;
					} else if (is.getType() == Material.NAME_TAG) {
						e.setCancelled(true);
						p.closeInventory();
						p.sendMessage("§cVocê não pode colocar 'Etiquetas' na bigorna.");
						break;
					}
				}
			}
		}
	}

	@EventHandler
	public void ontntexplode(EntityExplodeEvent e) {
		if (e.getEntityType().equals(EntityType.PRIMED_TNT)) {
			Location loc = e.getLocation();
			for (int i = 0; i < 30; i++) {
				loc.getWorld().playEffect(loc, Effect.FLAME, 20);
				loc.getWorld().playEffect(loc, Effect.LAVA_POP, 20);
				Particles packet = new Particles(EnumParticle.EXPLOSION_NORMAL, loc, 1.0F, 1.0F, 1.0F, 0.05F, 3);
				packet.sendToAll();
			}
		}
	}

	@EventHandler
	public void oncreeperexplode(EntityExplodeEvent e) {
		if (e.getEntityType().equals(EntityType.CREEPER)) {
			Location loc = e.getLocation();
			for (int i = 0; i < 30; i++) {
				loc.getWorld().playEffect(loc, Effect.FLAME, 20);
				loc.getWorld().playEffect(loc, Effect.LAVA_POP, 20);
				Particles packet = new Particles(EnumParticle.EXPLOSION_LARGE, loc, 1.0F, 1.0F, 1.0F, 0.05F, 3);
				packet.sendToAll();
			}
		}
	}
	
	@EventHandler
	public void onballexplode(EntityExplodeEvent e) {
		if (e.getEntityType().equals(EntityType.FIREBALL)) {
			Location loc = e.getLocation();
			for (int i = 0; i < 30; i++) {
				loc.getWorld().playEffect(loc, Effect.FLAME, 20);
				loc.getWorld().playEffect(loc, Effect.LAVA_POP, 20);
				Particles packet = new Particles(EnumParticle.EXPLOSION_NORMAL, loc, 1.0F, 1.0F, 1.0F, 0.05F, 3);
				packet.sendToAll();
			}
		}
	}
	
	@EventHandler
	public void onarrowlance(EntityExplodeEvent e) {
		if (e.getEntityType().equals(EntityType.ARROW)) {
			Location loc = e.getLocation();
				for (int i = 0; i < 30; i++) {
					loc.getWorld().playEffect(loc, Effect.COLOURED_DUST, 20);
			}
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	void event(VehicleCreateEvent event) {
		Vehicle vehicle = event.getVehicle();
		if ((vehicle instanceof Boat)) {
			Boat boat = (Boat) vehicle;
			Block belowBlock = boat.getLocation().add(0.0D, -1.0D, 0.0D).getBlock();
			if ((belowBlock.getType() != Material.WATER) && (belowBlock.getType() != Material.STATIONARY_WATER)) {
				boat.remove();
			}
		}
	}

	@EventHandler
	void event(BlockPistonExtendEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	void event(BlockPistonRetractEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	void event2(EntityDamageByEntityEvent e) {
		if (((e.getEntityType().equals(EntityType.DROPPED_ITEM))
				&& (e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION))
				&& ((e.getDamager() instanceof TNTPrimed))) || ((e.getDamager() instanceof Creeper))
				|| ((e.getDamager() instanceof EnderPearl))) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	void event(BlockExplodeEvent e) {
		Block block = e.getBlock();
		if (block.getType().toString().endsWith("_ORE")) {
			block.getDrops().clear();
		}
	}

	@EventHandler
	void event(PrepareItemCraftEvent e) {
		if ((e.getRecipe() == null) || (e.getRecipe().getResult() == null)) {
			return;
		}
		if ((e.getRecipe().getResult().getType().equals(Material.BEACON))
				|| (e.getRecipe().getResult().getType().equals(Material.BOAT))
				|| (e.getRecipe().getResult().getType() == Material.MINECART)
				|| (e.getRecipe().getResult().getType() == Material.COMMAND_MINECART)
				|| (e.getRecipe().getResult().getType() == Material.EXPLOSIVE_MINECART)
				|| (e.getRecipe().getResult().getType() == Material.EXPLOSIVE_MINECART)
				|| (e.getRecipe().getResult().getType() == Material.HOPPER_MINECART)
				|| (e.getRecipe().getResult().getType() == Material.POWERED_MINECART)
				|| (e.getRecipe().getResult().getType() == Material.STORAGE_MINECART)) {
			e.getInventory().setItem(0, new ItemStack(Material.AIR));
			e.getRecipe().getResult().setType(Material.AIR);
		}
	}

	@EventHandler
	void event(EntityMountEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	void event(WeatherChangeEvent e) {
		if (e.toWeatherState()) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	void event(InventoryOpenEvent e) {
		if (e.getInventory().getType().equals(InventoryType.HOPPER)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	void event(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if ((e.getInventory().getType().equals(InventoryType.ANVIL)) && ((e.getClick() == ClickType.SHIFT_LEFT) || (e.getClick() == ClickType.SHIFT_RIGHT))) {
			e.setCancelled(true);
			p.sendMessage("§cVocê não pode utilizar shift na bigorna.");
		}
	}

	@EventHandler
	void event(PlayerBedEnterEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	void event(BlockPlaceEvent e) {
		if ((Proteção.getArea(e.getPlayer()).equals("spawn")) && (e.getBlock().getType().equals(Material.MOB_SPAWNER))) {
			e.setBuild(false);
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void claim(PlayerCommandPreprocessEvent e) {
		if (((e.getMessage().contains("/f proteger")) || (e.getMessage().contains("/f dominar")) || (e.getMessage().contains("/f claim"))) && (Proteção.getArea(e.getPlayer()).equals("spawn"))) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cVocê não pode executar este comando neste local.");
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamageEvent(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getEntity();
		if (((e.getCause() == EntityDamageEvent.DamageCause.FIRE) || (e.getCause() == EntityDamageEvent.DamageCause.FALL) || (e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) || (e.getCause() == EntityDamageEvent.DamageCause.LAVA)) && (Proteção.getArea(p).equals("spawn"))) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onAntiLogDmg(EntityDamageByEntityEvent event) {
		if (((event.getDamager() instanceof Player)) && ((event.getEntity() instanceof Player))) {
			Player damager = (Player) event.getDamager();
			if (Proteção.getArea(damager).equals("spawn")) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	void event(PlayerTeleportEvent event) {
		Location from = event.getFrom();
		Location to = event.getTo();
		if ((from.getBlockX() == to.getBlockX()) && (from.getBlockZ() == to.getBlockZ())) {
			return;
		}
		if ((!isWithinBorder(to)) && (isWithinBorder(from))) {
			Player player = event.getPlayer();
			player.sendMessage(ChatColor.RED + "Você não pode ultrapassar o limite do mundo.");
			event.setTo(from);
		}
	}
	
	@EventHandler
	public void onMoveBoard(PlayerMoveEvent event) {
		if (event.getPlayer().getWorld().getWorldBorder() == null) {
			return;
		}
		double worldborder = event.getPlayer().getWorld().getWorldBorder().getSize() / 2.0D;
		if (event.getPlayer().getWorld().getWorldBorder().getCenter().getX() + worldborder + 0.5D < event.getTo().getX()) {
			event.setCancelled(true);
			event.getPlayer().teleport(event.getPlayer().getWorld().getSpawnLocation());
			return;
		}
		if (event.getPlayer().getWorld().getWorldBorder().getCenter().getX() - worldborder - 0.5D > event.getTo().getX()) {
			event.setCancelled(true);
			event.getPlayer().teleport(event.getPlayer().getWorld().getSpawnLocation());
			return;
		}
		if (event.getPlayer().getWorld().getWorldBorder().getCenter().getZ() + worldborder + 0.5D < event.getTo().getZ()) {
			event.setCancelled(true);
			event.getPlayer().teleport(event.getPlayer().getWorld().getSpawnLocation());
			return;
		}
		if (event.getPlayer().getWorld().getWorldBorder().getCenter().getZ() - worldborder - 0.5D > event.getTo().getZ()) {
			event.setCancelled(true);
			event.getPlayer().teleport(event.getPlayer().getWorld().getSpawnLocation());
			return;
		}
	}

	private boolean isWithinBorder(Location location) {
		double borderSize = location.getWorld().getWorldBorder().getSize() / 2.0D;
		return (Math.abs(location.getBlockX()) <= borderSize) && (Math.abs(location.getBlockZ()) <= borderSize);
	}

	public static List<Block> getNearbyBlocks(Location location, int radius) {
		List<Block> blocks = new ArrayList<Block>();
		for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
			for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
				for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
					blocks.add(location.getWorld().getBlockAt(x, y, z));
				}
			}
		}
		return blocks;
	}

	public int getPing(Player p) {
		CraftPlayer cp = (CraftPlayer) p;
		EntityPlayer ep = cp.getHandle();
		return ep.ping;
	}
}
