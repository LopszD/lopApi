package com.wandy.api.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EnchantCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o sender � um player
		if (!(s instanceof Player)) {
			s.sendMessage("�cApenas usu�rios in-game podem executar este comando.");
			return true;
		}

		// Se o sender for o console ele precisa especificar um player
		if (args.length != 2) {
			s.sendMessage("�cComando inv�lido.");
			return true;
		}

		// Pegando o encantamento e verificando se � 1 encantamento valido
		String en = args[0].toUpperCase();
		Enchantment ench = getEnchantment(en);
		if (ench == null) {
			s.sendMessage("�cEncantamento inv�lido.");
			return true;
		}

		// Pegando o level e verificando se � 1 level valido
		int level;
		try {
			level = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			s.sendMessage("�cN�mero inv�lido.");
			return true;
		}

		// Pegando o player o item na sua m�o e verificando se � valido
		Player p = (Player) s;
		ItemStack hand = p.getItemInHand();
		if (hand == null || hand.getType() == Material.AIR) {
			s.sendMessage("�cEste item n�o � inv�lido.");
			return true;
		}

		// Adicionando o encantamento no item
		hand.addUnsafeEnchantment(ench, level);
		s.sendMessage("�aItem encantado com sucesso!");
		return true;
	}

	// M�todo para pegar o encantamento
	private Enchantment getEnchantment(String enchant) {
		try {
			return Enchantment.getByName(enchant);
		} catch (Throwable e) {
			return null;
		}
	}
}