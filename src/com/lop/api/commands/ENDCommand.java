package com.wandy.api.commands;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wandy.api.Main;
import com.wandy.api.sql.Manager;

public class ENDCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("theend")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		final Player p = (Player) sender;
		if (args.length == 0) {
			if (!Manager.checarLocExiste("END")) {
				p.sendMessage("§cEstá localização ainda não foi definida.");
				return true;
			}
			Random r = new Random();
			int rn = r.nextInt(4);
			if (rn == 0) {
				final Location loc = Manager.pegarLocation("END").clone().add(0.0D, 0.0D, 2.0D);
				if (!p.hasPermission("wandy.semdelay")) {
					p.sendMessage("§eTeleportando em 3 segundos...");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
						public void run() {
							p.teleport(loc);
							p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
						}
					}, 60L);
					return true;
				}
				p.teleport(loc);
				p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
				return true;
			}
			if (rn == 1) {
				final Location loc = Manager.pegarLocation("END").clone().subtract(2.0D, 0.0D, 0.0D);
				if (!p.hasPermission("wandy.semdelay")) {
					p.sendMessage("§eTeleportando em 3 segundos...");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
						public void run() {
							p.teleport(loc);
							p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
						}
					}, 60L);
					return true;
				}
				p.teleport(loc);
				p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
				return true;
			}
			if (rn == 2) {
				final Location loc = Manager.pegarLocation("END").clone().subtract(0.0D, 0.0D, 2.0D);
				if (!p.hasPermission("wandy.semdelay")) {
					p.sendMessage("§eTeleportando em 3 segundos...");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
						public void run() {
							p.teleport(loc);
							p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
						}
					}, 60L);
					return true;
				}
				p.teleport(loc);
				p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
				return true;
			}
			if (rn == 3) {
				final Location loc = Manager.pegarLocation("END").clone().add(2.0D, 0.0D, 0.0D);
				if (!p.hasPermission("wandy.semdelay")) {
					p.sendMessage("§eTeleportando em 3 segundos...");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
						public void run() {
							p.teleport(loc);
							p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
						}
					}, 60L);

					return true;
				}
				p.teleport(loc);
				p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
				return true;
			}
			if (rn == 4) {
				final Location loc = Manager.pegarLocation("END").clone().add(0.0D, 0.0D, 2.0D);
				if (!p.hasPermission("wandy.semdelay")) {
					p.sendMessage("§eTeleportando em 3 segundos...");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
						public void run() {
							p.teleport(loc);
							p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
						}
					}, 60L);

					return true;
				}
				p.teleport(loc);
				p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
				return true;
			}
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("setar")) {
				if (p.hasPermission("wandy.setarspawn")) {
					Location loc = p.getLocation();
					if (Manager.checarLocExiste("END")) {
						Manager.updateLocation("END", loc);
						p.sendMessage("§aSpawn setado com sucesso.");
						return true;
					}
					Manager.setarLocation("END", loc);
					p.sendMessage("§aSpawn setado com sucesso.");
					return true;
				}
				if (!Manager.checarLocExiste("END")) {
					p.sendMessage("§cEstá localização ainda não foi definida.");
					return true;
				}
				Random r = new Random();
				int rn = r.nextInt(4);
				if (rn == 1) {
					final Location loc = Manager.pegarLocation("END").clone().subtract(2.0D, 0.0D, 0.0D);
					if (!p.hasPermission("wandy.semdelay")) {
						p.sendMessage("§eTeleportando em 3 segundos...");
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
							public void run() {
								p.teleport(loc);
								p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
							}
						}, 60L);

						return true;
					}
					p.teleport(loc);
					p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
					return true;
				}
				if (rn == 2) {
					final Location loc = Manager.pegarLocation("END").clone().subtract(0.0D, 0.0D, 2.0D);
					if (!p.hasPermission("wandy.semdelay")) {
						p.sendMessage("§eTeleportando em 3 segundos...");
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
							public void run() {
								p.teleport(loc);
								p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
							}
						}, 60L);

						return true;
					}
					p.teleport(loc);
					p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
					return true;
				}
				if (rn == 3) {
					final Location loc = Manager.pegarLocation("END").clone().add(2.0D, 0.0D, 0.0D);
					if (!p.hasPermission("wandy.semdelay")) {
						p.sendMessage("§eTeleportando em 3 segundos...");
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
							public void run() {
								p.teleport(loc);
								p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
							}
						}, 60L);

						return true;
					}
					p.teleport(loc);
					p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
					return true;
				}
				if (rn == 4) {
					final Location loc = Manager.pegarLocation("END").clone().add(0.0D, 0.0D, 2.0D);
					if (!p.hasPermission("wandy.semdelay")) {
						p.sendMessage("§eTeleportando em 3 segundos...");
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
							public void run() {
								p.teleport(loc);
								p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
							}
						}, 60L);
						return true;
					}
					p.teleport(loc);
					p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
					return true;
				}
				if (rn == 0) {
					final Location loc = Manager.pegarLocation("END").clone().add(0.0D, 0.0D, 2.0D);
					if (!p.hasPermission("wandy.semdelay")) {
						p.sendMessage("§eTeleportando em 3 segundos...");
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
							public void run() {
								p.teleport(loc);
								p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
							}
						}, 60L);

						return true;
					}
					p.teleport(loc);
					p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
					return true;
				}
				return true;
			}
			if (!Manager.checarLocExiste("END")) {
				p.sendMessage("§cEstá localização ainda não foi definida.");
				return true;
			}
			Random r = new Random();
			int rn = r.nextInt(4);
			if (rn == 1) {
				final Location loc = Manager.pegarLocation("END").clone().subtract(2.0D, 0.0D, 0.0D);
				if (!p.hasPermission("wandy.semdelay")) {
					p.sendMessage("§eTeleportando em 3 segundos...");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
						public void run() {
							p.teleport(loc);
							p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
						}
					}, 60L);

					return true;
				}
				p.teleport(loc);
				p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
				return true;
			}
			if (rn == 2) {
				final Location loc = Manager.pegarLocation("END").clone().subtract(0.0D, 0.0D, 2.0D);
				if (!p.hasPermission("wandy.semdelay")) {
					p.sendMessage("§eTeleportando em 3 segundos...");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
						public void run() {
							p.teleport(loc);
							p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
						}
					}, 60L);
					return true;
				}
				p.teleport(loc);
				p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
				return true;
			}
			if (rn == 3) {
				final Location loc = Manager.pegarLocation("END").clone().add(2.0D, 0.0D, 0.0D);
				if (!p.hasPermission("wandy.semdelay")) {
					p.sendMessage("§eTeleportando em 3 segundos...");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
						public void run() {
							p.teleport(loc);
							p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
						}
					}, 60L);
					return true;
				}
				p.teleport(loc);
				p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
				return true;
			}
			if (rn == 4) {
				final Location loc = Manager.pegarLocation("END").clone().add(0.0D, 0.0D, 2.0D);
				if (!p.hasPermission("wandy.semdelay")) {
					p.sendMessage("§eTeleportando em 3 segundos...");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
						public void run() {
							p.teleport(loc);
							p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
						}
					}, 60L);
					return true;
				}
				p.teleport(loc);
				p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
				return true;
			}
			if (rn == 0) {
				final Location loc = Manager.pegarLocation("END").clone().add(0.0D, 0.0D, 2.0D);
				if (!p.hasPermission("wandy.semdelay")) {
					p.sendMessage("§eTeleportando em 3 segundos...");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
						public void run() {
							p.teleport(loc);
							p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
						}
					}, 60L);

					return true;
				}
				p.teleport(loc);
				p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
				return true;
			}
			return true;
		}
		if (args.length > 1) {
			if (!Manager.checarLocExiste("END")) {
				p.sendMessage("§cEstá localização ainda não foi definida.");
				return true;
			}
			Random r = new Random();
			int rn = r.nextInt(4);
			if (rn == 1) {
				final Location loc = Manager.pegarLocation("END").clone().subtract(2.0D, 0.0D, 0.0D);
				if (!p.hasPermission("wandy.semdelay")) {
					p.sendMessage("§eTeleportando em 3 segundos...");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
						public void run() {
							p.teleport(loc);
							p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
						}
					}, 60L);

					return true;
				}
				p.teleport(loc);
				p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
				return true;
			}
			if (rn == 2) {
				final Location loc = Manager.pegarLocation("END").clone().subtract(0.0D, 0.0D, 2.0D);
				if (!p.hasPermission("wandy.semdelay")) {
					p.sendMessage("§eTeleportando em 3 segundos...");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
						public void run() {
							p.teleport(loc);
							p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
						}
					}, 60L);

					return true;
				}
				p.teleport(loc);
				p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
				return true;
			}
			if (rn == 3) {
				final Location loc = Manager.pegarLocation("END").clone().add(2.0D, 0.0D, 0.0D);
				if (!p.hasPermission("wandy.semdelay")) {
					p.sendMessage("§eTeleportando em 3 segundos...");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
						public void run() {
							p.teleport(loc);
							p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
						}
					}, 60L);

					return true;
				}
				p.teleport(loc);
				p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
				return true;
			}
			if (rn == 4) {
				final Location loc = Manager.pegarLocation("END").clone().add(0.0D, 0.0D, 2.0D);
				if (!p.hasPermission("wandy.semdelay")) {
					p.sendMessage("§eTeleportando em 3 segundos...");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
						public void run() {
							p.teleport(loc);
							p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
						}
					}, 60L);
					return true;
				}
				p.teleport(loc);
				p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
				return true;
			}
			if (rn == 0) {
				final Location loc = Manager.pegarLocation("END").clone().add(0.0D, 0.0D, 2.0D);
				if (!p.hasPermission("wandy.semdelay")) {
					p.sendMessage("§eTeleportando em 3 segundos...");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
						public void run() {
							p.teleport(loc);
							p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
						}
					}, 60L);

					return true;
				}
				p.teleport(loc);
				p.sendMessage("§eTeletransportado para o THE-END com sucesso!");
				return true;
			}
			return true;
		}
		return false;
	}
}
