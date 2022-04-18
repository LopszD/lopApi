package com.wandy.api.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Base64;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullManagerII
{
  public static ItemStack getSkullTwitter(String url)
  {
    ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
    if (url == null) {
      return item;
    }
    SkullMeta itemMeta = (SkullMeta)item.getItemMeta();
    itemMeta.setDisplayName("§bTwitter do servidor:");
    ArrayList<String> itemLore = new ArrayList<String>();
    itemLore.add("§7Clique para seguir nosso §bTwitter§7.");
    itemMeta.setLore(itemLore);
    
    GameProfile profile = new GameProfile(UUID.randomUUID(), null);
    byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", new Object[] { url }).getBytes());
    profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
    Field profileField = null;
    try
    {
      profileField = itemMeta.getClass().getDeclaredField("profile");
      profileField.setAccessible(true);
      profileField.set(itemMeta, profile);
    }
    catch (NoSuchFieldException|IllegalArgumentException|IllegalAccessException e)
    {
      e.printStackTrace();
    }
    item.setItemMeta(itemMeta);
    return item;
  }
  
  public static ItemStack getSkullDiscord(String url)
  {
    ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
    if (url == null) {
      return item;
    }
    SkullMeta itemMeta = (SkullMeta)item.getItemMeta();
    itemMeta.setDisplayName("§9Discord do servidor:");
    ArrayList<String> itemLore = new ArrayList<String>();
    itemLore.add("§7Clique para acessar nosso §9Discord§7.");
    itemMeta.setLore(itemLore);
    
    GameProfile profile = new GameProfile(UUID.randomUUID(), null);
    byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", new Object[] { url }).getBytes());
    profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
    Field profileField = null;
    try
    {
      profileField = itemMeta.getClass().getDeclaredField("profile");
      profileField.setAccessible(true);
      profileField.set(itemMeta, profile);
    }
    catch (NoSuchFieldException|IllegalArgumentException|IllegalAccessException e)
    {
      e.printStackTrace();
    }
    item.setItemMeta(itemMeta);
    return item;
  }
  
  public static ItemStack getSkullLoja(String url)
  {
    ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
    if (url == null) {
      return item;
    }
    SkullMeta itemMeta = (SkullMeta)item.getItemMeta();
    
    itemMeta.setDisplayName("§aLoja do servidor:");
    ArrayList<String> itemLore = new ArrayList<String>();
    itemLore.add("§7Clique para acessar nossa §aLoja§7.");
    itemLore.add("§7");
    itemLore.add("§aPromoções: §7promoção de abertura");
    itemLore.add("§7Cupom 'THORMS' para adquirir 50% de desconto.");
    itemMeta.setLore(itemLore);
    
    GameProfile profile = new GameProfile(UUID.randomUUID(), null);
    byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", new Object[] { url }).getBytes());
    profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
    Field profileField = null;
    try
    {
      profileField = itemMeta.getClass().getDeclaredField("profile");
      profileField.setAccessible(true);
      profileField.set(itemMeta, profile);
    }
    catch (NoSuchFieldException|IllegalArgumentException|IllegalAccessException e)
    {
      e.printStackTrace();
    }
    item.setItemMeta(itemMeta);
    return item;
  }
  
  public static ItemStack getSkullNovidades(String url)
  {
    ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
    if (url == null) {
      return item;
    }
    SkullMeta itemMeta = (SkullMeta)item.getItemMeta();
    
    itemMeta.setDisplayName("§6Ultimas novidades:");
    ArrayList<String> itemLore = new ArrayList<String>();
    itemLore.add("§6§l* §fServidor aberto.");
    itemLore.add("§6§l* §fSistema de ativarvip reformulado.");
    itemLore.add("§6§l* §fSistema de kits customizados.");
    itemLore.add("§6§l* §fSistema de stattrak arrumado.");
    itemLore.add("§6§l* §fSistema de Facção reformulado.");
    itemLore.add("§6§l* §fSistema de Buff adicionado.");
    itemLore.add("§6§l* §fSistema de Efeitos adicionado.");
    itemLore.add("§6§l* §fSistema de Mana adicionado.");    		
    itemMeta.setLore(itemLore);
    
    GameProfile profile = new GameProfile(UUID.randomUUID(), null);
    byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", new Object[] { url }).getBytes());
    profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
    Field profileField = null;
    try
    {
      profileField = itemMeta.getClass().getDeclaredField("profile");
      profileField.setAccessible(true);
      profileField.set(itemMeta, profile);
    }
    catch (NoSuchFieldException|IllegalArgumentException|IllegalAccessException e)
    {
      e.printStackTrace();
    }
    item.setItemMeta(itemMeta);
    return item;
  }
  
  public static ItemStack getSkullInfo(String url)
  {
    ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
    if (url == null) {
      return item;
    }
    SkullMeta itemMeta = (SkullMeta)item.getItemMeta();
    
    itemMeta.setDisplayName("§6Sobre o servidor:");
    ArrayList<String> itemLore = new ArrayList<String>();
    itemLore.add("§6Informações:");
    itemLore.add("");
    itemLore.add("§7RedeWandy é uma nova rede com conjunto de ");
    itemLore.add("§7servidores alocada na comunidade de minecraft.");
    itemLore.add("§7Servidor foi fundado por §fLeonardo Rabello §7-§fyLeooSz §7na data 27/07/2019");
    itemLore.add("§7e está online atualmente com §f" + Bukkit.getOnlinePlayers().size() + "/200 §7usuários");
    itemLore.add("§7entre estes alguns são jogadores VIP's que possuem certas vantagens");
    itemLore.add("§7e ajudam a manter o servidor online e também temos a questão dos afiliados");
    itemLore.add("§7o que são afiliados? Afiliados são aqueles Youtubers");
    itemLore.add("§7que divulgam o nosso servidor em seus canais trazendo");
    itemLore.add("§7assim mais jogadores para nossa rede.");
    itemLore.add("§7Temos também a questão da equipe que está totalmente");
    itemLore.add("§7dedicado á dar o melhor suporte possível ao jogador com total");
    itemLore.add("§7formalidade e correspondência adequada e educada.");
    itemLore.add("");
    itemLore.add("§7Copyright RedeWandy. Todos os direitos reservados.");
    itemMeta.setLore(itemLore);
    
    GameProfile profile = new GameProfile(UUID.randomUUID(), null);
    byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", new Object[] { url }).getBytes());
    profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
    Field profileField = null;
    try
    {
      profileField = itemMeta.getClass().getDeclaredField("profile");
      profileField.setAccessible(true);
      profileField.set(itemMeta, profile);
    }
    catch (NoSuchFieldException|IllegalArgumentException|IllegalAccessException e)
    {
      e.printStackTrace();
    }
    item.setItemMeta(itemMeta);
    return item;
  }
  
  public static ItemStack getSkullRegras(String url)
  {
    ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
    if (url == null) {
      return item;
    }
    SkullMeta itemMeta = (SkullMeta)item.getItemMeta();
    
    itemMeta.setDisplayName("§6Tabela de Punições:");
    ArrayList<String> itemLore = new ArrayList<String>();
    itemLore.add("§6Leia as regras e evite ao máximo respeita-las para que não");
    itemLore.add(" §6seja punido!");
    itemLore.add("");
    itemLore.add("§7§l* §fAbuso de bugs §8- §7Punição permanente");
    itemLore.add("§7§l* §fAntijogo §8- §7Punição por 1 dia");
    itemLore.add("§7§l* §fConta fake §8- §7Punição permanente");
    itemLore.add("§7§l* §fDivulgação grave §8- §7Punição permanente");
    itemLore.add("§7§l* §fDivulgação simples §8- §7Punição de 2 dias");
    itemLore.add("§7§l* §fDesavença no Bate Papo §8- §7Mute por 360 minutos");
    itemLore.add("§7§l* §fCliente Alternativo §8- §7Punição permanente");
    itemLore.add("§7§l* §fNickname inapropriado §8- §7Punição permanente");
    itemLore.add("§7§l* §fOfensa á jogador §8- §7Mute por 320 minutos");
    itemLore.add("§7§l* §fOfensa á staff/servidor §8- §7Punição permanente");
    itemLore.add("§7§l* §fAtitude de discriminação §8- §7Punição de 1 dia");
    itemLore.add("§7§l* §fAmeaça §8- §7Punição de 3 dias");
    itemLore.add("§7§l* §fDesinformação §8- §7Punição de 2 dias");
    itemLore.add("");
    itemLore.add("§7- §cTodas punições acima são aplicadas em mute ou até");
    itemLore.add("§cbanimentos no tempo especificado.");
    itemMeta.setLore(itemLore);
    
    GameProfile profile = new GameProfile(UUID.randomUUID(), null);
    byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", new Object[] { url }).getBytes());
    profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
    Field profileField = null;
    try
    {
      profileField = itemMeta.getClass().getDeclaredField("profile");
      profileField.setAccessible(true);
      profileField.set(itemMeta, profile);
    }
    catch (NoSuchFieldException|IllegalArgumentException|IllegalAccessException e)
    {
      e.printStackTrace();
    }
    item.setItemMeta(itemMeta);
    return item;
  }
}
