package com.wandy.api.commands;

import java.util.Date;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.wandy.api.especiais.listeners.BoosterDropListener;
import com.wandy.api.sql.Manager;
import com.wandy.api.stack.Evento_Spawn;

public class BoosterDropCommand implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("boosterdrop")) || (!(sender instanceof Player))) {
			sender.sendMessage("�cApenas usu�rios in-game podem executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (args.length == 2) {
			if (p.hasPermission("wandy.booster")) {
				if (args[0].equalsIgnoreCase("remover")) {
					String nome = args[1];
					if (!Manager.checarContaExiste(nome)) {
						p.sendMessage("�cConta n�o encontrada.");
						return true;
					}
					String nick = Manager.pegarNomePlayer(nome);
					if (!BoosterDropListener.bos.containsKey(nick)) {
						p.sendMessage("�cEste usu�rio n�o est� com o booster ativado.");
						return true;
					}
					BoosterDropListener.removerBooster(nick);
					p.sendMessage("�aVoc� removeu o booster do usu�rio " + nick + ".");
					return true;
				}
			}
		}
		if (!BoosterDropListener.bos.containsKey(p.getName())) {
			p.sendMessage("�cVoc� n�o tem booster ativado.");
			return true;
		}
		Date span = BoosterDropListener.bosd.get(p.getName());
		Date now = new Date();
		if (now.after(span)) {
			BoosterDropListener.removerBooster(p.getName());
			p.sendMessage("�cVoc� n�o tem um booster ativado.");
			return true;
		}
		EntityType tipo = BoosterDropListener.bos.get(p.getName());
		String nome = Evento_Spawn.translateMob(tipo.getName());
		String data = BoosterDropListener.getData(p.getName()).replace(" ", " �s ");
		p.sendMessage("�1");
		p.sendMessage("�6 B�nus de drops: �7(+2x de quantidade)");
		p.sendMessage("�f � " + nome + " �7T�rmino: " + data);
		p.sendMessage("�2");
		return false;
	}
}
