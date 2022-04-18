package com.wandy.api.effects.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wandy.api.Main;
import com.wandy.api.effects.EffectType;
import com.wandy.api.effects.manager.EffectManager;

public class EffectSQL {
	
	private static Connection connection;
	private static String host,user,password,database;
	
	static {
		host = "54.39.141.61";
		user = "u1052_RkPKCQmVOZ";
		password = "6hC0MWnzHfTwA0Kjlv74lVRP";
		database = "s1052_moedas";
	}
	
	public static void startConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, user, password);
			connection.createStatement().execute("CREATE TABLE IF NOT EXISTS effects (username VARCHAR(255), death BOOLEAN, ender_pearl BOOLEAN, health BOOLEAN, jump BOOLEAN, power BOOLEAN, zeus BOOLEAN);");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void stopConnection(){
		try{
			if (!connection.isClosed()){
				connection.close();
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		return connection;
	}
	
	public static boolean hasAccount(String username) {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM effects WHERE username='" + username.toLowerCase() + "';");
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void createAccount(String username) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO effects (username,death,ender_pearl,health,jump,power,zeus) VALUES (?,?,?,?,?,?,?)");
			ps.setString(1, username.toLowerCase());
			ps.setBoolean(2, false);
			ps.setBoolean(3, false);
			ps.setBoolean(4, false);
			ps.setBoolean(5, false);
			ps.setBoolean(6, false);
			ps.setBoolean(7, false);
			ps.execute();
			List<EffectType> effects = new ArrayList<>();
			EffectManager em = new EffectManager(username, effects);
			Main.getInstance().effects.put(username, em);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadAccount(String username) {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM effects WHERE username=?");
			ps.setString(1, username.toLowerCase());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				List<EffectType> effects = new ArrayList<>();
				for (EffectType ef : EffectType.values()) {
					String name = ef.toString().toLowerCase();
					if (rs.getBoolean(name)) {
						effects.add(ef);
					}
				}
				EffectManager em = new EffectManager(username, effects);
				Main.getInstance().effects.put(username, em);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateAccount(String username) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE effects SET death=?,ender_pearl=?,health=?,jump=?,power=?,zeus=? WHERE username='" + username.toLowerCase() + "';");
			EffectManager em = Main.getInstance().effects.get(username);
			ps.setBoolean(1, em.hasEffect(EffectType.DEATH));
			ps.setBoolean(2, em.hasEffect(EffectType.ENDER_PEARL));
			ps.setBoolean(3, em.hasEffect(EffectType.HEALTH));
			ps.setBoolean(4, em.hasEffect(EffectType.JUMP));
			ps.setBoolean(5, em.hasEffect(EffectType.POWER));
			ps.setBoolean(6, em.hasEffect(EffectType.ZEUS));
			ps.execute();
			Main.getInstance().effects.remove(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
