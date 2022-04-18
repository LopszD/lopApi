package com.wandy.api.mobspawner;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.wandy.api.caixas.spawners.Conteúdo;

public class MobsCommand implements CommandExecutor, Listener {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("mobspawner")) {
		}
		CommandSender p = sender;
		if (!p.hasPermission("wandy.mobspawner")) {
			p.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			Player p1 = (Player) p;
			MobsMenu.abrirMenu(p1);
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("clear")) {
				if ((!p.isOp())) {
					p.sendMessage("§cVocê não tem permissão para executar este comando.");
					return true;
				}
				abrirMenu((Player) p);
				return true;
			}
			p.sendMessage("§cUtilize /mobspawner <usuário> <tipo> <quantidade> para enviar um mobspawner.");
			return true;
		}
		if (args.length >= 3) {
			String jogador = args[0];
			Integer quantia = null;
			if (Bukkit.getPlayer(jogador) == null) {
				p.sendMessage("§cEste usuário não está on-line.");
				return true;
			}
			try {
				quantia = Integer.valueOf(args[2]);
			} catch (NumberFormatException e) {
				p.sendMessage("§cO número " + args[2] + " é inválido.");
				return true;
			}
			Player p1 = Bukkit.getPlayer(jogador);
			String tipo = args[1].toUpperCase();
			String letra = new StringBuilder().append(tipo.charAt(0)).toString();
			String mob = letra.toUpperCase() + tipo.toLowerCase().substring(1);
			ItemStack a = Conteúdo.spawnerCreate(mob);
			a.setAmount(quantia);
			p1.getInventory().addItem(a);
			p.sendMessage("§aVocê enviou " + quantia + " mobspawner(s) de " + tipo.toUpperCase() + " para " + p1.getName() + ".");
			return true;
		}
		return false;
	}

	public static boolean eFora(Chunk ps, Player p) {
		WorldBorder wb = p.getWorld().getWorldBorder();
		int raio = (int) wb.getSize() / 2;
		int x = raio - 16;
		int y = raio - 16;
		int x1 = -raio;
		int y1 = -raio;
		int cx = ps.getX() * 16;
		int cz = ps.getZ() * 16;
		if (cx > x) {
			return true;
		}
		if (cz > y) {
			return true;
		}
		if (cx < x1) {
			return true;
		}
		if (cz < y1) {
			return true;
		}
		return false;
	}

	public static void abrirMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, "§8Deseja mesmo fazer isso?");

		ItemStack C = new ItemStack(Material.WOOL, 1, (short) 5);
		ItemMeta Cm = C.getItemMeta();
		Cm.setDisplayName("§aAceitar (Leia abaixo)");
		C.setItemMeta(Cm);
		List<String> lore = new ArrayList<String>();
		lore.add("§7Ao confirmar está ação você irá deletar/remover");
		lore.add("§7todos os mobspawners de cave e zombie do servidor.");
		lore.add("§1");
		lore.add("§7Clique aqui para confirmar!");
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
		
		inv.setItem(11, C);
		inv.setItem(15, C2);
		
		p.openInventory(inv);
	}

	@EventHandler
	public static void aoClicar(InventoryClickEvent e) {
		if (e.getInventory().getTitle().equals("§8Deseja mesmo fazer isso?")) {
			if (e.getCurrentItem() != null) {
				e.setCancelled(true);
				Player p = (Player) e.getWhoClicked();
				if (e.getSlot() == 11) {
					p.closeInventory();
				}
				if (e.getSlot() == 15) {
					p.closeInventory();
				}
			}
		}
	}
}
