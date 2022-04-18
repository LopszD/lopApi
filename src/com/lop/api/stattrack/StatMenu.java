package com.wandy.api.stattrack;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.wandy.economy.API_Economy;

public class StatMenu implements Listener {
	
	public static NumberFormat numberFormat;
	public static int Novo = 10000;

	public static void abrirMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, "�8Maldi��o");

		ItemStack a = new ItemStack(Material.NETHER_STAR);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName("�bFabricar novo item");
		String cor = "�c";
		String para = "�cVoc� n�o tem coins suficientes.";
		if (API_Economy.getMoney(p.getName()) >= Novo) {
			para = "�aClique para fabricar!";
			cor = "�a";
		}
		am.setLore(Arrays.asList(new String[] { "�7Adicionar � um novo item", "�7uma contagem de �babates�7.", "", "�7Voc� precisa de " + cor + format(Novo) + " coins �7para", "�7criar um novo item.", para }));
		a.setItemMeta(am);

		ItemStack b = new ItemStack(Material.PAPER);
		ItemMeta bm = b.getItemMeta();
		bm.setDisplayName("�eInforma��es");
		bm.setLore(Arrays.asList(new String[] { "", "�fLista de itens suportados:", " �7� Espadas", " �7� Machados", "�3", "�fLista de recompensas:", " �7� Caixas", " �7� Cash", "" }));
		b.setItemMeta(bm);

		ItemStack c = new ItemStack(Material.STORAGE_MINECART);
		ItemMeta cm = c.getItemMeta();
		cm.setDisplayName("�6Recompensas");
		cm.setLore(Arrays.asList(new String[] { "�7Troque seus abates por recompensas,", "�7resetando-�s, assim que a troca", "�7for efetuada." }));
		c.setItemMeta(cm);

		inv.setItem(11, a);
		inv.setItem(13, b);
		inv.setItem(15, c);
		p.openInventory(inv);
	}

	@EventHandler
	public static void aoClicar(InventoryClickEvent e) {
		if (e.getInventory().getTitle().equals("�8Maldi��o")) {
			if (e.getCurrentItem() == null) {
				return;
			}
			if (e.getCurrentItem().getType().equals(Material.AIR)) {
				return;
			}
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			if (e.getSlot() == 11) {
				if (((String) e.getCurrentItem().getItemMeta().getLore().get(5)).contains("�aClique para fabricar!")) {
					if (p.getItemInHand() == null) {
						p.closeInventory();
						p.sendMessage("�cVoc� precisa de um item na sua m�o.");
						return;
					}
					if (p.getItemInHand().getType().equals(Material.AIR)) {
						p.closeInventory();
						p.sendMessage("�cVoc� precisa de um item na sua m�o.");
						return;
					}
					if (!MagaiverListener.checarItem(p.getItemInHand())) {
						p.closeInventory();
						p.sendMessage("�cTipo inv�lido de item.");
						return;
					}
					if (MagaiverListener.temStat(p.getItemInHand())) {
						p.closeInventory();
						p.sendMessage("�cEste item j� possui o sistema de maldi��o.");
						return;
					}
					Confirma��oMenu.abrirMenu(p);
				}
			}
			if (e.getSlot() == 15) {
				if (p.getItemInHand() == null) {
					p.closeInventory();
					p.sendMessage("�cVoc� precisa de um item na sua m�o.");
					return;
				}
				if (p.getItemInHand().getType().equals(Material.AIR)) {
					p.closeInventory();
					p.sendMessage("�cVoc� precisa de um item na sua m�o.");
					return;
				}
				if (!MagaiverListener.checarItem(p.getItemInHand())) {
					p.closeInventory();
					p.sendMessage("�cTipo inv�lido de item.");
					return;
				}
				if (!MagaiverListener.temStat(p.getItemInHand())) {
					p.closeInventory();
					p.sendMessage("�cEste item n�o possui o sistema de maldi��o.");
					return;
				}
				RecompensasMenu.abrirMenu(p);
			}
		}
	}

	public static String format(double value) {
		numberFormat = NumberFormat.getNumberInstance(Locale.forLanguageTag("en-US"));
		if (value <= 1.0D) {
			return numberFormat.format(value).concat(" ").concat("");
		}
		return numberFormat.format(value).concat(" ").concat("").replace(",", ".").replace(" ", "");
	}
}
