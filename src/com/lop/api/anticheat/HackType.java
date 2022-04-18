package com.wandy.api.anticheat;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.wandy.api.Main;

public class HackType implements Listener{
	
	@EventHandler
	private void onPlayerInteractEvent(PlayerInteractEvent Evento) {
		if (!(Evento.getPlayer() instanceof Player)) {
			return;
		}
		if (!Evento.getAction().name().contains("LEFT")) {
			return;
		}
		final Player Jogador = Evento.getPlayer();
		if (HackUtils.FastAttackClicks.containsKey(Jogador)) {
			HackUtils.FastAttackClicks.put(Jogador, Integer.valueOf(Integer.valueOf(((Integer)HackUtils.FastAttackClicks.get(Jogador)).intValue()).intValue() + 1));
		} else {
			HackUtils.FastAttackClicks.put(Jogador, Integer.valueOf(1));
		}
		if (HackUtils.ClicksFastClicks.containsKey(Jogador)) {
			HackUtils.ClicksFastClicks.put(Jogador, Integer.valueOf(Integer.valueOf(((Integer)HackUtils.ClicksFastClicks.get(Jogador)).intValue()).intValue() + 1));
		} else {
			HackUtils.ClicksFastClicks.put(Jogador, Integer.valueOf(1));
		}
		HackUtils.Hack FastClickTalvez = HackUtils.Hack.FASTCLICKTALVEZ;
		HackUtils.Hack FastClickProvelmente = HackUtils.Hack.FASTCLICKPROVAVELMENTE;
		HackUtils.Hack FastClickDefinitimante = HackUtils.Hack.FASTCLICKDEFINITIVAMENTE;
		new BukkitRunnable() {
			
			@Override
			public void run() {
				HackUtils.ClicksFastClicks.remove(Jogador);
				HackUtils.FastAttackClicks.remove(Jogador);	
			}
		}.runTaskLater(Main.getPlugin(), 20L);
		if (((Integer)HackUtils.FastAttackClicks.get(Jogador)).intValue() >= 50) {
			HackUtils.FastAttack = FastClickTalvez.getMensagem().replace("nick", Jogador.getDisplayName().toString()).replace("avisos", HackUtils.AvisosFastClick.get(Jogador) + "").replace("clicks", HackUtils.ClicksFastClicks.get(Jogador) + "");
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
				public void run() {
					if (HackUtils.FastAttackClicks.containsKey(Jogador)) {
						HackUtils.FastAttackClicks.remove(Jogador);
					}
					if (HackUtils.FastAttack != null) {
						HackUtils.AvisosFastClick.put(Jogador, Integer.valueOf(HackUtils.AvisosFastClick.get(Jogador) + 1));
						HackUtils.sendAntiCheat(HackUtils.FastAttack);
					}
					HackUtils.FastAttack = null;
					HackUtils.ClicksFastClicks.remove(Jogador);
					HackUtils.FastAttackClicks.remove(Jogador);
				}
			}
			, 20L);
		}
		if (((Integer)HackUtils.FastAttackClicks.get(Jogador)).intValue() >= 60) {
			HackUtils.FastAttack = FastClickProvelmente.getMensagem().replace("nick", Jogador.getDisplayName().toString()).replace("avisos", HackUtils.AvisosFastClick.get(Jogador) + "").replace("clicks", HackUtils.ClicksFastClicks.get(Jogador) + "");
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
				public void run() {
					if (HackUtils.FastAttackClicks.containsKey(Jogador)) {
						HackUtils.FastAttackClicks.remove(Jogador);
					}
					if (HackUtils.FastAttack != null) {
						HackUtils.AvisosFastClick.put(Jogador, Integer.valueOf(HackUtils.AvisosFastClick.get(Jogador) + 1));
						HackUtils.sendAntiCheat(HackUtils.FastAttack);
					}
					HackUtils.FastAttack = null;
					HackUtils.ClicksFastClicks.remove(Jogador);
					HackUtils.FastAttackClicks.remove(Jogador);
				}
			}
			, 20L);
		}
		if (((Integer)HackUtils.FastAttackClicks.get(Jogador)).intValue() >= 80) {
			HackUtils.FastAttack = FastClickDefinitimante.getMensagem().replace("nick", Jogador.getDisplayName().toString()).replace("avisos", HackUtils.AvisosFastClick.get(Jogador) + "").replace("clicks", HackUtils.ClicksFastClicks.get(Jogador) + "");
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
				public void run() {
					if (HackUtils.FastAttackClicks.containsKey(Jogador)) {
						HackUtils.FastAttackClicks.remove(Jogador);
					}
					if (HackUtils.FastAttack != null) {
						HackUtils.AvisosFastClick.put(Jogador, Integer.valueOf(HackUtils.AvisosFastClick.get(Jogador) + 1));
						HackUtils.sendAntiCheat(HackUtils.FastAttack);
					}
					HackUtils.ClicksFastClicks.remove(Jogador);
					HackUtils.FastAttackClicks.remove(Jogador);
				}
			}
			, 20L);
		}
	}
	
private static HashMap<UUID, Long> JogadorSendoAtacado = new HashMap<UUID, Long>();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onSoup(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Damageable hp = p;
		if (hp.getHealth() != 20.0D) {
			int sopa = 7;
	        if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && (p.getItemInHand().getTypeId() == 282)) {
	        	p.setHealth(hp.getHealth() + sopa > hp.getMaxHealth() ? hp.getMaxHealth() : hp.getHealth() + sopa);
	        	p.getItemInHand().setType(Material.BOWL);
	        }
		}
	}
	  
	@EventHandler
	private void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player)) {
			return;
		}
	    Player player = (Player)event.getDamager();
	    SetarAtacandoTempo(player.getUniqueId());
	}
	
	@EventHandler
	public void onInventoryInteract(InventoryClickEvent Evento) {
		Player Jogador = (Player)Evento.getWhoClicked();
		if (!(Evento.getWhoClicked() instanceof Player)) {
			return;
		}
		if (Evento.isCancelled()) {
			return;
		}
		if (Evento.getAction() != InventoryAction.MOVE_TO_OTHER_INVENTORY) {
			return;
		}
		if (Evento.getSlot() == -1) {
			return;
		}
		long Tempo = System.currentTimeMillis() - PegarAtacandoTempo(Jogador.getUniqueId());
		int Botes = 0;
		ItemStack[] ItemStacks;
		int ASoup = (ItemStacks = Jogador.getInventory().getContents()).length;
		for (int i = 0; i < ASoup; i++) {
			ItemStack ItemStack = ItemStacks[i];
			if ((ItemStack != null) && (ItemStack.getType() == Material.BOWL)) {
				Botes += ItemStack.getAmount();
			}
		}
		if (Tempo >= 99L) {
			return;
		}
		if (Botes < 5) {
			return;
		}
	}
	  
	public static long PegarAtacandoTempo(UUID uuid) {
		if (!JogadorSendoAtacado.containsKey(uuid)) {
			JogadorSendoAtacado.put(uuid, Long.valueOf(System.currentTimeMillis() / 1000L));
	    }
		return ((Long)JogadorSendoAtacado.get(uuid)).longValue();
	}
	  
	public static void SetarAtacandoTempo(UUID uuid) {
	    JogadorSendoAtacado.put(uuid, Long.valueOf(System.currentTimeMillis()));
	}
	
	@EventHandler
	private void onEntityDamageByEntityEvent(EntityDamageByEntityEvent Evento) {
		if (!(Evento.getDamager() instanceof Player)) {
			return;
		}
		if (!(Evento.getEntity() instanceof LivingEntity)) {
			return;
		}
		Player Jogador = (Player)Evento.getDamager();
	    HackUtils.Hack ForcefieldTalvez = HackUtils.Hack.FORCEFIELDTALVEZ;
	    HackUtils.Hack ForcefieldProvavelmente = HackUtils.Hack.FORCEFIELDPROVAVELMENTE;
	    HackUtils.Hack ForcefieldDefinitivamente = HackUtils.Hack.FORCEFIELDDEFINITIVAMENTE;
	    if (((Evento.getEntity() instanceof LivingEntity)) && ((Evento.getDamager() instanceof Player))) {
	    	if (Evento.getEntity().getLocation().distance(Jogador.getLocation()) > 6.7D) {
	    		HackUtils.Forcefield = ForcefieldDefinitivamente.getMensagem().replace("nick", Jogador.getDisplayName()).replace("avisos", HackUtils.AvisosForcefield.get(Jogador) + "");
	    		if (HackUtils.Forcefield != null) {
	    			HackUtils.AvisosForcefield.put(Jogador, Integer.valueOf(HackUtils.AvisosForcefield.get(Jogador) + 1));
	    			HackUtils.sendAntiCheat(HackUtils.Forcefield);
	    		}
	    		HackUtils.Forcefield = null;
	    	}
	    	else if (Evento.getEntity().getLocation().distance(Jogador.getLocation()) > 6.5D) {
	    		HackUtils.Forcefield = ForcefieldProvavelmente.getMensagem().replace("nick", Jogador.getDisplayName()).replace("avisos", HackUtils.AvisosForcefield.get(Jogador) + "");
	    		if (HackUtils.Forcefield != null) {
	    			HackUtils.AvisosForcefield.put(Jogador, Integer.valueOf(HackUtils.AvisosForcefield.get(Jogador) + 1));
	    			HackUtils.sendAntiCheat(HackUtils.Forcefield);
	    		}
	    		HackUtils.Forcefield = null;
	    	}
	    	else if (Evento.getEntity().getLocation().distance(Jogador.getLocation()) > 6.0D) {
	    		HackUtils.Forcefield = ForcefieldTalvez.getMensagem().replace("nick", Jogador.getDisplayName()).replace("avisos", HackUtils.AvisosForcefield.get(Jogador) + "");
	    		if (HackUtils.Forcefield != null) {
	    			HackUtils.AvisosForcefield.put(Jogador, Integer.valueOf(HackUtils.AvisosForcefield.get(Jogador) + 1));
	    			HackUtils.sendAntiCheat(HackUtils.Forcefield);
	    		}
	    		HackUtils.Forcefield = null;
	    	}
	    }
	}
	
	@EventHandler
	private void onInventoryClickEvent(InventoryClickEvent Evento) { 
		if (!(Evento.getWhoClicked() instanceof Player)) {
			return;
		}
	    if (!Evento.isShiftClick()) {
	    	return;
	    }
	    final Player Jogador = (Player)Evento.getWhoClicked();
	    if (HackUtils.MacroClicks.containsKey(Jogador))
	    	HackUtils.MacroClicks.put(Jogador, Integer.valueOf(Integer.valueOf(((Integer)HackUtils.MacroClicks.get(Jogador)).intValue()).intValue() + 1));
	    else {
	    	HackUtils.MacroClicks.put(Jogador, Integer.valueOf(1));
	    }
	    if (HackUtils.ClicksMacro.containsKey(Jogador))
	    	HackUtils.ClicksMacro.put(Jogador, Integer.valueOf(Integer.valueOf(((Integer)HackUtils.ClicksMacro.get(Jogador)).intValue()).intValue() + 1));
	    else {
	    	HackUtils.ClicksMacro.put(Jogador, Integer.valueOf(1));
	    }
	    HackUtils.Hack MacroTalvez = HackUtils.Hack.MACROTALVEZ;
	    HackUtils.Hack MacroProvavelmente = HackUtils.Hack.MACROPROVAVELMENTE;
	    HackUtils.Hack MacroDefinitivamente = HackUtils.Hack.MACRODEFINITIVAMENTE;
	    new BukkitRunnable() {
			
			@Override
			public void run() {
				HackUtils.ClicksMacro.remove(Jogador);
				HackUtils.MacroClicks.remove(Jogador);	
			}
		}.runTaskLater(Main.getPlugin(), 20L);
	    if (((Integer)HackUtils.MacroClicks.get(Jogador)).intValue() >= 45) {
	    	HackUtils.Macro = MacroTalvez.getMensagem().replace("nick", Jogador.getDisplayName()).replace("avisos", HackUtils.AvisosMacro.get(Jogador) + "").replace("clicks", HackUtils.ClicksMacro.get(Jogador) + "");
	    
	    	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
	    		public void run() {
	    			if (HackUtils.Macro != null) {
	    				HackUtils.AvisosMacro.put(Jogador, Integer.valueOf(HackUtils.AvisosMacro.get(Jogador) + 1));
	    				HackUtils.sendAntiCheat(HackUtils.Macro);
	    			}
	    			if (HackUtils.MacroClicks.containsKey(Jogador)) {
	    				HackUtils.MacroClicks.remove(Jogador);
	    			}
	    			HackUtils.Macro = null;
	    			HackUtils.ClicksMacro.put(Jogador, Integer.valueOf(0));
	    		}
	    	}
	    	, 20L);
	    }
	    if (((Integer)HackUtils.MacroClicks.get(Jogador)).intValue() >= 55) {
	    	HackUtils.Macro = MacroProvavelmente.getMensagem().replace("nick", Jogador.getDisplayName()).replace("avisos", HackUtils.AvisosMacro.get(Jogador) + "").replace("clicks", HackUtils.ClicksMacro.get(Jogador) + "");
	    
	    	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
	    		public void run() {
	    			if (HackUtils.Macro != null) {
	    				HackUtils.AvisosMacro.put(Jogador, Integer.valueOf(HackUtils.AvisosMacro.get(Jogador) + 1));
	    				HackUtils.sendAntiCheat(HackUtils.Macro);
	    			}
	    			if (HackUtils.MacroClicks.containsKey(Jogador)) {
	    				HackUtils.MacroClicks.remove(Jogador);
	    			}
	    			HackUtils.Macro = null;
	    			HackUtils.ClicksMacro.put(Jogador, Integer.valueOf(0));
	    		}
	    	}
	    	, 20L);
	    }
	    if (((Integer)HackUtils.MacroClicks.get(Jogador)).intValue() >= 65) {
	    	HackUtils.Macro = MacroDefinitivamente.getMensagem().replace("nick", Jogador.getDisplayName()).replace("avisos", HackUtils.AvisosMacro.get(Jogador) + "").replace("clicks", HackUtils.ClicksMacro.get(Jogador) + "");
	    
	    	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
	    		public void run() {
	    			if (HackUtils.Macro != null) {
	    				HackUtils.AvisosMacro.put(Jogador, Integer.valueOf(HackUtils.AvisosMacro.get(Jogador) + 1));
	    				HackUtils.sendAntiCheat(HackUtils.Macro);
	    			}
	    			if (HackUtils.MacroClicks.containsKey(Jogador)) {
	    				HackUtils.MacroClicks.remove(Jogador);
	    			}
	    			HackUtils.Macro = null;
	    			HackUtils.ClicksMacro.put(Jogador, Integer.valueOf(0));
	    		}
	    	}
	    	, 20L);
	    }
	}
}
