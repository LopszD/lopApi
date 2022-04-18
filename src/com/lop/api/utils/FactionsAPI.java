package com.wandy.api.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;

public class FactionsAPI {
	public static Protection getProtection(Location loc, Player p) {
		Protection pr = Protection.Livre;
		MPlayer mp = MPlayer.get(p);
		Faction fac = BoardColl.get().getFactionAt(PS.valueOf(loc));
		if (fac == null) {
			return Protection.Livre;
		}
		if (fac.isNone()) {
			pr = Protection.Livre;
		} else if (fac.getId().equalsIgnoreCase("warzone")) {
			pr = Protection.Guerra;
		} else if (fac.getId().equalsIgnoreCase("safezone")) {
			pr = Protection.Protegida;
		} else if (mp.hasFaction()) {
			if (fac != mp.getFaction()) {
				switch (mp.getFaction().getRelationWish(fac)) {
				case ALLY:
					pr = Protection.Aliada;
					break;
				case ENEMY:
					pr = Protection.Inimiga;
					break;
				case NEUTRAL:
					pr = Protection.Neutra;
					break;
				case TRUCE:
					pr = Protection.Sua;
					break;
				default:
					pr = Protection.Livre;
				}
			} else {
				pr = Protection.Sua;
			}
		} else {
			pr = Protection.Inimiga;
		}
		return pr;
	}
}
