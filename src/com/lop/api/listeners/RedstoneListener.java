package com.wandy.api.listeners;

import java.util.ArrayList;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class RedstoneListener implements Listener {
	
	public static ArrayList<String> reds = new ArrayList<String>();

	@EventHandler
	public static void aoColocar(BlockPlaceEvent e) {
		if (taDesativada()) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public static void aoPistonar1(BlockPistonExtendEvent e) {
		if (taDesativada()) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public static void aoPistonar2(BlockPistonRetractEvent e) {
		if (taDesativada()) {
			e.setCancelled(true);
		}
	}

	public static boolean taDesativada() {
		if (reds.contains("AA")) {
			return true;
		}
		return false;
	}

	public static void ativarRed() {
		if (reds.contains("AA")) {
			reds.remove("AA");
		}
	}

	public static void desativarRed() {
		if (!reds.contains("AA")) {
			reds.add("AA");
		}
	}
}
