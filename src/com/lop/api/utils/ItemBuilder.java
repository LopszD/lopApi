package com.wandy.api.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.md_5.bungee.api.ChatColor;

public class ItemBuilder {

	private ItemStack is;

	public ItemBuilder(Material m) {
		this(m, 1, (short) 0);
	}

	public ItemBuilder(int id) {
		this(id, 1, (short) 0);
	}

	public ItemBuilder(ItemStack is) {
		this.is = is.clone();
	}

	public ItemBuilder(Material m, int amount, short data) {
		is = new ItemStack(m, amount, data);
	}

	@SuppressWarnings("deprecation")
	public ItemBuilder(int id, int amount, short data) {
		is = new ItemStack(id, amount, data);
	}

	public ItemBuilder clone() {
		return new ItemBuilder(is);
	}

	public ItemBuilder durability(int dur) {
		is.setDurability((short) dur);
		return this;
	}

	public ItemBuilder name(String name) {
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		is.setItemMeta(im);
		return this;
	}

	public ItemBuilder unsafeEnchantment(Enchantment ench, int level) {
		is.addUnsafeEnchantment(ench, level);
		return this;
	}

	public ItemBuilder enchant(Enchantment ench, int level) {
		ItemMeta im = is.getItemMeta();
		im.addEnchant(ench, level, true);
		is.setItemMeta(im);
		return this;
	}

	public ItemBuilder removeEnchantment(Enchantment ench) {
		is.removeEnchantment(ench);
		return this;
	}

	public ItemBuilder owner(String owner) {
		try {
			SkullMeta im = (SkullMeta) is.getItemMeta();
			im.setOwner(owner);
			is.setItemMeta(im);
		} catch (ClassCastException expected) {
		}
		return this;
	}

	public ItemBuilder infinityDurabilty() {
		is.setDurability(Short.MAX_VALUE);
		return this;
	}

	public ItemBuilder lore(String... lore) {
		ItemMeta im = is.getItemMeta();
		List<String> out = im.getLore() == null ? new ArrayList<>() : im.getLore();
		for (String string : lore)
			out.add(ChatColor.translateAlternateColorCodes('&', string));
		im.setLore(out);
		is.setItemMeta(im);
		return this;
	}

	public ItemBuilder listlore(List<String> lore) {
		ItemMeta im = is.getItemMeta();
		im.setLore(lore);
		is.setItemMeta(im);
		return this;
	}

	@SuppressWarnings("deprecation")
	public ItemBuilder woolColor(DyeColor color) {
		if (!is.getType().equals(Material.WOOL))
			return this;
		is.setDurability(color.getDyeData());
		return this;
	}

	public ItemBuilder amount(int amount) {
		if (amount > 64)
			amount = 64;
		is.setAmount(amount);
		return this;
	}

	public ItemBuilder removeAttributes() {
		ItemMeta meta = is.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		is.setItemMeta(meta);
		return this;
	}

	public ItemStack build() {
		return is;
	}

	public ItemBuilder color(Color color) {
		if (!is.getType().name().contains("LEATHER_"))
			return this;
		LeatherArmorMeta meta = (LeatherArmorMeta) is.getItemMeta();
		meta.setColor(color);
		is.setItemMeta(meta);
		return this;
	}

	public ItemBuilder(Material m, int quantia) {
		this.is = new ItemStack(m, quantia);
	}

	public ItemBuilder(Material m, int quantia, byte durabilidade) {
		this.is = new ItemStack(m, quantia, durabilidade);
	}

	public ItemBuilder setDurability(short durabilidade) {
		this.is.setDurability(durabilidade);
		return this;
	}

	public ItemBuilder setName(String nome) {
		ItemMeta im = this.is.getItemMeta();
		im.setDisplayName(nome);
		this.is.setItemMeta(im);
		return this;
	}

	public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {
		this.is.addUnsafeEnchantment(ench, level);
		return this;
	}

	public ItemBuilder setSkullOwner(String dono) {
		try {
			SkullMeta im = (SkullMeta) this.is.getItemMeta();
			im.setOwner(dono);
			this.is.setItemMeta(im);
		} catch (ClassCastException localClassCastException) {
		}
		return this;
	}

	public ItemBuilder addEnchant(Enchantment ench, int level) {
		ItemMeta im = this.is.getItemMeta();
		im.addEnchant(ench, level, true);
		this.is.setItemMeta(im);
		return this;
	}

	public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
		this.is.addEnchantments(enchantments);
		return this;
	}

	public ItemBuilder setInfinityDurability() {
		this.is.setDurability((short) Short.MAX_VALUE);
		return this;
	}

	public ItemBuilder addItemFlag(ItemFlag flag) {
		ItemMeta im = this.is.getItemMeta();
		im.addItemFlags(new ItemFlag[] { flag });
		this.is.setItemMeta(im);
		return this;
	}

	public ItemBuilder setLore(String... lore) {
		ItemMeta im = this.is.getItemMeta();
		im.setLore(Arrays.asList(lore));
		this.is.setItemMeta(im);
		return this;
	}

	public ItemBuilder setLore(List<String> lore) {
		ItemMeta im = this.is.getItemMeta();
		im.setLore(lore);
		this.is.setItemMeta(im);
		return this;
	}

	public ItemBuilder removeLoreLine(String linha) {
		ItemMeta im = this.is.getItemMeta();
		List<String> lore = new ArrayList<String>(im.getLore());
		if (!lore.contains(linha)) {
			return this;
		}
		lore.remove(linha);
		im.setLore(lore);
		this.is.setItemMeta(im);
		return this;
	}

	public ItemBuilder removeLoreLine(int index) {
		ItemMeta im = this.is.getItemMeta();
		List<String> lore = new ArrayList<String>(im.getLore());
		if ((index < 0) || (index > lore.size())) {
			return this;
		}
		lore.remove(index);
		im.setLore(lore);
		this.is.setItemMeta(im);
		return this;
	}

	public ItemBuilder addLoreLine(String linha) {
		ItemMeta im = this.is.getItemMeta();
		List<String> lore = new ArrayList<String>();
		if (im.hasLore()) {
			lore = new ArrayList<String>(im.getLore());
		}
		lore.add(linha);
		im.setLore(lore);
		this.is.setItemMeta(im);
		return this;
	}

	public ItemBuilder addLoreLine(String linha, int pos) {
		ItemMeta im = this.is.getItemMeta();
		List<String> lore = new ArrayList<String>(im.getLore());
		lore.set(pos, linha);
		im.setLore(lore);
		this.is.setItemMeta(im);
		return this;
	}

	@SuppressWarnings("deprecation")
	public ItemBuilder setDyeColor(DyeColor cor) {
		this.is.setDurability(cor.getData());
		return this;
	}

	@Deprecated
	public ItemBuilder setWoolColor(DyeColor cor) {
		if (!this.is.getType().equals(Material.WOOL)) {
			return this;
		}
		this.is.setDurability(cor.getData());
		return this;
	}

	public ItemBuilder setLeatherArmorColor(Color cor) {
		try {
			LeatherArmorMeta im = (LeatherArmorMeta) this.is.getItemMeta();
			im.setColor(cor);
			this.is.setItemMeta(im);
		} catch (ClassCastException localClassCastException) {
		}
		return this;
	}

	public ItemStack toItemStack() {
		return this.is;
	}

	public ItemBuilder head(String texture) {
//		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
//		GameProfile profile = (texture, UUID.randomUUID());
//		ItemMeta headMeta = head.getItemMeta();
//	    Class<?> headMetaClass = headMeta.getClass();
//		RefSet(headMetaClass, headMeta, "profile", profile);
		return this;
	}

	public static boolean RefSet(Class<?> sourceClass, Object instance, String fieldName, Object value) {
		try {
			Field field = sourceClass.getDeclaredField(fieldName);
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			int modifiers = modifiersField.getModifiers();
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}

			if ((modifiers & 16) == 16) {
				modifiersField.setAccessible(true);
				modifiersField.setInt(field, modifiers & -17);
			}

			try {
				field.set(instance, value);
			} finally {
				if ((modifiers & 16) == 16) {
					modifiersField.setInt(field, modifiers | 16);
				}

				if (!field.isAccessible()) {
					field.setAccessible(false);
				}

			}

			return true;
		} catch (Exception var11) {
			Bukkit.getLogger().log(Level.WARNING, "Unable to inject Gameprofile", var11);
			return false;
		}
	}

}