package com.wandy.api.stack;

import java.util.List;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.metadata.FixedMetadataValue;

import com.wandy.api.Main;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

@SuppressWarnings("all")
public class Evento_Spawn implements Listener {
	@EventHandler
	public static void aoTomar(EntityDamageEvent e) {
		if (e.getEntity().getType().equals(EntityType.SLIME)
				&& e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
			e.setCancelled(true);
		}
	}

	public static void freezeNPC(Entity en) {
		net.minecraft.server.v1_8_R3.Entity nmsEn = ((CraftEntity) en).getHandle();
		NBTTagCompound compound = new NBTTagCompound();
		nmsEn.c(compound);
		compound.setByte("NoAI", (byte) 1);
		nmsEn.f(compound);
	}

	@EventHandler
	public void aoSpawnar(CreatureSpawnEvent event) {
		if (event.getEntity().hasMetadata("MORPH")) {
			return;
		}
		if (event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.BUILD_IRONGOLEM)
				|| event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.BUILD_SNOWMAN)
				|| event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.BUILD_SNOWMAN)) {
			event.setCancelled(true);
			return;
		}
		if (event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.NATURAL)) {
			event.setCancelled(true);
			return;
		}
		if (event.getEntity().getType().equals(EntityType.CAVE_SPIDER)
				|| event.getEntity().getType().equals(EntityType.ZOMBIE)) {
			event.setCancelled(true);
			return;
		}
		String cor = "§e";
		Entity entidade = (Entity) event.getEntity();
		if (event.getEntity().getType() == EntityType.ARMOR_STAND) {
			return;
		}
		if (!event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.SPAWNER)) {
			return;
		}
		if (event.getEntityType().equals(EntityType.SLIME)) {
			Slime sl = (Slime) event.getEntity();
			sl.setSize(1);
		}
		if (event.getEntityType().equals(EntityType.MAGMA_CUBE)) {
			MagmaCube sl2 = (MagmaCube) event.getEntity();
			sl2.setSize(1);
		}
		if (event.getEntity().getType().equals(EntityType.WITHER)) {
			freezeNPC((Entity) event.getEntity());
		}
		double raio = 10.0;
		List<Entity> entidades = (List<Entity>) entidade.getNearbyEntities(raio * 2.0, raio * 2.0, raio * 2.0);
		if (entidades.size() >= 1) {
			for (Entity ent : entidades) {
				if (ent.getType() == entidade.getType()) {
					if (!ent.hasMetadata("qnt")) {
						int entQuantidade = 2;
						ent.setMetadata("qnt",
								new FixedMetadataValue(Main.plugin, entQuantidade));
						event.setCancelled(true);
						return;
					}
					int entqnt = ent.getMetadata("qnt").get(0).asInt();
					if (entqnt >= 200) {
						event.setCancelled(true);
						return;
					}
					ent.setMetadata("qnt",
							new FixedMetadataValue(Main.plugin, (++entqnt)));
					ent.setCustomName(String.valueOf(cor) + entqnt + "x " + translateMob(ent.getType().getName()));
					event.setCancelled(true);
					return;
				}
			}
			entidade.setMetadata("qnt", new FixedMetadataValue(Main.plugin, 1));
		} else {
			event.setCancelled(false);
		}
	}

	public static String translateMob(String nome) {
		String ret = "";
		if (nome.equalsIgnoreCase("Spider")) {
			ret = "Aranha";
		}
		if (nome.equalsIgnoreCase("Blaze")) {
			ret = "Blaze";
		}
		if (nome.equalsIgnoreCase("CaveSpider")) {
			ret = "Aranha ven.";
		}
		if (nome.equalsIgnoreCase("PigZombie")) {
			ret = "Porco Zumbi";
		}
		if (nome.equalsIgnoreCase("VillagerGolem")) {
			ret = "Golem";
		}
		if (nome.equalsIgnoreCase("Pig")) {
			ret = "Porco";
		}
		if (nome.equalsIgnoreCase("Sheep")) {
			ret = "Ovelha";
		}
		if (nome.equalsIgnoreCase("Zombie")) {
			ret = "Zumbi";
		}
		if (nome.equalsIgnoreCase("Cow")) {
			ret = "Vaca";
		}
		if (nome.equalsIgnoreCase("Skeleton")) {
			ret = "Esqueleto";
		}
		if (nome.equalsIgnoreCase("Slime")) {
			ret = "Slime";
		}
		if (nome.equalsIgnoreCase("Mooshroom")) {
			ret = "Vaca Cogu.";
		}
		if (nome.equalsIgnoreCase("Enderman")) {
			ret = "Enderman";
		}
		if (nome.equalsIgnoreCase("Chicken")) {
			ret = "Galinha";
		}
		if (nome.equalsIgnoreCase("Creeper")) {
			ret = "Creeper";
		}
		if (nome.equalsIgnoreCase("WitherBoss")) {
			ret = "Wither";
		}
		if (nome.equalsIgnoreCase("Witch")) {
			ret = "Bruxa";
		}
		if (nome.equalsIgnoreCase("LavaSlime")) {
			ret = "Magma";
		}
		if (ret.equals("")) {
			ret = nome;
		}
		return ret;
	}
}
