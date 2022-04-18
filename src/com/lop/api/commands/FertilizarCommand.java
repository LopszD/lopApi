package com.wandy.api.commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.massivecore.ps.PS;
import com.wandy.api.utils.partículas.ParticleEffect;

public class FertilizarCommand implements CommandExecutor {
	
	public static HashMap<String, Date> fertilizar = new HashMap<String, Date>();

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("fertilizar")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		if (!sender.hasPermission("wandy.fertilizar")) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (isBlocked(p.getName())) {
			p.sendMessage("§cAguarde até " + getDate(p.getName()) + " para poder utilizar este comando novamente.");
			return true;
		}
		if (BoardColl.get().getFactionAt(PS.valueOf(p.getLocation())).getId().equalsIgnoreCase("safezone")) {
			p.sendMessage("§cVocê não pode utilizar este comando neste local.");
			return true;
		}
		if (BoardColl.get().getFactionAt(PS.valueOf(p.getLocation())).getId().equalsIgnoreCase("warzone")) {
			p.sendMessage("§cVocê não pode utilizar este comando neste local.");
			return true;
		}
		if (p.getWorld().getName().equals("minas")) {
			p.sendMessage("§cVocê não pode utilizar este comando neste mundo.");
			return true;
		}
		if (p.hasPermission("wandy.fertilizar.semdelay")) {
			int i = 0;
			int xx = 10;
			int yy = 20;
			int zz = 10;
			for (int x = -xx; x <= xx; x++) {
				for (int y = -yy; y <= yy; y++) {
					for (int z = -zz; z <= zz; z++) {
						Block b = p.getLocation().clone().add(x, y, z).getBlock();
						if (b.getType().equals(Material.NETHER_WARTS)) {
							if (b.getData() < 3) {
								i++;
								ParticleEffect.VILLAGER_HAPPY.display(1.0F, 1.0F, 1.0F, 20.0F, 70, b.getLocation(), 16.0D);
								b.setData((byte) 3);
							}
						}
						if (b.getType().equals(Material.CROPS)) {
							if (b.getData() < 7) {
								i++;
								ParticleEffect.VILLAGER_HAPPY.display(1.0F, 1.0F, 1.0F, 20.0F, 70, b.getLocation(), 16.0D);
								b.setData((byte) 7);
							}
						}
						if (b.getType().equals(Material.PUMPKIN_SEEDS)) {
							if (b.getData() < 7) {
								i++;
								ParticleEffect.VILLAGER_HAPPY.display(1.0F, 1.0F, 1.0F, 20.0F, 70, b.getLocation(), 16.0D);
								b.setData((byte) 7);
							}
						}
						if (b.getType().equals(Material.MELON_SEEDS)) {
							if (b.getData() < 7) {
								i++;
								ParticleEffect.VILLAGER_HAPPY.display(1.0F, 1.0F, 1.0F, 20.0F, 70, b.getLocation(), 16.0D);
								b.setData((byte) 7);
							}
						}
						if (b.getType().equals(Material.POTATO)) {
							if (b.getData() < 7) {
								i++;
								ParticleEffect.VILLAGER_HAPPY.display(1.0F, 1.0F, 1.0F, 20.0F, 70, b.getLocation(), 16.0D);
								b.setData((byte) 7);
							}
						}
						if (b.getType().equals(Material.CARROT)) {
							if (b.getData() < 7) {
								i++;
								ParticleEffect.VILLAGER_HAPPY.display(1.0F, 1.0F, 1.0F, 20.0F, 70, b.getLocation(), 16.0D);
								b.setData((byte) 7);
							}
						}
					}
				}
			}
			if (i == 0) {
				p.sendMessage("§cNão existe blocos fertilizáveis onde você está.");
				return true;
			}
			p.sendMessage("§eVocê fertilizou todos as plantações em um raio de 10 blocos.");
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 10.0F, 10.0F);
			return true;
		}
		Date now = new Date();
		int min = now.getMinutes() + 30;
		now.setMinutes(min);
		fertilizar.put(p.getName(), now);
		int xx = 10;
		int yy = 20;
		int zz = 10;
		for (int x = -xx; x <= xx; x++) {
			for (int y = -yy; y <= yy; y++) {
				for (int z = -zz; z <= zz; z++) {
					Block b = p.getLocation().clone().add(x, y, z).getBlock();
					if (b.getType().equals(Material.NETHER_WARTS)) {
						if (b.getData() < 3) {
							ParticleEffect.VILLAGER_HAPPY.display(1.0F, 1.0F, 1.0F, 20.0F, 70, b.getLocation(), 16.0D);
							b.setData((byte) 3);
						}
					}
					if (b.getType().equals(Material.CROPS)) {
						if (b.getData() < 7) {
							ParticleEffect.VILLAGER_HAPPY.display(1.0F, 1.0F, 1.0F, 20.0F, 70, b.getLocation(), 16.0D);
							b.setData((byte) 7);
						}
					}
					if (b.getType().equals(Material.PUMPKIN_SEEDS)) {
						if (b.getData() < 7) {
							ParticleEffect.VILLAGER_HAPPY.display(1.0F, 1.0F, 1.0F, 20.0F, 70, b.getLocation(), 16.0D);
							b.setData((byte) 7);
						}
					}
					if (b.getType().equals(Material.MELON_SEEDS)) {
						if (b.getData() < 7) {
							ParticleEffect.VILLAGER_HAPPY.display(1.0F, 1.0F, 1.0F, 20.0F, 70, b.getLocation(), 16.0D);
							b.setData((byte) 7);
						}
					}
					if (b.getType().equals(Material.POTATO)) {
						if (b.getData() < 7) {
							ParticleEffect.VILLAGER_HAPPY.display(1.0F, 1.0F, 1.0F, 20.0F, 70, b.getLocation(), 16.0D);
							b.setData((byte) 7);
						}
					}
					if (b.getType().equals(Material.CARROT)) {
						if (b.getData() < 7) {
							ParticleEffect.VILLAGER_HAPPY.display(1.0F, 1.0F, 1.0F, 20.0F, 70, b.getLocation(), 16.0D);
							b.setData((byte) 7);
						}
					}
				}
			}
		}
		p.sendMessage("§eVocê fertilizou todos as plantações em um raio de 10 blocos.");
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
		return false;
	}

	public static boolean isBlocked(String nome) {
		if (fertilizar.containsKey(nome)) {
			Date span = fertilizar.get(nome);
			Date now = new Date();
			if (now.after(span)) {
				fertilizar.remove(nome);
			} else {
				return true;
			}
		}
		return false;
	}

	public static String getDate(String nome) {
		Date now = fertilizar.get(nome);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return format.format(now);
	}
}
