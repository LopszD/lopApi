package com.wandy.api.stattrack;

import java.util.Arrays;

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

import com.wandy.api.caixas.spawners.Conteúdo;
import com.wandy.api.mobspawner.MobsListener;
import com.wandy.api.utils.Magaiver;
import com.wandy.cash.WandyCash;
import com.wandy.cash.models.PlayerCash;

public class RecompensasMenu implements Listener {
	
	public static void abrirMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 36, "§8Recompensas");
		
		//
		// RECOMPENSA 1
		//
		
		ItemStack a = new ItemStack(Material.MINECART);
		ItemMeta am = a.getItemMeta();
		String cor = "§c";
		if (MagaiverListener.getAbates(p.getItemInHand()) >= 50) {
			a.setType(Material.STORAGE_MINECART);
			cor = "§a";
		}
		am.setLore(Arrays.asList(new String[] { "", "§fRecompensas:", "§7• 5x Caixa Épica", "§7• 1.500 Cash", "", "§7Você precisa de " + cor + "150 de maldições §7para", "§7pegar este recompensa." }));
		am.setDisplayName(cor + "50 Abates");
		a.setItemMeta(am);

		//
		// RECOMPENSA 2
		//
		
		ItemStack a1 = new ItemStack(Material.MINECART);
		ItemMeta am1 = a1.getItemMeta();
		String cor1 = "§c";
		if (MagaiverListener.getAbates(p.getItemInHand()) >= 100) {
			a1.setType(Material.STORAGE_MINECART);

			cor1 = "§a";
		}
		am1.setLore(Arrays.asList(new String[] { "", "§fRecompensas:", "§7• 5x Caixa Lendária", "§7• 3.000 Cash", "§2", "§7Você precisa de " + cor1 + "300 de maldições §7para", "§7pegar este recompensa." }));
		am1.setDisplayName(cor1 + "100 Abates");
		a1.setItemMeta(am1);

		//
		// RECOMPENSA 3
		//
		
		ItemStack a2 = new ItemStack(Material.MINECART);
		ItemMeta am2 = a2.getItemMeta();
		String cor2 = "§c";
		if (MagaiverListener.getAbates(p.getItemInHand()) >= 200) {
			a2.setType(Material.STORAGE_MINECART);
			cor2 = "§a";
		}
		am2.setLore(Arrays.asList(new String[] { "§1", "§fRecompensas:", "§7• 5x Caixa Spawners", "§7• 1x Surpresinha", "§7• 5.000 Cash", "§2", "§7Você precisa de " + cor2 + "700 de maldições §7para", "§7pegar este recompensa." }));
		am2.setDisplayName(cor2 + "200 Abates");
		a2.setItemMeta(am2);
		inv.setItem(31, Conteúdo.itemCreate("§aVoltar", Material.ARROW, 1, (short) 0, Arrays.asList(new String[] { "§7Clique para voltar" })));
		inv.setItem(12, a);
		inv.setItem(13, a1);
		inv.setItem(14, a2);
		
		p.openInventory(inv);
	}

	@EventHandler
	public static void aoClicar(InventoryClickEvent e) {
		if (e.getInventory().getTitle().equals("§8Recompensas")) {
			if (e.getCurrentItem() == null) {
				return;
			}
			if (e.getCurrentItem().getType().equals(Material.AIR)) {
				return;
			}
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			PlayerCash pc = WandyCash.getInstance().getPlayerCash(p.getName());
			if (e.getSlot() == 31) {
				Magaiver.abrirMenu(p);
			}
			if (e.getSlot() == 12) {
				if (p.getItemInHand() == null) {
					p.closeInventory();
					p.sendMessage("§cVocê precisa de um item na sua mão.");
					return;
				}
				if (p.getItemInHand().getType().equals(Material.AIR)) {
					p.closeInventory();
					p.sendMessage("§cVocê precisa de um item na sua mão.");
					return;
				}
				if (!MagaiverListener.checarItem(p.getItemInHand())) {
					p.closeInventory();
					p.sendMessage("§cTipo inválido de item.");
					return;
				}
				if (!MagaiverListener.temStat(p.getItemInHand())) {
					p.closeInventory();
					p.sendMessage("§cEste item não possui o sistema de maldição.");
					return;
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§a")) {
					if (!MobsListener.checarInv(p, Integer.valueOf(1))) {
						p.closeInventory();
						p.sendMessage("§cSeu inventário está cheio, esvazie-o para poder pegar está recompensa.");
						return;
					}
					p.closeInventory();
					int tem = MagaiverListener.getAbates(p.getItemInHand());
					int dps = tem - 150;
					ItemStack al = MagaiverListener.setarConta(p.getItemInHand(), dps);
					p.setItemInHand(al);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "especiais " + p.getName() + " épica 5");
					pc.depositCash(1500);
					p.playSound(p.getLocation(), Sound.BURP, 0.5F, 1.0F);
				}
			}
			if (e.getSlot() == 13) {
				if (p.getItemInHand() == null) {
					p.closeInventory();
					p.sendMessage("§cVocê precisa de um item na sua mão.");
					return;
				}
				if (p.getItemInHand().getType().equals(Material.AIR)) {
					p.closeInventory();
					p.sendMessage("§cVocê precisa de um item na sua mão.");
					return;
				}
				if (!MagaiverListener.checarItem(p.getItemInHand())) {
					p.closeInventory();
					p.sendMessage("§cTipo inválido de item.");
					return;
				}
				if (!MagaiverListener.temStat(p.getItemInHand())) {
					p.closeInventory();
					p.sendMessage("§cEste item não possui o sistema de maldição.");
					return;
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§a")) {
					if (!MobsListener.checarInv(p, Integer.valueOf(1))) {
						p.closeInventory();
						p.sendMessage("§cSeu inventário está cheio, esvazie-o para poder pegar está recompensa.");
						return;
					}
					p.closeInventory();
					int tem = MagaiverListener.getAbates(p.getItemInHand());
					int dps = tem - 300;
					ItemStack al = MagaiverListener.setarConta(p.getItemInHand(), dps);
					p.setItemInHand(al);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "especiais" + p.getName() + " lendária 5");
					pc.depositCash(3000);
					p.playSound(p.getLocation(), Sound.BURP, 0.5F, 1.0F);
				}
			}
			if (e.getSlot() == 14) {
				if (p.getItemInHand() == null) {
					p.closeInventory();
					p.sendMessage("§cVocê precisa de um item na sua mão.");
					return;
				}
				if (p.getItemInHand().getType().equals(Material.AIR)) {
					p.closeInventory();
					p.sendMessage("§cVocê precisa de um item na sua mão.");
					return;
				}
				if (!MagaiverListener.checarItem(p.getItemInHand())) {
					p.closeInventory();
					p.sendMessage("§cTipo inválido de item.");
					return;
				}
				if (!MagaiverListener.temStat(p.getItemInHand())) {
					p.closeInventory();
					p.sendMessage("§cEste item não possui o sistema de maldição.");
					return;
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§a")) {
					if (!MobsListener.checarInv(p, Integer.valueOf(1))) {
						p.closeInventory();
						p.sendMessage("§cSeu inventário está cheio, esvazie-o para poder pegar está recompensa.");
						return;
					}
					p.closeInventory();
					int tem = MagaiverListener.getAbates(p.getItemInHand());
					int dps = tem - 700;
					ItemStack al = MagaiverListener.setarConta(p.getItemInHand(), dps);
					p.setItemInHand(al);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "especiais " + p.getName() + " spawners 5");
					pc.depositCash(5000);
					p.playSound(p.getLocation(), Sound.BURP, 0.5F, 1.0F);
				}
			}
		}
	}
}
