package com.wandy.api.listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.wandy.api.Main;
import com.wandy.api.commands.ReinícioCommand;
import com.wandy.api.commands.VanishCommand;

public class CombatLogListener implements Listener {
	
	public static HashMap<String, Integer> cooldown = new HashMap<String, Integer>();
	public static HashMap<String, Integer> combate = new HashMap<String, Integer>();

	public static void startTask() {
		new BukkitRunnable() {
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (CombatLogListener.combate.containsKey(p.getName())) {
						int t = CombatLogListener.combate.get(p.getName()).intValue();
						int dps = t - 1;
						if (dps <= 0) {
							CombatLogListener.combate.remove(p.getName());
							p.sendMessage("§aVocê §lnão §aestá mais em combate, agora você pode deslogar sem perigo.");
						}
						if (dps > 0) {
							CombatLogListener.combate.replace(p.getName(), Integer.valueOf(dps));
						}
					}
					if (CombatLogListener.cooldown.containsKey(p.getName())) {
						int t = CombatLogListener.cooldown.get(p.getName()).intValue();
						int dps = t - 1;
						if (dps <= 0) {
							CombatLogListener.cooldown.remove(p.getName());
						}
						if (dps > 0) {
							CombatLogListener.cooldown.replace(p.getName(), Integer.valueOf(dps));
						}
					}
					if (AFKListener.afk.containsKey(p.getName())) {
						if (!p.hasPermission("wandy.semafk")) {
							if (AFKListener.afk.get(p.getName()).intValue() >= 180) {
								AFKListener.afk.remove(p.getName());
								p.kickPlayer("§c§lREDE WANDY\n§1\n§cVocê foi desconectado por ficar três minutos sem se movimentar.");
							} else {
								int jatem = (AFKListener.afk.get(p.getName())).intValue();
								int dps = jatem + 1;
								AFKListener.afk.replace(p.getName(), Integer.valueOf(dps));
							}
						}
					}
				}
			}
		}.runTaskTimer(Main.plugin, 30L, 30L);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoHitar(EntityDamageByEntityEvent e) {
		if ((e.getDamager() instanceof Arrow)) {
			if ((e.getEntity() instanceof Player)) {
				Arrow arrow = (Arrow) e.getDamager();
				if ((arrow.getShooter() instanceof Player)) {
					Player p = (Player) e.getEntity();
					Player p1 = (Player) arrow.getShooter();
					if (!e.isCancelled()) {
						if (e.getDamage() > 0.0D) {
							if (VanishCommand.vanish.contains(p1.getName())) {
								return;
							}
							if (p1.getGameMode().equals(GameMode.CREATIVE)) {
								return;
							}
							if (!combate.containsKey(p.getName())) {
								combate.put(p.getName(), Integer.valueOf(15));
								p.sendMessage("§cVocê entrou em combate. Aguarde até 15s para poder deslogar.");
							}
							if (!combate.containsKey(p1.getName())) {
								combate.put(p1.getName(), Integer.valueOf(15));
								p1.sendMessage("§cVocê entrou em combate. Aguarde até 15s para poder deslogar.");
							}
							if (combate.containsKey(p.getName())) {
								combate.replace(p.getName(), Integer.valueOf(15));
							}
							if (combate.containsKey(p1.getName())) {
								combate.replace(p1.getName(), Integer.valueOf(15));
							}
						}
					}
				}
			}
		}
		if ((e.getDamager() instanceof Player)) {
			if ((e.getEntity() instanceof Player)) {
				Player p = (Player) e.getEntity();
				Player p1 = (Player) e.getDamager();
				if (!e.isCancelled()) {
					if (e.getDamage() > 0.0D) {
						if (VanishCommand.vanish.contains(p1.getName())) {
							return;
						}
						if (p1.getGameMode().equals(GameMode.CREATIVE)) {
							return;
						}
						if (!combate.containsKey(p.getName())) {
							combate.put(p.getName(), Integer.valueOf(15));
							p.sendMessage("§cVocê entrou em combate. Aguarde até 15s para poder deslogar.");
						}
						if (!combate.containsKey(p1.getName())) {
							combate.put(p1.getName(), Integer.valueOf(15));
							p1.sendMessage("§cVocê entrou em combate. Aguarde até 15s para poder deslogar.");
						}
						if (combate.containsKey(p.getName())) {
							combate.replace(p.getName(), Integer.valueOf(15));
						}
						if (combate.containsKey(p1.getName())) {
							combate.replace(p1.getName(), Integer.valueOf(15));
						}
					}
				}
			}
		}
	}

	@EventHandler
	public static void aoMorrer(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if ((e.getEntity() instanceof Player)) {
			if (combate.containsKey(p.getName())) {
				combate.remove(p.getName());
				return;
			}
		}
	}

	@EventHandler
	public static void aoSair(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (combate.containsKey(p.getName())) {
			if (ReinícioCommand.ras) {
				combate.remove(p.getName());
			} else {
				Bukkit.broadcastMessage(" §c[!] " + p.getName() + " deslogou em combate e perdeu todos os seus itens.");
				combate.remove(p.getName());
				p.setHealth(0.0D);
			}
		}
	}

	@EventHandler
	public static void aoComandar(PlayerCommandPreprocessEvent e) {
		if (CombatLogListener.combate.containsKey(e.getPlayer().getName())) {
			if (!e.getPlayer().hasPermission("wandy.semcombatlog")) {
				e.setCancelled(true);
				int rs = CombatLogListener.combate.get(e.getPlayer().getName()).intValue();
				Player p = e.getPlayer();
				p.sendMessage("§cO uso de comandos em combate está desabilitado.");
				EntrarListener.mandarAction(p, "§cAguarde " + rs + " segundos e tente novamente.");
				e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.VILLAGER_NO, 1.0F, 1.0F);
				return;
			}
		}
	}

	@EventHandler
	public static void aoJogar(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (p.getInventory().getItemInHand().getType().equals(Material.ENDER_PEARL)) {
				if (CombatLogListener.combate.containsKey(p.getName())) {
					e.setCancelled(true);
					p.sendMessage("§cVocê não pode jogar uma enderpearl em combate!");
				}
			}
		}
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			if (p.getInventory().getItemInHand().getType().equals(Material.ENDER_PEARL)) {
				if (CombatLogListener.combate.containsKey(p.getName())) {
					e.setCancelled(true);
					p.sendMessage("§cVocê não pode jogar uma enderpearl em combate!");
				}
			}
		}
	}

	public static boolean taCombate(Player p) {
		if (combate.containsKey(p.getName())) {
			return true;
		}
		return false;
	}

	public static void tirarCombate(Player p) {
		if (combate.containsKey(p.getName())) {
			combate.remove(p.getName());
			return;
		}
	}
}
