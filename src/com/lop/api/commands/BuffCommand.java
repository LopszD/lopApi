package com.wandy.api.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.wandy.utils.utils.Mine;

public class BuffCommand implements CommandExecutor {
	
	public static ItemStack buff1 = Mine.newItem(Material.POTION, "§cBuff de Força", 1, 8233);
	public static ItemStack buff2 = Mine.newItem(Material.POTION, "§cBuff de Velocidade", 1, 8226);
	public static ItemStack vidro = Mine.newItem(Material.STAINED_GLASS_PANE, " ", 1, 11);

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			Inventory menu = Mine.newInventory("§8Buffs", 9 * 3);
			ItemMeta vidrom = vidro.getItemMeta();
			vidrom.addEnchant(Enchantment.DURABILITY, 1, false);
			vidro.setItemMeta(vidrom);
			for (int i = 0; i < 27; i++) {
				menu.setItem(i, vidro);
			}
			menu.setItem(Mine.getPosition(2, 4), buff1);
			menu.setItem(Mine.getPosition(2, 6), buff2);
			if (p.hasPermission("wandy.buff")) {
				p.openInventory(menu);
			} else {
				p.sendMessage("§cVocê não tem permissão para executar este comando.");
			}
		}
		return false;
	}
}
