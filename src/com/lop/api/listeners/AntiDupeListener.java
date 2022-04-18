package com.wandy.api.listeners;

import java.util.ArrayList;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class AntiDupeListener implements Listener {

	public static ArrayList<String> abriu = new ArrayList<String>();

	/*
	 * @EventHandler public static void aoSair(PlayerQuitEvent e) { if
	 * (abriu.contains(e.getPlayer().getName())) {
	 * abriu.remove(e.getPlayer().getName()); } }
	 * 
	 * @EventHandler public static void aoEntrar(PlayerJoinEvent e) { if
	 * (abriu.contains(e.getPlayer().getName())) {
	 * abriu.remove(e.getPlayer().getName()); } }
	 */

	@EventHandler
	void event(PlayerDropItemEvent e) {
		if (e.getPlayer().getOpenInventory().getTitle().startsWith("Enderchest de")) {
			e.setCancelled(true);
		}
	}

	/*
	 * @EventHandler public static void aoAbrir(InventoryOpenEvent e) { if
	 * (e.getInventory().getTitle().contains("Enderchest de " +
	 * e.getPlayer().getName())) { if (abriu.contains(e.getPlayer().getName())) {
	 * e.setCancelled(true); e.getPlayer().
	 * sendMessage("§cOcorreu um erro ao abrir o enderchest, tente novamente mais tarde."
	 * ); return; }
	 * Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new
	 * Runnable() { public void run() { Player p = (Player) e.getPlayer(); if
	 * (!AntiDupeListener.abriu.contains(p.getName())) {
	 * AntiDupeListener.abriu.add(p.getName()); return; } } }, 20L); } }
	 */

	/*
	 * @EventHandler public static void aoDigitar(PlayerCommandPreprocessEvent e) {
	 * Player p = e.getPlayer(); String[] msg = e.getMessage().split(" "); if
	 * (msg.length == 0) { return; } if
	 * ((msg[0].equalsIgnoreCase("/whyzechest:bau")) ||
	 * (msg[0].equalsIgnoreCase("/whyzechest:chest")) ||
	 * (msg[0].equalsIgnoreCase("/whyzeenderchests:ec")) ||
	 * (msg[0].equalsIgnoreCase("/whyzeenderchests:echest")) ||
	 * (msg[0].equalsIgnoreCase("/whyzeenderchests:enderchest")) ||
	 * (msg[0].equalsIgnoreCase("/bau")) || (msg[0].equalsIgnoreCase("/chest")) ||
	 * (msg[0].equalsIgnoreCase("/ec")) || (msg[0].equalsIgnoreCase("/enderchest")))
	 * { if (abriu.contains(e.getPlayer().getName())) { e.setCancelled(true);
	 * e.getPlayer().
	 * sendMessage("§cOcorreu um erro ao abrir o enderchest, tente novamente mais tarde."
	 * ); return; } if (abriu.contains(e.getPlayer().getName())) {
	 * e.setCancelled(true); abriu.add(p.getName()); return; } } }
	 */

	/*
	 * @EventHandler public static void aoTocar(PlayerInteractEvent e) { if
	 * (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) { if (e.getClickedBlock() !=
	 * null) { if (e.getClickedBlock().getType().equals(Material.ENDER_CHEST)) { if
	 * (abriu.contains(e.getPlayer().getName())) { e.setCancelled(true);
	 * e.getPlayer().
	 * sendMessage("§cOcorreu um erro ao abrir o enderchest, tente novamente mais tarde."
	 * ); return; } if (abriu.contains(e.getPlayer().getName())) {
	 * e.setCancelled(true); abriu.add(e.getPlayer().getName()); return; } } } } }
	 */

	/*
	 * @EventHandler public static void aoFechar(InventoryCloseEvent e) { if
	 * (e.getInventory().getTitle().contains("Enderchest de")) { if
	 * (abriu.contains(e.getPlayer().getName())) {
	 * abriu.remove(e.getPlayer().getName()); } } }
	 */

	@EventHandler
	public static void aoComandar(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (p.getOpenInventory() != null) {
			p.closeInventory();
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onAntiDupe(PlayerInteractEntityEvent e) {
		if (e.isCancelled())
			return;
		Player p = e.getPlayer();
		EntityType t = e.getRightClicked().getType();
		String message = "§cVocê está realizando atividades suspeitas, foi enviado um alerta para a equipe.";
		if (t == EntityType.ITEM_FRAME && p.isInsideVehicle()) {
			e.setCancelled(true);
			p.sendMessage(message);
		} else if (e.getRightClicked().getType() == EntityType.MINECART_CHEST) {
			e.setCancelled(true);
			p.sendMessage(message);
		} else if (t == EntityType.MINECART_FURNACE || t == EntityType.MINECART_HOPPER || t == EntityType.MINECART_MOB_SPAWNER || t == EntityType.MINECART_TNT || t == EntityType.MINECART) {
			e.setCancelled(true);
			p.sendMessage(message);
		} else if (t == EntityType.BOAT) {
			e.setCancelled(true);
			p.sendMessage(message);
		}
	}

	@EventHandler
	public void onOpen(InventoryOpenEvent e) {
		if (e.getInventory().getType() == InventoryType.HOPPER) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cVocê não pode abrir funis.");
		}
	}
}
