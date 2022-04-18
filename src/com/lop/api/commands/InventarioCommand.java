package com.wandy.api.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InventarioCommand implements CommandExecutor {

	public static List<String> inventory = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("inventario")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
				return true;
			} else {
				Player p = (Player) sender;
				if (sender.hasPermission("wandy.inventario")) {
					if (inventory.contains(p.getName())) {
						inventory.remove(p.getName());
						sender.sendMessage("§cRecolhimento dos itens do chão ativado.");
						return true;
					} else {
						inventory.add(p.getName());
						sender.sendMessage("§aRecolhimento dos itens do chão desativado.");
						return true;
					}
				} else {
					sender.sendMessage("§cVocê não tem permissão para executar este comando.");
					return true;
				}
			}
		}
		return false;
	}
}