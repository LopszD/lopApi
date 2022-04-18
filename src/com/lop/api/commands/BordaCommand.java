package com.wandy.api.commands;

import java.text.NumberFormat;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BordaCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender p, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("borda")) {
			if (!p.hasPermission("wandy.borda")) {
				p.sendMessage("§cVocê não tem permissão para executar este comando.");
				return true;
			}
			if ((p instanceof Player)) {
				Player pl = (Player) p;
				if (args.length == 0) {
					if (pl.getWorld().getWorldBorder() == null) {
						p.sendMessage("§cEste mundo não tem uma borda.");
						return true;
					}
					WorldBorder wb = pl.getWorld().getWorldBorder();
					double raio = wb.getSize() / 2.0D;
					p.sendMessage("§fMundo: §7" + pl.getWorld().getName());
					p.sendMessage("§fRaio: §7" + format(raio));
					p.sendMessage("§fCentro: §7X: " + format(wb.getCenter().getX()) + " Z: " + format(wb.getCenter().getZ()));
					p.sendMessage("§fTamanho da borda: §7" + format(wb.getSize()));
					return true;
				}
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("resetar")) {
						if (pl.getWorld().getWorldBorder() == null) {
							p.sendMessage("§cEste mundo não tem uma borda.");
							return true;
						}
						WorldBorder wb = pl.getWorld().getWorldBorder();
						wb.setCenter(0.0D, 0.0D);
						wb.setSize(20000.0D);
						p.sendMessage("§aBorda resetada para " + format(wb.getSize()) + ".");
						return true;
					}
				}
				if (args.length >= 2) {
					if (args[0].equalsIgnoreCase("aumentar")) {
						if (isInt(args[1])) {
							double v = Double.valueOf(args[1]).doubleValue();
							if (pl.getWorld().getWorldBorder() == null) {
								p.sendMessage("§cEste mundo não tem uma borda.");
								return true;
							}
							WorldBorder wb = pl.getWorld().getWorldBorder();
							double valor = v * 2.0D;
							double fim = wb.getSize() + valor;
							wb.setSize(fim);
							p.sendMessage("§aVocê adicionou " + format(valor) + " blocos na borda e agora ela tem " + format(fim) + " blocos.");
						}
						return true;
					}
					if (args[0].equalsIgnoreCase("diminuir")) {
						if (isInt(args[1])) {
							double v = Double.valueOf(args[1]).doubleValue();
							if (pl.getWorld().getWorldBorder() == null) {
								p.sendMessage("§cEste mundo não tem uma borda.");
								return true;
							}
							WorldBorder wb = pl.getWorld().getWorldBorder();
							double valor = v * 2.0D;
							double fim = wb.getSize() - valor;
							wb.setSize(fim);
							p.sendMessage("§aVocê removeu " + format(valor) + " blocos na borda e agora ela tem " + format(fim) + " blocos.");
						}
						return true;
					}
				}
			} else {
				if (args.length == 0) {
					if (Bukkit.getWorld("world").getWorldBorder() == null) {
						p.sendMessage("§cEste mundo não tem uma borda.");
						return true;
					}
					WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
					double raio = wb.getSize() / 2.0D;
					p.sendMessage("§fMundo: §7" + Bukkit.getWorld("world").getName());
					p.sendMessage("§fRaio: §7" + format(raio));
					p.sendMessage("§fCentro: §7X: " + format(wb.getCenter().getX()) + " Z: " + format(wb.getCenter().getZ()));
					p.sendMessage("§fTamanho da borda: §7" + format(wb.getSize()));
					return true;
				}
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("resetar")) {
						if (Bukkit.getWorld("world").getWorldBorder() == null) {
							p.sendMessage("§cEste mundo não tem uma borda.");
							return true;
						}
						WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
						wb.setCenter(0.0D, 0.0D);
						wb.setSize(20000.0D);
						p.sendMessage("§aBorda resetada para " + format(wb.getSize()) + ".");
						return true;
					}
				}
				if (args.length >= 2) {
					if (args[0].equalsIgnoreCase("aumentar")) {
						if (isInt(args[1])) {
							double v = Double.valueOf(args[1]).doubleValue();
							if (Bukkit.getWorld("world").getWorldBorder() == null) {
								p.sendMessage("§cEste mundo não tem uma borda.");
								return true;
							}
							WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
							double valor = v * 2.0D;
							double fim = wb.getSize() + valor;
							wb.setSize(fim);
							p.sendMessage("§aVocê adicionou " + format(valor) + " blocos na borda e agora ela tem " + format(fim) + " blocos.");
						}
						return true;
					}
					if (args[0].equalsIgnoreCase("diminuir")) {
						if (isInt(args[1])) {
							double v = Double.valueOf(args[1]).doubleValue();
							if (Bukkit.getWorld("world").getWorldBorder() == null) {
								p.sendMessage("§cEste mundo não tem uma borda.");
								return true;
							}
							WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
							double valor = v * 2.0D;
							double fim = wb.getSize() - valor;
							wb.setSize(fim);
							p.sendMessage("§aVocê removeu " + format(valor) + " blocos na borda e agora ela tem " + format(fim) + " blocos.");
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	public static String format(double value) {
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.forLanguageTag("en-US"));
		if (value <= 1.0D) {
			return numberFormat.format(value).concat(" ").concat("");
		}
		return numberFormat.format(value).concat(" ").concat("").replace(",", ".").replace(" ", "");
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
