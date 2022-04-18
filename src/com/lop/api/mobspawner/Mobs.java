package com.wandy.api.mobspawner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;

public class Mobs {
	
	public static HashMap<String, Mobs> mobs = new HashMap<String, Mobs>();
	private Location loc;
	private Date data;
	private EntityType type;

	public Mobs(World world, int x, int y, int z, Date data) {
		Location loc = new Location(world, x, y, z);
		try {
			if (loc.getBlock() != null) {
				if (loc.getBlock().getType() == Material.MOB_SPAWNER) {
					this.loc = loc;
					this.data = data;
					CreatureSpawner spawner = (CreatureSpawner) loc.getBlock().getState();
					this.type = spawner.getSpawnedType();
					mobs.put(world.getName() + "@" + x + "@" + y + "@" + z, this);
				}
			}
		} catch (IllegalStateException localIllegalStateException) {
		}
	}

	public Location getLocation() {
		return this.loc;
	}

	public int getX() {
		return this.loc.getBlockX();
	}

	public int getY() {
		return this.loc.getBlockX();
	}

	public int getZ() {
		return this.loc.getBlockX();
	}

	public World getWorld() {
		return this.loc.getWorld();
	}

	public Date getData() {
		return this.data;
	}

	public EntityType getType() {
		return this.type;
	}

	public String getDataF() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return format.format(this.data);
	}

	public boolean podeTirar() {
		Date data = new Date();
		if (data.after(this.data)) {
			return true;
		}
		return false;
	}

	public void setLocation(Location loc) {
		this.loc = loc;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public static void criarMob(Block b) {
		Date data = new Date();
		updateDate(data, b);
		World world = b.getLocation().getWorld();
		int x = b.getLocation().getBlockX();
		int y = b.getLocation().getBlockY();
		int z = b.getLocation().getBlockZ();
		new Mobs(world, x, y, z, data);
	}

	@SuppressWarnings("deprecation")
	public static void updateDate(Date data, Block b) {
		data.setHours(data.getHours() + 4);
	}

	public static boolean existeMob(Block b) {
		String world = b.getLocation().getWorld().getName();
		int x = b.getLocation().getBlockX();
		int y = b.getLocation().getBlockY();
		int z = b.getLocation().getBlockZ();
		if (mobs.containsKey(world + "@" + x + "@" + y + "@" + z)) {
			return true;
		}
		return false;
	}

	public void deletarMob() {
		String world = this.loc.getWorld().getName();
		int x = this.loc.getBlockX();
		int y = this.loc.getBlockY();
		int z = this.loc.getBlockZ();
		if (mobs.containsKey(world + "@" + x + "@" + y + "@" + z)) {
			mobs.remove(world + "@" + x + "@" + y + "@" + z);
		}
	}

	public static Mobs getMobs(Block b) {
		String world = b.getLocation().getWorld().getName();
		int x = b.getLocation().getBlockX();
		int y = b.getLocation().getBlockY();
		int z = b.getLocation().getBlockZ();

		return mobs.get(world + "@" + x + "@" + y + "@" + z);
	}
}
