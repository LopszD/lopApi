package com.wandy.api.listeners;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.SortedMap;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.google.common.base.Functions;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Ordering;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;
import com.wandy.api.Main;
import com.wandy.api.sql.Manager;
import com.wandy.api.utils.PermissionsAPI;
import com.wandy.economy.API_Economy;
import com.wandy.economy.objects.MoneyTop;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class RankListener implements Listener {
	
	public static NPC npcyoutube = null;
	public static Hologram holoyoutube = null;
	public static NPC npcyoutube1 = null;
	public static Hologram holoyoutube1 = null;
	public static NPC npcmissoes = null;
	public static Hologram holomissoes = null;
	public static NPC npcmissoes1 = null;
	public static Hologram holomissoes1 = null;
	public static NPC npcrecompensas = null;
	public static Hologram holorecompensas = null;
	public static NPC npcrecompensas1 = null;
	public static Hologram holorecompensas1 = null;
	public static NPC magaiver = null;
	public static Hologram holomagaiver = null;
	public static Chest chestmagaiver = null;
	public static Hologram holoarenax1 = null;
	public static Hologram hololoja = null;
	public static HashMap<String, Hologram> hds = new HashMap<String, Hologram>();
	public static HashMap<String, NPC> armor = new HashMap<String, NPC>();
	public static SortedMap<String, Integer> ctopgeral;
	public static HashMap<String, Faction> ctopgeralitns;

	@SuppressWarnings("deprecation")
	public static void animar() {
		Bukkit.getWorld("world").spawnFallingBlock(new Location(Bukkit.getWorld("world"), 131.0D, 162.0D, -30.0D), Material.STEP, (byte) 0);
		Bukkit.getWorld("world").spawnFallingBlock(new Location(Bukkit.getWorld("world"), 131.0D, 162.0D, -33.0D), Material.DOUBLE_STEP, (byte) 0);
		Bukkit.getWorld("world").spawnFallingBlock(new Location(Bukkit.getWorld("world"), 129.0D, 162.0D, -35.0D), Material.DOUBLE_STEP, (byte) 0);
		Bukkit.getWorld("world").spawnFallingBlock(new Location(Bukkit.getWorld("world"), 129.0D, 167.0D, -35.0D), Material.STEP, (byte) 0);
		Bukkit.getWorld("world").spawnFallingBlock(new Location(Bukkit.getWorld("world"), 127.0D, 162.0D, -37.0D), Material.DOUBLE_STEP, (byte) 0);
		Bukkit.getWorld("world").spawnFallingBlock(new Location(Bukkit.getWorld("world"), 127.0D, 167.0D, -37.0D), Material.DOUBLE_STEP, (byte) 0);
		Bukkit.getWorld("world").spawnFallingBlock(new Location(Bukkit.getWorld("world"), 124.0D, 162.0D, -38.0D), Material.DOUBLE_STEP, (byte) 0);
		Bukkit.getWorld("world").spawnFallingBlock(new Location(Bukkit.getWorld("world"), 124.0D, 167.0D, -38.0D), Material.DOUBLE_STEP, (byte) 0);
		Bukkit.getWorld("world").spawnFallingBlock(new Location(Bukkit.getWorld("world"), 124.0D, 172.0D, -38.0D), Material.STEP, (byte) 0);
	}

	public static void removerTOP() {
		for (NPC ar : armor.values()) {
			ar.destroy();
		}
		armor.clear();
		for (String hd : hds.keySet()) {
			Hologram hdd = hds.get(hd);
			hdd.delete();
		}
		hds.clear();
		Bukkit.getConsoleSender().sendMessage("RANK REMOVIDO COM SUCESSO!");
	}

	public static void removerNPCYoutuber() {
		if (npcyoutube != null) {
			npcyoutube.destroy();
		}
		npcyoutube = null;
		if (holoyoutube != null) {
			holoyoutube.delete();
		}
		holoyoutube = null;
		if (npcmissoes != null) {
			npcmissoes.destroy();
		}
		npcmissoes = null;
		if (holomissoes != null) {
			holomissoes.delete();
		}
		holomissoes = null;
		if (npcrecompensas != null) {
			npcrecompensas.destroy();
		}
		npcrecompensas = null;
		if (holorecompensas != null) {
			holorecompensas.delete();
		}
		holorecompensas = null;
		if (magaiver != null) {
			magaiver.destroy();
		}
		magaiver = null;
		if (holomagaiver != null) {
			holomagaiver.delete();
		}
		holomagaiver = null;
		if (npcyoutube1 != null) {
			npcyoutube1.destroy();
		}
		npcyoutube1 = null;
		if (holoyoutube1 != null) {
			holoyoutube1.delete();
		}
		holoyoutube = null;
		if (npcmissoes1 != null) {
			npcmissoes1.destroy();
		}
		npcmissoes1 = null;
		if (holomissoes1 != null) {
			holomissoes1.delete();
		}
		holomissoes = null;
		if (npcrecompensas != null) {
			npcrecompensas1.destroy();
		}
		npcrecompensas1 = null;
		if (holorecompensas1 != null) {
			holorecompensas1.delete();
		}
		holorecompensas = null;
		if (holoarenax1 != null) {
			holoarenax1.delete();
		}
		holoarenax1 = null;
		if (hololoja != null) {
			hololoja.delete();
		}
		hololoja = null;
	}

	public static void createNPCYoutuber(Location loc) {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "npc remove all");
		
		NPC np = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "NPCYOUTUBER");
		np.data().set("player-skin-name", "LuginBr");
		np.data().set("player-skin-textures", "eyJ0aW1lc3RhbXAiOjE1MjQ5NzkxNjMwMDQsInByb2ZpbGVJZCI6IjM2Mjk4MTgzN2E5MzQ0MjU5Y2U0NTRlZGMwOTQxYmNiIiwicHJvZmlsZU5hbWUiOiJ1bHRyYUxpZ2h0dCIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjU5N2Y4Y2M3MWQ0YjI0MTFlZmE0ZTdjMjExOTFhYzNjNjEzZDllYTZiZThjNDkyZGY1N2E4Y2ZmMTIwYmQifX19");
		np.data().set("player-skin-signature", "wRHF06COo/VMJVX9DnKsHUCHMjIhTPfA+mEijb+xLVe+DpiSyt+FRqtvxx79/EIZxCqFJsIBdZvXu+oZKZf+v3X2Q0Zq2P2Vr6+6aocuKMjgjY1LXvklRsweEF2q0sSPKxb1n5KGGspBpq0jf2VTk8GUl86Mhe9TsZTko5eCzZUpC3+BQcEDy7k9961Go4zOtDavAWluc/30RXtaEbdOIp4oQ5vFMyJdsGIxNTk/kwCb4Bb0JolZ1TKJhXnnlWXWC7Ct/ykMfOKuTzx08HwAG9JUVS9/t+CLtUc+cdrgI90tMq9xvwiOZzznyv7PDwLPF7JPV2YdAPMDQos5reSnyP90r6d3/+eAZW1PC4b6R4NQ2Z+2Dle7bPfT6YPFGGA9AtSRbPzWQog7bR4oSwbCaaAGdh0Cec/ZLoys8rhkkqdtBb8nsav/bfQsq0WOHP84jwTPT0oXdms/SX1pdIHpW29DPpH3eu8brRSyfUMR75muO7710yPwFXDxN5s2W7VTGzaOgFREnsIyHpCBc5tc0kDGslg0l8oYoxSLl9+Gr7Y3diXsbN+2F15Oe9JO9xrO0wr+ITEwrt/LqSVHjb9JDxDeKmtYR0W8JDEnXIyrs3H0eFvF82VMx3I0oHTgprzmi30e8bwgyTOFSrslc/cAl1pbyrQPsdTdqsf5nn6J8qo=");
		np.setName("");
		np.spawn(loc);
		npcyoutube = np;

		Hologram holo = HologramsAPI.createHologram(Main.getInstance(), np.getStoredLocation().add(0.0D, 2.8D, 0.0D));
		holo.insertTextLine(0, "§c§lYoutuber");
		holo.insertTextLine(1, "§fClique para ver requisitos.");
		holoyoutube = holo;

		NPC npc1 = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "NPCMAGAIVER");
		npc1.data().set("player-skin-name", "Gameplan");
		npc1.data().set("player-skin-textures", "eyJ0aW1lc3RhbXAiOjE1MjUwMjE5MjgzNzUsInByb2ZpbGVJZCI6ImI3MzZiZWI4NWFkNjRkYjA5Y2FmNTU2MTFkZWJjNDYwIiwicHJvZmlsZU5hbWUiOiJTbG90aHlWb2x0Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS85ODcxNmRlOWJmZWFhYjNmYmZiNmE5YzVhOTkwYjJiNDgzMjQ1ZmRmZWIyZjM4OTk5Yjc5ZWU3YTVhYWI0OWEifX19");
		npc1.data().set("player-skin-signature", "xQ6NSZiHvj6PuYT1gk/35xgkayadxzs+n/u4ZKgJsgtveedtr20xb0ZNgmJJRqYliK5mmNx4UY/lUy665Ms4hVHWmUlJiqGedPSoEj4dAgYvaFLIK8OZJx2FTIk2hUHobuNuNGv+DzSkU4IYf4+w85EE1t+/VB0FVQ/+izGmm/zycgoOdcQH3cmOr11fCpOsCkahQ+REkbJdPiYWgduFBXWz95DjvFJaKDQZeV8BfRpm1waw2Kuo/I3Q+gLZSrYHr0hsSv2sfFHqVJkY76dPOQ/POiyeZsQe9QbNhdNOSIMGdGanmc+bJfYhouN5B1oC9fo5ZC+eseexAJyDp/PwsLUOXgSRjGtFmeGtSNT+IrwY5M78uJ8dGMdSXaf/2bk+0Hcqs9u77UJLX3WGW++EkETR4vMzsiZcZia+dMriSBe/2M8sO++MeanTEuFoYmAO4kpwgvG3btqEqziTPD/vTQeSzv2v4KkxPRd1uS44i+C0+9L+e3OjNDLL54faXSsGlqnnpo4oVXiGFFYuvwc3BnabovkW2XQitu3YxI/sqcItx/YaZNHJU7OmkR5WmvamxLy6xaMXxwY9oka0Jxp/mqocisE2kR/tIYfSQc0Q5iZCvMbKEwQznkh0pjiAkH9kOcqzP4QbtGcsp7sqsJwuH2aEAQb5exbXXTgo7IHJjB4=");
		npc1.setName("");
		npc1.spawn(Manager.pegarLocation("MAGAIVER"));
		magaiver = npc1;

		Hologram holoc1 = HologramsAPI.createHologram(Main.getInstance(), npc1.getStoredLocation().add(0.0D, 2.8D, 0.0D)); //2.8
		holoc1.insertTextLine(0, "§4§lMacumbeiro");
		holoc1.insertTextLine(1, "§fClique para negociar.");
		holomagaiver = holoc1;
		new BukkitRunnable() {
			public void run() {
				Manager.pegarLocation("MAGAIVER").getWorld().playEffect(Manager.pegarLocation("MAGAIVER"), Effect.SPELL, 5);
				Manager.pegarLocation("MAGAIVER").getWorld().playEffect(Manager.pegarLocation("MAGAIVER"), Effect.SPELL, 5);
				Manager.pegarLocation("MAGAIVER").getWorld().playEffect(Manager.pegarLocation("MAGAIVER"), Effect.SPELL, 5);
			}
		}.runTaskTimer(Main.getInstance(), 0L, 20L);
		
		NPC npc11 = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "NPCMISSOES");
		npc11.data().set("player-skin-name", "yLeooSz");
		npc11.data().set("player-skin-textures", "eyJ0aW1lc3RhbXAiOjE1MjUwMjE5MjgzNzUsInByb2ZpbGVJZCI6ImI3MzZiZWI4NWFkNjRkYjA5Y2FmNTU2MTFkZWJjNDYwIiwicHJvZmlsZU5hbWUiOiJTbG90aHlWb2x0Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS85ODcxNmRlOWJmZWFhYjNmYmZiNmE5YzVhOTkwYjJiNDgzMjQ1ZmRmZWIyZjM4OTk5Yjc5ZWU3YTVhYWI0OWEifX19");
		npc11.data().set("player-skin-signature", "xQ6NSZiHvj6PuYT1gk/35xgkayadxzs+n/u4ZKgJsgtveedtr20xb0ZNgmJJRqYliK5mmNx4UY/lUy665Ms4hVHWmUlJiqGedPSoEj4dAgYvaFLIK8OZJx2FTIk2hUHobuNuNGv+DzSkU4IYf4+w85EE1t+/VB0FVQ/+izGmm/zycgoOdcQH3cmOr11fCpOsCkahQ+REkbJdPiYWgduFBXWz95DjvFJaKDQZeV8BfRpm1waw2Kuo/I3Q+gLZSrYHr0hsSv2sfFHqVJkY76dPOQ/POiyeZsQe9QbNhdNOSIMGdGanmc+bJfYhouN5B1oC9fo5ZC+eseexAJyDp/PwsLUOXgSRjGtFmeGtSNT+IrwY5M78uJ8dGMdSXaf/2bk+0Hcqs9u77UJLX3WGW++EkETR4vMzsiZcZia+dMriSBe/2M8sO++MeanTEuFoYmAO4kpwgvG3btqEqziTPD/vTQeSzv2v4KkxPRd1uS44i+C0+9L+e3OjNDLL54faXSsGlqnnpo4oVXiGFFYuvwc3BnabovkW2XQitu3YxI/sqcItx/YaZNHJU7OmkR5WmvamxLy6xaMXxwY9oka0Jxp/mqocisE2kR/tIYfSQc0Q5iZCvMbKEwQznkh0pjiAkH9kOcqzP4QbtGcsp7sqsJwuH2aEAQb5exbXXTgo7IHJjB4=");
		npc11.setName("");
		npc11.spawn(Manager.pegarLocation("MISSOES"));
		npcmissoes = npc11;

		Hologram holoc11 = HologramsAPI.createHologram(Main.getInstance(), npc11.getStoredLocation().add(0.0D, 2.8D, 0.0D)); //2.8
		holoc11.insertTextLine(0, "§b§lMissões");
		holoc11.insertTextLine(1, "§fClique para se orientar.");
		holomissoes = holoc11;
		
		
		NPC npc12 = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "NPCRECOMPENSAS");
		npc12.data().set("player-skin-name", "baecomeover");
		npc12.data().set("player-skin-textures", "eyJ0aW1lc3RhbXAiOjE1MjUwMjE5MjgzNzUsInByb2ZpbGVJZCI6ImI3MzZiZWI4NWFkNjRkYjA5Y2FmNTU2MTFkZWJjNDYwIiwicHJvZmlsZU5hbWUiOiJTbG90aHlWb2x0Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS85ODcxNmRlOWJmZWFhYjNmYmZiNmE5YzVhOTkwYjJiNDgzMjQ1ZmRmZWIyZjM4OTk5Yjc5ZWU3YTVhYWI0OWEifX19");
		npc12.data().set("player-skin-signature", "xQ6NSZiHvj6PuYT1gk/35xgkayadxzs+n/u4ZKgJsgtveedtr20xb0ZNgmJJRqYliK5mmNx4UY/lUy665Ms4hVHWmUlJiqGedPSoEj4dAgYvaFLIK8OZJx2FTIk2hUHobuNuNGv+DzSkU4IYf4+w85EE1t+/VB0FVQ/+izGmm/zycgoOdcQH3cmOr11fCpOsCkahQ+REkbJdPiYWgduFBXWz95DjvFJaKDQZeV8BfRpm1waw2Kuo/I3Q+gLZSrYHr0hsSv2sfFHqVJkY76dPOQ/POiyeZsQe9QbNhdNOSIMGdGanmc+bJfYhouN5B1oC9fo5ZC+eseexAJyDp/PwsLUOXgSRjGtFmeGtSNT+IrwY5M78uJ8dGMdSXaf/2bk+0Hcqs9u77UJLX3WGW++EkETR4vMzsiZcZia+dMriSBe/2M8sO++MeanTEuFoYmAO4kpwgvG3btqEqziTPD/vTQeSzv2v4KkxPRd1uS44i+C0+9L+e3OjNDLL54faXSsGlqnnpo4oVXiGFFYuvwc3BnabovkW2XQitu3YxI/sqcItx/YaZNHJU7OmkR5WmvamxLy6xaMXxwY9oka0Jxp/mqocisE2kR/tIYfSQc0Q5iZCvMbKEwQznkh0pjiAkH9kOcqzP4QbtGcsp7sqsJwuH2aEAQb5exbXXTgo7IHJjB4=");
		npc12.setName("");
		npc12.spawn(Manager.pegarLocation("RECOMPENSAS"));
		npcrecompensas = npc12;

		Hologram holoc12 = HologramsAPI.createHologram(Main.getInstance(), npc12.getStoredLocation().add(0.0D, 2.8D, 0.0D)); //2.8
		holoc12.insertTextLine(0, "§5§lRecompensas");
		holoc12.insertTextLine(1, "§fClique para recolher.");
		holorecompensas = holoc12;

		Block b = npc1.getStoredLocation().clone().add(-1.0D, -2.0D, 0.0D).getBlock();
		if (b != null) {
			if (b.getType().equals(Material.CHEST)) {
				Chest c = (Chest) b.getState();
				c.setMetadata("CHESTMAGAIVER", new FixedMetadataValue(Main.getInstance(), "CHESTMAGAIVER"));
				chestmagaiver = c;
			}
		}
		if (Manager.checarLocExiste("HOLOARENAX1")) {
			Hologram holo1 = HologramsAPI.createHologram(Main.getInstance(), Manager.pegarLocation("HOLOARENAX1").add(0.0D, 0.0D, 0.0D));
			holo1.insertTextLine(0, "§f§lArena X1");
			holo1.insertTextLine(1, "§7(Desafios 1 contra 1 são disputados aqui)");
			holoarenax1 = holo1;
		}
		if (Manager.checarLocExiste("HOLOLOJA")) {
			Hologram holo1 = HologramsAPI.createHologram(Main.getInstance(), Manager.pegarLocation("HOLOLOJA").add(0.0D, 0.0D, 0.0D));
			holo1.insertTextLine(0, "§f§lLoja Oficial");
			holo1.insertTextLine(1, "§7(Compre e venda seus itens aqui)");
			hololoja = holo1;
		}
	}

	public static void createSoloHolo(Integer i, Location loc, Material m1, Material m2, Material m3, boolean ft) {
		if (ft) {
			if (armor.get("FR" + i) != null) {
				Faction f = FactionColl.get().getByName(getRankF(i));
				String nome = f.getName();
				String tag = f.getTag();
				Hologram holo = HologramsAPI.createHologram(Main.getInstance(), ((NPC) armor.get("FR" + i)).getStoredLocation().add(0.0D, 3.3D, 0.0D));
				holo.insertTextLine(0, "§b" + i + "§lº §bLugar");
				holo.insertTextLine(1, "§c[" + tag + "] " + nome);
				holo.insertTextLine(2, "§7" + ctopgeral.get(f.getName()) + " guerras");
				hds.put("F" + i, holo);
			}
			return;
		}
		NumberFormat df = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
		if (armor.get("R" + i) != null) {
			String nome = getRankP(i);
			Hologram holo = HologramsAPI.createHologram(Main.getInstance(), ((NPC) armor.get("R" + i)).getStoredLocation().add(0.0D, 3.1D, 0.0D));
			holo.insertTextLine(0, "§b" + i + "§lº §bLugar");
			holo.insertTextLine(1, PermissionsAPI.getPrefix(nome) + nome);
			String money = df.format(API_Economy.getScoin(nome));
			holo.insertTextLine(2, "§7" + money + " coins");
			hds.put(new StringBuilder().append(i).toString(), holo);
		}
	}

	@SuppressWarnings("deprecation")
	public static void createNPC(Integer i, Location loc, Material m1, Material m2, Material m3, boolean ft) {
		NumberFormat df = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
		if (ft) {
			Faction f = FactionColl.get().getByName(getRankF(i));
			String nome = f.getName();
			String tag = f.getTag();
			NPC np = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "FR" + i);
			np.data().set("player-skin-name", f.getLeader().getName());
			np.setName("");
			np.spawn(loc);
			armor.put("FR" + i, np);
			Hologram holo = HologramsAPI.createHologram(Main.getInstance(), np.getStoredLocation().add(0.0D, 3.3D, 0.0D));
			holo.insertTextLine(0, "§b" + i + "§lº §bLugar");
			holo.insertTextLine(1, "§c[" + tag + "] " + nome);
			holo.insertTextLine(3, "§7" + ctopgeral.get(f.getName()) + " guerras");
			hds.put("F" + i, holo);
			return;
		}
		String nome = getRankP(i);
		NPC np = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "R" + i);
		np.data().set("player-skin-name", nome);
		np.setName("");
		np.spawn(loc);
		armor.put("R" + i, np);
		Hologram holo = HologramsAPI.createHologram(Main.getInstance(), np.getStoredLocation().add(0.0D, 3.1D, 0.0D));
		holo.insertTextLine(0, "§b" + i + "§lº §bLugar");
		String prefix = PermissionsEx.getUser(nome).getGroups()[0].getPrefix();
		if (!prefix.equals("")) {
			prefix = new StringBuilder(String.valueOf(prefix.charAt(1))).toString();
		}
		if (prefix.equals("")) {
			prefix = "7";
		}
		holo.insertTextLine(1, PermissionsAPI.getPrefix(nome) + nome);
		String money = df.format(API_Economy.getScoin(nome));
		holo.insertTextLine(2, "§7" + money + " coins");
		hds.put(new StringBuilder().append(i).toString(), holo);
	}

	public static String Formatar(Double value) {
		DecimalFormat formatter = new DecimalFormat("#,##0.00");
		String formatted = formatter.format(value);
		if (formatted.endsWith(".")) {
			formatted = formatted.substring(0, formatted.length() - 1);
		}
		return formatted;
	}

	public static void recarregarTOP() {
		API_Economy.plugin.getPlayerManager().upadateMoneyTop();
		if (Manager.checarLocExiste("CENTRAL")) {
			Location central = Manager.pegarLocation("CENTRAL");
			Hologram holo = HologramsAPI.createHologram(Main.getInstance(), central);
			holo.insertTextLine(0, "§f§lMoney TOP");
			holo.insertTextLine(1, "§7(Atualizado à cada 5 minutos)");
			hds.put("CENTRAL", holo);
		}
		if (Manager.checarLocExiste("TOP1")) {
			if ((getRankP(Integer.valueOf(1)) != null) && (getRankP(Integer.valueOf(1)) != "")) {
				Location loc = Manager.pegarLocation("TOP1");
				createNPC(Integer.valueOf(1), loc, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, false);
			}
		}
		if (Manager.checarLocExiste("TOP2")) {
			if ((getRankP(Integer.valueOf(2)) != null) && (getRankP(Integer.valueOf(2)) != "")) {
				Location loc = Manager.pegarLocation("TOP2");
				createNPC(Integer.valueOf(2), loc, Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS, false);
			}
		}
		if (Manager.checarLocExiste("TOP3")) {
			if ((getRankP(Integer.valueOf(3)) != null) && (getRankP(Integer.valueOf(3)) != "")) {
				Location loc = Manager.pegarLocation("TOP3");
				createNPC(Integer.valueOf(3), loc, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, false);
			}
		}
		if (Manager.checarLocExiste("TOP4")) {
			if ((getRankP(Integer.valueOf(4)) != null) && (getRankP(Integer.valueOf(4)) != "")) {
				Location loc = Manager.pegarLocation("TOP4");
				createNPC(Integer.valueOf(4), loc, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS, false);
			}
		}
		if (Manager.checarLocExiste("TOP5")) {
			if ((getRankP(Integer.valueOf(5)) != null) && (getRankP(Integer.valueOf(5)) != "")) {
				Location loc = Manager.pegarLocation("TOP5");
				createNPC(Integer.valueOf(5), loc, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, false);
			}
		}
		/*int ch = 0;
		if (Manager.checarLocExiste("FTOP1")) {
			if ((getRankF(Integer.valueOf(1)) != null) && (getRankF(Integer.valueOf(1)) != "")) {
				Location loc = Manager.pegarLocation("FTOP1");
				createNPC(Integer.valueOf(1), loc, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, true);
				ch++;
			}
		}
		if (Manager.checarLocExiste("FTOP2")) {
			if ((getRankF(Integer.valueOf(2)) != null) && (getRankF(Integer.valueOf(2)) != "")) {
				Location loc = Manager.pegarLocation("FTOP2");
				createNPC(Integer.valueOf(2), loc, Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS, true);
				ch++;
			}
		}
		if (Manager.checarLocExiste("FTOP3")) {
			if ((getRankF(Integer.valueOf(3)) != null) && (getRankF(Integer.valueOf(3)) != "")) {
				Location loc = Manager.pegarLocation("FTOP3");
				createNPC(Integer.valueOf(3), loc, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, true);
				ch++;
			}
		}
		if (Manager.checarLocExiste("FCENTRAL")) {
			Location central = Manager.pegarLocation("FCENTRAL");
			Hologram holo = HologramsAPI.createHologram(Main.getInstance(), central.add(0.0D, 0.0D, 0.0D));
			if (ch == 0) {
				holo.insertTextLine(0, "§f§lRanking de Guerras das Facções");
				holo.insertTextLine(1, "§7(Atualizado à cada Guerra)");
				holo.insertTextLine(2, "§eDisponível após a primeira batalha!");
			} else {
				holo.insertTextLine(0, "§f§lRanking de Guerras das Facções");
				holo.insertTextLine(1, "§7(Atualizado à cada Guerra)");
			}
			hds.put("FCENTRAL", holo);
		}*/
		Bukkit.getConsoleSender().sendMessage("§aRANK RECARREGADO COM SUCESSO!");
	}

	public static void carregarTOP() {
		int i = 0;
		if (Manager.checarLocExiste("CENTRAL")) {
			Location central = Manager.pegarLocation("CENTRAL");
			Hologram holo = HologramsAPI.createHologram(Main.getInstance(), central.add(0.0D, 0.0D, 0.0D));
			holo.insertTextLine(0, "§f§lMoney TOP");
			holo.insertTextLine(1, "§7(Atualizado à cada 5 minutos)");
			hds.put("CENTRAL", holo);
			i++;
		}
		if (Manager.checarLocExiste("TOP1")) {
			if ((getRankP(Integer.valueOf(1)) != null) && (getRankP(Integer.valueOf(1)) != "")) {
				Location loc = Manager.pegarLocation("TOP1");
				createNPC(Integer.valueOf(1), loc, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, false);
				i++;
			}
		}
		if (Manager.checarLocExiste("TOP2")) {
			if ((getRankP(Integer.valueOf(2)) != null) && (getRankP(Integer.valueOf(2)) != "")) {
				Location loc = Manager.pegarLocation("TOP2");
				createNPC(Integer.valueOf(2), loc, Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS, false);
				i++;
			}
		}
		if (Manager.checarLocExiste("TOP3")) {
			if ((getRankP(Integer.valueOf(3)) != null) && (getRankP(Integer.valueOf(3)) != "")) {
				Location loc = Manager.pegarLocation("TOP3");
				createNPC(Integer.valueOf(3), loc, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, false);
				i++;
			}
		}
		if (Manager.checarLocExiste("TOP4")) {
			if ((getRankP(Integer.valueOf(4)) != null) && (getRankP(Integer.valueOf(4)) != "")) {
				Location loc = Manager.pegarLocation("TOP4");
				createNPC(Integer.valueOf(4), loc, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS, false);
				i++;
			}
		}
		if (Manager.checarLocExiste("TOP5")) {
			if ((getRankP(Integer.valueOf(5)) != null) && (getRankP(Integer.valueOf(5)) != "")) {
				Location loc = Manager.pegarLocation("TOP5");
				createNPC(Integer.valueOf(5), loc, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, false);
				i++;
			}
		}
/*		int ch = 0;
		if (Manager.checarLocExiste("FTOP1")) {
			if ((getRankF(Integer.valueOf(1)) != null) && (getRankF(Integer.valueOf(1)) != "")) {
				Location loc = Manager.pegarLocation("FTOP1");
				createNPC(Integer.valueOf(1), loc, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, true);
				i++;
				ch++;
			}
		}
		if (Manager.checarLocExiste("FTOP2")) {
			if ((getRankF(Integer.valueOf(2)) != null) && (getRankF(Integer.valueOf(2)) != "")) {
				Location loc = Manager.pegarLocation("FTOP2");
				createNPC(Integer.valueOf(2), loc, Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS, true);
				i++;
				ch++;
			}
		}
		if (Manager.checarLocExiste("FTOP3")) {
			if ((getRankF(Integer.valueOf(3)) != null) && (getRankF(Integer.valueOf(3)) != "")) {
				Location loc = Manager.pegarLocation("FTOP3");
				createNPC(Integer.valueOf(3), loc, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, true);
				i++;
				ch++;
			}
		}
		if (Manager.checarLocExiste("FCENTRAL")) {
			Location central = Manager.pegarLocation("FCENTRAL");
			Hologram holo = HologramsAPI.createHologram(Main.getInstance(), central.add(0.0D, 0.0D, 0.0D));
			if (ch == 0) {
				holo.insertTextLine(0, "§f§lRanking de Guerras das Facções");
				holo.insertTextLine(1, "§7(Atualizado à cada Guerra)");
				holo.insertTextLine(2, "§eDisponível após a primeira batalha!");
			} else {
				holo.insertTextLine(0, "§f§lRanking de Guerras das Facções");
				holo.insertTextLine(1, "§7(Atualizado à cada Guerra)");
			}
			hds.put("FCENTRAL", holo);
			i++;
		}*/
		Bukkit.getConsoleSender().sendMessage("§aCARREGADO " + i + "/7!");
		Bukkit.getConsoleSender().sendMessage("§aRANK CARREGADO COM SUCESSO!");
	}

	@EventHandler
	public static void aoTomar(EntityDamageEvent e) {
		if (npcyoutube == null) {
			return;
		}
		if (npcmissoes == null) {
			return;
		}
		if (npcrecompensas == null) {
			return;
		}
		if (CitizensAPI.getNPCRegistry().isNPC(e.getEntity())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public static void aoClicar(PlayerInteractAtEntityEvent e) {
		if (CitizensAPI.getNPCRegistry().isNPC(e.getRightClicked())) {
			if (npcyoutube != null) {
				if (CitizensAPI.getNPCRegistry().getNPC(e.getRightClicked()).getId() == npcyoutube.getId()) {
					e.setCancelled(true);
					abrirMenuYou(e.getPlayer());
				}
			}
			if (npcyoutube1 != null) {
				if (CitizensAPI.getNPCRegistry().getNPC(e.getRightClicked()).getId() == npcyoutube1.getId()) {
					e.setCancelled(true);
					abrirMenuYou(e.getPlayer());
				}
			}
		}
	}
	
	@EventHandler
	public static void aoClicar2(PlayerInteractAtEntityEvent e) {
		if (CitizensAPI.getNPCRegistry().isNPC(e.getRightClicked())) {
			if (npcmissoes != null) {
				if (CitizensAPI.getNPCRegistry().getNPC(e.getRightClicked()).getId() == npcmissoes.getId()) {
					e.setCancelled(true);
					e.getPlayer().chat("/missoes");
					//EntrarListener.mandarAction(e.getPlayer(), "§cManutenção Temporária!");
				}
			}
			if (npcmissoes1 != null) {
				if (CitizensAPI.getNPCRegistry().getNPC(e.getRightClicked()).getId() == npcmissoes1.getId()) {
					e.setCancelled(true);
					e.getPlayer().chat("/missoes");
					//EntrarListener.mandarAction(e.getPlayer(), "§cManutenção Temporária!");
				}
			}
		}
	}
	
	@EventHandler
	public static void aoClicar3(PlayerInteractAtEntityEvent e) {
		if (CitizensAPI.getNPCRegistry().isNPC(e.getRightClicked())) {
			if (npcrecompensas != null) {
				if (CitizensAPI.getNPCRegistry().getNPC(e.getRightClicked()).getId() == npcrecompensas.getId()) {
					e.setCancelled(true);
					e.getPlayer().chat("/recompensas");
				}
			}
			if (npcrecompensas1 != null) {
				if (CitizensAPI.getNPCRegistry().getNPC(e.getRightClicked()).getId() == npcrecompensas1.getId()) {
					e.setCancelled(true);
					e.getPlayer().chat("/recompensas");
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void abrirMenuYou(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, "§8Informações para o Youtuber");
		ItemStack j = new ItemStack(Material.SKULL_ITEM);
		j.setDurability((short) 3);
		Bukkit.getUnsafe().modifyItemStack(j, "{display:{Name:\"Youtube\"},SkullOwner:{Id:\"c8a11994-e4b0-4179-9387-7964bd0eb733\",Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzNTNmZDBmODYzMTQzNTM4NzY1ODYwNzViOWJkZjBjNDg0YWFiMDMzMWI4NzJkZjExYmQ1NjRmY2IwMjllZCJ9fX0=\"}]}}}");
		SkullMeta jm = (SkullMeta) j.getItemMeta();
		jm.setDisplayName("§cRequisitos");
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add("§fTag §c[YouTuber]§f:");
		lore.add("§f • Canal com, no mínimo, §7300 §finscritos.");
		lore.add("§f • Feedback compatível com o número de inscritos.");
		lore.add("");
		jm.setLore(lore);
		j.setItemMeta(jm);
		inv.setItem(13, j);
		p.openInventory(inv);
	}
	
	@EventHandler
	public static void aoClicar(InventoryClickEvent e) {
		if (e.getInventory().getTitle().equals("§8Informações para o Youtuber")) {
			if (e.getCurrentItem() == null) {
				return;
			}
			if (e.getCurrentItem().getType().equals(Material.AIR)) {
				return;
			}
			e.setCancelled(true);
		}
	}

	@EventHandler
	public static void aoFlechar(EntityDamageByEntityEvent e) {
		if ((e.getDamager() instanceof Arrow)) {
			if (e.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
				if (e.getEntity().getCustomName() != null) {
					if (e.getEntity().getCustomName().contains("R")) {
						e.getDamager().remove();
					}
				}
			}
			if (npcyoutube == null) {
				return;
			}
			if (npcmissoes == null) {
				return;
			}
			if (CitizensAPI.getNPCRegistry().isNPC(e.getEntity())) {
				e.getDamager().remove();
			}
		}
	}

	public static String getRankP(Integer n) {
		String nome = "";
		List<MoneyTop> out = API_Economy.plugin.getPlayerManager().getMoneyTop();
		int i = 0;
		for (MoneyTop cp : out) {
			if (i != n.intValue()) {
				i++;
			}
			if (i == n.intValue()) {
				nome = cp.getNome();
				return nome;
			}
		}
		return nome;
	}

	public static String getRankF(Integer n) {
		String nome = "";
		int i = 0;
		for (String fac : getTopFacGeral().keySet()) {
			if (i != n.intValue()) {
				i++;
			}
			if (i == n.intValue()) {
				nome = fac;
				return nome;
			}
		}
		return nome;
	}

	@SuppressWarnings("unused")
	public static void carregarTopGeralFac() {
		HashMap<String, Integer> list2 = new HashMap<String, Integer>();
		for (Faction f : FactionColl.get().getAll()) {
			int ca = 0;
			if (f.getName().contains("Área livre")) {
				ca++;
			}
			if (f.getName().contains("Área segura")) {
				ca++;
			}
			if (f.getName().contains("Área de Guerra")) {
				ca++;
			}
			/*if (ca == 0) {
				int i = 0;
				for (MPlayer mp : f.getMPlayers()) {
					if (Perfil.existePlayer(mp.getName())) {
						i += Perfil.getPerfil(mp.getName()).getWinsGuerra();
					}
				}
				if (i > 0) {
					list2.put(f.getName(), Integer.valueOf(i));
				}
			}*/
		}
		SortedMap<String, Integer> st = ImmutableSortedMap.copyOf(list2, Ordering.natural().reverse().onResultOf(Functions.forMap(list2)).compound(Ordering.natural().reverse()));
		ctopgeral = st;
	}

	public static SortedMap<String, Integer> getTopFacGeral() {
		return ctopgeral;
	}

	public static double getTotalCoins(Faction f) {
		double t = 0.0D;
		for (MPlayer mp : f.getMPlayers()) {
			t += API_Economy.getMoney(mp.getName());
		}
		return t;
	}
}