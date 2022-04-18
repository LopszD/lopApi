package com.wandy.api.especiais.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.MPlayer;
import com.wandy.api.listeners.CombatLogListener;
import com.wandy.api.sql.Manager;

public class RetrocederListener implements Listener {

	public static HashMap<Player, Long> usou;
	
	public List<Player> getPlayersAt(Player p, Integer distance) {
		List<Player> players = new ArrayList<>();
		if (MPlayer.get(p).hasFaction()) {
			for (Player all : p.getWorld().getPlayers()) {
				if (MPlayer.get(all).hasFaction() && MPlayer.get(all).getFaction().equals(MPlayer.get(p).getFaction())) {
					if (all.getLocation().distance(p.getLocation()) <= distance) {
						players.add(all);
					}
				}
			}
		}
		return players;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		MPlayer mp = MPlayer.get(p);
		if (e.hasItem() && p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasDisplayName() && p.getItemInHand().getItemMeta().getDisplayName().contains("§aRetroceder")) {
			e.setCancelled(true);
			if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
				List<Player> list_players = getPlayersAt(p, 10);
				if (!mp.hasFaction()) {
					p.sendMessage("§cVocê não tem uma facção.");
					return;
				}
				if (!mp.getRole().equals(Rel.LEADER) && !mp.getRole().equals(Rel.OFFICER)) {
					p.sendMessage("§cApenas capitão ou líder podem usar este item.");
					return;
				}
				if (list_players.size() == 0) {
					p.sendMessage("§cNão há nenhum membro de sua facção perto de você.");
					return;
				}
				ItemStack item = p.getItemInHand();
				if (item.getAmount() > 1) {
					item.setAmount(item.getAmount() - 1);
				} else {
					p.setItemInHand(new ItemStack(Material.AIR));
				}
				for(Player player : list_players) {
					Location loc = Manager.pegarLocation("SPAWN");
					CombatLogListener.tirarCombate(player);
					player.teleport(loc);
					player.sendMessage("§aVocê foi recuado pelo usuário " + mp.getRole().getPrefix() + p.getName() + ".");
				}
			}
			p.sendMessage("§aVocê recuou os usuários de sua facção em um raio de 10 blocos.");
			return;
		}
	}
}