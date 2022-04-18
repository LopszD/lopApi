package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wandy.api.utils.fanciful.FancyMessage;

public class ChecarCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("check")) {
			if (!p.hasPermission("wandy.check")) {
				sender.sendMessage("§cVocê não tem permissão para executar este comando.");
				return true;
			}
			if ((args.length == 0) || (args.length > 1)) {
				p.sendMessage("§cUtilize /check <usuário> para mostrar as informações dele.");
				return true;
			}
			Player p1 = Bukkit.getPlayer(args[0]);
			if (p1 == null) {
				p.sendMessage("§cEste usuário não está on-line.");
				return true;
			}
			int vi;
			vi = 20;
			vi = (int) p1.getHealth();
			p.sendMessage("§4 ");
			p.sendMessage("§a Informações do usuário:");
			p.sendMessage(" ");
			new FancyMessage("").then("  §fNome: §7" + p1.getName() + " ").then("§a(Kickar)").tooltip("§aKickar este usuário.").command("/kick " + p1.getName()).send(p);
			p.sendMessage("  §fIP: §7" + p1.getAddress().getHostName());
			new FancyMessage("").then("  §fModo de jogo: §7" + p1.getGameMode() + " ").then("§a(").then("§aCriativo").tooltip("§aAlterar o modo de jogo.").command("/gamemode 1 " + p1.getName()).then("§a, ").then("§aAventura").tooltip("§aAlterar o modo de jogo.").command("/gamemode 2 " + p1.getName()).then("§a, ").then("§aSurvival").tooltip("§aAlterar o modo de jogo.").command("/gamemode 0 " + p1.getName()).then("§a)").send(p);
			new FancyMessage("").then("  §fVida: §7" + getBar(vi) + " §8- §c❤").then("§a(").then("§aCurar").tooltip("§aCurar este usuário.").command("/heal " + p1.getName()).then("§a, ").then("§aMatar").tooltip("§aMatar este usuário.").command("/kill " + p1.getName()).then("§a)").send(p);
			p.sendMessage("§5 ");
		}
		return false;
	}

	public static String getBar(int vi) {
		String bar = "";
		int foi = 0;
		for (int i = 0; i < vi; i++) {
			bar = bar + "§c|";
			foi++;
		}
		if (foi < 20) {
			int fal = 20 - vi;
			for (int i = 0; i < fal; i++) {
				bar = bar + "§7|";
			}
		}
		return bar;
	}
}
