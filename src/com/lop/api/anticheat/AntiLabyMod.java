package com.wandy.api.anticheat;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload;

public class AntiLabyMod implements Listener{
	
	public static void disableMod(Player p, List<LabyMod> mods) {
		try{
			HashMap<String, Boolean> dList = new HashMap<String, Boolean>();
			for(LabyMod mod : mods) {
			dList.put(mod.toString(), false);
			}
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectOutputStream objectOut = new ObjectOutputStream(out);
			objectOut.writeObject(dList);
			ByteBuf bb = Unpooled.copiedBuffer(out.toByteArray());
			PacketDataSerializer serializer = new PacketDataSerializer(bb);
			PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("LABYMOD", serializer);
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	public static enum LabyMod {
		FOOD, 
		GUI,
		NICK,
		BLOCKBUILD,
		CHAT,
		EXTRAS,
		ANIMATIONS,
		DAMAGEINDICATOR,
		MINIMAP_RADAR;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		List<LabyMod> mods = new ArrayList<>();
		for(LabyMod mod : LabyMod.values()) {
			mods.add(mod);
		}
		AntiLabyMod.disableMod(e.getPlayer(), mods);
	}
}