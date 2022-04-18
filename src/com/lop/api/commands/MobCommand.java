package com.wandy.api.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import com.wandy.api.listeners.EntrarListener;
import com.wandy.api.utils.ItemBuilder;
import com.wandy.api.utils.ScrollerInventory;

public class MobCommand implements CommandExecutor {

	public static HashMap<Chunk, HashMap<EntityType, Location>> trapped;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("mob")){
			Player p = (Player) sender;
			MPlayer mp = MPlayer.get(p);
			if (!(sender instanceof Player)){
				sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
				return true;
			}
		    if (!mp.hasFaction()){
		        p.sendMessage("§cVocê não tem uma facção.");
		        return true;
		    }
            if (!BoardColl.get().getFactionAt(PS.valueOf(p.getLocation())).equals(mp.getFaction())) {
                p.sendMessage("§cVocê precisa estar dentro de seu território para setar os spawns dos mobspawner.");
                return true;
            } else {
            if (sender.hasPermission("wandy.mob")){
					if (args.length == 0 || args.length > 1){
						ArrayList<ItemStack> items = new ArrayList<>();
						List<EntityType> entities = getEntities(p);
						if (!entities.isEmpty()){
							for (EntityType ent : entities){
								items.add(new ItemBuilder(Material.SKULL_ITEM)
										.durability(3)
										.owner(head(ent))
										.name("§a" + ent.toString())
										.lore("§7Defina a localização perto dos", "§7mobspawner de " + ent.toString() + ".", "", "§aClique para definir localização!")
										.build());
							}
							ScrollerInventory scroll = new ScrollerInventory(items, "§8" + mp.getFaction().getTag() + " - Pontos de spawn", p);
							ScrollerInventory.users.put(p.getUniqueId(), scroll);
						} else {
							EntrarListener.mandarAction(p, "§cNão foi encontrado mobspawner nesta chunk.");
							return true;
						}
						return true;
					} else if (args.length == 1){
						if (args[0].equals("limpar")){
							if (trapped.containsKey(p.getLocation().getChunk())){
								trapped.remove(p.getLocation().getChunk());
								p.sendMessage("§aOs mobs que estavam presos nesta chunk foram liberados.");
								return true;
							} else {
								p.sendMessage("§cNão haviam mobs presos nesta chunk.");
								return true;
							}
						} else {
							try {
								if (EntityType.valueOf(translate(args[0].toUpperCase()).toString()) != null) {
									EntityType ent = translate(args[0].toUpperCase());
									if (containsEntity(p, ent)){
										HashMap<EntityType, Location> toput;
										if (trapped.containsKey(p.getLocation().getChunk())){
											toput = trapped.get(p.getLocation().getChunk());
										} else {
											toput = new HashMap<>();
										}
										toput.put(ent, p.getLocation());
										trapped.put(p.getLocation().getChunk(), toput);
							            EntrarListener.mandarAction(p, "§aPonto de SPAWN de " + ent.toString() + " definido com sucesso.");
							            p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0f, 1.0f);
										return true;
									} else {
										sender.sendMessage("§cNão haviam mobspawner do tipo de mob escolhido.");
										return true;
									}
								}
							} catch (Exception e){
								sender.sendMessage("§cEste não é um mob válido.");
								return true;
							}
						}
					}
				} else {
					sender.sendMessage("§cVocê não tem permissão para executar este comando.");
					return true;
			  }
		   }
		}
		return false;
	}
	
	private EntityType translate(String str){
		EntityType e = null;
		switch (str.toUpperCase()){
		case "VACA":
		case "COW":
			e = EntityType.COW;
			break;
		case "ZUMBI":
		case "ZOMBIE":
			e = EntityType.ZOMBIE;
			break;
		case "ESQUELETO":
		case "SKELETON":
			e = EntityType.SKELETON;
			break;
		case "SLIME":
			e = EntityType.SLIME;
			break;
		case "WITHER":
			e = EntityType.WITHER;
			break;
		case "BLAZER":
		case "BLAZE":
			e = EntityType.BLAZE;
			break;
		case "MAGMA":
		case "MAGMA_CUBE":
		case "CUBE_MAGMA":
		case "LAVASLIME":
		case "LAVA_SLIME":
			e = EntityType.MAGMA_CUBE;
			break;
		case "PORCO_ZUMBI":
		case "PORCOZUMBI":
		case "PIGMAN":
		case "PIG_ZOMBIE":
		case "ZOMBIE_PIGMAN":
		case "ZOMBIE_PIG":
			e = EntityType.PIG_ZOMBIE;
			break;
		case "SPIDER":
		case "ARANHA":
			e = EntityType.SPIDER;
			break;
		case "GOLEM":
		case "IRON_GOLEM":
		case "IRONGOELM":
		case "VILLAGERGOLEM":
		case "VILLAGER_GOLEM":
			e = EntityType.IRON_GOLEM;
			break;
		}
		return e;
	}
	

	public String head(EntityType t) {
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
		default:
			return "MHF_"+t.toString().toUpperCase();
		}
	}
	
	public static boolean containsEntity(Player p, EntityType e) {
		boolean ret = false;
		Chunk c = p.getLocation().getChunk();
    	int cx = c.getX() << 4;
    	int cz = c.getZ() << 4;
    	for (int x = cx; x < cx + 16; x++) {
    		for (int z = cz; z < cz + 16; z++) {
    			for (int y = 0; y < c.getWorld().getMaxHeight(); y++) {
    				if (c.getWorld().getBlockAt(x, y, z).getType() == Material.MOB_SPAWNER) {
    					CreatureSpawner spawner = (CreatureSpawner) c.getWorld().getBlockAt(x, y, z).getState();
    					if (spawner.getSpawnedType().equals(e)){
    						ret = true;
    					}
    				}
    			}
    		}
	    }
		return ret;
	}
	

	public static List<EntityType> getEntities(Player p) {
		Chunk c = p.getLocation().getChunk();
    	int cx = c.getX() << 4;
    	int cz = c.getZ() << 4;
    	List<EntityType> ret = new ArrayList<>();
    	for (int x = cx; x < cx + 16; x++) {
    		for (int z = cz; z < cz + 16; z++) {
    			for (int y = 0; y < c.getWorld().getMaxHeight(); y++) {
    				if (c.getWorld().getBlockAt(x, y, z).getType() == Material.MOB_SPAWNER) {
    					CreatureSpawner spawner = (CreatureSpawner) c.getWorld().getBlockAt(x, y, z).getState();
    					if (ret.contains(spawner.getSpawnedType())) break;
    					ret.add(spawner.getSpawnedType());
    				}
    			}
    		}
	    }
		return ret;
	}
}
