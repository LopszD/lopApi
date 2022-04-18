package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveCommand implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("give")) || (!sender.hasPermission("wandy.give"))) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage("§cUtilize /give <nick> <id> [quantidade] para enviar um item à um usuário.");
			return true;
		}
		if (args.length == 1) {
			sender.sendMessage("§cUtilize /give [nick] <id> [quantidade] para enviar um item à um usuário.");
			return true;
		}
		if (args.length == 2) {
			Player p1 = Bukkit.getPlayer(args[0]);
			if (p1 == null) {
				sender.sendMessage("§cEste usuário não está on-line.");
				return true;
			}
			if (args[1].contains(":")) {
				String it = args[1].split(":")[0];
				int damage = Integer.valueOf(args[1].split(":")[1]).intValue();
				if (isInt(it)) {
					int id = Integer.valueOf(it).intValue();
					if (it.contains("-")) {
						sender.sendMessage("§cItem não encontrado.");
						return true;
					}
					if (it.contains("+")) {
						sender.sendMessage("§cItem não encontrado.");
						return true;
					}
					if (Material.getMaterial(id) == null) {
						sender.sendMessage("§cItem não encontrado.");
						return true;
					}
					ItemStack item = new ItemStack(Material.getMaterial(id), 1, (short) damage);
					p1.getInventory().addItem(new ItemStack[] { item });
					sender.sendMessage("§aEnviando 1x " + item.getType().toString().toUpperCase() + " para " + p1.getName() + ".");
					return true;
				}
				String name = it.toUpperCase();
				if (Material.getMaterial(name) == null) {
					sender.sendMessage("§cItem não encontrado.");
					return true;
				}
				ItemStack item = new ItemStack(Material.getMaterial(name), 1, (short) damage);
				p1.getInventory().addItem(new ItemStack[] { item });
				sender.sendMessage("§aEnviando 1x " + item.getType().toString().toUpperCase() + " para " + p1.getName() + ".");
				return true;
			}
			if (isInt(args[1])) {
				int id = Integer.valueOf(args[1]).intValue();
				if (args[1].contains("-")) {
					sender.sendMessage("§cItem não encontrado.");
					return true;
				}
				if (args[1].contains("+")) {
					sender.sendMessage("§cItem não encontrado.");
					return true;
				}
				if (Material.getMaterial(id) == null) {
					sender.sendMessage("§cItem não encontrado.");
					return true;
				}
				ItemStack item = new ItemStack(Material.getMaterial(id));
				p1.getInventory().addItem(new ItemStack[] { item });
				sender.sendMessage("§aEnviando 1x " + item.getType().toString().toUpperCase() + " para " + p1.getName() + ".");
				return true;
			}
			String name = args[1].toUpperCase();
			if (Material.getMaterial(name) == null) {
				sender.sendMessage("§cItem não encontrado.");
				return true;
			}
			ItemStack item = new ItemStack(Material.getMaterial(name));
			p1.getInventory().addItem(new ItemStack[] { item });
			sender.sendMessage("§aEnviando 1x " + item.getType().toString().toUpperCase() + " para " + p1.getName() + ".");
			return true;
		}
		if (args.length == 3) {
			Player p1 = Bukkit.getPlayer(args[0]);
			if (p1 == null) {
				sender.sendMessage("§cEste usuário não está on-line.");
				return true;
			}
			if (args[1].contains(":")) {
				String it = args[1].split(":")[0];
				int damage = Integer.valueOf(args[1].split(":")[1]).intValue();
				if (isInt(args[2])) {
					int quant = Integer.valueOf(args[2]).intValue();
					if (isInt(it)) {
						int id = Integer.valueOf(it).intValue();
						if (it.contains("-")) {
							sender.sendMessage("§cItem não encontrado.");
							return true;
						}
						if (it.contains("+")) {
							sender.sendMessage("§cItem não encontrado.");
							return true;
						}
						if (Material.getMaterial(id) == null) {
							sender.sendMessage("§cItem não encontrado.");
							return true;
						}
						ItemStack item = new ItemStack(Material.getMaterial(id), quant, (short) damage);
						p1.getInventory().addItem(new ItemStack[] { item });
						sender.sendMessage("§aEnviando " + quant + "x " + item.getType().toString().toUpperCase() + " para " + p1.getName() + ".");
						return true;
					}
					String name = it.toUpperCase();
					if (Material.getMaterial(name) == null) {
						sender.sendMessage("§cItem não encontrado.");
						return true;
					}
					ItemStack item = new ItemStack(Material.getMaterial(name), quant, (short) damage);
					p1.getInventory().addItem(new ItemStack[] { item });
					sender.sendMessage("§aEnviando " + quant + "x " + item.getType().toString().toUpperCase() + " para " + p1.getName() + ".");
					return true;
				}
				sender.sendMessage("§cVocê inseriu um número inválido.");
				return true;
			}
			if (isInt(args[1])) {
				if (isInt(args[2])) {
					int id = Integer.valueOf(args[1]).intValue();
					int quant = Integer.valueOf(args[2]).intValue();
					if (Material.getMaterial(id) == null) {
						sender.sendMessage("§cItem não encontrado.");
						return true;
					}
					ItemStack item = new ItemStack(Material.getMaterial(id), quant);
					p1.getInventory().addItem(new ItemStack[] { item });
					sender.sendMessage("§aEnviando " + quant + "x " + item.getType().toString().toUpperCase() + " para " + p1.getName() + ".");
					return true;
				}
				sender.sendMessage("§cVocê inseriu um número inválido.");
				return true;
			}
			if (isInt(args[2])) {
				String name = args[1].toUpperCase();
				if (Material.getMaterial(name) == null) {
					sender.sendMessage("§cItem não encontrado.");
					return true;
				}
				int quant = Integer.valueOf(args[2]).intValue();
				ItemStack item = new ItemStack(Material.getMaterial(name), quant);
				p1.getInventory().addItem(new ItemStack[] { item });
				sender.sendMessage("§aEnviando " + quant + "x " + item.getType().toString().toUpperCase() + " para " + p1.getName() + ".");
				return true;
			}
			sender.sendMessage("§cVocê inseriu um número inválido.");
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
