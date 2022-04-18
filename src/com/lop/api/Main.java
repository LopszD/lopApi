package com.wandy.api;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;

import com.comphenix.protocol.ProtocolManager;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.wandy.api.anticheat.AntiCheat;
import com.wandy.api.anticheat.AntiLabyMod;
import com.wandy.api.anticheat.FreeCam;
import com.wandy.api.anticheat.HackListener;
import com.wandy.api.anticheat.HackManager;
import com.wandy.api.anticheat.HackType;
import com.wandy.api.arenas.ArenaCommand;
import com.wandy.api.arenas.ArenaListener;
import com.wandy.api.arenas.ArenaMenu;
import com.wandy.api.caixas.armaduras.Animação;
import com.wandy.api.chat.ChatCommand;
import com.wandy.api.chat.ChatMenu;
import com.wandy.api.commands.APICommand;
import com.wandy.api.commands.BackCommand;
import com.wandy.api.commands.BoosterDropCommand;
import com.wandy.api.commands.BoosterExpCommand;
import com.wandy.api.commands.BordaCommand;
import com.wandy.api.commands.BuffCommand;
import com.wandy.api.commands.BukkitInfoCommand;
import com.wandy.api.commands.ChatFakeCommand;
import com.wandy.api.commands.ChecarCommand;
import com.wandy.api.commands.ClearChatCommand;
import com.wandy.api.commands.ClearCommand;
import com.wandy.api.commands.ColetarCommand;
import com.wandy.api.commands.ColocarTNTCommand;
import com.wandy.api.commands.CompactarCommand;
import com.wandy.api.commands.CoresCommand;
import com.wandy.api.commands.CraftCommand;
import com.wandy.api.commands.DerreterCommand;
import com.wandy.api.commands.DesbugarCommand;
import com.wandy.api.commands.DiaCommand;
import com.wandy.api.commands.DivulgarCommand;
import com.wandy.api.commands.DropsCommand;
import com.wandy.api.commands.EfeitosCommand;
import com.wandy.api.commands.EnchantCommand;
import com.wandy.api.commands.EsconderCommand;
import com.wandy.api.commands.FertilizarCommand;
import com.wandy.api.commands.FindChestCommand;
import com.wandy.api.commands.FindPlayerCommand;
import com.wandy.api.commands.FlyCommand;
import com.wandy.api.commands.FomeCommand;
import com.wandy.api.commands.GamemodeCommand;
import com.wandy.api.commands.GenioCommand;
import com.wandy.api.commands.GiveCommand;
import com.wandy.api.commands.GodCommand;
import com.wandy.api.commands.GravarCommand;
import com.wandy.api.commands.HasteCommand;
import com.wandy.api.commands.HatCommand;
import com.wandy.api.commands.HeadCommand;
import com.wandy.api.commands.HealCommand;
import com.wandy.api.commands.InventarioCommand;
import com.wandy.api.commands.InvseeCommand;
import com.wandy.api.commands.KickCommand;
import com.wandy.api.commands.KillCommand;
import com.wandy.api.commands.KillMobsCommand;
import com.wandy.api.commands.LixeiraCommand;
import com.wandy.api.commands.LojaCommand;
import com.wandy.api.commands.LoreCommand;
import com.wandy.api.commands.LuzCommand;
import com.wandy.api.commands.MacumbeiroCommand;
import com.wandy.api.commands.MagaiverCommand;
import com.wandy.api.commands.MobCommand;
import com.wandy.api.commands.MobstackCommand;
import com.wandy.api.commands.NoiteCommand;
import com.wandy.api.commands.PiadaCommand;
import com.wandy.api.commands.PokeCommand;
import com.wandy.api.commands.PotCommand;
import com.wandy.api.commands.PotionsCommand;
import com.wandy.api.commands.PromoteCommand;
import com.wandy.api.commands.RankCommand;
import com.wandy.api.commands.RedstoneCommand;
import com.wandy.api.commands.ReinícioCommand;
import com.wandy.api.commands.RepararCommand;
import com.wandy.api.commands.RocketCommand;
import com.wandy.api.commands.SairComEstiloCommand;
import com.wandy.api.commands.SayCommand;
import com.wandy.api.commands.SkillsCommand;
import com.wandy.api.commands.SlimeCommand;
import com.wandy.api.commands.SpawnCommand;
import com.wandy.api.commands.SpeedCommand;
import com.wandy.api.commands.StaffCommand;
import com.wandy.api.commands.SuporteCommand;
import com.wandy.api.commands.ThorCommand;
import com.wandy.api.commands.TitleCommand;
import com.wandy.api.commands.ToggleCommand;
import com.wandy.api.commands.TopCommand;
import com.wandy.api.commands.TpAllCommand;
import com.wandy.api.commands.TpCommand;
import com.wandy.api.commands.TpHereCommand;
import com.wandy.api.commands.VIPCommand;
import com.wandy.api.commands.VanishCommand;
import com.wandy.api.commands.YTCommand;
import com.wandy.api.commands.tpa.TpAceitarCommand;
import com.wandy.api.commands.tpa.TpCancelarCommand;
import com.wandy.api.commands.tpa.TpRecusarCommand;
import com.wandy.api.commands.tpa.TpaCommand;
import com.wandy.api.dúvida.CentralD;
import com.wandy.api.dúvida.DuvidaCommand;
import com.wandy.api.dúvida.DuvidaListener;
import com.wandy.api.effects.database.EffectSQL;
import com.wandy.api.effects.listeners.EnderPearlDamageListener;
import com.wandy.api.effects.listeners.PlayerDamangeListener;
import com.wandy.api.effects.listeners.PlayerDeathListener;
import com.wandy.api.effects.listeners.PlayerJumpListener;
import com.wandy.api.effects.manager.EffectManager;
import com.wandy.api.especiais.EspeciaisCommand;
import com.wandy.api.especiais.PowerCommand;
import com.wandy.api.especiais.listeners.AgrupadorListener;
import com.wandy.api.especiais.listeners.ArmadilhaListener;
import com.wandy.api.especiais.listeners.BookCoordenadasListener;
import com.wandy.api.especiais.listeners.BoosterDropListener;
import com.wandy.api.especiais.listeners.BoosterExpListener;
import com.wandy.api.especiais.listeners.CreeperListener;
import com.wandy.api.especiais.listeners.DefensorListener;
import com.wandy.api.especiais.listeners.DevatacaoListener;
import com.wandy.api.especiais.listeners.DispenserListener;
import com.wandy.api.especiais.listeners.DivinaListener;
import com.wandy.api.especiais.listeners.EletromagnéticoListener;
import com.wandy.api.especiais.listeners.FireListener;
import com.wandy.api.especiais.listeners.FoodPotionListener;
import com.wandy.api.especiais.listeners.FragmentoListener;
import com.wandy.api.especiais.listeners.FuradeiraListener;
import com.wandy.api.especiais.listeners.ImpulsaoListener;
import com.wandy.api.especiais.listeners.IncineradorListener;
import com.wandy.api.especiais.listeners.LançadorListener;
import com.wandy.api.especiais.listeners.MembroListener;
import com.wandy.api.especiais.listeners.MoedaReparaçãoListener;
import com.wandy.api.especiais.listeners.NukeTntExplode;
import com.wandy.api.especiais.listeners.PaperListener;
import com.wandy.api.especiais.listeners.PortableCannonListener;
import com.wandy.api.especiais.listeners.PowerInsListener;
import com.wandy.api.especiais.listeners.PowerMaxListener;
import com.wandy.api.especiais.listeners.PurificadorListener;
import com.wandy.api.especiais.listeners.RaioMestreListener;
import com.wandy.api.especiais.listeners.ReparadorListener;
import com.wandy.api.especiais.listeners.ResetKDRListener;
import com.wandy.api.especiais.listeners.RetrocederListener;
import com.wandy.api.especiais.listeners.RunaExtraçãoListener;
import com.wandy.api.especiais.listeners.SilkTouchListener;
import com.wandy.api.especiais.listeners.SlimeListener;
import com.wandy.api.especiais.listeners.SuperCreeperListener;
import com.wandy.api.especiais.listeners.TotemListener;
import com.wandy.api.especiais.listeners.VarinhaListener;
import com.wandy.api.especiais.listeners.VidaMListener;
import com.wandy.api.especiais.listeners.WitherHeadListener;
import com.wandy.api.item.ItemCommand;
import com.wandy.api.item.ItemListener;
import com.wandy.api.listeners.AFKListener;
import com.wandy.api.listeners.AntiBugListener;
import com.wandy.api.listeners.AntiDupeListener;
import com.wandy.api.listeners.BaúsListener;
import com.wandy.api.listeners.CombatLogListener;
import com.wandy.api.listeners.CommandsListener;
import com.wandy.api.listeners.DeusListener;
import com.wandy.api.listeners.DropHeadListener;
import com.wandy.api.listeners.EnchantListener;
import com.wandy.api.listeners.EnchantsListener;
import com.wandy.api.listeners.EnderPearlListener;
import com.wandy.api.listeners.EntrarListener;
import com.wandy.api.listeners.EsconderListener;
import com.wandy.api.listeners.EvScrollListener;
import com.wandy.api.listeners.FindChestListener;
import com.wandy.api.listeners.FindPlayerListener;
import com.wandy.api.listeners.GeralListener;
import com.wandy.api.listeners.IPListener;
import com.wandy.api.listeners.InventoryListener;
import com.wandy.api.listeners.LixeiraListener;
import com.wandy.api.listeners.MCMMoListener;
import com.wandy.api.listeners.MinaListener;
import com.wandy.api.listeners.Morrer2Listener;
import com.wandy.api.listeners.PayListener;
import com.wandy.api.listeners.PersonalizarListener;
import com.wandy.api.listeners.PotionsCustomListener;
import com.wandy.api.listeners.RankListener;
import com.wandy.api.listeners.RedstoneListener;
import com.wandy.api.listeners.RepararListener;
import com.wandy.api.listeners.SkillsListener;
import com.wandy.api.listeners.SlimeChunkListener;
import com.wandy.api.listeners.StaffListener;
import com.wandy.api.listeners.SuporteListener;
import com.wandy.api.listeners.ToggleListener;
import com.wandy.api.listeners.VanishListener;
import com.wandy.api.listeners.WorldVIPListener;
import com.wandy.api.mana.ManaCommand;
import com.wandy.api.mana.ManaListener;
import com.wandy.api.mana.ManaModel;
import com.wandy.api.mana.SQLMana;
import com.wandy.api.manutenção.ManutençãoCommand;
import com.wandy.api.manutenção.ManutençãoListener;
import com.wandy.api.mobspawner.MobsCommand;
import com.wandy.api.mobspawner.MobsListener;
import com.wandy.api.mobspawner.MobsMenu;
import com.wandy.api.proteção.BlockListener;
import com.wandy.api.proteção.BlockStatusListener;
import com.wandy.api.proteção.DurabilityRegenTask;
import com.wandy.api.punish.MembroP;
import com.wandy.api.punish.PunirCommand;
import com.wandy.api.punish.PunirListener;
import com.wandy.api.sql.Manager;
import com.wandy.api.sql.MySQL;
import com.wandy.api.stack.Evento_Morrer;
import com.wandy.api.stack.Evento_Spawn;
import com.wandy.api.stack.Evento_StackMorrer;
import com.wandy.api.stattrack.ConfirmaçãoMenu;
import com.wandy.api.stattrack.MagaiverListener;
import com.wandy.api.stattrack.RecompensasMenu;
import com.wandy.api.stattrack.StatCommand;
import com.wandy.api.stattrack.StatMenu;
import com.wandy.api.utils.Magaiver;
import com.wandy.api.utils.Proteção;
import com.wandy.api.utils.StartedTime;
import com.wandy.api.utils.TPS;
import com.wandy.api.utils.ftop.TOPAPI;
import com.wandy.api.utils.ftop.TOPEVENTOS;

import net.milkbowl.vault.economy.Economy;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class Main extends JavaPlugin implements Listener {

	public static List<String> bypass = new ArrayList<String>();
	public static List<PrintWriter> escritores = new ArrayList<PrintWriter>();
	public static Main plugin;
	public static ProtocolManager protocolManager;
	public static MySQL sql;
	public StartedTime start;
	public static int Teleporte;
	public static HashMap<String, Integer> JogadorTeleporte = new HashMap<String, Integer>();
	public HashMap<String, ManaModel> maniacs = new HashMap<>();
	public HashMap<String, EffectManager> effects = new HashMap<>();
	public FileConfiguration cf = getConfig();
	public static Economy econ = null;
	public static Integer spawn = Integer.valueOf(3);
	private static Main i;

	public Main() {
		Main.i = this;
	}

	public static Main getInstance() {
		return i;
	}

	public static Plugin getPlugin(){
		return plugin;
	}
	
	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = (Economy) rsp.getProvider();
		return econ != null;
	}

	@SuppressWarnings("deprecation")
	public void onEnable() {
		if (getConfig().getString("Config") == null) {
			salvarConfiguracao();
			saveResource("canhao.schematic", false);
			Bukkit.getConsoleSender().sendMessage("§cNecessário acesso à MySQL para o plugin funcionar.");
			return;
		}
		if (!getConfig().getString("Config.MySQL").equalsIgnoreCase("true")) {
			Bukkit.getConsoleSender().sendMessage("§cNecessário acesso à MySQL para o plugin funcionar.");
			return;
		}
		setupSQL();
		EffectSQL.startConnection();
		SQLMana.startConnection();
		ManutençãoListener.block.add("BLOCK");
		getLogger();
		plugin = this;
		getCommand("gm").setExecutor(new GamemodeCommand());
		getCommand("clear").setExecutor(new ClearCommand());
		getCommand("desbugar").setExecutor(new DesbugarCommand());
		getCommand("invsee").setExecutor(new InvseeCommand());
		getCommand("hat").setExecutor(new HatCommand());
		getCommand("tp").setExecutor(new TpCommand());
		getCommand("speed").setExecutor(new SpeedCommand());
		getCommand("god").setExecutor(new GodCommand());
		getCommand("head").setExecutor(new HeadCommand());
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("top").setExecutor(new TopCommand());
		getCommand("kill").setExecutor(new KillCommand());
		getCommand("killmobs").setExecutor(new KillMobsCommand());
		getCommand("macumba").setExecutor(new MacumbeiroCommand());
		getCommand("tpall").setExecutor(new TpAllCommand());
		getCommand("heal").setExecutor(new HealCommand());
		getCommand("feed").setExecutor(new FomeCommand());
		getCommand("dia").setExecutor(new DiaCommand());
		getCommand("yt").setExecutor(new YTCommand());
		getCommand("noite").setExecutor(new NoiteCommand());
		getCommand("limparchat").setExecutor(new ClearChatCommand());
		getCommand("suporte").setExecutor(new SuporteCommand());
		getCommand("promover").setExecutor(new PromoteCommand());
		getCommand("rebaixar").setExecutor(new PromoteCommand());
		getCommand("rocket").setExecutor(new RocketCommand());
		getCommand("saircomestilo").setExecutor(new SairComEstiloCommand());
		getCommand("thor").setExecutor(new ThorCommand());
		getCommand("kick").setExecutor(new KickCommand());
		getCommand("vanish").setExecutor(new VanishCommand());
		getCommand("tphere").setExecutor(new TpHereCommand());
		getCommand("title").setExecutor(new TitleCommand());
		getCommand("give").setExecutor(new GiveCommand());
		getCommand("say").setExecutor(new SayCommand());
		getCommand("reiniciar").setExecutor(new ReinícioCommand());
		getCommand("spawn").setExecutor(new SpawnCommand());
		getCommand("mobspawner").setExecutor(new MobsCommand());
		getCommand("vip").setExecutor(new VIPCommand());
		getCommand("cores").setExecutor(new CoresCommand());
		getCommand("toggle").setExecutor(new ToggleCommand());
		getCommand("chat").setExecutor(new ChatCommand());
		getCommand("poder").setExecutor(new PowerCommand());
		getCommand("findchest").setExecutor(new FindChestCommand());
		getCommand("fertilizar").setExecutor(new FertilizarCommand());
		getCommand("compactar").setExecutor(new CompactarCommand());
		getCommand("item").setExecutor(new ItemCommand());
		getCommand("staff").setExecutor(new StaffCommand());
		getCommand("piada").setExecutor(new PiadaCommand());
		getCommand("check").setExecutor(new ChecarCommand());
		getCommand("mobstack").setExecutor(new MobstackCommand());
		getCommand("especiais").setExecutor(new EspeciaisCommand());
		getCommand("findplayer").setExecutor(new FindPlayerCommand());
		getCommand("enchant").setExecutor(new EnchantCommand());
		getCommand("lore").setExecutor(new LoreCommand());
		getCommand("api").setExecutor(new APICommand());
		getCommand("chatfake").setExecutor(new ChatFakeCommand());
		getCommand("poke").setExecutor(new PokeCommand());
		getCommand("rank").setExecutor(new RankCommand());
		getCommand("punir").setExecutor(new PunirCommand());
		getCommand("esconder").setExecutor(new EsconderCommand());
		getCommand("craft").setExecutor(new CraftCommand());
		getCommand("manutencao").setExecutor(new ManutençãoCommand());
		getCommand("redstone").setExecutor(new RedstoneCommand());
		getCommand("slimechunk").setExecutor(new SlimeCommand());
		getCommand("boosterdrop").setExecutor(new BoosterDropCommand());
		getCommand("boosterexp").setExecutor(new BoosterExpCommand());
		getCommand("potions").setExecutor(new PotionsCommand());
		getCommand("loja").setExecutor(new LojaCommand());
		getCommand("lixeira").setExecutor(new LixeiraCommand());
		getCommand("skills").setExecutor(new SkillsCommand());
		getCommand("derreter").setExecutor(new DerreterCommand());
		getCommand("borda").setExecutor(new BordaCommand());
		getCommand("drops").setExecutor(new DropsCommand());
		getCommand("macumbeiro").setExecutor(new MagaiverCommand());
		getCommand("efeitos").setExecutor(new EfeitosCommand());
		getCommand("mana").setExecutor(new ManaCommand());
		getCommand("coletar").setExecutor(new ColetarCommand());
		getCommand("inventario").setExecutor(new InventarioCommand());
		getCommand("buff").setExecutor(new BuffCommand());
		getCommand("arena").setExecutor(new ArenaCommand());
		getCommand("checar").setExecutor(new BukkitInfoCommand());
		getCommand("colocartnt").setExecutor(new ColocarTNTCommand());
		getCommand("reparar").setExecutor(new RepararCommand());
		getCommand("pot").setExecutor(new PotCommand());
		getCommand("haste").setExecutor(new HasteCommand());
		getCommand("gravar").setExecutor(new GravarCommand());
		getCommand("genio").setExecutor(new GenioCommand());
		getCommand("divulgar").setExecutor(new DivulgarCommand());
		getCommand("maldicao").setExecutor(new StatCommand());
		getCommand("tpa").setExecutor(new TpaCommand());
		getCommand("tpcancelar").setExecutor(new TpCancelarCommand());
		getCommand("tpaceitar").setExecutor(new TpAceitarCommand());
		getCommand("tprecusar").setExecutor(new TpRecusarCommand());
		getCommand("duvida").setExecutor(new DuvidaCommand());
		new BackCommand(this);
		new LuzCommand(this);
		setupEconomy();
		loadChunks();
		isVanish();
		isDefensor();
		isCombat();
		RegistrarEventos();
		start = new StartedTime(System.currentTimeMillis());
		MobCommand.trapped = new HashMap<>();
		CommandsListener.enviou = new HashMap<>();
		if (!this.getServer().getMessenger().isOutgoingChannelRegistered(this, "BungeeCord")) {
			this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		}
		new DurabilityRegenTask().runTaskTimerAsynchronously(this, 0L, 12000L);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new TPS(), 100L, 1L);
		if (LançadorListener.usou == null) {
			LançadorListener.usou = new HashMap<>();
		}
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				RankListener.removerTOP();
				RankListener.recarregarTOP();
				TOPAPI.carregarDados();
				TOPAPI.criarTop();
				TOPAPI.updateTop();
				TOPAPI.mobs.clear();
			}
		}, 6000L, 6000L);
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				Bukkit.getWorld("world").save();
			}
		}, 25000L, 25000L);
		if (!ManutençãoCommand.checarExiste()) {
			ManutençãoCommand.registrarServer();
		}
		Manager.importarLocs();
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				Manager.importarLocs();
				RankListener.carregarTopGeralFac();
				if (Manager.checarLocExiste("NPCYOUTUBER")) {
					RankListener.createNPCYoutuber(Manager.pegarLocation("NPCYOUTUBER"));
				}
				if (Manager.checarLocExiste("NPCMISSOES")) {
					RankListener.createNPCYoutuber(Manager.pegarLocation("NPCMISSOES"));
				}
				if (Manager.checarLocExiste("NPCMAGAIVER")) {
					RankListener.createNPCYoutuber(Manager.pegarLocation("NPCMAGAIVER"));
				}
				if (Manager.checarLocExiste("NPCRECOMPENSAS")) {
					RankListener.createNPCYoutuber(Manager.pegarLocation("NPCRECOMPENSAS"));
				}
				RankListener.carregarTOP();
				TOPAPI.carregarDados();
				TOPAPI.criarTop();
				TOPAPI.updateTop();
			}
		}, 200L);
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				if (ManutençãoListener.block.contains("BLOCK")) {
					ManutençãoListener.block.remove("BLOCK");
				}
			}
		}, 300L);
		CombatLogListener.startTask();
		Manager.importarPunishs();
		Bukkit.getMessenger().registerIncomingPluginChannel(plugin, "PatraoChannel", new PluginMessageListener() {
			public void onPluginMessageReceived(String channel, Player player, byte[] message) {
				if (channel.equals("PatraoChannel")) {
					player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 1.0F);
				}
			}
		});
		Bukkit.getMessenger().registerIncomingPluginChannel(plugin, "MutePunish", new PluginMessageListener() {
			public void onPluginMessageReceived(String channel, Player player, byte[] message) {
				if (channel.equals("MutePunish")) {
					MembroP.refreshPunish(player);
				}
			}
		});
		Bukkit.getMessenger().registerIncomingPluginChannel(plugin, "GOCHANNEL", new PluginMessageListener() {
			public void onPluginMessageReceived(String channel, Player player, byte[] message) {
				if (channel.equals("GOCHANNEL")) {
					ByteArrayDataInput in = ByteStreams.newDataInput(message);
					String name = in.readUTF();
					Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, new Runnable() {
						public void run() {
							if (Bukkit.getPlayer(name) != null) {
								Bukkit.getPlayer(name).teleport(player);
							}
						}
					}, 50L);
				}
			}
		});
	}

	public void onDisable() {
		RankListener.removerTOP();
		Manager.exportarLocs();
		Manager.exportarMobs();
		for (World world : Bukkit.getServer().getWorlds()) {
			world.save();
		}
		for (Player todos : Bukkit.getOnlinePlayers()) {
			if (todos.getOpenInventory() != null) {
				todos.closeInventory();
			}
		}
		for (ManaModel mana : maniacs.values()) {
			SQLMana.updateAccount(mana.getName(), mana.getMana());
		}
		RankListener.removerNPCYoutuber();
		Manager.exportarPunishs();
		Bukkit.getOnlinePlayers().stream().filter(p -> p.getOpenInventory() != null).forEach(p -> p.getOpenInventory().close());
		Bukkit.getWorlds().stream().forEach(r -> r.save());
		plugin = null;
	}

	void RegistrarEventos() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(this, this);
		pm.registerEvents(new HackListener(), this);
		pm.registerEvents(new FreeCam(), this);
		pm.registerEvents(new AntiLabyMod(), this);
		pm.registerEvents(new AntiCheat(this), this);
		pm.registerEvents(new HackType(), this);
		pm.registerEvents(new HackManager(), this);
		pm.registerEvents(new EntrarListener(), this);
		pm.registerEvents(new Morrer2Listener(), this);
		pm.registerEvents(new DeusListener(), this);
		pm.registerEvents(new CommandsListener(), this);
		pm.registerEvents(new VanishCommand(), this);
		pm.registerEvents(new DropHeadListener(), this);
		pm.registerEvents(new SpeedCommand(), this);
		pm.registerEvents(new AFKListener(), this);
		pm.registerEvents(new ToggleListener(), this);
		pm.registerEvents(new EnderPearlListener(), this);
		pm.registerEvents(new FindChestListener(), this);
		pm.registerEvents(new BlockListener(), this);
		pm.registerEvents(new BlockStatusListener(), this);
		pm.registerEvents(new ItemListener(), this);
		pm.registerEvents(new StaffListener(), this);
		pm.registerEvents(new EnchantsListener(), this);
		pm.registerEvents(new WorldVIPListener(), this);
		pm.registerEvents(new Evento_Spawn(), this);
		pm.registerEvents(new FindPlayerListener(), this);
		pm.registerEvents(new EspeciaisCommand(), this);
		pm.registerEvents(new Animação(), this);
		pm.registerEvents(new ArenaMenu(), this);
		pm.registerEvents(new GeralListener(), this);
		pm.registerEvents(new ChatMenu(), this);
		pm.registerEvents(new SuperCreeperListener(), this);
		pm.registerEvents(new Proteção(), this);
		pm.registerEvents(new ArenaListener(), this);
		pm.registerEvents(new EsconderListener(), this);
		pm.registerEvents(new SuporteListener(), this);
		pm.registerEvents(new FireListener(), this);
		pm.registerEvents(new ManutençãoListener(), this);
		pm.registerEvents(new Magaiver(), this);
		pm.registerEvents(new StatMenu(), this);
		pm.registerEvents(new ConfirmaçãoMenu(), this);
		pm.registerEvents(new RecompensasMenu(), this);
		pm.registerEvents(new RedstoneListener(), this);
		pm.registerEvents(new SlimeChunkListener(), this);
		pm.registerEvents(new RepararListener(), this);
		pm.registerEvents(new BoosterDropListener(), this);
		pm.registerEvents(new BoosterExpListener(), this);
		pm.registerEvents(new LançadorListener(), this);
		pm.registerEvents(new RaioMestreListener(), this);
		pm.registerEvents(new ResetKDRListener(), this);
		pm.registerEvents(new CombatLogListener(), this);
		pm.registerEvents(new PotionsCommand(), this);
		pm.registerEvents(new SilkTouchListener(), this);
		pm.registerEvents(new FuradeiraListener(), this);
		pm.registerEvents(new PayListener(), this);
		pm.registerEvents(new ReinícioCommand(), this);
		pm.registerEvents(new CentralD(), this);
		pm.registerEvents(new LixeiraListener(), this);
		pm.registerEvents(new PersonalizarListener(), this);
		pm.registerEvents(new TOPEVENTOS(), this);
		pm.registerEvents(new SkillsListener(), this);
		pm.registerEvents(new APICommand(), this);
		pm.registerEvents(new MCMMoListener(), this);
		pm.registerEvents(new EnchantListener(), this);
		pm.registerEvents(new AntiBugListener(), this);
		pm.registerEvents(new RankListener(), this);
		pm.registerEvents(new MobsCommand(), this);
		pm.registerEvents(new ImpulsaoListener(), this);
		pm.registerEvents(new PunirListener(), this);
		pm.registerEvents(new PotionsCustomListener(), this);
		pm.registerEvents(new MinaListener(), this);
		pm.registerEvents(new BookCoordenadasListener(), this);
		pm.registerEvents(new RankListener(), this);
		pm.registerEvents(new PowerMaxListener(), this);
		pm.registerEvents(new PowerInsListener(), this);
		pm.registerEvents(new EnderPearlDamageListener(), this);
		pm.registerEvents(new EvScrollListener(), this);
		pm.registerEvents(new PlayerDamangeListener(), this);
		pm.registerEvents(new PlayerDeathListener(), this);
		pm.registerEvents(new PlayerJumpListener(), this);
		pm.registerEvents(new InventoryListener(), this);
		pm.registerEvents(new ManaListener(), this);
		pm.registerEvents(new VidaMListener(), this);
		pm.registerEvents(new MobsListener(), this);
		pm.registerEvents(new MobsMenu(), this);
		pm.registerEvents(new MembroListener(), this);
		pm.registerEvents(new DispenserListener(), this);
		pm.registerEvents(new RunaExtraçãoListener(), this);
		pm.registerEvents(new Evento_Morrer(), this);
		pm.registerEvents(new Evento_StackMorrer(), this);
		pm.registerEvents(new DefensorListener(), this);
		pm.registerEvents(new DevatacaoListener(), this);
		pm.registerEvents(new FoodPotionListener(), this);
		pm.registerEvents(new FragmentoListener(), this);
		pm.registerEvents(new NukeTntExplode(), this);
		pm.registerEvents(new ArmadilhaListener(), this);
		pm.registerEvents(new VarinhaListener(), this);
		pm.registerEvents(new WitherHeadListener(), this);
		pm.registerEvents(new MoedaReparaçãoListener(), this);
		pm.registerEvents(new PurificadorListener(), this);
		pm.registerEvents(new TotemListener(), this);
		pm.registerEvents(new SlimeListener(), this);
		pm.registerEvents(new CreeperListener(), this);
		pm.registerEvents(new IncineradorListener(), this);
		pm.registerEvents(new AgrupadorListener(), this);
		pm.registerEvents(new ReparadorListener(), this);
		pm.registerEvents(new RetrocederListener(), this);
		pm.registerEvents(new PortableCannonListener(), this);
		pm.registerEvents(new VanishListener(), this);
		pm.registerEvents(new DivinaListener(), this);
		pm.registerEvents(new DuvidaListener(), this);
		pm.registerEvents(new AntiDupeListener(), this);
		pm.registerEvents(new IPListener(), this);
		pm.registerEvents(new EletromagnéticoListener(), this);
		pm.registerEvents(new PaperListener(), this);
		pm.registerEvents(new MagaiverListener(), this);
		pm.registerEvents(new BaúsListener(), this);
		pm.registerEvents(new EnchantListener(), this);
		pm.registerEvents(new com.wandy.api.caixas.lendaria.Confirmação(), this);
		pm.registerEvents(new com.wandy.api.caixas.lendaria.Conteúdo(), this);
		pm.registerEvents(new com.wandy.api.caixas.epica.Confirmação(), this);
		pm.registerEvents(new com.wandy.api.caixas.epica.Conteúdo(), this);
		pm.registerEvents(new com.wandy.api.caixas.basica.Confirmação(), this);
		pm.registerEvents(new com.wandy.api.caixas.basica.Conteúdo(), this);
		pm.registerEvents(new com.wandy.api.caixas.astral.Confirmação(), this);
		pm.registerEvents(new com.wandy.api.caixas.astral.Conteúdo(), this);
		pm.registerEvents(new com.wandy.api.caixas.armaduras.Confirmação(), this);
		pm.registerEvents(new com.wandy.api.caixas.armaduras.Conteúdo(), this);
		pm.registerEvents(new com.wandy.api.caixas.spawners.Confirmação(), this);
		pm.registerEvents(new com.wandy.api.caixas.spawners.Conteúdo(), this);
	}

	public static String prefix = "Config.";
	public void setupSQL() {
		if (getConfig().getStringList("bypass") != null) {
			bypass = getConfig().getStringList("bypass");
		}
		String user = getConfig().getString(prefix + "Usuario");
		String password = getConfig().getString(prefix + "Senha");
		String host = getConfig().getString(prefix + "Host");
		String database = getConfig().getString(prefix + "Database");
		sql = new MySQL(user, password, host, database, this);
		sql.startConnection();
	}

	public void salvarConfiguracao() {
		getConfig().set(prefix + "MySQL", Boolean.valueOf(false));
		getConfig().set(prefix + "Usuario", "user");
		getConfig().set(prefix + "Host", "localhost");
		getConfig().set(prefix + "Database", "CoelhoHome");
		getConfig().set(prefix + "Senha", "coelho123");
		saveConfig();
	}

	public static WorldGuardPlugin getWorldGuard() {
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
		if ((plugin == null) || (!(plugin instanceof WorldGuardPlugin))) {
			return null;
		}
		return (WorldGuardPlugin) plugin;
	}

	@EventHandler
	public static void aoChover(WeatherChangeEvent e) {
		if (e.toWeatherState()) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public static void aoNascer(CreatureSpawnEvent e) {
		if (e.getEntity().getType().equals(EntityType.VILLAGER)) {
			e.setCancelled(true);
		}
	}

	public static void sendHeaderAndFooter(Player p, String head, String foot) {
		CraftPlayer craftplayer = (CraftPlayer) p;
		PlayerConnection connection = craftplayer.getHandle().playerConnection;
		IChatBaseComponent header = IChatBaseComponent.ChatSerializer.a("{'color': '', 'text': '" + head + "'}");
		IChatBaseComponent footer = IChatBaseComponent.ChatSerializer.a("{'color': '', 'text': '" + foot + "'}");
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
		try {
			Field headerField = packet.getClass().getDeclaredField("a");
			headerField.setAccessible(true);
			headerField.set(packet, header);
			headerField.setAccessible(!headerField.isAccessible());
			Field footerField = packet.getClass().getDeclaredField("b");
			footerField.setAccessible(true);
			footerField.set(packet, footer);
			footerField.setAccessible(!footerField.isAccessible());
		} catch (Exception e) {
			e.printStackTrace();
		}
		connection.sendPacket(packet);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoClicar(PlayerInteractEvent e) {
		if (!e.isCancelled()) {
			if (e.getPlayer().getItemInHand() != null) {
				if (!e.getPlayer().getItemInHand().getType().equals(Material.AIR)) {
					if (e.getPlayer().getItemInHand().getType().equals(Material.LAVA_BUCKET)) {
						if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
							if (e.getClickedBlock() != null) {
								if (!e.getClickedBlock().getType().equals(Material.AIR)) {
									e.setCancelled(true);
								}
							}
						}
					}
				}
			}
		}
	}

	private void loadChunks() {
		for (int x = 65036; x < 250; ++x) {
			for (int z = 65036; z < 250; ++z) {
				Chunk chunk = Bukkit.getWorld("world").getBlockAt(x, 64, z).getChunk();
				if (!chunk.isLoaded()) {
					chunk.load(true);
				}
			}
		}
	}

	public void connect(Player p, String server) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("Connect");
			out.writeUTF(server);
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.sendPluginMessage(Main.getInstance(), "BungeeCord", b.toByteArray());
	}

	
	  @SuppressWarnings("unused")
	private void alwaysDay() { new BukkitRunnable() { public void run() { for
	  (World world : Bukkit.getWorlds()) { world.setTime(1000L); } }
	  }.runTaskTimerAsynchronously(this, 500L, 8000L); }
	 
	private void isCombat() {
		new BukkitRunnable() {
			public void run() {
				for (Player x : Bukkit.getOnlinePlayers()) {
					if (CombatLogListener.taCombate(x)) {
						EntrarListener.mandarAction(x, "§cVocê está em combate.");
					}
				}
			}
		}.runTaskTimerAsynchronously(this, 40L, 40L);
	}
	
	private void isDefensor() {
		new BukkitRunnable() {
			public void run() {
				for (Player x : Bukkit.getOnlinePlayers()) {
					if (DefensorListener.lista.contains(x.getName())) {
						EntrarListener.mandarAction(x, "§aSeu defensor está ativo.");
					}
				}
			}
		}.runTaskTimerAsynchronously(this, 40L, 40L);
	}
	
	private void isVanish() {
		new BukkitRunnable() {
			public void run() {
				for (Player x : Bukkit.getOnlinePlayers()) {
					if (x.hasPermission("wandy.vanish")) {
						if (VanishCommand.vanish.contains(x.getName())) {
							EntrarListener.mandarAction(x, "§aSua invisíbilidade está ativa.");
						}
					}
				}
			}
		}.runTaskTimerAsynchronously(this, 40L, 40L);
	}
}
