package com.wandy.api.arenas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ArenaMenu implements Listener {
	
	public static HashMap<String, String> areia = new HashMap<String, String>();
	public static HashMap<String, String> pedra = new HashMap<String, String>();
	public static HashMap<String, String> nether = new HashMap<String, String>();

	@SuppressWarnings("unused")
	public static void abrirMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, "§8Selecione uma arena:");
		
		//
		// ARENA 1
		//
		
		List<String> l = new ArrayList<String>();
		l.add("§1");
		l.add("§8Batalhando: §7" + getDentro("Areia") + "/50");
		String cor = "§a";
		String ch = "§aClique para entrar!";
		if (!p.hasPermission("wandy.equipe")) {
			if (getDentro("Areia").intValue() >= 50) {
				ch = "§cArena lotada.";
				cor = "§c";
			}
		}
		if (checarDentro(p.getName(), "Areia")) {
			ch = "§cVocê já está aqui.";
			cor = "§c";
		}
		l.add(ch);
		ItemStack a = itemCreate(cor + "Miragem", Material.SANDSTONE, 1, (short) 1, l);

		//
		// ARENA 2
		//
		
		List<String> l1 = new ArrayList<String>();
		l1.add("§1");
		l1.add("§8Batalhando: §7" + getDentro("Pedra") + "/50");
		String cor1 = "§a";
		String ch1 = "§aClique para entrar!";
		if (!p.hasPermission("wandy.equipe")) {
			if (getDentro("Pedra").intValue() >= 50) {
				ch1 = "§cArena lotada.";
				cor1 = "§c";
			}
		}
		if (checarDentro(p.getName(), "Pedra")) {
			ch1 = "§cVocê já está aqui.";
			cor1 = "§c";
		}
		l1.add(ch1);
		ItemStack a1 = itemCreate(cor1 + "Acampamento", Material.DIRT, 1, (short) 2, l1);

		//
		// ARENA 3
		//
		
		List<String> l2 = new ArrayList<String>();
		l2.add("§1");
		l2.add("§8Batalhando: §7" + getDentro("Nether") + "/50");
		String cor2 = "§a";
		String ch2 = "§aClique para entrar!";
		if (!p.hasPermission("wandy.equipe")) {
			if (getDentro("Nether").intValue() >= 50) {
				ch2 = "§cArena lotada.";
				cor2 = "§c";
			}
		}
		if (checarDentro(p.getName(), "Nether")) {
			ch2 = "§cVocê já está aqui.";
			cor2 = "§c";
		}
		l2.add(ch2);
		ItemStack a2 = itemCreate(cor2 + "Sky", Material.BEACON, 1, (short) 1, l2);

		//inv.setItem(10, a);
		inv.setItem(13, a1);
		//inv.setItem(16, a2);

		p.openInventory(inv);
	}

	@SuppressWarnings("unlikely-arg-type")
	public static ItemStack itemCreate(String nome, Material m, int qnt, short damage, List<String> lore) {
		ItemStack a = new ItemStack(m, qnt, damage);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am = a.getItemMeta();
		am.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		am.setDisplayName(nome);
		if (!lore.equals("")) {
			am.setLore(lore);
		}
		a.setItemMeta(am);
		return a;
	}

	@SuppressWarnings("unlikely-arg-type")
	public static ItemStack itemCreate2(String nome, Material m, int qnt, short damage, List<String> lore) {
		ItemStack a = new ItemStack(m, qnt, damage);
		ItemMeta am = a.getItemMeta();
		am.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		am.setDisplayName(nome);
		if (!lore.equals("")) {
			am.setLore(lore);
		}
		a.setItemMeta(am);
		return a;
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (!e.getInventory().getName().equalsIgnoreCase("§8Selecione uma arena:")) {
			return;
		}
		Player p = (Player) e.getWhoClicked();
		e.setCancelled(false);
		if (e.getSlot() == 10) {
			e.setCancelled(true);
			p.closeInventory();
			if (!p.hasPermission("wandy.equipe")) {
				if (getDentro("Areia").intValue() >= 50) {
					p.sendMessage("§cEssa arena está lotada.");
					return;
				}
			}
			if (checarDentro(p.getName(), "Areia")) {
				p.sendMessage("§cVocê já está aqui.");
				return;
			}
			mandarArenaA(p);
		}
		if (e.getSlot() == 13) {
			e.setCancelled(true);
			p.closeInventory();
			if (!p.hasPermission("wandy.equipe")) {
				if (getDentro("Pedra").intValue() >= 50) {
					p.sendMessage("§cEssa arena está lotada.");
					return;
				}
			}
			if (checarDentro(p.getName(), "Pedra")) {
				p.sendMessage("§cVocê já está aqui.");
				return;
			}
			mandarArenaP(p);
		}
		if (e.getSlot() == 16) {
			e.setCancelled(true);
			p.closeInventory();
			if (!p.hasPermission("wandy.equipe")) {
				if (getDentro("Nether").intValue() >= 50) {
					p.sendMessage("§cEssa arena está lotada.");
					return;
				}
			}
			if (checarDentro(p.getName(), "Nether")) {
				p.sendMessage("§cVocê já está aqui.");
				return;
			}
			mandarArenaN(p);
		}
		if (e.getSlot() == 16) {
			e.setCancelled(true);
		}
		if (e.getCurrentItem() == null) {
			return;
		}
		e.setCancelled(true);
	}

	public static void mandarArenaA(Player p) {
		if (pedra.containsKey(p.getName())) {
			pedra.remove(p.getName());
		}
		if (nether.containsKey(p.getName())) {
			nether.remove(p.getName());
		}
		areia.put(p.getName(), p.getName());
		Location loc1 = new Location(Bukkit.getWorld("world_arenas"), 3005.5D, 5.0D, 2998.5D);
		Random r = new Random();
		int rn = r.nextInt(4);
		if (rn == 0) {
			Location loc = loc1.clone().add(0.0D, 2.0D, 15.0D);
			p.teleport(loc);
		}
		if (rn == 1) {
			Location loc = loc1.clone().subtract(15.0D, 0.0D, 0.0D).add(0.0D, 2.0D, 0.0D);
			p.teleport(loc);
		}
		if (rn == 2) {
			Location loc = loc1.clone().subtract(0.0D, 0.0D, 15.0D).add(0.0D, 2.0D, 0.0D);
			p.teleport(loc);
		}
		if (rn == 3) {
			Location loc = loc1.clone().add(15.0D, 2.0D, 0.0D);
			p.teleport(loc);
		}
		if (rn == 4) {
			Location loc = loc1.clone().add(0.0D, 2.0D, 15.0D);
			p.teleport(loc);
		}
		p.sendMessage("§aTeleportado para a arena Miragem!");
	}

	public static void mandarArenaP(Player p) {
		if (areia.containsKey(p.getName())) {
			areia.remove(p.getName());
		}
		if (nether.containsKey(p.getName())) {
			nether.remove(p.getName());
		}
		pedra.put(p.getName(), p.getName());
		Location loc1 = new Location(Bukkit.getWorld("world_arenas"), -725.8D, 41.0D, -218.8D);
		Random r = new Random();
		int rn = r.nextInt(4);
		if (rn == 0) {
			Location loc = loc1.clone().add(0.0D, 2.0D, 20.0D);
			p.teleport(loc);
		}
		if (rn == 1) {
			Location loc = loc1.clone().subtract(20.0D, 0.0D, 0.0D).add(0.0D, 2.0D, 0.0D);
			p.teleport(loc);
		}
		if (rn == 2) {
			Location loc = loc1.clone().subtract(0.0D, 0.0D, 20.0D).add(0.0D, 2.0D, 0.0D);
			p.teleport(loc);
		}
		if (rn == 3) {
			Location loc = loc1.clone().add(20.0D, 2.0D, 0.0D);
			p.teleport(loc);
		}
		if (rn == 4) {
			Location loc = loc1.clone().add(0.0D, 2.0D, 20.0D);
			p.teleport(loc);
		}
		p.sendMessage("§aTeleportado para a arena Acampamento!");
	}

	public static void mandarArenaN(Player p) {
		if (areia.containsKey(p.getName())) {
			areia.remove(p.getName());
		}
		if (pedra.containsKey(p.getName())) {
			pedra.remove(p.getName());
		}
		if (nether.containsKey(p.getName())) {
			nether.remove(p.getName());
		}
		nether.put(p.getName(), p.getName());
		Location loc1 = new Location(Bukkit.getWorld("world_arenas"), 2582.5D, 23.0D, 3016.5D);
		Random r = new Random();
		int rn = r.nextInt(4);
		if (rn == 0) {
			Location loc = loc1.clone().add(0.0D, 2.0D, 2.0D);
			p.teleport(loc);
		}
		if (rn == 1) {
			Location loc = loc1.clone().subtract(2.0D, 0.0D, 0.0D).add(0.0D, 2.0D, 0.0D);
			p.teleport(loc);
		}
		if (rn == 2) {
			Location loc = loc1.clone().subtract(0.0D, 0.0D, 2.0D).add(0.0D, 2.0D, 0.0D);
			p.teleport(loc);
		}
		if (rn == 3) {
			Location loc = loc1.clone().add(2.0D, 2.0D, 0.0D);
			p.teleport(loc);
		}
		if (rn == 4) {
			Location loc = loc1.clone().add(0.0D, 2.0D, 2.0D);
			p.teleport(loc);
		}
		p.sendMessage("§aTeleportado para a arena Sky!");
	}

	public static boolean checarDentro(String nome, String ar) {
		if (ar.equals("Areia")) {
			if (areia.containsKey(nome)) {
				return true;
			}
		}
		if (ar.equals("Pedra")) {
			if (pedra.containsKey(nome)) {
				return true;
			}
		}
		if (ar.equals("Nether")) {
			if (nether.containsKey(nome)) {
				return true;
			}
		}
		return false;
	}

	public static Integer getDentro(String ar) {
		if (ar.equals("Areia")) {
			int i = 0;
			for (String nomes : areia.keySet()) {
				areia.get(nomes);
				i++;
			}
			return Integer.valueOf(i);
		}
		if (ar.equals("Pedra")) {
			int i = 0;
			for (String nomes : pedra.keySet()) {
				pedra.get(nomes);
				i++;
			}
			return Integer.valueOf(i);
		}
		if (ar.equals("Nether")) {
			int i = 0;
			for (String nomes : nether.keySet()) {
				nether.get(nomes);
				i++;
			}
			return Integer.valueOf(i);
		}
		return Integer.valueOf(0);
	}
}
