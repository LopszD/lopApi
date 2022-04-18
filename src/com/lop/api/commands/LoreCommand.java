package com.wandy.api.commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LoreCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("lore")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("wandy.lore")) {
			p.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (p.getInventory().getItemInHand() == null) {
			p.sendMessage("§cVocê precisa de um item na sua mão.");
			return true;
		}
		if (p.getInventory().getItemInHand().getType() == Material.AIR) {
			p.sendMessage("§cVocê precisa de um item na sua mão.");
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("§cUtilize /lore <argumentos> para definir a lore deste item.");
			return true;
		}
		if (args.length > 0) {
			ItemStack a = p.getInventory().getItemInHand();
			ItemMeta am = a.getItemMeta();
			List<String> lore = new ArrayList<String>();
			String[] arrayOfString;
			int j = (arrayOfString = args).length;
			for (int i = 0; i < j; i++) {
				String arg = arrayOfString[i];
				lore.add(arg.replace("&", "§").replace("%", " "));
			}
			am.setLore(lore);
			a.setItemMeta(am);
			p.sendMessage("§aLore modificada com sucesso.");
			return true;
		}
		return false;
	}
}
