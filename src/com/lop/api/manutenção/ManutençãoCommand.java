package com.wandy.api.manutenção;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.wandy.api.sql.MySQL;

public class ManutençãoCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender p, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("manutencao")) || (!p.hasPermission("wandy.manutenção"))) {
			p.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("§cUtilize /manutencao <on/off> para gerenciar a entrada do servidor.");
			return true;
		}
		if (args.length >= 1) {
			if (args[0].equalsIgnoreCase("on")) {
				p.sendMessage("§fModo §aMANUTENÇãO §fativado.");
				setarOn();
				return true;
			}
			if (args[0].equalsIgnoreCase("off")) {
				p.sendMessage("§fModo §cMANUTENÇãO §fdesativado.");
				setarOff();
				return true;
			}
			p.sendMessage("§cUtilize /manutencao <on/off> para gerenciar a entrada do servidor.");
			return true;
		}
		return false;
	}

	public static boolean checarOn() {
		int port = Bukkit.getServer().getPort();
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

	public static boolean checarExiste() {
		int port = Bukkit.getServer().getPort();
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM ManutencaoGlobal WHERE PORTA=?");
			ps.setInt(1, port);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void registrarServer() {
		int port = Bukkit.getServer().getPort();
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("INSERT INTO ManutencaoGlobal (PORTA,VALOR) VALUES (?,?);");
			ps.setInt(1, port);
			ps.setString(2, "OFF");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void setarOn() {
		int port = Bukkit.getServer().getPort();
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("UPDATE ManutencaoGlobal SET VALOR = ? WHERE PORTA = ?;");
			ps.setString(1, "ON");
			ps.setInt(2, port);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void setarOff() {
		int port = Bukkit.getServer().getPort();
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("UPDATE ManutencaoGlobal SET VALOR = ? WHERE PORTA = ?;");
			ps.setString(1, "OFF");
			ps.setInt(2, port);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
