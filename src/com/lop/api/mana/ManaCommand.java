package com.wandy.api.mana;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wandy.api.Main;

public class ManaCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("mana")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
				return true;
			} else {
				DecimalFormat df = new DecimalFormat("###,###");
				if (args.length == 0) {
					if (Main.getInstance().maniacs.containsKey(sender.getName())) {
						ManaModel model = Main.getInstance().maniacs.get(sender.getName());
						sender.sendMessage("§eVocê tem: §f" + df.format(model.getMana()).replace(",", ".") + " §emana.");
						return true;
					}
				} else if (args.length == 1) {
					Player p = Bukkit.getPlayer(args[0]);
					if (p == null) {
						sender.sendMessage("§cEste usuário não está on-line.");
						return true;
					}
					ManaModel model = Main.getInstance().maniacs.get(p.getName());
					sender.sendMessage("§e" + p.getName() + " tem: §f" + df.format(model.getMana()).replace(",", ".") + " §emana.");
				} else if (args.length == 3) {
					if (!(sender.hasPermission("wandy.mana"))) {
						sender.sendMessage("§cVocê não tem permissão para executar este comando.");
						return true;
					}
					if (!(args[0].equalsIgnoreCase("setar"))) {
						sender.sendMessage("§cUtilize /mana setar <usuário> <quantidade>.");
						return true;
					}
					Player p = Bukkit.getPlayer(args[1]);
					if (p == null) {
						sender.sendMessage("§cEste usuário não está on-line.");
						return true;
					}
					Integer mana = null;
					try {
						mana = Integer.valueOf(args[2]);
					} catch (NumberFormatException e) {
						sender.sendMessage("§cO número é inválido.");
						return true;
					}
					ManaModel model = Main.getInstance().maniacs.get(p.getName());
					model.withdrawMana(model.getMana());
					model.addMana(mana);
					sender.sendMessage("§aVocê setou " + mana + " mana para " + p.getName() + "!");
				}
			}
		}
		return false;
	}
}
