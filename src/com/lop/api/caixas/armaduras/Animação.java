package com.wandy.api.caixas.armaduras;

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

	public static void spawnerAnime(Player p) {
		Inventory inv = Bukkit.createInventory((InventoryHolder) null, 54, "§8Caixa Misteriosa");
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
				inv.setItem(26, Animação.getVidrinho());
				inv.setItem(27, Animação.getVidrinho());
				inv.setItem(35, Animação.getVidrinho());
				inv.setItem(36, Animação.getVidrinho());
				inv.setItem(44, Animação.getVidrinho());
				inv.setItem(45, Animação.getVidrinho());
				inv.setItem(46, Animação.getVidrinho());
				inv.setItem(47, Animação.getVidrinho());
				inv.setItem(48, Animação.getVidrinho());
				inv.setItem(49, Animação.getVidrinho());
				inv.setItem(50, Animação.getVidrinho());
				inv.setItem(51, Animação.getVidrinho());
				inv.setItem(52, Animação.getVidrinho());
				inv.setItem(53, Animação.getVidrinho());
				if (this.i == 0) {
					++this.i;
					inv.setItem(4, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15));
					inv.setItem(22, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15));
					inv.setItem(10, ItensCaixas.getItemLendaria(310));
					inv.setItem(11, ItensCaixas.getItemLendaria(310));
					inv.setItem(12, ItensCaixas.getItemLendaria(310));
					inv.setItem(13, ItensCaixas.getItemLendaria(310));
					inv.setItem(14, ItensCaixas.getItemLendaria(310));
					inv.setItem(15, ItensCaixas.getItemLendaria(310));
					inv.setItem(16, ItensCaixas.getItemLendaria(310));
					inv.setItem(19, ItensCaixas.getItemLendaria(311));
					inv.setItem(20, ItensCaixas.getItemLendaria(311));
					inv.setItem(21, ItensCaixas.getItemLendaria(311));
					inv.setItem(22, ItensCaixas.getItemLendaria(311));
					inv.setItem(23, ItensCaixas.getItemLendaria(311));
					inv.setItem(24, ItensCaixas.getItemLendaria(311));
					inv.setItem(25, ItensCaixas.getItemLendaria(311));
					inv.setItem(28, ItensCaixas.getItemLendaria(312));
					inv.setItem(29, ItensCaixas.getItemLendaria(312));
					inv.setItem(30, ItensCaixas.getItemLendaria(312));
					inv.setItem(31, ItensCaixas.getItemLendaria(312));
					inv.setItem(32, ItensCaixas.getItemLendaria(312));
					inv.setItem(33, ItensCaixas.getItemLendaria(312));
					inv.setItem(34, ItensCaixas.getItemLendaria(312));
					inv.setItem(37, ItensCaixas.getItemLendaria(313));
					inv.setItem(38, ItensCaixas.getItemLendaria(313));
					inv.setItem(39, ItensCaixas.getItemLendaria(313));
					inv.setItem(40, ItensCaixas.getItemLendaria(313));
					inv.setItem(41, ItensCaixas.getItemLendaria(313));
					inv.setItem(42, ItensCaixas.getItemLendaria(313));
					inv.setItem(43, ItensCaixas.getItemLendaria(313));
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
				inv.setItem(16, ItensCaixas.getItemLendaria(310));
				inv.setItem(19, inv.getItem(20));
				inv.setItem(20, inv.getItem(21));
				inv.setItem(21, inv.getItem(22));
				inv.setItem(22, inv.getItem(23));
				inv.setItem(23, inv.getItem(24));
				inv.setItem(24, inv.getItem(25));
				inv.setItem(25, ItensCaixas.getItemLendaria(311));
				inv.setItem(28, inv.getItem(29));
				inv.setItem(29, inv.getItem(30));
				inv.setItem(30, inv.getItem(31));
				inv.setItem(31, inv.getItem(32));
				inv.setItem(32, inv.getItem(33));
				inv.setItem(33, inv.getItem(34));
				inv.setItem(34, ItensCaixas.getItemLendaria(312));
				inv.setItem(37, inv.getItem(38));
				inv.setItem(38, inv.getItem(39));
				inv.setItem(39, inv.getItem(40));
				inv.setItem(40, inv.getItem(41));
				inv.setItem(41, inv.getItem(42));
				inv.setItem(42, inv.getItem(43));
				inv.setItem(43, ItensCaixas.getItemLendaria(313));
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
				ItemStack item2 = inv.getItem(22);
				ItemStack item3 = inv.getItem(31);
				ItemStack item4 = inv.getItem(40);
				p.updateInventory();
				String checki = "A";
				if (!Animação.checarInv(p, 4)) {
					checki = "B";
					p.getWorld().dropItemNaturally(p.getLocation(), item);
					p.getWorld().dropItemNaturally(p.getLocation(), item2);
					p.getWorld().dropItemNaturally(p.getLocation(), item3);
					p.getWorld().dropItemNaturally(p.getLocation(), item4);
				}
				if (p.getOpenInventory() != null && p.getOpenInventory().getTitle().equals("§8Caixa Misteriosa")) {
					p.closeInventory();
				}
				if (checki.equals("A")) {
					p.getInventory().addItem(new ItemStack[] { item });
					p.getInventory().addItem(new ItemStack[] { item2 });
					p.getInventory().addItem(new ItemStack[] { item3 });
					p.getInventory().addItem(new ItemStack[] { item4 });
				}
				inv.setItem(13, item);
				inv.setItem(22, item);
				inv.setItem(31, item);
				inv.setItem(40, item);
			}
		}, 200L);
		p.updateInventory();
	}

	public static int getEspe(ItemStack i) {
		int r = 0;
		if (i.containsEnchantment(Enchantment.DAMAGE_ALL)) {
			if (i.getEnchantmentLevel(Enchantment.DAMAGE_ALL) >= 4) {
				r = 3;
			}
		}
		if (i.getType().equals(Material.GOLDEN_APPLE)) {
			r = 3;
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
				FancyMessage fa = new FancyMessage("").then("§b[Caixa Misteriosa]").color(ChatColor.AQUA).then(" §b" + FakeCommand.getFake(p.getName())).color(ChatColor.GRAY).itemTooltip(i).then(" §7abriu uma caixa §fInicial §7e ganhou um item ").color(ChatColor.GRAY).itemTooltip(i).then(bruto + "§7!").color(c).itemTooltip(i);
				for (Player todos : Bukkit.getOnlinePlayers()) {
					fa.send(todos);
				}
			} else {
				FancyMessage fa = new FancyMessage("").then("§b[Caixa Misteriosa]").color(ChatColor.AQUA).then(" §" + prefix + p.getName()).color(ChatColor.GRAY).itemTooltip(i).then(" §7abriu uma caixa §fInicial §7e ganhou um item ").color(ChatColor.GRAY).itemTooltip(i).then(bruto + "§7!").color(c).itemTooltip(i);
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
			if (itens != null && !(itens.getType() == Material.AIR)) {
				++v;
			}
		}
		return v <= antes;
	}
}
