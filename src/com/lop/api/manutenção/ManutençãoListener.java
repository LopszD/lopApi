package com.wandy.api.manutenção;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.wandy.api.commands.ReinícioCommand;
import com.wandy.api.sql.MySQL;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ManutençãoListener implements Listener {
	
	public static ArrayList<String> block = new ArrayList<String>();

	@EventHandler
	public static void aoEntrar(PlayerLoginEvent e) {
		int port = Bukkit.getServer().getPort();
		if (checarOn(port)) {
			if (!e.getPlayer().hasPermission("wandy.gerente")) {
				e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§c§lREDE WANDY\n§1\n§cServidor em §c§lMANUTENÇãO§c!\n§cEm breve estará aberto novamente.\n§2\n§cMais informações: §btwitter.com/@RedeWandy§c.");
				return;
			}
		}
		if (block.contains("BLOCK")) {
			e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§c§lREDE WANDY\n§1\n§cServidor reiniciando! Aguarde para tentar novamente...");
		}
		if (ReinícioCommand.taReiniciando()) {
			if (!e.getPlayer().hasPermission("wandy.gerente")) {
				e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§c§lREDE WANDY\n§1\n§cServidor reiniciando! Aguarde para tentar novamente...");
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static boolean temVIP(Player p, int id) {
		int i = 0;
		PermissionUser user = PermissionsEx.getUser(p.getName());
		if (!user.getGroupNames()[0].equals("membro")) {
			i++;
		}
		if ((p.hasPermission("wandy.vip")) || (p.hasPermission("wandy.equipe"))) {
			i++;
		}
		if (i > 0) {
			return true;
		}
		return false;
	}

	public static boolean checarOn(int port) {
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM ManutencaoGlobal WHERE PORTA=?");
			ps.setInt(1, port);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String valor = rs.getString("VALOR");
				if (valor.equals("ON")) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
