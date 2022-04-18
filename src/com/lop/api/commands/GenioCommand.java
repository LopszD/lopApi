 package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wandy.api.utils.ItemBuilder;

public class GenioCommand implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("genio")) || (!sender.hasPermission("wandy.genio"))) {
			sender.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (args.length == 0) {
			p.sendMessage("�cUtilize /genio <usu�rio>.");
			return true;
		}
		if (args.length == 1) {
			Player p1 = Bukkit.getServer().getPlayer(args[0]);
			if (p1 == null) {
				sender.sendMessage("�cEste usu�rio n�o est� on-line.");
				return true;
			}
			if (args[0].equalsIgnoreCase(p.getName())) {
				p.sendMessage("�cVoc� n�o pode executar este comando com voc� mesmo.");
				return true;
			}
			p.sendMessage("�aVoc� est� realizando o desejo do usu�rio " + p1.getName() + "�a!");
			p1.sendTitle("�e�lParab�ns", "�aVoc� foi escolhido!");
			p1.sendMessage("");
			p1.sendMessage(" �e�l[G�NIO M�GICO] �aVoc� tem o direito de �e1 pedido�a!");
			p1.sendMessage(" �aEscreva na placa ou diga-me no chat o que voc� deseja.");
			p1.sendMessage("");
			p1.playSound(p1.getLocation(), Sound.SUCCESSFUL_HIT, 10.F, 10F);
			p1.getInventory().addItem(new ItemBuilder(Material.SIGN).build());
			return true;
		}
		return false;
	}
}
