package com.wandy.api.especiais.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.wandy.api.utils.FireworkEffectPlayer;

public class MembroListener implements Listener {

	static FireworkEffectPlayer fplayer = new FireworkEffectPlayer();

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoColocar(PlayerInteractEvent e) {
		if (e.getItem() == null) {
			return;
		}
		if (e.getItem().getType().equals(Material.AIR)) {
			return;
		}
		if (e.getItem().getType().equals(Material.NETHER_STAR)) {
			if (e.getItem().getItemMeta().hasDisplayName()) {
				if (e.getItem().getItemMeta().getDisplayName().contains("§eMembros+")) {
					Player p = e.getPlayer();
					if ((e.getAction().equals(Action.RIGHT_CLICK_AIR)) || (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
						e.setCancelled(true);
						abrirConfi(p);
					}
				}
			}
		}
	}

	@EventHandler
	public static void aoClicar(InventoryClickEvent e) throws Exception {
		if (e.getInventory().getTitle().equals("§8Confirmação - Membro+")) {
			Player p = (Player) e.getWhoClicked();
			if (e.getCurrentItem() == null) {
				return;
			}
			if (e.getCurrentItem().getType().equals(Material.AIR)) {
				return;
			}
			e.setCancelled(true);
			if (eDup(p)) {
				p.closeInventory();
				p.sendMessage("§cOcorreu um erro ao concluir está ação. Tente novamente.");
				return;
			}
			if (e.getSlot() == 24) {
				p.closeInventory();
			}
			if (e.getSlot() == 20) {
				MPlayer mp = MPlayer.get(p);
				Faction f = mp.getFaction();
				int maximo = f.getMembersLimit();
				Color color = Color.GREEN;
				if (!mp.hasFaction()) {
					p.sendMessage("§cVocê não tem uma facção.");
					return;
				}
				if (maximo >= 20) {
					p.sendMessage("§cSua facção já está com o seu limite de membros no máximo (Máx: 20).");
					return;
				}
				if (p.getItemInHand().getAmount() >= 2) {
					p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
				} else if (p.getItemInHand().getAmount() == 1) {
					p.setItemInHand(new ItemStack(Material.AIR));
				}
				p.updateInventory();
				p.closeInventory();
				f.setMemberBoost(f.getMemberBoost() + 1);
				p.sendMessage("§aLimite de membros da facção foi aumentado para " + f.getMembersLimit() + "!");
				p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 10.0F, 10.0F);
				fplayer.playFirework(p.getWorld(), p.getLocation(), getRandomEffect(color));
				return;
			}
		}
	}

	public static boolean eDup(Player p) {
		if (p.getItemInHand() == null) {
			return true;
		}
		if (p.getItemInHand().getType().equals(Material.AIR)) {
			return true;
		}
		if (!p.getItemInHand().getType().equals(Material.NETHER_STAR)) {
			return true;
		}
		if (p.getItemInHand().hasItemMeta()) {
			if (p.getItemInHand().getItemMeta().hasDisplayName()) {
				if (!p.getItemInHand().getItemMeta().getDisplayName().contains("§eMembros+")) {
					return true;
				}
			}
		}
		return false;
	}

	public static void abrirConfi(Player p) {
		Inventory inv = Bukkit.createInventory(null, 36, "§8Confirmação - Membro+");

		ItemStack a = new ItemStack(Material.NETHER_STAR);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName("§eMembros+");
		am.setLore(Arrays.asList(new String[] { "§7Ao utilizar este item, o número máxímo de", "§7membros da facção será aumentado." }));
		a.setItemMeta(am);

		ItemStack C = new ItemStack(Material.WOOL, 1, (short) 5);
		ItemMeta Cm = C.getItemMeta();
		Cm.setDisplayName("§aAceitar");
		C.setItemMeta(Cm);
		List<String> lore = new ArrayList<String>();
		lore.add("§7Tem certeza que deseja confirmar?");
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

		inv.setItem(13, a);
		inv.setItem(20, C);
		inv.setItem(24, C2);

		p.openInventory(inv);
	}

	public static FireworkEffect getRandomEffect(Color color) {
		return FireworkEffect.builder().with(org.bukkit.FireworkEffect.Type.BALL).withColor(color).build();
	}
}
