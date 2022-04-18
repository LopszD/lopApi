package com.wandy.api.tablist;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.wandy.api.Main;

public class TabListPacket {

	public static void sendTablist(Player p){
		PacketContainer pc = Main.protocolManager.createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
		pc.getChatComponents()
			.write(0, WrappedChatComponent.fromText("\n§6§lREDE WANDY"
				+ "\n"
				+ "§c    §fVocê está conectado atualmente no §6Factions§f"
				+ "\n§r"))
			.write(1,
				WrappedChatComponent.fromText("\n§6Discord: §fbit.ly/WandyDC"
				+ "\n§6Twitter: §ftwitter.com/ServidoresWandy"
				+ "\n§r"
				+ "\n§c    §c    §c    §c    §6Adquira §lVIP§6 e §lCASH§6 acessando: §fwww.redewandy.com/loja    §c    §c    §c    §c"
				+ "\n§r"));
		try {
			Main.protocolManager.sendServerPacket(p, pc);
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§c§lERRO: §r§c" + e.getMessage());
		}
	}
}
