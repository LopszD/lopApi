package com.wandy.api.especiais.listeners;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.wandy.api.sql.MySQL;

public class BoosterDropListener implements Listener {
	
	public static HashMap<String, EntityType> bos = new HashMap<String, EntityType>();
	public static HashMap<String, Date> bosd = new HashMap<String, Date>();

	public static void abrirMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, "§8Selecione um tipo");
		ItemStack i = new ItemStack(Material.PAPER);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName("§aInformações");
		im.setLore(Arrays.asList("§fLembrando, o drop é baseado no número de", "§fspawners do tipo do mob dentro", "§fda chunk.", "§3", "§fDuração do booster: §71 hora."));
		i.setItemMeta(im);
		inv.setItem(29, itemCreate("§eBlaze", Material.BLAZE_ROD, 1, (short) 0));
		inv.setItem(33, itemCreate("§eEsqueleto", Material.BONE, 1, (short) 0));
		inv.setItem(15, itemCreate("§eVaca", Material.LEATHER, 1, (short) 0));
		inv.setItem(31, itemCreate("§eGolem", Material.IRON_INGOT, 1, (short) 0));
		inv.setItem(13, itemCreate("§eSlime", Material.SLIME_BALL, 1, (short) 0));
		inv.setItem(22, itemCreate("§eAranha", Material.STRING, 1, (short) 0));
		inv.setItem(21, itemCreate("§eAranha ven.", Material.FERMENTED_SPIDER_EYE, 1, (short) 0));
		inv.setItem(23, itemCreate("§ePorco zumbi", Material.GOLD_NUGGET, 1, (short) 0));
		inv.setItem(11, itemCreate("§eZumbi", Material.ROTTEN_FLESH, 1, (short) 0));
		p.openInventory(inv);
	}

	@EventHandler
	public static void aoInteragir(PlayerInteractEvent e) {
		if (e.getItem() != null) {
			if (!(e.getItem().getType() == Material.AIR)) {
				if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
					if (!(e.getItem().getType() == Material.BLAZE_POWDER)) {
						return;
					}
					if (e.getItem().hasItemMeta()) {
						if (e.getItem().getItemMeta().hasDisplayName()) {
							if (e.getItem().getItemMeta().getDisplayName().contains("§6Booster de Drop")) {
								e.setCancelled(true);
								e.getPlayer().updateInventory();
								abrirMenu(e.getPlayer());
							}
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	@EventHandler
	public static void aoClicar(InventoryClickEvent e) {
		if (e.getInventory().getTitle().equals("§8Confirmação - Booster")) {
			if (e.getCurrentItem() != null) {
				if (!e.getCurrentItem().equals(Material.AIR)) {
					e.setCancelled(true);
					Player p = (Player) e.getWhoClicked();
					if (eDup(p)) {
						p.closeInventory();
						p.sendMessage("§cOcorreu um erro ao concluir está ação. Tente novamente.");
						return;
					}
					if (e.getSlot() == 24) {
						p.closeInventory();
					}
					if (e.getSlot() == 20) {
						if (bos.containsKey(p.getName())) {
							p.closeInventory();
							p.sendMessage("§cVocê já tem um booster ativo!");
							return;
						}
						p.setItemInHand(new ItemStack(Material.AIR));
						EntityType tipo = getTipo(e.getInventory().getItem(13));
						p.closeInventory();
						p.sendMessage("§1");
						p.sendMessage("§a Booster ativado para os mobs do tipo " + e.getInventory().getItem(13).getItemMeta().getDisplayName().replace("§e", "§a") + ".");
						p.sendMessage("§3");
						p.sendMessage("§a * Lembrando que o drop é baseado no número de spawners na chunk.");
						p.sendMessage("§2");
						p.playSound(p.getLocation(), getSound(tipo), 1.0F, 1.0F);
						addBooster(p.getName(), serealize(tipo));
					}
				}
			}
		}
		if (e.getInventory().getTitle().equals("§8Selecione um tipo")) {
			if (e.getCurrentItem() != null) {
				if (!e.getCurrentItem().equals(Material.AIR)) {
					e.setCancelled(true);
					Player p = (Player) e.getWhoClicked();
					if (eDup(p)) {
						p.closeInventory();
						p.sendMessage("§cOcorreu um erro ao concluir está ação. Tente novamente.");
						return;
					}
					if ((e.getSlot() == 29) || (e.getSlot() == 33) || (e.getSlot() == 15) || (e.getSlot() == 31) || (e.getSlot() == 13) || (e.getSlot() == 22) || (e.getSlot() == 21) || (e.getSlot() == 23) || (e.getSlot() == 11)) {
						if (bos.containsKey(p.getName())) {
							p.closeInventory();
							p.sendMessage("§cVocê já está com um booster ativo!");
							return;
						}
						abrirConfirm(p, e.getCurrentItem());
					}
				}
			}
		}
	}

	public static Sound getSound(EntityType tipo) {
		Sound nome = null;
		if (tipo == EntityType.BLAZE) {
			nome = Sound.BLAZE_DEATH;
		}
		if (tipo == EntityType.SKELETON) {
			nome = Sound.SKELETON_DEATH;
		}
		if (tipo == EntityType.COW) {
			nome = Sound.COW_HURT;
		}
		if (tipo == EntityType.IRON_GOLEM) {
			nome = Sound.IRONGOLEM_DEATH;
		}
		if (tipo == EntityType.SLIME) {
			nome = Sound.SLIME_ATTACK;
		}
		if (tipo == EntityType.SPIDER) {
			nome = Sound.SPIDER_DEATH;
		}
		if (tipo == EntityType.CAVE_SPIDER) {
			nome = Sound.SPIDER_DEATH;
		}
		if (tipo == EntityType.PIG_ZOMBIE) {
			nome = Sound.ZOMBIE_PIG_DEATH;
		}
		if (tipo == EntityType.ZOMBIE) {
			nome = Sound.ZOMBIE_DEATH;
		}
		return nome;
	}

	public static String serealize(EntityType tipo) {
		String nome = "";
		if (tipo == EntityType.BLAZE) {
			nome = "blaze";
		}
		if (tipo == EntityType.SKELETON) {
			nome = "esqueleto";
		}
		if (tipo == EntityType.COW) {
			nome = "vaca";
		}
		if (tipo == EntityType.IRON_GOLEM) {
			nome = "golem";
		}
		if (tipo == EntityType.SLIME) {
			nome = "slime";
		}
		if (tipo == EntityType.SPIDER) {
			nome = "aranha";
		}
		if (tipo == EntityType.CAVE_SPIDER) {
			nome = "aranhac";
		}
		if (tipo == EntityType.PIG_ZOMBIE) {
			nome = "pzumbi";
		}
		if (tipo == EntityType.ZOMBIE) {
			nome = "zumbi";
		}
		return nome;
	}

	public static EntityType deserealize(String nome) {
		EntityType tipo = null;
		if (nome == "blaze") {
			tipo = EntityType.BLAZE;
		}
		if (nome == "esqueleto") {
			tipo = EntityType.SKELETON;
		}
		if (nome == "vaca") {
			tipo = EntityType.COW;
		}
		if (nome == "golem") {
			tipo = EntityType.IRON_GOLEM;
		}
		if (nome == "slime") {
			tipo = EntityType.SLIME;
		}
		if (nome == "aranha") {
			tipo = EntityType.SPIDER;
		}
		if (nome == "aranhac") {
			tipo = EntityType.CAVE_SPIDER;
		}
		if (nome == "pzumbi") {
			tipo = EntityType.PIG_ZOMBIE;
		}
		if (nome == "zumbi") {
			tipo = EntityType.ZOMBIE;
		}
		return tipo;
	}

	public static void abrirConfirm(Player p, ItemStack i) {
		Inventory inv = Bukkit.createInventory(null, 36, "§8Confirmação - Booster");
		inv.setItem(13, i);

		ItemStack C = new ItemStack(Material.WOOL, 1, (short) 5);
		ItemMeta Cm = C.getItemMeta();
		Cm.setDisplayName("§aAceitar");
		C.setItemMeta(Cm);
		List<String> lore = new ArrayList<String>();
		lore.add("§7Ao ativar este item você receberá §f1 hora §7de");
		lore.add("§7drops em dobro para o mob selecionado.");
		Cm.setLore(lore);
		C.setItemMeta(Cm);
		inv.setItem(20, C);

		ItemStack C2 = new ItemStack(Material.WOOL, 1, (short) 14);
		ItemMeta Cm2 = C2.getItemMeta();
		Cm2.setDisplayName("§cNegar");
		C2.setItemMeta(Cm2);
		List<String> lore1 = new ArrayList<String>();
		lore1.add("§7Cancelar está operação.");
		Cm2.setLore(lore1);
		C2.setItemMeta(Cm2);
		inv.setItem(24, C2);

		p.openInventory(inv);
	}

	public static EntityType getTipo(ItemStack i) {
		if (i.getType() == Material.BLAZE_ROD) {
			return EntityType.BLAZE;
		}
		if (i.getType() == Material.BONE) {
			return EntityType.SKELETON;
		}
		if (i.getType() == Material.LEATHER) {
			return EntityType.COW;
		}
		if (i.getType() == Material.IRON_INGOT) {
			return EntityType.IRON_GOLEM;
		}
		if (i.getType() == Material.SLIME_BALL) {
			return EntityType.SLIME;
		}
		if (i.getType() == Material.STRING) {
			return EntityType.SPIDER;
		}
		if (i.getType() == Material.FERMENTED_SPIDER_EYE) {
			return EntityType.CAVE_SPIDER;
		}
		if (i.getType() == Material.GOLD_NUGGET) {
			return EntityType.PIG_ZOMBIE;
		}
		if (i.getType() == Material.ROTTEN_FLESH) {
			return EntityType.ZOMBIE;
		}
		return null;
	}

	public static boolean eDup(Player p) {
		if (p.getItemInHand() == null) {
			return true;
		}
		if (p.getItemInHand().getType() == Material.AIR) {
			return true;
		}
		int i = 0;
		if (p.getItemInHand().getType() == Material.BLAZE_POWDER) {
			i++;
		}
		if (p.getItemInHand().hasItemMeta()) {
			i++;
			if (p.getItemInHand().getItemMeta().hasDisplayName()) {
				i++;
				if (p.getItemInHand().getItemMeta().getDisplayName().contains("§6Booster de Drop")) {
					i++;
				}
			}
		}
		if (i < 4) {
			return true;
		}
		return false;
	}

	public static ItemStack itemCreate(String nome, Material m, int qnt, short damage) {
		ItemStack a = new ItemStack(m, qnt, damage);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName(nome);
		am.setLore(Arrays.asList("§7Booster de " + nome.replace("§e", "§7"), "§1", "§7Bônus: §f+2x de drops", "§7Duração: §f1 hora."));
		a.setItemMeta(am);
		return a;
	}

	@SuppressWarnings("deprecation")
	public static void addBooster(String nome, String tipo) {
		Date now = new Date();
		int min = now.getHours() + 1;
		now.setHours(min);
		bos.put(nome, deserealize(tipo));
		bosd.put(nome, now);
	}

	public static String getData(String nome) {
		Date now = bosd.get(nome);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return format.format(now);
	}

	public static void removerBooster(String nome) {
		if (bos.containsKey(nome)) {
			bos.remove(nome);
		}
		if (bosd.containsKey(nome)) {
			bosd.remove(nome);
		}
	}

	public static boolean taBoost(String nome) {
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM BoosterDrop WHERE Nome=?");
			ps.setString(1, nome.toLowerCase());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
