package com.wandy.api.chat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChatMenu implements Listener {
	
	public static ArrayList<String> check = new ArrayList<String>();
	public static List<String> spy1 = new ArrayList<String>();
	public static List<String> spy2 = new ArrayList<String>();

	public static void abrirMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, "§8Gerenciar o Chat");
		
		String ts = "§a";
		String gs = "§a";
		String tps = "§a";
		String cs = "§a";
		String spa1 = "§c";
		String spa2 = "§c";
		
		if (check.contains("GLOBAL")) {
			ts = "§c";
		}
		if (check.contains("LOCAL")) {
			gs = "§c";
		}
		if (check.contains("ALIADO")) {
			tps = "§c";
		}
		if (check.contains("FACCAO")) {
			cs = "§c";
		}
		if (spy1.contains(p.getName())) {
			spa1 = "§a";
		}
		if (spy2.contains(p.getName())) {
			spa2 = "§a";
		}
		
		ItemStack t = new ItemStack(Material.POWERED_RAIL);
		ItemMeta tm = t.getItemMeta();
		tm.setDisplayName(ts + "Global");
		tm.setLore(Arrays.asList(new String[] { "§1", "§7Estado: §7" + ts.replace("§a", "§aAtivado").replace("§c", "§cDesativado"), "§2", "§7Clique §7para " + ts.replace("§a", "desativar.").replace("§c", "ativar.") + "    " }));
		t.setItemMeta(tm);
		inv.setItem(10, t);

		ItemStack g = new ItemStack(Material.DETECTOR_RAIL);
		ItemMeta gm = g.getItemMeta();
		gm.setDisplayName(gs + "Local");
		gm.setLore(Arrays.asList(new String[] { "§1", "§7Estado: §7" + gs.replace("§a", "§aAtivado").replace("§c", "§cDesativado"), "§2", "§7Clique §7para " + gs.replace("§a", "desativar.").replace("§c", "ativar.") + "    " }));
		g.setItemMeta(gm);
		inv.setItem(12, g);

		ItemStack tp = new ItemStack(Material.ACTIVATOR_RAIL);
		ItemMeta tpm = tp.getItemMeta();
		tpm.setDisplayName(tps + "Aliado");
		tpm.setLore(Arrays.asList(new String[] { "§1", "§7Estado: §7" + tps.replace("§a", "§aAtivado").replace("§c", "§cDesativado"), "§7Spy: §7" + spa1.replace("§a", "§aAtivado").replace("§c", "§cDesativado"), "§2", "§fClique esquerdo §7para " + tps.replace("§a", "desativar.").replace("§c", "ativar.") + "    ", "§fClique direito §7para " + spa1.replace("§a", "desativar o spy.").replace("§c", "ativar o spy.") + "    " }));
		tp.setItemMeta(tpm);
		inv.setItem(14, tp);

		ItemStack c = new ItemStack(Material.RAILS);
		ItemMeta cm = c.getItemMeta();
		cm.setDisplayName(cs + "Facção");
		cm.setLore(Arrays.asList(new String[] { "§1", "§7Estado: §7" + cs.replace("§a", "§aAtivado").replace("§c", "§cDesativado"), "§7Spy: §7" + spa2.replace("§a", "§aAtivado").replace("§c", "§cDesativado"), "§2", "§fClique esquerdo §7para " + cs.replace("§a", "desativar.").replace("§c", "ativar.") + "    ", "§fClique direito §7para " + spa2.replace("§a", "desativar o spy.").replace("§c", "ativar o spy.") + "    " }));
		c.setItemMeta(cm);
		inv.setItem(16, c);

		p.openInventory(inv);
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (!e.getInventory().getName().contains("§8Gerenciar o Chat")) {
			return;
		}
		Player p = (Player) e.getWhoClicked();
		e.setCancelled(false);
		if (e.getCurrentItem() == null) {
			return;
		}
		if (e.getSlot() == 10) {
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aGlobal")) {
				e.setCancelled(true);
				check.add("GLOBAL");
				abrirMenu(p);
				return;
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cGlobal")) {
				e.setCancelled(true);
				check.remove("GLOBAL");
				abrirMenu(p);
				return;
			}
		}
		if (e.getSlot() == 12) {
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLocal")) {
				e.setCancelled(true);
				check.add("LOCAL");
				abrirMenu(p);
				return;
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocal")) {
				e.setCancelled(true);
				check.remove("LOCAL");
				abrirMenu(p);
				return;
			}
		}
		if (e.getSlot() == 14) {
			if (e.getClick().equals(ClickType.LEFT)) {
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aAliado")) {
					e.setCancelled(true);
					check.add("ALIADO");
					abrirMenu(p);
					return;
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cAliado")) {
					e.setCancelled(true);
					check.remove("ALIADO");
					abrirMenu(p);
					return;
				}
			}
			if (e.getClick().equals(ClickType.RIGHT)) {
				e.setCancelled(true);
				if (spy1.contains(e.getWhoClicked().getName())) {
					spy1.remove(e.getWhoClicked().getName());
					abrirMenu(p);
					return;
				}
				spy1.add(e.getWhoClicked().getName());
				abrirMenu(p);
			}
		}
		if (e.getSlot() == 16) {
			if (e.getClick().equals(ClickType.LEFT)) {
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aFacção")) {
					e.setCancelled(true);
					check.add("FACCAO");
					abrirMenu(p);
					return;
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cFacção")) {
					e.setCancelled(true);
					check.remove("FACCAO");
					abrirMenu(p);
					return;
				}
			}
			if (e.getClick().equals(ClickType.RIGHT)) {
				e.setCancelled(true);
				if (spy2.contains(e.getWhoClicked().getName())) {
					spy2.remove(e.getWhoClicked().getName());
					abrirMenu(p);
					return;
				}
				spy2.add(e.getWhoClicked().getName());
				abrirMenu(p);
			}
		}
		if (e.getCurrentItem() == null) {
			return;
		}
		e.setCancelled(true);
	}
}
