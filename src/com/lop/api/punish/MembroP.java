package com.wandy.api.punish;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import org.bukkit.entity.Player;

import com.wandy.api.sql.MySQL;

public class MembroP {

	public static HashMap<String, MembroP> mem = new HashMap<String, MembroP>();
	private String nome;
	private Punicao list;

	public MembroP(String nome) {
		this.nome = nome;
		this.list = null;
		mem.put(nome.toLowerCase(), this);
	}

	public String getNome() {
		return this.nome;
	}

	public Punicao getPunicao() {
		return this.list;
	}

	public boolean hasPunicao() {
		if (this.list != null) {
			return true;
		}
		return false;
	}

	public void setPunicao(Punicao p) {
		this.list = p;
	}

	public static MembroP getMembro(String nome) {
		return mem.get(nome.toLowerCase());
	}

	public static boolean hasMembro(String nome) {
		return mem.containsKey(nome.toLowerCase());
	}

	public static void loadInfomation(Player p) {
		MembroP m = new MembroP(p.getName());
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM Punish WHERE Ativo = ? AND Tipo = ? AND Nick = ?");
			ps.setString(1, "SIM");
			ps.setString(2, "MUTE");
			ps.setString(3, p.getName());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				m.setPunicao(new Punicao(p.getName(), rs.getInt("ID"), rs.getString("Autor"), rs.getString("Prova"), rs.getString("DataF"), rs.getString("Data"), rs.getString("Motivo")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void refreshPunish(Player p) {
		if (hasMembro(p.getName())) {
			MembroP m = getMembro(p.getName());
			m.setPunicao(null);
			try {
				PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM Punish WHERE Ativo = ? AND Tipo = ? AND Nick = ?");
				ps.setString(1, "SIM");
				ps.setString(2, "MUTE");
				ps.setString(3, p.getName());
				ResultSet rs = ps.executeQuery();
				if (!rs.next()) {
					return;
				}
				m.setPunicao(new Punicao(p.getName(), rs.getInt("ID"), rs.getString("Autor"), rs.getString("Prova"), rs.getString("DataF"), rs.getString("Data"), rs.getString("Motivo")));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			MembroP m = new MembroP(p.getName());
			try {
				PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM Punish WHERE Ativo = ? AND Tipo = ? AND Nick = ?");
				ps.setString(1, "SIM");
				ps.setString(2, "MUTE");
				ps.setString(3, p.getName());
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					m.setPunicao(new Punicao(p.getName(), rs.getInt("ID"), rs.getString("Autor"), rs.getString("Prova"), rs.getString("DataF"), rs.getString("Data"), rs.getString("Motivo")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
