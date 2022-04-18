package com.wandy.api.caixas.spawners;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.wandy.api.utils.fanciful.FancyMessage;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ItensCaixas {
	
	public static ItemStack getItemSpawners() {
		ItemStack item = null;
		Random r = new Random();
		int rn = r.nextInt(161);
		if (rn >= 0) {
			if (rn <= 10) {
				ItemStack a = new ItemStack(Material.IRON_BLOCK, 16);
				item = a;
			}
		}
		if (rn >= 11) {
			if (rn <= 20) {
				ItemStack b = new ItemStack(Material.EMERALD_BLOCK, 16);
				item = b;
			}
		}
		if (rn >= 21) {
			if (rn <= 30) {
				ItemStack c = new ItemStack(Material.GOLD_BLOCK, 16);
				item = c;
			}
		}
		if (rn >= 31) {
			if (rn <= 40) {
				ItemStack d = new ItemStack(Material.BEDROCK, 64);
				item = d;
			}
		}
		if (rn >= 41) {
			if (rn <= 55) {
				ItemStack d3 = new ItemStack(Material.ENDER_STONE, 64);
				item = d3;
			}
		}
		if (rn >= 56) {
			if (rn <= 70) {
				item = new ItemStack(Material.MONSTER_EGG, 1, (short) 50);
			}
		}
		if (rn >= 71) {
			if (rn <= 80) {
				ItemStack a1 = new ItemStack(Material.GOLDEN_APPLE, 15, (short) 1);
				item = a1;
			}
		}
		if (rn >= 81) {
			if (rn <= 100) {
				ItemStack b1 = Conteúdo.spawnerCreate("Cow");
				item = b1;
			}
		}
		if (rn >= 101) {
			if (rn <= 118) {
				ItemStack c1 = Conteúdo.spawnerCreate("Spider");
				item = c1;
			}
		}
		if (rn >= 119) {
			if (rn <= 128) {
				ItemStack d1 = Conteúdo.spawnerCreate("Skeleton");
				item = d1;
			}
		}
		if (rn >= 129) {
			if (rn <= 139) {
				ItemStack d31 = Conteúdo.spawnerCreate("Zombie");
				item = d31;
			}
		}
		if (rn >= 140) {
			if (rn <= 150) {
				ItemStack b111 = Conteúdo.spawnerCreate("Blaze");
				item = b111;
			}
		}
		if (rn >= 151) {
			if (rn <= 159) {
				ItemStack b1 = Conteúdo.spawnerCreate("Pig_zombie");
				item = b1;
			}
		}
		if (rn >= 160) {
			if (rn <= 161) {
				ItemStack b1 = Conteúdo.spawnerCreate("Iron_golem");
				item = b1;
			}
		}
		return item;
	}

	@SuppressWarnings("deprecation")
	public static ItemStack getItemSpawners(Player p) {
		ItemStack item = null;
		Random r = new Random();
		int rn = r.nextInt(161);
		String tp = "";
		if (rn >= 0) {
			if (rn <= 10) {
				ItemStack a = new ItemStack(Material.IRON_BLOCK, 16);
				item = a;
			}
		}
		if (rn >= 11) {
			if (rn <= 20) {
				ItemStack b = new ItemStack(Material.EMERALD_BLOCK, 16);
				item = b;
			}
		}
		if (rn >= 21) {
			if (rn <= 30) {
				ItemStack c = new ItemStack(Material.GOLD_BLOCK, 16);
				item = c;
			}
		}
		if (rn >= 31) {
			if (rn <= 40) {
				ItemStack d = new ItemStack(Material.BEDROCK, 64);
				item = d;
			}
		}
		if (rn >= 41) {
			if (rn <= 55) {
				ItemStack d3 = new ItemStack(Material.ENDER_STONE, 64);
				item = d3;
			}
		}
		if (rn >= 56) {
			if (rn <= 70) {
				item = new ItemStack(Material.MONSTER_EGG, 1, (short) 50);
				tp = "§5RARO";
			}
		}
		if (rn >= 71) {
			if (rn <= 80) {
				ItemStack a1 = new ItemStack(Material.GOLDEN_APPLE, 15, (short) 1);
				item = a1;
				tp = "§5RARO";
			}
		}
		if (rn >= 81) {
			if (rn <= 100) {
				ItemStack b1 = Conteúdo.spawnerCreate("Cow");
				item = b1;
			}
		}
		if (rn >= 101) {
			if (rn <= 118) {
				ItemStack c1 = Conteúdo.spawnerCreate("Spider");
				item = c1;
			}
		}
		if (rn >= 119) {
			if (rn <= 128) {
				ItemStack d1 = Conteúdo.spawnerCreate("Skeleton");
				item = d1;
			}
		}
		if (rn >= 129) {
			if (rn <= 139) {
				ItemStack d31 = Conteúdo.spawnerCreate("Zombie");
				item = d31;
				tp = "§5RARO";
			}
		}
		if (rn >= 140) {
			if (rn <= 150) {
				ItemStack b111 = Conteúdo.spawnerCreate("Blaze");
				item = b111;

				tp = "§4HÍPER RARO";
			}
		}
		if (rn >= 151) {
			if (rn <= 159) {
				ItemStack b1 = Conteúdo.spawnerCreate("Pig_zombie");
				item = b1;
				tp = "§4HÍPER RARO";
			}
		}
		if (rn >= 160) {
			if (rn <= 161) {
				ItemStack b1 = Conteúdo.spawnerCreate("Iron_golem");
				item = b1;
				tp = "§6ULTRA RARO";
			}
		}
		if (!tp.equals("")) {
			String prefix = PermissionsEx.getUser(p).getGroups()[0].getPrefix();
			String pr = "";
			if (tp.equals("§6ULTRA RARO")) {
				pr = " §aParabéns!";
				p.getWorld().strikeLightning(p.getLocation().clone().add(0.0D, 5.0D, 0.0D));
			}
			for (Player todos : Bukkit.getOnlinePlayers()) {
				new FancyMessage("").then(prefix.replace("&", "§") + " " + p.getName() + " ").then("§aconseguiu um item ").color(ChatColor.GREEN).then(tp).itemTooltip(item).then(" §ana Caixa Misteriosa!" + pr).color(ChatColor.GREEN).send(todos);
			}
		}
		return item;
	}
}
