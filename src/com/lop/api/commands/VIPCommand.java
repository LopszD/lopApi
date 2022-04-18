package com.wandy.api.commands;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wandy.api.Main;
import com.wandy.api.listeners.EntrarListener;
import com.wandy.api.sql.Manager;

public class VIPCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("vip")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("wandy.vip")) {
			p.sendMessage("§cVocê não tem permissão para entrar neste mundo.");
			return true;
		}
		if (args.length == 0) {
			if (!Manager.checarLocExiste("VIP")) {
				p.sendMessage("§cEstá localização ainda não foi definida.");
				return true;
			}
			if (p.getLocation().getWorld().getName().equals(Manager.pegarLocation("VIP").getWorld().getName())) {
				p.sendMessage("§cVocê já está no mundo VIP.");
				return true;
			}
			Random r = new Random();
			int rn = r.nextInt(4);
			if (rn == 0) {
				Location loc = Manager.pegarLocation("VIP").clone().add(0.0D, 0.0D, Main.spawn.intValue());
				p.teleport(loc);
			}
			if (rn == 1) {
				Location loc = Manager.pegarLocation("VIP").clone().subtract(Main.spawn.intValue(), 0.0D, 0.0D);
				p.teleport(loc);
			}
			if (rn == 2) {
				Location loc = Manager.pegarLocation("VIP").clone().subtract(0.0D, 0.0D, Main.spawn.intValue());
				p.teleport(loc);
			}
			if (rn == 3) {
				Location loc = Manager.pegarLocation("VIP").clone().add(Main.spawn.intValue(), 0.0D, 0.0D);
				p.teleport(loc);
			}
			if (rn == 4) {
				Location loc = Manager.pegarLocation("VIP").clone().add(0.0D, 0.0D, Main.spawn.intValue());
				p.teleport(loc);
			}
			EntrarListener.mandarAction(p, "§eTeletransportado para a VIP com sucesso!");
			p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 1.0F);
			return true;
		}
		if (args.length >= 1) {
			if (p.hasPermission("wandy.setarspawn")) {
				if (args[0].equalsIgnoreCase("setar")) {
					Location loc = p.getLocation();
					if (Manager.checarLocExiste("VIP")) {
						Manager.updateLocation("VIP", loc);
						p.sendMessage("§aVIP setado com sucesso.");
						return true;
					}
					Manager.setarLocation("VIP", loc);
					p.sendMessage("§aVIP setado com sucesso.");
					return true;
				}
			}
			if (p.getLocation().getWorld().getName().equals(Manager.pegarLocation("VIP").getWorld().getName())) {
				p.sendMessage("§cVocê já está no mundo VIP.");
				return true;
			}
			if (!Manager.checarLocExiste("VIP")) {
				p.sendMessage("§cEstá localização ainda não foi definida.");
				return true;
			}
			EntrarListener.mandarAction(p, "§eTeletransportado para a VIP com sucesso!");
			p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 1.0F);
			Random r = new Random();
			int rn = r.nextInt(4);
			if (rn == 1) {
				Location loc = Manager.pegarLocation("VIP").clone().subtract(Main.spawn.intValue(), 0.0D, 0.0D);
				p.teleport(loc);
				return true;
			}
			if (rn == 2) {
				Location loc = Manager.pegarLocation("VIP").clone().subtract(0.0D, 0.0D, Main.spawn.intValue());
				p.teleport(loc);
				return true;
			}
			if (rn == 3) {
				Location loc = Manager.pegarLocation("VIP").clone().add(Main.spawn.intValue(), 0.0D, 0.0D);
				p.teleport(loc);
				return true;
			}
			if (rn == 4) {
				Location loc = Manager.pegarLocation("VIP").clone().add(0.0D, 0.0D, Main.spawn.intValue());
				p.teleport(loc);
				return true;
			}
			if (rn == 0) {
				Location loc = Manager.pegarLocation("VIP").clone().add(0.0D, 0.0D, Main.spawn.intValue());
				p.teleport(loc);
				return true;
			}
			return true;
		}
		if (args.length > 1) {
			if (!Manager.checarLocExiste("VIP")) {
				p.sendMessage("§cEstá localização ainda não foi definida.");
				return true;
			}
			EntrarListener.mandarAction(p, "§eTeletransportado para a VIP com sucesso!");
			p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 1.0F);
			Random r = new Random();
			int rn = r.nextInt(4);
			if (rn == 1) {
				Location loc = Manager.pegarLocation("VIP").clone().subtract(Main.spawn.intValue(), 0.0D, 0.0D);
				p.teleport(loc);
				return true;
			}
			if (rn == 2) {
				Location loc = Manager.pegarLocation("VIP").clone().subtract(0.0D, 0.0D, Main.spawn.intValue());
				p.teleport(loc);
				return true;
			}
			if (rn == 3) {
				Location loc = Manager.pegarLocation("VIP").clone().add(Main.spawn.intValue(), 0.0D, 0.0D);
				p.teleport(loc);
				return true;
			}
			if (rn == 4) {
				Location loc = Manager.pegarLocation("VIP").clone().add(0.0D, 0.0D, Main.spawn.intValue());
				p.teleport(loc);
				return true;
			}
			if (rn == 0) {
				Location loc = Manager.pegarLocation("VIP").clone().add(0.0D, 0.0D, Main.spawn.intValue());
				p.teleport(loc);
				return true;
			}
			return true;
		}
		return false;
	}
}
