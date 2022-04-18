package com.wandy.api.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FindChestListener implements Listener {
	
	public static void abrirMenu(Player p, Material m, Integer qnt) {
		Inventory inv = Bukkit.createInventory((InventoryHolder) null, 54, "§8Baús encontrados");
		int i = 10;
		int n = 1;
		for (World world : Bukkit.getWorlds()) {
			Chunk[] loadedChunks;
			for (int length = (loadedChunks = world.getLoadedChunks()).length, j = 0; j < length; ++j) {
				Chunk e = loadedChunks[j];
				BlockState[] tileEntities;
				for (int length2 = (tileEntities = e.getTileEntities()).length, k = 0; k < length2; ++k) {
					BlockState en = tileEntities[k];
					if (en.getBlock().getType().equals(Material.CHEST)) {
						Chest c = (Chest) en.getBlock().getState();
						if (c.getInventory().contains(m)) {
							if (i == 17) {
								i = 19;
							}
							if (i == 26) {
								i = 28;
							}
							if (i == 35) {
								i = 37;
							}
							if (i <= 43) {
								int its = 0;
								ItemStack[] contents;
								for (int length3 = (contents = c.getInventory().getContents()).length, l = 0; l < length3; ++l) {
									ItemStack itsn = contents[l];
									if (itsn != null && itsn.getType().equals(m)) {
										int amount = itsn.getAmount();
										its += amount;
									}
								}
								if (its >= qnt) {
									ItemStack a = new ItemStack(Material.CHEST);
									ItemMeta am = a.getItemMeta();
									am.setDisplayName("§e#" + n);
									List<String> lore = new ArrayList<String>();
									lore.add("§2");
									lore.add("§fClique esquerdo §7para teleportar.");
									lore.add("§fClique direito §7para mais informações.");
									lore.add("§3");
									lore.add("§7X: §e" + en.getLocation().getX() + "º");
									lore.add("§7Y: §e" + en.getLocation().getY() + "º");
									lore.add("§7Z: §e" + en.getLocation().getZ() + "º");
									lore.add("§7Mundo: §e" + en.getWorld().getName());
									lore.add("§7Itens: §e" + its);
									lore.add("§4");
									am.setLore((List<String>) lore);
									a.setItemMeta(am);
									inv.setItem(i, a);
									++n;
									++i;
								}
							}
						}
					}
				}
			}
		}
		p.openInventory(inv);
	}

	@EventHandler
	public void onInventoryClickEvent1(InventoryClickEvent e) {
		if (!e.getInventory().getName().contains("§8Baú #")) {
			return;
		}
		e.setCancelled(false);
		if (e.getCurrentItem() == null) {
			return;
		}
		e.setCancelled(true);
	}

	@EventHandler
	public void onInventoryClickEvent2(InventoryClickEvent e) {
		if (!e.getInventory().getName().contains("§8Opções - Baú #")) {
			return;
		}
		e.setCancelled(false);
		if (e.getCurrentItem() == null) {
			return;
		}
		if (e.getSlot() == 15) {
			ItemStack a = e.getInventory().getItem(13);
			ItemMeta am = a.getItemMeta();
			double x = Double.valueOf(((String) am.getLore().get(2)).replace("§7X: §e", "").replace("º", "")).doubleValue();
			double y = Double.valueOf(((String) am.getLore().get(3)).replace("§7Y: §e", "").replace("º", "")).doubleValue();
			double z = Double.valueOf(((String) am.getLore().get(4)).replace("§7Z: §e", "").replace("º", "")).doubleValue();
			String world = ((String) am.getLore().get(5)).replace("§7Mundo: §e", "");
			Location loc = new Location(Bukkit.getWorld(world), x, y, z);
			Chest c = (Chest) loc.getBlock().getState();
			c.getInventory().clear();
			loc.getBlock().setType(Material.AIR);
			e.getWhoClicked().closeInventory();
			e.getWhoClicked().sendMessage("§aBaú deletado com sucesso.");
		}
		if (e.getSlot() == 11) {
			ItemStack a = e.getInventory().getItem(13);
			ItemMeta am = a.getItemMeta();
			String nome = ((String) am.getLore().get(1)).replace("§7Nome: §e", "");
			double x = Double.valueOf(((String) am.getLore().get(2)).replace("§7X: §e", "").replace("º", "")).doubleValue();
			double y = Double.valueOf(((String) am.getLore().get(3)).replace("§7Y: §e", "").replace("º", "")).doubleValue();
			double z = Double.valueOf(((String) am.getLore().get(4)).replace("§7Z: §e", "").replace("º", "")).doubleValue();
			String world = ((String) am.getLore().get(5)).replace("§7Mundo: §e", "");
			Location loc = new Location(Bukkit.getWorld(world), x, y, z);
			Chest c = (Chest) loc.getBlock().getState();
			Inventory inv = Bukkit.createInventory(null, 54, "§8" + nome);
			inv.setContents(c.getInventory().getContents());
			Player p = (Player) e.getWhoClicked();
			p.playSound(p.getLocation(), Sound.CHEST_OPEN, 1.0F, 1.0F);
			e.getWhoClicked().openInventory(inv);
		}
		e.setCancelled(true);
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (!e.getInventory().getName().contains("§8Baús encontrados")) {
			return;
		}
		e.setCancelled(false);
		if (e.getCurrentItem() == null) {
			return;
		}
		if (e.getSlot() > 9 && e.getSlot() < 44) {
			ItemStack a = e.getCurrentItem();
			ItemMeta am = a.getItemMeta();
			double x = Double.valueOf(am.getLore().get(4).replace("§7X: §e", "").replace("º", ""));
			double y = Double.valueOf(am.getLore().get(5).replace("§7Y: §e", "").replace("º", ""));
			double z = Double.valueOf(am.getLore().get(6).replace("§7Z: §e", "").replace("º", ""));
			String world = am.getLore().get(7).replace("§7Mundo: §e", "");
			Location loc = new Location(Bukkit.getWorld(world), x, y, z).add(0.0, 1.0, 0.0);
			if (e.getClick().equals(ClickType.RIGHT)) {
				Inventory inv = Bukkit.createInventory((InventoryHolder) null, 27, "§8Opções - Baú " + am.getDisplayName().replace("§e", ""));
				List<String> lore = new ArrayList<String>();
				ItemStack d = new ItemStack(Material.BARRIER);
				ItemMeta dm = d.getItemMeta();
				dm.setDisplayName("§cDeletar");
				lore.clear();
				lore.add("§7Clique para deletar este baú.");
				dm.setLore(lore);
				d.setItemMeta(dm);
				inv.setItem(15, d);
				ItemStack f = new ItemStack(Material.PAPER);
				ItemMeta fm = f.getItemMeta();
				fm.setDisplayName("§eInformações");
				lore.clear();
				lore.add("§3");
				lore.add("§7Nome: §eBaús " + am.getDisplayName().replace("§e", ""));
				lore.add("§7X: §e" + x + "º");
				lore.add("§7Y: §e" + y + "º");
				lore.add("§7Z: §e" + z + "º");
				lore.add("§7Mundo: §e" + world);
				lore.add("§4");
				fm.setLore(lore);
				f.setItemMeta(fm);
				inv.setItem(13, f);
				ItemStack g = new ItemStack(Material.EYE_OF_ENDER);
				ItemMeta gm = g.getItemMeta();
				gm.setDisplayName("§aConteúdo");
				lore.clear();
				lore.add("§7Clique para ver o conteúdo.");
				gm.setLore(lore);
				g.setItemMeta(gm);
				inv.setItem(11, g);
				Player p = (Player) e.getWhoClicked();
				p.openInventory(inv);
			}
			if (e.getClick().equals(ClickType.LEFT)) {
				e.getWhoClicked().teleport(loc);
				e.getWhoClicked().sendMessage("§aTeleportado!");
			}
		}
		if (e.getCurrentItem() == null) {
			return;
		}
		e.setCancelled(true);
	}
}
