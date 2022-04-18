package com.wandy.api.punish;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import com.wandy.api.utils.fanciful.FancyMessage;

public class PunirS {
	
	@EventHandler
	public static void enviarJogador(Player p, String jogador) {
		if (!p.hasPermission("wandy.banirtudo")) {
			p.sendMessage("§b");
			p.sendMessage("§eSelecione um tipo de infração:");
			List<String> a = new ArrayList<String>();
			a.clear();
			a.add("§eCaps-Lock");
			a.add("§1");
			a.add("§fMutado por 60 minutos");
			a.add("§2");
			a.add("§eClique para selecionar!");

			new FancyMessage("").then("§f  Caps-Lock").suggest("/mute " + jogador + " 60m Caps-Lock").tooltip(a)
					.send(p);

			a.clear();
			a.add("§eDivulgação simples");
			a.add("§1");
			a.add("§fMutado por 30 minutos");
			a.add("§2");
			a.add("§eClique para selecionar!");

			new FancyMessage("").then("§7  Divulgação simples").suggest("/mute " + jogador + " 30m Divulgação simples")
					.tooltip(a).send(p);

			a.clear();
			a.add("§eFlood/SPAM");
			a.add("§1");
			a.add("§fMutado por 100 minutos");
			a.add("§2");
			a.add("§eClique para selecionar!");

			new FancyMessage("").then("§f  Flood/SPAM").suggest("/mute " + jogador + " 100m Flood/SPAM").tooltip(a)
					.send(p);

			a.clear();
			a.add("§eIniciativa de briga");
			a.add("§1");
			a.add("§fMutado por 100 minutos");
			a.add("§2");
			a.add("§eClique para selecionar!");

			new FancyMessage("").then("§f  Iniciativa de briga")
					.suggest("/mute " + jogador + " 100m Iniciativa de briga").tooltip(a).send(p);

			a.clear();
			a.add("§eIniciativa de flood");
			a.add("§1");
			a.add("§fMutado por 60 minutos");
			a.add("§2");
			a.add("§eClique para selecionar!");

			new FancyMessage("").then("§7  Iniciativa de flood")
					.suggest("/mute " + jogador + " 60m Iniciativa de flood").tooltip(a).send(p);

			a.clear();
			a.add("§eChat fake");
			a.add("§1");
			a.add("§fMutado por 30 minutos");
			a.add("§2");
			a.add("§eClique para selecionar!");

			new FancyMessage("").then("§f  Chat fake").suggest("/mute " + jogador + " 30m Chat Fake").tooltip(a)
					.send(p);

			a.clear();
			a.add("§eOfensa à jogador");
			a.add("§1");
			a.add("§fMutado por 120 minutos");
			a.add("§2");
			a.add("§eClique para selecionar!");

			new FancyMessage("").then("§f  Ofensa à jogador").suggest("/mute " + jogador + " 120m Ofensa à jogador")
					.tooltip(a).send(p);

			a.clear();
			a.add("§ePalavras inadequadas");
			a.add("§1");
			a.add("§fMutado por 150 minutos");
			a.add("§2");
			a.add("§eClique para selecionar!");

			new FancyMessage("").then("§f  Palavras inadequadas")
					.suggest("/mute " + jogador + " 150m Palavras inadequadas").tooltip(a).send(p);

			a.clear();
			a.add("§eHashTag");
			a.add("§1");
			a.add("§fMutado por 100 minutos");
			a.add("§2");
			a.add("§eClique para selecionar!");

			new FancyMessage("").then("§7  HashTag").suggest("/mute " + jogador + " 100m HashTag").tooltip(a).send(p);

			p.sendMessage("§3");

			return;
		}
		p.sendMessage("§b");
		p.sendMessage("§eSelecione um tipo de infração:");

		List<String> a = new ArrayList<String>();

		a.add("§eAbuso de bugs");
		a.add("§1");
		a.add("§fPermantente");
		a.add("§2");
		a.add("§eClique para selecionar!");

		new FancyMessage("").then("§f  Abuso de bugs").suggest("/ban " + jogador + " Abuso de Bugs").tooltip(a).send(p);

		a.clear();
		a.add("§eAntijogo");
		a.add("§1");
		a.add("§fPunido por 1 dia");
		a.add("§2");
		a.add("§eClique para selecionar!");

		new FancyMessage("").then("§7  Antijogo").suggest("/ban " + jogador + " 1d AntiJogo").tooltip(a).send(p);

		a.clear();
		a.add("§eCaps-Lock");
		a.add("§1");
		a.add("§fMutado por 60 minutos");
		a.add("§2");
		a.add("§eClique para selecionar!");

		new FancyMessage("").then("§f  Caps-Lock").suggest("/mute " + jogador + " 60m Caps-Lock").tooltip(a).send(p);

		a.clear();
		a.add("§eConta fake");
		a.add("§1");
		a.add("§fPermanente");
		a.add("§2");
		a.add("§eClique para selecionar!");

		new FancyMessage("").then("§7  Conta fake").suggest("/ban " + jogador + " Conta Fake").tooltip(a).send(p);

		a.clear();
		a.add("§eDivulgação grave");
		a.add("§1");
		a.add("§fPermanente");
		a.add("§2");
		a.add("§eClique para selecionar!");

		new FancyMessage("").then("§f  Divulgação grave").suggest("/ban " + jogador + " Divulgação grave").tooltip(a)
				.send(p);

		a.clear();
		a.add("§eDivulgação simples");
		a.add("§1");
		a.add("§fMutado por 30 minutos");
		a.add("§2");
		a.add("§eClique para selecionar!");

		new FancyMessage("").then("§7  Divulgação simples").suggest("/mute " + jogador + " 30m Divulgação simples")
				.tooltip(a).send(p);

		a.clear();
		a.add("§eFlood/SPAM");
		a.add("§1");
		a.add("§fMutado por 100 minutos");
		a.add("§2");
		a.add("§eClique para selecionar!");

		new FancyMessage("").then("§f  Flood/SPAM").suggest("/mute " + jogador + " 100m Flood/SPAM").tooltip(a).send(p);

		a.clear();
		a.add("§eHack");
		a.add("§1");
		a.add("§fPermanente");
		a.add("§2");
		a.add("§eClique para selecionar!");

		new FancyMessage("").then("§7  Hack").suggest("/ban " + jogador + " Hack").tooltip(a).send(p);

		a.clear();
		a.add("§eIniciativa de briga");
		a.add("§1");
		a.add("§fMutado por 100 minutos");
		a.add("§2");
		a.add("§eClique para selecionar!");

		new FancyMessage("").then("§f  Iniciativa de briga").suggest("/mute " + jogador + " 100m Iniciativa de briga")
				.tooltip(a).send(p);

		a.clear();
		a.add("§eIniciativa de flood");
		a.add("§1");
		a.add("§fMutado por 60 minutos");
		a.add("§2");
		a.add("§eClique para selecionar!");

		new FancyMessage("").then("§7  Iniciativa de flood").suggest("/mute " + jogador + " 60m Iniciativa de flood")
				.tooltip(a).send(p);

		a.clear();
		a.add("§eChat fake");
		a.add("§1");
		a.add("§fMutado por 30 minutos");
		a.add("§2");
		a.add("§eClique para selecionar!");

		new FancyMessage("").then("§f  Chat fake").suggest("/mute " + jogador + " 30m Chat Fake").tooltip(a).send(p);

		a.clear();
		a.add("§eNickname inapropriado");
		a.add("§1");
		a.add("§fPermanente");
		a.add("§2");
		a.add("§eClique para selecionar!");

		new FancyMessage("").then("§7  Nickname inapropriado").suggest("/ban " + jogador + " Nickname inapropriado")
				.tooltip(a).send(p);

		a.clear();
		a.add("§eOfensa à jogador");
		a.add("§1");
		a.add("§fMutado por 120 minutos");
		a.add("§2");
		a.add("§eClique para selecionar!");

		new FancyMessage("").then("§f  Ofensa à jogador").suggest("/mute " + jogador + " 120m Ofensa à jogador")
				.tooltip(a).send(p);

		a.clear();
		a.add("§eOfensa à staff/servidor");
		a.add("§1");
		a.add("§fPermanente");
		a.add("§2");
		a.add("§eClique para selecionar!");

		new FancyMessage("").then("§7  Ofensa à staff/servidor").suggest("/ban " + jogador + " Ofensa à staff/servidor")
				.tooltip(a).send(p);

		a.clear();
		a.add("§ePalavras inadequadas");
		a.add("§1");
		a.add("§fMutado por 150 minutos");
		a.add("§2");
		a.add("§eClique para selecionar!");

		new FancyMessage("").then("§f  Palavras inadequadas").suggest("/mute " + jogador + " 150m Palavras inadequadas")
				.tooltip(a).send(p);

		a.clear();
		a.add("§eHashTag");
		a.add("§1");
		a.add("§fMutado por 100 minutos");
		a.add("§2");
		a.add("§eClique para selecionar!");

		new FancyMessage("").then("§7  HashTag").suggest("/mute " + jogador + " 100m HashTag").tooltip(a).send(p);

		p.sendMessage("§3");
	}
}
