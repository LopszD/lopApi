package com.wandy.api.item;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.EulerAngle;

public class ItemCommand implements CommandExecutor {
	
	public static NumberFormat numberFormat;
	public static HashMap<String, Entity> selected = new HashMap<String, Entity>();
	public static ArrayList<String> editarmini = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("item")) || (!(sender instanceof Player))) {
			sender.sendMessage("§cApenas usuários in-game podem executar este comando.");
			return true;
		}
		if (!sender.hasPermission("wandy.item")) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (args.length == 0) {
			p.sendMessage("§cUtilize /item <criar/menu/deletar/teleport/editar>.");
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("criar")) {
				ArmorStand as = (ArmorStand) p.getLocation().getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
				as.setSmall(false);
				as.setBasePlate(false);
				p.sendMessage("§aCriado!");
				selected.put(p.getName(), as);
				return true;
			}
			if (args[0].equalsIgnoreCase("menu")) {
				if (!selected.containsKey(p.getName())) {
					p.sendMessage("§cVocê não selecionou o objeto.");
					return true;
				}
				Entity s = (Entity) selected.get(p.getName());
				if (s == null) {
					p.sendMessage("§cSeu objeto selecionado não existe.");
					return true;
				}
				ArmorStand as = (ArmorStand) s;
				TextComponent.sendMenu(p, as);
				return true;
			}
			if (args[0].equalsIgnoreCase("teleport")) {
				if (!selected.containsKey(p.getName())) {
					p.sendMessage("§cVocê não selecionou um objeto.");
					return true;
				}
				Entity s = (Entity) selected.get(p.getName());
				if (s == null) {
					p.sendMessage("§cSeu objeto selecionado não existe.");
					return true;
				}
				ArmorStand as = (ArmorStand) s;
				as.teleport(p);
				p.sendMessage("§aTeleportado!");
				return true;
			}
			if (args[0].equalsIgnoreCase("deletar")) {
				if (!selected.containsKey(p.getName())) {
					p.sendMessage("§cVocê não selecionou um objeto.");
					return true;
				}
				Entity s = (Entity) selected.get(p.getName());
				if (s == null) {
					p.sendMessage("§cSeu objeto selecionado não existe.");
					return true;
				}
				selected.remove(p.getName());
				s.remove();
				p.sendMessage("§aRemovido!");
				return true;
			}
			if (args[0].equalsIgnoreCase("option")) {
				if (!selected.containsKey(p.getName())) {
					p.sendMessage("§cVocê não selecionou um objeto.");
					return true;
				}
				Entity s = (Entity) selected.get(p.getName());
				if (s == null) {
					p.sendMessage("§cSeu objeto selecionado não existe.");
					return true;
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("editar")) {
				if (editarmini.contains(p.getName())) {
					editarmini.remove(p.getName());
					p.sendMessage("§cModo de edição desabilitado!");
					return true;
				}
				editarmini.add(p.getName());
				p.sendMessage("§aModo de edição ativado!");
				return true;
			}
		}
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("option")) {
				if (!selected.containsKey(p.getName())) {
					p.sendMessage("§cVocê não selecionou um objeto.");
					return true;
				}
				Entity s = (Entity) selected.get(p.getName());
				if (s == null) {
					p.sendMessage("§cSeu objeto selecionado não existe.");
					return true;
				}
				ArmorStand as = (ArmorStand) s;
				if (args[1].equalsIgnoreCase("baseplate")) {
					if (as.hasBasePlate()) {
						as.setBasePlate(false);
						return true;
					}
					as.setBasePlate(true);
					return true;
				}
				if (args[1].equalsIgnoreCase("visible")) {
					if (as.isVisible()) {
						as.setVisible(false);
						return true;
					}
					as.setVisible(true);
					return true;
				}
				if (args[1].equalsIgnoreCase("showarms")) {
					if (as.hasArms()) {
						as.setArms(false);
						return true;
					}
					as.setArms(true);
					return true;
				}
				if (args[1].equalsIgnoreCase("small")) {
					if (as.isSmall()) {
						as.setSmall(false);
						return true;
					}
					as.setSmall(true);
					return true;
				}
			}
			return true;
		}
		if (args.length == 2) {
			return true;
		}
		if (args.length == 4) {
			if (args[0].equalsIgnoreCase("modify")) {
				if (!selected.containsKey(p.getName())) {
					p.sendMessage("§cVocê não selecionou um objeto.");
					return true;
				}
				Entity s = (Entity) selected.get(p.getName());
				if (s == null) {
					p.sendMessage("§cSeu objeto selecionado não existe.");
					return true;
				}
				ArmorStand as = (ArmorStand) s;
				if (args[1].equalsIgnoreCase("Head")) {
					if (args[2].equalsIgnoreCase("x")) {
						double x = as.getHeadPose().getX();
						double y = as.getHeadPose().getY();
						double z = as.getHeadPose().getZ();
						double q = Double.valueOf(args[3]).doubleValue();
						double dps = x + q;
						EulerAngle ea = new EulerAngle(dps, y, z);
						as.setHeadPose(ea);
						return true;
					}
					if (args[2].equalsIgnoreCase("y")) {
						double x = as.getHeadPose().getX();
						double y = as.getHeadPose().getY();
						double z = as.getHeadPose().getZ();
						double q = Double.valueOf(args[3]).doubleValue();
						double dps = y + q;
						EulerAngle ea = new EulerAngle(x, dps, z);
						as.setHeadPose(ea);
						return true;
					}
					if (args[2].equalsIgnoreCase("z")) {
						double x = as.getHeadPose().getX();
						double y = as.getHeadPose().getY();
						double z = as.getHeadPose().getZ();
						double q = Double.valueOf(args[3]).doubleValue();
						double dps = z + q;
						EulerAngle ea = new EulerAngle(x, y, dps);
						as.setHeadPose(ea);
						return true;
					}
					return true;
				}
				if (args[1].equalsIgnoreCase("Body")) {
					if (args[2].equalsIgnoreCase("x")) {
						double x = as.getBodyPose().getX();
						double y = as.getBodyPose().getY();
						double z = as.getBodyPose().getZ();
						double q = Double.valueOf(args[3]).doubleValue();
						double dps = x + q;
						EulerAngle ea = new EulerAngle(dps, y, z);
						as.setBodyPose(ea);
						return true;
					}
					if (args[2].equalsIgnoreCase("y")) {
						double x = as.getBodyPose().getX();
						double y = as.getBodyPose().getY();
						double z = as.getBodyPose().getZ();
						double q = Double.valueOf(args[3]).doubleValue();
						double dps = y + q;
						EulerAngle ea = new EulerAngle(x, dps, z);
						as.setBodyPose(ea);
						return true;
					}
					if (args[2].equalsIgnoreCase("z")) {
						double x = as.getBodyPose().getX();
						double y = as.getBodyPose().getY();
						double z = as.getBodyPose().getZ();
						double q = Double.valueOf(args[3]).doubleValue();
						double dps = z + q;
						EulerAngle ea = new EulerAngle(x, y, dps);
						as.setBodyPose(ea);
						return true;
					}
					return true;
				}
				if (args[1].equalsIgnoreCase("LeftLeg")) {
					if (args[2].equalsIgnoreCase("x")) {
						double x = as.getLeftLegPose().getX();
						double y = as.getLeftLegPose().getY();
						double z = as.getLeftLegPose().getZ();
						double q = Double.valueOf(args[3]).doubleValue();
						double dps = x + q;
						EulerAngle ea = new EulerAngle(dps, y, z);
						as.setLeftLegPose(ea);
						return true;
					}
					if (args[2].equalsIgnoreCase("y")) {
						double x = as.getLeftLegPose().getX();
						double y = as.getLeftLegPose().getY();
						double z = as.getLeftLegPose().getZ();
						double q = Double.valueOf(args[3]).doubleValue();
						double dps = y + q;
						EulerAngle ea = new EulerAngle(x, dps, z);
						as.setLeftLegPose(ea);
						return true;
					}
					if (args[2].equalsIgnoreCase("z")) {
						double x = as.getLeftLegPose().getX();
						double y = as.getLeftLegPose().getY();
						double z = as.getLeftLegPose().getZ();
						double q = Double.valueOf(args[3]).doubleValue();
						double dps = z + q;
						EulerAngle ea = new EulerAngle(x, y, dps);
						as.setLeftLegPose(ea);
						return true;
					}
					return true;
				}
				if (args[1].equalsIgnoreCase("RightLeg")) {
					if (args[2].equalsIgnoreCase("x")) {
						double x = as.getRightLegPose().getX();
						double y = as.getRightLegPose().getY();
						double z = as.getRightLegPose().getZ();
						double q = Double.valueOf(args[3]).doubleValue();
						double dps = x + q;
						EulerAngle ea = new EulerAngle(dps, y, z);
						as.setRightLegPose(ea);
						return true;
					}
					if (args[2].equalsIgnoreCase("y")) {
						double x = as.getRightLegPose().getX();
						double y = as.getRightLegPose().getY();
						double z = as.getRightLegPose().getZ();
						double q = Double.valueOf(args[3]).doubleValue();
						double dps = y + q;
						EulerAngle ea = new EulerAngle(x, dps, z);
						as.setRightLegPose(ea);
						return true;
					}
					if (args[2].equalsIgnoreCase("z")) {
						double x = as.getRightLegPose().getX();
						double y = as.getRightLegPose().getY();
						double z = as.getRightLegPose().getZ();
						double q = Double.valueOf(args[3]).doubleValue();
						double dps = z + q;
						EulerAngle ea = new EulerAngle(x, y, dps);
						as.setRightLegPose(ea);
						return true;
					}
				}
				if (args[1].equalsIgnoreCase("LeftArm")) {
					if (args[2].equalsIgnoreCase("x")) {
						double x = as.getLeftArmPose().getX();
						double y = as.getLeftArmPose().getY();
						double z = as.getLeftArmPose().getZ();
						double q = Double.valueOf(args[3]).doubleValue();
						double dps = x + q;
						EulerAngle ea = new EulerAngle(dps, y, z);
						as.setLeftArmPose(ea);
						return true;
					}
					if (args[2].equalsIgnoreCase("y")) {
						double x = as.getLeftArmPose().getX();
						double y = as.getLeftArmPose().getY();
						double z = as.getLeftArmPose().getZ();
						double q = Double.valueOf(args[3]).doubleValue();
						double dps = y + q;
						EulerAngle ea = new EulerAngle(x, dps, z);
						as.setLeftArmPose(ea);
						return true;
					}
					if (args[2].equalsIgnoreCase("z")) {
						double x = as.getLeftArmPose().getX();
						double y = as.getLeftArmPose().getY();
						double z = as.getLeftArmPose().getZ();
						double q = Double.valueOf(args[3]).doubleValue();
						double dps = z + q;
						EulerAngle ea = new EulerAngle(x, y, dps);
						as.setLeftArmPose(ea);
						return true;
					}
					return true;
				}
				if (args[1].equalsIgnoreCase("RightArm")) {
					if (args[2].equalsIgnoreCase("x")) {
						double x = as.getRightArmPose().getX();
						double y = as.getRightArmPose().getY();
						double z = as.getRightArmPose().getZ();
						double q = Double.valueOf(args[3]).doubleValue();
						double dps = x + q;
						EulerAngle ea = new EulerAngle(dps, y, z);
						as.setRightArmPose(ea);
						return true;
					}
					if (args[2].equalsIgnoreCase("y")) {
						double x = as.getRightArmPose().getX();
						double y = as.getRightArmPose().getY();
						double z = as.getRightArmPose().getZ();
						double q = Double.valueOf(args[3]).doubleValue();
						double dps = y + q;
						EulerAngle ea = new EulerAngle(x, dps, z);
						as.setRightArmPose(ea);
						return true;
					}
					if (args[2].equalsIgnoreCase("z")) {
						double x = as.getRightArmPose().getX();
						double y = as.getRightArmPose().getY();
						double z = as.getRightArmPose().getZ();
						double q = Double.valueOf(args[3]).doubleValue();
						double dps = z + q;
						EulerAngle ea = new EulerAngle(x, y, dps);
						as.setRightArmPose(ea);
						return true;
					}
				}
			}
		}
		return false;
	}

	public static String format(double value) {
		numberFormat = NumberFormat.getNumberInstance(Locale.forLanguageTag("en_US"));
		if (value <= 1.0D) {
			return numberFormat.format(value).concat(" ").concat("");
		}
		return numberFormat.format(value).concat(" ").concat("");
	}
}
