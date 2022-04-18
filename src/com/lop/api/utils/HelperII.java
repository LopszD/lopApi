package com.wandy.api.utils;

import java.text.NumberFormat;
import java.util.Locale;
import org.bukkit.entity.Player;
import com.massivecraft.factions.entity.MPlayer;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class HelperII {

	public static String getTag(String p) {
		return "§7" + PermissionsEx.getUser(p).getPrefix().replace("&", "§");
	}

	public static String getTag(Player p) {
		return "§7" + PermissionsEx.getUser(p).getPrefix().replace("&", "§");
	}

	public static String getRoleFaction(Player p) {
		String facP = "";
		if (MPlayer.get(p).hasFaction())
			facP = "§7[" + MPlayer.get(p).getRole().getPrefix() + MPlayer.get(p).getFaction().getTag() + "] ";
		return facP;
	}

	public static String getNameFaction(Player p) {
		String facP = "";
		if (MPlayer.get(p).hasFaction())
			facP = "§7 [" + MPlayer.get(p).getFaction().getTag() + "]";
		return facP;
	}

	public static String numberFormat(double valor) {
		return NumberFormat.getNumberInstance(new Locale("pt", "BR")).format(valor);
	}

	public static boolean isInteger(String n) {
		try {
			Integer.parseInt(n);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
