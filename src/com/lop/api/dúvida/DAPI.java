package com.wandy.api.dúvida;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.wandy.api.sql.MySQL;

public class DAPI {
	
	public static boolean temDuvida(String nome) {
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM DuvidasAPI WHERE Nome=?");
			ps.setString(1, nome.toLowerCase());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean taRespondida(String nome) {
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM DuvidasAPI WHERE Nome=?");
			ps.setString(1, nome.toLowerCase());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String rp = rs.getString("Respondido");
				if (rp.equals("SIM")) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String getDuvida(String nome) {
		String d = "";
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM DuvidasAPI WHERE Nome=?");
			ps.setString(1, nome.toLowerCase());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				d = rs.getString("Mensagem");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static String getTitulo(String nome) {
		String d = "";
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM DuvidasAPI WHERE Nome=?");
			ps.setString(1, nome.toLowerCase());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				d = rs.getString("Titulo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static String getResposta(String nome) {
		String d = "";
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM DuvidasAPI WHERE Nome=?");
			ps.setString(1, nome.toLowerCase());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				d = rs.getString("Resposta");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static String getNick(String nome) {
		String d = "";
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM DuvidasAPI WHERE Nome=?");
			ps.setString(1, nome.toLowerCase());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				d = rs.getString("Nick");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static String getRespondeu(String nome) {
		String d = "";
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM DuvidasAPI WHERE Nome=?");
			ps.setString(1, nome.toLowerCase());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				d = rs.getString("Respondeu");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static String getCDATA(String nome) {
		String d = "";
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM DuvidasAPI WHERE Nome=?");
			ps.setString(1, nome.toLowerCase());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				d = rs.getString("CDATA");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static String getRDATA(String nome) {
		String d = "";
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM DuvidasAPI WHERE Nome=?");
			ps.setString(1, nome.toLowerCase());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				d = rs.getString("RDATA");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}

	@SuppressWarnings("deprecation")
	public static String getDayFormated() {
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		int hora = now.getHours();
		int dps = hora + 1;

		now.setHours(dps);

		String data = format.format(now);

		return data;
	}

	public static void criarDuvida(String nome, String titulo, String mensagem) {
		String data = getDayFormated();
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("INSERT INTO DuvidasAPI (Nome,Nick,Titulo,Mensagem,CDATA,Respondeu,Resposta,RDATA,Respondido) VALUES (?,?,?,?,?,?,?,?,?);");
			ps.setString(1, nome.toLowerCase());
			ps.setString(2, nome);
			ps.setString(3, titulo);
			ps.setString(4, mensagem);
			ps.setString(5, data);
			ps.setString(6, "SEM");
			ps.setString(7, "SEM");
			ps.setString(8, "SEM");
			ps.setString(9, "NAO");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void responderDuvida(String nome, String respondeu, String resposta) {
		String data = getDayFormated();
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("UPDATE DuvidasAPI SET Respondeu = ?, Resposta = ?, Respondido = ?, RDATA = ? WHERE Nome = ?;");
			ps.setString(5, nome.toLowerCase());
			ps.setString(1, respondeu);
			ps.setString(2, resposta);
			ps.setString(3, "SIM");
			ps.setString(4, data);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int getCriadas(String nome) {
		int i = 0;
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM DuvidasAPI WHERE Nome = ?");
			ps.setString(1, nome.toLowerCase());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static int getRespondidas(String nome) {
		int i = 0;
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM DuvidasAPI WHERE Respondeu = ?");
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static String getUltima(String nome) {
		int i = 0;
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM DuvidasAPI WHERE Nome = ?");
			ps.setString(1, nome.toLowerCase());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
				if (i > 0) {
					return rs.getString("CDATA");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Nenhuma";
	}

	public static int getDuvidas() {
		int i = 0;
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM DuvidasAPI");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String rp = rs.getString("Respondido");
				if (!rp.equals("SIM")) {
					i++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static void deletarDuvida(String nome) {
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("DELETE FROM DuvidasAPI WHERE Nome=?");
			ps.setString(1, nome.toLowerCase());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
