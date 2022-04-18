package com.wandy.api.proteção;

import com.massivecraft.factions.entity.BoardColl;
import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BlockStatus {
	
	public static HashMap<Location, BlockMap> getBlockStatus = new HashMap<Location, BlockMap>();

	public static void setBlockDurability(Block b) {
		if (b.getLocation().getY() != 0.0D) {
			if ((!isHashedBlock(b)) && (isValidBlock(b))) {
				getBlockStatus.put(b.getLocation(), new BlockMap(b, getMaxDurabByType(b)));
			}
			if ((isHashedBlock(b)) && (isValidBlock(b))) {
				((BlockMap) getBlockStatus.get(b.getLocation())).setMinusDurability();
				if (getDurability(b) < 1) {
					getBlockStatus.remove(b.getLocation());
					breakBlock(b);
					return;
				}
			}
		}
	}

	public static void setBlockDurabilitySC(Block b) {
		if (b.getLocation().getY() != 0.0D) {
			if ((!isHashedBlock(b)) && (isValidBlock(b))) {
				getBlockStatus.put(b.getLocation(), new BlockMap(b, getMaxDurabByType(b)));
			}
			if ((isHashedBlock(b)) && (isValidBlock(b))) {
				((BlockMap) getBlockStatus.get(b.getLocation())).setMinusDurabilitySC();
				if (getDurability(b) < 1) {
					getBlockStatus.remove(b.getLocation());
					breakBlock(b);
					return;
				}
			}
		}
	}

	public static boolean isHashedBlock(Block b) {
		return getBlockStatus.containsKey(b.getLocation());
	}

	private static int getDurability(Block b) {
		if (isHashedBlock(b)) {
			int dura = ((BlockMap) getBlockStatus.get(b.getLocation())).getDurability();
			if (dura > getMaxDurabByType(b)) {
				return getMaxDurabByType(b);
			}
			return dura;
		}
		return getMaxDurabByType(b);
	}

	private static void breakBlock(Block b) {
		b.setType(Material.AIR);
	}

	public static void sendDurability(Block b, Player p) {
		if (isValidBlock(b)) {
			if (!BoardColl.get().isActive()) {
				return;
			}
			if ((b.getType() == Material.ANVIL) || (b.getType() == Material.ENDER_CHEST) || (b.getType() == Material.ENCHANTMENT_TABLE)) {
				return;
			}
			if (isHashedBlock(b)) {
				p.sendMessage("§eEste item tem a durabilidade de §7 " + getDurability(b) + "/" + getMaxDurabByType(b));
				return;
			}
			p.sendMessage("§eEste item tem a durabilidade de §7 " + getMaxDurabByType(b) + "/" + getMaxDurabByType(b));
			return;
		}
	}

	public static boolean isValidBlock(Block b) {
		try {
			if (BlocksType.valueOf(b.getType().name().toUpperCase()) != null) {
				return true;
			}
		} catch (IllegalArgumentException e) {
			return false;
		}
		return false;
	}

	private static int getMaxDurabByType(Block b) {
		switch (b.getType()) {
		case BEDROCK:
			return 10;
		case OBSIDIAN:
			return 5;
		case ENDER_STONE:
			return 7;
		default:
			break;
		}
		return 0;
	}
}
