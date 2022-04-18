package com.wandy.api.especiais.listeners;

import com.gmail.nossr50.datatypes.skills.SkillType;
import com.wandy.api.Main;
import com.wandy.api.sql.MySQL;

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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class BoosterExpListener implements Listener {
	
	public static HashMap<String, SkillType> bos = new HashMap<String, SkillType>();
	public static HashMap<String, String> bosd = new HashMap<String, String>();

	public static void abrirMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, "§1§8Selecione um tipo");
		inv.setItem(11, itemCreate("§eEspadas", Material.DIAMOND_SWORD, 1, (short) 0));
		inv.setItem(13, itemCreate("§eArqueiro", Material.BOW, 1, (short) 0));
		inv.setItem(15, itemCreate("§eMineração", Material.DIAMOND_PICKAXE, 1, (short) 0));
		inv.setItem(29, itemCreate("§eEscavação", Material.DIAMOND_HOE, 1, (short) 0));
		inv.setItem(31, itemCreate("§eReparação", Material.ANVIL, 1, (short) 0));
		inv.setItem(33, itemCreate("§eAcrobacia", Material.DIAMOND_BOOTS, 1, (short) 0));
		p.openInventory(inv);
	}

	@EventHandler
	public static void aoInteragir(PlayerInteractEvent e) {
		if (e.getItem() != null) {
			if (!(e.getItem().getType() == Material.AIR)) {
				if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
					if (!(e.getItem().getType() == Material.EXP_BOTTLE)) {
						return;
					}
					if (e.getItem().hasItemMeta()) {
						if (e.getItem().getItemMeta().hasDisplayName()) {
							if (e.getItem().getItemMeta().getDisplayName().contains("§aBooster de Experiência")) {
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
		if (e.getInventory().getTitle().equals("§1§8Confirmação - Booster")) {
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
						if (taBooster(p.getName())) {
							p.closeInventory();
							p.sendMessage("§cVocê já tem um booster ativo!");
							return;
						}
						p.setItemInHand(new ItemStack(Material.AIR));
						SkillType tipo = getTipo(e.getInventory().getItem(13));
						p.closeInventory();
						p.sendMessage("§1");
						p.sendMessage("§a Booster ativado para a habilidade do tipo " + e.getInventory().getItem(13).getItemMeta().getDisplayName().replace("§e", "§a") + ".");
						p.sendMessage("§3");
						p.sendMessage("§a * Lembrando que a duração do booster é 1 hora.");
						p.sendMessage("§2");
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
						addBooster(p.getName(), serealize(tipo));
					}
				}
			}
		}
		if (e.getInventory().getTitle().equals("§1§8Selecione um tipo")) {
			if (e.getCurrentItem() != null) {
				if (!e.getCurrentItem().equals(Material.AIR)) {
					e.setCancelled(true);
					Player p = (Player) e.getWhoClicked();
					if (eDup(p)) {
						p.closeInventory();
						p.sendMessage("§cOcorreu um erro ao concluir está ação. Tente novamente.");
						return;
					}
					if ((e.getSlot() == 29) || (e.getSlot() == 33) || (e.getSlot() == 15) || (e.getSlot() == 31) || (e.getSlot() == 13) || (e.getSlot() == 11)) {
						if (taBooster(p.getName())) {
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

	public static String serealize(SkillType tipo) {
		String nome = "";
		if (tipo == SkillType.SWORDS) {
			nome = "espadas";
		}
		if (tipo == SkillType.ACROBATICS) {
			nome = "acrobacia";
		}
		if (tipo == SkillType.MINING) {
			nome = "mineracao";
		}
		if (tipo == SkillType.EXCAVATION) {
			nome = "escavacao";
		}
		if (tipo == SkillType.REPAIR) {
			nome = "reparacao";
		}
		if (tipo == SkillType.ARCHERY) {
			nome = "arqueiro";
		}
		return nome;
	}

	public static SkillType deserealize(String nome) {
		SkillType tipo = null;
		if (nome == "escavacao") {
			tipo = SkillType.EXCAVATION;
		}
		if (nome == "espadas") {
			tipo = SkillType.SWORDS;
		}
		if (nome == "mineracao") {
			tipo = SkillType.MINING;
		}
		if (nome == "arqueiro") {
			tipo = SkillType.ARCHERY;
		}
		if (nome == "reparacao") {
			tipo = SkillType.REPAIR;
		}
		if (nome == "acrobacia") {
			tipo = SkillType.ACROBATICS;
		}
		return tipo;
	}

	public static void abrirConfirm(Player p, ItemStack i) {
		Inventory inv = Bukkit.createInventory(null, 36, "§1§8Confirmação - Booster");
		inv.setItem(13, i);

		ItemStack C = new ItemStack(Material.WOOL, 1, (short) 5);
		ItemMeta Cm = C.getItemMeta();
		Cm.setDisplayName("§aAceitar");
		C.setItemMeta(Cm);
		List<String> lore = new ArrayList<String>();
		lore.add("§7Ao ativar este item você receberá §f1 hora §7de");
		lore.add("§7experiência em dobro para a habilidade selecionada.");
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

	public static SkillType getTipo(ItemStack i) {
		if (i.getType() == Material.DIAMOND_SWORD) {
			return SkillType.SWORDS;
		}
		if (i.getType() == Material.BOW) {
			return SkillType.ARCHERY;
		}
		if (i.getType() == Material.DIAMOND_BOOTS) {
			return SkillType.ACROBATICS;
		}
		if (i.getType() == Material.ANVIL) {
			return SkillType.REPAIR;
		}
		if (i.getType() == Material.DIAMOND_HOE) {
			return SkillType.EXCAVATION;
		}
		if (i.getType() == Material.DIAMOND_PICKAXE) {
			return SkillType.MINING;
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
		if (p.getItemInHand().getType() == Material.EXP_BOTTLE) {
			i++;
		}
		if (p.getItemInHand().hasItemMeta()) {
			i++;
			if (p.getItemInHand().getItemMeta().hasDisplayName()) {
				i++;
				if (p.getItemInHand().getItemMeta().getDisplayName().contains("§aBooster de Experiência")) {
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
		am.setLore(Arrays.asList("§7Booster de " + nome.replace("§e", "§7"), "§1", "§7Bônus: §f+2x de experiência", "§7Duração: §f1 hora."));
		a.setItemMeta(am);
		return a;
	}

	public static void addBooster(String nome, String tipo) {
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("INSERT INTO BoosterExp (Nome,Nick,Data,Tipo) VALUES (?,?,?,?);");
			ps.setString(1, nome.toLowerCase());
			ps.setString(2, nome);
			ps.setString(3, getDateF());
			ps.setString(4, tipo);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		BoosterExpListener.bos.put(nome, deserealize(tipo));
		int task = new BukkitRunnable() {
			public void run() {
				int p = Integer.valueOf(BoosterExpListener.getData(nome).split(" ")[0].replace("/", ""));
				int m = Integer.valueOf(BoosterExpListener.getDataNow().split(" ")[0].replace("/", ""));
				if (m > p) {
					BoosterExpListener.removerBooster(nome);
					return;
				}
				if (m == p) {
					int p2 = Integer.valueOf(BoosterExpListener.getData(nome).split(" ")[1].replace(":", ""));
					int p3 = Integer.valueOf(BoosterExpListener.getDataNow().split(" ")[1].replace(":", ""));
					if (p3 >= p2) {
						BoosterExpListener.removerBooster(nome);
					}
				}
			}
		}.runTaskTimerAsynchronously(Main.getInstance(), 1200L, 1200L).getTaskId();
		BoosterExpListener.bosd.put(nome, String.valueOf(task) + "@" + getDateF());
	}

	public static String getData(String nome) {
		String data = bosd.get(nome).split("@")[1];
		return data;
	}

	@SuppressWarnings("deprecation")
	public static String getDataNow() {
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		int hora = now.getHours();
		int dps = hora + 1;
		now.setHours(dps);
		String data = format.format(now);
		return data;
	}

	@SuppressWarnings("deprecation")
	public static String getDateF() {
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		int hora = now.getHours();
		int dps = hora + 2;
		now.setHours(dps);
		String data = format.format(now);
		return data;
	}

	public static void removerBooster(String nome) {
		pararTask(nome);
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("DELETE FROM BoosterExp WHERE Nome=?");
			ps.setString(1, nome.toLowerCase());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void pararTask(String nome) {
		int task = getTaskID(nome);
		Bukkit.getScheduler().cancelTask(task);
		bos.remove(nome);
		bosd.remove(nome);
	}

	public static void recuperarTask(String nome) {
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM BoosterExp WHERE Nome=?");
			ps.setString(1, nome.toLowerCase());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String tipo = rs.getString("Tipo");
				String data = rs.getString("Data");
				BoosterExpListener.bos.put(nome, deserealize(tipo));
				int task = new BukkitRunnable() {
					public void run() {
						int p = Integer.valueOf(data.split(" ")[0].replace("/", ""));
						int m = Integer.valueOf(BoosterExpListener.getDataNow().split(" ")[0].replace("/", ""));
						if (m > p) {
							BoosterExpListener.removerBooster(nome);
							return;
						}
						if (m == p) {
							int p2 = Integer.valueOf(data.split(" ")[1].replace(":", ""));
							int p3 = Integer.valueOf(BoosterExpListener.getDataNow().split(" ")[1].replace(":", ""));
							if (p3 >= p2) {
								BoosterExpListener.removerBooster(nome);
							}
						}
					}
				}.runTaskTimerAsynchronously(Main.getInstance(), 20L, 1200L).getTaskId();
				BoosterExpListener.bosd.put(nome, String.valueOf(task) + "@" + data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int getTaskID(String nome) {
		int task = 0;
		String bru = bosd.get(nome);
		task = Integer.valueOf(bru.split("@")[0]).intValue();
		return task;
	}

	public static boolean taBooster(String nome) {
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM BoosterExp WHERE Nome=?");
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

	@EventHandler
	public static void aoSair(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (taBooster(p.getName())) {
			pararTask(p.getName());
		}
	}

	@EventHandler
	public static void aoEntrar(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (taBooster(p.getName())) {
			recuperarTask(p.getName());
		}
	}
}
