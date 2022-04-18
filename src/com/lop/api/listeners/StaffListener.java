package com.wandy.api.listeners;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.massivecraft.factions.entity.MPlayer;
import com.wandy.api.commands.FlyCommand;
import com.wandy.api.commands.GodCommand;
import com.wandy.api.commands.VanishCommand;
import com.wandy.economy.API_Economy;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class StaffListener implements Listener {
	
	@SuppressWarnings({ "deprecation" })
	public static void abrirMenu(Player p) {
		NumberFormat df = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
		Inventory inv = Bukkit.createInventory(null, 54, "§8Membros da equipe");
		int i = 10;
		for (Player todos : Bukkit.getOnlinePlayers()) {
			if (todos.hasPermission("wandy.equipe")) {
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
					String prefix = PermissionsEx.getUser(todos).getGroups()[0].getPrefix();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM);
					skull.setDurability((short) 3);
					SkullMeta sm = (SkullMeta) skull.getItemMeta();
					sm.setOwner(todos.getName());
					List<String> lore = new ArrayList<String>();
					String god = "§cDesligado";
					String fly = "§cDesligado";
					String v = "§cDesligado";
					if (VanishCommand.vanish.contains(todos.getName())) {
						v = "§aLigado";
					}
					if (GodCommand.godmode.contains(todos.getName())) {
						god = "§aLigado";
					}
					if (FlyCommand.voando.contains(todos.getName())) {
						fly = "§aLigado";
					}
					lore.add("§2");
					lore.add("§7Nick: §e" + todos.getName());
					lore.add("§7Modo deus: " + god);
					lore.add("§7Modo fly: " + fly);
					lore.add("§7Invisibilidade: " + v);
					lore.add("§7Modo de jogo: §e" + todos.getGameMode());
					lore.add("§7Coins: §e" + df.format(API_Economy.getScoin(todos.getName())));
					String fac = "Nenhuma";
					MPlayer m = MPlayer.get(todos.getName());
					if (m.hasFaction()) {
						fac = m.getFaction().getTag() + " - " + m.getFaction().getName();
					}
					lore.add("§7Facção: §7" + fac);
					lore.add("§4");
					sm.setDisplayName(prefix.replace("&", "§") + " " + todos.getName());
					sm.setLore(lore);
					skull.setItemMeta(sm);
					inv.setItem(i, skull);
					i++;
				}
			}
		}
		p.openInventory(inv);
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (!e.getInventory().getName().contains("§8Membros da equipe")) {
			return;
		}
		e.setCancelled(false);
		if (e.getCurrentItem() == null) {
			return;
		}
		e.setCancelled(true);
	}

	public static String getTempo(long time) {
		long variacao = time;
		long varsegundos = variacao / 1000 % 60;
		long varminutos = variacao / 60000 % 60;
		long varhoras = variacao / 3600000 % 24;
		long vardias = variacao / 86400000 % 7;
		String segundos = String.valueOf(varsegundos).replaceAll("-", "");
		String minutos = String.valueOf(varminutos).replaceAll("-", "");
		String horas = String.valueOf(varhoras).replaceAll("-", "");
		String dias = String.valueOf(vardias).replaceAll("-", "");
		if (dias.equals("0") && horas.equals("0") && minutos.equals("0")) {
			return "§c" + segundos + "§cs";
		}
		if (dias.equals("0") && horas.equals("0")) {
			return "§c" + minutos + "§cm " + segundos + "§cs";
		}
		if (dias.equals("0")) {
			return "§c" + horas + "§ch " + minutos + "§cm " + segundos + "§cs";
		}
		return "§c" + dias + "d " + horas + "§ch " + minutos + "§cm " + segundos + "§cs ";
	}
}
