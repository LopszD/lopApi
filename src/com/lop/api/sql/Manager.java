package com.wandy.api.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.wandy.api.commands.FlyCommand;
import com.wandy.api.commands.GodCommand;
import com.wandy.api.commands.VanishCommand;
import com.wandy.api.listeners.ToggleListener;
import com.wandy.api.mobspawner.Mobs;
import com.wandy.api.punish.MembroP;
import com.wandy.api.punish.Punicao;
import com.wandy.api.punish.Punish;
import com.wandy.api.punish.PunishAPI;
import com.wandy.economy.API_Economy;
import com.wandy.economy.WandyEconomy;
import com.wandy.economy.objects.MoneyTop;
import com.wandy.economy.objects.PlayerObj;

public class Manager {
	
	public static HashMap<String, String> Vizu;
	public static ArrayList<String> jatem;
	public static HashMap<String, LocationsAPI> locs;
	public static HashMap<String, Punish> punishs;
	public static Random RANDOM;
	private static char[] APPEND;

	static {
		Manager.Vizu = new HashMap<String, String>();
		Manager.jatem = new ArrayList<String>();
		Manager.locs = new HashMap<String, LocationsAPI>();
		Manager.punishs = new HashMap<String, Punish>();
		RANDOM = new Random();
		APPEND = new char[] { '§', '\0', '§', '\0', '§', '\0', '§', '\0' };
	}

	public static String randomString() {
		Manager.APPEND[1] = (char) (48 + Manager.RANDOM.nextInt(10));
		Manager.APPEND[3] = (char) (48 + Manager.RANDOM.nextInt(10));
		Manager.APPEND[5] = (char) (48 + Manager.RANDOM.nextInt(10));
		Manager.APPEND[7] = (char) (48 + Manager.RANDOM.nextInt(10));
		return new String(Manager.APPEND);
	}

	public static boolean isBlocked(Player p) {
		if (MembroP.getMembro(p.getName()).hasPunicao()) {
			Punicao pu = MembroP.getMembro(p.getName()).getPunicao();
			String data1 = pu.getDataF();
			String data = getDayFormated();
			int part1 = Integer.valueOf(data1.split(" ")[0].replace("/", "")).intValue();
			int partn = Integer.valueOf(data.split(" ")[0].replace("/", "")).intValue();
			if (partn < part1) {
				String autor = pu.getAutor();
				String motivo = pu.getMotivo();
				if (pu.hasProva()) {
					String prova = pu.getProva();
					motivo = motivo + " - " + prova;
				}
				p.sendMessage("§1");
				p.sendMessage("§c Você está mutado, poderá utilizar o chat novamente em " + data1.replace(" ", " às ") + ".");
				p.sendMessage("§2");
				p.sendMessage("§c Autor: " + autor);
				p.sendMessage("§c Motivo: " + motivo);
				p.sendMessage("§4");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 0.5F, 1.0F);
				return true;
			}
			if (partn > part1) {
				int id = pu.getID();
				PunishAPI.despunirJogador(p.getName(), id);
			}
			if (partn == part1) {
				int part12 = Integer.valueOf(data1.split(" ")[1].replace(":", "")).intValue();
				int partn2 = Integer.valueOf(data.split(" ")[1].replace(":", "")).intValue();
				if (partn2 >= part12) {
					int id = pu.getID();
					PunishAPI.despunirJogador(p.getName(), id);
				}
				if (partn2 < part12) {
					String autor = pu.getAutor();
					String motivo = pu.getMotivo();
					if (pu.hasProva()) {
						String prova = pu.getProva();
						motivo = motivo + " - " + prova;
					}
					p.sendMessage("§1");
					p.sendMessage("§c Você está mutado, poderá utilizar o chat novamente em " + data1.replace(" ", " às ") + ".");
					p.sendMessage("§2");
					p.sendMessage("§c Autor: " + autor);
					p.sendMessage("§c Motivo: " + motivo);
					p.sendMessage("§4");
					p.playSound(p.getLocation(), Sound.VILLAGER_NO, 0.5F, 1.0F);
					return true;
				}
			}
		}
		return false;
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

	public static void importarToggle(Player p) {
		int i = 0;
		int i2 = 0;
		int i3 = 0;
		int i4 = 0;
		int i5 = 0;
		int i6 = 0;
		int i7 = 0;
		String nome = p.getName();
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM ContasAPI WHERE Nome=?");
			ps.setString(1, nome.toLowerCase());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String[] prefs = rs.getString("Prefs").split(";");
				i = Integer.valueOf(prefs[0]);
				i2 = Integer.valueOf(prefs[1]);
				i3 = Integer.valueOf(prefs[2]);
				i4 = Integer.valueOf(prefs[3]);
				i5 = Integer.valueOf(prefs[4]);
				i6 = Integer.valueOf(prefs[5]);
				i7 = Integer.valueOf(prefs[6]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (i == 1) {
			if (!ToggleListener.canceltell.contains(nome)) {
				ToggleListener.canceltell.add(nome);
			}
		} else if (ToggleListener.canceltell.contains(nome)) {
			ToggleListener.canceltell.remove(nome);
		}
		if (i2 == 1) {
			if (!ToggleListener.cancelglobal.contains(nome)) {
				ToggleListener.cancelglobal.add(nome);
			}
		} else if (ToggleListener.cancelglobal.contains(nome)) {
			ToggleListener.cancelglobal.remove(nome);
		}
		if (i3 == 1) {
			if (!ToggleListener.canceltpa.contains(nome)) {
				ToggleListener.canceltpa.add(nome);
			}
		} else if (ToggleListener.canceltpa.contains(nome)) {
			ToggleListener.canceltpa.remove(nome);
		}
		if (i4 == 1) {
			if (!ToggleListener.cancelcoins.contains(nome)) {
				ToggleListener.cancelcoins.add(nome);
				PlayerObj po = WandyEconomy.getPlugin().getPlayerManager().getPlayer(nome);
				if (po != null) {
					po.setMoneyPay(false);
				}
			}
		} else if (ToggleListener.cancelcoins.contains(nome)) {
			ToggleListener.cancelcoins.remove(nome);
			PlayerObj po = WandyEconomy.getPlugin().getPlayerManager().getPlayer(nome);
			if (po != null) {
				po.setMoneyPay(true);
			}
		}
		if (i5 == 1) {
			if (!VanishCommand.vanish.contains(nome)) {
				VanishCommand.vanish.add(nome);
			}
		} else if (VanishCommand.vanish.contains(nome)) {
			VanishCommand.vanish.remove(nome);
		}
		if (i6 == 1) {
			if (!FlyCommand.voando.contains(nome)) {
				FlyCommand.voando.add(nome);
				if (!p.getAllowFlight()) {
					p.setAllowFlight(true);
					p.setFlying(true);
				}
			}
		} else if (FlyCommand.voando.contains(nome)) {
			FlyCommand.voando.remove(nome);
			if (p.getAllowFlight()) {
				p.setAllowFlight(false);
				p.setFlying(false);
			}
		}
		if (i7 == 1) {
			if (!GodCommand.godmode.contains(nome)) {
				GodCommand.godmode.add(nome);
			}
		} else if (GodCommand.godmode.contains(nome)) {
			GodCommand.godmode.remove(nome);
		}
	}

	public static void exportarToggle(Player p) {
		int i = 0;
		int i2 = 0;
		int i3 = 0;
		int i4 = 0;
		int i5 = 0;
		int i6 = 0;
		int i7 = 0;
		String nome = p.getName();
		if (ToggleListener.canceltell.contains(nome)) {
			i = 1;
			ToggleListener.canceltell.remove(nome);
		}
		if (ToggleListener.cancelglobal.contains(nome)) {
			i2 = 1;
			ToggleListener.cancelglobal.remove(nome);
		}
		if (ToggleListener.canceltpa.contains(nome)) {
			i3 = 1;
			ToggleListener.canceltpa.remove(nome);
		}
		if (ToggleListener.cancelcoins.contains(nome)) {
			i4 = 1;
			ToggleListener.cancelcoins.remove(nome);
			PlayerObj po = WandyEconomy.getPlugin().getPlayerManager().getPlayer(nome);
			if (po != null) {
				po.setMoneyPay(true);
			}
		}
		if (VanishCommand.vanish.contains(nome)) {
			i5 = 1;
			VanishCommand.vanish.remove(nome);
		}
		if (FlyCommand.voando.contains(nome)) {
			i6 = 1;
			FlyCommand.voando.remove(nome);
			if (p.getAllowFlight()) {
				p.setAllowFlight(false);
				p.setFlying(false);
			}
		}
		if (GodCommand.godmode.contains(nome)) {
			i7 = 1;
			GodCommand.godmode.remove(nome);
		}
		String awe = String.valueOf(i) + ";" + i2 + ";" + i3 + ";" + i4 + ";" + i5 + ";" + i6 + ";" + i7;
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("UPDATE ContasAPI SET Prefs = ? WHERE Nome = ?;");
			ps.setString(1, awe);
			ps.setString(2, nome.toLowerCase());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean checarContaExiste(String nome) {
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM ContasAPI WHERE Nome=?");
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

	public static boolean checarMagnata(String nome) {
		List<MoneyTop> out = API_Economy.plugin.getPlayerManager().getMoneyTop();
		int i = 1;
		for (MoneyTop cp : out) {
			if (i <= 1) {
				i++;
				if (cp.getNome().equals(nome)) {
					return true;
				}
			}
		}
		return false;
	}

	public static void checarPrefs(String nome) {
		int i = 0;
		int i2 = 0;
		int i3 = 0;
		int i4 = 0;
		int i5 = 0;
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM ContasAPI WHERE Nome=?");
			ps.setString(1, nome.toLowerCase());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String[] prefs = rs.getString("Prefs").split(";");
				i = Integer.valueOf(prefs[0]);
				i2 = Integer.valueOf(prefs[1]);
				i3 = Integer.valueOf(prefs[2]);
				i4 = Integer.valueOf(prefs[3]);
				i5 = Integer.valueOf(prefs[4]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (i == 1 && !ToggleListener.canceltell.contains(nome)) {
			ToggleListener.canceltell.add(nome);
		}
		if (i2 == 1 && !ToggleListener.cancelglobal.contains(nome)) {
			ToggleListener.cancelglobal.add(nome);
		}
		if (i3 == 1 && !ToggleListener.canceltpa.contains(nome)) {
			ToggleListener.canceltpa.add(nome);
		}
		if (i4 == 1 && !ToggleListener.cancelcoins.contains(nome)) {
			ToggleListener.cancelcoins.add(nome);
			PlayerObj po = WandyEconomy.getPlugin().getPlayerManager().getPlayer(nome);
			if (po != null) {
				po.setMoneyPay(false);
			}
		}
		if (i5 == 1 && !VanishCommand.vanish.contains(nome)) {
			VanishCommand.vanish.add(nome);
		}
	}

	public static Integer pegarPrefs(String nome, String tipo) {
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM ContasAPI WHERE Nome=?");
			ps.setString(1, nome.toLowerCase());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String[] prefs = rs.getString("Prefs").split(";");
				if (tipo.equals("TELL")) {
					int i = Integer.valueOf(prefs[0]);
					return i;
				}
				if (tipo.equals("GLOBAL")) {
					int i = Integer.valueOf(prefs[1]);
					return i;
				}
				if (tipo.equals("TPA")) {
					int i = Integer.valueOf(prefs[2]);
					return i;
				}
				if (tipo.equals("COINS")) {
					int i = Integer.valueOf(prefs[3]);
					return i;
				}
				if (tipo.equals("VANISH")) {
					int i = Integer.valueOf(prefs[4]);
					return i;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String pegarNomePlayer(String nome) {
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM ContasAPI WHERE Nome=?");
			ps.setString(1, nome.toLowerCase());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("Nick");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void setarPrefs(String nome, int a, int b, int c, int d, int v) {
		String awe = a + ";" + b + ";" + c + ";" + d + ";" + v;
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("UPDATE ContasAPI SET Prefs = ? WHERE Nome = ?;");
			ps.setString(1, awe);
			ps.setString(2, nome.toLowerCase());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void importarLocs() {
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM LocationsAPI");
			ResultSet rs = ps.executeQuery();
			locs.clear();
			while (rs.next()) {
				String home = rs.getString("Location");
				String[] parts = home.split("@");
				String world = parts[0];
				double x = Double.parseDouble(parts[1]);
				double y = Double.parseDouble(parts[2]);
				double z = Double.parseDouble(parts[3]);
				float yaw = Float.parseFloat(parts[4]);
				float pitch = Float.parseFloat(parts[5]);
				Location loc1 = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
				LocationsAPI lo = new LocationsAPI(rs.getString("Nome"), loc1);
				locs.put(rs.getString("Nome"), lo);
				updateLocation(rs.getString("Nome"), loc1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	  public static void setarNumeroHomes(String nome, Integer valor)
	  {
	    try
	    {
	      PreparedStatement ps1 = MySQL.connection.prepareStatement("UPDATE ContasAPI SET Homes = ? WHERE Nome = ?;");
	      ps1.setInt(1, valor.intValue());
	      ps1.setString(2, nome.toLowerCase());
	      ps1.executeUpdate();
	    }
	    catch (SQLException e)
	    {
	      e.printStackTrace();
	    }
	  }

	  public static Integer pegarNumeroHomes(String nome)
	  {
	    try
	    {
	      PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM ContasAPI WHERE Nome=?");
	      ps.setString(1, nome.toLowerCase());
	      ResultSet rs = ps.executeQuery();
	      if (rs.next())
	      {
	        int n = rs.getInt("Homes");
	        
	        return Integer.valueOf(n);
	      }
	    }
	    catch (SQLException e)
	    {
	      e.printStackTrace();
	    }
	    return null;
	  }

	public static void exportarLocs() {
		try {
			for (LocationsAPI lo : locs.values()) {
				PreparedStatement ps = MySQL.connection.prepareStatement("UPDATE LocationsAPI SET Location = ? WHERE Nome = ?;");
				ps.setString(2, lo.getNome());
				Location loc = lo.getLocation();
				String a = loc.getWorld().getName() + "@" + loc.getX() + "@" + loc.getY() + "@" + loc.getZ() + "@" + loc.getYaw() + "@" + loc.getPitch();
				ps.setString(1, a);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void setarLocation(String nome, Location loc) {
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("INSERT INTO LocationsAPI (Nome,Location) VALUES (?,?);");
			ps.setString(1, nome.toLowerCase());
			String a = loc.getWorld().getName() + "@" + loc.getX() + "@" + loc.getY() + "@" + loc.getZ() + "@" + loc.getYaw() + "@" + loc.getPitch();
			ps.setString(2, a);
			ps.executeUpdate();
			LocationsAPI lo = new LocationsAPI(nome.toLowerCase(), loc);
			locs.put(lo.getNome(), lo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deletarLocation(String nome) {
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("DELETE FROM LocationsAPI WHERE Nome=?");
			ps.setString(1, nome.toLowerCase());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Location pegarLocation(String nome) {
		if (locs.containsKey(nome.toLowerCase())) {
			return ((LocationsAPI) locs.get(nome.toLowerCase())).getLocation();
		}
		return null;
	}

	public static boolean checarLocExiste(String nome) {
		if (locs.containsKey(nome.toLowerCase())) {
			return true;
		}
		return false;
	}

	public static void updateLocation(String nome, Location loc) {
		if (locs.containsKey(nome.toLowerCase())) {
			LocationsAPI lo = (LocationsAPI) locs.get(nome.toLowerCase());
			lo.setLocation(loc);
		}
	}

	public static void importarPunishs() {
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM Punish WHERE Ativo = ? AND Tipo = ?");
			ps.setString(1, "SIM");
			ps.setString(2, "MUTE");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				boolean fim = false;
				boolean ativo = false;
				if (rs.getString("Fim").equals("SIM")) {
					fim = true;
				}
				if (rs.getString("Ativo").equals("SIM")) {
					ativo = true;
				}
				Punish p = new Punish(rs.getInt("ID"), rs.getString("Nome"), rs.getString("Nick"), rs.getString("Tipo"),
						rs.getString("Motivo"), rs.getString("Autor"), rs.getString("Prova"), rs.getString("Data"), fim,
						rs.getString("DataF"), ativo);

				punishs.put(p.getNome(), p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void exportarPunishs() {
		try {
			for (Punish p : punishs.values()) {
				String ativo = new StringBuilder(String.valueOf(p.getAtivo())).toString();

				PreparedStatement ps = MySQL.connection.prepareStatement("UPDATE Punish SET Motivo = ?, Autor = ?, Prova = ?, Data = ?, DataF = ?, Ativo = ? WHERE Nome = ? AND ID = ?;");
				ps.setString(1, p.getMotivo());
				ps.setString(2, p.getAutor());
				ps.setString(3, p.getProva());
				ps.setString(4, p.getData());
				ps.setString(5, p.getDataF());
				ps.setString(6, ativo.replace("false", "NAO").replace("true", "SIM"));
				ps.setString(7, p.getNome());
				ps.setInt(8, p.getID());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Punish getPunish(String nome) {
		if (punishs.containsKey(nome.toLowerCase())) {
			return (Punish) punishs.get(nome.toLowerCase());
		}
		return null;
	}

	public static boolean temPunish(String nome) {
		if (punishs.containsKey(nome.toLowerCase())) {
			return true;
		}
		return false;
	}

	public static void carregarPunish(String nome) {
		if (PunishAPI.checkPunish(nome)) {
			if (PunishAPI.taAtivo(nome)) {
				try {
					PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM Punish WHERE ID = ? AND Nome = ?");
					ps.setInt(1, PunishAPI.getID(nome).intValue());
					ps.setString(2, nome.toLowerCase());
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						boolean ativo = false;
						if (rs.getString("Ativo").equals("SIM")) {
							ativo = true;
						}
						getPunish(nome).setAtivo(ativo);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM Punish WHERE Ativo = ? AND Nome = ?");
			ps.setString(1, "SIM");
			ps.setString(2, nome.toLowerCase());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				boolean fim = false;
				boolean ativo = false;
				if (rs.getString("Fim").equals("SIM")) {
					fim = true;
				}
				if (rs.getString("Ativo").equals("SIM")) {
					ativo = true;
				}
				Punish p = new Punish(rs.getInt("ID"), rs.getString("Nome"), rs.getString("Nick"), rs.getString("Tipo"), rs.getString("Motivo"), rs.getString("Autor"), rs.getString("Prova"), rs.getString("Data"), fim, rs.getString("DataF"), ativo);
				if (PunishAPI.checkPunish(nome)) {
					punishs.remove(p.getNome());
				}
				punishs.put(p.getNome(), p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void importarMobs() {
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM WandyMobs");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				long lolo = Long.valueOf(rs.getString("Data")).longValue();
				Date data = new Date(lolo);
				Date now = new Date();
				if (now.after(data)) {
					PreparedStatement ps1 = MySQL.connection.prepareStatement("DELETE FROM WandyMobs WHERE Location = ? AND Data = ?");
					ps1.setString(1, rs.getString("Location"));
					ps1.setString(2, rs.getString("Data"));
					ps1.executeUpdate();
				} else {
					String bruto = rs.getString("Location");
					World world = Bukkit.getWorld(bruto.split("@")[0]);
					int x = Integer.valueOf(bruto.split("@")[1]).intValue();
					int y = Integer.valueOf(bruto.split("@")[2]).intValue();
					int z = Integer.valueOf(bruto.split("@")[3]).intValue();

					new Mobs(world, x, y, z, data);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void exportarMobs() {
		try {
			PreparedStatement ps1 = MySQL.connection.prepareStatement("DELETE FROM WandyMobs");
			ps1.executeUpdate();
			for (Mobs m : Mobs.mobs.values()) {
				PreparedStatement ps = MySQL.connection.prepareStatement("INSERT INTO WandyMobs (Location,Data) VALUES (?,?);");
				String bruto = m.getLocation().getWorld().getName() + "@" + m.getLocation().getBlockX() + "@" + m.getLocation().getBlockY() + "@" + m.getLocation().getBlockZ();
				String data = new StringBuilder(String.valueOf(m.getData().getTime())).toString();
				ps.setString(1, bruto);
				ps.setString(2, data);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
