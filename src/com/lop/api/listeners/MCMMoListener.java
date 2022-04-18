package com.wandy.api.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.gmail.nossr50.mcMMO;
import com.gmail.nossr50.datatypes.database.PlayerStat;
import com.gmail.nossr50.datatypes.player.PlayerProfile;
import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
import com.gmail.nossr50.events.experience.McMMOPlayerXpGainEvent;
import com.gmail.nossr50.util.player.UserManager;
import com.wandy.api.especiais.listeners.BoosterExpListener;
import com.wandy.api.utils.Util;
import com.wandy.api.utils.fanciful.FancyMessage;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class MCMMoListener implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public static void aoUpar(McMMOPlayerXpGainEvent e) {
		PlayerProfile pro = UserManager.getPlayer(e.getPlayer()).getProfile();
		Player p = e.getPlayer();
		double amais = 0.0D;
		if (p.hasPermission("wandy.bonus1")) {
			amais = 1.25D;
		}
		if (p.hasPermission("wandy.bonus2")) {
			amais = 1.5D;
		}
		if (p.hasPermission("wandy.bonus3")) {
			amais = 1.75D;
		}
		if (p.hasPermission("wandy.bonus4")) {
			amais = 2.1D;
		}
		double ant = e.getXpGained() * amais;
		if (BoosterExpListener.bos.containsKey(p.getName())) {
			if (e.getSkill().equals(BoosterExpListener.bos.get(p.getName()))) {
				ant *= 2.0D;
			}
		}
		if ((e.getSkill().equals(SkillType.ACROBATICS)) && (e.getPlayer().getInventory().getBoots() != null) && (!e.getPlayer().getInventory().getBoots().getType().equals(Material.AIR))) {
			ItemStack i = e.getPlayer().getInventory().getBoots();
			if ((i.hasItemMeta()) && (i.getItemMeta().hasLore()) && (i.getItemMeta().getLore().contains("§7Experiência I"))) {
				ant *= 2.0D;
			}
		}
		if ((e.getSkill().equals(SkillType.SWORDS)) && (e.getPlayer().getInventory().getItemInHand() != null) && (!e.getPlayer().getInventory().getItemInHand().getType().equals(Material.AIR))) {
			String ty = new StringBuilder().append(e.getPlayer().getInventory().getItemInHand().getType()).toString();
			if (ty.contains("SWORD")) {
				ItemStack i = e.getPlayer().getInventory().getItemInHand();
				if ((i.hasItemMeta()) && (i.getItemMeta().hasLore()) && (i.getItemMeta().getLore().contains("§7Experiência I"))) {
					ant *= 2.0D;
				}
			}
		}
		if ((e.getSkill().equals(SkillType.EXCAVATION)) && (e.getPlayer().getInventory().getItemInHand() != null) && (!e.getPlayer().getInventory().getItemInHand().getType().equals(Material.AIR))) {
			String ty = new StringBuilder().append(e.getPlayer().getInventory().getItemInHand().getType()).toString();
			if (ty.contains("SPADE")) {
				ItemStack i = e.getPlayer().getInventory().getItemInHand();
				if ((i.hasItemMeta()) && (i.getItemMeta().hasLore()) && (i.getItemMeta().getLore().contains("§7Experiência I"))) {
					ant *= 2.0D;
				}
			}
		}
		if ((e.getSkill().equals(SkillType.AXES)) && (e.getPlayer().getInventory().getItemInHand() != null) && (!e.getPlayer().getInventory().getItemInHand().getType().equals(Material.AIR))) {
			String ty = new StringBuilder().append(e.getPlayer().getInventory().getItemInHand().getType()).toString();
			if (ty.contains("AXE")) {
				ItemStack i = e.getPlayer().getInventory().getItemInHand();
				if ((i.hasItemMeta()) && (i.getItemMeta().hasLore()) && (i.getItemMeta().getLore().contains("§7Experiência I"))) {
					ant *= 2.0D;
				}
			}
		}
		if ((e.getSkill().equals(SkillType.MINING)) && (e.getPlayer().getInventory().getItemInHand() != null) && (!e.getPlayer().getInventory().getItemInHand().getType().equals(Material.AIR))) {
			String ty = new StringBuilder().append(e.getPlayer().getInventory().getItemInHand().getType()).toString();
			if (ty.contains("PICKAXE")) {
				ItemStack i = e.getPlayer().getInventory().getItemInHand();
				if ((i.hasItemMeta()) && (i.getItemMeta().hasLore()) && (i.getItemMeta().getLore().contains("§7Experiência I"))) {
					ant *= 2.0D;
				}
			}
		}
		double dpss = e.getXpGained() + ant;
		int form = (int) dpss;
		e.setXpGained(form);
		String skill = e.getSkill().getName();
		int lvl = pro.getSkillLevel(e.getSkill());
		int xp = pro.getSkillXpLevel(e.getSkill());
		int dxp = pro.getXpToLevel(e.getSkill());
		int gn = Math.round(e.getXpGained());
		if (gn != 0) {
			EntrarListener.mandarAction(p, "§a" + skill + ": " + lvl + " (" + xp + "/" + dxp + ") +" + gn + "XP");
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public static void aoNivel(McMMOPlayerLevelUpEvent e) {
		PlayerProfile pro = UserManager.getPlayer(e.getPlayer()).getProfile();
		Player p = e.getPlayer();
		if (e.getLevelsGained() > 0) {
			String a = new StringBuilder().append(pro.getSkillLevel(e.getSkill())).toString();
			if (a.endsWith("00")) {
				String prefix = "§7";
				String prefi = PermissionsEx.getUser(p).getGroups()[0].getPrefix();
				if (!prefi.equals("")) {
					prefix = "§" + prefi.charAt(1);
				}
				List<String> lore = new ArrayList<String>();
				lore.add("§e" + e.getSkill().getName());
				List<PlayerStat> userStats = mcMMO.getDatabaseManager().readLeaderboard(e.getSkill(), 1, 10);
				int i = 1;
				for (PlayerStat cp : userStats) {
					if (i <= 10) {
						lore.add("§f" + i + "º §7" + Util.getColorPrefix(cp.name) + cp.name + ": §7" + cp.statVal);
						i++;
					}
				}
				p.sendMessage("§aParabéns! Você atingiu o nível §6" + pro.getSkillLevel(e.getSkill()) + " §aem: §6" + e.getSkill().getName());
				FancyMessage fa = new FancyMessage("").then("§6§lHABILIDADES: " + prefix + p.getName() + " §aatingiu o nível §6" + pro.getSkillLevel(e.getSkill()) + " §ana habilidade " + e.getSkill().getName() + "§a.").color(ChatColor.GREEN).tooltip(lore);
				for (Player todos : Bukkit.getOnlinePlayers()) {
					todos.sendMessage("§1");
					fa.send(todos);
					todos.sendMessage("§2");
					todos.playSound(todos.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
				}
			}
		}
	}

	public static void mandarAction(Player p, String msg) {
		PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + msg + "\"}"), (byte) 2);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	}
}
