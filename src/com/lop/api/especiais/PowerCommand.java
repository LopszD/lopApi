package com.wandy.api.especiais;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PowerCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("poder")) {
			if (!sender.hasPermission("whyze.especiais")) {
				sender.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
				return true;
			}
			if (args.length == 0) {
				sender.sendMessage("�cUtilize /poder <P/M> [usu�rio] para pegar um item de poder.");
				return true;
			}
			if (args.length == 1) {
				if (!(sender instanceof Player)) {
					sender.sendMessage("�cApenas usu�rios in-game podem executar este comando.");
					return true;
				}
				Player p = (Player) sender;
				if (args[0].equalsIgnoreCase("P")) {
					ItemStack a = new ItemStack(Material.PAPER);
					ItemMeta am = a.getItemMeta();
					am.setDisplayName("�6+1 de Poder Instant�neo");
					List<String> lore = new ArrayList<String>();
					lore.add("�7Ativando este item, voc�");
					lore.add("�7ganhar� 1 ponto de poder.");
					am.setLore(lore);
					a.setItemMeta(am);

					p.getInventory().addItem(a);
					p.sendMessage("�aVoc� recebeu 1x poder instant�neo.");
					p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 10.0F, 10.0F);
					return true;
				}
				if (args[0].equalsIgnoreCase("M")) {
					ItemStack a = new ItemStack(Material.NETHER_STAR);
					ItemMeta am = a.getItemMeta();
					am.setDisplayName("�6+1 de Poder M�ximo");
					List<String> lore = new ArrayList<String>();
					lore.add("�7Ativando este item, voc� aumenta");
					lore.add("�7 1 ponto em seu poder m�ximo.");
					lore.add("�1");
					lore.add("�7 * Limite de poder m�ximo: 20");
					am.setLore(lore);
					a.setItemMeta(am);

					p.getInventory().addItem(a);
					p.sendMessage("�aVoc� recebeu 1x poder m�ximo.");
					p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 10.0F, 10.0F);
					return true;
				}
				p.sendMessage("�cTipo de item n�o encontrado.");

				return true;
			}
			if (args.length == 2) {
				Player target = Bukkit.getPlayerExact(args[1]);
				if ((target == null) || (!(target instanceof Player))) {
					sender.sendMessage("�cEste usu�rio n�o est� on-line.");
					return true;
				}
				if (args[0].equalsIgnoreCase("P")) {
					ItemStack a = new ItemStack(Material.PAPER);
					ItemMeta am = a.getItemMeta();
					am.setDisplayName("�6+1 de Poder Instant�neo");
					List<String> lore = new ArrayList<String>();
					lore.add("�7Ativando este item, voc�");
					lore.add("�7ganhar� 1 ponto de poder.");
					am.setLore(lore);
					a.setItemMeta(am);

					target.getInventory().addItem(a);
					target.playSound(target.getLocation(), Sound.SUCCESSFUL_HIT, 10.0F, 10.0F);
					target.sendMessage("�aVoc� recebeu 1x poder instant�neo.");
					sender.sendMessage("�aEnviando 1x poder instant�neo para " + target.getName() + ".");
					return true;
				}
				if (args[0].equalsIgnoreCase("M")) {
					ItemStack a = new ItemStack(Material.NETHER_STAR);
					ItemMeta am = a.getItemMeta();
					am.setDisplayName("�6+1 de Poder M�ximo");
					List<String> lore = new ArrayList<String>();
					lore.add("�7Ativando este item, voc� aumenta");
					lore.add("�7 1 ponto em seu poder m�ximo.");
					lore.add("�1");
					lore.add("�7 * Limite de poder m�ximo: 20");
					am.setLore(lore);
					a.setItemMeta(am);

					target.getInventory().addItem(a);
					target.playSound(target.getLocation(), Sound.SUCCESSFUL_HIT, 10.0F, 10.0F);
					target.sendMessage("�aVoc� recebeu 1x poder m�ximo.");
					sender.sendMessage("�aEnviando 1x poder m�ximo para " + target.getName() + ".");
					return true;
				}
				sender.sendMessage("�cItem n�o encontrado.");

				return true;
			}
			if (args.length == 3) {
				Player target = Bukkit.getPlayerExact(args[1]);
				if ((target == null) || (!(target instanceof Player))) {
					sender.sendMessage("�cEste usu�rio n�o est� on-line.");
					return true;
				}
				int qnt = Integer.valueOf(args[2]).intValue();
				if (args[0].equalsIgnoreCase("P")) {
					ItemStack a = new ItemStack(Material.PAPER, qnt);
					ItemMeta am = a.getItemMeta();
					am.setDisplayName("�6+1 de Poder Instant�neo");
					List<String> lore = new ArrayList<String>();
					lore.add("�7Ativando este item, voc�");
					lore.add("�7ganhar� 1 ponto de poder.");
					am.setLore(lore);
					a.setItemMeta(am);

					target.getInventory().addItem(a);
					target.playSound(target.getLocation(), Sound.SUCCESSFUL_HIT, 10.0F, 10.0F);
					target.sendMessage("�aVoc� recebeu 1x poder instant�neo.");
					sender.sendMessage("�aEnviando 1x poder instant�neo para " + target.getName() + ".");
					return true;
				}
				if (args[0].equalsIgnoreCase("M")) {
					ItemStack a = new ItemStack(Material.NETHER_STAR, qnt);
					ItemMeta am = a.getItemMeta();
					am.setDisplayName("�6+1 de Poder M�ximo");
					List<String> lore = new ArrayList<String>();
					lore.add("�7Ativando este item, voc� aumenta");
					lore.add("�7 1 ponto em seu poder m�ximo.");
					lore.add("�1");
					lore.add("�7 * Limite de poder m�ximo: 20");
					am.setLore(lore);
					a.setItemMeta(am);

					target.getInventory().addItem(a);
					target.playSound(target.getLocation(), Sound.SUCCESSFUL_HIT, 10.0F, 10.0F);
					target.sendMessage("�aVoc� recebeu 1x poder m�ximo.");
					sender.sendMessage("�aEnviando 1x poder m�ximo para " + target.getName() + ".");
					return true;
				}
				sender.sendMessage("�cItem n�o encontrado.");
				return true;
			}
			if (args.length > 3) {
				sender.sendMessage("�cUtilize /poder <P/M> [usu�rio] para pegar um item de poder.");
				return true;
			}
		}
		return false;
	}
}
