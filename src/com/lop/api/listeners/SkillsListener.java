package com.wandy.api.listeners;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
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
import org.bukkit.inventory.meta.SkullMeta;

import com.gmail.nossr50.mcMMO;
import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.api.SkillAPI;
import com.gmail.nossr50.datatypes.database.PlayerStat;
import com.gmail.nossr50.datatypes.skills.SkillType;
import com.wandy.api.sql.MySQL;
import com.wandy.api.utils.Util;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class SkillsListener implements Listener {

	public static boolean taBooste(String nome, String skk) {
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM Booster WHERE player=?");
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString("skill").equalsIgnoreCase(skk)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String getTim(String nome, String skk) {
		long i = 0L;
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM Booster WHERE player=? AND skill=?");
			ps.setString(1, nome);
			ps.setString(2, skk);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				i = Long.valueOf(rs.getString("delay")).longValue();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		long aa = i + 3600000L;
		long ant = aa - System.currentTimeMillis();
		long minutes = TimeUnit.MILLISECONDS.toMinutes(ant);
		String bos = "x2 (" + minutes + "m)";
		if (minutes <= 0L) {
			bos = "Nenhuma";
		}
		return bos;
	}

	@SuppressWarnings("deprecation")
	public static void abrirMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, "§8Habilidades");
		ItemStack skull = new ItemStack(Material.SKULL_ITEM);
		skull.setDurability((short) 3);
		SkullMeta sm = (SkullMeta) skull.getItemMeta();
		sm.setOwner(p.getName());
		String prefix = PermissionsEx.getUser(p).getGroups()[0].getPrefix();
		sm.setDisplayName(prefix.replace("&", "§") + " " + p.getName());
		sm.setLore(Arrays.asList("§f[⚒] Nível total: §7" + getAllLevel(p), "", "§f1º no rank: §7" + Util.getColorPrefix(getTopKill(null)) + getTopKill(null)));
		skull.setItemMeta(sm);
		ItemStack o = new ItemStack(Material.BOOK_AND_QUILL);
		ItemMeta om = o.getItemMeta();
		om.setDisplayName("§eRank de Habilidades");
		om.setLore(Arrays.asList("§7Clique para abrir o Rank de Habilidades."));
		o.setItemMeta(om);
		inv.setItem(14, o);
		inv.setItem(12, skull);
		inv.setItem(29, criarHab(p, "§eAcrobacias", Material.DIAMOND_BOOTS, (short) 0, SkillType.ACROBATICS));
		inv.setItem(30, criarHab(p, "§eReparação", Material.ANVIL, (short) 0, SkillType.REPAIR));
		inv.setItem(24, criarHab(p, "§eEscavação", Material.DIAMOND_SPADE, (short) 0, SkillType.EXCAVATION));
		inv.setItem(23, criarHab(p, "§eMineração", Material.DIAMOND_PICKAXE, (short) 0, SkillType.MINING));
		inv.setItem(22, criarHab(p, "§eArqueiro", Material.BOW, (short) 0, SkillType.ARCHERY));
		inv.setItem(21, criarHab(p, "§eMachados", Material.DIAMOND_AXE, (short) 0, SkillType.AXES));
		inv.setItem(20, criarHab(p, "§eEspadas", Material.DIAMOND_SWORD, (short) 0, SkillType.SWORDS));
		p.openInventory(inv);
	}

	@SuppressWarnings("deprecation")
	public static void abrirMenuOutro(Player p1, Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, "§8Habilidades");
		ItemStack skull = new ItemStack(Material.SKULL_ITEM);
		skull.setDurability((short) 3);
		SkullMeta sm = (SkullMeta) skull.getItemMeta();
		sm.setOwner(p.getName());
		String prefix = PermissionsEx.getUser(p).getGroups()[0].getPrefix();
		sm.setDisplayName(prefix.replace("&", "§") + " " + p.getName());
		sm.setLore(Arrays.asList("§f[⚒] Nível total: §7" + getAllLevel(p), "", "§f1º no rank: §7" + Util.getColorPrefix(getTopKill(null)) + getTopKill(null)));
		skull.setItemMeta(sm);
		ItemStack o = new ItemStack(Material.BOOK_AND_QUILL);
		ItemMeta om = o.getItemMeta();
		om.setDisplayName("§eRank de Habilidades");
		om.setLore(Arrays.asList("§7Clique para abrir o Rank de Habilidades."));
		o.setItemMeta(om);
		inv.setItem(14, o);
		inv.setItem(12, skull);
		inv.setItem(23, criarHab(p, "§eEscavação", Material.DIAMOND_SPADE, (short) 0, SkillType.EXCAVATION));
		inv.setItem(22, criarHab(p, "§eMineração", Material.DIAMOND_PICKAXE, (short) 0, SkillType.MINING));
		inv.setItem(21, criarHab(p, "§eArqueiro", Material.BOW, (short) 0, SkillType.ARCHERY));
		inv.setItem(20, criarHab(p, "§eEspadas", Material.DIAMOND_SWORD, (short) 0, SkillType.SWORDS));
		inv.setItem(24, criarHab(p, "§eAcrobacias", Material.DIAMOND_BOOTS, (short) 0, SkillType.ACROBATICS));
		inv.setItem(31, criarHab(p, "§eReparação", Material.ANVIL, (short) 0, SkillType.REPAIR));
		p1.openInventory(inv);
	}

	public static void abrirTopRank(Player p) {
		Inventory inv = Bukkit.createInventory(null, 54, "§8Rank de Habilidades");
		inv.setItem(13, criarTopHab("§eNível Total", Material.BOOK_AND_QUILL, null));
		inv.setItem(20, criarTopHab("§eEspadas", Material.DIAMOND_SWORD, SkillType.SWORDS));
		inv.setItem(21, criarTopHab("§eMachados", Material.DIAMOND_AXE, SkillType.AXES));
		inv.setItem(22, criarTopHab("§eArqueiro", Material.BOW, SkillType.ARCHERY));
		inv.setItem(23, criarTopHab("§eMineração", Material.DIAMOND_PICKAXE, SkillType.MINING));
		inv.setItem(24, criarTopHab("§eEscavação", Material.DIAMOND_SPADE, SkillType.EXCAVATION));
		inv.setItem(29, criarTopHab("§eAcrobacias", Material.DIAMOND_BOOTS, SkillType.ACROBATICS));
		inv.setItem(30, criarTopHab("§eReparação", Material.ANVIL, SkillType.REPAIR));
		ItemStack v = new ItemStack(Material.ARROW);
		ItemMeta vm = v.getItemMeta();
		vm.setDisplayName("§aVoltar");
		v.setItemMeta(vm);
		inv.setItem(49, v);
		p.openInventory(inv);
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (e.getInventory().getName().equals("§8Habilidades")) {
			if (e.getCurrentItem() == null) {
				return;
			}
			e.setCancelled(true);
			if (e.getSlot() == 14) {
				e.setCancelled(true);
				Player p = (Player) e.getWhoClicked();
				abrirTopRank(p);
				return;
			}
		}
		if (e.getInventory().getName().contains("§8Rank de Habilidades")) {
			if (e.getCurrentItem() == null) {
				return;
			}
			e.setCancelled(true);
			if (e.getSlot() == 49) {
				e.setCancelled(true);
				Player p = (Player) e.getWhoClicked();
				abrirMenu(p);
			}
		}
	}

	public static ItemStack criarHabG(Player p, String nome, Material m, short damage, SkillType skill) {
		ItemStack a = new ItemStack(m, 1, damage);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName(nome);
		am.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		List<String> lore = new ArrayList<String>();
		int lvl = ExperienceAPI.getLevel(p, skill.getName());
		lore.add("§fNível: §7" + lvl);
		String bonus = "§7Nenhum";
		if (p.hasPermission("wandy.bonus1")) {
			bonus = "+1.25x";
		}
		if (p.hasPermission("wandy.bonus2")) {
			bonus = "+1.50x";
		}
		if (p.hasPermission("wandy.bonus3")) {
			bonus = "+1.70x";
		}
		if (p.hasPermission("wandy.bonus4")) {
			bonus = "+2.10x";
		}
		lore.add("");
		lore.add("§fBônus VIP: §7" + bonus);
		lore.add("");
		lore.add("§f1º em " + skill.getName() + ": §7" + Util.getColorPrefix(getTopKill(skill)) + getTopKill(skill));
		am.setLore(lore);
		a.setItemMeta(am);
		return a;
	}

	public static ItemStack criarHab(Player p, String nome, Material m, short damage, SkillType skill) {
		ItemStack a = new ItemStack(m, 1, damage);
		ItemMeta am = a.getItemMeta();
		am.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		am.setDisplayName(nome);
		List<String> lore = new ArrayList<String>();
		int lvl = ExperienceAPI.getLevel(p, skill.getName());
		lore.add("§fNível: §7" + lvl);
		String bonus = "§7Nenhum";
		if (p.hasPermission("wandy.bonus1")) {
			bonus = "+1.25x";
		}
		if (p.hasPermission("wandy.bonus2")) {
			bonus = "+1.50x";
		}
		if (p.hasPermission("wandy.bonus3")) {
			bonus = "+1.70x";
		}
		if (p.hasPermission("wandy.bonus4")) {
			bonus = "+2.10x";
		}
		lore.add("");
		lore.add("§fBônus VIP: §7" + bonus);
		lore.add("");
		lore.add("§f1º em "  + skill.getName() + ": §7" + Util.getColorPrefix(getTopKill(skill)) + getTopKill(skill));
		am.setLore(lore);
		a.setItemMeta(am);
		return a;
	}

	public static ItemStack criarTopHab(String nome, Material m, SkillType skill) {
		ItemStack a = new ItemStack(m);
		ItemMeta am = a.getItemMeta();
		am.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		am.setDisplayName(nome);
		List<String> lore = new ArrayList<String>();
		List<PlayerStat> userStats = mcMMO.getDatabaseManager().readLeaderboard(skill, 1, 10);
		int i = 1;
		for (PlayerStat cp : userStats) {
			if (i <= 10) {
				lore.add("§f" + i + "º §7" + Util.getColorPrefix(cp.name) + cp.name + ": §7" + cp.statVal);
				i++;
			}
		}
		am.setLore(lore);
		a.setItemMeta(am);
		return a;
	}

	public static String getTopKill(SkillType st) {
		String nome = "";
		List<PlayerStat> userStats = mcMMO.getDatabaseManager().readLeaderboard(st, 1, 10);
		nome = userStats.get(0).name;
		return nome;
	}

	public static int getAllLevel(Player p) {
		int sum = 0;
		for (String skill : SkillAPI.getSkills()) {
			sum += ExperienceAPI.getLevel(p, skill);
		}
		return sum;
	}

	public static int getLevelsCombate(Player p) {
		int i = 0;
		for (String skill : SkillAPI.getCombatSkills()) {
			i += ExperienceAPI.getLevel(p, skill);
		}
		return i;
	}
}
