package com.wandy.api.utils.ftop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;

public class TOPAPI
{
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static List<FTOP> top = new ArrayList();
  public static HashMap<Block, Faction> mobs = new HashMap<Block, Faction>();
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static List<FTOP> getTop()
  {
    List<FTOP> out = top;
    
    Collections.sort(out, new Comparator()
    {
      public int compare(Object o1, Object o2)
      {
        FTOP p1 = (FTOP)o1;
        FTOP p2 = (FTOP)o2;
        return p1.getMoney() > p2.getMoney() ? -1 : p1.getMoney() < p2.getMoney() ? 1 : 0;
      }
    });
    List<FTOP> out1 = new ArrayList();
    
    int i = 0;
    for (FTOP lin : out)
    {
      i++;
      if (i <= 10) {
        out1.add(lin);
      }
    }
    return out1;
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static void updateTop()
  {
    List<FTOP> out = criarTop();
    
    Collections.sort(out, new Comparator()
    {
      public int compare(Object o1, Object o2)
      {
        FTOP p1 = (FTOP)o1;
        FTOP p2 = (FTOP)o2;
        return p1.getMoney() > p2.getMoney() ? -1 : p1.getMoney() < p2.getMoney() ? 1 : 0;
      }
    });
    top = out;
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
public static List<FTOP> criarTop()
  {
    HashMap<Faction, String> alow = new HashMap<Faction, String>();
    double iss;
    int ixo;
    for (Block b : mobs.keySet()) {
      if (b.getType().equals(Material.MOB_SPAWNER))
      {
        iss = 0.0D;
        String irs = "S";
        ixo = 0;
        if (alow.containsKey(mobs.get(b)))
        {
          iss = Double.valueOf(((String)alow.get(mobs.get(b))).split("@")[0]).doubleValue() + getValor(getMobOf(b));
          
          ixo++;
          
          alow.replace((Faction)mobs.get(b), iss + "@" + ixo);
          
          irs = "N";
        }
        if (irs.equals("S"))
        {
          ixo++;
          
          alow.put((Faction)mobs.get(b), getValor(getMobOf(b)) + "@" + ixo);
        }
      }
    }
    List<FTOP> top = new ArrayList();
    for (Faction f : alow.keySet())
    {
      int isis = 0;
      for (Block b : mobs.keySet()) {
        if (b.getType().equals(Material.MOB_SPAWNER)) {
          if (((Faction)mobs.get(b)).getName().equals(f.getName())) {
            isis++;
          }
        }
      }
      FTOP ft = new FTOP(f);
      ft.setMoney(Double.valueOf(((String)alow.get(f)).split("@")[0]).doubleValue());
      ft.setTotal(isis);
      
      top.add(ft);
    }
    return top;
  }
  
  public static String translate(EntityType e)
  {
    String d = "";
    if (e.equals(EntityType.COW)) {
      d = "Vaca";
    }
    if (e.equals(EntityType.SPIDER)) {
      d = "Aranha";
    }
    if (e.equals(EntityType.CAVE_SPIDER)) {
      d = "Aranha da caverna";
    }
    if (e.equals(EntityType.SKELETON)) {
      d = "Esqueleto";
    }
    if (e.equals(EntityType.ZOMBIE)) {
      d = "Zumbi";
    }
    if (e.equals(EntityType.SLIME)) {
      d = "Slime";
    }
    if (e.equals(EntityType.PIG_ZOMBIE)) {
      d = "Porco zumbi";
    }
    if (e.equals(EntityType.BLAZE)) {
      d = "Blaze";
    }
    if (e.equals(EntityType.IRON_GOLEM)) {
      d = "Golem";
    }
    return d;
  }
  
  public static int getSpawners(Faction f, EntityType e)
  {
    int d = 0;
    if (e.equals(EntityType.COW)) {
      for (Block b : mobs.keySet()) {
        if (((Faction)mobs.get(b)).getName().equals(f.getName())) {
          if (getMobOf(b).equals(e)) {
            d++;
          }
        }
      }
    }
    if (e.equals(EntityType.SPIDER)) {
      for (Block b : mobs.keySet()) {
        if (((Faction)mobs.get(b)).getName().equals(f.getName())) {
          if (getMobOf(b).equals(e)) {
            d++;
          }
        }
      }
    }
    if (e.equals(EntityType.CAVE_SPIDER)) {
      for (Block b : mobs.keySet()) {
        if (((Faction)mobs.get(b)).getName().equals(f.getName())) {
          if (getMobOf(b).equals(e)) {
            d++;
          }
        }
      }
    }
    if (e.equals(EntityType.SKELETON)) {
      for (Block b : mobs.keySet()) {
        if (((Faction)mobs.get(b)).getName().equals(f.getName())) {
          if (getMobOf(b).equals(e)) {
            d++;
          }
        }
      }
    }
    if (e.equals(EntityType.ZOMBIE)) {
      for (Block b : mobs.keySet()) {
        if (((Faction)mobs.get(b)).getName().equals(f.getName())) {
          if (getMobOf(b).equals(e)) {
            d++;
          }
        }
      }
    }
    if (e.equals(EntityType.SLIME)) {
      for (Block b : mobs.keySet()) {
        if (((Faction)mobs.get(b)).getName().equals(f.getName())) {
          if (getMobOf(b).equals(e)) {
            d++;
          }
        }
      }
    }
    if (e.equals(EntityType.PIG_ZOMBIE)) {
      for (Block b : mobs.keySet()) {
        if (((Faction)mobs.get(b)).getName().equals(f.getName())) {
          if (getMobOf(b).equals(e)) {
            d++;
          }
        }
      }
    }
    if (e.equals(EntityType.BLAZE)) {
      for (Block b : mobs.keySet()) {
        if (((Faction)mobs.get(b)).getName().equals(f.getName())) {
          if (getMobOf(b).equals(e)) {
            d++;
          }
        }
      }
    }
    if (e.equals(EntityType.IRON_GOLEM)) {
      for (Block b : mobs.keySet()) {
        if (((Faction)mobs.get(b)).getName().equals(f.getName())) {
          if (getMobOf(b).equals(e)) {
            d++;
          }
        }
      }
    }
    return d;
  }
  
  public static double getValor(EntityType e)
  {
    double d = 0.0D;
    if (e.equals(EntityType.COW)) {
      d = 50000.0D;
    }
    if (e.equals(EntityType.SPIDER)) {
      d = 100000.0D;
    }
    if (e.equals(EntityType.CAVE_SPIDER)) {
      d = 100000.0D;
    }
    if (e.equals(EntityType.SKELETON)) {
      d = 100000.0D;
    }
    if (e.equals(EntityType.ZOMBIE)) {
      d = 100000.0D;
    }
    if (e.equals(EntityType.SLIME)) {
      d = 100000.0D;
    }
    if (e.equals(EntityType.PIG_ZOMBIE)) {
      d = 500000.0D;
    }
    if (e.equals(EntityType.BLAZE)) {
      d = 500000.0D;
    }
    if (e.equals(EntityType.IRON_GOLEM)) {
      d = 2000000.0D;
    }
    return d;
  }
  
  public static void addSpawner(Faction f, Block b)
  {
    if (b.getType().equals(Material.MOB_SPAWNER))
    {
      if (mobs.containsKey(b))
      {
        if (!((Faction)mobs.get(b)).getName().equals(f.getName())) {
          mobs.replace(b, f);
        }
        return;
      }
      mobs.put(b, f);
    }
  }
  
  public static ArrayList<String> getTeste()
  {
    ArrayList<String> aa = new ArrayList<String>();
    for (Block bs : mobs.keySet())
    {
      String s = deserealize(bs);
      Block b = seralize(s);
      
      aa.add("§a" + ((Faction)mobs.get(b)).getName() + " -> " + getMobOf(b));
    }
    return aa;
  }
  
  public static String deserealize(Block b)
  {
    String e = b.getLocation().getWorld().getName() + "@" + b.getLocation().getBlockX() + 
      "@" + b.getLocation().getBlockY() + 
      "@" + b.getLocation().getBlockZ();
    
    return e;
  }
  
  public static Block seralize(String s)
  {
    World w = Bukkit.getWorld(s.split("@")[0]);
    int x = Integer.valueOf(s.split("@")[1]).intValue();
    int y = Integer.valueOf(s.split("@")[2]).intValue();
    int z = Integer.valueOf(s.split("@")[3]).intValue();
    
    Block b = w.getBlockAt(x, y, z);
    
    return b;
  }
  
  public static Faction getFactionOf(Block b)
  {
    Faction f = getFaction(b.getLocation());
    return f;
  }
  
  public static EntityType getMobOf(Block b)
  {
    if (b.getType().equals(Material.MOB_SPAWNER))
    {
      BlockState blockstate = b.getState();
      
      CreatureSpawner spawner = (CreatureSpawner)blockstate;
      
      EntityType en = spawner.getSpawnedType();
      
      return en;
    }
    return null;
  }
  
  public static void removerSpawner(Faction f, Block b)
  {
    if (mobs.containsKey(b)) {
      if (((Faction)mobs.get(b)).getName().equals(f.getName())) {
        mobs.remove(b);
      }
    }
  }
  
  public static int getSpawnersFac(Faction f)
  {
    int i = 0;
    for (Block b : mobs.keySet()) {
      if (((Faction)mobs.get(b)).getName().equals(f.getName())) {
        i++;
      }
    }
    return i;
  }
  
  public static void carregarDados() {
      TOPAPI.mobs.clear();
      for (final Faction f : FactionColl.get().getAll()) {
          if (!f.isNone() && !f.getName().equals("Zona Segura") && !f.getName().equals("Zona de Guerra")) {
              for (final PS ps : BoardColl.get().getChunks(f)) {
                  final Chunk c = ps.asBukkitChunk();
                  if (!c.isLoaded()) {
                      c.load();
                  }
                  BlockState[] tileEntities;
                  for (int length = (tileEntities = c.getTileEntities()).length, i = 0; i < length; ++i) {
                      final BlockState bs = tileEntities[i];
                      if (bs.getBlock().getType().equals((Object)Material.MOB_SPAWNER)) {
                          TOPAPI.mobs.put(bs.getBlock(), f);
                      }
                  }
              }
          }
      }
      Bukkit.getConsoleSender().sendMessage("TOP GERADORES CARREGADO");
  }
  
  public static Faction getFaction(Location loc)
  {
    return BoardColl.get().getFactionAt(PS.valueOf(loc));
  }
  
  public static Faction getFaction(Player p)
  {
    MPlayer mp = MPlayer.get(p);
    
    return mp.getFaction();
  }
}