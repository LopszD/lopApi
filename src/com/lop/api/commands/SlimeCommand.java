package com.wandy.api.commands;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wandy.api.listeners.SlimeChunkListener;
import com.wandy.api.utils.fanciful.FancyMessage;

public class SlimeCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("slimechunk")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("wandy.slime")) {
			p.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("som")) {
				p.playSound(p.getLocation(), Sound.SLIME_WALK, 1.0F, 0.8F);
				return true;
			}
		}
		if (SlimeChunkListener.seeslime.contains(p.getName())) {
			SlimeChunkListener.seeslime.remove(p.getName());
			p.sendMessage("§cVocê desativou o alerta de Slimes Chunks.");
			return true;
		}
		SlimeChunkListener.seeslime.add(p.getName());
		p.sendMessage("");
		p.sendMessage("§a Você ativou o alerta de SlimesChunks.");
		p.sendMessage("§a Ao entrar em uma chunk de slime, você será notificado");
		new FancyMessage("§a com ").color(ChatColor.GREEN).then("§a§neste").color(ChatColor.GREEN).command("/slimechunk som").then(" §asom.").color(ChatColor.GREEN).send(p);
		p.sendMessage("");
		return false;
	}
}
