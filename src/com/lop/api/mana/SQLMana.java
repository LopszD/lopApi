package com.wandy.api.mana;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wandy.api.Main;

public class SQLMana {

	private static Connection connection;

	static String host, user, password, database;

	public static void startConnection() {
		try {
			host = "54.39.141.61";
			user = "u1052_RkPKCQmVOZ";
			password = "6hC0MWnzHfTwA0Kjlv74lVRP";
			database = "s1052_moedas";
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, user, password);
			connection.createStatement().execute("CREATE TABLE IF NOT EXISTS maniacs (name VARCHAR(16), maniacs INT)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean hasAccount(String name) {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM maniacs WHERE name='" + name.toLowerCase() + "';");
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void createAccount(String name, int maniacs) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO maniacs (name, maniacs) VALUES (?,?);");
			ps.setString(1, name.toLowerCase());
			ps.setInt(2, maniacs);
			ps.executeUpdate();
			ManaModel model = new ManaModel(name, maniacs);
			Main.getInstance().maniacs.put(name, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateAccount(String name, int maniacs) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE maniacs SET maniacs=? WHERE name=?");
			ps.setString(2, name.toLowerCase());
			ps.setInt(1, maniacs);
			ps.executeUpdate();
			Main.getInstance().maniacs.remove(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadAccount(String name) {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM maniacs WHERE name=?");
			ps.setString(1, name.toLowerCase());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ManaModel model = new ManaModel(name, rs.getInt("maniacs"));
				Main.getInstance().maniacs.put(name, model);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
