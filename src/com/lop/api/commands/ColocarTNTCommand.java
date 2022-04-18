package com.wandy.api.commands;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.block.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.scheduler.*;

import com.wandy.api.Main;

import java.util.*;

public class ColocarTNTCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("colocartnt")) || (!sender.hasPermission("wandy.colocartnt"))) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (p.hasPermission("wandy.colocartnt")) {
			if (p.getGameMode() == GameMode.SURVIVAL) {
				this.scanArea(p, p.getLocation(), 16, 16, 16);
			} else if (p.getGameMode() == GameMode.CREATIVE) {
				this.scanAreaCreative(p, p.getLocation(), 16, 16, 16);
			}
		}
		return false;
	}

	private ArrayList<Dispenser> ondispensers(Location loc, int yradius, int xradius, int zradius) {
		ArrayList<Dispenser> result = new ArrayList<Dispenser>();
		World w = loc.getWorld();
		for (int x = loc.getBlockX() - xradius; x < loc.getBlockX() + xradius; ++x) {
			for (int z = loc.getBlockZ() - zradius; z < loc.getBlockZ() + zradius; ++z) {
				for (int y = loc.getBlockY() - yradius; y < loc.getBlockY() + yradius; ++y) {
					Block b = w.getBlockAt(x, y, z);
					if (b.getState() instanceof Dispenser) {
						Dispenser dispenser = (Dispenser) b.getState();
						result.add(dispenser);
					}
				}
			}
		}
		return result;
	}

	private int TNTInvetory(Inventory inv) {
		int result = 0;
		for (int i = 0; i < inv.getSize(); ++i) {
			if (inv.getItem(i) != null) {
				ItemStack is = inv.getItem(i);
				if (is.getType() == Material.TNT) {
					int amount = is.getAmount();
					result += amount;
				}
			}
		}
		return result;
	}

	private boolean isInventoryFull(Inventory inv) {
		boolean result = false;
		if (inv.firstEmpty() == -1) {
			result = true;
		}
		return result;
	}

	private void scanAreaCreative(Player p, Location loc, int yradius, int xradius, int zradius) {
		new BukkitRunnable() {
			public void run() {
				ArrayList<Dispenser> dispensers = ColocarTNTCommand.this.ondispensers(loc, yradius, xradius, zradius);
				if (dispensers.size() == 0) {
					p.sendMessage("§cNão foi encontrado nenhum Ejetor em um raio de 16.");
					return;
				}
				Inventory playerinv = (Inventory) p.getInventory();
				if (playerinv == null) {
					p.sendMessage("§cVocê não tem tem TNT no seu inventário.");
					return;
				}
				int TNTininv = TNTInvetory((Inventory) p.getInventory());
				int split = TNTininv / dispensers.size();
				if (split <= 0 && p.getGameMode() != GameMode.CREATIVE) {
					p.sendMessage("§cVocê não tem tem TNT no seu inventário.");
					return;
				}
				if (p.getGameMode() == GameMode.CREATIVE) {
					for (Dispenser dispenser : dispensers) {
						dispenser.getInventory().addItem(new ItemStack[] { new ItemStack(Material.TNT, 576) });
					}
					p.sendMessage("§aTodas as TNT foram colocadas dentro dos Ejetores com sucesso.");
				}
			}
		}.runTaskAsynchronously(Main.getInstance());
		p.updateInventory();
	}

	private void scanArea(Player p, Location loc, int yradius, int xradius, int zradius) {
		new BukkitRunnable() {
			public void run() {
				ArrayList<Dispenser> dispensers = ColocarTNTCommand.this.ondispensers(loc, yradius, xradius, zradius);
				if (dispensers.size() == 0) {
					p.sendMessage("§cNão foi encontrado nenhum Ejetor em um raio de 16.");
					return;
				}
				int TNTininv = TNTInvetory(p.getInventory());
				int split = TNTininv / dispensers.size();
				if (split <= 0) {
					p.sendMessage("§cVocê não tem tem TNT no seu inventário.");
					return;
				}
				if (p.getGameMode() == GameMode.SURVIVAL) {
					new BukkitRunnable() {
						public void run() {
							int usedTNT = 0;
							for (Dispenser dispenser : dispensers) {
								if (!ColocarTNTCommand.this.isInventoryFull(dispenser.getInventory())) {
									dispenser.getInventory().addItem(new ItemStack[] { new ItemStack(Material.TNT, split) });
									usedTNT += split;
								}
							}
							p.getInventory().remove(Material.TNT);
							if (TNTininv - usedTNT >= 1) {
								p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.TNT, TNTininv - usedTNT) });
							}
							p.updateInventory();
							p.sendMessage("§aTodas as TNT foram colocadas dentro dos Ejetores com sucesso.");
						}
					}.runTask(Main.getInstance());
				}
			}
		}.runTaskAsynchronously(Main.getInstance());
		p.updateInventory();
	}
}
