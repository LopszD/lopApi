package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wandy.kits.utils.UltimateFancy;

import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PromoteCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("promover")){
			if (sender.hasPermission("wandy.promover")){
				if (args.length == 0) {
					sender.sendMessage("§cUtilize /promover <usuário> para prover um usuário.");
					return true;
				} else if (args.length == 1){
					String p2 = args[0];
					sendCargosP(sender, p2);
					return true;
				} else if (args.length == 2){
					PermissionGroup group = PermissionsEx.getPermissionManager().getGroup(args[1]);
					if (group == null){
						sender.sendMessage("§cEste grupo não existe.");
						return true;
					}
					Player p2 = Bukkit.getPlayerExact(args[0]);
					PermissionUser user = PermissionsEx.getUser(args[0]);
					user.removeGroup("default");
					user.addGroup(group);
					
					if (p2 != null){
					}
					
					sender.sendMessage("§eUsuário promovido ao cargo de " + group.getPrefix());
					return true;
				}
			} else {
				sender.sendMessage("§cVocê não tem permissão para execultar esse comando.");
				return true;
			}
		} else if (command.getName().equalsIgnoreCase("rebaixar")){
			if (sender.hasPermission("wandy.rebaixar")){
				if (args.length == 0) {
					sender.sendMessage("§cUtilize /rebaixar <usuário> para rebaixar um usuário.");
					return true;
				} else if (args.length == 1){
					String p2 = args[0];
					sendCargosR(sender, p2);
					return true;
				} else if (args.length == 2){
					PermissionGroup group = PermissionsEx.getPermissionManager().getGroup(args[1]);
					if (group == null){
						sender.sendMessage("§cEste grupo não existe.");
						return true;
					}
					Player p2 = Bukkit.getPlayerExact(args[0]);
					PermissionUser user = PermissionsEx.getUser(args[0]);
					if (user.inGroup(group)){
						user.removeGroup(group);
						user.addGroup("default");
					} else {
						sender.sendMessage("§cEste usuário não pertence a este grupo.");
						return true;
					}

					if (p2 != null){
					}
					
					sender.sendMessage("§eUsuário removido do cargo " + group.getPrefix());
					return true;
				}
			} else {
				sender.sendMessage("§cVocê não tem permissão para execultar esse comando.");
				return true;
			}
		}
		return false;
	}
	
	public void sendCargosP(CommandSender p, String p2){
		UltimateFancy output = new UltimateFancy();
		p.sendMessage("");
		p.sendMessage("§eEscolha um cargo atualmente disponível:");
		p.sendMessage("");
		for (PermissionGroup gp : PermissionsEx.getPermissionManager().getGroupList()){
			if (gp.getName().toLowerCase().contains("master")) break;
			if (gp.getName().toLowerCase().contains("youtuber")) break;
			if (gp.getName().toLowerCase().contains("wandy")) break;
			if (gp.getName().toLowerCase().contains("phunish")) break;
			if (gp.getName().toLowerCase().contains("ghuenon")) break;
			if (gp.getName().toLowerCase().contains("betther")) break;
			if (gp.getName().toLowerCase().contains("default")) break;
			output.next().text("  " + gp.getPrefix() + "§f" + p2).clickSuggestCmd("/promover " + p2 + " " + gp.getName()).next().text("\n");
		}
		output.send(p);
	}

	public void sendCargosR(CommandSender p, String p2){
		UltimateFancy output = new UltimateFancy();
		p.sendMessage("");
		p.sendMessage("§eEscolha um cargo atualmente disponível:");
		p.sendMessage("");
		for (PermissionGroup gp : PermissionsEx.getPermissionManager().getGroupList()){
			if (gp.getName().toLowerCase().contains("youtuber")) break;
			if (gp.getName().toLowerCase().contains("wandy")) break;
			if (gp.getName().toLowerCase().contains("phunish")) break;
			if (gp.getName().toLowerCase().contains("ghuenon")) break;
			if (gp.getName().toLowerCase().contains("betther")) break;
			if (gp.getName().toLowerCase().contains("default")) break;
			output.next().text("  " + gp.getPrefix() + "§f" + p2).clickSuggestCmd("/rebaixar " + p2 + " " + gp.getName()).next().text("\n");
		}
		output.send(p);
	}
}
