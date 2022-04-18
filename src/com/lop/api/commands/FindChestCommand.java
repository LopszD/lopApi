package com.wandy.api.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wandy.api.listeners.FindChestListener;

public class FindChestCommand implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("findchest")) || (!(sender instanceof Player))) {
			sender.sendMessage("�cApenas usu�rios in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("wandy.findchest")) {
			p.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("�cUtilize /findchest <item> [quantidade] para encontrar b�us com um item espec�fico.");
			return true;
		}
		if (args.length == 1) {
			if (isInt(args[0])) {
				int id = Integer.valueOf(args[0]).intValue();
				if (Material.getMaterial(id) == null) {
					sender.sendMessage("�cItem n�o encontrado.");
					return true;
				}
				Material m = Material.getMaterial(id);
				FindChestListener.abrirMenu(p, m, Integer.valueOf(0));
				return true;
			}
			String name = args[0].toUpperCase();
			if (Material.getMaterial(name) == null) {
				sender.sendMessage("�cItem n�o encontrado.");
				return true;
			}
			Material m = Material.getMaterial(name);
			FindChestListener.abrirMenu(p, m, Integer.valueOf(0));
			return true;
		}
		if (args.length == 2) {
			if (isInt(args[1])) {
				if (args[1].contains("-")) {
					p.sendMessage("�cN�mero inv�lido.");
					return true;
				}
				if (args[1].contains("+")) {
					p.sendMessage("�cN�mero inv�lido.");
					return true;
				}
				if (Integer.valueOf(args[1]).intValue() == 0) {
					p.sendMessage("�cN�mero inv�lido.");
					return true;
				}
				if (isInt(args[0])) {
					int id = Integer.valueOf(args[0]).intValue();
					if (Material.getMaterial(id) == null) {
						sender.sendMessage("�cItem n�o encontrado.");
						return true;
					}
					Material m = Material.getMaterial(id);
					FindChestListener.abrirMenu(p, m, Integer.valueOf(0));
					return true;
				}
				String name = args[0].toUpperCase();
				if (Material.getMaterial(name) == null) {
					sender.sendMessage("�cItem n�o encontrado.");
					return true;
				}
				Material m = Material.getMaterial(name);
				FindChestListener.abrirMenu(p, m, Integer.valueOf(0));
				return true;
			}
			p.sendMessage("�cN�mero inv�lido.");
			return true;
		}
		return false;
	}

	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
