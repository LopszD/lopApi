package com.wandy.api.punish;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.wandy.api.sql.Manager;
import com.wandy.api.sql.MySQL;

public class PunishAPI {
	
	public static boolean checkPunish(String nome) {
		if (Manager.temPunish(nome)) {
			return true;
		}
		return false;
	}

	public static boolean checkBAN(String nome) {
		String tipo = Manager.getPunish(nome).getTipo();
		if (tipo.equals("BAN")) {
			return true;
		}
		return false;
	}

	public static boolean checkMUTE(String nome) {
		String tipo = Manager.getPunish(nome).getTipo();
		if (tipo.equals("MUTE")) {
			if (taAtivo(nome)) {
				return true;
			}
		}
		return false;
	}

	public static boolean taAtivo(String nome) {
		if (Manager.getPunish(nome).getAtivo()) {
			return true;
		}
		return false;
	}

	public static boolean temFim(String nome) {
		if (Manager.getPunish(nome).getFim()) {
			return true;
		}
		return false;
	}

	public static boolean temProva(String nome) {
		if (!Manager.getPunish(nome).getProva().equals("N")) {
			return true;
		}
		return false;
	}

	public static String getData(String nome) {
		String data = Manager.getPunish(nome).getData();
		return data;
	}

	public static String getTipo(String nome) {
		String tipo = Manager.getPunish(nome).getTipo();
		return tipo;
	}

	public static String getAutor(String nome) {
		String autor = Manager.getPunish(nome).getAutor();
		return autor;
	}

	public static String getDataF(String nome) {
		String dataf = Manager.getPunish(nome).getDataF();
		return dataf;
	}

	public static String getProva(String nome) {
		String prova = Manager.getPunish(nome).getProva();
		return prova;
	}

	public static String getMotivo(String nome) {
		String motivo = Manager.getPunish(nome).getMotivo();
		return motivo;
	}

	public static String getNick(String nome) {
		String nick = Manager.getPunish(nome).getNick();
		return nick;
	}

	public static Integer getID(String nome) {
		int id = Manager.getPunish(nome).getID();
		return Integer.valueOf(id);
	}

	@SuppressWarnings("deprecation")
	public static String getDateF(int dias, int horas, int minutos) {
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		int hora = now.getHours();
		int dps = hora + 1;
		now.setHours(dps);
		int d = now.getDate();
		int dd = d + dias;
		int h = now.getHours();
		int hd = h + horas;
		int m = now.getMinutes();
		int md = m + minutos;
		now.setDate(dd);
		now.setHours(hd);
		now.setMinutes(md);
		String data = format.format(now);
		return data;
	}

	public static void punirJogador(String nome, String autor, String tipo, String prova, String motivo, String data, int dias, int horas, int minutos) {
		String fim = "NAO";
		String dataf = "N";
		int check = dias + horas + minutos;
		if (check > 0) {
			fim = "SIM";
			dataf = getDateF(dias, horas, minutos);
		}
		int id = getLastID() + 1;
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("INSERT INTO Punish (ID,Nome,Nick,Tipo,Motivo,Autor,Prova,Data,Fim,DataF,Ativo) VALUES (?,?,?,?,?,?,?,?,?,?,?);");
			ps.setInt(1, id);
			ps.setString(2, nome.toLowerCase());
			ps.setString(3, nome);
			ps.setString(4, tipo);
			ps.setString(5, motivo);
			ps.setString(6, autor);
			ps.setString(7, prova);
			ps.setString(8, data);
			ps.setString(9, fim);
			ps.setString(10, dataf);
			ps.setString(11, "SIM");
			ps.executeUpdate();
			boolean fi = false;
			if (fim.equals("SIM")) {
				fi = true;
			}
			Punish p = new Punish(id, nome.toLowerCase(), nome, tipo, motivo, autor, prova, data, fi, dataf, true);
			Manager.punishs.put(nome.toLowerCase(), p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int getLastID() {
		int last = 0;
		int i = 0;
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM Punish ORDER BY Punish.ID DESC;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				if (i != 1) {
					i++;
					last = id;
				}
			}
		} catch (SQLException ev) {
			ev.printStackTrace();
		}
		return last;
	}

	public static void despunirJogador(String nome, int id) {
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("UPDATE Punish SET Ativo = ? WHERE ID = ?;");
			ps.setString(1, "NAO");
			ps.setInt(2, id);
			ps.executeUpdate();
			Punish p = Manager.getPunish(nome.toLowerCase());
			try {
				p.setAtivo(false);
			} catch (NullPointerException localNullPointerException) {
			}
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
