package com.wandy.api.utils.partículas;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class Particles {
	private PacketPlayOutWorldParticles packet;

	public Particles(EnumParticle type, Location loc, float xOffset, float yOffset, float zOffset, float speed, int count) {
		float x = (float) loc.getX();
		float y = (float) loc.getY();
		float z = (float) loc.getZ();
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(type, true, x, y, z, xOffset, yOffset, zOffset, speed, count, null);
		this.packet = packet;
	}

	public void sendToAll() {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(this.packet);
		}
	}

	public void sendToPlayer(Player p) {
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(this.packet);
	}
}
