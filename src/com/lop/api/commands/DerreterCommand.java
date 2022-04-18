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

public class DerreterCommand implements CommandExecutor {
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
		if (command.getName().equalsIgnoreCase("derreter")) {
		}
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cApenas jogadores in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (p.getInventory().getItemInHand().getType().equals(Material.AIR)) {
			p.sendMessage("§cVocê precisa de um item em sua mão.");
			return true;
		}
		int total;
		@SuppressWarnings("unused")
		int amount = total = getAmount(p.getItemInHand(), p.getInventory());
		if (p.getItemInHand().getType() == Material.IRON_ORE) {
			p.getInventory().remove(p.getItemInHand().getType());
			ItemStack b = new ItemStack(Material.IRON_INGOT, total);
			p.getInventory().addItem(new ItemStack[] { b });
			EntrarListener.mandarAction(p, "§aYAY! Você derreteu " + total + " minérios de ferro.");
			return true;
		}
		if (p.getItemInHand().getType() == Material.GOLD_ORE) {
			p.getInventory().remove(p.getItemInHand().getType());
			ItemStack b = new ItemStack(Material.GOLD_INGOT, total);
			p.getInventory().addItem(new ItemStack[] { b });
			EntrarListener.mandarAction(p, "§aYAY! Você derreteu " + total + " minérios de ouro.");
			return true;
		}
		p.sendMessage("§cVocê não pode derreter este item.");
		return false;
	}
}
