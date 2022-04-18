package com.wandy.api.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class MobstackCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("mobstack")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("wandy.mobstack")) {
			p.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("§cUtilize /mobstack <soltar/matar> [raio] para gerenciar os mobs estacados.");
			return true;
		}
		int entqnt;
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("matar")) {
				int i = 0;
				for (Entity e : p.getWorld().getEntities()) {
					if (e.hasMetadata("qnt")) {
						e.remove();
						i++;
					}
				}
				if (i == 0) {
					p.sendMessage("§cNão existe nenhuma entidade estacada neste mundo.");
					return true;
				}
				p.sendMessage("§aVocê matou " + i + " entidades que estavam estacadas no mundo " + p.getWorld().getName() + ".");
				return true;
			}
			if (args[0].equalsIgnoreCase("soltar")) {
				int i = 0;
				for (Entity e : p.getWorld().getEntities()) {
					if (e.hasMetadata("qnt")) {
						entqnt = e.getMetadata("qnt").get(0).asInt();
						for (int c = 0; c < entqnt; c++) {
							p.getWorld().spawnEntity(e.getLocation(), e.getType());
						}
						e.remove();
						i++;
					}
				}
				if (i == 0) {
					p.sendMessage("§cNão existe nenhuma entidade estacada neste mundo.");
					return true;
				}
				p.sendMessage("§aVocê soltou " + i + " entidades que estavam estacadas no mundo " + p.getWorld().getName() + ".");
				return true;
			}
			p.sendMessage("§cUtilize /mobstack <soltar/matar> [raio] para gerenciar os mobs estacados.");
			return true;
		}
		if (args.length == 2) {
			if (isInt(args[1])) {
				int raio = Integer.valueOf(args[1]).intValue();
				if (args[0].equalsIgnoreCase("matar")) {
					int i = 0;
					for (Entity e : p.getWorld().getEntities()) {
						if (p.getLocation().distance(e.getLocation()) <= raio) {
							if (e.hasMetadata("qnt")) {
								e.remove();
								i++;
							}
						}
					}
					if (i == 0) {
						p.sendMessage("§cNão existe nenhuma entidade estacada neste mundo.");
						return true;
					}
					p.sendMessage("§aVocê matou " + i + " entidades que estavam estacadas no mundo " + p.getWorld().getName() + ".");
					return true;
				}
				if (args[0].equalsIgnoreCase("soltar")) {
					int i = 0;
					for (Entity e : p.getWorld().getEntities()) {
						if (e.hasMetadata("qnt")) {
							if (p.getLocation().distance(e.getLocation()) <= raio) {
								int entqnt1 = e.getMetadata("qnt").get(0).asInt();
								for (int c = 0; c < entqnt1; c++) {
									p.getWorld().spawnEntity(e.getLocation(), e.getType());
								}
								e.remove();
								i++;
							}
						}
					}
					if (i == 0) {
						p.sendMessage("§cNão existe nenhuma entidade estacada neste mundo.");
						return true;
					}
					p.sendMessage("§aVocê soltou " + i + " entidades que estavam estacadas no mundo " + p.getWorld().getName() + ".");
					return true;
				}
				p.sendMessage("§cUtilize /mobstack <soltar/matar> [raio] para gerenciar os mobs estacados.");
				return true;
			}
			p.sendMessage("§cVocê inseriu um raio inválido.");
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
