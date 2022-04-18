package com.wandy.api.item;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import com.wandy.api.utils.fanciful.FancyMessage;

public class TextComponent {
	
	public static NumberFormat numberFormat;

	public static void sendMenu(Player p, ArmorStand a) {
		for (int i = 0; i < 100; i++) {
			p.sendMessage("§1");
		}
		new FancyMessage("").then("§dOpções: ").then("Visível").color(ChatColor.LIGHT_PURPLE)
				.command("/item option visible").then(" ").then("Base").color(ChatColor.LIGHT_PURPLE)
				.command("/item option baseplate").then(" ").then("MostrarBraços").color(ChatColor.LIGHT_PURPLE)
				.command("/item option showarms").then(" ").then("Pequeno").color(ChatColor.LIGHT_PURPLE)
				.command("/item option small").send(p);

		p.sendMessage("§2");

		new FancyMessage("").then("§fCabeça: ").then("§7X: §e" + format(a.getHeadPose().getX()) + " ").then("[-]")
				.color(ChatColor.RED).command("/item modify Head x -0.1").then(" ").then("[+]").color(ChatColor.GREEN)
				.command("/item modify Head x 0.1")

				.then(" §7Y: §e" + format(a.getHeadPose().getY()) + " ").then("[-]").color(ChatColor.RED)
				.command("/item modify Head y -0.1").then(" ").then("[+]").color(ChatColor.GREEN)
				.command("/item modify Head y 0.1")

				.then(" §7Z: §e" + format(a.getHeadPose().getZ()) + " ").then("[-]").color(ChatColor.RED)
				.command("/item modify Head z -0.1").then(" ").then("[+]").color(ChatColor.GREEN)
				.command("/item modify Head z 0.1")

				.send(p);

		new FancyMessage("").then("§fTronco: ").then("§7X: §e" + format(a.getBodyPose().getX()) + " ").then("[-]")
				.color(ChatColor.RED).command("/item modify Body x -0.1").then(" ").then("[+]").color(ChatColor.GREEN)
				.command("/item modify Body x 0.1")

				.then(" §7Y: §e" + format(a.getBodyPose().getY()) + " ").then("[-]").color(ChatColor.RED)
				.command("/item modify Body y -0.1").then(" ").then("[+]").color(ChatColor.GREEN)
				.command("/item modify Body y 0.1")

				.then(" §7Z: §e" + format(a.getBodyPose().getZ()) + " ").then("[-]").color(ChatColor.RED)
				.command("/item modify Body z -0.1").then(" ").then("[+]").color(ChatColor.GREEN)
				.command("/item modify Body z 0.1")

				.send(p);

		new FancyMessage("").then("§fPernaE: ").then("§7X: §e" + format(a.getLeftLegPose().getX()) + " ").then("[-]")
				.color(ChatColor.RED).command("/item modify LeftLeg x -0.1").then(" ").then("[+]")
				.color(ChatColor.GREEN).command("/item modify LeftLeg x 0.1")

				.then(" §7Y: §e" + format(a.getLeftLegPose().getY()) + " ").then("[-]").color(ChatColor.RED)
				.command("/item modify LeftLeg y -0.1").then(" ").then("[+]").color(ChatColor.GREEN)
				.command("/item modify LeftLeg y 0.1")

				.then(" §7Z: §e" + format(a.getLeftLegPose().getZ()) + " ").then("[-]").color(ChatColor.RED)
				.command("/item modify LeftLeg z -0.1").then(" ").then("[+]").color(ChatColor.GREEN)
				.command("/item modify LeftLeg z 0.1")

				.send(p);

		new FancyMessage("").then("§fPernaD: ").then("§7X: §e" + format(a.getRightLegPose().getX()) + " ").then("[-]")
				.color(ChatColor.RED).command("/item modify RightLeg x -0.1").then(" ").then("[+]")
				.color(ChatColor.GREEN).command("/item modify RightLeg x 0.1")

				.then(" §7Y: §e" + format(a.getRightLegPose().getY()) + " ").then("[-]").color(ChatColor.RED)
				.command("/item modify RightLeg y -0.1").then(" ").then("[+]").color(ChatColor.GREEN)
				.command("/item modify RightLeg y 0.1")

				.then(" §7Z: §e" + format(a.getRightLegPose().getZ()) + " ").then("[-]").color(ChatColor.RED)
				.command("/item modify RightLeg z -0.1").then(" ").then("[+]").color(ChatColor.GREEN)
				.command("/item modify RightLeg z 0.1")

				.send(p);

		new FancyMessage("").then("§fBraçoE: ").then("§7X: §e" + format(a.getLeftArmPose().getX()) + " ").then("[-]")
				.color(ChatColor.RED).command("/item modify LeftArm x -0.1").then(" ").then("[+]")
				.color(ChatColor.GREEN).command("/item modify LeftArm x 0.1")

				.then(" §7Y: §e" + format(a.getLeftArmPose().getY()) + " ").then("[-]").color(ChatColor.RED)
				.command("/item modify LeftArm y -0.1").then(" ").then("[+]").color(ChatColor.GREEN)
				.command("/item modify LeftArm y 0.1")

				.then(" §7Z: §e" + format(a.getLeftArmPose().getZ()) + " ").then("[-]").color(ChatColor.RED)
				.command("/item modify LeftArm z -0.1").then(" ").then("[+]").color(ChatColor.GREEN)
				.command("/item modify LeftArm z 0.1")

				.send(p);

		new FancyMessage("").then("§fBraçoD: ").then("§7X: §e" + format(a.getRightArmPose().getX()) + " ").then("[-]")
				.color(ChatColor.RED).command("/item modify RightArm x -0.1").then(" ").then("[+]")
				.color(ChatColor.GREEN).command("/item modify RightArm x 0.1")

				.then(" §7Y: §e" + format(a.getRightArmPose().getY()) + " ").then("[-]").color(ChatColor.RED)
				.command("/item modify RightArm y -0.1").then(" ").then("[+]").color(ChatColor.GREEN)
				.command("/item modify RightArm y 0.1")

				.then(" §7Z: §e" + format(a.getRightArmPose().getZ()) + " ").then("[-]").color(ChatColor.RED)
				.command("/item modify RightArm z -0.1").then(" ").then("[+]").color(ChatColor.GREEN)
				.command("/item modify RightArm z 0.1")

				.send(p);

		p.sendMessage("§2");
	}

	public static String format(double value) {
		DecimalFormat df2 = new DecimalFormat("###.#");
		String kdrff = df2.format(value);
		if (!kdrff.contains(".")) {
			kdrff = df2.format(value) + ".0";
		}
		String aa = kdrff + "º";

		return aa;
	}
}
