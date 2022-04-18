package com.wandy.api.commands;

import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;

public class PotCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!command.getName().equals("pot")) {
			return false;
		}
		if (!(sender instanceof Player)) {
			sender.sendMessage("�cApenas usu�rios in-game podem executar este comando");
			return true;
		}
		if (!sender.hasPermission("wandy.pot")) {
			sender.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		Map<ItemStack, Integer> potionMap = new LinkedHashMap<ItemStack, Integer>();
		for (int i = 0; i < p.getInventory().getSize(); ++i) {
			ItemStack item = p.getInventory().getItem(i);
			if (item != null && item.getType() == Material.POTION && !Potion.fromItemStack(item).isSplash() && item.getDurability() != 0) {
				ItemStack contains = null;
				for (ItemStack stack : potionMap.keySet()) {
					if (stack.getDurability() == item.getDurability() && stack.getItemMeta().equals(item.getItemMeta())) {
						contains = stack;
						break;
					}
				}
				if (contains != null) {
					potionMap.put(contains, potionMap.get(contains) + item.getAmount());
				} else {
					potionMap.put(item, item.getAmount());
				}
			}
		}
		if (potionMap.isEmpty()) {
			p.sendMessage("�cNenhuma po��o foi encontrada em seu invent�rio.");
			return true;
		}
		ItemStack[] items = p.getInventory().getContents();
		for (int j = 0; j < items.length; ++j) {
			if (items[j] != null && items[j].getType() == Material.POTION && !Potion.fromItemStack(items[j]).isSplash() && items[j].getDurability() != 0) {
				p.getInventory().clear(j);
			}
		}
		for (Map.Entry<ItemStack, Integer> entry : potionMap.entrySet()) {
			ItemStack stack2 = entry.getKey();
			stack2.setAmount((int) entry.getValue());
			p.getInventory().addItem(new ItemStack[] { stack2 });
		}
		p.updateInventory();
		p.sendMessage("�aSuas po��es foram stackadas com sucesso.");
		return true;
	}
}
