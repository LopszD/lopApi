package com.wandy.api.anticheat;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class AntiBOT implements Listener{

	public Map<String, String> playerData = new HashMap<String, String>();
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		String si = "NOBOT";
		String playerIP = player.getAddress().getAddress().toString();
		playerIP = playerIP.replaceAll("/", "");
		playerIP = playerIP.replaceAll("\\.", "-");
		if (this.playerData.containsKey(si)){
			this.playerData.put(playerIP, player.getName());
			event.getPlayer().kickPlayer("§c§lREDE WANDY\n\n§cVocê tentou usar conexão direta.\n§cIdentificamos que você é um bot!\n§cCaso não seja, basta atualizar sua lista relogar.");
		}
		if (!this.playerData.containsKey(playerIP)) {
			event.getPlayer().kickPlayer("§c§lREDE WANDY\n\n§cVocê tentou usar conexão direta.\n§cIdentificamos que você é um bot!\n§cCaso não seja, basta atualizar sua lista relogar.");
		}
	}

	@EventHandler
	public void ServerListPing(ServerListPingEvent event){
		String playerIP = event.getAddress().toString();
		playerIP = playerIP.replaceAll("/", "");
		playerIP = playerIP.replaceAll("\\.", "-");
		if (!this.playerData.containsKey(playerIP)) {
			this.playerData.put(playerIP, "NOBOT");
		}
	}
}
