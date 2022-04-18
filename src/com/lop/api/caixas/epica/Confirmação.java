package com.wandy.api.caixas.epica;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.wandy.api.Main;
import com.wandy.api.commands.ReinícioCommand;

public class Confirmação implements Listener {
	
	@EventHandler
	private void aoClicar(PlayerInteractEvent e) {
		if (e.getItem() == null) {
			return;
		}
		if (e.getItem().getType() == Material.AIR) {
			return;
		}
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			ItemMeta meta = e.getPlayer().getItemInHand().getItemMeta();
			if ((e.getPlayer().getItemInHand().getType() == Material.ENDER_CHEST) && (meta != null) && (meta.getDisplayName() != null) && (meta.getDisplayName().contains("§3Caixa Misteriosa"))) {
				e.getPlayer().updateInventory();
				e.setCancelled(true);
				if (ReinícioCommand.taReiniciando()) {
					e.getPlayer().sendMessage("§cServidor reiniciando, você não pode completar está ação no momento.");
					e.setCancelled(true);
					return;
				}
				confirmarAbrirEPICA(e.getPlayer());
			}
		}
		if (e.getAction() == Action.RIGHT_CLICK_AIR) {
			ItemMeta meta = e.getPlayer().getItemInHand().getItemMeta();
			if ((e.getPlayer().getItemInHand().getType() == Material.ENDER_CHEST) && (meta != null) && (meta.getDisplayName() != null) && (meta.getDisplayName().contains("§3Caixa Misteriosa"))) {
				e.setCancelled(true);
				e.getPlayer().updateInventory();
				if (ReinícioCommand.taReiniciando()) {
					e.getPlayer().sendMessage("§cServidor reiniciando, você não pode completar está ação no momento.");
					e.setCancelled(true);
					return;
				}
				confirmarAbrirEPICA(e.getPlayer());
			}
		}
	}

	public static void confirmarAbrirEPICA(Player p) {
		Inventory inv = Bukkit.createInventory(null, 36, "§8Confirmação - Caixa Platina");

		ItemStack m = new ItemStack(Material.ENDER_CHEST);
		ItemMeta mm = m.getItemMeta();
		mm.setDisplayName("§3Caixa Misteriosa");
		List<String> l = new ArrayList<String>();
		l.add("§1");
		l.add("§7Abra está caixa e receba um item");
		l.add("§7incrível que será sorteado.");
		l.add("§2");
		l.add("§fTipo da caixa: §3Platina");
		l.add("§aClique para ver o conteúdo!");
		mm.setLore(l);
		m.setItemMeta(mm);

		ItemStack C = new ItemStack(Material.WOOL, 1, (short) 5);
		ItemMeta Cm = C.getItemMeta();
		Cm.setDisplayName("§aAceitar (Leia abaixo)");
		C.setItemMeta(Cm);
		List<String> lore = new ArrayList<String>();
		lore.add("§7Tem certeza que deseja abrí-la?");
		Cm.setLore(lore);
		C.setItemMeta(Cm);

		ItemStack C2 = new ItemStack(Material.WOOL, 1, (short) 14);
		ItemMeta Cm2 = C2.getItemMeta();
		Cm2.setDisplayName("§cNegar");
		C2.setItemMeta(Cm2);
		List<String> lore1 = new ArrayList<String>();
		lore1.add("§7Cancelar está operação.");
		Cm2.setLore(lore1);
		C2.setItemMeta(Cm2);

		inv.setItem(13, m);
		inv.setItem(20, C);
		inv.setItem(24, C2);

		p.openInventory(inv);
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (e.getInventory().getName().equalsIgnoreCase("§8Confirmação - Caixa Platina")) {
			Player p = (Player) e.getWhoClicked();
			if (e.getCurrentItem() == null) {
				return;
			}
			e.setCancelled(true);
			if (e.getSlot() == 20) {
				e.setCancelled(true);
				if (p.getInventory().getItemInHand().getType() == Material.AIR) {
					e.setCancelled(true);
					p.closeInventory();
					p.sendMessage("§cTá tentando dupar meu filho?! Aqui é trabalho!");
					return;
				}
				if (!(p.getItemInHand().getType() == Material.ENDER_CHEST)) {
					e.setCancelled(true);
					p.closeInventory();
					p.sendMessage("§cTá tentando dupar meu filho?! Aqui é trabalho!");
					return;
				}
				if (!p.getItemInHand().getItemMeta().getDisplayName().contains("§3Caixa Misteriosa")) {
					e.setCancelled(true);
					p.closeInventory();
					p.sendMessage("§cTá tentando dupar meu filho?! Aqui é trabalho!");
					return;
				}
				if (p.getItemInHand().getAmount() >= 2) {
					p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
				} else if (p.getItemInHand().getAmount() == 1) {
					p.setItemInHand(new ItemStack(Material.AIR));
				}
				e.setCancelled(true);
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
					public void run() {
						Animação.spawnerAnime(p);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
					}
				}, 0L);
			}
			if (e.getSlot() == 24) {
				p.closeInventory();
			}
			if (e.getSlot() == 13) {
				Conteúdo.confirmarAbrir(p);
			}
		}
	}
}
