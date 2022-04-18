package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class APICommand implements CommandExecutor, Listener {
	
	public boolean onCommand(CommandSender p, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("api")) || (!p.hasPermission("wandy.api"))) {
			p.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("§cUtilize /api <listar/ligar/desligar> [plugin] para gerenciar os plug-ins do servidor.");
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("listar")) {
				p.sendMessage("§1");
				p.sendMessage("§aLista de plug-ins do servidor:");
				p.sendMessage("§f Total: §7" + getPlugins());
				String linha = "";
				int i = 0;
				Plugin[] plugins;
				for (int length = (plugins = Bukkit.getPluginManager().getPlugins()).length, j = 0; j < length; ++j) {
					final Plugin pl = plugins[j];
					String cor = "§c";
					if (checarLigado(pl)) {
						cor = "§a";
					}
					linha = linha + "§7, " + cor + pl.getName();
					if (i == 0) {
						linha = cor + pl.getName();
					}
					++i;
				}
				p.sendMessage("§7 " + linha);
				p.sendMessage("§2");
				return true;
			}
			if (args[0].equalsIgnoreCase("ligar")) {
				p.sendMessage("§cUtilize /api ligar <plugin> para ligar um plug-in.");
				return true;
			}
			if (args[0].equalsIgnoreCase("desligar")) {
				p.sendMessage("§cUtilize /api desligar <plugin> para desligar um plug-in.");
				return true;
			}
			return true;
		}
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("ligar")) {
				String plu = args[1];
				if (!checarExiste(plu)) {
					p.sendMessage("§cEste plug-in não foi encontrado.");
					return true;
				}
				Plugin pl = getPlugin(plu);
				if (checarLigado(pl)) {
					p.sendMessage("§cEste plug-in já está ligado.");

					return true;
				}
				ligarPlugin(pl);
				p.sendMessage("§aVocê ligou o plug-in " + pl.getName() + " com sucesso.");
				return true;
			}
			if (args[0].equalsIgnoreCase("desligar")) {
				String plu = args[1];
				if (!checarExiste(plu)) {
					p.sendMessage("§cEste plug-in não foi encontrado.");
					return true;
				}
				Plugin pl = getPlugin(plu);
				if (!checarLigado(pl)) {
					p.sendMessage("§cEste plug-in já está desligado.");
					return true;
				}
				desligarPlugin(pl);
				p.sendMessage("§aVocê desligou o plug-in " + pl.getName() + " com sucesso.");
				return true;
			}
			if (args[0].equalsIgnoreCase("reiniciar")) {
				String plu = args[1];
				if (!checarExiste(plu)) {
					p.sendMessage("§cEste plug-in não foi encontrado.");
					return true;
				}
				Plugin pl = getPlugin(plu);
				reiniciarPlugin(pl);
				p.sendMessage("§aVocê reiniciou o plug-in " + pl.getName() + " com sucesso.");
				return true;
			}
			return true;
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public static void abrirMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 54, "§8Olha a mensagem");
		ItemStack a = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		Bukkit.getUnsafe().modifyItemStack(a, "{display:{Name:\"Oak Wood 1\"},SkullOwner:{Id:\"00684a88-5cc8-4713-9e91-7b1906e67580\",Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzFiYzJiY2ZiMmJkMzc1OWU2YjFlODZmYzdhNzk1ODVlMTEyN2RkMzU3ZmMyMDI4OTNmOWRlMjQxYmM5ZTUzMCJ9fX0=\"}]}}}");
		ItemMeta am = a.getItemMeta();
		am.setDisplayName("§aTecla");
		a.setItemMeta(am);

		ItemStack a1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		Bukkit.getUnsafe().modifyItemStack(a1, "{display:{Name:\"Oak Wood 2\"},SkullOwner:{Id:\"f7218833-aceb-4d3e-a1bc-a334be09c375\",Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGNkOWVlZWU4ODM0Njg4ODFkODM4NDhhNDZiZjMwMTI0ODVjMjNmNzU3NTNiOGZiZTg0ODczNDE0MTk4NDcifX19\"}]}}}");
		ItemMeta am1 = a1.getItemMeta();
		am1.setDisplayName("§aTecla");
		a1.setItemMeta(am1);

		ItemStack a2 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		Bukkit.getUnsafe().modifyItemStack(a2, "{display:{Name:\"Oak Wood 3\"},SkullOwner:{Id:\"870c6ce6-78b5-4e09-8745-bd96d616e516\",Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWQ0ZWFlMTM5MzM4NjBhNmRmNWU4ZTk1NTY5M2I5NWE4YzNiMTVjMzZiOGI1ODc1MzJhYzA5OTZiYzM3ZTUifX19\"}]}}}");
		ItemMeta am2 = a2.getItemMeta();
		am2.setDisplayName("§aTecla");
		a2.setItemMeta(am2);

		ItemStack a3 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		Bukkit.getUnsafe().modifyItemStack(a3, "{display:{Name:\"Oak Wood 4\"},SkullOwner:{Id:\"d531b607-3d92-4760-b19f-b64d51da0fa5\",Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDJlNzhmYjIyNDI0MjMyZGMyN2I4MWZiY2I0N2ZkMjRjMWFjZjc2MDk4NzUzZjJkOWMyODU5ODI4N2RiNSJ9fX0=\"}]}}}");
		ItemMeta am3 = a3.getItemMeta();
		am3.setDisplayName("§aTecla");
		a3.setItemMeta(am3);

		ItemStack a4 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		Bukkit.getUnsafe().modifyItemStack(a4, "{display:{Name:\"Oak Wood 5\"},SkullOwner:{Id:\"4aaa0af9-ffde-4f5a-ad06-112dffbade0c\",Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmQ1N2UzYmM4OGE2NTczMGUzMWExNGUzZjQxZTAzOGE1ZWNmMDg5MWE2YzI0MzY0M2I4ZTU0NzZhZTIifX19\"}]}}}");
		ItemMeta am4 = a4.getItemMeta();
		am4.setDisplayName("§aTecla");
		a4.setItemMeta(am4);

		ItemStack a5 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		Bukkit.getUnsafe().modifyItemStack(a5, "{display:{Name:\"Oak Wood 6\"},SkullOwner:{Id:\"58a05887-3473-4c87-8506-2acf877d7ff1\",Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzM0YjM2ZGU3ZDY3OWI4YmJjNzI1NDk5YWRhZWYyNGRjNTE4ZjVhZTIzZTcxNjk4MWUxZGNjNmIyNzIwYWIifX19\"}]}}}");
		ItemMeta am5 = a5.getItemMeta();
		am5.setDisplayName("§aTecla");
		a5.setItemMeta(am5);

		ItemStack a6 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		Bukkit.getUnsafe().modifyItemStack(a6, "{display:{Name:\"Oak Wood 7\"},SkullOwner:{Id:\"23378bd2-28e5-4d7e-8d39-621b732e1f49\",Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmRiNmViMjVkMWZhYWJlMzBjZjQ0NGRjNjMzYjU4MzI0NzVlMzgwOTZiN2UyNDAyYTNlYzQ3NmRkN2I5In19fQ==\"}]}}}");
		ItemMeta am6 = a6.getItemMeta();
		am6.setDisplayName("§aTecla");
		a6.setItemMeta(am6);

		ItemStack a7 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		Bukkit.getUnsafe().modifyItemStack(a7, "{display:{Name:\"Oak Wood 8\"},SkullOwner:{Id:\"19c144be-a435-42a4-9503-83bcd8a7fa70\",Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTkxOTQ5NzNhM2YxN2JkYTk5NzhlZDYyNzMzODM5OTcyMjI3NzRiNDU0Mzg2YzgzMTljMDRmMWY0Zjc0YzJiNSJ9fX0=\"}]}}}");
		ItemMeta am7 = a7.getItemMeta();
		am7.setDisplayName("§aTecla");
		a7.setItemMeta(am7);

		ItemStack a8 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		Bukkit.getUnsafe().modifyItemStack(a8, "{display:{Name:\"Oak Wood 9\"},SkullOwner:{Id:\"c7cad554-93b2-4176-a4ba-8de42aa9c9f2\",Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTY3Y2FmNzU5MWIzOGUxMjVhODAxN2Q1OGNmYzY0MzNiZmFmODRjZDQ5OWQ3OTRmNDFkMTBiZmYyZTViODQwIn19fQ==\"}]}}}");
		ItemMeta am8 = a8.getItemMeta();
		am8.setDisplayName("§aTecla");
		a8.setItemMeta(am8);

		inv.setItem(30, a);
		inv.setItem(31, a1);
		inv.setItem(32, a2);
		inv.setItem(21, a3);
		inv.setItem(22, a4);
		inv.setItem(23, a5);
		inv.setItem(12, a6);
		inv.setItem(13, a7);
		inv.setItem(14, a8);

		p.openInventory(inv);
	}

	@EventHandler
	public static void aoClicar(InventoryClickEvent e) {
		if (e.getInventory().getTitle().equals("§8Olha a mensagem")) {
			if (e.getCurrentItem() != null) {
				if (!e.getCurrentItem().getType().equals(Material.AIR)) {
					e.setCancelled(true);
				}
			}
		}
	}

	public static void desligarPlugin(Plugin pl) {
		Bukkit.getPluginManager().disablePlugin(pl);
	}

	public static void reiniciarPlugin(Plugin pl) {
		Bukkit.getPluginManager().disablePlugin(pl);
		Bukkit.getPluginManager().enablePlugin(pl);
	}

	public static void ligarPlugin(Plugin pl) {
		Bukkit.getPluginManager().enablePlugin(pl);
	}

	public static boolean checarLigado(Plugin pl) {
		if (Bukkit.getPluginManager().isPluginEnabled(pl)) {
			return true;
		}
		return false;
	}

	public static Plugin getPlugin(String pl) {
		Plugin p = Bukkit.getPluginManager().getPlugin(pl);

		return p;
	}

	public static boolean checarExiste(String pl) {
		if (Bukkit.getPluginManager().getPlugin(pl) != null) {
			return true;
		}
		return false;
	}

	public static int getPlugins() {
		int i = 0;
		Plugin[] plugins;
		for (int length = (plugins = Bukkit.getPluginManager().getPlugins()).length, j = 0; j < length; ++j) {
			final Plugin pl = plugins[j];
			pl.getName();
			++i;
		}
		return i;
	}
}
