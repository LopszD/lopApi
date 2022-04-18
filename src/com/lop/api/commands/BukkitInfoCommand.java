package com.wandy.api.commands;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import com.massivecraft.factions.entity.MPlayer;
import com.wandy.api.utils.PotionName;
import com.wandy.economy.API_Economy;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class BukkitInfoCommand implements CommandExecutor {

	private long lastActivityMillis = System.currentTimeMillis();
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (!player.hasPermission("wandy.check")) {
			player.sendMessage("§cVocê não tem permissão pra executar este comando.");
			return true;
		}
		if (args.length == 0) {
			player.sendMessage("§cUtilize /checar <usuário> para mostrar as informações dele.");
			return true;
		}
		Player target = Bukkit.getPlayer(args[0]);
		if (target == null) {
			player.sendMessage("§cEste usuário não está on-line.");
			return true;
		}
		Locale brasil = new Locale("pt", "BR");
		NumberFormat nf = NumberFormat.getNumberInstance(brasil);
		DateFormat df = DateFormat.getDateTimeInstance(0, 0, brasil);
		NumberFormat dff = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
		nf.setMaximumFractionDigits(2);

		Location loc = target.getLocation();
		String efeitos = getEffects(target.getActivePotionEffects());
		String playerName = target.getName();
		String food = String.format("%d", target.getFoodLevel());
		String maxFood = String.format("%d", 20);
		String saturation = String.format("%.1f", target.getSaturation());
		String maxSaturation = String.format("%d", target.getFoodLevel());
		String exhaustion = String.format("%.1f", target.getExhaustion());
		String maxExhaustion = String.format("%.1f", 4.0f);
		String air = String.format("%d", target.getRemainingAir());
		String maxAir = String.format("%d", target.getMaximumAir());
		String xpTotal = String.format("%d", target.getTotalExperience());
		String xpLevel = String.format("%d", target.getLevel());
		String flySpeed = String.format("%.1f", target.getFlySpeed());
		String walkSpeed = String.format("%.1f", target.getWalkSpeed());
		String x = String.format("%.0f", loc.getX());
		String y = String.format("%.0f", loc.getY());
		String z = String.format("%.0f", loc.getZ());
		String world = loc.getWorld().getName();
		String op = translateBoolean(target.isOp() || target.hasPermission("*"));
		String whitelist = translateBoolean(target.isWhitelisted());
		String ip = target.getAddress().getAddress().getHostAddress();
		String locale = getLocale(target);
		String primeiroLogin = df.format(new Date(target.getFirstPlayed()));
		
        String god = "§cNão";
        String fly = "§cNão";
        String v = "§cNão";
        if (VanishCommand.vanish.contains(target.getName())) {
          v = "§aSim";
        }
        if (GodCommand.godmode.contains(target.getName())) {
          god = "§aSim";
        }
        if (FlyCommand.voando.contains(target.getName())) {
          fly = "§aSim";
        }

		player.sendMessage("");
		player.sendMessage("§f Informações de momento sobre " + target.getName() + ":");
		player.sendMessage("");
		player.sendMessage(" §fNome: §7" + playerName);
		player.sendMessage(" §fGrupo: §7" + PermissionsEx.getUser(target).getPrefix().replace("&", "§"));
		player.sendMessage(" §fStatus: §7" + (target.isOnline() ? "§aOn-line" : "§cOff-line"));
		player.sendMessage(" §fUUID: §7" + target.getUniqueId().toString());
		player.sendMessage(" §fCoins: §7" + dff.format(API_Economy.getScoin(target.getName())));
		player.sendMessage(" §fFacção: §7" + (MPlayer.get(target).hasFaction() ? MPlayer.get(target).getFaction().getName() : "Nenhuma"));
		player.sendMessage(" §fCargo: §7" + (MPlayer.get(target).hasFaction() ? MPlayer.get(target).getRole().getPrefix() + MPlayer.get(target).getRole().getName() : "Nenhum"));
		player.sendMessage(" §fAr: §7" + air + "/" + maxAir);
		player.sendMessage(" §fVida: §7" + Integer.valueOf((int) target.getHealth()) + "/" + target.getMaxHealth() + " §c❤");
		player.sendMessage(" §fFome: §7" + food + "/" + maxFood);
		player.sendMessage(" §fSaturação: §7" + saturation + "/" + maxSaturation);
		player.sendMessage(" §fExaustão: §7" + exhaustion + "/" + maxExhaustion);
		player.sendMessage(" §fEfeitos de poção: §7" + efeitos);
		player.sendMessage(" §fXP Total: §7" + xpTotal);
		player.sendMessage(" §fXP Level: §7" + xpLevel);
		player.sendMessage(" §fPing: §7" + getPing(target));
		player.sendMessage(" §fVelocidade voando: §7" + flySpeed);
		player.sendMessage(" §fVelocidade andando: §7" + walkSpeed);
		player.sendMessage(" §fLocalização: §7" + world + "   X-" + x + "   Y-" + y + "   Z" + z);
		player.sendMessage(" §fGamemode: §7Sobrevivencia.");
		player.sendMessage(" §fFly: §7" + fly);
		player.sendMessage(" §fGod: §7" + god);
		player.sendMessage(" §fVanish: §7" + v);
		player.sendMessage(" §fWhitelist: §7" + whitelist);
		player.sendMessage(" §fOP: §7" + op);
		player.sendMessage(" §fIP: §7" + ip);
		player.sendMessage(" §fLinguagem do minecraft: §7" + locale);
		player.sendMessage(" §fPrimeiro login: §7" + primeiroLogin);
		player.sendMessage(" §fÚltimo login: §7" + getLast());
		player.sendMessage("");
		return false;
	}
	
	public String getLast(){
		Date date = new Date(lastActivityMillis);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy 'às' HH:mm:ss");
		return format.format(date);
	}

	public int getPing(Player p) {
		CraftPlayer cp = (CraftPlayer) p;
		EntityPlayer ep = cp.getHandle();
		return ep.ping;
	}

	private String translateBoolean(boolean bool) {
		return bool ? "§aSim" : "§cNão";
	}

	private String getEffects(Collection<PotionEffect> effects) {
		if (!effects.isEmpty()) {
			String str = "";
			for (PotionEffect effect : effects) {
				str += PotionName.valueOf(effect.getType()).getName() + ", ";
			}
			return str.substring(0, str.length() - 2);
		} else
			return "§cNenhum";
	}

	private String getLocale(Player player) {
		try {
			Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
			Field ping = entityPlayer.getClass().getField("locale");
			return String.valueOf(ping.get(entityPlayer));
		} catch (Throwable e) {
			return "§cIndisponivel";
		}
	}
}
