package com.wandy.api.utils;

import org.bukkit.entity.Player;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;

public class MassiveFactions {

	public static boolean upPower(Player p) {
		MPlayer mp = MPlayer.get(p);
		int poderatual = mp.getPowerRounded();
		if (poderatual >= mp.getPowerMax()) {
			p.sendMessage("§cVocê está com seu poder atual no máximo. (" + mp.getPowerRounded() + "/20)");
			return false;
		} else {
			p.closeInventory();
			mp.setPower(poderatual + 1.0);
			p.sendMessage("§aVocê aumentou seu poder para " + mp.getPowerRounded() + "!");
			return true;
		}
	}

	public static boolean upMaxPower(Player p) {
		MPlayer mp = MPlayer.get(p);
		int maximo = mp.getPowerMaxRounded();
		if (maximo >= 20) {
			p.sendMessage("§cVocê já está com seu poder no máximo (Máx: 20).");
			return false;
		} else {
			mp.setPowerBoost(mp.getPowerBoost() + 1.0);
			p.sendMessage("§aVocê aumentou seu poder máximo para " + mp.getPowerMaxRounded() + "!");
			return true;
		}
	}

	public static boolean upMaxMembers(Player p) {
		MPlayer mp = MPlayer.get(p);
		if (!mp.hasFaction()) {
			p.sendMessage("§cVocê não possui uma facção.");
			return false;
		}
		Faction f = mp.getFaction();
		int maximo = f.getMembersLimit();
		if (maximo >= 25) {
			p.sendMessage("§cSua facção já está com o seu limite de membros no máximo (Máx: 25).");
			return false;
		} else {
			f.setMemberBoost(f.getMemberBoost() + 1);
			p.sendMessage("§aLimite de membros da facção foi aumentado para " + f.getMembersLimit() + "§a!");
			return true;
		}
	}
}
