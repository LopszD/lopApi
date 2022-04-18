package com.wandy.api.especiais.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import com.wandy.api.Main;

public class ImpulsaoListener implements Listener {
	
	public static List<Dispenser> list = new ArrayList<Dispenser>();
	public static String meta = "TNTimpu";

	public static ItemStack getItem() {
		ItemStack i = new ItemStack(Material.TNT);
		i.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta im = i.getItemMeta();
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		im.setDisplayName("§cTNT de Impulsão");
		List<String> l = new ArrayList<String>();
		l.add("§1");
		l.add("§7Ao colocar este bloco no chão,");
		l.add("§7ele se transformará em uma TNT");
		l.add("§7que não destrói outros blocos.");
		l.add("§1");
		l.add("§e * Utilizada na criação de canhões.");
		l.add("§1");
		im.setLore(l);
		i.setItemMeta(im);
		return i;
	}

	public static boolean isTNT(ItemStack i) {
		if (i != null) {
			if (!(i.getType() == Material.AIR)) {
				if (i.getType() == Material.TNT) {
					if (i.hasItemMeta()) {
						if (i.getItemMeta().hasDisplayName()) {
							if (i.getItemMeta().getDisplayName().equals("§cTNT de Impulsão")) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean isTNT(Block b) {
		if (b != null) {
			if (!(b.getType() == Material.AIR)) {
				if (b.getType() == Material.TNT) {
					if (b.hasMetadata(meta)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean taCheio(Player p) {
		if (p.getInventory().getContents().length > 0) {
			int i = 0;
			ItemStack[] contents;
			for (int length = (contents = p.getInventory().getContents()).length, j = 0; j < length; ++j) {
				ItemStack it = contents[j];
				if (it != null && !(it.getType() == Material.AIR)) {
					++i;
				}
			}
			if (i >= 36) {
				return true;
			}
		}
		return false;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoColocar(BlockPlaceEvent e) {
		TNTPrimed tnt;
		if (isTNT(e.getItemInHand())) {
			if (!e.isCancelled()) {
				if (e.canBuild()) {
					Block blk = e.getBlock();
					ArrayList<Block> tntBlocks = new ArrayList<Block>();
					Boolean tntExists = Boolean.valueOf(false);
					if ((blk.getRelative(BlockFace.NORTH).getType() == Material.REDSTONE_TORCH_ON) || (blk.getRelative(BlockFace.UP).getType() == Material.REDSTONE_BLOCK)) {
						tntBlocks.add(blk.getRelative(BlockFace.NORTH));
						tntExists = Boolean.valueOf(true);
					} else if ((blk.getRelative(BlockFace.EAST).getType() == Material.REDSTONE_TORCH_ON) || (blk.getRelative(BlockFace.UP).getType() == Material.REDSTONE_BLOCK)) {
						tntBlocks.add(blk.getRelative(BlockFace.EAST));
						tntExists = Boolean.valueOf(true);
					} else if ((blk.getRelative(BlockFace.SOUTH).getType() == Material.REDSTONE_TORCH_ON) || (blk.getRelative(BlockFace.UP).getType() == Material.REDSTONE_BLOCK)) {
						tntBlocks.add(blk.getRelative(BlockFace.SOUTH));
						tntExists = Boolean.valueOf(true);
					} else if ((blk.getRelative(BlockFace.WEST).getType() == Material.REDSTONE_TORCH_ON) || (blk.getRelative(BlockFace.UP).getType() == Material.REDSTONE_BLOCK)) {
						tntBlocks.add(blk.getRelative(BlockFace.WEST));
						tntExists = Boolean.valueOf(true);
					} else if ((blk.getRelative(BlockFace.DOWN).getType() == Material.REDSTONE_TORCH_ON) || (blk.getRelative(BlockFace.UP).getType() == Material.REDSTONE_BLOCK)) {
						tntBlocks.add(blk.getRelative(BlockFace.DOWN));
						tntExists = Boolean.valueOf(true);
					} else if ((blk.getRelative(BlockFace.UP).getType() == Material.REDSTONE_TORCH_ON) || (blk.getRelative(BlockFace.UP).getType() == Material.REDSTONE_BLOCK)) {
						tntBlocks.add(blk.getRelative(BlockFace.UP));
						tntExists = Boolean.valueOf(true);
					}
					if (tntExists.booleanValue()) {
						e.getPlayer().getInventory().removeItem(new ItemStack[] { getItem() });
						e.setCancelled(true);
						Location location = blk.getLocation();
						location.getWorld().getBlockAt(location).setType(Material.AIR);
						tnt = (TNTPrimed) location.getWorld().spawnEntity(location.add(0.5D, 0.0D, 0.5D), EntityType.PRIMED_TNT);
						tnt.setMetadata(meta, new FixedMetadataValue(Main.getInstance(), meta));
						return;
					}
					e.getBlock().setMetadata(meta, new FixedMetadataValue(Main.getInstance(), meta));
				}
			}
		}
		if (!e.isCancelled()) {
			if (e.canBuild()) {
				if ((e.getBlock().getType() == Material.REDSTONE_BLOCK) || (e.getBlock().getType() == Material.REDSTONE_TORCH_ON)) {
					Block blk = e.getBlock();
					ArrayList<Block> tntBlocks = new ArrayList<Block>();
					Boolean tntExists = Boolean.valueOf(false);
					if (blk.getRelative(BlockFace.NORTH).getType() == Material.TNT) {
						tntBlocks.add(blk.getRelative(BlockFace.NORTH));
						tntExists = Boolean.valueOf(true);
					} else if (blk.getRelative(BlockFace.EAST).getType() == Material.TNT) {
						tntBlocks.add(blk.getRelative(BlockFace.EAST));
						tntExists = Boolean.valueOf(true);
					} else if (blk.getRelative(BlockFace.SOUTH).getType() == Material.TNT) {
						tntBlocks.add(blk.getRelative(BlockFace.SOUTH));
						tntExists = Boolean.valueOf(true);
					} else if (blk.getRelative(BlockFace.WEST).getType() == Material.TNT) {
						tntBlocks.add(blk.getRelative(BlockFace.WEST));
						tntExists = Boolean.valueOf(true);
					} else if (blk.getRelative(BlockFace.DOWN).getType() == Material.TNT) {
						tntBlocks.add(blk.getRelative(BlockFace.DOWN));
						tntExists = Boolean.valueOf(true);
					} else if (blk.getRelative(BlockFace.UP).getType() == Material.TNT) {
						tntBlocks.add(blk.getRelative(BlockFace.UP));
						tntExists = Boolean.valueOf(true);
					}
					if (tntExists.booleanValue()) {
						for (Block tntBlk : tntBlocks) {
							if (isTNT(tntBlk)) {
								Location location = tntBlk.getLocation();
								location.getWorld().getBlockAt(location).setType(Material.AIR);
								TNTPrimed tnt1 = (TNTPrimed) location.getWorld().spawnEntity(location.add(0.5D, 0.0D, 0.5D), EntityType.PRIMED_TNT);
								tnt1.setMetadata(meta, new FixedMetadataValue(Main.getInstance(), meta));
							}
						}
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoQuebrar(BlockBreakEvent e) {
		if (isTNT(e.getBlock())) {
			if (!e.isCancelled()) {
				e.getBlock().setType(Material.AIR);
				e.getBlock().removeMetadata(meta, Main.getInstance());
				e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), getItem());
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoRedstonar(BlockRedstoneEvent e) {
		if (e.getNewCurrent() >= 1) {
			Block blk = e.getBlock();
			ArrayList<Block> tntBlocks = new ArrayList<Block>();
			Boolean tntExists = Boolean.valueOf(false);
			if (blk.getRelative(BlockFace.NORTH).getType() == Material.TNT) {
				tntBlocks.add(blk.getRelative(BlockFace.NORTH));
				tntExists = Boolean.valueOf(true);
			} else if (blk.getRelative(BlockFace.EAST).getType() == Material.TNT) {
				tntBlocks.add(blk.getRelative(BlockFace.EAST));
				tntExists = Boolean.valueOf(true);
			} else if (blk.getRelative(BlockFace.SOUTH).getType() == Material.TNT) {
				tntBlocks.add(blk.getRelative(BlockFace.SOUTH));
				tntExists = Boolean.valueOf(true);
			} else if (blk.getRelative(BlockFace.WEST).getType() == Material.TNT) {
				tntBlocks.add(blk.getRelative(BlockFace.WEST));
				tntExists = Boolean.valueOf(true);
			} else if (blk.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH).getType() == Material.TNT) {
				tntBlocks.add(blk.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH));
				tntExists = Boolean.valueOf(true);
			} else if (blk.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST).getType() == Material.TNT) {
				tntBlocks.add(blk.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST));
				tntExists = Boolean.valueOf(true);
			} else if (blk.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH).getType() == Material.TNT) {
				tntBlocks.add(blk.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH));
				tntExists = Boolean.valueOf(true);
			} else if (blk.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST).getType() == Material.TNT) {
				tntBlocks.add(blk.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST));
				tntExists = Boolean.valueOf(true);
			} else if (blk.getRelative(BlockFace.DOWN).getType() == Material.TNT) {
				tntBlocks.add(blk.getRelative(BlockFace.DOWN));
				tntExists = Boolean.valueOf(true);
			}
			if (tntExists.booleanValue()) {
				for (Block tntBlk : tntBlocks) {
					if (isTNT(tntBlk)) {
						Location location = tntBlk.getLocation();
						location.getWorld().getBlockAt(location).setType(Material.AIR);
						TNTPrimed tnt = (TNTPrimed) location.getWorld().spawnEntity(location.add(0.5D, 0.0D, 0.5D), EntityType.PRIMED_TNT);
						tnt.setMetadata(meta, new FixedMetadataValue(Main.getInstance(), meta));
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public static void aoDispensar(BlockDispenseEvent e) {
		if (!e.isCancelled() && isTNT(e.getItem())) {
			e.setCancelled(true);
			Dispenser dis = (Dispenser) e.getBlock().getState();
			Block block = e.getBlock().getRelative(e.getBlock().getFace(e.getBlock()));
			Location location = block.getLocation();
			if (e.getBlock().getData() == 8) {
				location.setY(location.getY() - 1.0);
			}
			if (e.getBlock().getData() == 9) {
				location.setY(location.getY() + 1.0);
			}
			if (e.getBlock().getData() == 10) {
				location.setZ(location.getZ() - 1.0);
			}
			if (e.getBlock().getData() == 11) {
				location.setZ(location.getZ() + 1.0);
			}
			if (e.getBlock().getData() == 12) {
				location.setX(location.getX() - 1.0);
			}
			if (e.getBlock().getData() == 13) {
				location.setX(location.getX() + 1.0);
			}
			if (!ImpulsaoListener.list.contains(dis)) {
				ImpulsaoListener.list.add(dis);
				TNTPrimed tnt = (TNTPrimed) location.getWorld().spawnEntity(location.add(0.5, 0.0, 0.5), EntityType.PRIMED_TNT);
				tnt.setMetadata(ImpulsaoListener.meta, new FixedMetadataValue(Main.getInstance(), ImpulsaoListener.meta));
				Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getInstance(), new Runnable() {
					@Override
					public void run() {
						if (ImpulsaoListener.list.size() > 0) {
							ImpulsaoListener.list.clear();
						}
						if (dis != null && dis.isPlaced()) {
							dis.getInventory().removeItem(new ItemStack[] { ImpulsaoListener.getItem() });
						}
					}
				}, 10L);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoColocar(PlayerInteractEvent e) {
		if (!e.isCancelled()) {
			if (isTNT(e.getClickedBlock())) {
				if (e.getPlayer().getItemInHand() != null) {
					if (!(e.getPlayer().getItemInHand().getType() == Material.AIR)) {
						if (e.getPlayer().getItemInHand().getType() == Material.FLINT_AND_STEEL) {
							if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
								e.setCancelled(true);
								e.getClickedBlock().setType(Material.AIR);
								e.getClickedBlock().removeMetadata(meta, Main.getInstance());
								TNTPrimed tnt = (TNTPrimed) e.getClickedBlock().getWorld().spawnEntity(e.getClickedBlock().getLocation().add(0.5D, 0.0D, 0.5D), EntityType.PRIMED_TNT);
								tnt.setMetadata(meta, new FixedMetadataValue(Main.getInstance(), meta));
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public static void aoPistonar1(BlockPistonExtendEvent e) {
		for (Block b : e.getBlocks()) {
			if (isTNT(b)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public static void aoPistonar2(BlockPistonRetractEvent e) {
		for (Block b : e.getBlocks()) {
			if (isTNT(b)) {
				e.setCancelled(true);
			}
		}
	}
}
