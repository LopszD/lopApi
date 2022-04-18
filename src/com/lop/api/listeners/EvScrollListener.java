package com.wandy.api.listeners;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import com.massivecraft.factions.entity.MPlayer;
import com.wandy.api.commands.VanishCommand;
import com.wandy.economy.API_Economy;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class EvScrollListener implements Listener {

	public static ArrayList<String> semscroll = new ArrayList<String>();
	public static NumberFormat numberFormat;

	@SuppressWarnings({ "deprecation" })
	@EventHandler
	public static void aoMudar(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (semscroll.contains(p.getName())) {
			return;
		}
		if (getTargetPlayer(p) != null) {
			if (!getTargetPlayer(p).equals(p)) {
				Player p1 = getTargetPlayer(p);
				MPlayer mp = MPlayer.get(p1);
				String prefix = PermissionsEx.getUser(p1).getGroups()[0].getPrefix();
				if (!prefix.equals("")) {
					prefix = prefix + " ";
				}
				if (prefix.equals("")) {
					prefix = "§7";
				}
				NumberFormat df = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
				if (!p1.getGameMode().equals(GameMode.SPECTATOR)) {
					if (VanishCommand.vanish.contains(p1.getName())) {
						if (p.hasPermission("wandy.vanish")) {
							String message = prefix.replace("&", "§") + p1.getName() + " §7- §fCoins: §e" + df.format(API_Economy.getScoin(p1.getName())) + " §fKDR: §e" + mp.getKdrRounded() + " §fNível: §e" + SkillsListener.getAllLevel(p1);
							EntrarListener.mandarAction(p, message);
						}
					} else {
						String message = prefix.replace("&", "§") + p1.getName() + " §7- §fCoins: §e" + df.format(API_Economy.getScoin(p1.getName())) + " §fKDR: §e" + mp.getKdrRounded() + " §fNível: §e" + SkillsListener.getAllLevel(p1);
						EntrarListener.mandarAction(p, message);
					}
				}
			}
		}
	}

	public static Player getTargetPlayer(Player player) {
		return (Player) getTarget(player, player.getWorld().getPlayers());
	}

	public static Entity getTargetEntity(Entity entity) {
		return getTarget(entity, entity.getWorld().getEntities());
	}

	public static <T extends Entity> T getTarget(Entity entity, Iterable<T> entities) {
		if (entity == null) {
			return null;
		}
		T target = null;
		for (T other : entities) {
			Vector n = other.getLocation().toVector().subtract(entity.getLocation().toVector());
			if (entity.getLocation().getDirection().normalize().crossProduct(n).lengthSquared() < 1.0D) {
				if ((n.normalize().dot(entity.getLocation().getDirection().normalize()) >= 0.0D) && ((target == null) || (target.getLocation().distanceSquared(entity.getLocation()) > other.getLocation().distanceSquared(entity.getLocation())))) {
					if (other.getLocation().distance(entity.getLocation()) <= 5.0D) {
						target = other;
					}
				}
			}
		}
		return target;
	}

	public static String format(double value) {
		numberFormat = NumberFormat.getNumberInstance(Locale.forLanguageTag("en-US"));
		if (value <= 1.0D) {
			return numberFormat.format(value).concat(" ").concat("");
		}
		return numberFormat.format(value).concat(" ").concat("").replace(",", ".").replace(" ", "");
	}
}
