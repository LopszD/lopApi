package com.wandy.api.caixas.astral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItensCaixas {
	
	public static HashMap<ItemStack, String> lis = new HashMap<ItemStack, String>();
	public static List<ItemStack> lon = new ArrayList<ItemStack>();

	@SuppressWarnings("deprecation")
	public static ItemStack getItemLendaria() {
		ItemStack item = null;
		Random r = new Random();
		int rn = r.nextInt(55);
		int i = 0;
		for (ItemStack got : getLista()) {
			if (i == 0) {
				int min = Integer.valueOf(((String) lis.get(got)).split("@")[0]).intValue();
				int max = Integer.valueOf(((String) lis.get(got)).split("@")[1]).intValue();
				if (rn >= min) {
					if (rn <= max) {
						i++;
						item = got;
					}
				}
			}
		}
		if (item == null) {
			item = new ItemStack(Material.getMaterial(42), 16, (short) 0);
		}
		return item;
	}

	@SuppressWarnings("deprecation")
	public static List<ItemStack> getLista() {
		if (lon.size() > 0) {
			return lon;
		}
		
		HashMap<ItemStack, String> lan = new HashMap<ItemStack, String>();
		List<ItemStack> las = new ArrayList<ItemStack>();

		ItemStack a = new ItemStack(Material.getMaterial(42), 64, (short) 0);
		lan.put(a, "0@5");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(41), 64, (short) 0);
		lan.put(a, "6@10");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(57), 32, (short) 0);
		lan.put(a, "11@16");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(133), 32, (short) 0);
		lan.put(a, "17@22");
		las.add(a);

		a = new ItemStack(Material.getMaterial(49), 64, (short) 0);
		lan.put(a, "23@27");
		las.add(a);

		a = new ItemStack(Material.getMaterial(7), 64, (short) 0);
		lan.put(a, "28@33");
		las.add(a);

		a = new ItemStack(Material.MONSTER_EGG, 5, (short) 50);
		lan.put(a, "34@36");
		las.add(a);

		ItemStack f = new ItemStack(Material.ENDER_CHEST, 2);
		ItemMeta fm = f.getItemMeta();
		fm.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		fm.setDisplayName("§4Caixa Misteriosa");
		List<String> lf = new ArrayList<String>();
		lf.add("§1");
		lf.add("§7Abra está caixa e receba um item");
		lf.add("§7incrível que será sorteado.");
		lf.add("§2");
		lf.add("§fTipo da caixa: §4Épica");
		fm.setLore(lf);
		f.setItemMeta(fm);
		lan.put(f, "38@39");
		las.add(f);

		ItemStack a3 = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta am3 = a3.getItemMeta();
		am3.setDisplayName("§6Booster de Drop");
		am3.setLore(Arrays.asList(new String[] { "§7Use para receber o dobro de drops do tipo", "§7de mob que você selecionar." }));
		a3.setItemMeta(am3);
		lan.put(a3, "40@41");
		las.add(a3);

		a = new ItemStack(Material.MONSTER_EGG, 2, (short) 50);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am = a.getItemMeta();
		am.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		am.setDisplayName("§aSuper Creeper");
		am.setLore(Arrays.asList(new String[] { "§7Use para invocar um creeper eletrizado", "§7que causará 5 de dano em blocos." }));
		a.setItemMeta(am);
		lan.put(a, "42@43");
		las.add(a);

		ItemStack ac = new ItemStack(Material.POTION, 1, (short) 8233);
		ItemMeta amc = ac.getItemMeta();
		amc.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		amc.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
		amc.setDisplayName("§1§5Poção de Força II e Velocidade II");
		amc.setLore(Arrays.asList(new String[] { "§7Força II (2:30)", "§7Velocidade II (2:30)", "§1", "§eItem encontrado na Caixa Misteriosa", "§e Astral." }));
		ac.setItemMeta(amc);
		lan.put(ac, "44@45");
		las.add(ac);

		ItemStack a4 = new ItemStack(Material.FIREWORK, 20);
		ItemMeta am4 = a4.getItemMeta();
		am4.setDisplayName("§eLançador");
		am4.setLore(Arrays.asList(new String[] { "§7Ao usar você será lançado 40 blocos para cima." }));
		a4.setItemMeta(am4);
		lan.put(a4, "46@46");
		las.add(a4);

		ItemStack g = new ItemStack(Material.ENDER_PORTAL_FRAME, 3);
		ItemMeta gm = g.getItemMeta();
		gm.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		gm.setDisplayName("§6Caixa Misteriosa");
		List<String> lg = new ArrayList<String>();
		lg.add("§1");
		lg.add("§7Abra está caixa e receba um item");
		lg.add("§7incrível que será sorteado.");
		lg.add("§2");
		lg.add("§fTipo da caixa: §6Lendária");
		gm.setLore(lg);
		g.setItemMeta(gm);
		lan.put(g, "47@47");
		las.add(g);
		
		a = new ItemStack(Material.getMaterial(278), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.DIG_SPEED, 6);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		am = a.getItemMeta();
		am.setLore(Arrays.asList(new String[] { "§eItem encontrado na Caixa Misteriosa", "§e Astral." }));
		a.setItemMeta(am);
		lan.put(a, "48@48");
		las.add(a);

		a = new ItemStack(Material.getMaterial(276), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 6);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		a.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
		am = a.getItemMeta();
		am.setLore(Arrays.asList(new String[] { "§eItem encontrado na Caixa Misteriosa", "§e Astral." }));
		a.setItemMeta(am);
		lan.put(a, "49@49");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(276), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 6);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		a.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
		am = a.getItemMeta();
		am.setLore(Arrays.asList(new String[] { "§7Sobre Carga II", "", "§eItem encontrado na Caixa Misteriosa", "§e Astral." }));
		a.setItemMeta(am);
		lan.put(a, "50@50");
		las.add(a);

		a = new ItemStack(Material.getMaterial(313), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		am = a.getItemMeta();
		am.setLore(Arrays.asList(new String[] { "§eItem encontrado na Caixa Misteriosa", "§e Astral." }));
		a.setItemMeta(am);
		lan.put(a, "51@51");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(312), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		am = a.getItemMeta();
		am.setLore(Arrays.asList(new String[] { "§eItem encontrado na Caixa Misteriosa", "§e Astral." }));
		a.setItemMeta(am);
		lan.put(a, "52@52");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(311), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		am = a.getItemMeta();
		am.setLore(Arrays.asList(new String[] { "§eItem encontrado na Caixa Misteriosa", "§e Astral." }));
		a.setItemMeta(am);
		lan.put(a, "53@53");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(310), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		am = a.getItemMeta();
		am.setLore(Arrays.asList(new String[] { "§eItem encontrado na Caixa Misteriosa", "§e Astral." }));
		a.setItemMeta(am);
		lan.put(a, "54@54");
		las.add(a);

		a = new ItemStack(Material.getMaterial(278), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 2);
		am = a.getItemMeta();
		am.setLore(Arrays.asList(new String[] { "§eItem encontrado na Caixa Misteriosa", "§e Astral." }));
		a.setItemMeta(am);
		lan.put(a, "55@55");
		las.add(a);

		Collections.reverse(las);
		lis = lan;
		lon = las;
		return lon;
	}
}
