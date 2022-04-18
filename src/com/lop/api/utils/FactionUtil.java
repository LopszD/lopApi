package com.wandy.api.utils;

import com.massivecraft.factions.TerritoryAccess;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class FactionUtil {
	
	public Faction getFaction(Location loc) {
		return BoardColl.get().getFactionAt(PS.valueOf(loc));
	}

	public TerritoryAccess getTerritoryAccessAt(PS ps) {
		if (ps == null) {
			return null;
		}
		ps = ps.getChunkCoords(true);
		TerritoryAccess ret = BoardColl.get().getTerritoryAccessAt(ps);
		if (ret == null) {
			ret = TerritoryAccess.valueOf("none");
		}
		return ret;
	}

	public Faction getFactionAt(PS ps) {
		if (ps == null) {
			return null;
		}
		TerritoryAccess ta = getTerritoryAccessAt(ps);
		return ta.getHostFaction();
	}

	public Faction getFaction(PS ps) {
		return BoardColl.get().getFactionAt(ps);
	}

	public Faction getFaction(Player p) {
		MPlayer mp = MPlayer.get(p);
		return mp.getFaction();
	}
}
