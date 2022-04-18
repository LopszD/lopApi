package com.wandy.api.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.wandy.api.utils.Helper;
import com.wandy.api.utils.ScrollerInventory;

public class ColetarCommand implements CommandExecutor {

	public static HashMap<String, List<Integer>> collectores = new HashMap<String, List<Integer>>();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("coletar")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
				return true;
			} else {
				Player p = (Player) sender;
				if (sender.hasPermission("wandy.coletar")) {
					if (args.length == 0 || args.length > 2) {
						sender.sendMessage("§cUtilize /coletar <ID/lista/limpar>.");
						return true;
					} else if (args.length == 1) {
						if (Helper.isInteger(args[0])) {
							int id = Integer.parseInt(args[0]);
							List<Integer> ids = (collectores.containsKey(p.getName()) ? collectores.get(p.getName()) : new ArrayList<>());
							try {
								if (new ItemStack(id) != null) {
									ids.add(id);
									p.sendMessage("§aVocê adicionou o ID '" + id + "' na sua lista de drops.");
									collectores.put(p.getName(), ids);
									return true;
								}
							} catch (Exception e) {
								sender.sendMessage("§cO ID inserido é inválido.");
								return true;
							}
						} else if (args[0].equalsIgnoreCase("lista")) {
							ArrayList<ItemStack> items = new ArrayList<>();
							if (collectores.containsKey(p.getName())) {
								List<Integer> a = ColetarCommand.collectores.get(p.getName());
								a.forEach(id -> {
									items.add(new ItemStack(id));
								});
								ScrollerInventory scroll = new ScrollerInventory(items, "§8Drops coletáveis", p);
								ScrollerInventory.users.put(p.getUniqueId(), scroll);
							} else {
								sender.sendMessage("§cVocê não tem drops especificos para coleta.");
								return true;
							}
							return true;
						} else if (args[0].equalsIgnoreCase("limpar")) {
							if (collectores.containsKey(p.getName())) {
								collectores.remove(p.getName());
								sender.sendMessage("§aLista de drops especificos limpa, agora você coletará todos drops.");
								return true;
							} else {
								sender.sendMessage("§cVocê não tem drops especificos para serem coletados.");
								return true;
							}
						} else {
							sender.sendMessage("§cUtilize /coletar limpar <ID>.");
							return true;
						}
					} else if (args.length == 2) {
						if (Helper.isInteger(args[1])) {
							if (args[0].equalsIgnoreCase("remover")) {
								if (collectores.containsKey(p.getName())) {
									int id = Integer.parseInt(args[1]);
									if (collectores.get(p.getName()).contains(id)) {
										if (collectores.get(p.getName()).size() == 1) {
											collectores.remove(p.getName());
										} else {
											collectores.get(p.getName()).remove(id);
										}
										sender.sendMessage("§cO ID " + id + " foi removido da sua lista de drops.");
										return true;
									} else {
										sender.sendMessage("§cO ID inserido não estava na sua lista de drops especificos para serem coletados.");
										return true;
									}
								} else {
									sender.sendMessage("§cVocê não tem drops especificos para serem coletados.");
									return true;
								}
							} else {
								sender.sendMessage("§cUtilize /coletar remover <ID>.");
								return true;
							}
						} else {
							sender.sendMessage("§cUtilize /coletar remover <ID>.");
							return true;
						}
					}
				} else {
					sender.sendMessage("§cVocê não tem permissão para executar este comando.");
					return true;
				}
			}
		}
		return false;
	}
}