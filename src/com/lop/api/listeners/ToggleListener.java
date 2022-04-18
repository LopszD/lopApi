package com.wandy.api.listeners;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.wandy.api.commands.FakeCommand;
import com.wandy.api.commands.VanishCommand;
import com.wandy.api.sql.Manager;
import com.wandy.economy.WandyEconomy;
import com.wandy.economy.objects.PlayerObj;

public class ToggleListener implements Listener {
	
	public static ArrayList<String> canceltell;
	public static ArrayList<String> cancelglobal;
	public static ArrayList<String> canceltpa;
	public static ArrayList<String> cancelcoins;
	public static ArrayList<String> cancelanuncio;

	static {
		ToggleListener.canceltell = new ArrayList<String>();
		ToggleListener.cancelglobal = new ArrayList<String>();
		ToggleListener.canceltpa = new ArrayList<String>();
		ToggleListener.cancelcoins = new ArrayList<String>();
		ToggleListener.cancelanuncio = new ArrayList<String>();
	}

	public static void abrirMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, "§8Preferências");
		String ts = "§a";
		String gs = "§a";
		String tps = "§a";
		String cs = "§a";
		String an = "§a";
		String es = "§a";
		String ds = "§c";
		if (canceltell.contains(p.getName())) {
			ts = "§c";
		}
		if (cancelglobal.contains(p.getName())) {
			gs = "§c";
		}
		if (canceltpa.contains(p.getName())) {
			tps = "§c";
		}
		if (cancelcoins.contains(p.getName())) {
			cs = "§c";
		}
		if (cancelanuncio.contains(p.getName())) {
			an = "§c";
		}
		if (EvScrollListener.semscroll.contains(p.getName())) {
			es = "§c";
		}
		if (VanishCommand.vanish.contains(p.getName())) {
			ds = "§a";
		}
		
		//
		// TELL
		//
		
		ItemStack t = new ItemStack(Material.PAPER);
		if (ts.equals("§a")) {
			t.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		}
		ItemMeta tm = t.getItemMeta();
		if (ts.equals("§a")) {
			tm.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		}
		tm.setDisplayName(ts + "Mensagens privadas");
		tm.setLore(Arrays.asList(new String[] { "§7Receber mensagens privadas.", "§1", "§fEstado: " + ts.replace("§c", "§cDesligado").replace("§a", "§aLigado") }));
		t.setItemMeta(tm);
		inv.setItem(10, t);
		
		//
		// CHAT GLOBAL
		//
		
		ItemStack g = new ItemStack(Material.BOOK_AND_QUILL);
		if (gs.equals("§a")) {
			g.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		}
		ItemMeta gm = g.getItemMeta();
		if (gs.equals("§a")) {
			gm.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		}
		gm.setDisplayName(gs + "Ver chat global");
		gm.setLore(Arrays.asList(new String[] { "§7Receber as mensagens do chat global", "§7em todos os servidores.", "§1", "§fEstado: " + gs.replace("§c", "§cDesligado").replace("§a", "§aLigado") }));
		g.setItemMeta(gm);
		inv.setItem(11, g);
		
		//
		// TPA
		//
		
		ItemStack tp = new ItemStack(Material.ENDER_PEARL);
		if (tps.equals("§a")) {
			tp.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		}
		ItemMeta tpm = tp.getItemMeta();
		if (tps.equals("§a")) {
			tpm.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		}
		tpm.setDisplayName(tps + "Pedidos de teleporte");
		tpm.setLore(Arrays.asList(new String[] { "§7Receber pedidos de teleporte.", "§1", "§fEstado: " + tps.replace("§c", "§cDesligado").replace("§a", "§aLigado") }));
		tp.setItemMeta(tpm);
		inv.setItem(12, tp);
		
		//
		// COINS PAY
		//
		
		ItemStack c = new ItemStack(Material.GOLD_NUGGET);
		if (cs.equals("§a")) {
			c.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		}
		ItemMeta cm = c.getItemMeta();
		if (cs.equals("§a")) {
			cm.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		}
		cm.setDisplayName(cs + "Recebimento de coins");
		cm.setLore(Arrays.asList(new String[] { "§7Receber coins enviados pelos", "§7jogadores.", "§1", "§fEstado: " + cs.replace("§c", "§cDesligado").replace("§a", "§aLigado") }));
		c.setItemMeta(cm);
		inv.setItem(13, c);
		
		//
		// STATUS BAR
		//
		
		ItemStack e = new ItemStack(Material.BLAZE_ROD);
		if (es.equals("§a")) {
			e.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		}
		ItemMeta em = e.getItemMeta();
		if (es.equals("§a")) {
			em.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		}
		em.setDisplayName(es + "Informações jogad. na StatusBar");
		em.setLore(Arrays.asList(new String[] { "§7Ver as informações dos jogadores na", "§7sua StatusBar.", "§1", "§fEstado: " + es.replace("§c", "§cDesligado").replace("§a", "§aLigado") }));
		e.setItemMeta(em);
		inv.setItem(14, e);
		
		//
		// ANÚNCIOS
		//
		
		ItemStack e1 = new ItemStack(Material.BOOK_AND_QUILL);
		if (an.equals("§a")) {
			e1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		}
		ItemMeta em1 = e1.getItemMeta();
		if (an.equals("§a")) {
			em1.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		}
		em1.setDisplayName(an + "Recebimento de anúncios");
		em1.setLore(Arrays.asList(new String[] { "§7Receber anúncios enviados do", "§7mercado e caixas.", "§1", "§fEstado: " + an.replace("§c", "§cDesligado").replace("§a", "§aLigado") }));
		e1.setItemMeta(em1);
		inv.setItem(15, e1);
		
		//
		// VANISH
		//
		
		if (p.hasPermission("wandy.vanish")) {
			ItemStack d = new ItemStack(Material.FEATHER);
			if (ds.equals("§a")) {
				d.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
			}
			ItemMeta dm = d.getItemMeta();
			if (ds.equals("§a")) {
				dm.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
			}
			dm.setDisplayName(ds + "Invisibilidade");
			dm.setLore(Arrays.asList(new String[] { "§7Outros jogadores verem você.", "§1", "§fEstado: " + ds.replace("§c", "§cDesligado").replace("§a", "§aLigado") }));
			d.setItemMeta(dm);
			inv.setItem(16, d);
		}
		p.openInventory(inv);
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (!e.getInventory().getName().contains("§8Preferências")) {
			return;
		}
		Player p = (Player) e.getWhoClicked();
		e.setCancelled(false);
		if (e.getCurrentItem() == null) {
			return;
		}
		if (e.getSlot() == 10) {
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aMensagens privadas")) {
				e.setCancelled(true);
				int g = Integer.valueOf(Manager.pegarPrefs(p.getName(), "GLOBAL").intValue()).intValue();
				int tp = Integer.valueOf(Manager.pegarPrefs(p.getName(), "TPA").intValue()).intValue();
				int c = Integer.valueOf(Manager.pegarPrefs(p.getName(), "COINS").intValue()).intValue();
				int v = Integer.valueOf(Manager.pegarPrefs(p.getName(), "VANISH").intValue()).intValue();
				Manager.setarPrefs(p.getName(), 1, g, tp, c, v);
				canceltell.add(p.getName());
				abrirMenu(p);
				return;
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cMensagens privadas")) {
				e.setCancelled(true);
				int g = Integer.valueOf(Manager.pegarPrefs(p.getName(), "GLOBAL").intValue()).intValue();
				int tp = Integer.valueOf(Manager.pegarPrefs(p.getName(), "TPA").intValue()).intValue();
				int c = Integer.valueOf(Manager.pegarPrefs(p.getName(), "COINS").intValue()).intValue();
				int v = Integer.valueOf(Manager.pegarPrefs(p.getName(), "VANISH").intValue()).intValue();
				Manager.setarPrefs(p.getName(), 0, g, tp, c, v);
				canceltell.remove(p.getName());
				abrirMenu(p);
				return;
			}
		}
		if (e.getSlot() == 11) {
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aVer chat global")) {
				e.setCancelled(true);
				int g = Integer.valueOf(Manager.pegarPrefs(p.getName(), "TELL").intValue()).intValue();
				int tp = Integer.valueOf(Manager.pegarPrefs(p.getName(), "TPA").intValue()).intValue();
				int c = Integer.valueOf(Manager.pegarPrefs(p.getName(), "COINS").intValue()).intValue();
				int v = Integer.valueOf(Manager.pegarPrefs(p.getName(), "VANISH").intValue()).intValue();
				Manager.setarPrefs(p.getName(), g, 1, tp, c, v);
				cancelglobal.add(p.getName());
				abrirMenu(p);
				return;
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cVer chat global")) {
				e.setCancelled(true);
				int g = Integer.valueOf(Manager.pegarPrefs(p.getName(), "TELL").intValue()).intValue();
				int tp = Integer.valueOf(Manager.pegarPrefs(p.getName(), "TPA").intValue()).intValue();
				int c = Integer.valueOf(Manager.pegarPrefs(p.getName(), "COINS").intValue()).intValue();
				int v = Integer.valueOf(Manager.pegarPrefs(p.getName(), "VANISH").intValue()).intValue();
				Manager.setarPrefs(p.getName(), g, 0, tp, c, v);
				cancelglobal.remove(p.getName());
				abrirMenu(p);
				return;
			}
		}
		if (e.getSlot() == 12) {
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aPedidos de teleporte")) {
				e.setCancelled(true);
				int g = Integer.valueOf(Manager.pegarPrefs(p.getName(), "TELL").intValue()).intValue();
				int tp = Integer.valueOf(Manager.pegarPrefs(p.getName(), "GLOBAL").intValue()).intValue();
				int c = Integer.valueOf(Manager.pegarPrefs(p.getName(), "COINS").intValue()).intValue();
				int v = Integer.valueOf(Manager.pegarPrefs(p.getName(), "VANISH").intValue()).intValue();
				Manager.setarPrefs(p.getName(), g, tp, 1, c, v);
				canceltpa.add(p.getName());
				abrirMenu(p);
				return;
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cPedidos de teleporte")) {
				e.setCancelled(true);
				int g = Integer.valueOf(Manager.pegarPrefs(p.getName(), "TELL").intValue()).intValue();
				int tp = Integer.valueOf(Manager.pegarPrefs(p.getName(), "GLOBAL").intValue()).intValue();
				int c = Integer.valueOf(Manager.pegarPrefs(p.getName(), "COINS").intValue()).intValue();
				int v = Integer.valueOf(Manager.pegarPrefs(p.getName(), "VANISH").intValue()).intValue();
				Manager.setarPrefs(p.getName(), g, tp, 0, c, v);
				canceltpa.remove(p.getName());
				abrirMenu(p);
				return;
			}
		}
		if (e.getSlot() == 13) {
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aRecebimento de coins")) {
				e.setCancelled(true);
				int g = Integer.valueOf(Manager.pegarPrefs(p.getName(), "TELL").intValue()).intValue();
				int tp = Integer.valueOf(Manager.pegarPrefs(p.getName(), "GLOBAL").intValue()).intValue();
				int c = Integer.valueOf(Manager.pegarPrefs(p.getName(), "TPA").intValue()).intValue();
				int v = Integer.valueOf(Manager.pegarPrefs(p.getName(), "VANISH").intValue()).intValue();
				Manager.setarPrefs(p.getName(), g, tp, c, 1, v);
				cancelcoins.add(p.getName());
				PlayerObj po = WandyEconomy.getPlugin().getPlayerManager().getPlayer(p.getName());
				po.setMoneyPay(false);
				abrirMenu(p);
				return;
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cRecebimento de coins")) {
				e.setCancelled(true);
				int g = Integer.valueOf(Manager.pegarPrefs(p.getName(), "TELL").intValue()).intValue();
				int tp = Integer.valueOf(Manager.pegarPrefs(p.getName(), "GLOBAL").intValue()).intValue();
				int c = Integer.valueOf(Manager.pegarPrefs(p.getName(), "TPA").intValue()).intValue();
				int v = Integer.valueOf(Manager.pegarPrefs(p.getName(), "VANISH").intValue()).intValue();
				Manager.setarPrefs(p.getName(), g, tp, c, 0, v);
				cancelcoins.remove(p.getName());
				PlayerObj po = WandyEconomy.getPlugin().getPlayerManager().getPlayer(p.getName());
				po.setMoneyPay(true);
				abrirMenu(p);
				return;
			}
		}
		if (e.getSlot() == 14) {
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aInformações jogad. na StatusBar")) {
				e.setCancelled(true);
				EvScrollListener.semscroll.add(p.getName());
				abrirMenu(p);
				return;
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cInformações jogad. na StatusBar")) {
				e.setCancelled(true);
				EvScrollListener.semscroll.remove(p.getName());
				abrirMenu(p);
				return;
			}
		}
		if (e.getSlot() == 15) {
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aRecebimento de anúncios")) {
				e.setCancelled(true);
				cancelanuncio.add(p.getName());
				abrirMenu(p);
				return;
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cRecebimento de anúncios")) {
				e.setCancelled(true);
				cancelanuncio.remove(p.getName());
				abrirMenu(p);
				return;
			}
		}
		if (e.getSlot() == 16) {
			if (FakeCommand.temFake(p.getName())) {
				e.setCancelled(true);
				p.sendMessage("§cVocê não pode utilizar está função enquanto estiver de fake.");
				return;
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cInvisibilidade")) {
				e.setCancelled(true);
				VanishCommand.vanish.add(p.getName());
				final int g = Manager.pegarPrefs(p.getName(), "TELL");
				final int tp = Manager.pegarPrefs(p.getName(), "GLOBAL");
				final int c = Manager.pegarPrefs(p.getName(), "TPA");
				final int d = Manager.pegarPrefs(p.getName(), "COINS");
				Manager.setarPrefs(p.getName(), g, tp, c, d, 1);
				for (final Player todos : Bukkit.getOnlinePlayers()) {
					if (!todos.hasPermission("whyze.vanish")) {
						todos.hidePlayer(p);
					}
				}
				abrirMenu(p);
				return;
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aInvisibilidade")) {
				e.setCancelled(true);
				VanishCommand.vanish.remove(p.getName());
				final int g = Manager.pegarPrefs(p.getName(), "TELL");
				final int tp = Manager.pegarPrefs(p.getName(), "GLOBAL");
				final int c = Manager.pegarPrefs(p.getName(), "TPA");
				final int d = Manager.pegarPrefs(p.getName(), "COINS");
				Manager.setarPrefs(p.getName(), g, tp, c, d, 0);
				for (final Player todos : Bukkit.getOnlinePlayers()) {
					todos.showPlayer(p);
				}
				abrirMenu(p);
				return;
			}
		}
		e.getSlot();
		if (e.getCurrentItem() == null) {
			return;
		}
		e.setCancelled(true);
	}
}
