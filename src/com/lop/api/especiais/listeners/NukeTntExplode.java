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
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.massivecore.ps.PS;
import com.wandy.api.Main;

public class NukeTntExplode implements Listener {

	public String meta = "NUKE";

	public boolean isTNT(ItemStack i) {
		if ((i != null) && (!(i.getType() == Material.AIR)) && (i.getType() == Material.TNT) && (i.hasItemMeta()) && (i.getItemMeta().hasDisplayName()) && (i.getItemMeta().getDisplayName().equals("§cTNT Nuke"))) {
			return true;
		}
		return false;
	}

	public boolean isTNT(Block b) {
		if ((b != null) && (!(b.getType() == Material.AIR)) && (b.getType() == Material.TNT) && (b.hasMetadata(this.meta))) {
			return true;
		}
		return false;
	}

	public boolean taCheio(Player p) {
		if (p.getInventory().getContents().length > 0) {
			int i = 0;
			ItemStack[] arrayOfItemStack;
			int j = (arrayOfItemStack = p.getInventory().getContents()).length;
			for (int ia = 0; i < j; i++) {
				ItemStack it = arrayOfItemStack[ia];
				if ((it != null) && (!(it.getType() == Material.AIR))) {
					i++;
				}
			}
			if (i >= 36) {
				return true;
			}
		}
		return false;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void aoColocar(BlockPlaceEvent e) {
		Location location;
		if ((isTNT(e.getItemInHand())) && (!e.isCancelled()) && (e.canBuild())) {
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
				location = blk.getLocation();
				location.getWorld().getBlockAt(location).setType(Material.AIR);
				TNTPrimed tnt = (TNTPrimed) location.getWorld().spawnEntity(location.add(0.5D, 0.0D, 0.5D), EntityType.PRIMED_TNT);
				tnt.setMetadata(this.meta, new FixedMetadataValue(Main.getInstance(), this.meta));
				return;
			}
			e.getBlock().setMetadata(this.meta, new FixedMetadataValue(Main.getInstance(), this.meta));
		}
		if ((!e.isCancelled()) && (e.canBuild()) && ((e.getBlock().getType() == Material.REDSTONE_BLOCK) || (e.getBlock().getType() == Material.REDSTONE_TORCH_ON))) {
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
						final Location location1 = tntBlk.getLocation();
						location1.getWorld().getBlockAt(location1).setType(Material.AIR);
						TNTPrimed tnt = (TNTPrimed) location1.getWorld().spawnEntity(location1.add(0.5D, 0.0D, 0.5D), EntityType.PRIMED_TNT);
						tnt.setMetadata(this.meta, new FixedMetadataValue(Main.getInstance(), this.meta));
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void aoQuebrar(BlockBreakEvent e) {
		if ((isTNT(e.getBlock())) && (!e.isCancelled())) {
			e.getBlock().setType(Material.AIR);
			e.getBlock().removeMetadata(this.meta, Main.getInstance());
			e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), getItem());
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void aoRedstonar(BlockRedstoneEvent e) {
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
						tnt.setMetadata(this.meta, new FixedMetadataValue(Main.getInstance(), this.meta));
					}
				}
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Dispenser> list = new ArrayList();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void aoDispensar(BlockDispenseEvent e) {
		if ((!e.isCancelled()) && (isTNT(e.getItem()))) {
			e.setCancelled(true);
			final Dispenser dis = (Dispenser) e.getBlock().getState();
			Block block = e.getBlock().getRelative(e.getBlock().getFace(e.getBlock()));
			Location location = block.getLocation();
			if (e.getBlock().getData() == 8) {
				location.setY(location.getY() - 1.0D);
			}
			if (e.getBlock().getData() == 9) {
				location.setY(location.getY() + 1.0D);
			}
			if (e.getBlock().getData() == 10) {
				location.setZ(location.getZ() - 1.0D);
			}
			if (e.getBlock().getData() == 11) {
				location.setZ(location.getZ() + 1.0D);
			}
			if (e.getBlock().getData() == 12) {
				location.setX(location.getX() - 1.0D);
			}
			if (e.getBlock().getData() == 13) {
				location.setX(location.getX() + 1.0D);
			}
			if (!this.list.contains(dis)) {
				this.list.add(dis);
				TNTPrimed tnt = (TNTPrimed) location.getWorld().spawnEntity(location.add(0.5D, 0.0D, 0.5D), EntityType.PRIMED_TNT);
				tnt.setMetadata(this.meta, new FixedMetadataValue(Main.getInstance(), this.meta));
				Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getInstance(), new Runnable() {
					public void run() {
						if (NukeTntExplode.this.list.size() > 0) {
							NukeTntExplode.this.list.clear();
						}
						if ((dis != null) && (dis.isPlaced())) {
							dis.getInventory().removeItem(new ItemStack[] { NukeTntExplode.this.getItem() });
						}
					}
				}, 10L);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void aoColocar(PlayerInteractEvent e) {
		if ((!e.isCancelled()) && (isTNT(e.getClickedBlock())) && (e.getPlayer().getItemInHand() != null) && (!(e.getPlayer().getItemInHand().getType() == Material.AIR)) && (e.getPlayer().getItemInHand().getType() == Material.FLINT_AND_STEEL) && (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			e.setCancelled(true);
			e.getClickedBlock().setType(Material.AIR);
			e.getClickedBlock().removeMetadata(this.meta, Main.getInstance());
			TNTPrimed tnt = (TNTPrimed) e.getClickedBlock().getWorld().spawnEntity(e.getClickedBlock().getLocation().add(0.5D, 0.0D, 0.5D), EntityType.PRIMED_TNT);
			tnt.setMetadata(this.meta, new FixedMetadataValue(Main.getInstance(), this.meta));
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPrime(ExplosionPrimeEvent e) {
		if (e.getEntity().getType() == EntityType.PRIMED_TNT) {
			if (e.getEntity().hasMetadata(this.meta)) {
				e.setRadius(20.0F);
			} else {
				e.setRadius(e.getRadius() + 4.0F);
			}
		}
	}

	@EventHandler
	public void aoExplodir(EntityExplodeEvent e) {
		if (e.getEntity().getType() == EntityType.PRIMED_TNT) {
			for (Block b : e.blockList()) {
				if (b.getType() == Material.TNT) {
					b.setType(Material.AIR);
					if (!b.hasMetadata(this.meta)) {
						b.getLocation().getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.TNT));
					} else {
						b.getLocation().getWorld().dropItemNaturally(b.getLocation(), getItem());
					}
				}
			}
		}
	}

	@EventHandler
	public void onExplodir(EntityExplodeEvent e) {
		if ((!e.isCancelled()) && (e.getEntity().getType() == EntityType.PRIMED_TNT) && (e.getEntity().hasMetadata(this.meta))) {
			Entity entidade = e.getEntity();
			Location loc = entidade.getLocation();
			for (int x = -6; x <= 6; x++) {
				for (int y = -6; y <= 6; y++) {
					for (int z = -6; z <= 6; z++) {
						Location targetLoc = new Location(entidade.getWorld(), loc.getX() + x, loc.getY() + y, loc.getZ() + z);
						if (loc.distance(targetLoc) <= 6.0D) {
							Block b = targetLoc.getBlock();
							Faction f = BoardColl.get().getFactionAt(PS.valueOf(b.getLocation()));
							if ((FactionColl.get().getSafezone().equals(f)) || (FactionColl.get().getWarzone().equals(f))) {
								return;
							}
							if (b.getLocation().getY() <= 1.0D) {
								return;
							}
							if (b.getType() == Material.BEDROCK) {
								b.breakNaturally();
							}
							if (b.getType() == Material.ENDER_STONE) {
								b.breakNaturally();
							}
							if (b.getType() == Material.OBSIDIAN) {
								b.breakNaturally();
							}
						}
					}
				}
			}
		}
	}

	private ItemStack getItem() {
		return getNuke(1);
	}

	public static ItemStack getNuke(int amount) {
		ItemStack i = new ItemStack(Material.TNT);
		i.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta im = i.getItemMeta();
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		im.setDisplayName("§cTNT Nuke");
		List<String> l = new ArrayList<String>();
		l.add("");
		l.add("§7Ao colocar este bloco no chão,");
		l.add("§7ele se transformará em uma TNT");
		l.add("§7que destroi todos os tipos de blocos");
		l.add("§7em um raio de 10x10.");
		l.add("");
		l.add("§e * Utilizada na criação de canhões.");
		l.add("");
		im.setLore(l);
		i.setItemMeta(im);
		i.setAmount(amount);
		return i;
	}
}
