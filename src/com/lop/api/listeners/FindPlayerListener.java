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
import com.wandy.economy.API_Economy;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class FindPlayerListener implements Listener {
	
	@SuppressWarnings({ "deprecation" })
	public static void abrirMenu(Player p, Integer raio) {
		Inventory inv = Bukkit.createInventory(null, 54, "§8Usuários encontrados");
		int i = 10;
		for (Player todos : Bukkit.getOnlinePlayers()) {
			if (!todos.equals(p)) {
				if (p.getLocation().distance(todos.getLocation()) <= raio.intValue()) {
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
						NumberFormat df = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
						if (prefix.length() <= 2) {
							prefix = "Membro";
						}
						ItemStack skull = new ItemStack(Material.SKULL_ITEM);
						skull.setDurability((short) 3);
						SkullMeta sm = (SkullMeta) skull.getItemMeta();
						sm.setOwner(todos.getName());
						sm.setDisplayName("§e" + todos.getName());
						List<String> lore = new ArrayList<String>();
						lore.add("§2");
						lore.add("§fClique aqui §7para teleportar.");
						lore.add("§3");
						lore.add("§7Grupo: §e" + prefix.replace("&", "§").replace("[", "").replace("]", ""));
						lore.add("§7Coins: §e" + df.format(API_Economy.getScoin(todos.getName())));
						String fac = "Nenhuma";
						MPlayer m = MPlayer.get(todos.getName());
						if (m.hasFaction()) {
							fac = m.getFaction().getTag() + " - " + m.getFaction().getName();
						}
						lore.add("§7Facção: §e" + fac);
						lore.add("§4");
						sm.setLore(lore);
						skull.setItemMeta(sm);
						inv.setItem(i, skull);
						i++;
					}
				}
			}
		}
		p.openInventory(inv);
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (!e.getInventory().getName().contains("§8Usuários encontrados")) {
			return;
		}
		e.setCancelled(false);
		if (e.getCurrentItem() == null) {
			return;
		}
		Player p = (Player) e.getWhoClicked();
		if (e.getSlot() > 9) {
			if (e.getSlot() < 44) {
				e.setCancelled(true);
				String nome = e.getCurrentItem().getItemMeta().getDisplayName().replace("§e", "");
				p.closeInventory();
				if (Bukkit.getPlayer(nome) == null) {
					p.sendMessage("§cEste usuário não está on-line.");
					return;
				}
				Player p1 = Bukkit.getPlayer(nome);
				p.teleport(p1);
				p.sendMessage("§aTeleportado!");
			}
		}
		if (e.getCurrentItem() == null) {
			return;
		}
		e.setCancelled(true);
	}
}
