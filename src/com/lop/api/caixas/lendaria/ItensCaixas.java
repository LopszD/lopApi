package com.wandy.api.caixas.lendaria;

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
		int rn = r.nextInt(70);
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
		
		ItemStack a = new ItemStack(Material.getMaterial(42), 16, (short) 0);
		lan.put(a, "0@5");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(41), 16, (short) 0);
		lan.put(a, "6@10");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(57), 16, (short) 0);
		lan.put(a, "11@15");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(133), 16, (short) 0);
		lan.put(a, "16@20");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(49), 64, (short) 0);
		lan.put(a, "21@25");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(7), 64, (short) 0);
		lan.put(a, "26@30");
		las.add(a);
		
		a = new ItemStack(Material.FIREWORK, 4);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName("?eLan?ador");
		am.setLore(Arrays.asList(new String[] { "?7Ao usar voc? ser? lan?ado 40 blocos para cima." }));
		a.setItemMeta(am);
		lan.put(a, "31@33");
		las.add(a);

		a = new ItemStack(Material.BLAZE_ROD, 2);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		am = a.getItemMeta();
		am.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		am.setDisplayName("?6Raio Mestre");
		am.setLore(Arrays.asList(new String[] { "?7Use para lan?ar um raio aonde voc?", "?7estiver olhando." }));
		a.setItemMeta(am);
		lan.put(a, "34@36");
		las.add(a);

		a = new ItemStack(Material.BLAZE_ROD, 4);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		am = a.getItemMeta();
		am.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		am.setDisplayName("?6Raio Mestre");
		am.setLore(Arrays.asList(new String[] { "?7Use para lan?ar um raio aonde voc?", "?7estiver olhando." }));
		a.setItemMeta(am);
		lan.put(a, "39@39");
		las.add(a);

		a = new ItemStack(Material.getMaterial(383), 4, (short) 50);
		lan.put(a, "40@41");
		las.add(a);

		a = new ItemStack(Material.MONSTER_EGG, 2, (short) 50);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		am = a.getItemMeta();
		am.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		am.setDisplayName("?aSuper Creeper");
		am.setLore(Arrays.asList(new String[] { "?7Use para invocar um creeper eletrizado", "?7que causar? 5 de dano em blocos." }));
		a.setItemMeta(am);
		lan.put(a, "42@43");
		las.add(a);

		a = new ItemStack(Material.NETHER_STAR, 2);
		am = a.getItemMeta();
		am.setDisplayName("?6+1 de Poder M?ximo");
		List<String> lore = new ArrayList<String>();
		lore.add("?7Ativando este item voc? aumenta");
		lore.add("?7 1 ponto em seu poder m?ximo.");
		lore.add("?1");
		lore.add("?7* Limite de poder m?ximo: 20");
		am.setLore(lore);
		a.setItemMeta(am);
		lan.put(a, "44@44");
		las.add(a);

		a = new ItemStack(Material.NETHER_STAR, 4);
		am = a.getItemMeta();
		am.setDisplayName("?6+1 de Poder M?ximo");
		lore = new ArrayList<String>();
		lore.add("?7Ativando este item voc? aumenta");
		lore.add("?7 1 ponto em seu poder m?ximo.");
		lore.add("?1");
		lore.add("?7* Limite de poder m?ximo: 20");
		am.setLore(lore);
		a.setItemMeta(am);
		lan.put(a, "46@46");
		las.add(a);

		a = new ItemStack(Material.getMaterial(261), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 5);
		a.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 2);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		a.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
		lan.put(a, "47@47");
		las.add(a);

		a = new ItemStack(Material.getMaterial(278), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.DIG_SPEED, 5);
		a.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		lan.put(a, "48@48");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(278), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.DIG_SPEED, 5);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		a.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
		lan.put(a, "49@49");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(276), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 6);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		a.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
		lan.put(a, "50@50");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(276), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 6);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		a.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
		a.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
		lan.put(a, "51@51");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(313), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		lan.put(a, "52@52");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(312), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		lan.put(a, "53@53");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(311), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		lan.put(a, "54@54");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(310), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		lan.put(a, "55@55");
		las.add(a);

		//a = new ItemStack(Material.getMaterial(138), 1);
		//lan.put(a, "56@56");
		//las.add(a);

		Collections.reverse(las);
		lis = lan;
		lon = las;
		return lon;
	}
}
