package com.wandy.api.listeners;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.massivecraft.factions.entity.MPlayer;
import com.wandy.api.Main;
import com.wandy.api.effects.EffectType;
import com.wandy.api.effects.manager.EffectManager;
import com.wandy.api.effects.menu.SelectEffectMenu;
import com.wandy.api.mana.ManaModel;
import com.wandy.api.utils.ScrollerInventory;

import net.md_5.bungee.api.ChatColor;

public class InventoryListener implements Listener {

	@SuppressWarnings({ "deprecation" })
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getCurrentItem() == null || e.getCurrentItem().getTypeId() == 0) return;
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase("Selecione o efeito")) {
			e.setCancelled(true);
			EffectManager em = Main.getInstance().effects.get(p.getName().toLowerCase());
			switch (e.getRawSlot()) {
			case 10:
				if (em.hasEffect(EffectType.ENDER_PEARL)) {
					if (EffectManager.effects.containsKey(p) && EffectManager.effects.get(p) == EffectType.ENDER_PEARL) {
						p.sendMessage("§cO efeito '" + e.getCurrentItem().getItemMeta().getDisplayName() + "§c' já está ativo.");
						return;
					}
					EffectManager.effects.put(p, EffectType.ENDER_PEARL);
				} else {
					buy(p, EffectType.ENDER_PEARL);
					return;
				}
				break;
			case 11:
				if (em.hasEffect(EffectType.ZEUS)) {
					if (EffectManager.effects.containsKey(p) && EffectManager.effects.get(p) == EffectType.ZEUS) {
						p.sendMessage("§cO efeito '" + e.getCurrentItem().getItemMeta().getDisplayName() + "§c' já está ativo.");
						return;
					}
					EffectManager.effects.put(p, EffectType.ZEUS);
				} else {
					buy(p, EffectType.ZEUS);
					return;
				}
				break;
			case 12:
				if (em.hasEffect(EffectType.DEATH)) {
					if (EffectManager.effects.containsKey(p) && EffectManager.effects.get(p) == EffectType.DEATH) {
						p.sendMessage("§cO efeito '" + e.getCurrentItem().getItemMeta().getDisplayName() + "§c' já está ativo.");
						return;
					}
					EffectManager.effects.put(p, EffectType.DEATH);
				} else {
					buy(p, EffectType.DEATH);
					return;
				}
				break;
			case 14:
				if (em.hasEffect(EffectType.HEALTH)) {
					if (EffectManager.effects.containsKey(p) && EffectManager.effects.get(p) == EffectType.HEALTH) {
						p.sendMessage("§cO efeito '" + e.getCurrentItem().getItemMeta().getDisplayName() + "§c' já está ativo.");
						return;
					}
					EffectManager.effects.put(p, EffectType.HEALTH);
				} else {
					buy(p, EffectType.HEALTH);
					return;
				}
				break;
			case 15:
				if (em.hasEffect(EffectType.JUMP)) {
					if (EffectManager.effects.containsKey(p) && EffectManager.effects.get(p) == EffectType.JUMP) {
						p.sendMessage("§cO efeito '" + e.getCurrentItem().getItemMeta().getDisplayName() + "§c' já está ativo.");
						return;
					}
					EffectManager.effects.put(p, EffectType.JUMP);
				} else {
					buy(p, EffectType.JUMP);
					return;
				}
				break;
			case 16:
				if (em.hasEffect(EffectType.POWER)) {
					if (EffectManager.effects.containsKey(p) && EffectManager.effects.get(p) == EffectType.POWER) {
						p.sendMessage("§cO efeito '" + e.getCurrentItem().getItemMeta().getDisplayName() + "§c' já está ativo.");
						return;
					}
					EffectManager.effects.put(p, EffectType.POWER);
				} else {
					buy(p, EffectType.POWER);
					return;
				}
				break;
			}
			p.closeInventory();
			p.sendMessage("§aO efeito '" + e.getCurrentItem().getItemMeta().getDisplayName() + "§a' escolhida com sucesso.");
		}
		MPlayer mp = MPlayer.get(p);
		if (e.getInventory().getName().toLowerCase().contains("Jogadores que tentaram dupar") || e.getInventory().getName().contains("§8Drops coletáveis") || e.getInventory().getName().contains("§8" + mp.getFaction().getName() + " - Pontos de spawn")) {
			e.setCancelled(true);
			if (!ScrollerInventory.users.containsKey(p.getUniqueId())) {
				return;
			}
			ScrollerInventory inv = ScrollerInventory.users.get(p.getUniqueId());
			if (!e.getClickedInventory().getName().equals(inv.pages.get(inv.currpage).getName()))
				return;
			if (e.getSlot() == 26) {
				e.setCancelled(true);
				if (inv.currpage >= inv.pages.size() - 1) {
					return;
				} else {
					inv.currpage += 1;
					p.openInventory(inv.pages.get(inv.currpage));
				}
			} else if (e.getSlot() == 18) {
				e.setCancelled(true);
				if (inv.currpage > 0) {
					inv.currpage -= 1;
					p.openInventory(inv.pages.get(inv.currpage));
				}
			}
			if (e.getSlot() == 49) {
				e.setCancelled(true);
				p.closeInventory();
			}
			if (e.getCurrentItem().getType() == Material.SKULL_ITEM){
				String name = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
				p.closeInventory();
				p.chat("/mob " + name);
			}
		}
	}

	public boolean enchant(int i) {
		return new Random().nextInt() < i / 100.0;
	}

	protected boolean buy(Player p, EffectType effect) {
		EffectManager em = Main.getInstance().effects.get(p.getName().toLowerCase());
		ManaModel mm = Main.getInstance().maniacs.get(p.getName());
		if (mm.getMana() >= 100000) {
			mm.withdrawMana(100000);
			em.addEffect(effect);
			p.sendMessage("§eCompra efetuada com sucesso!");
			SelectEffectMenu.open(p);
			return true;
		}
		p.sendMessage("§cVocê não tem mana suficiente!");
		return false;
	}
}
