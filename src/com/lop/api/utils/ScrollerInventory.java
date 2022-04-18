package com.wandy.api.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ScrollerInventory{

    public ArrayList<Inventory> pages = new ArrayList<Inventory>();
    public UUID id;
    public int currpage = 0;
    public int atual = 1;
    public static HashMap<UUID, ScrollerInventory> users = new HashMap<UUID, ScrollerInventory>();

    public ScrollerInventory(ArrayList<ItemStack> items, String name, Player p){
        this.id = UUID.randomUUID();
        Inventory page = getBlankPage(name);
        atual++;
    
		int slot = 0;
        int a = -1;
        for(int b = 0;b < 1000; b++){
        	if (a +1  == items.size()) {
        		break;
        	}
        	if(slot == 35){
                pages.add(page);
                page = getBlankPage(name);
                atual++;
                slot = 0;
                int i = slot;
                if (i < 9 || i == 17 || i == 26 || i == 35 || i == 44 || i == 53 || i % 9 == 0) continue;
                a++;
				page.setItem(slot,items.get(a));
            }else{
            	slot++;
            	int i = slot;
				if (i < 9 || i == 17 || i == 26 || i == 35 || i == 44 || i == 53 || i % 9 == 0) continue;
				a++;
				page.setItem(slot,items.get(a));
            }
        }
        pages.add(page);
        p.openInventory(pages.get(currpage));
        users.put(p.getUniqueId(), this);
    }

    private Inventory getBlankPage(String name){
        Inventory page = Bukkit.createInventory(null, 54, name);

        ItemStack nextpage =  new ItemStack(Material.ARROW, 1);
        ItemMeta meta = nextpage.getItemMeta();
        meta.setDisplayName("§aPagina "+(atual + 1));
        nextpage.setItemMeta(meta);

        ItemStack prevpage = new ItemStack(Material.ARROW, 1);
        meta = prevpage.getItemMeta();
        meta.setDisplayName("§aPagina "+(atual - 1));
        prevpage.setItemMeta(meta);
        
        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta meta2 = back.getItemMeta();
        meta2.setDisplayName("§aVoltar");
        back.setItemMeta(meta2);
        
        page.setItem(49, back);

       	page.setItem(26, nextpage);
        if (atual != 1)
       	page.setItem(18, prevpage);
        return page;
    }
}