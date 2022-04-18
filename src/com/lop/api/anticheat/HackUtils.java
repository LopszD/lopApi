package com.wandy.api.anticheat;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class HackUtils {

	public static ArrayList<String> avisos = new ArrayList<>();
	public static HashMap<Player, Integer> FastAttackClicks = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> MacroClicks = new HashMap<Player, Integer>();
	public static ArrayList<Player> fly = new ArrayList<>();
	public static String FastAttack = null;
	public static String Forcefield = null;
	public static String Macro = null;
	public static String Fly = null;
	public static String Speed = null;
	public static String ForjandoAutoSoup = null;
	public static String AutoSoup = null;

	public static HashMap<Player, Integer> ClicksFastClicks = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> ClicksMacro = new HashMap<Player, Integer>();

	public static HashMap<Player, Integer> AvisosForcefield = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> AvisosFastClick = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> AvisosFly = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> AvisosSpeed = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> AvisosMacro = new HashMap<Player, Integer>();

	public static enum Hack {

		FASTCLICKTALVEZ(API.PrefixStaffer+"§fO jogador §7nick §c§lTALVEZ §festá clicando muito rápido. §7(clicks hits) §4(AVISO avisos)"),
		FASTCLICKPROVAVELMENTE(API.PrefixStaffer+"§fO jogador §7nick §c§lPROVAVELMENTE §festá clicando muito rapido. §7(clicks hits) §4(AVISO avisos)"),
		FASTCLICKDEFINITIVAMENTE(API.PrefixStaffer+"§fO jogador §7nick §c§lDEFINITIVAMENTE §festá clicando muito rapido. §7(clicks hits) §4(AVISO avisos)"),
		
		MACROTALVEZ(API.PrefixStaffer+"§fO jogador §7nick §c§l§c§lTALVEZ §festá usando macro. §7(clicks hits) §4(AVISO avisos)"),
		MACROPROVAVELMENTE(API.PrefixStaffer+"§fO jogador §7nick §c§l§c§lPROVAVELMENTE §festá usando macro. §7(clicks hits) §4(AVISO avisos)"),
		MACRODEFINITIVAMENTE(API.PrefixStaffer+"§fO jogador §7nick §c§l§c§lDEFINITIVAMENTE §festá usando macro. §7(clicks hits) §4(AVISO avisos)"),
		
		FORCEFIELDTALVEZ(API.PrefixStaffer+"§fO jogador §7nick §c§lTALVEZ §festá usando forcefield. §4(AVISO avisos)"),
		FORCEFIELDPROVAVELMENTE(API.PrefixStaffer+"§fO jogador §7nick §c§lPROVAVELMENTE §festá usando forcefield. §4(AVISO avisos)"),
		FORCEFIELDDEFINITIVAMENTE(API.PrefixStaffer+"§fO jogador §7nick §c§lDEFINITIVAMENTE §festá usando forcefield. §4(AVISO avisos)"),
		
		FLY(API.PrefixStaffer+"§fO jogador §7nick §c§lPODE §festar usando fly. §7(Ping ms) §4(AVISO avisos)"),
		
		SPEED(API.PrefixStaffer+"§fO jogador §7nick §c§lPODE §festar usando speed. §7(Ping ms) §4(AVISO avisos)");
		
		private String Mensagem;

		private Hack(String Mensagem) { this.Mensagem = Mensagem; }

		public String getMensagem() {
			return this.Mensagem;
		}
	}

	public static void sendAntiCheat(String Mensagem) {

		for (Player playeres : Bukkit.getOnlinePlayers()) {
			if (playeres.hasPermission("wandy.avisos")) {
					playeres.sendMessage(Mensagem);
				}
			}
		}

	public static boolean Flight(Player playeres) {
		return (playeres.getGameMode() == GameMode.CREATIVE) || (playeres.getAllowFlight());
	}

	public static boolean Speeding(Player playeres) {
		return (playeres.getGameMode() == GameMode.CREATIVE) || (playeres.getAllowFlight());
	}

	public static void setPlayer(Player player) {
		AvisosFastClick.put(player, Integer.valueOf(1));
		AvisosMacro.put(player, Integer.valueOf(1));
		AvisosFly.put(player, Integer.valueOf(1));
		AvisosSpeed.put(player, Integer.valueOf(1));
		AvisosForcefield.put(player, Integer.valueOf(1));

		ClicksFastClicks.put(player, Integer.valueOf(1));
		ClicksMacro.put(player, Integer.valueOf(1));
		avisos.add(player.getName());
	}
	
	public static void removePlayer(Player player) {
		AvisosFastClick.remove(player);
		AvisosMacro.remove(player);
		AvisosFly.remove(player);
		AvisosSpeed.remove(player);
		AvisosForcefield.remove(player);

		ClicksFastClicks.remove(player);
		ClicksMacro.remove(player);
		avisos.add(player.getName());
	}
}
