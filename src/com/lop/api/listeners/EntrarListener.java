package com.wandy.api.listeners;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.potion.PotionEffectType;

import com.mojang.authlib.GameProfile;
import com.wandy.api.Main;
import com.wandy.api.commands.FakeCommand;
import com.wandy.api.commands.FlyCommand;
import com.wandy.api.commands.VanishCommand;
import com.wandy.api.sql.Manager;
import com.wandy.api.sql.MySQL;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.WorldServer;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class EntrarListener implements Listener {
	
	@EventHandler
	public static void JoinRegister(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!Manager.checarContaExiste(p.getName())) {
			try {
				PreparedStatement ps = MySQL.connection.prepareStatement("INSERT INTO ContasAPI (Nome,Nick,Prefs) VALUES (?,?,?);");
				ps.setString(1, p.getName().toLowerCase());
				ps.setString(2, p.getName());
				ps.setString(3, "0;0;0;0;0;0;0");
				ps.setInt(3, 0);
			} catch (SQLException ev) {
				ev.printStackTrace();
			}
		}
		Manager.checarPrefs(p.getName());
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void aoEntrar(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		Server s = Bukkit.getServer();
		int max = s.getMaxPlayers() - 50;
		if (s.getOnlinePlayers().size() >= max) {
			if (!p.hasPermission("wandy.vip")) {
				if (!p.hasPermission("wandy.equipe")) {
					e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§c§lREDE WANDY\n§1\n§cQue pena, o servidor está lotado!\nAdquira §bVIP §ce tenha uma vaga reservada!\n§cwww.redewandy.com");
					return;
				}
			}
		}
		int maxt = s.getMaxPlayers() - 5;
		if (s.getOnlinePlayers().size() >= maxt) {
			int alo = 0;
			if (p.hasPermission("wandy.vip")) {
				alo++;
			}
			if (p.hasPermission("wandy.equipe")) {
				alo++;
			}
			if (alo > 0) {
				int ch = 0;
				for (Player on : s.getOnlinePlayers()) {
					if (ch == 0) {
						if (!p.hasPermission("wandy.vip")) {
							if (!p.hasPermission("wandy.equipe")) {
								if (!p.hasPermission("wandy.youtuber")) {
									on.kickPlayer("§c§lREDE WANDY\n§1\n§cVocê foi kickado para dar vaga à um VIP!\n§cAdquira seu §bVIP §cem §bwww.redewandy.com");
									ch++;
								}
							}
						}
					}
					
				}
				if (ch > 1) {
					e.allow();
				}
			}
		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoEntrar(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		for (Player todos : Bukkit.getOnlinePlayers()) {
			if (!p.hasPermission("wandy.vanish")) {
				if (VanishCommand.vanish.contains(todos.getName())) {
					p.hidePlayer(todos);
				}
			}
		}
		if (VanishCommand.vanish.contains(p.getName())) {
			if (p.hasPermission("wandy.vanish")) {
				for (Player todos : Bukkit.getOnlinePlayers()) {
					if (!todos.hasPermission("wandy.vanish")) {
						todos.hidePlayer(p);
					}
				}
			}
		}
		if (p.hasPermission("wandy.equipe")) {
			if (!VanishCommand.vanish.contains(p.getName())) {
				String prefix = PermissionsEx.getUser(p).getGroups()[0].getPrefix();
				if (!FakeCommand.temFake(p.getName())) {
					for (Player todos : Bukkit.getOnlinePlayers()) {
						mandarAction(todos, prefix.replace("&", "§") + " " + p.getName() + " §7entrou no servidor!");
					}
				}
			}
		}
		e.setJoinMessage("");
		p.setGameMode(GameMode.SURVIVAL);
		if (p.hasPermission("wandy.vip")) {
			if (Manager.checarLocExiste("VIP")) {
				Random r = new Random();
				int rn = r.nextInt(4);
				if (rn == 0) {
					Location loc = Manager.pegarLocation("VIP").clone().add(0.0D, 0.0D, Main.spawn.intValue());
					p.teleport(loc);
				}
				if (rn == 1) {
					Location loc = Manager.pegarLocation("VIP").clone().subtract(Main.spawn.intValue(), 0.0D, 0.0D);
					p.teleport(loc);
				}
				if (rn == 2) {
					Location loc = Manager.pegarLocation("VIP").clone().subtract(0.0D, 0.0D, Main.spawn.intValue());
					p.teleport(loc);
				}
				if (rn == 3) {
					Location loc = Manager.pegarLocation("VIP").clone().add(Main.spawn.intValue(), 0.0D, 0.0D);
					p.teleport(loc);
				}
				if (rn == 4) {
					Location loc = Manager.pegarLocation("VIP").clone().add(0.0D, 0.0D, Main.spawn.intValue());
					p.teleport(loc);
				}
				if (e.getPlayer().hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
					e.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
				}
				p.sendTitle("§6§lFactions Thorms", "§fBoa sorte com as invasões.");
				p.setGameMode(GameMode.SURVIVAL);
				if (!FlyCommand.voando.contains(p.getName())) {
					p.setAllowFlight(true);
					p.setFlying(true);
				}
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
					@SuppressWarnings("unused")
					@Override
					public void run() {
						if (FakeCommand.temFake(p.getName())) {
							MinecraftServer craftServer = ((CraftServer) Bukkit.getServer()).getServer();
							WorldServer craftWorld = ((CraftWorld) p.getWorld()).getHandle();
							EntityPlayer newPlayer = new EntityPlayer(craftServer, craftWorld, new GameProfile(p.getUniqueId(), FakeCommand.getFake(p.getName())), new PlayerInteractManager(craftWorld));
							PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, new EntityPlayer[] { ((CraftPlayer) p).getHandle() });
							PacketPlayOutPlayerInfo packet2 = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[] { newPlayer });
							if (Bukkit.getOnlinePlayers().size() > 0) {
								for (Player on : Bukkit.getOnlinePlayers()) {
									if (!on.getName().equals(p.getName())) {
										((CraftPlayer) on).getHandle().playerConnection.sendPacket((Packet<?>) packet);
										((CraftPlayer) on).getHandle().playerConnection.sendPacket((Packet<?>) packet2);
									}
								}
								List<Player> list = new ArrayList<Player>();
								for (Entity en : p.getNearbyEntities(20.0, 20.0, 20.0)) {
									if (en.getType().equals((Object) EntityType.PLAYER)) {
										Player pls = (Player) en;
										if (pls == null) {
											continue;
										}
										list.add(pls);
									}
								}
								FakeCommand.updateEntity((Entity) p, list);
							}
						}
						for (Player ps : Bukkit.getOnlinePlayers()) {
							WorldServer craftWorld = ((CraftWorld) ps.getWorld()).getHandle();
							if (FakeCommand.temFake(ps.getName())) {
								MinecraftServer craftServer2 = ((CraftServer) Bukkit.getServer()).getServer();
								WorldServer craftWorld2 = ((CraftWorld) ps.getWorld()).getHandle();
								EntityPlayer newPlayer2 = new EntityPlayer(craftServer2, craftWorld2, new GameProfile(ps.getUniqueId(), FakeCommand.getFake(ps.getName())), new PlayerInteractManager(craftWorld));
								PacketPlayOutPlayerInfo packet3 = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, new EntityPlayer[] { ((CraftPlayer) ps).getHandle() });
								PacketPlayOutPlayerInfo packet4 = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[] { newPlayer2 });
								((CraftPlayer) p).getHandle().playerConnection.sendPacket((Packet<?>) packet3);
								((CraftPlayer) p).getHandle().playerConnection.sendPacket((Packet<?>) packet4);
								List<Player> list2 = new ArrayList<Player>();
								list2.add(p);
								FakeCommand.updateEntity((Entity) ps, list2);
							}
						}
					}
				}, 15L);
				return;
			}
		}
		if (Manager.checarLocExiste("SPAWN")) {
			Random r = new Random();
			int rn = r.nextInt(4);
			if (rn == 0) {
				Location loc = Manager.pegarLocation("SPAWN").clone().subtract(2.0D, 0.0D, 0.0D);
				p.teleport(loc);
			}
			if (rn == 1) {
				Location loc = Manager.pegarLocation("SPAWN").clone().subtract(2.0D, 0.0D, 0.0D);
				p.teleport(loc);
			}
			if (rn == 2) {
				Location loc = Manager.pegarLocation("SPAWN").clone().subtract(0.0D, 0.0D, 2.0D);
				p.teleport(loc);
			}
			if (rn == 3) {
				Location loc = Manager.pegarLocation("SPAWN").clone().add(2.0D, 0.0D, 0.0D);
				p.teleport(loc);
			}
			if (rn == 4) {
				Location loc = Manager.pegarLocation("SPAWN").clone().add(0.0D, 0.0D, 2.0D);
				p.teleport(loc);
			}
			if (e.getPlayer().hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
				e.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
			}
			p.sendTitle("§6§lFactions Thorms", "§fBoa sorte com as invasões.");
			p.setGameMode(GameMode.SURVIVAL);
		}
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			@SuppressWarnings("unused")
			@Override
			public void run() {
				if (FakeCommand.temFake(p.getName())) {
					MinecraftServer craftServer = ((CraftServer) Bukkit.getServer()).getServer();
					WorldServer craftWorld = ((CraftWorld) p.getWorld()).getHandle();
					EntityPlayer newPlayer = new EntityPlayer(craftServer, craftWorld, new GameProfile(p.getUniqueId(), FakeCommand.getFake(p.getName())), new PlayerInteractManager(craftWorld));
					PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, new EntityPlayer[] { ((CraftPlayer) p).getHandle() });
					PacketPlayOutPlayerInfo packet2 = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[] { newPlayer });
					if (Bukkit.getOnlinePlayers().size() > 0) {
						for (Player on : Bukkit.getOnlinePlayers()) {
							if (!on.getName().equals(p.getName())) {
								((CraftPlayer) on).getHandle().playerConnection.sendPacket((Packet<?>) packet);
								((CraftPlayer) on).getHandle().playerConnection.sendPacket((Packet<?>) packet2);
							}
						}
						List<Player> list = new ArrayList<Player>();
						for (Entity en : p.getNearbyEntities(20.0, 20.0, 20.0)) {
							if (en.getType().equals((Object) EntityType.PLAYER)) {
								Player pls = (Player) en;
								if (pls == null) {
									continue;
								}
								list.add(pls);
							}
						}
						FakeCommand.updateEntity((Entity) p, list);
					}
				}
				for (Player ps : Bukkit.getOnlinePlayers()) {
					if (FakeCommand.temFake(ps.getName())) {
						WorldServer craftWorld = ((CraftWorld) ps.getWorld()).getHandle();
						MinecraftServer craftServer2 = ((CraftServer) Bukkit.getServer()).getServer();
						WorldServer craftWorld2 = ((CraftWorld) ps.getWorld()).getHandle();
						EntityPlayer newPlayer2 = new EntityPlayer(craftServer2, craftWorld2, new GameProfile(ps.getUniqueId(), FakeCommand.getFake(ps.getName())), new PlayerInteractManager(craftWorld));
						PacketPlayOutPlayerInfo packet3 = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, new EntityPlayer[] { ((CraftPlayer) ps).getHandle() });
						PacketPlayOutPlayerInfo packet4 = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[] { newPlayer2 });
						((CraftPlayer) p).getHandle().playerConnection.sendPacket((Packet<?>) packet3);
						((CraftPlayer) p).getHandle().playerConnection.sendPacket((Packet<?>) packet4);
						List<Player> list2 = new ArrayList<Player>();
						for (Entity en2 : ps.getNearbyEntities(20.0, 20.0, 20.0)) {
							if (en2.getType().equals((Object) EntityType.PLAYER)) {
								Player pls2 = (Player) en2;
								if (pls2 == null) {
									continue;
								}
								list2.add(pls2);
							}
						}
						FakeCommand.updateEntity((Entity) ps, list2);
					}
				}
			}
		}, 15L);
	}

	public static void mandarAction(Player p, String msg) {
		PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + msg + "\"}"), (byte) 2);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	}
}
