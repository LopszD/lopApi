package com.wandy.api.caixas.astral;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.wandy.api.Main;
import com.wandy.api.commands.FakeCommand;
import com.wandy.api.utils.fanciful.FancyMessage;
import com.wandy.api.utils.partículas.ParticleEffect;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Animação implements Listener {

	public static ItemStack getVidrinho() {
		Random r = new Random();
		int ran = r.nextInt(15);
		if (ran == 7) {
			ran = 3;
		}
		if (ran == 8) {
			ran = 4;
		}
		if (ran == 15) {
			ran = 5;
		}
		ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) ran);
		return item;
	}

	public static void spawnerAnime(Player p, Material m) {
		Inventory inv = Bukkit.createInventory((InventoryHolder) null, 27, "§8Caixa Misteriosa");
		p.openInventory(inv);
		p.updateInventory();
		int essa = new BukkitRunnable() {
			int i = 0;
			public void run() {
				inv.setItem(0, Animação.getVidrinho());
				inv.setItem(1, Animação.getVidrinho());
				inv.setItem(2, Animação.getVidrinho());
				inv.setItem(3, Animação.getVidrinho());
				inv.setItem(5, Animação.getVidrinho());
				inv.setItem(6, Animação.getVidrinho());
				inv.setItem(7, Animação.getVidrinho());
				inv.setItem(8, Animação.getVidrinho());
				inv.setItem(9, Animação.getVidrinho());
				inv.setItem(17, Animação.getVidrinho());
				inv.setItem(18, Animação.getVidrinho());
				inv.setItem(19, Animação.getVidrinho());
				inv.setItem(20, Animação.getVidrinho());
				inv.setItem(21, Animação.getVidrinho());
				inv.setItem(23, Animação.getVidrinho());
				inv.setItem(24, Animação.getVidrinho());
				inv.setItem(25, Animação.getVidrinho());
				inv.setItem(26, Animação.getVidrinho());
				if (this.i == 0) {
					++this.i;
					inv.setItem(4, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15));
					inv.setItem(22, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15));
					inv.setItem(10, ItensCaixas.getItemLendaria());
					inv.setItem(11, ItensCaixas.getItemLendaria());
					inv.setItem(12, ItensCaixas.getItemLendaria());
					inv.setItem(13, ItensCaixas.getItemLendaria());
					inv.setItem(14, ItensCaixas.getItemLendaria());
					inv.setItem(15, ItensCaixas.getItemLendaria());
					inv.setItem(16, ItensCaixas.getItemLendaria());
					inv.setItem(16, ItensCaixas.getItemLendaria());
					p.playSound(p.getLocation(), Sound.CLICK, 0.5f, 20.0f);
					return;
				}
				++this.i;
				inv.setItem(10, inv.getItem(11));
				inv.setItem(11, inv.getItem(12));
				inv.setItem(12, inv.getItem(13));
				inv.setItem(13, inv.getItem(14));
				inv.setItem(14, inv.getItem(15));
				inv.setItem(15, inv.getItem(16));
				inv.setItem(16, ItensCaixas.getItemLendaria());
				p.playSound(p.getLocation(), Sound.CLICK, 0.5f, 20.0f);
			}
		}.runTaskTimerAsynchronously(Main.getInstance(), 0L, 4L).getTaskId();
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				Bukkit.getScheduler().cancelTask(essa);
			}
		}, 160L);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				ParticleEffect.SPELL_WITCH.display(1.0f, 1.0f, 1.0f, 20.0f, 70, p.getLocation(), 10.0);
				p.playSound(p.getLocation(), Sound.NOTE_PLING, 10.0f, 1.0f);
				ItemStack item = inv.getItem(13);
				p.updateInventory();
				String checki = "A";
				if (!Animação.checarInv(p, 1)) {
					checki = "B";
					p.getWorld().dropItemNaturally(p.getLocation(), item);
				}
				if (p.getOpenInventory() != null && p.getOpenInventory().getTitle().equals("§8Caixa Misteriosa")) {
					p.closeInventory();
				}
				if (checki.equals("A")) {
					p.getInventory().addItem(new ItemStack[] { item });
				}
				inv.setItem(13, item);
				Animação.anuncioCaixa(p, Animação.getEspe(item), item);
			}
		}, 200L);
		p.updateInventory();
	}

	public static int getEspe(ItemStack i) {
		int r = 0;
		if (i.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)) {
			if (i.containsEnchantment(Enchantment.DURABILITY)) {
				r = 4;
			}
		}
		if (i.getType() == Material.MONSTER_EGG) {
			r = 3;
		}
		if (i.getType() == Material.ENDER_CHEST) {
			r = 3;
		}
		if (i.getType() == Material.BLAZE_POWDER) {
			r = 3;
		}
		if (i.getType() == Material.POTION) {
			r = 3;
		}
		if (i.getType() == Material.FIREWORK) {
			r = 4;
		}
		if (i.getType() == Material.ENDER_PORTAL_FRAME) {
			r = 4;
		}
		if (i.getType() == Material.DIAMOND_PICKAXE) {
			r = 4;
		}
		if (i.getType() == Material.DIAMOND_SWORD) {
			r = 4;
		}
		if (i.getType() == Material.FIREWORK) {
			r = 4;
		}
		return r;
	}

	@SuppressWarnings("deprecation")
	public static void anuncioCaixa(Player p, int r, ItemStack i) {
		if (r > 0) {
			String prefix = PermissionsEx.getUser(p).getGroups()[0].getPrefix();
			if (!prefix.equals("")) {
				prefix = new StringBuilder(String.valueOf(prefix.charAt(1))).toString();
			}
			if (prefix.equals("")) {
				prefix = "7";
			}
			String bruto = "";
			ChatColor c = null;
			if (r == 3) {
				bruto = "§5raro";
				c = ChatColor.DARK_PURPLE;
			}
			if (r == 4) {
				bruto = "§6ultra raro";
				c = ChatColor.GOLD;
				p.getWorld().strikeLightning(p.getLocation().clone().add(0.0D, 5.0D, 0.0D));
			}
			if (FakeCommand.temFake(p.getName())) {
				FancyMessage fa = new FancyMessage("").then("§b[Caixa Misteriosa]").color(ChatColor.AQUA).then(" §b" + FakeCommand.getFake(p.getName())).color(ChatColor.GRAY).itemTooltip(i).then(" §7abriu uma caixa §fAstral §7e ganhou um item ").color(ChatColor.GRAY).itemTooltip(i).then(bruto + "§7!").color(c).itemTooltip(i);
				for (Player todos : Bukkit.getOnlinePlayers()) {
					fa.send(todos);
				}
			} else {
				FancyMessage fa = new FancyMessage("").then("§b[Caixa Misteriosa]").color(ChatColor.AQUA).then(" §" + prefix + p.getName()).color(ChatColor.GRAY).itemTooltip(i).then(" §7abriu uma caixa §fAstral §7e ganhou um item ").color(ChatColor.GRAY).itemTooltip(i).then(bruto + "§7!").color(c).itemTooltip(i);
				for (Player todos : Bukkit.getOnlinePlayers()) {
					fa.send(todos);
				}
			}
		}
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (e.getInventory().getName().equalsIgnoreCase("§8Caixa Misteriosa")) {
			if (e.getCurrentItem() == null) {
				return;
			}
			if (e.getCurrentItem().getType() == Material.AIR) {
				return;
			}
			e.setCancelled(true);
		}
	}

	public static void enviarJogador(Player p, ItemStack stack, String lvl) {
		net.minecraft.server.v1_8_R3.ItemStack nms = CraftItemStack.asNMSCopy(stack);
		new FancyMessage("").then("§a" + p.getName() + " ganhou um item " + lvl + " na caixa misteriosa!").tooltip(nms.getName());
	}

	public static boolean checarInv(Player p, Integer i) {
		int antes = 36 - i;
		int v = 0;
		ItemStack[] contents;
		for (int length = (contents = p.getInventory().getContents()).length, j = 0; j < length; ++j) {
			ItemStack itens = contents[j];
			if (itens != null && !(itens.getType() == (Object) Material.AIR)) {
				++v;
			}
		}
		return v <= antes;
	}
}
