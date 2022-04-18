package com.wandy.api.dúvida;

import java.util.Arrays;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class CentralD implements Listener {
	
	public static void abrirMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, "§8Sistema de Dúvidas");
		ItemStack a = new ItemStack(Material.EMPTY_MAP);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName("§eEnviar uma dúvida");
		am.setLore(Arrays.asList("§7Clique para enviar uma dúvida", "§7para a nossa equipe respondê-la."));
		a.setItemMeta(am);
		inv.setItem(11, a);
		if (DAPI.temDuvida(p.getName())) {
			ItemStack b = new ItemStack(Material.MAP);
			ItemMeta bm = b.getItemMeta();
			bm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			bm.addItemFlags(ItemFlag.HIDE_DESTROYS);
			bm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			bm.addItemFlags(ItemFlag.HIDE_PLACED_ON);
			bm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			bm.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
			bm.setDisplayName("§eVer a sua dúvida");
			String tr = "§cNão";
			String ak = "§eESPERANDO";
			if (DAPI.taRespondida(p.getName())) {
				tr = "§aSim";
				ak = "§cFECHADO";
			}
			bm.setLore(Arrays.asList("§7Clique esquerdo §fpara ver a dúvida§7.", "§7Clique direito §fpara deletá-la§7.", "", "§7Status: " + ak, "§7Respondida: " + tr));
			b.setItemMeta(bm);
			inv.setItem(11, b);
		}
		ItemStack b = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta bm = (SkullMeta) b.getItemMeta();
		bm.setOwner(p.getName());
		bm.setDisplayName("§aInformações pessoais");
		bm.setLore(Arrays.asList("", "§f Dúvidas: ", "§f • Criadas: §7" + DAPI.getCriadas(p.getName()), "§f • Última: §7" + DAPI.getUltima(p.getName()), ""));
		if (p.hasPermission("whyze.equipe")) {
			bm.setLore(Arrays.asList("", "§f Dúvidas: ", "§f • Criadas: §7" + DAPI.getCriadas(p.getName()), "§f • Última: §7" + DAPI.getUltima(p.getName()), "§f • Respondidas: §e" + DAPI.getRespondidas(p.getName()), ""));
		}
		b.setItemMeta(bm);
		inv.setItem(13, b);
		if (p.hasPermission("wandy.equipe")) {
			ItemStack c = new ItemStack(Material.BOOK);
			ItemMeta cm = c.getItemMeta();
			cm.setDisplayName("§eResponder as dúvidas");
			cm.setLore(Arrays.asList("§7Responda as dúvidas dos usuários no momento.", "§7", "§7Disponíveis: §f" + DAPI.getDuvidas()));
			c.setItemMeta(cm);
			inv.setItem(15, c);
		}
		p.openInventory(inv);
	}

	@EventHandler
	public static void aoClicar(InventoryClickEvent e) {
		if (e.getInventory().getTitle().equals("§8Sistema de Dúvidas")) {
			if (e.getCurrentItem() != null) {
				if (!e.getCurrentItem().getType().equals(Material.AIR)) {
					e.setCancelled(true);
					Player p = (Player) e.getWhoClicked();
					if (e.getSlot() == 11) {
						if (e.getClick().equals(ClickType.LEFT)) {
							if (!DAPI.temDuvida(p.getName())) {
								p.closeInventory();
								if (step1.containsKey(p.getName())) {
									p.sendMessage("§cVocê já está no processo de criação. Digite 'cancelar' para cancelar o processo.");
									return;
								}
								step1.put(p.getName(), "§");
								p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 1.0F);
								p.sendMessage("§1");
								p.sendMessage("§aDigite uma palavra para ser o título da dúvida.");
								p.sendMessage("§7Caso queira cancelar, responda 'cancelar'.");
								p.sendMessage("§2");
								return;
							}
							p.closeInventory();
							String prefix = "§f";
							if (DAPI.taRespondida(p.getName())) {
								String prefix1 = "§f";
								p.sendMessage("§1");
								p.sendMessage("§a Informações sobre sua dúvida:");
								p.sendMessage("§4");
								p.sendMessage("§a   Nick: §f" + prefix + p.getName());
								p.sendMessage("§a   Título: §f" + DAPI.getTitulo(p.getName()));
								p.sendMessage("§a   Mensagem: §f" + DAPI.getDuvida(p.getName()));
								p.sendMessage("§a   Criada: §f" + DAPI.getCDATA(p.getName()));
								p.sendMessage("§a   Status: §cFECHADO");
								p.sendMessage("§a   Respondido por: §f" + prefix1 + DAPI.getRespondeu(p.getName()));
								p.sendMessage("§a   Resposta: §f" + DAPI.getResposta(p.getName()));
								p.sendMessage("§a   Respondida: §f" + DAPI.getRDATA(p.getName()));
								p.sendMessage("§2");
								return;
							}
							p.sendMessage("§1");
							p.sendMessage("§a Informações sobre sua dúvida:");
							p.sendMessage("§4");
							p.sendMessage("§a   Nick: §f" + prefix + p.getName());
							p.sendMessage("§a   Título: §f" + DAPI.getTitulo(p.getName()));
							p.sendMessage("§a   Mensagem: §f" + DAPI.getDuvida(p.getName()));
							p.sendMessage("§a   Criada: §f" + DAPI.getCDATA(p.getName()));
							p.sendMessage("§a   Status: §eESPERANDO");
							p.sendMessage("§3");
							return;
						}
						if (e.getClick().equals(ClickType.RIGHT)) {
							if (!DAPI.temDuvida(p.getName())) {
								return;
							}
							p.closeInventory();
							p.sendMessage("§aDúvida deletada com sucesso.");
							DAPI.deletarDuvida(p.getName());
						}
					}
					if (e.getSlot() == 15) {
						p.closeInventory();
						DuvidaCommand.mandarLista(p);
					}
				}
			}
		}
	}

	public static HashMap<String, String> step1 = new HashMap<String, String>();

	@EventHandler
	public static void aoSair(PlayerQuitEvent e) {
		if (step1.containsKey(e.getPlayer().getName())) {
			step1.remove(e.getPlayer().getName());
		}
	}

	public static String getTitulo(String nome) {
		if (step1.containsKey(nome)) {
			return step1.get(nome);
		}
		return null;
	}

	public static void finalizar(String nome) {
		if (step1.containsKey(nome)) {
			step1.remove(nome);
		}
	}

	public static void setTitulo(String nome, String titulo) {
		if (step1.containsKey(nome)) {
			step1.replace(nome, titulo);
		}
	}

	public static void setDuvida(String nome, String duvida) {
		if (step1.containsKey(nome)) {
			step1.replace(nome, duvida);
		}
	}
}
