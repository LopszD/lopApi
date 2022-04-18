package com.wandy.api.listeners;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;

import com.wandy.economy.API_Economy;

public class RepararListener implements Listener {
	
	public static NumberFormat numberFormat;
	static NumberFormat df = NumberFormat.getNumberInstance(new Locale("pt", "BR"));

	public static void abrirMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 36, "§8Reparação");
		inv.setItem(13, p.getItemInHand());
		//int menas = 10;
		//int max = p.getItemInHand().getType().getMaxDurability();
		//int tem = p.getItemInHand().getDurability();
		//int dps = max - tem;
		//int preco = menas * dps;
		ItemStack C = new ItemStack(Material.WOOL, 1, (short) 5);
		ItemMeta Cm = C.getItemMeta();
		Cm.setDisplayName("§aReparar");
		C.setItemMeta(Cm);
		List<String> lore = new ArrayList<String>();
		lore.add("§7Você precisa de §a" + df.format(10000).replace(" ", "") + " coins §7para");
		lore.add("§7reparar este item.");
		Cm.setLore(lore);
		C.setItemMeta(Cm);
		inv.setItem(20, C);
		if (API_Economy.getMoney(p.getName()) < 10000) {
			ItemStack C1 = new ItemStack(Material.WOOL, 1, (short) 8);
			ItemMeta Cm1 = C1.getItemMeta();
			Cm1.setDisplayName("§cReparar");
			C1.setItemMeta(Cm);
			List<String> lore1 = new ArrayList<String>();
			lore1.add("§7Você precisa de §c" + df.format(10000).replace(" ", "") + " coins §7para");
			lore1.add("§7reparar este item.");
			Cm1.setLore(lore1);
			C1.setItemMeta(Cm1);
			inv.setItem(20, C1);
		}
		ItemStack C2 = new ItemStack(Material.WOOL, 1, (short) 14);
		ItemMeta Cm2 = C2.getItemMeta();
		Cm2.setDisplayName("§cNegar");
		C2.setItemMeta(Cm2);
		List<String> lore1 = new ArrayList<String>();
		lore1.add("§7Cancelar está operação.");
		Cm2.setLore(lore1);
		C2.setItemMeta(Cm2);
		inv.setItem(24, C2);
		p.openInventory(inv);
	}

	public static String format(double value) {
		numberFormat = NumberFormat.getNumberInstance(Locale.forLanguageTag("en-US"));
		if (value <= 1.0D) {
			return numberFormat.format(value).concat(" ").concat("");
		}
		return numberFormat.format(value).concat(" ").concat("").replace(",", ".").replace(" ", "");
	}

	@EventHandler
	public static void aoClicar(InventoryClickEvent e) {
		if (!e.getInventory().getTitle().equals("§8Reparação")) {
			return;
		}
		if (e.getCurrentItem() == null) {
			return;
		}
		if (e.getCurrentItem().getType().equals(Material.AIR)) {
			return;
		}
		e.setCancelled(true);
		Player p = (Player) e.getWhoClicked();
		if (e.getSlot() == 24) {
			p.closeInventory();
		}
		if (e.getSlot() == 20) {
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cReparar")) {
				return;
			}
			p.closeInventory();
			if (!p.hasPermission("wandy.reparar")) {
				p.sendMessage("§cVocê não tem permissão para reparar um item. Adquira seu VIP em §bwww.redewandy.com§c.");
				return;
			}
			if (p.getItemInHand() == null) {
				p.sendMessage("§cVocê precisa de um item em sua mão.");
				return;
			}
			if (p.getItemInHand().getType().equals(Material.AIR)) {
				p.sendMessage("§cVocê precisa de um item em sua mão.");
				return;
			}
			if (p.getItemInHand().getType().equals(Material.DIAMOND_BOOTS)) {
				p.sendMessage("§cVocê não pode reparar armaduras pelo /reparar.");
				return;
			}
			if (p.getItemInHand().getType().equals(Material.DIAMOND_CHESTPLATE)) {
				p.sendMessage("§cVocê não pode reparar armaduras pelo /reparar.");
				return;
			}
			if (p.getItemInHand().getType().equals(Material.DIAMOND_LEGGINGS)) {
				p.sendMessage("§cVocê não pode reparar armaduras pelo /reparar.");
				return;
			}
			if (p.getItemInHand().getType().equals(Material.DIAMOND_HELMET)) {
				p.sendMessage("§cVocê não pode reparar armaduras pelo /reparar.");
				return;
			}
			if (!(p.getItemInHand().getItemMeta() instanceof Repairable)) {
				p.sendMessage("§cTipo inválido de item.");
				return;
			}
			if (p.getItemInHand().getDurability() < 0) {
				p.sendMessage("§cEste item não pode ser reparado.");
				return;
			}
			if (p.getItemInHand().getType().isBlock()) {
				p.sendMessage("§cEste item não pode ser reparado.");
				return;
			}
			if (p.getItemInHand().getDurability() == 0) {
				p.sendMessage("§cEste item não pode ser reparado.");
				return;
			}
			ItemStack i1 = e.getInventory().getItem(13);
			ItemStack i2 = p.getItemInHand();
			if (!i1.equals(i2)) {
				p.sendMessage("§cOcorreu um erro ao reparar este item, tente novamente.");
				return;
			}
			if (i1.getDurability() != i2.getDurability()) {
				p.sendMessage("§cOcorreu um erro ao reparar este item, tente novamente.");
				return;
			}
			//String bruto = e.getCurrentItem().getItemMeta().getLore().get(0);
			//int preco = Integer.valueOf(bruto.replace("§7Você precisa de §a", "").replace(" coins §7para", "").replace(".", "")).intValue();
			API_Economy.delMoney(p.getName(), 10000);
			p.playSound(p.getLocation(), Sound.ANVIL_USE, 0.5F, 1.0F);
			p.getItemInHand().setDurability((short) 0);
		}
	}
}
