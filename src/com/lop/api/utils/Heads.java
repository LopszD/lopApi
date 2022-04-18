package com.wandy.api.utils;

import java.lang.reflect.Field;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class Heads {
	
	public static ItemStack VERDE;
	public static ItemStack CINZA;
	public static ItemStack AMARELO;
	public static ItemStack ROXO;
	public static ItemStack LARANJA;
	public static ItemStack AZURE;
	public static ItemStack BRANCO;
	public static ItemStack VERMELHO;
	public static ItemStack BATALHAR;
	public static ItemStack CIMA;
	public static ItemStack BAIXO;
	public static ItemStack MAGENTA;
	public static ItemStack AZUL;
	public static ItemStack NOME;
	public static ItemStack CIANO_CLARO;
	public static ItemStack CIANO_ESCURO;
	public static ItemStack LOVE1;
	public static ItemStack CORE;
	public static ItemStack ICON;
	public static ItemStack N1;
	public static ItemStack N5;
	public static ItemStack N0;
	public static ItemStack N8;
	public static ItemStack N2;
	public static ItemStack C4;
	public static ItemStack CANHAO;
	public static ItemStack MAGNETICO;
	public static ItemStack RASTREADOR;
	
	static {
		N1 = getSkull("http://textures.minecraft.net/texture/78a42df06fc916de110f61bd76eddbf58ed4249fce5ee51c219ec75a37b414");
		N5 = getSkull("http://textures.minecraft.net/texture/49357cb4664426a9ae0f9c7725ea4351dc69f15a8062c03591e26ac11bbc5a");
		N0 = getSkull("http://textures.minecraft.net/texture/dcdf22e25ab547b376f083cbf8462c4268cef5555a9689b0e7d2dffe8672b2");
		N8 = getSkull("http://textures.minecraft.net/texture/a112a392ef19ee393a14505bbdad9c1e9293a04ec3ab337e374880222e708244");
		N2 = getSkull("http://textures.minecraft.net/texture/1ef134f0efa88351b837f7c087afe1b3fb36435ab7d746fa37c0ef155e4f29");
		ICON = getSkull("http://textures.minecraft.net/texture/72103527faa45aded5a3a1682900da54601d30eb1257a8f48619322fe5721d80");
		CORE = getSkull("http://textures.minecraft.net/texture/d45f4d139c9e89262ec06b27aaad73fa488ab49290d2ccd685a2554725373c9b");
		LOVE1 = getSkull("http://textures.minecraft.net/texture/2869bdd9a8f77eeff75d8f67ed0322bd9c16dd494972314ed707dd10a3139a58");
		VERDE = getSkull("http://textures.minecraft.net/texture/361e5b333c2a3868bb6a58b6674a2639323815738e77e053977419af3f77");
		CINZA = getSkull("http://textures.minecraft.net/texture/f2f085c6b3cb228e5ba81df562c4786762f3c257127e9725c77b7fd301d37");
		VERMELHO = getSkull("http://textures.minecraft.net/texture/61856c7b378d350262143843d1f9fbb21911a71983ba7b39a4d4ba5b66bedc6");
		AZURE = getSkull("http://textures.minecraft.net/texture/bfaf7aab1e177ad38e51bfc19ab662149c31953a569a40caa81f7a4932069");
		MAGENTA = new ItemBuilder(Material.SKULL_ITEM).durability(3).owner("diablo3pk").build();
		BRANCO = new ItemBuilder(Material.SKULL_ITEM).durability(3).owner("cy1337").build();
		NOME = new ItemBuilder(Material.SKULL_ITEM).durability(3).owner("yLeooSz").build();
		LARANJA = getSkull("http://textures.minecraft.net/texture/e79add3e5936a382a8f7fdc37fd6fa96653d5104ebcadb0d4f7e9d4a6efc454");
		AMARELO = getSkull("http://textures.minecraft.net/texture/c641682f43606c5c9ad26bc7ea8a30ee47547c9dfd3c6cda49e1c1a2816cf0ba");
		ROXO = getSkull("http://textures.minecraft.net/texture/f0c05d560d8e13f0cb25c15c831bc595450c5e54ce35fa54e17e0495267c");
		CINZA = getSkull("http://textures.minecraft.net/texture/f2f085c6b3cb228e5ba81df562c4786762f3c257127e9725c77b7fd301d37");
		AZUL = getSkull("http://textures.minecraft.net/texture/f477f44389362c4c764c847a9739bc78c32467eab4e3830ae4c8beac3442ef9");
		CIMA = getSkull("http://textures.minecraft.net/texture/3f46abad924b22372bc966a6d517d2f1b8b57fdd262b4e04f48352e683fff92");
		BAIXO = getSkull("http://textures.minecraft.net/texture/be9ae7a4be65fcbaee65181389a2f7d47e2e326db59ea3eb789a92c85ea46");
		CIANO_CLARO = getSkull("http://textures.minecraft.net/texture/a4413fe767f282780cdec4903b5abd9e91ca596f3286c3df9d46e5647dc");
		CIANO_ESCURO = getSkull("http://textures.minecraft.net/texture/95b9a48467f0212aa68864e6342116f8f79a275454bf215f67f701a6f2c818");
		C4 = getSkull("http://textures.minecraft.net/texture/7faf3efbff6d7ef465ecacbc517f4dad5cc1a2261ea7a609f216aae48784");
		CANHAO = getSkull("http://textures.minecraft.net/texture/c4d7fc8e3a959ade7d9cf663f1e82db7975543e288ab8d11eb2541888213526");
		MAGNETICO = getSkull("http://textures.minecraft.net/texture/8942827d434faa81e84fd06882f9a1553cb7c20a113d5734f37ef480e0c2a59a");
		RASTREADOR = getSkull("http://textures.minecraft.net/texture/10e10af511457df93d48302ca6916738e9618bef7c15eff324a942f1ade9bffd");
	}
	
	public static ItemStack getSkull(String url) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        if (url == null || url.isEmpty())
            return skull;
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(skullMeta);
        return skull;
    }
	
	public static ItemBuilder getMobHead(String entity) {
		EntityType en = EntityType.valueOf(entity);
		return new ItemBuilder(Material.SKULL_ITEM).name("§e" + traduzir(en)).durability(3).owner(head(en));
	}

	@SuppressWarnings("incomplete-switch")
	public static String traduzir(EntityType t) {
		if (t == null) return "";
		switch (t) {
		case BAT:
			return "Morcego";
		case BLAZE:
			return "Blaze";
		case CAVE_SPIDER:
			return "Aranha das cavernas";
		case CHICKEN:
			return "Galinha";
		case COW:
			return "Vaca";
		case CREEPER:
			return "Creeper";
		case GHAST:
			return "Ghast";
		case GUARDIAN:
			return "Guardian";
		case IRON_GOLEM:
			return "Iron Golem";
		case MAGMA_CUBE:
			return "Magma Cube";
		case OCELOT:
			return "Jaguatirica";
		case PIG:
			return "Porco";
		case PIG_ZOMBIE:
			return "Porco Zumbi";
		case RABBIT:
			return "Coelho";
		case SHEEP:
			return "Ovelha";
		case SILVERFISH:
			return "Silverfish";
		case SKELETON:
			return "Esqueleto";
		case SLIME:
			return "Slime";
		case SPIDER:
			return "Aranha";
		case SQUID:
			return "Lula";
		case WITCH:
			return "Bruxa";
		case WITHER:
			return "Wither";
		case WOLF:
			return "Lobo";
		case ZOMBIE:
			return "Zumbi";
		case ENDERMAN:
			return "Enderman";
		}
		return t.toString();
	}
	
	@SuppressWarnings("incomplete-switch")
	private static String head(EntityType t) {
		if (t == null) return "";
		switch (t) {
		case BLAZE:
			return "MHF_BLAZE";
		case CAVE_SPIDER:
			return "MHF_CAVESPIDER";
		case CHICKEN:
			return "MHF_CHICKEN";
		case COW:
			return "MHF_COW";
		case CREEPER: 		//
			return "MHF_CREEPER"; //
		case GHAST:
			return "MHF_GHAST";
		case IRON_GOLEM:
			return "MHF_GOLEM";
		case MAGMA_CUBE:
			return "MHF_LavaSlime";
		case OCELOT:
			return "MHF_OCELOT";
		case PIG:
			return "MHF_PIG";
		case PIG_ZOMBIE:
			return "MHF_PIGZOMBIE";
		case SHEEP:
			return "MHF_SHEEP";
		case SKELETON: 		// 
			return "MHF_SKELETON"; //
		case SLIME:
			return "MHF_SLIME";
		case SPIDER:
			return "MHF_SPIDER";
		case SQUID:
			return "MHF_SQUID";
		case WITHER:
			return "MHF_WITHER";
		case ZOMBIE: 	///
			return "MHF_ZOMBIE"; //
		case ENDERMAN:
			return "MHF_ENDERMAN";
		case WOLF:
			return "MHF_WOLF";
		case GUARDIAN:
			return "MHF_GUARDIAN";
		}
		return "MHF_"+t.toString().toUpperCase();
	}
}
