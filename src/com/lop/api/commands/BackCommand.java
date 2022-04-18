package com.wandy.api.commands;

import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import com.wandy.api.Main;

public class BackCommand implements CommandExecutor {

	public static HashMap<String, Location> backs;
	public static HashSet<String> backscooldown;
	private HashMap<Player, Long> cooldown;

	public BackCommand(Main plugin) {
		plugin.getCommand("back").setExecutor(this);
		backs = new HashMap<String, Location>();
		backscooldown = new HashSet<String>();
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		if (!sender.hasPermission("wandy.back")) {
			sender.sendMessage("§cVocê não tem permisão para executar este comando.");
			return true;
		}
		Player player = (Player) sender;
		if (!backs.containsKey(player.getName())) {
			player.sendMessage("§cVocê não tem um local para voltar.");
			return true;
		}
		Location location = backs.get(player.getName());
		if (isValidTeleport(location, player)) {
			player.teleport(location);
			player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
			player.sendMessage("§aTeleportado!");
		}
		return false;
	}

	protected boolean isValidTeleport(Location location, Player player) {
		MPlayer mp = MPlayer.get(player);
		Faction factionAt = BoardColl.get().getFactionAt(PS.valueOf(location));
		Faction mpFaction = mp.getFaction();
		if ((factionAt.isNone()) || (factionAt.getId().equals("warzone")) || (factionAt.getId().equals("safezone")) || (factionAt.equals(mpFaction))) {
			return true;
		}
		player.sendMessage("§cVocê não pode retornar para este local.");
		return false;
	}

	public HashMap<Player, Long> getCooldown() {
		return this.cooldown;
	}
}
