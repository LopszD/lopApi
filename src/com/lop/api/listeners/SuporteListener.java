package com.wandy.api.listeners;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SuporteListener
  implements Listener
{
  @EventHandler
  public void click(InventoryClickEvent e)
  {
    Player p = (Player)e.getWhoClicked();
    if (e.getInventory().getTitle().equalsIgnoreCase("Suporte"))
    {
      e.setCancelled(true);
      if (e.getCurrentItem() == null) {
        return;
      }
      if ((e.getCurrentItem().getType() == Material.SKULL_ITEM) && (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bTwitter do servidor:")))
      {
          p.closeInventory();
          p.sendMessage("");
          p.spigot().sendMessage(new ComponentBuilder("§bAcesse o nosso Twitter. Clicando ").append("§b§lAQUI").event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://twitter.com/ServidoresWandy")).event(
            new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§bTwitter do servidor").create())).append(
            "§a.").create());
          p.sendMessage("");
        
        return;
      }
    }
  }
  
  @EventHandler
  public void click2(InventoryClickEvent e)
  {
    Player p = (Player)e.getWhoClicked();
    if (e.getInventory().getTitle().equalsIgnoreCase("Suporte"))
    {
      e.setCancelled(true);
      if (e.getCurrentItem() == null) {
        return;
      }
      if ((e.getCurrentItem().getType() == Material.SKULL_ITEM) && (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9Discord do servidor:")))
      {
        p.closeInventory();
        p.sendMessage("");
        p.spigot().sendMessage(new ComponentBuilder("§9Acesse o nosso Discord. Clicando ").append("§9§lAQUI").event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/8DWWYSw")).event(
          new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§eDiscord do servidor").create())).append(
          "§9.").create());
        p.sendMessage("");
        
        return;
      }
    }
  }
  
  @EventHandler
  public void click3(InventoryClickEvent e)
  {
    Player p = (Player)e.getWhoClicked();
    if (e.getInventory().getTitle().equalsIgnoreCase("Suporte"))
    {
      e.setCancelled(true);
      if (e.getCurrentItem() == null) {
        return;
      }
      if ((e.getCurrentItem().getType() == Material.SKULL_ITEM) && (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aLoja do servidor:")))
      {
        p.closeInventory();
        p.sendMessage("");
        p.spigot().sendMessage(new ComponentBuilder("§aAdquira §lVIP §ae §lCASH §aem nossa loja.").create());
        p.spigot().sendMessage(new ComponentBuilder("§aClique ").append("§f§lAQUI ").event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.redewandy.com/loja")).event(
          new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aLoja do servidor").create())).append(
          "§apara acessar nossa loja.").create());
        p.sendMessage("");
        
        return;
      }
    }
  }
}
