package com.wandy.api.especiais.listeners;

import java.util.Arrays;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import com.wandy.api.sql.Manager;

public class BookCoordenadasListener implements Listener {

	@EventHandler
	public static void aoMorrer(PlayerDeathEvent e) {
		if (e.getEntity().getKiller() != null) {
			if ((e.getEntity().getKiller() instanceof Player)) {
				if (MPlayer.get(e.getEntity().getName()).hasFaction()) {
					if (MPlayer.get(e.getEntity().getName()).getFaction().getLandCount() > 0) {
						e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), getBook(e.getEntity(), MPlayer.get(e.getEntity().getName()).getFaction()));
					}
				}
			}
		}
	}

	public static ItemStack getBook(Player p, Faction f) {
		Set<PS> ls = BoardColl.get().getChunks(f);
		PS pas = null;
		int ch = 0;
		for (PS pss : ls) {
			if (ch > 0) {
				break;
			}
			pas = pss;
			ch++;
		}
		int zn = pas.getChunkZ().intValue() * 16 - 500;
		int zp = pas.getChunkZ().intValue() * 16 + 500;
		ItemStack a = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta am = (BookMeta) a.getItemMeta();
		am.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		am.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		am.setAuthor(p.getName());
		am.setDisplayName("§eCoordenadas da " + f.getName());
		am.setPages(Arrays.asList("Coords"));
		am.setPage(1, "§9§lCoordenadas\n§c\n§8Facção: §5" + f.getName() + "\n§1\n" + "§8O Z da localização da" + "\n" + "§8facção está entre" + "\n" + "§9" + zn + " e §9" + zp + "§8." + "\n§3\n" + "§8Data: §9" + Manager.getDayFormated().split(" ")[0]);
		a.setItemMeta(am);
		return a;
	}
}
