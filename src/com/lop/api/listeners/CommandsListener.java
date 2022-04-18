package com.wandy.api.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import com.wandy.api.Main;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class CommandsListener implements Listener {

	public static HashMap<String, Long> enviou;
	public static List<String> messages = new ArrayList<>();

	@EventHandler(ignoreCancelled = false, priority = EventPriority.MONITOR)
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if (e.isCancelled()) {
			e.setCancelled(true);
			return;
		}
		if (!e.isCancelled()) {
			if (e.getMessage().toLowerCase().startsWith("/bank")
					|| e.getMessage().toLowerCase().startsWith("/craftconomy")
					|| e.getMessage().toLowerCase().startsWith("/ccsetup")
					|| e.getMessage().toLowerCase().startsWith("/msg")
					|| e.getMessage().toLowerCase().startsWith("/currency")
					|| e.getMessage().toLowerCase().startsWith("/pay")
					|| e.getMessage().toLowerCase().startsWith("/craftco")
					|| e.getMessage().toLowerCase().startsWith("/bukkit:")
					|| e.getMessage().toLowerCase().startsWith("/bukkit")
					|| e.getMessage().toLowerCase().startsWith("/minecraft:")
					|| e.getMessage().toLowerCase().startsWith("/minecraft")
					|| e.getMessage().toLowerCase().startsWith("/plugin")
					|| e.getMessage().toLowerCase().startsWith("/massivecore")
					|| e.getMessage().toLowerCase().startsWith("/ver")
					|| e.getMessage().toLowerCase().startsWith("/op")
					|| e.getMessage().toLowerCase().startsWith("/deop")
					|| e.getMessage().toLowerCase().startsWith("/worldedit")
					|| e.getMessage().toLowerCase().startsWith("/worldedit:")
					|| e.getMessage().toLowerCase().startsWith("/execute")
					|| e.getMessage().toLowerCase().startsWith("/promote")
					|| e.getMessage().toLowerCase().startsWith("/br")
					|| e.getMessage().toLowerCase().startsWith("/nte")
					|| e.getMessage().toLowerCase().startsWith("/gmc")
					|| e.getMessage().toLowerCase().startsWith("/heal")
					|| e.getMessage().toLowerCase().startsWith("/oex")
					|| e.getMessage().toLowerCase().startsWith("/ml")
					|| e.getMessage().toLowerCase().startsWith("/cm")
					|| e.getMessage().toLowerCase().startsWith("/mass")
					|| e.getMessage().toLowerCase().startsWith("/mc")
					|| e.getMessage().toLowerCase().startsWith("/pex")
					|| e.getMessage().toLowerCase().startsWith("/permissionsex")
					|| e.getMessage().toLowerCase().startsWith("/perm")
					|| e.getMessage().toLowerCase().startsWith("/gerarmundo")
					|| e.getMessage().toLowerCase().startsWith("/gerar")
					|| e.getMessage().toLowerCase().startsWith("/wandycaixas")
					|| e.getMessage().toLowerCase().startsWith("/wandycaixas:")
					|| e.getMessage().toLowerCase().startsWith("/wandymina")
					|| e.getMessage().toLowerCase().startsWith("/wandymina:")
					|| e.getMessage().toLowerCase().startsWith("/customec")
					|| e.getMessage().toLowerCase().startsWith("/customenderchest")
					|| e.getMessage().toLowerCase().startsWith("/wandymana")
					|| e.getMessage().toLowerCase().startsWith("/wandymana:")
					|| e.getMessage().toLowerCase().startsWith("/mundo")
					|| e.getMessage().toLowerCase().startsWith("/?")) {
				e.setCancelled(true);
				e.getPlayer().sendMessage("§cComando desconhecido.");
				return;
			}
			if (e.getMessage().toLowerCase().startsWith("//")) {
				if (!e.getPlayer().getName().equalsIgnoreCase("iflastbr")) {
					e.setCancelled(true);
					e.getPlayer().sendMessage("§cVocê não tem permissão para executar este comando.");
					return;
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommandCash(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String[] msg = e.getMessage().split(" ");
		if (!msg[0].equalsIgnoreCase("/cash")) {
			if (!msg[0].equalsIgnoreCase("/wandycash:cash")) {
			}
		}
		try {
			if (msg[1] != null && (msg[1].equalsIgnoreCase("set")
					|| msg[1].equalsIgnoreCase("adicionar")
					|| msg[1].equalsIgnoreCase("give")
					|| msg[1].equalsIgnoreCase("add")
					|| msg[1].equalsIgnoreCase("remover")
					|| msg[1].equalsIgnoreCase("take")
					|| msg[1].equalsIgnoreCase("remove")
					|| msg[1].equalsIgnoreCase("del")
					|| msg[1].equalsIgnoreCase("definir")
					|| msg[1].equalsIgnoreCase("define"))) {
				int is = 0;
				if (Main.bypass.size() > 0) {
					for (String nomes : Main.bypass) {
						if (nomes.equalsIgnoreCase(p.getName())) {
							++is;
						}
					}
					if (is == 0) {
						e.setCancelled(true);
						p.sendMessage("§cVocê não tem permissão para executar este comando.");
						return;
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException ex) {
		}
	}
	
	public void onCommandCoins(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String[] msg = e.getMessage().split(" ");
		if (!msg[0].equalsIgnoreCase("/money")) {
			if (!msg[0].equalsIgnoreCase("/wandyeconomy:money")) {
			}
		}
		try {
			if (msg[1] != null && (msg[1].equalsIgnoreCase("set")
					|| msg[1].equalsIgnoreCase("adicionar")
					|| msg[1].equalsIgnoreCase("give")
					|| msg[1].equalsIgnoreCase("add")
					|| msg[1].equalsIgnoreCase("remover")
					|| msg[1].equalsIgnoreCase("take")
					|| msg[1].equalsIgnoreCase("remove")
					|| msg[1].equalsIgnoreCase("del")
					|| msg[1].equalsIgnoreCase("definir")
					|| msg[1].equalsIgnoreCase("define"))) {
				int is = 0;
				if (Main.bypass.size() > 0) {
					for (String nomes : Main.bypass) {
						if (nomes.equalsIgnoreCase(p.getName())) {
							++is;
						}
					}
					if (is == 0) {
						e.setCancelled(true);
						p.sendMessage("§cVocê não tem permissão para executar este comando.");
						return;
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException ex) {
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (p.getName().equalsIgnoreCase("iflastbr")) {
			return;
		}
		if (p.isOp()) {
		    Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage(" §f§l[ANÚNCIO] §fO usuário '§c" + p.getName() + "§f' entrou com §c§lOP §fno servidor!");
			Bukkit.broadcastMessage("");
		}
	}
	
	@SuppressWarnings("unused")
	private void checkStaffMore(Player p) {
		if (p.getName().equalsIgnoreCase("iflastbr")) return;
		checkPermissionOrGroups(p);
	}
	
	private void checkPermissionOrGroups(Player p) {
		if (p.hasPermission("*")) PermissionsEx.getUser(p).removePermission("*");
		if (p.isOp()) p.setOp(false);;
		if (PermissionsEx.getUser(p).inGroup("master")) PermissionsEx.getUser(p).removeGroup("master");
		if (PermissionsEx.getUser(p).inGroup("gerente")) PermissionsEx.getUser(p).removeGroup("gerente");
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getMaterial().equals(Material.ITEM_FRAME)) {
			if (!e.getPlayer().hasPermission("wandy.gerente")) {
				e.setCancelled(true);
				return;
			}
		}
		if (((e.getMaterial().equals(Material.MINECART)) || (e.getMaterial().equals(Material.BOAT)) || (e.getMaterial().equals(Material.SADDLE)) || (e.getMaterial().equals(Material.HOPPER_MINECART)) || (e.getMaterial().equals(Material.STORAGE_MINECART))) && (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockPlace(BlockBreakEvent e) {
		if (e.getBlock().getType().equals(Material.WEB)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (e.getBlock().getType().equals(Material.ITEM_FRAME)) {
			if (!e.getPlayer().hasPermission("wandy.gerente")) {
				e.setCancelled(true);
				return;
			}
		}
		if ((e.getBlock().getType().equals(Material.MINECART)) || (e.getBlock().getType().equals(Material.WEB)) || (e.getBlock().getType().equals(Material.BOAT)) || (e.getBlock().getType().equals(Material.SADDLE)) || (e.getBlock().getType().equals(Material.HOPPER_MINECART)) || (e.getBlock().getType().equals(Material.STORAGE_MINECART))) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		if ((e.getItemDrop().getItemStack().getType().equals(Material.MINECART)) || (e.getItemDrop().getItemStack().getType().equals(Material.BOAT)) || (e.getItemDrop().getItemStack().getType().equals(Material.SADDLE)) || (e.getItemDrop().getItemStack().getType().equals(Material.HOPPER_MINECART)) || (e.getItemDrop().getItemStack().getType().equals(Material.STORAGE_MINECART))) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		if ((e.getItem().getItemStack().getType().equals(Material.MINECART)) || (e.getItem().getItemStack().getType().equals(Material.BOAT)) || (e.getItem().getItemStack().getType().equals(Material.SADDLE)) || (e.getItem().getItemStack().getType().equals(Material.HOPPER_MINECART)) || (e.getItem().getItemStack().getType().equals(Material.STORAGE_MINECART))) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlaceHanging(HangingPlaceEvent e) {
		if ((e.getBlock().getType().equals(Material.MINECART)) || (e.getBlock().getType().equals(Material.BOAT)) || (e.getBlock().getType().equals(Material.SADDLE)) || (e.getBlock().getType().equals(Material.HOPPER_MINECART)) || (e.getBlock().getType().equals(Material.STORAGE_MINECART))) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void craftItem(PrepareItemCraftEvent e) {
		Material itemType = e.getRecipe().getResult().getType();
		if ((itemType.equals(Material.MINECART)) || (itemType.equals(Material.BOAT)) || (itemType.equals(Material.BEACON)) || (itemType.equals(Material.SADDLE)) || (itemType.equals(Material.HOPPER)) || (itemType.equals(Material.HOPPER_MINECART)) || (itemType.equals(Material.STORAGE_MINECART))) {
			e.getInventory().setResult(new ItemStack(Material.AIR));
			for (HumanEntity he : e.getViewers()) {
				if ((he instanceof Player)) {
					((Player) he).sendMessage("§cVocê não tem permissão para craftar esse item.");
				}
			}
		}
	}
	
	@EventHandler
	void event(PlayerInteractEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (e.getPlayer().isOp()) {
			return;
		}
		EntityType entity = e.getRightClicked().getType();
		if ((entity.equals(EntityType.BOAT))
				|| (entity.equals(EntityType.MINECART))
				|| (entity.equals(EntityType.MINECART_CHEST))
				|| (entity.equals(EntityType.MINECART_COMMAND))
				|| (entity.equals(EntityType.MINECART_FURNACE))
				|| (entity.equals(EntityType.MINECART_HOPPER))
				|| (entity.equals(EntityType.MINECART_MOB_SPAWNER))
				|| (entity.equals(EntityType.MINECART_TNT))
				|| (entity.equals(EntityType.ITEM_FRAME))
				|| (entity.equals(EntityType.HORSE))) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	void event(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.isCancelled()) {
			return;
		}
		if (p.isOp()) {
			e.setCancelled(false);
			return;
		}
		if (p.getItemInHand() == null) {
			return;
		}
		if (p.getItemInHand().getType() == Material.AIR) {
			return;
		}
		if (p.getItemInHand().getType() == Material.BOAT) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.ITEM_FRAME) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.ARMOR_STAND) {
			e.setCancelled(true);
		} else if (p.getItemInHand().getType() == Material.WATER_LILY) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.MINECART) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.COMMAND_MINECART) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.EXPLOSIVE_MINECART) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.HOPPER_MINECART) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.POWERED_MINECART) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.STORAGE_MINECART) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.IRON_DOOR_BLOCK) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.DARK_OAK_DOOR) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.BIRCH_DOOR) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.IRON_DOOR) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.JUNGLE_DOOR) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!!");
		} else if (p.getItemInHand().getType() == Material.TRAP_DOOR) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.SPRUCE_DOOR) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.WOODEN_DOOR) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.WOOD_DOOR) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.IRON_TRAPDOOR) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.SAPLING) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.BOOK_AND_QUILL) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.ACACIA_FENCE_GATE) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.BIRCH_FENCE_GATE) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.DARK_OAK_FENCE_GATE) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.FENCE_GATE) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.JUNGLE_FENCE_GATE) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		} else if (p.getItemInHand().getType() == Material.SPRUCE_FENCE_GATE) {
			e.setCancelled(true);
			p.sendMessage("§cEste item está banido!");
		}
	}
}