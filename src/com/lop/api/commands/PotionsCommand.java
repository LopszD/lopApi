package com.wandy.api.commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionsCommand implements CommandExecutor, Listener {
	
	public static ArrayList<String> efeitos = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("potions")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("wandy.potions")) {
			p.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		abrirMenu(p);
		return false;
	}
	public static void abrirMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, "§8Poções dos usuários");
		inv.setItem(10, getPot("Regeneração I", 8193));
		inv.setItem(11, getPot("Regeneração II", 8225));
		inv.setItem(12, getPot("Velocidade I", 8194));
		inv.setItem(13, getPot("Velocidade II", 8226));
		inv.setItem(19, getPot("Força I", 8201));
		inv.setItem(20, getPot("Força II", 8233));
		inv.setItem(21, getPot("Super Pulo I", 8203));
		inv.setItem(22, getPot("Super Pulo II", 8235));
		inv.setItem(28, getPot("Registência ao Fogo", 8227));
		inv.setItem(29, getPot("Invisibilidade", 8238));
		inv.setItem(30, getPot("Fraqueza", 8232));
		inv.setItem(31, getPot("Lentidão", 8234));
		ItemStack a = new ItemStack(Material.SKULL_ITEM);
		a.setDurability((short) 3);
		SkullMeta am = (SkullMeta) a.getItemMeta();
		int i = 0;
		List<String> lore = new ArrayList<String>();
		for (String li : efeitos) {
			i++;
			lore.add("§7" + li);
		}
		if (i == 0) {
			lore.add("§7Nenhum efeito ativo.");
		}
		a.setAmount(i);
		am.setDisplayName("§aEfeitos (" + i + ")");
		am.setLore(lore);
		am.setOwner("yThaiis_");
		a.setItemMeta(am);
		inv.setItem(24, a);
		ItemStack b = new ItemStack(Material.BARRIER);
		ItemMeta bm = b.getItemMeta();
		bm.setDisplayName("§cRemover todos");
		b.setItemMeta(bm);
		inv.setItem(25, b);
		p.openInventory(inv);
	}

	public static ItemStack getPot(String nome, int d) {
		ItemStack i = new ItemStack(Material.POTION, 1, (short) d);
		ItemMeta im = i.getItemMeta();
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_DESTROYS });
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_UNBREAKABLE });
		String cor = "§c";
		if (efeitos.contains(nome)) {
			cor = "§a";
		}
		im.setDisplayName(cor + nome);
		i.setItemMeta(im);
		return i;
	}

	@EventHandler
	public static void aoClicar(InventoryClickEvent e) {
		if (e.getInventory().getTitle().contains("§8Poções dos usuários")) {
			if (e.getCurrentItem() == null) {
				return;
			}
			if (e.getCurrentItem().getType() == Material.AIR) {
				return;
			}
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.POTION && e.getSlot() >= 10 && e.getSlot() <= 31) {
				Player p = (Player) e.getWhoClicked();
				if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§c")) {
					String nome = e.getCurrentItem().getItemMeta().getDisplayName().replace("§c", "");
					PotionsCommand.efeitos.add(nome);
					int i = 1;
					if (nome.contains("II")) {
						i = 2;
					}
					for (Player todos : Bukkit.getOnlinePlayers()) {
						todos.addPotionEffect(new PotionEffect(getTipo(nome), 90000, i));
					}
					abrirMenu(p);
					return;
				}
				String nome = e.getCurrentItem().getItemMeta().getDisplayName().replace("§a", "");
				PotionsCommand.efeitos.remove(nome);
				for (Player todos2 : Bukkit.getOnlinePlayers()) {
					todos2.removePotionEffect(getTipo(nome));
				}
				abrirMenu(p);
			}
			if (e.getSlot() == 25 && PotionsCommand.efeitos.size() >= 1) {
				Player p = (Player) e.getWhoClicked();
				for (String nome : PotionsCommand.efeitos) {
					for (Player todos : Bukkit.getOnlinePlayers()) {
						todos.removePotionEffect(getTipo(nome));
					}
				}
				PotionsCommand.efeitos.clear();
				abrirMenu(p);
			}
		}
	}

	public static PotionEffectType getTipo(String nome) {
		PotionEffectType pt = null;
		if (nome.contains("Regeneração")) {
			pt = PotionEffectType.REGENERATION;
		}
		if (nome.contains("Velocidade")) {
			pt = PotionEffectType.SPEED;
		}
		if (nome.contains("Força")) {
			pt = PotionEffectType.INCREASE_DAMAGE;
		}
		if (nome.contains("Super Pulo")) {
			pt = PotionEffectType.JUMP;
		}
		if (nome.contains("Fogo")) {
			pt = PotionEffectType.FIRE_RESISTANCE;
		}
		if (nome.contains("Invisibilidade")) {
			pt = PotionEffectType.INVISIBILITY;
		}
		if (nome.contains("Fraqueza")) {
			pt = PotionEffectType.WEAKNESS;
		}
		if (nome.contains("Lentidão")) {
			pt = PotionEffectType.SLOW;
		}
		return pt;
	}

	@EventHandler
	public static void aoMorrer(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		if (efeitos.size() >= 1) {
			for (String nome : efeitos) {
				int i = 1;
				if (nome.contains("II")) {
					i = 2;
				}
				p.addPotionEffect(new PotionEffect(getTipo(nome), 90000, i));
			}
		}
	}

	@EventHandler
	public static void aoBeber(PlayerBucketEmptyEvent e) {
		if (e.getBucket() == Material.MILK_BUCKET) {
			Player p = e.getPlayer();
			if (efeitos.size() >= 1) {
				for (String nome : efeitos) {
					int i = 1;
					if (nome.contains("II")) {
						i = 2;
					}
					p.addPotionEffect(new PotionEffect(getTipo(nome), 90000, i));
				}
			}
		}
	}

	@EventHandler
	public static void aoSair(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (efeitos.size() >= 1) {
			for (String nome : efeitos) {
				p.removePotionEffect(getTipo(nome));
			}
		}
	}

	@EventHandler
	public static void aoEntrar(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (efeitos.size() >= 1) {
			for (String nome : efeitos) {
				int i = 1;
				if (nome.contains("II")) {
					i = 2;
				}
				p.addPotionEffect(new PotionEffect(getTipo(nome), 90000, i));
			}
		}
	}
}
