package com.wandy.api.utils;

import org.bukkit.entity.Player;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;

public class MassiveFactions {

	public static boolean upPower(Player p) {
		MPlayer mp = MPlayer.get(p);
		int poderatual = mp.getPowerRounded();
		if (poderatual >= mp.getPowerMax()) {
			p.sendMessage("�cVoc� est� com seu poder atual no m�ximo. (" + mp.getPowerRounded() + "/20)");
			return false;
		} else {
			p.closeInventory();
			mp.setPower(poderatual + 1.0);
			p.sendMessage("�aVoc� aumentou seu poder para " + mp.getPowerRounded() + "!");
			return true;
		}
	}

	public static boolean upMaxPower(Player p) {
		MPlayer mp = MPlayer.get(p);
		int maximo = mp.getPowerMaxRounded();
		if (maximo >= 20) {
			p.sendMessage("�cVoc� j� est� com seu poder no m�ximo (M�x: 20).");
			return false;
		} else {
			mp.setPowerBoost(mp.getPowerBoost() + 1.0);
			p.sendMessage("�aVoc� aumentou seu poder m�ximo para " + mp.getPowerMaxRounded() + "!");
			return true;
		}
	}

	public static boolean upMaxMembers(Player p) {
		MPlayer mp = MPlayer.get(p);
		if (!mp.hasFaction()) {
			p.sendMessage("�cVoc� n�o possui uma fac��o.");
			return false;
		}
		Faction f = mp.getFaction();
		int maximo = f.getMembersLimit();
		if (maximo >= 25) {
			p.sendMessage("�cSua fac��o j� est� com o seu limite de membros no m�ximo (M�x: 25).");
			return false;
		} else {
			f.setMemberBoost(f.getMemberBoost() + 1);
			p.sendMessage("�aLimite de membros da fac��o foi aumentado para " + f.getMembersLimit() + "�a!");
			return true;
		}
	}
}
