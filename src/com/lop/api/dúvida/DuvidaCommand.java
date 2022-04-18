package com.wandy.api.d�vida;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wandy.api.sql.MySQL;
import com.wandy.api.utils.fanciful.FancyMessage;

public class DuvidaCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("duvida")) || (!(sender instanceof Player))) {
			sender.sendMessage("�cApenas usu�rios in-game podem executar este comando.");
		}
		Player p = (Player) sender;
		if (args.length == 0) {
			if (!DAPI.temDuvida(p.getName())) {
				CentralD.abrirMenu(p);
				return true;
			}
			if (!DAPI.taRespondida(p.getName())) {
				CentralD.abrirMenu(p);
				return true;
			}
			CentralD.abrirMenu(p);
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("ajuda")) {
				if (!p.hasPermission("wandy.equipe")) {
					p.sendMessage("�1");
					p.sendMessage("�a/duvida �8- �7Criar uma nova d�vida.");
					p.sendMessage("�a/duvida ver �8- �7Ver sua d�vida criada.");
					p.sendMessage("�a/duvida deletar �8- �7Excluir uma d�vida.");
					p.sendMessage("�2");
					return true;
				}
				p.sendMessage("�1");
				p.sendMessage("�a/duvida �8- �7Criar uma nova d�vida.");
				p.sendMessage("�a/duvida ver �8- �7Ver sua d�vida criada.");
				p.sendMessage("�a/duvida deletar �8- �7Excluir uma d�vida.");
				p.sendMessage("�c/duvida responder <nick> <resposta> �8- �7Responder uma d�vida.");
				p.sendMessage("�c/duvida deletar <nick> �8- �7Deletar a d�vida de um usu�rio.");
				p.sendMessage("�c/duvida ver <nick> �8- �7Ver uma d�vida.");
				p.sendMessage("�c/duvida listar [p�gina] �8- �7Ver todas as d�vidas criadas.");
				p.sendMessage("�2");
				return true;
			}
			if (args[0].equalsIgnoreCase("listar")) {
				if (!p.hasPermission("wandy.equipe")) {
					p.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
					return true;
				}
				mandarLista(p);
				return true;
			}
			if (args[0].equalsIgnoreCase("ver")) {
				if (!DAPI.temDuvida(p.getName())) {
					p.sendMessage("�cVoc� n�o tem uma d�vida criada no momento.");
					return true;
				}
				String prefix = "�f";
				if (DAPI.taRespondida(p.getName())) {
					String prefix1 = "�f";

					p.sendMessage("�1");
					p.sendMessage("�a Informa��es sobre sua d�vida:");
					p.sendMessage("�4");
					p.sendMessage("�a   Nick: �f" + prefix + p.getName());
					p.sendMessage("�a   T�tulo: �f" + DAPI.getTitulo(p.getName()));
					p.sendMessage("�a   Mensagem: �f" + DAPI.getDuvida(p.getName()));
					p.sendMessage("�a   Criada: �f" + DAPI.getCDATA(p.getName()));
					p.sendMessage("�a   Status: �cFECHADO");
					p.sendMessage("�a   Respondido por: �f" + prefix1 + DAPI.getRespondeu(p.getName()));
					p.sendMessage("�a   Resposta: �f" + DAPI.getResposta(p.getName()));
					p.sendMessage("�a   Respondida: �f" + DAPI.getRDATA(p.getName()));
					p.sendMessage("�2");
					return true;
				}
				p.sendMessage("�1");
				p.sendMessage("�a Informa��es sobre sua d�vida:");
				p.sendMessage("�4");
				p.sendMessage("�a   Nick: �f" + prefix + p.getName());
				p.sendMessage("�a   T�tulo: �f" + DAPI.getTitulo(p.getName()));
				p.sendMessage("�a   Mensagem: �f" + DAPI.getDuvida(p.getName()));
				p.sendMessage("�a   Criada: �f" + DAPI.getCDATA(p.getName()));
				p.sendMessage("�a   Status: �eESPERANDO");
				p.sendMessage("�3");
				return true;
			}
			if (args[0].equalsIgnoreCase("deletar")) {
				if (!DAPI.temDuvida(p.getName())) {
					p.sendMessage("�cVoc� n�o tem tem d�vida criada no momento.");
					return true;
				}
				DAPI.deletarDuvida(p.getName());
				p.sendMessage("�aVoc� deletou sua d�vida com sucesso.");
				return true;
			}
			if (args[0].equalsIgnoreCase("responder")) {
				if (!p.hasPermission("wandy.equipe")) {
					p.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
					return true;
				}
				p.sendMessage("�cUtilize /duvida responder <nick> <resposta> para responder uma d�vida.");
				return true;
			}
			p.sendMessage("�cUtilize /duvida <t�tulo> <mensagem> para criar uma d�vida.");
			return true;
		}
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("listar")) {
				if (!p.hasPermission("wandy.equipe")) {
					p.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
					return true;
				}
				if (isInt(args[1])) {
					if (args[1].contains("-")) {
						p.sendMessage("�cN�mero inv�lido.");
						return true;
					}
					int pa = Integer.valueOf(args[1]).intValue();
					mandarListaP(p, pa);
					return true;
				}
				p.sendMessage("�cN�mero inv�lido.");
				return true;
			}
			String prefix1;
			if (args[0].equalsIgnoreCase("ver")) {
				if (!p.hasPermission("wandy.equipe")) {
					p.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
					return true;
				}
				String nome = args[1].toLowerCase();
				if (!DAPI.temDuvida(nome)) {
					p.sendMessage("�cEste usu�rio n�o tem uma d�vida criada no momento.");
					return true;
				}
				String prefix = "�f";
				if (DAPI.taRespondida(nome)) {
					prefix1 = "�f";
					p.sendMessage("�1");
					p.sendMessage("�a Informa��es sobre a d�vida de " + DAPI.getNick(nome) + ":");
					p.sendMessage("�4");
					p.sendMessage("�a   Nick: �f" + prefix + DAPI.getNick(nome));
					p.sendMessage("�a   T�tulo: �f" + DAPI.getTitulo(nome));
					p.sendMessage("�a   Mensagem: �f" + DAPI.getDuvida(nome));
					p.sendMessage("�a   Criada: �f" + DAPI.getCDATA(nome));
					p.sendMessage("�a   Status: �cFECHADO");
					p.sendMessage("�a   Respondido por: �f" + prefix1 + DAPI.getRespondeu(nome));
					p.sendMessage("�a   Resposta: �f" + DAPI.getResposta(nome));
					p.sendMessage("�a   Respondida: �f" + DAPI.getRDATA(nome));
					p.sendMessage("�2");
					return true;
				}
				p.sendMessage("�1");
				p.sendMessage("�a Informa��es sobre a d�vida de " + DAPI.getNick(nome) + ":");
				p.sendMessage("�4");
				p.sendMessage("�a   Nick: �f" + prefix + DAPI.getNick(nome));
				p.sendMessage("�a   T�tulo: �f" + DAPI.getTitulo(nome));
				p.sendMessage("�a   Mensagem: �f" + DAPI.getDuvida(nome));
				p.sendMessage("�a   Criada: �f" + DAPI.getCDATA(nome));
				p.sendMessage("�a   Status: �eESPERANDO");
				p.sendMessage("�3");
				return true;
			}
			if (args[0].equalsIgnoreCase("deletar")) {
				if (!p.hasPermission("whyze.equipe")) {
					p.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
					return true;
				}
				String nome = args[1].toLowerCase();
				if (!DAPI.temDuvida(nome)) {
					p.sendMessage("�cEste usu�rio n�o tem uma d�vida criada no momento.");
					return true;
				}
				DAPI.deletarDuvida(nome);

				p.sendMessage("�aD�vida deletada com sucesso.");
				for (Player all : Bukkit.getOnlinePlayers()) {
					if (all.getName().equalsIgnoreCase(nome)) {
						all.sendMessage("�cSua d�vida foi �c�lDELETADA �cpor um membro da equipe.");
					}
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("responder")) {
				if (!p.hasPermission("wandy.equipe")) {
					p.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
					return true;
				}
				String nome = args[1].toLowerCase();
				if (!DAPI.temDuvida(nome)) {
					p.sendMessage("�cEste usu�rio n�o tem uma d�vida criada no momento.");
					return true;
				}
				if (DAPI.taRespondida(nome)) {
					p.sendMessage("�A d�vida deste usu�rio j� est� respondida.");
					return true;
				}
				String resposta = args[2];

				DAPI.responderDuvida(nome, p.getName(), resposta);
				p.sendMessage("�aD�vida respondida com sucesso.");
				for (Player all : Bukkit.getOnlinePlayers()) {
					if (all.getName().equalsIgnoreCase(nome)) {
						new FancyMessage("").then("�eSua d�vida j� foi respondida! Clique ").color(ChatColor.YELLOW).then("�e�lAQUI").color(ChatColor.GOLD).command("/duvida ver").then(" �epara v�-la.").color(ChatColor.YELLOW).send(all);
					}
				}
				return true;
			}
			if (DAPI.temDuvida(p.getName())) {
				p.sendMessage("�cVoc� j� tem uma d�vida criada.");
				return true;
			}
			String titulo = args[0];
			String mensagem = args[1];
			DAPI.criarDuvida(p.getName(), titulo, mensagem);
			p.sendMessage("�1");
			p.sendMessage("�e Sua d�vida foi enviada, nossa equipe foi �bnotificada �ee em breve ela ser� respondida.");
			p.sendMessage("�e Para excluir, utilize o comando �b/duvida deletar�e.");
			p.sendMessage("�2");
			return true;
		}
		if (args.length > 2) {
			String[] arrayOfString;
			Player all;
			if (args[0].equalsIgnoreCase("responder")) {
				if (!p.hasPermission("wandy.equipe")) {
					p.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
					return true;
				}
				String nome = args[1].toLowerCase();
				if (!DAPI.temDuvida(nome)) {
					p.sendMessage("�cEste usu�rio n�o tem uma d�vida criada no momento.");
					return true;
				}
				int tm = args[0].length() + nome.length() + 2;
				String message = "";
				int j = (arrayOfString = args).length;
				for (int i = 0; i < j; i++) {
					String part = arrayOfString[i];
					if (message != "") {
						message = message + " ";
					}
					message = message + part;
				}
				String resposta = message.substring(tm);
				DAPI.responderDuvida(nome, p.getName(), resposta);
				p.sendMessage("�aD�vida respondida com sucesso.");
				for (Iterator<? extends Player> localIterator2 = Bukkit.getOnlinePlayers().iterator(); localIterator2.hasNext();) {
					all = (Player) localIterator2.next();
					if (all.getName().equalsIgnoreCase(nome)) {
						new FancyMessage("").then("�eSua d�vida j� foi respondida! Clique ").color(ChatColor.YELLOW).then("�e�lAQUI").color(ChatColor.GOLD).command("/duvida ver").then(" �epara v�-la.").color(ChatColor.YELLOW).send(all);
					}
				}
				return true;
			}
			if (DAPI.temDuvida(p.getName())) {
				p.sendMessage("�cVoc� j� tem uma d�vida criada.");
				return true;
			}
			final String titulo = args[0];
			final int tm = titulo.length() + 1;
			String message = "";
			for (final String part : args) {
				if (message != "") {
					message = String.valueOf(message) + " ";
				}
				message = String.valueOf(message) + part;
			}
			final String mensagem2 = message.substring(tm);
			DAPI.criarDuvida(p.getName(), titulo, mensagem2);
			p.sendMessage("�1");
			p.sendMessage("�e Sua d�vida foi enviada, nossa equipe foi �bnotificada �ee em breve ela ser� respondida.");
			p.sendMessage("�e Para excluir, utilize o comando �b/duvida deletar�e.");
			p.sendMessage("�2");
			return true;
		}
		return false;
	}

	public static void mandarLista(Player p) {
		p.sendMessage("�1");
		p.sendMessage("�2D�vidas sem resposta no momento: �7(P�gina 1)");
		String nh = "S";
		int i = 0;
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM DuvidasAPI;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String nick = rs.getString("Nick");
				String titulo = rs.getString("Titulo");
				String data = rs.getString("CDATA");
				if (!DAPI.taRespondida(nick)) {
					if (i != 10) {
						new FancyMessage("").then("�e " + titulo).color(ChatColor.YELLOW).tooltip(Arrays.asList("�fClique para ver.", "�8/duvida ver " + nick)).command("/duvida ver " + nick).then(" �8- �f" + nick + "�7 (" + data + ")").color(ChatColor.GRAY).send(p);
						i++;
						if (nh.equalsIgnoreCase("S")) {
							nh = "N";
						}
					}
				}
			}
		} catch (SQLException ev) {
			ev.printStackTrace();
		}
		if (i == 10) {
			p.sendMessage("�6");
			p.sendMessage("�a * Pr�xima p�gina dispon�vel.");
			p.sendMessage("�aDigite /duvida listar <p�gina> para ver as pr�ximas p�ginas.");
		}
		if (nh.equalsIgnoreCase("S")) {
			p.sendMessage("�7 Nenhuma");
		}
		p.sendMessage("�3");
	}

	public static void mandarListaP(Player p, int pa) {
		p.sendMessage("�1");
		p.sendMessage("�aD�vidas sem resposta no momento: �7(P�gina " + pa + ")");
		String nh = "S";
		int pg = pa;
		int pagina = pg * 10;
		int ant = pagina - 10;
		int i = 0;
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM DuvidasAPI;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String nick = rs.getString("Nick");
				String titulo = rs.getString("Titulo");
				String data = rs.getString("CDATA");
				if (!DAPI.taRespondida(nick)) {
					if (i != pagina) {
						i++;
						if (i > ant) {
							new FancyMessage("").then("�e " + titulo).color(ChatColor.YELLOW).tooltip(Arrays.asList("�fClique para ver.", "�8/duvida ver " + nick)).command("/duvida ver " + nick).then(" �8- " + nick + "�7 (" + data + ")").color(ChatColor.GRAY).send(p);
							if (nh.equalsIgnoreCase("S")) {
								nh = "N";
							}
						}
					}
				}
			}
		} catch (SQLException ev) {
			ev.printStackTrace();
		}
		if (i <= ant) {
			p.sendMessage("�c P�gina n�o existe.");
		}
		if (i == pagina) {
			p.sendMessage("�6");
			p.sendMessage("�a * Pr�xima p�gina dispon�vel.");
			p.sendMessage("�aDigite /duvida listar <p�gina> para ver as pr�ximas p�ginas.");
		}
		p.sendMessage("�5");
	}

	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
