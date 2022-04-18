package com.wandy.api.caixas.armaduras;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class ItensCaixas {
	
	public static HashMap<ItemStack, String> lis = new HashMap<ItemStack, String>();
	public static List<ItemStack> lon = new ArrayList<ItemStack>();
	public static HashMap<ItemStack, String> lisa = new HashMap<ItemStack, String>();
	public static HashMap<ItemStack, String> lisb = new HashMap<ItemStack, String>();
	public static HashMap<ItemStack, String> lisc = new HashMap<ItemStack, String>();
	public static HashMap<ItemStack, String> lisd = new HashMap<ItemStack, String>();
	public static List<ItemStack> lona = new ArrayList<ItemStack>();
	public static List<ItemStack> lonb = new ArrayList<ItemStack>();
	public static List<ItemStack> lonc = new ArrayList<ItemStack>();
	public static List<ItemStack> lond = new ArrayList<ItemStack>();
	public static HashMap<String, List<ItemStack>> lon2 = new HashMap<String, List<ItemStack>>();

	@SuppressWarnings("deprecation")
	public static ItemStack getItemLendaria() {
		ItemStack item = null;
		Random r = new Random();
		int rn = r.nextInt(58);
		int i = 0;
		for (ItemStack got : getLista()) {
			if (i == 0) {
				int min = Integer.valueOf((lis.get(got)).split("@")[0]).intValue();
				int max = Integer.valueOf((lis.get(got)).split("@")[1]).intValue();
				if (rn >= min) {
					if (rn <= max) {
						i++;
						item = got;
					}
				}
			}
		}
		if (item == null) {
			item = new ItemStack(Material.getMaterial(313), 1, (short) 0);
			item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
			item.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		}
		return item;
	}

	@SuppressWarnings("deprecation")
	public static ItemStack getItemLendaria(int v) {
		ItemStack item = null;
		Random r = new Random();
		int rn = r.nextInt(14);
		int i = 0;
		if (v == 310) {
			for (ItemStack got : getLista(v)) {
				if (i == 0) {
					int min = Integer.valueOf((lisa.get(got)).split("@")[0]).intValue();
					int max = Integer.valueOf((lisa.get(got)).split("@")[1]).intValue();
					if (rn >= min) {
						if (rn <= max) {
							i++;
							item = got;
						}
					}
				}
			}
		}
		if (v == 311) {
			for (ItemStack got : getLista(v)) {
				if (i == 0) {
					int min = Integer.valueOf((lisb.get(got)).split("@")[0]).intValue();
					int max = Integer.valueOf((lisb.get(got)).split("@")[1]).intValue();
					if (rn >= min) {
						if (rn <= max) {
							i++;
							item = got;
						}
					}
				}
			}
		}
		if (v == 312) {
			for (ItemStack got : getLista(v)) {
				if (i == 0) {
					int min = Integer.valueOf((lisc.get(got)).split("@")[0]).intValue();
					int max = Integer.valueOf((lisc.get(got)).split("@")[1]).intValue();
					if (rn >= min) {
						if (rn <= max) {
							i++;
							item = got;
						}
					}
				}
			}
		}
		if (v == 313) {
			for (ItemStack got : getLista(v)) {
				if (i == 0) {
					int min = Integer.valueOf((lisd.get(got)).split("@")[0]).intValue();
					int max = Integer.valueOf((lisd.get(got)).split("@")[1]).intValue();
					if (rn >= min) {
						if (rn <= max) {
							i++;
							item = got;
						}
					}
				}
			}
		}
		if (item == null) {
			item = new ItemStack(Material.getMaterial(v), 1, (short) 0);
			item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			item.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		}
		return item;
	}

	@SuppressWarnings("deprecation")
	public static List<ItemStack> getLista(int v) {
		if ((v == 310) && (lona.size() > 0)) {
			return lona;
		}
		if ((v == 311) && (lonb.size() > 0)) {
			return lonb;
		}
		if ((v == 312) && (lonc.size() > 0)) {
			return lonc;
		}
		if ((v == 313) && (lond.size() > 0)) {
			return lond;
		}
		
		HashMap<ItemStack, String> lan = new HashMap<ItemStack, String>();
		List<ItemStack> las = new ArrayList<ItemStack>();

		ItemStack a = new ItemStack(Material.getMaterial(v), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		lan.put(a, "0@4");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(v), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		lan.put(a, "5@8");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(v), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		lan.put(a, "9@11");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(v), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		lan.put(a, "12@13");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(v), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		lan.put(a, "14@14");
		las.add(a);

		Collections.reverse(las);
		if (v == 310) {
			lisa = lan;
			lona = las;
			return lona;
		}
		if (v == 311) {
			lisb = lan;
			lonb = las;
			return lonb;
		}
		if (v == 312) {
			lisc = lan;
			lonc = las;
			return lonc;
		}
		if (v == 313) {
			lisd = lan;
			lond = las;
			return lond;
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public static List<ItemStack> getLista() {
		if (lon.size() > 0) {
			return lon;
		}
		
		HashMap<ItemStack, String> lan = new HashMap<ItemStack, String>();
		List<ItemStack> las = new ArrayList<ItemStack>();

		ItemStack a = new ItemStack(Material.getMaterial(313), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		lan.put(a, "0@4");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(312), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		lan.put(a, "5@9");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(311), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		lan.put(a, "10@14");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(310), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		lan.put(a, "15@19");
		las.add(a);

		a = new ItemStack(Material.getMaterial(313), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		lan.put(a, "20@23");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(312), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		lan.put(a, "24@27");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(311), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		lan.put(a, "28@31");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(310), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		lan.put(a, "32@35");
		las.add(a);

		a = new ItemStack(Material.getMaterial(313), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		lan.put(a, "36@38");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(312), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		lan.put(a, "39@41");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(311), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		lan.put(a, "42@44");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(310), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		lan.put(a, "45@46");
		las.add(a);

		a = new ItemStack(Material.getMaterial(313), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		lan.put(a, "47@48");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(312), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		lan.put(a, "49@50");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(311), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		lan.put(a, "51@52");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(310), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		lan.put(a, "53@54");
		las.add(a);

		a = new ItemStack(Material.getMaterial(313), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		lan.put(a, "55@55");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(312), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		lan.put(a, "56@56");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(311), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		lan.put(a, "57@57");
		las.add(a);
		
		a = new ItemStack(Material.getMaterial(310), 1, (short) 0);
		a.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		lan.put(a, "58@58");
		las.add(a);

		Collections.reverse(las);
		lis = lan;
		lon = las;
		return lon;
	}
}
