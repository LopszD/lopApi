package com.wandy.api.commands.tpa;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.wandy.api.Main;
import com.wandy.api.commands.VanishCommand;
import com.wandy.api.listeners.ToggleListener;
import com.wandy.api.utils.HelperII;
import com.wandy.api.utils.TimeUtils;
import com.wandy.api.utils.custommessage.CustomMessageFormat;
import com.wandy.api.utils.custommessage.MessageFormatClickType;
import com.wandy.api.utils.custommessage.MessageFormatHoverType;

public class MethodsTpa {

	private static ArrayList<String> tpas = new ArrayList<>();
	private static HashMap<String, Long> cooldown = new HashMap<>();

	protected void sendTpa(Player p, String targetName) {
		Player target = Bukkit.getPlayer(targetName);
		String pName = p.getName();
		if (cooldown.containsKey(pName)) {
			p.sendMessage("�cEspere �7" + TimeUtils.formatTimeExtense((int) (60L - (System.currentTimeMillis() - cooldown.get(pName)) / 1000L)) + "�c para poder enviar outro pedido.");
			return;
		}
		if (target != null) {
			targetName = target.getName();
			if (pName == targetName) {
				p.sendMessage("�cVoc� n�o pode enviar pedidos de teletransporte para voc� mesmo.");
				return;
			}
			String tagP = HelperII.getTag(pName);
			String tagTarget = HelperII.getTag(targetName);
			String facP = HelperII.getRoleFaction(p);
			String facTarget = HelperII.getRoleFaction(target);
			if (VanishCommand.vanish.contains(targetName)) {
				p.sendMessage("�cEste usu�rio n�o est� on-line.");
				return;
			}
			if (ToggleListener.canceltpa.contains(targetName)) {
				p.sendMessage("�cEste usu�rio desativou o recebimento de teleportes.");
				return;
			}
			p.sendMessage("");
			p.sendMessage("�e Voc� pediu para " + facTarget + tagTarget + targetName + "�e para teletransportar at� ele.");
			new CustomMessageFormat("�e Caso queira cancelar o pedido clique ").append(new CustomMessageFormat("�c�lAQUI").setHover(MessageFormatHoverType.SHOW_TEXT, "�c/tpcancelar " + target.getName()).setClick(MessageFormatClickType.RUN_COMMAND, "/tpcancelar " + target.getName()).getJSON()).append(new CustomMessageFormat("�e.").getJSON()).send(p);
			p.sendMessage("");
			target.sendMessage("");
			target.sendMessage(" " + facP + tagP + pName + "�e pediu para teletransportar at� voc�.");
			new CustomMessageFormat("�e Para aceitar clique ").append(new CustomMessageFormat("�a�lAQUI").setHover(MessageFormatHoverType.SHOW_TEXT, "�a/tpaceitar " + p.getName()).setClick(MessageFormatClickType.RUN_COMMAND, "/tpaceitar " + p.getName()).getJSON()).append(new CustomMessageFormat("�e ou ").getJSON()).append(new CustomMessageFormat("�c�lAQUI").setHover(MessageFormatHoverType.SHOW_TEXT, "�c/tprecusar " + p.getName()).setClick(MessageFormatClickType.RUN_COMMAND, "/tprecusar " + p.getName()).getJSON()).append(new CustomMessageFormat("�e para recusar.").getJSON()).send(target);
			target.sendMessage("�e Voc� tem 60 segundos para responder � este pedido.");
			target.sendMessage("");
			tpas.add(pName + "-" + targetName);
			cooldown.put(pName, System.currentTimeMillis());
			new BukkitRunnable() {
				@Override
				public void run() {
					if (tpas.contains(pName + "-" + target.getName()) && cooldown.containsKey(pName)) {
						tpas.remove(pName + "-" + target.getName());
						cooldown.remove(pName);
						p.sendMessage("�cO pedido enviado para " + facTarget + tagTarget + target.getName() + "�c foi expirado.");
					}
				}
			}.runTaskLaterAsynchronously(Main.getInstance(), 20L * 60);
			return;
		}
		p.sendMessage("�cEste usu�rio n�o est� on-line.");
	}

	protected void acceptTpa(Player target, String[] args) {
		if (args.length >= 1) {
			Player p = Bukkit.getPlayer(args[0]);
			if (p != null) {
				//if (X1Command.emx1.contains(p)) {
				//	target.sendMessage("�cEste usu�rio est� em X1.");
				//	return;
				//}
				subAcceptTpa(target, p);
				return;
			}
			target.sendMessage("�cEste usu�rio n�o est� on-line.");
		} else {
			if (tpas.isEmpty()) {
				target.sendMessage("�cVoc� n�o tem nenhum pedido para ser aceito.");
				return;
			}
			for (String x : tpas) {
				if (x.contains(target.getName())) {
					Player p = Bukkit.getPlayer(x.split("-")[0]);
					if (p != null) {
						//if (X1Command.emx1.contains(p)) {
						//	target.sendMessage("�cEste usu�rio est� em X1.");
						//	return;
						//}
						subAcceptTpa(target, p);
						return;
					}
				}
				target.sendMessage("�cVoc� n�o tem nenhum pedido para ser aceito.");
				return;
			}
		}
	}

	protected void cancelTpa(Player p, String targetName) {
		Player target = Bukkit.getPlayer(targetName);
		String pName = p.getName();
		if (target != null) {
			targetName = target.getName();
			String tagP = HelperII.getTag(pName);
			String tagTarget = HelperII.getTag(targetName);
			String facP = HelperII.getRoleFaction(p);
			String facTarget = HelperII.getRoleFaction(target);
			if (pName == targetName) {
				p.sendMessage("�cVoc� n�o pode cancelar pedidos de teletransporte de voc� mesmo.");
				return;
			}
			if (!tpas.contains(pName + "-" + targetName)) {
				p.sendMessage("�cVoc� n�o tem nenhum pedido de �7" + facTarget + tagTarget + targetName + "�c para cancelar.");
				return;
			}
			tpas.remove(pName + "-" + targetName);
			cooldown.remove(pName);
			p.sendMessage("�cVoc� cancelou o pedido de teletransporte at� " + facTarget + tagTarget + targetName + "�c.");
			target.sendMessage(facP + tagP + pName + "�c cancelou o pedido de teletransporte at� voc�.");
			return;
		}
		p.sendMessage("�cEste usu�rio n�o est� on-line.");
	}

	protected void recuseTpa(Player target, String pName) {
		Player p = Bukkit.getPlayer(pName);
		String targetName = target.getName();
		if (p != null) {
			pName = p.getName();
			String tagP = HelperII.getTag(pName);
			String tagTarget = HelperII.getTag(targetName);
			String facP = HelperII.getRoleFaction(p);
			String facTarget = HelperII.getRoleFaction(target);
			if (targetName == pName) {
				target.sendMessage("�cVoc� n�o pode recusar pedidos de teletransporte de voc� mesmo.");
				return;
			}
			if (!tpas.contains(pName + "-" + targetName)) {
				target.sendMessage("�cVoc� n�o tem nenhum pedido de �7" + facP + tagP + pName + "�c.");
				return;
			}
			tpas.remove(pName + "-" + targetName);
			cooldown.remove(pName);
			target.sendMessage("�cVoc� recusou o pedido de teletransporte de " + facP + tagP + pName + "�c.");
			p.sendMessage(facTarget + tagTarget + targetName + "�c recusou o pedido de teletransporte at� ele.");
			return;
		}
		target.sendMessage("�cEste usu�rio n�o est� on-line.");
	}

	private void subAcceptTpa(Player target, Player p) {
		String pName = p.getName();
		String targetName = target.getName();
		String tagP = HelperII.getTag(pName);
		String tagTarget = HelperII.getTag(targetName);
		String facP = HelperII.getRoleFaction(p);
		String facTarget = HelperII.getRoleFaction(target);
		if (targetName == pName) {
			p.sendMessage("�cVoc� n�o pode aceitar pedidos de teletransporte de voc� mesmo.");
			return;
		}
		if (!tpas.contains(pName + "-" + targetName)) {
			target.sendMessage("�cVoc� n�o tem nenhum pedido de �7" + facP + tagP + pName + "�c.");
			return;
		}
		tpas.remove(pName + "-" + targetName);
		cooldown.remove(pName);
		target.sendMessage("�eVoc� aceitou o pedido de " + facP + tagP + pName + "�e de teletransporte at� voc�.");
		p.sendMessage("�e" + facTarget + tagTarget + targetName + "�e aceitou seu pedido de teletransporte.");
		if (p.hasPermission("wandy.semdelay")) {
			p.teleport(getLocation(target));
			p.sendMessage("�eVoc� foi teletransportado at� " + facTarget + tagTarget + targetName + "�e.");
			return;
		}
		p.sendMessage("�eTeletransportando...");
		new BukkitRunnable() {
			@Override
			public void run() {
				p.teleport(getLocation(target));
				p.sendMessage("�eVoc� foi teletransportado at� " + facTarget + tagTarget + targetName + "�e.");
			}
		}.runTaskLaterAsynchronously(Main.getInstance(), 20L * 3);
		return;
	}

	private Location getLocation(Player p) {
		return new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
	}
}