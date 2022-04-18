package com.wandy.api.utils;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.wandy.api.listeners.EntrarListener;
import com.wandy.api.listeners.RankListener;
import com.wandy.api.stattrack.ConfirmaçãoMenu;
import com.wandy.api.stattrack.RecompensasMenu;
import com.wandy.economy.API_Economy;

import net.citizensnpcs.api.CitizensAPI;
import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockAction;

public class Magaiver implements Listener {
	
	public static boolean open = false;

	@EventHandler
	public static void aoClicar(PlayerInteractEvent e) {
		if (e.getClickedBlock() != null) {
			if (!e.getClickedBlock().getType().equals(Material.AIR)) {
				if (e.getClickedBlock().getType().equals(Material.CHEST)) {
					if (e.getClickedBlock().hasMetadata("CHESTMAGAIVER")) {
						e.setCancelled(true);
					}
				}
			}
		}
	}

	@EventHandler
	public static void aoClicar(BlockBreakEvent e) {
		if (e.getBlock() != null) {
			if (!e.getBlock().getType().equals(Material.AIR)) {
				if (e.getBlock().getType().equals(Material.CHEST)) {
					if (e.getBlock().hasMetadata("CHESTMAGAIVER")) {
						e.setCancelled(true);
					}
				}
			}
		}
	}

	@EventHandler
	public static void aoClicar(PlayerInteractAtEntityEvent e) {
		Player p = e.getPlayer();
		if (CitizensAPI.getNPCRegistry().isNPC(e.getRightClicked())) {
			if (RankListener.magaiver != null) {
				if (CitizensAPI.getNPCRegistry().getNPC(e.getRightClicked()).getId() == RankListener.magaiver.getId()) {
					e.setCancelled(true);
					abrirMenu(p);
				}
			}
		}
	}

	public static int Novo = 25000;
	public static void abrirMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, "§8Macumbeiro");

		ItemStack a = new ItemStack(Material.WEB);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName("§cInformações");
		am.setLore(Arrays.asList(new String[] { "§7Você pode selecionar qualquer tipo de", "§7arma, seja ela uma espada ou um machado.", "§1", "§7Ao adicionar a contagem à sua arma, você", "§7poderá trocá-las por §cCashs §7e §cCaixas Misteriosas§7." }));
		a.setItemMeta(am);
		inv.setItem(22, a);

		ItemStack b = new ItemStack(Material.ANVIL);
		ItemMeta bm = b.getItemMeta();
		bm.setDisplayName("§cMacumbar um novo item");
	    String cor = "§c";
	    String para = "§cVocê não tem coins suficientes.";
	    if (API_Economy.getMoney(p.getName()) >= Novo)
	    {
	      para = "§aClique para fabricar!";
	      cor = "§a";
	    }
	    bm.setLore(Arrays.asList(new String[] { "§7Adicionar à um novo item", "§7uma contagem de §cMaldição§7.", "§2", "§7Você precisa de " + cor + "25.000 coins §7para", "§7amaldiçoar um novo item.", para }));
		b.setItemMeta(bm);
		inv.setItem(24, b);

		ItemStack c = new ItemStack(Material.STORAGE_MINECART);
		ItemMeta cm = c.getItemMeta();
		cm.setDisplayName("§cRecompensas");
		cm.setLore(Arrays.asList(new String[] { "§7Troque suas maldições por recompensas,", "§7resetando-às, assim que a troca", "§7for efetuada." }));
		c.setItemMeta(cm);
		inv.setItem(20, c);

		p.openInventory(inv);
	}

	@EventHandler
	public static void aoClicar(InventoryClickEvent e) {
		if (e.getInventory().getTitle().equals("§8Macumbeiro")) {
			Player p = (Player) e.getWhoClicked();
			if (e.getCurrentItem() == null) {
				return;
			}
			if (e.getCurrentItem().getType().equals(Material.AIR)) {
				return;
			}
			e.setCancelled(true);
			if (e.getSlot() == 20) {
				if (p.getItemInHand() == null) {
					p.closeInventory();
					EntrarListener.mandarAction(p, "§cVocê precisa de um item na sua mão.");
					return;
				}
				if (p.getItemInHand().getType().equals(Material.AIR)) {
					p.closeInventory();
					EntrarListener.mandarAction(p, "§cVocê precisa de um item na sua mão.");
					return;
				}
				if (!com.wandy.api.stattrack.MagaiverListener.checarItem(p.getItemInHand())) {
					p.closeInventory();
					EntrarListener.mandarAction(p, "§cTipo inválido de item.");
					return;
				}
				if (!com.wandy.api.stattrack.MagaiverListener.temStat(p.getItemInHand())) {
					p.closeInventory();
					EntrarListener.mandarAction(p, "§cEste item não possui o sistema de maldição.");
					return;
				}
				RecompensasMenu.abrirMenu(p);
			}
			if (e.getSlot() == 24) {
				if (p.getItemInHand() == null) {
					p.closeInventory();
					EntrarListener.mandarAction(p, "§cVocê precisa de um item na sua mão.");
					return;
				}
				if (p.getItemInHand().getType().equals(Material.AIR)) {
					p.closeInventory();
					EntrarListener.mandarAction(p, "§cVocê precisa de um item na sua mão.");
					return;
				}
				if (!com.wandy.api.stattrack.MagaiverListener.checarItem(p.getItemInHand())) {
					p.closeInventory();
					EntrarListener.mandarAction(p, "§cTipo inválido de item.");
					return;
				}
				if (com.wandy.api.stattrack.MagaiverListener.temStat(p.getItemInHand())) {
					p.closeInventory();
					EntrarListener.mandarAction(p, "§cEste item já possui o sistema de maldição.");
					return;
				}
				ConfirmaçãoMenu.abrirMenu(p);
			}
		}
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public static void changeChestState(final Location loc, final boolean open) {
		final byte dataByte = (byte) (open ? 1 : 0);
		for (final Player player : Bukkit.getOnlinePlayers()) {
			player.playNote(loc, (byte) 1, dataByte);
			final BlockPosition position = new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
			final PacketPlayOutBlockAction blockActionPacket = new PacketPlayOutBlockAction(position, Block.getById(loc.getBlock().getTypeId()), 1, (int) dataByte);
			((CraftPlayer) player).getHandle().playerConnection.sendPacket((Packet) blockActionPacket);
		}
	}
}
