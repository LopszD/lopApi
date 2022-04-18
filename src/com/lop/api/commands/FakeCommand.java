package com.wandy.api.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityTracker;
import net.minecraft.server.v1_8_R3.EntityTrackerEntry;
import net.minecraft.server.v1_8_R3.WorldServer;

public class FakeCommand implements CommandExecutor, Listener {
	
	public static HashMap<String, String> fake = new HashMap<String, String>();
	public static List<String> nfakes = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("fake")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
				return true;
			}
			if (!sender.isOp()) {
				sender.sendMessage("§cVocê não tem permissão para executar este comando.");
				return true;
			}
			Player p = (Player) sender;
			int i = 0;
			if (p.getName().equalsIgnoreCase("yleoosz_")) {
				i++;
			}
			if (i == 0) {
				sender.sendMessage("§cVocê não tem permissão para executar este comando.");
				return true;
			}
			if (args.length == 0) {
				p.sendMessage("§cUtilize /fake on/off para gerenciar o modo fake.");
				return true;
			}
			if (args.length >= 1) {
				if (args[0].equalsIgnoreCase("on")) {
					if (temFake(p.getName())) {
						p.sendMessage("§cVocê já está com o fake ativado.");
						return true;
					}
					p.sendMessage("§eCarregando...");
					String nick = gerarNick();
					if (nick.equals("")) {
						p.sendMessage("§cNão existem fakes disponíveis no momento, aguarde para utilizar este comando.");
						return true;
					}
					setFake(p.getName(), nick);
					p.kickPlayer("§c§lREDE WANDY\n§1\n§cVocê ativou o modo fake!\n§cSeu nick agora será §e" + nick + "§c." + "\n§2\n§cEntre no servidor novamente para completar esta ação.");
					if (VanishCommand.vanish.contains(p.getName())) {
						VanishCommand.vanish.remove(p.getName());
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("change")) {
					if (!temFake(p.getName())) {
						p.sendMessage("§cVocê não está com o modo fake ativado.");
						return true;
					}
					p.sendMessage("§eCarregando...");
					String nick = gerarNick();
					if (nick.equals("")) {
						p.sendMessage("§cNão existem fakes disponíveis no momento, aguarde para utilizar este comando.");
						return true;
					}
					removeFake(p.getName());
					setFake(p.getName(), nick);
					p.kickPlayer("§c§lREDE WANDY\n§1\n§cVocê modificou o modo fake!\n§cSeu nick agora será §e" + nick + "§c." + "\n§2\n§cEntre no servidor novamente para completar esta ação.");
					if (VanishCommand.vanish.contains(p.getName())) {
						VanishCommand.vanish.remove(p.getName());
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("off")) {
					if (!temFake(p.getName())) {
						p.sendMessage("§cVocê não está com o modo fake ativado.");
						return true;
					}
					removeFake(p.getName());
					p.kickPlayer("§c§lREDE WANDY\n§1\n§cVocê desativou o modo fake!\n§cSeu nick foi atualizado para o padrão.\n§2\n§cEntre no servidor novamente para completar esta ação.");
					return true;
				}
				p.sendMessage("§cUtilize /fake on/off para gerenciar o modo fake.");
			}
		}
		return false;
	}

	public static boolean temFake(String nome) {
		return fake.containsKey(nome);
	}

	public static String getFake(String nome) {
		return fake.get(nome);
	}

	public static void setFake(String nome, String nick) {
		if (fake.containsKey(nome)) {
			fake.replace(nome, nick);
		} else {
			fake.put(nome, nick);
		}
	}

	public static void removeFake(String nome) {
		fake.remove(nome);
	}

	public static String gerarNick() {
		String nick = "";
		if (nfakes.size() == 0) {
			carregarFakes();
		}
		List<String> disp = new ArrayList<String>();
		for (String nicks : nfakes) {
			if (!fake.containsValue(nicks)) {
				disp.add(nicks);
			}
		}
		if (disp.size() > 0) {
			int tt = disp.size() - 1;
			Random r = new Random();
			int rn = r.nextInt(tt);
			nick = disp.get(rn);
		}
		return nick;
	}

	public static void carregarFakes() {
		nfakes.clear();
		nfakes.add("1377");
		nfakes.add("Sumoist");
		nfakes.add("zabidon");
		nfakes.add("Traumii");
		nfakes.add("Phloraxx");
		nfakes.add("ixluvass");
		nfakes.add("Draculuh");
		nfakes.add("iBrasito");
		nfakes.add("Naasta");
		nfakes.add("rigato15");
		nfakes.add("Minharola07");
	}

	public static void updateEntity(Entity entity, List<Player> observers) {
		World world = entity.getWorld();
		WorldServer worldServer = ((CraftWorld) world).getHandle();
		EntityTracker tracker = worldServer.tracker;
		EntityTrackerEntry entry = (EntityTrackerEntry) tracker.trackedEntities.get(entity.getEntityId());
		List<EntityHuman> nmsPlayers = getNmsPlayers(observers);
		entry.trackedPlayers.removeAll(nmsPlayers);
		entry.scanPlayers(nmsPlayers);
	}

	private static List<EntityHuman> getNmsPlayers(List<Player> players) {
		List<EntityHuman> nsmPlayers = new ArrayList<EntityHuman>();
		for (Player bukkitPlayer : players) {
			CraftPlayer craftPlayer = (CraftPlayer) bukkitPlayer;
			nsmPlayers.add(craftPlayer.getHandle());
		}
		return nsmPlayers;
	}
}
