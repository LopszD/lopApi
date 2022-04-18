package com.wandy.api.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class SerializeLocation {

	public static String serialize(Location loc) {
		String world = loc.getWorld().getName();
		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();
		float yaw = loc.getYaw();
		float pitch = loc.getPitch();
		String serialized = world + "@" + x + "@" + y + "@" + z + "@" + yaw + "@" + pitch;
		return serialized;
	}
	
	public static Location fromserialized(String s) {
		String[] parts = s.split("@");
		World world = Bukkit.getServer().getWorld(parts[0]);
		double x = Double.valueOf(parts[1]);
		double y = Double.valueOf(parts[2]);
		double z = Double.valueOf(parts[3]);
		float yaw = Float.valueOf(parts[4]);
		float pitch = Float.valueOf(parts[5]);
		Location loc = new Location(world, x, y, z, yaw, pitch);
		return loc;
	}
}
