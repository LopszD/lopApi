package com.wandy.api.commands;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.wandy.api.Main;
import com.wandy.api.chat.ChatMenu;

public class ReinícioCommand implements CommandExecutor, Listener {

	public static ArrayList<Integer> rein = new ArrayList<Integer>();
	public static boolean ras = false;

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("reiniciar")) || (!sender.hasPermission("wandy.reiniciar"))) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("cancelar")) {
				if (sender.hasPermission("wandy.reiniciar")) {
					if (!taReiniciando()) {
						sender.sendMessage("§cServidor não está em processo de reinicío.");
						return true;
					}
					ChatMenu.check.remove("REINICIO");
					ChatMenu.check.remove("GLOBAL");
					ChatMenu.check.remove("ALIADO");
					ChatMenu.check.remove("LOCAL");
					ChatMenu.check.remove("FACCAO");
					for (Iterator<Integer> localIterator = rein.iterator(); localIterator.hasNext();) {
						int is = localIterator.next().intValue();
						Bukkit.getServer().getScheduler().cancelTask(is);
					}
					sender.sendMessage("§aVocê cancelou o reinicío com sucesso.");
					Bukkit.broadcastMessage("§cUm staffer cancelou o reinício do servidor, algumas funções foram ativadas novamente.");
					return true;
				}
			}
		}
		if (taReiniciando()) {
			sender.sendMessage("§cO servidor já está reiniciando.");
			return true;
		}
		Bukkit.broadcastMessage("§1");
		Bukkit.broadcastMessage("§e [REINíCIO] Reiniciando o servidor em 3 minutos.");
		Bukkit.broadcastMessage("§3");
		ChatMenu.check.add("REINICIO");
		fecharInv();
		if (!ChatMenu.check.contains("GLOBAL")) {
			ChatMenu.check.add("GLOBAL");
		}
		if (!ChatMenu.check.contains("LOCAL")) {
			ChatMenu.check.add("LOCAL");
		}
		if (!ChatMenu.check.contains("ALIADO")) {
			ChatMenu.check.add("ALIADO");
		}
		if (!ChatMenu.check.contains("FACCAO")) {
			ChatMenu.check.add("FACCAO");
		}
		int a = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			public void run() {
				Bukkit.broadcastMessage("§1");
				Bukkit.broadcastMessage("§e [REINíCIO] Reiniciando o servidor em 2 minutos.");
				Bukkit.broadcastMessage("§3");
			}
		}, 1200L);

		int b = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			public void run() {
				Bukkit.broadcastMessage("§1");
				Bukkit.broadcastMessage("§e [REINíCIO] Reiniciando o servidor em 1 minuto.");
				Bukkit.broadcastMessage("§3");
			}
		}, 2400L);

		int c = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			public void run() {
				Bukkit.broadcastMessage("§1");
				Bukkit.broadcastMessage("§e [REINíCIO] Reiniciando o servidor em 30 segundos.");
				Bukkit.broadcastMessage("§3");
			}
		}, 3000L);

		int d = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			public void run() {
				ReinícioCommand.ras = true;
				Bukkit.broadcastMessage("§1");
				Bukkit.broadcastMessage("§e [REINíCIO] Reiniciando...");
				Bukkit.broadcastMessage("§e [REINíCIO] Todos os usuários serão desconectados...");
				Bukkit.broadcastMessage("§3");
	            Bukkit.savePlayers();
	            Bukkit.getOnlinePlayers().stream().forEach(p -> {
	                p.saveData();
	                Main.getInstance().connect(p, "Lobby");
	                return;
	            });
			}
		}, 3600L);
		int e = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			public void run() {
				Bukkit.getWorlds().stream().forEach(worlds -> worlds.save());
                Bukkit.spigot().restart();
			}
		}, 3700L);
		rein.add(Integer.valueOf(a));
		rein.add(Integer.valueOf(b));
		rein.add(Integer.valueOf(c));
		rein.add(Integer.valueOf(d));
		rein.add(Integer.valueOf(e));
		return true;
	}

	public static void fecharInv() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getOpenInventory() != null) {
				p.closeInventory();
			}
		}
	}

	public static boolean taReiniciando() {
		if (ChatMenu.check.contains("REINICIO")) {
			return true;
		}
		return false;
	}

	@EventHandler
	public static void aoDropar(PlayerDropItemEvent e) {
		if (taReiniciando()) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public static void aoColocar(BlockPlaceEvent e) {
		if (taReiniciando()) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onBater(EntityDamageByEntityEvent e) {
		if ((!e.isCancelled()) && ((e.getEntity() instanceof Player))) {
			if ((e.getDamager() instanceof Player)) {
				if (taReiniciando()) {
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void putBigorna(BlockPlaceEvent e) {
		if ((taReiniciando()) && (e.getItemInHand().getType() == Material.ANVIL)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getInventory() == null) return;
		if (taReiniciando() && e.getClick().isShiftClick()) {
			e.setCancelled(true);
		}
	}
}
