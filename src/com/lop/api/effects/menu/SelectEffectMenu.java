package com.wandy.api.effects.menu;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.wandy.api.Main;
import com.wandy.api.effects.EffectType;
import com.wandy.api.effects.manager.EffectManager;
import com.wandy.api.mana.ManaModel;
import com.wandy.api.utils.ItemBuilder;

public class SelectEffectMenu {

	public static void open(Player p){
		Inventory inv = Bukkit.createInventory(p, 3*9, "Selecione o efeito");
		
		EffectManager em = Main.getInstance().effects.get(p.getName().toLowerCase());
		ManaModel mm = Main.getInstance().maniacs.get(p.getName());
		
		if (em.hasEffect(EffectType.ENDER_PEARL)) {
			inv.setItem(10, new ItemBuilder(Material.ENDER_PEARL)
					.enchant(Enchantment.DURABILITY, 1)
					.removeAttributes()
					.name("�aEnder Pearl")
					.listlore((em.hasEffect(EffectType.ENDER_PEARL) ? Arrays.asList("�7Este efeito te permite em uma chance de 5%","�7para voc� n�o receber o dano da ender pearl.","","�eClique para ativar/desativar!") : Arrays.asList("�7Este efeito te permite em uma chance de 5%","�7para voc� n�o receber o dano da ender pearl.","",(mm.getMana() >= 100000 ? "�aClique para comprar!" : "�cVoc� n�o tem mana suficiente."),"�fCusto: �7100.000")))
					.build());
		} else {
			inv.setItem(10, new ItemBuilder(Material.ENDER_PEARL)
					.name("�aEnder Pearl")
					.listlore((em.hasEffect(EffectType.ENDER_PEARL) ? Arrays.asList("�7Este efeito te permite em uma chance de 5%","�7para voc� n�o receber o dano da ender pearl.","","�eClique para ativar/desativar!") : Arrays.asList("�7Este efeito te permite em uma chance de 5%","�7para voc� n�o receber o dano da ender pearl.","",(mm.getMana() >= 100000 ? "�aClique para comprar!" : "�cVoc� n�o tem mana suficiente."),"�fCusto: �7100.000")))
					.build());
		}
		if (em.hasEffect(EffectType.ZEUS)) {
			inv.setItem(11, new ItemBuilder(Material.BLAZE_ROD)
					.enchant(Enchantment.DURABILITY, 1)
					.removeAttributes()
					.name("�aZeus")
					.listlore((em.hasEffect(EffectType.ZEUS) ? Arrays.asList("�7Este efeito te permite em uma chance de 2%", "�7para jogar um raio no seu inimigo.","","�eClique para ativar/desativar!") : Arrays.asList("�7Este efeito te permite em uma chance de 2%", "�7para jogar um raio no seu inimigo.","",(mm.getMana() >= 100000 ? "�aClique para comprar!" : "�cVoc� n�o tem mana suficiente."),"�fCusto: �7100.000")))
					.build());
		} else {
			inv.setItem(11, new ItemBuilder(Material.BLAZE_ROD)
					.name("�aZeus")
					.listlore((em.hasEffect(EffectType.ZEUS) ? Arrays.asList("�7Este efeito te permite em uma chance de 2%", "�7para jogar um raio no seu inimigo.","","�eClique para ativar/desativar!") : Arrays.asList("�7Este efeito te permite em uma chance de 2%", "�7para jogar um raio no seu inimigo.","",(mm.getMana() >= 100000 ? "�aClique para comprar!" : "�cVoc� n�o tem mana suficiente."),"�fCusto: �7100.000")))
					.build());
		}
		if (em.hasEffect(EffectType.DEATH)) {
			inv.setItem(12, new ItemBuilder(Material.TNT)
					.enchant(Enchantment.DURABILITY, 1)
					.removeAttributes()
					.name("�aExplos�o ap�s morte")
					.listlore((em.hasEffect(EffectType.DEATH) ? Arrays.asList("�7Este efeito te permite em uma chance de 2%", "�7para criar uma explos�o ao morrer.","","�eClique para ativar/desativar!") : Arrays.asList("�7Este efeito te permite em uma chance de 2%", "�7para criar uma explos�o ao morrer.","",(mm.getMana() >= 100000 ? "�aClique para comprar!" : "�cVoc� n�o tem mana suficiente."),"�fCusto: �7100.000")))
					.build());
		} else {
			inv.setItem(12, new ItemBuilder(Material.TNT)
					.name("�aExplos�o ap�s morte")
					.listlore((em.hasEffect(EffectType.DEATH) ? Arrays.asList("�7Este efeito te permite em uma chance de 2%", "�7para criar uma explos�o ao morrer.","","�eClique para ativar/desativar!") : Arrays.asList("�7Este efeito te permite em uma chance de 2%", "�7para criar uma explos�o ao morrer.","",(mm.getMana() >= 100000 ? "�aClique para comprar!" : "�cVoc� n�o tem mana suficiente."),"�fCusto: �7100.000")))
					.build());
		}
		if (em.hasEffect(EffectType.HEALTH)) {
			inv.setItem(14, new ItemBuilder(Material.REDSTONE)
					.enchant(Enchantment.DURABILITY, 1)
					.removeAttributes()
					.name("�aVida instant�nea")
					.listlore((em.hasEffect(EffectType.HEALTH) ? Arrays.asList("�7Este efeito te permite em uma chance de 2%", "�7para voc� receber 2 cora��es de vida ap�s levar dano.","","�eClique para ativar/desativar!") : Arrays.asList("�7Este efeito te permite em uma chance de 2%", "�7para voc� receber 2 cora��es de vida ap�s levar dano.","",(mm.getMana() >= 100000 ? "�aClique para comprar!" : "�cVoc� n�o tem mana suficiente."),"�fCusto: �7100.000")))
					.build());
		}else {
			inv.setItem(14, new ItemBuilder(Material.REDSTONE)
					.name("�aVida instant�nea")
					.listlore((em.hasEffect(EffectType.HEALTH) ? Arrays.asList("�7Este efeito te permite em uma chance de 2%", "�7para voc� receber 2 cora��es de vida ap�s levar dano.","","�eClique para ativar/desativar!") : Arrays.asList("�7Este efeito te permite em uma chance de 2%", "�7para voc� receber 2 cora��es de vida ap�s levar dano.","",(mm.getMana() >= 100000 ? "�aClique para comprar!" : "�cVoc� n�o tem mana suficiente."),"�fCusto: �7100.000")))
					.build());
		}
		if (em.hasEffect(EffectType.JUMP)) {
			inv.setItem(15, new ItemBuilder(Material.NETHERRACK)
					.enchant(Enchantment.DURABILITY, 1)
					.removeAttributes()
					.name("�aPulo duplo")
					.listlore((em.hasEffect(EffectType.JUMP) ? Arrays.asList("�7Este efeito te permite em uma chance de 2%", "�7para voc� dar um super pulo.","","�eClique para ativar/desativar!") : Arrays.asList("�7Este efeito te permite em uma chance de 2%", "�7para voc� dar um super pulo.","",(mm.getMana() >= 100000 ? "�aClique para comprar!" : "�cVoc� n�o tem mana suficiente."),"�fCusto: �7100.000")))
					.build());
		} else {
			inv.setItem(15, new ItemBuilder(Material.NETHERRACK)
					.name("�aPulo duplo")
					.listlore((em.hasEffect(EffectType.JUMP) ? Arrays.asList("�7Este efeito te permite em uma chance de 2%", "�7para voc� dar um super pulo.","","�eClique para ativar/desativar!") : Arrays.asList("�7Este efeito te permite em uma chance de 2%", "�7para voc� dar um super pulo.","",(mm.getMana() >= 100000 ? "�aClique para comprar!" : "�cVoc� n�o tem mana suficiente."),"�fCusto: �7100.000")))
					.build());
		}
		if (em.hasEffect(EffectType.POWER)) {
			inv.setItem(16, new ItemBuilder(Material.BLAZE_ROD)
					.enchant(Enchantment.DURABILITY, 1)
					.removeAttributes()
					.name("�aFor�a instant�nea")
					.listlore((em.hasEffect(EffectType.POWER) ? Arrays.asList("�7Este efeito te permite em uma chance de 2%", "�7para voc� ganhar for�a instant�nea.","","�eClique para ativar/desativar!") : Arrays.asList("�7Este efeito te permite em uma chance de 2%", "�7para voc� ganhar for�a instant�nea.","",(mm.getMana() >= 100000 ? "�aClique para comprar!" : "�cVoc� n�o tem mana suficiente."),"�fCusto: �7100.000")))
					.build());			
		} else {
			inv.setItem(16, new ItemBuilder(Material.BLAZE_ROD)
					.name("�aFor�a instant�nea")
					.listlore((em.hasEffect(EffectType.POWER) ? Arrays.asList("�7Este efeito te permite em uma chance de 2%", "�7para voc� ganhar for�a instant�nea.","","�eClique para ativar/desativar!") : Arrays.asList("�7Este efeito te permite em uma chance de 2%", "�7para voc� ganhar for�a instant�nea.","",(mm.getMana() >= 100000 ? "�aClique para comprar!" : "�cVoc� n�o tem mana suficiente."),"�fCusto: �7100.000")))
					.build());
		}
		
		p.openInventory(inv);
	}
}
