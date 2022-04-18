package com.wandy.api.commands;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.wandy.api.listeners.EntrarListener;

public class CompactarCommand implements CommandExecutor {
	public static boolean equals(ItemStack one, ItemStack two) {
		return one.isSimilar(two);
	}

	public static int getAmount(ItemStack item, Inventory inventory) {
		if (!inventory.contains(item.getType())) {
			return 0;
		}
		if (inventory.getType() == null) {
			return Integer.MAX_VALUE;
		}
		HashMap<Integer, ? extends ItemStack> items = (HashMap<Integer, ? extends ItemStack>) inventory
				.all(item.getType());
		int itemAmount = 0;
		for (ItemStack iStack : items.values()) {
			if (equals(iStack, item)) {
				itemAmount += iStack.getAmount();
			}
		}
		return itemAmount;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("compactar")) {
		}
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cApenas jogadores in-game podem executar este comando.");
			return true;
		}
		if (!sender.hasPermission("wandy.compactar")) {
			sender.sendMessage("§Você não tem permissão para executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (p.getInventory().getItemInHand().getType().equals(Material.AIR)) {
			p.sendMessage("§cVocê precisa de um item em sua mão.");
			return true;
		}
		int amount = getAmount(p.getItemInHand(), (Inventory) p.getInventory());
		int total = amount / 9;
		int resto = total * 9;
		int rest = amount - resto;
		if (p.getItemInHand().getType() == Material.SLIME_BALL) {
			if (amount <= 8) {
				p.sendMessage("§cVocê não possui itens suficientes para compactar.");
				return true;
			}
			p.getInventory().remove(p.getItemInHand().getType());
			ItemStack b = new ItemStack(Material.SLIME_BLOCK, total);
			p.getInventory().addItem(new ItemStack[] { b });
			EntrarListener.mandarAction(p, "§aYAY! Você compactou " + resto + " itens em " + total + " outros.");
			if (rest > 0) {
				ItemStack c = new ItemStack(Material.SLIME_BALL, rest);
				p.getInventory().addItem(new ItemStack[] { c });
			}
			return true;
		} else if (p.getItemInHand().getType() == Material.GOLD_NUGGET) {
			if (amount <= 8) {
				p.sendMessage("§cVocê não possui itens suficientes para compactar.");
				return true;
			}
			p.getInventory().remove(p.getItemInHand().getType());
			ItemStack b = new ItemStack(Material.GOLD_INGOT, total);
			p.getInventory().addItem(new ItemStack[] { b });
			EntrarListener.mandarAction(p, "§aYAY! Você compactou " + resto + " itens em " + total + " outros.");
			if (rest > 0) {
				ItemStack c = new ItemStack(Material.GOLD_NUGGET, rest);
				p.getInventory().addItem(new ItemStack[] { c });
			}
			return true;
		} else if (p.getItemInHand().getType() == Material.INK_SACK && p.getItemInHand().getDurability() == 4) {
			if (amount <= 8) {
				p.sendMessage("§cVocê não possui itens suficientes para compactar.");
				return true;
			}
			ItemStack[] contents;
			for (int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; ++i) {
				ItemStack in = contents[i];
				if (in != null && in.getType().equals(Material.INK_SACK) && in.getDurability() == 4) {
					p.getInventory().remove(in);
				}
			}
			ItemStack b = new ItemStack(Material.LAPIS_BLOCK, total);
			p.getInventory().addItem(new ItemStack[] { b });
			EntrarListener.mandarAction(p, "§aYAY! Você compactou " + resto + " itens em " + total + " outros.");
			if (rest > 0) {
				@SuppressWarnings("deprecation")
				ItemStack c = new ItemStack(Material.getMaterial(351), rest, (short) 4);
				p.getInventory().addItem(new ItemStack[] { c });
			}
			return true;
		} else if (p.getItemInHand().getType() == Material.IRON_INGOT) {
			if (amount <= 8) {
				p.sendMessage("§cVocê não possui itens suficientes para compactar.");
				return true;
			}
			p.getInventory().remove(p.getItemInHand().getType());
			ItemStack b = new ItemStack(Material.IRON_BLOCK, total);
			p.getInventory().addItem(new ItemStack[] { b });
			EntrarListener.mandarAction(p, "§aYAY! Você compactou " + resto + " itens em " + total + " outros.");
			if (rest > 0) {
				ItemStack c = new ItemStack(Material.IRON_INGOT, rest);
				p.getInventory().addItem(new ItemStack[] { c });
			}
			return true;
		} else if (p.getItemInHand().getType() == Material.GOLD_INGOT) {
			if (amount <= 8) {
				p.sendMessage("§cVocê não possui itens suficientes para compactar.");
				return true;
			}
			p.getInventory().remove(p.getItemInHand().getType());
			ItemStack b = new ItemStack(Material.GOLD_BLOCK, total);
			p.getInventory().addItem(new ItemStack[] { b });
			EntrarListener.mandarAction(p, "§aYAY! Você compactou " + resto + " itens em " + total + " outros.");
			if (rest > 0) {
				ItemStack c = new ItemStack(Material.GOLD_INGOT, rest);
				p.getInventory().addItem(new ItemStack[] { c });
			}
			return true;
		} else if (p.getItemInHand().getType() == Material.DIAMOND) {
			if (amount <= 8) {
				p.sendMessage("§cVocê não possui itens suficientes para compactar.");
				return true;
			}
			p.getInventory().remove(p.getItemInHand().getType());
			ItemStack b = new ItemStack(Material.DIAMOND_BLOCK, total);
			p.getInventory().addItem(new ItemStack[] { b });
			EntrarListener.mandarAction(p, "§aYAY! Você compactou " + resto + " itens em " + total + " outros.");
			if (rest > 0) {
				ItemStack c = new ItemStack(Material.DIAMOND, rest);
				p.getInventory().addItem(new ItemStack[] { c });
			}
			return true;
		} else if (p.getItemInHand().getType() == Material.COAL) {
			if (amount <= 8) {
				p.sendMessage("§cVocê não possui itens suficientes para compactar.");
				return true;
			}
			p.getInventory().remove(p.getItemInHand().getType());
			ItemStack b = new ItemStack(Material.COAL_BLOCK, total);
			p.getInventory().addItem(new ItemStack[] { b });
			p.sendMessage("§1");
			EntrarListener.mandarAction(p, "§aYAY! Você compactou " + resto + " itens em " + total + " outros.");
			p.sendMessage("§2");
			if (rest > 0) {
				ItemStack c = new ItemStack(Material.COAL, rest);
				p.getInventory().addItem(new ItemStack[] { c });
			}
			return true;
		} else if (p.getItemInHand().getType() == Material.EMERALD) {
			if (amount <= 8) {
				p.sendMessage("§cVocê não possui itens suficientes para compactar.");
				return true;
			}
			p.getInventory().remove(p.getItemInHand().getType());
			ItemStack b = new ItemStack(Material.EMERALD_BLOCK, total);
			p.getInventory().addItem(new ItemStack[] { b });
			EntrarListener.mandarAction(p, "§aYAY! Você compactou " + resto + " itens em " + total + " outros.");
			if (rest > 0) {
				ItemStack c = new ItemStack(Material.EMERALD, rest);
				p.getInventory().addItem(new ItemStack[] { c });
			}
			return true;
		} else {
			if (p.getItemInHand().getType() != Material.REDSTONE) {
				p.sendMessage("§cVocê não pode compactar este item.");
				return false;
			}
			if (amount <= 8) {
				p.sendMessage("§cVocê não possui itens suficientes para compactar.");
				return true;
			}
			p.getInventory().remove(p.getItemInHand().getType());
			ItemStack b = new ItemStack(Material.REDSTONE_BLOCK, total);
			p.getInventory().addItem(new ItemStack[] { b });
			EntrarListener.mandarAction(p, "§aYAY! Você compactou " + resto + " itens em " + total + " outros.");
			if (rest > 0) {
				ItemStack c = new ItemStack(Material.REDSTONE, rest);
				p.getInventory().addItem(new ItemStack[] { c });
			}
			return true;
		}
	}
}
