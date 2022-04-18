package com.wandy.api.especiais.listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import com.sk89q.worldedit.world.DataException;
import com.wandy.api.Main;
import com.wandy.api.utils.Proteção;

@SuppressWarnings("deprecation")
public class PortableCannonListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	void onPlaceBlock(BlockPlaceEvent event) {
		if (event.isCancelled())
			return;
		Player player = event.getPlayer();
		MPlayer mp = MPlayer.get(player);
		Faction factionAt = BoardColl.get().getFactionAt(PS.valueOf(event.getBlock().getLocation()));
		if (!factionAt.isNone() && factionAt != mp.getFaction()) {
			event.setCancelled(true);
			return;
		}
		ItemStack itemStack = player.getItemInHand();
		if (itemStack == null || itemStack.getType() == Material.AIR)
			return;
		if (itemStack.getItemMeta().hasDisplayName() && itemStack.getItemMeta().hasLore() && itemStack.getItemMeta().getDisplayName().endsWith("§bCanhão Portátil")) {
			event.setCancelled(true);
			if (BoardColl.get().getFactionAt(PS.valueOf(player.getLocation())).getId().equalsIgnoreCase("safezone")) {
				event.setCancelled(true);
				return;
			}
			if (BoardColl.get().getFactionAt(PS.valueOf(player.getLocation())).getId().equalsIgnoreCase("warzone")) {
				event.setCancelled(true);
				return;
			}
			if (Proteção.getArea(player).equals("spawn")) {
				event.setCancelled(true);
				return;
			}
			if (Proteção.getArea(player).equals("__global__")) {
				event.setCancelled(true);
				return;
			}
			if (player.getWorld().getName().equals("vip")) {
				event.setCancelled(true);
				return;
			}
			if (player.getWorld().getName().equals("minas")) {
				event.setCancelled(true);
				return;
			}
			if (player.getWorld().getName().equals("world_arenas")) {
				event.setCancelled(true);
				return;
			}
			if (player.getItemInHand().getAmount() > 1) {
				player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
			} else {
				player.setItemInHand(new ItemStack(Material.AIR));
			}
			loadSchematic(event.getBlock().getLocation());
			player.sendMessage("§aCanhão portátil colocado!");
			player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 1.0F);
			return;
		}
	}

	protected void loadSchematic(Location location) {
		WorldEditPlugin worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
		File schematic = new File(Main.plugin.getDataFolder(), "canhao.schematic");
		EditSession session = worldEditPlugin.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(location.getWorld()), 10000);
		try {
			CuboidClipboard clipboard = MCEditSchematicFormat.getFormat(schematic).load(schematic);
			clipboard.rotate2D(90);
			clipboard.paste(session, new Vector(location.getX(), location.getY(), location.getZ()), false);
		} catch (MaxChangedBlocksException | DataException | IOException e) {}
	}
}
