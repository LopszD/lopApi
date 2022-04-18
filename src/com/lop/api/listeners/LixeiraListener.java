package com.wandy.api.listeners;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LixeiraListener implements Listener {
	
  public static void abrirMenu(Player p) {
    Inventory inv = Bukkit.createInventory(null, 27, "§8Lixeira");
    
    ItemStack a = new ItemStack(Material.CAULDRON_ITEM);
    ItemMeta am = a.getItemMeta();
    am.setDisplayName("§bAbrir lixeira");
    am.setLore(Arrays.asList(new String[] { "§7Tudo o que você colocar na lixeira", "§7será perdido ao fechá-la." }));
    a.setItemMeta(am);
    inv.setItem(12, a);
    
    ItemStack a1 = new ItemStack(Material.BARRIER);
    ItemMeta am1 = a1.getItemMeta();
    am1.setDisplayName("§cLimpar inventário");
    am1.setLore(Arrays.asList(new String[] { "§7Todo o seu inventário será limpo", "§7e todos os itens serão perdidos." }));
    a1.setItemMeta(am1);
    inv.setItem(14, a1);
    																								
    p.openInventory(inv);
  }	
  
  public static void abrirConfi(Player p)
  {
    Inventory inv = Bukkit.createInventory(null, 27, "§8§9§8§a§2§d§2§3§8Confirmação");
    
    ItemStack a = new ItemStack(Material.WOOL, 1, (short)5);
    ItemMeta am = a.getItemMeta();
    am.setDisplayName("§aAceitar (Leia abaixo)");
    am.setLore(Arrays.asList(new String[] { "§7Todo o seu inventário será limpo", "§7e todos os itens serão perdidos." }));
    a.setItemMeta(am);
    inv.setItem(11, a);
    
    ItemStack a1 = new ItemStack(Material.WOOL, 1, (short)14);
    ItemMeta am1 = a1.getItemMeta();
    am1.setDisplayName("§cNegar");
    am1.setLore(Arrays.asList(new String[] { "§7Cancelar está operação." }));
    a1.setItemMeta(am1);
    inv.setItem(15, a1);
    
    p.openInventory(inv);
  }
  
  public static void abrirLixe(Player p)
  {
    Inventory inv = Bukkit.createInventory(null, 54, "§8Coloque o lixo");
    p.openInventory(inv);
  }
  
  @EventHandler
  public static void aoClicar(InventoryClickEvent e)
  {
    if (e.getInventory().getTitle().equals("§8Lixeira")) {
      if (e.getCurrentItem() != null) {
        if (!e.getCurrentItem().getType().equals(Material.AIR))
        {
          e.setCancelled(true);
          
          Player p = (Player)e.getWhoClicked();
          if (e.getSlot() == 12) {
            abrirLixe(p);
          }
          if (e.getSlot() == 14) {
            abrirConfi(p);
          }
        }
      }
    }
    if (e.getInventory().getTitle().equals("§8§9§8§a§2§d§2§3§8Confirmação")) {
      if (e.getCurrentItem() != null) {
        if (!e.getCurrentItem().getType().equals(Material.AIR))
        {
          e.setCancelled(true);
          Player p = (Player)e.getWhoClicked();
          if (e.getSlot() == 11)
          {
            p.closeInventory();
            p.getInventory().clear();
            p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 1.0F);
            p.sendMessage("§aSeu inventário foi limpo.");
          }
          if (e.getSlot() == 15) {
            p.closeInventory();
          }
        }
      }
    }
  }
}
