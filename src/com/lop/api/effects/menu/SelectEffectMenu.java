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
					.name("§aEnder Pearl")
					.listlore((em.hasEffect(EffectType.ENDER_PEARL) ? Arrays.asList("§7Este efeito te permite em uma chance de 5%","§7para você não receber o dano da ender pearl.","","§eClique para ativar/desativar!") : Arrays.asList("§7Este efeito te permite em uma chance de 5%","§7para você não receber o dano da ender pearl.","",(mm.getMana() >= 100000 ? "§aClique para comprar!" : "§cVocê não tem mana suficiente."),"§fCusto: §7100.000")))
					.build());
		} else {
			inv.setItem(10, new ItemBuilder(Material.ENDER_PEARL)
					.name("§aEnder Pearl")
					.listlore((em.hasEffect(EffectType.ENDER_PEARL) ? Arrays.asList("§7Este efeito te permite em uma chance de 5%","§7para você não receber o dano da ender pearl.","","§eClique para ativar/desativar!") : Arrays.asList("§7Este efeito te permite em uma chance de 5%","§7para você não receber o dano da ender pearl.","",(mm.getMana() >= 100000 ? "§aClique para comprar!" : "§cVocê não tem mana suficiente."),"§fCusto: §7100.000")))
					.build());
		}
		if (em.hasEffect(EffectType.ZEUS)) {
			inv.setItem(11, new ItemBuilder(Material.BLAZE_ROD)
					.enchant(Enchantment.DURABILITY, 1)
					.removeAttributes()
					.name("§aZeus")
					.listlore((em.hasEffect(EffectType.ZEUS) ? Arrays.asList("§7Este efeito te permite em uma chance de 2%", "§7para jogar um raio no seu inimigo.","","§eClique para ativar/desativar!") : Arrays.asList("§7Este efeito te permite em uma chance de 2%", "§7para jogar um raio no seu inimigo.","",(mm.getMana() >= 100000 ? "§aClique para comprar!" : "§cVocê não tem mana suficiente."),"§fCusto: §7100.000")))
					.build());
		} else {
			inv.setItem(11, new ItemBuilder(Material.BLAZE_ROD)
					.name("§aZeus")
					.listlore((em.hasEffect(EffectType.ZEUS) ? Arrays.asList("§7Este efeito te permite em uma chance de 2%", "§7para jogar um raio no seu inimigo.","","§eClique para ativar/desativar!") : Arrays.asList("§7Este efeito te permite em uma chance de 2%", "§7para jogar um raio no seu inimigo.","",(mm.getMana() >= 100000 ? "§aClique para comprar!" : "§cVocê não tem mana suficiente."),"§fCusto: §7100.000")))
					.build());
		}
		if (em.hasEffect(EffectType.DEATH)) {
			inv.setItem(12, new ItemBuilder(Material.TNT)
					.enchant(Enchantment.DURABILITY, 1)
					.removeAttributes()
					.name("§aExplosão após morte")
					.listlore((em.hasEffect(EffectType.DEATH) ? Arrays.asList("§7Este efeito te permite em uma chance de 2%", "§7para criar uma explosão ao morrer.","","§eClique para ativar/desativar!") : Arrays.asList("§7Este efeito te permite em uma chance de 2%", "§7para criar uma explosão ao morrer.","",(mm.getMana() >= 100000 ? "§aClique para comprar!" : "§cVocê não tem mana suficiente."),"§fCusto: §7100.000")))
					.build());
		} else {
			inv.setItem(12, new ItemBuilder(Material.TNT)
					.name("§aExplosão após morte")
					.listlore((em.hasEffect(EffectType.DEATH) ? Arrays.asList("§7Este efeito te permite em uma chance de 2%", "§7para criar uma explosão ao morrer.","","§eClique para ativar/desativar!") : Arrays.asList("§7Este efeito te permite em uma chance de 2%", "§7para criar uma explosão ao morrer.","",(mm.getMana() >= 100000 ? "§aClique para comprar!" : "§cVocê não tem mana suficiente."),"§fCusto: §7100.000")))
					.build());
		}
		if (em.hasEffect(EffectType.HEALTH)) {
			inv.setItem(14, new ItemBuilder(Material.REDSTONE)
					.enchant(Enchantment.DURABILITY, 1)
					.removeAttributes()
					.name("§aVida instantânea")
					.listlore((em.hasEffect(EffectType.HEALTH) ? Arrays.asList("§7Este efeito te permite em uma chance de 2%", "§7para você receber 2 corações de vida após levar dano.","","§eClique para ativar/desativar!") : Arrays.asList("§7Este efeito te permite em uma chance de 2%", "§7para você receber 2 corações de vida após levar dano.","",(mm.getMana() >= 100000 ? "§aClique para comprar!" : "§cVocê não tem mana suficiente."),"§fCusto: §7100.000")))
					.build());
		}else {
			inv.setItem(14, new ItemBuilder(Material.REDSTONE)
					.name("§aVida instantânea")
					.listlore((em.hasEffect(EffectType.HEALTH) ? Arrays.asList("§7Este efeito te permite em uma chance de 2%", "§7para você receber 2 corações de vida após levar dano.","","§eClique para ativar/desativar!") : Arrays.asList("§7Este efeito te permite em uma chance de 2%", "§7para você receber 2 corações de vida após levar dano.","",(mm.getMana() >= 100000 ? "§aClique para comprar!" : "§cVocê não tem mana suficiente."),"§fCusto: §7100.000")))
					.build());
		}
		if (em.hasEffect(EffectType.JUMP)) {
			inv.setItem(15, new ItemBuilder(Material.NETHERRACK)
					.enchant(Enchantment.DURABILITY, 1)
					.removeAttributes()
					.name("§aPulo duplo")
					.listlore((em.hasEffect(EffectType.JUMP) ? Arrays.asList("§7Este efeito te permite em uma chance de 2%", "§7para você dar um super pulo.","","§eClique para ativar/desativar!") : Arrays.asList("§7Este efeito te permite em uma chance de 2%", "§7para você dar um super pulo.","",(mm.getMana() >= 100000 ? "§aClique para comprar!" : "§cVocê não tem mana suficiente."),"§fCusto: §7100.000")))
					.build());
		} else {
			inv.setItem(15, new ItemBuilder(Material.NETHERRACK)
					.name("§aPulo duplo")
					.listlore((em.hasEffect(EffectType.JUMP) ? Arrays.asList("§7Este efeito te permite em uma chance de 2%", "§7para você dar um super pulo.","","§eClique para ativar/desativar!") : Arrays.asList("§7Este efeito te permite em uma chance de 2%", "§7para você dar um super pulo.","",(mm.getMana() >= 100000 ? "§aClique para comprar!" : "§cVocê não tem mana suficiente."),"§fCusto: §7100.000")))
					.build());
		}
		if (em.hasEffect(EffectType.POWER)) {
			inv.setItem(16, new ItemBuilder(Material.BLAZE_ROD)
					.enchant(Enchantment.DURABILITY, 1)
					.removeAttributes()
					.name("§aForça instantânea")
					.listlore((em.hasEffect(EffectType.POWER) ? Arrays.asList("§7Este efeito te permite em uma chance de 2%", "§7para você ganhar força instantânea.","","§eClique para ativar/desativar!") : Arrays.asList("§7Este efeito te permite em uma chance de 2%", "§7para você ganhar força instantânea.","",(mm.getMana() >= 100000 ? "§aClique para comprar!" : "§cVocê não tem mana suficiente."),"§fCusto: §7100.000")))
					.build());			
		} else {
			inv.setItem(16, new ItemBuilder(Material.BLAZE_ROD)
					.name("§aForça instantânea")
					.listlore((em.hasEffect(EffectType.POWER) ? Arrays.asList("§7Este efeito te permite em uma chance de 2%", "§7para você ganhar força instantânea.","","§eClique para ativar/desativar!") : Arrays.asList("§7Este efeito te permite em uma chance de 2%", "§7para você ganhar força instantânea.","",(mm.getMana() >= 100000 ? "§aClique para comprar!" : "§cVocê não tem mana suficiente."),"§fCusto: §7100.000")))
					.build());
		}
		
		p.openInventory(inv);
	}
}
