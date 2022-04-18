package com.wandy.api.stattrack;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import com.wandy.api.Main;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class MagaiverListener implements Listener {
	
	public static HashMap<String, String> last = new HashMap<String, String>();
	public static HashMap<String, Entity> maga = new HashMap<String, Entity>();
	public static NumberFormat numberFormat;

	public static boolean checarItem(ItemStack i) {
		if ((i.getType().equals(Material.DIAMOND_SWORD)) || (i.getType().equals(Material.GOLD_SWORD)) || (i.getType().equals(Material.IRON_SWORD)) || (i.getType().equals(Material.WOOD_SWORD)) || (i.getType().equals(Material.STONE_SWORD)) || (i.getType().equals(Material.ARROW)) || (i.getType().equals(Material.DIAMOND_AXE)) || (i.getType().equals(Material.GOLD_AXE)) || (i.getType().equals(Material.STONE_AXE)) || (i.getType().equals(Material.WOOD_AXE)) || (i.getType().equals(Material.IRON_AXE))) {
			return true;
		}
		return false;
	}

	public static boolean temStat(ItemStack i) {
		int a = 0;
		if (i.getItemMeta().hasLore()) {
			for (String linhas : i.getItemMeta().getLore()) {
				if (linhas.contains("§cMaldição:")) {
					a++;
				}
			}
			if (a > 0) {
				return true;
			}
		}
		return false;
	}
	
	public static void adicionarItem(ItemStack i) {
		List<String> lore = new ArrayList<String>();
		if (i.getItemMeta().hasLore()) {
			for (String strs : i.getItemMeta().getLore()) {
				lore.add(strs);
			}
		}
		lore.add("§cMaldição: §f0");
		ItemMeta im = i.getItemMeta();
		im.setLore(lore);
		i.setItemMeta(im);
	}

	@EventHandler
	public static void aoMatar(PlayerDeathEvent e) {
		if ((e.getEntity().getKiller() instanceof Player)) {
			Player p = e.getEntity().getKiller();
			if (p.getItemInHand() == null) {
				return;
			}
			if (p.getItemInHand().getType().equals(Material.AIR)) {
				return;
			}
			if (checarItem(p.getItemInHand())) {
				if (temStat(p.getItemInHand())) {
					if (last.containsKey(p.getName())) {
						if (last.get(p.getName()).equals(e.getEntity().getName())) {
							p.sendMessage("§cMate um usuário diferente para continuar a contagem da maldição.");
							return;
						}
					}
					int nala = getLinha(p.getItemInHand());
					String lin = p.getItemInHand().getItemMeta().getLore().get(nala);
					String array = lin.replace("§cMaldição: §f", "");
					int nun = Integer.valueOf(array.replace(".", "").replace(" ", "")).intValue();
					int dps = nun + 1;
					String nali = "§cMaldição: §f" + format(dps);
					List<String> lore = p.getItemInHand().getItemMeta().getLore();
					lore.set(nala, nali);
					ItemMeta im = p.getItemInHand().getItemMeta();
					im.setLore(lore);
					p.getItemInHand().setItemMeta(im);
					if (last.containsKey(p.getName())) {
						last.replace(p.getName(), e.getEntity().getName());
						return;
					}
					last.put(p.getName(), e.getEntity().getName());
					return;
				}
			}
		}
	}

	public static int getAbates(ItemStack i) {
		int nala = getLinha(i);
		String lin = i.getItemMeta().getLore().get(nala);
		String array = lin.replace("§cMaldição: §f", "");
		int nun = Integer.valueOf(array.replace(".", "").replace(" ", "")).intValue();
		return nun;
	}

	public static int getLinha(ItemStack i) {
		int a = 0;
		String para = "S";
		String for0 = "S";
		for (String sts : i.getItemMeta().getLore()) {
			if (!para.equals("N")) {
				if (sts.contains("§cMaldição:")) {
					if (a == 0) {
						for0 = "N";
					}
					if (para.equals("S")) {
						para = "N";
					}
				}
				a++;
			}
		}
		a--;
		if (for0.equals("N")) {
			a = 0;
		}
		return a;
	}

	public static ItemStack resetarConta(ItemStack i) {
		int nala = getLinha(i);
		int dps = 0;
		String nali = "§cMaldição: §f" + format(dps);
		List<String> lore = i.getItemMeta().getLore();
		lore.set(nala, nali);
		ItemMeta im = i.getItemMeta();
		im.setLore(lore);
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack setarConta(ItemStack i, int dps) {
		int nala = getLinha(i);
		String nali = "§cMaldição: §f" + format(dps);
		List<String> lore = i.getItemMeta().getLore();
		lore.set(nala, nali);
		ItemMeta im = i.getItemMeta();
		im.setLore(lore);
		i.setItemMeta(im);
		return i;
	}

	public static String format(double value) {
		numberFormat = NumberFormat.getNumberInstance(Locale.forLanguageTag("en-US"));
		if (value <= 1.0D) {
			return numberFormat.format(value).concat(" ").concat("");
		}
		return numberFormat.format(value).concat(" ").concat("").replace(",", ".").replace(" ", "");
	}

	public static void removerNPC(final String nome) {
		for (final World w : Bukkit.getWorlds()) {
			for (final Entity e : w.getEntities()) {
				if (e instanceof Villager && e.getCustomName() != null && e.getCustomName().equalsIgnoreCase(nome)) {
					((Villager) e).setHealth(0.0);
				}
			}
		}
	}

	public static void criarNPC(Location loc, String nome) {
		LivingEntity v = (LivingEntity) Bukkit.getWorld(loc.getWorld().getName()).spawn(loc, Villager.class);
		v.setCustomName(nome);
		v.setCustomNameVisible(true);
		v.setMetadata("STATTRAK", new FixedMetadataValue(Main.plugin, "§5Magaiver"));
		v.setNoDamageTicks(5);
		freezeNPC(v);
	}

	public static void freezeNPC(Entity en) {
		net.minecraft.server.v1_8_R3.Entity nmsEn = ((CraftEntity) en).getHandle();
		NBTTagCompound compound = new NBTTagCompound();
		nmsEn.c(compound);
		compound.setByte("NoAI", (byte) 1);
		nmsEn.f(compound);
	}

	public static void carregarMaga() {
	}

	public static void removerMaga() {
		Entity np = (Entity) maga.get("STATTRAK");
		np.remove();
	}

	@EventHandler
	public void ClickOnVillager(PlayerInteractEntityEvent e) {
		if ((e.getRightClicked() instanceof Villager)) {
			Player p = e.getPlayer();
			Villager v = (Villager) e.getRightClicked();
			if (v.getCustomName() != null) {
				if (v.getCustomName().equalsIgnoreCase("§5Magaiver")) {
					e.setCancelled(true);
					StatMenu.abrirMenu(p);
				}
			}
		}
	}

	@EventHandler
	public void DamageOnVillager(EntityDamageByEntityEvent e) {
		if ((e.getEntity() instanceof Villager)) {
			Villager v = (Villager) e.getEntity();
			if (v.getCustomName() != null) {
				if (v.getCustomName().equalsIgnoreCase("§5Magaiver")) {
					e.setCancelled(true);
				}
			}
		}
	}
}
