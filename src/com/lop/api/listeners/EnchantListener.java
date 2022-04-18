package com.wandy.api.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class EnchantListener implements Listener {

	public static void aoEncantar(EnchantItemEvent e) {
		int rnd = 0;
		int rndn = 0;
		Random r = new Random();
		rnd = r.nextInt(100);
		rndn = r.nextInt(100);
		boolean pod = false;
		boolean jorj = false;
		if ((e.getEnchanter().hasPermission("wandy.vip")) || (e.getEnchanter().hasPermission("wandy.youtuber")) || (e.getEnchanter().hasPermission("wandy.equipe"))) {
			if (rnd >= 80) {
				pod = true;
			}
		}
		if (e.getExpLevelCost() >= 30) {
			if ((e.getEnchanter().hasPermission("wandy.vip")) || (e.getEnchanter().hasPermission("wandy.youtuber")) || (e.getEnchanter().hasPermission("wandy.equipe"))) {
				if (rndn >= 80) {
					jorj = true;
					return;
				}
				return;
			}
		}
		if (jorj) {
			int ab = 0;
			int ac = 0;
			ab = 0;
			String ty = new StringBuilder().append(e.getItem().getType()).toString();
			if (ty.contains("SWORD")) {
				ItemStack i = e.getItem();
				if ((i.hasItemMeta()) && (i.getItemMeta().hasLore()) && (i.getItemMeta().getLore().contains("§7Experiência I"))) {
					ac++;
				}
				if (ac == 0) {
					ItemMeta am = i.getItemMeta();
					List<String> l = am.getLore();
					if (l == null) {
						l = new ArrayList<String>();
					}
					l.add("§7Experiência I");
					am.setLore(l);
					i.setItemMeta(am);
				}
			}
			if (ty.contains("SPADE")) {
				ItemStack i = e.getItem();
				if ((i.hasItemMeta()) && (i.getItemMeta().hasLore()) && (i.getItemMeta().getLore().contains("§7Experiência I"))) {
					ac++;
				}
				if (ac == 0) {
					ItemMeta am = i.getItemMeta();
					List<String> l = am.getLore();
					if (l == null) {
						l = new ArrayList<String>();
					}
					l.add("§7Experiência I");
					am.setLore(l);
					i.setItemMeta(am);
				}
			}
			if (ty.contains("AXE")) {
				ItemStack i = e.getItem();
				if ((i.hasItemMeta()) && (i.getItemMeta().hasLore())
						&& (i.getItemMeta().getLore().contains("§7Experiência I"))) {
					ac++;
				}
				if (ac == 0) {
					ItemMeta am = i.getItemMeta();
					List<String> l = am.getLore();
					if (l == null) {
						l = new ArrayList<String>();
					}
					l.add("§7Experiência I");
					am.setLore(l);
					i.setItemMeta(am);
				}
			}
			if (ty.contains("PICKAXE")) {
				ItemStack i = e.getItem();
				if ((i.hasItemMeta()) && (i.getItemMeta().hasLore())) {
					if (i.getItemMeta().getLore().contains("§7Experiência I")) {
						ac++;
					}
					if (i.getItemMeta().getLore().contains("§7Derretimento I")) {
						ab++;
					}
				}
				if (ac == 0) {
					ItemMeta am = i.getItemMeta();
					List<String> l = am.getLore();
					if (l == null) {
						l = new ArrayList<String>();
					}
					l.add("§7Experiência I");
					am.setLore(l);
					i.setItemMeta(am);
				}
				if (ab == 0) {
					ItemMeta am = i.getItemMeta();
					List<String> l = am.getLore();
					if (l == null) {
						l = new ArrayList<String>();
					}
					l.add("§7Derretimento I");
					am.setLore(l);
					i.setItemMeta(am);
				}
			}
		}
		if (pod) {
			for (Enchantment en : e.getEnchantsToAdd().keySet()) {
				if (en == Enchantment.DURABILITY) {
					int max = 5;
					int current = e.getEnchantsToAdd().get(en);
					int add = r.nextInt(4);
					int todo = current + add;
					if (todo > max) {
						todo = max;
					}
					if (current != todo) {
						e.getEnchantsToAdd().replace(en, Integer.valueOf(todo));
						if (add != 0) {
							e.getEnchanter().sendMessage("§aO encantamento '" + translate(en.getName()) + "' foi upado de " + current + " para " + todo + "!");
						}
					}
				}
				if (en == Enchantment.LOOT_BONUS_BLOCKS) {
					int max = 3;
					int current = ((Integer) e.getEnchantsToAdd().get(en)).intValue();
					int add = r.nextInt(2);
					int todo = current + add;
					if (todo > max) {
						todo = max;
					}
					e.getEnchantsToAdd().replace(en, Integer.valueOf(todo));
					if (current != todo) {
						e.getEnchantsToAdd().replace(en, Integer.valueOf(todo));
						if (add != 0) {
							e.getEnchanter().sendMessage("§aO encantamento '" + translate(en.getName()) + "' foi upado de " + current + " para " + todo + "!");
						}
					}
				}
				if (en == Enchantment.DIG_SPEED) {
					int max = 5;
					int current = ((Integer) e.getEnchantsToAdd().get(en)).intValue();
					int add = r.nextInt(4);
					int todo = current + add;
					if (todo > max) {
						todo = max;
					}
					e.getEnchantsToAdd().replace(en, Integer.valueOf(todo));
					if (current != todo) {
						e.getEnchantsToAdd().replace(en, Integer.valueOf(todo));
						if (add != 0) {
							e.getEnchanter().sendMessage("§aO encantamento '" + translate(en.getName()) + "' foi upado de " + current + " para " + todo + "!");
						}
					}
				}
				if (en == Enchantment.LOOT_BONUS_MOBS) {
					int max = 3;
					int current = ((Integer) e.getEnchantsToAdd().get(en)).intValue();
					int add = r.nextInt(2);
					int todo = current + add;
					if (todo > max) {
						todo = max;
					}
					e.getEnchantsToAdd().replace(en, Integer.valueOf(todo));
					if (current != todo) {
						e.getEnchantsToAdd().replace(en, Integer.valueOf(todo));
						if (add != 0) {
							e.getEnchanter().sendMessage("§aO encantamento '" + translate(en.getName()) + "' foi upado de " + current + " para " + todo + "!");
						}
					}
				}
				if (en == Enchantment.DAMAGE_ALL) {
					int max = 6;
					int current = ((Integer) e.getEnchantsToAdd().get(en)).intValue();
					int add = r.nextInt(5);
					int todo = current + add;
					if (todo > max) {
						todo = max;
					}
					e.getEnchantsToAdd().replace(en, Integer.valueOf(todo));
					if (current != todo) {
						e.getEnchantsToAdd().replace(en, Integer.valueOf(todo));
						if (add != 0) {
							e.getEnchanter().sendMessage("§aO encantamento '" + translate(en.getName()) + "' foi upado de " + current + " para " + todo + "!");
						}
					}
				}
				if (en == Enchantment.PROTECTION_ENVIRONMENTAL) {
					int max = 5;
					int current = ((Integer) e.getEnchantsToAdd().get(en)).intValue();
					int add = r.nextInt(4);
					int todo = current + add;
					if (todo > max) {
						todo = max;
					}
					e.getEnchantsToAdd().replace(en, Integer.valueOf(todo));
					if (current != todo) {
						e.getEnchantsToAdd().replace(en, Integer.valueOf(todo));
						if (add != 0) {
							e.getEnchanter().sendMessage("§aO encantamento '" + translate(en.getName()) + "' foi upado de " + current + " para " + todo + "!");
						}
					}
				}
				if (en == Enchantment.FIRE_ASPECT) {
					int max = 3;
					int current = e.getEnchantsToAdd().get(en);
					int add = r.nextInt(2);
					int todo = current + add;
					if (todo > max) {
						todo = max;
					}
					e.getEnchantsToAdd().replace(en, todo);
					if (current == todo) {
						continue;
					}
					e.getEnchantsToAdd().replace(en, todo);
					if (add == 0) {
						continue;
					}
					e.getEnchanter().sendMessage("§aO encantamento '" + translate(en.getName()) + "' foi upado de " + current + " para " + todo + "!");
				}
			}
		}
		if (e.getEnchanter().hasPermission("wandy.vip")) {
			@SuppressWarnings("deprecation")
			String cor = "§" + PermissionsEx.getUser(e.getEnchanter().getName()).getGroups()[0].getPrefix().charAt(1);
			List<String> lore = new ArrayList<String>();
			if (e.getItem().hasItemMeta()) {
				if (e.getItem().getItemMeta().hasLore()) {
					List<String> lis = new ArrayList<String>();
					for (String ni : e.getItem().getItemMeta().getLore()) {
						if (ni.contains("§1§3§1")) {
							lis.add(ni);
						}
						if (ni.contains("§1§3§2")) {
							lis.add(ni);
						}
						if (ni.contains("§1§3§3")) {
							lis.add(ni);
						}
					}
					ItemMeta am = e.getItem().getItemMeta();
					lore = am.getLore();
					for (String la : lis) {
						lore.remove(la);
					}
				}
			}
			ItemMeta am = e.getItem().getItemMeta();
			lore.add("§1§3§3");
			lore.add("§1§3§1" + cor + "Encantado por:");
			lore.add("§1§3§2" + cor + e.getEnchanter().getName());
			am.setLore(lore);
			e.getItem().setItemMeta(am);
		}
	}

	public static String translate(String ench) {
		ench = ench.toLowerCase();
		if (ench.contains("_")) {
			ench = ench.replace("_", " ");
		}
		if (ench == "durability") {
			ench = "durabilidade";
		}
		if (ench == "loot bonus blocks") {
			ench = "fortuna";
		}
		if (ench == "dig speed") {
			ench = "eficiência";
		}
		if (ench == "loot bonus mobs") {
			ench = "pilhagem";
		}
		if (ench == "damage all") {
			ench = "afiação";
		}
		if (ench == "protection projectile") {
			ench = "proteção contra projéteteis";
		}
		if (ench == "protection explosions") {
			ench = "proteção contra explosões";
		}
		if (ench == "protection fire") {
			ench = "proteção contra fogo";
		}
		if (ench == "protection environmental") {
			ench = "proteção";
		}
		if (ench == "fire aspect") {
			ench = "aspecto flamejante";
		}
		return ench;
	}
}
