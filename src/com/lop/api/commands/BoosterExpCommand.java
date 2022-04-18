package com.wandy.api.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.nossr50.datatypes.skills.SkillType;
import com.wandy.api.especiais.listeners.BoosterExpListener;
import com.wandy.api.sql.Manager;

public class BoosterExpCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("boosterexp")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (args.length == 2) {
			if (p.hasPermission("wandy.booster")) {
				if (args[0].equalsIgnoreCase("remover")) {
					String nome = args[1];
					if (!Manager.checarContaExiste(nome)) {
						p.sendMessage("§cConta não encontrada.");
						return true;
					}
					String nick = Manager.pegarNomePlayer(nome);
					if (!BoosterExpListener.bos.containsKey(nick)) {
						p.sendMessage("§cEste usuário não está com o booster ativado.");
						return true;
					}
					BoosterExpListener.removerBooster(nick);
					p.sendMessage("§aVocê removeu o booster do usuário " + nick + ".");
					return true;
				}
			}
		}
		if (!BoosterExpListener.bos.containsKey(p.getName())) {
			p.sendMessage("§cVocê não tem um booster ativado.");
			return true;
		}
		SkillType tipo = BoosterExpListener.bos.get(p.getName());
		String nome = tipo.getName();
		String data = BoosterExpListener.bosd.get(p.getName()).split("@")[1].replace(" ", " às ");
		p.sendMessage("§1");
		p.sendMessage("§a Bônus de experiência: §7(+2x de quantidade)");
		p.sendMessage("§f • " + nome + " §7Término: " + data);
		p.sendMessage("§2");
		return false;
	}
}
