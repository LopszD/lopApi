package com.wandy.api.anticheat;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.util.Vector;

public class HackManager implements Listener{

	@EventHandler
	private void onPlayerTeleportEvent(PlayerTeleportEvent Evento) {
		Invalidate(Evento.getPlayer(), 4000L);
	}
	  
	@EventHandler
	private void onPlayerPortalEvent(PlayerPortalEvent Evento) {
		Invalidate(Evento.getPlayer(), 4000L);
	}
	  
	@EventHandler
	private void onEntityDamageEvent(EntityDamageEvent Evento) {
		if ((Evento.getEntity() instanceof Player)) {
			Player player = (Player)Evento.getEntity();
	      
			Invalidate(player, 1000L);
		}
	}
	  
	@EventHandler
	private void onEntityDeathEvent(EntityDeathEvent Evento) {
		if ((Evento instanceof PlayerDeathEvent)) {
			Invalidate((Player)Evento.getEntity(), 5000L);
		}
	}
	  
	@EventHandler
	private void onPlayerLoginEvent(PlayerLoginEvent Evento) {
		Invalidate(Evento.getPlayer(), 5000L);
	}
	  
	@EventHandler
	private void onPlayerRespawnEvent(PlayerRespawnEvent Evento) {
		Invalidate(Evento.getPlayer(), 5000L);
	}
	  
	@EventHandler
	private void onPlayerMoveEvent(PlayerMoveEvent Evento) {
		AddMove(Evento.getPlayer(), Evento.getTo());
	}
	  
	@EventHandler
	private void onPlayerVelocityEvent(PlayerVelocityEvent Evento) {
		int Velocidade = (int)Evento.getVelocity().length();
		Invalidate(Evento.getPlayer(), Velocidade * 1000L + 500L);
	}
	
	public HackManager start;
	public HackManager apex;
	public HackManager end;
	public double height;
	public double fallDistance;
	public double length;
	public double jumpTime;
	public double fallTime;
	public double verticalSpeed;
	public double jumpSpeed;
	public double fallSpeed;
	public double horizontalSpeed;
	public boolean isFloating = false;
	public boolean isOnGround = false;
	public static double nextexpectedY;

	public HackManager(HackManager start, HackManager apex, HackManager end) {
		this.start = start;
		this.apex = apex;
		this.end = end;

		if ((start.location.getY() == apex.location.getY()) && (apex.location.getY() == end.location.getY()) && (!start.isAir) && (!apex.isAir) && (!end.isAir)) {
			this.isOnGround = true;
		}
		this.height = (apex.location.getY() - start.location.getY());
		this.fallDistance = (apex.location.getY() - end.location.getY());
		this.length = (GetHorzDistance(start.location, apex.location) + GetHorzDistance(apex.location, end.location));

		this.time = (long) ((end.time - start.time) / 1000.0D);
		this.jumpTime = ((apex.time - start.time) / 1000.0D);
		this.fallTime = ((end.time - apex.time) / 1000.0D);
		this.jumpSpeed = (this.height / this.jumpTime);
		this.fallSpeed = (this.fallDistance / this.fallTime);
		this.verticalSpeed = ((this.height + this.fallDistance) / this.time);

		this.horizontalSpeed = (this.length / this.time);
 	}
	public static double getNextExpectedY() {
		return nextexpectedY;
	}
 	public static void setNextExpectedY(double d) {
 		nextexpectedY = d;
 	}

 	private double GetHorzDistance(Location a, Location b) {
 		double x = Math.abs(a.getX() - b.getX());
 		double z = Math.abs(a.getZ() - b.getZ());

 		return Math.sqrt(x * x + z * z);
 	}
 	
 	public static HashMap<Player, Location> LastLocation;
	public static HashMap<Player, ArrayList<HackManager>> Moves;
	public static HashMap<Player, Long> InvalidateExpires;
	public static HackManager Instance;
	public static int CheckCounter = 0;
	public static Player LastHacker;
	public static Server server;
  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void AddMove(Player Jogador, Location Local) {
		if ((HackUtils.Flight(Jogador)) && (HackUtils.Speeding(Jogador))) {
			return;
		}
		Long expires = (Long)InvalidateExpires.get(Jogador);
		if ((expires != null) && (expires.longValue() > System.currentTimeMillis())) {
			return;
		}
		if (!Moves.containsKey(Jogador)) {
			Moves.put(Jogador, new ArrayList<>());
		} synchronized ((ArrayList)Moves.get(Jogador)) {
			HackManager tmp97_94 = Instance;
			tmp97_94.getClass();
			((ArrayList)Moves.get(Jogador)).add(new HackManager(Jogador, Local));
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void Invalidate(Player Jogador, long Tempo) {
		if (Moves.containsKey(Jogador)) {
			((ArrayList)Moves.get(Jogador)).clear();
		}
		Tempo += System.currentTimeMillis();
		Long expires = (Long)InvalidateExpires.get(Jogador);
		if ((expires == null) || (expires.longValue() < Tempo)) {
			InvalidateExpires.put(Jogador, Long.valueOf(Tempo));
		}
	}
  
	public static void Clear(Player player) {
		if (Moves.containsKey(player)) {
			Moves.remove(player);
		}
		if (LastLocation.containsKey(player)) {
			LastLocation.remove(player);
		}
	}
  
	public HackManager() {
		LastLocation = new HashMap<>();
		Moves = new HashMap<>();
		InvalidateExpires = new HashMap<>();
    
		Instance = this;
	}
  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void run() {
		for(Player Jogador : Bukkit.getOnlinePlayers()){
			if ((!Moves.containsKey(Jogador)) || (!LastLocation.containsKey(Jogador))) {
				LastLocation.put(Jogador, Jogador.getLocation().clone());
			} else {
				try {
					synchronized ((ArrayList)Moves.get(Jogador)) {
						ArrayList<HackManager> jumps = HackManager.GetJumps((ArrayList)Moves.get(Jogador));
						for (HackManager jump : jumps) {
							int Ping = ((CraftPlayer)Jogador).getHandle().ping;
							if ((!jump.isOnGround) && (!HackUtils.Flight(Jogador))) {
								HackUtils.Hack Fly = HackUtils.Hack.FLY;
								
								if ((jump.height > 5.0D) || (jump.isFloating)) {
									HackUtils.Fly = Fly.getMensagem().replace("nick", Jogador.getDisplayName()).replace("avisos", HackUtils.AvisosFly.get(Jogador) + "").replace("ping", Ping + "");
									if (HackUtils.Fly != null) {
										HackUtils.AvisosFly.put(Jogador, HackUtils.AvisosFly.get(Jogador) + 1);
										HackUtils.sendAntiCheat(HackUtils.Fly);
									}
									HackUtils.Fly = null;
								} else if (((jump.height >= 1.3D) && (!jump.isOnFire)) || (jump.height >= 2.0D)) {
									HackUtils.Fly = Fly.getMensagem().replace("nick", Jogador.getDisplayName()).replace("avisos", HackUtils.AvisosFly.get(Jogador) + "").replace("ping", Ping + "");
									if (HackUtils.Fly != null) {
										HackUtils.AvisosFly.put(Jogador, HackUtils.AvisosFly.get(Jogador) + 1);
										HackUtils.sendAntiCheat(HackUtils.Fly);
									}
									HackUtils.Fly = null;
								}
							}
							if (!HackUtils.Speeding(Jogador)) {
								
								HackUtils.Hack Speed = HackUtils.Hack.SPEED;
								if (((jump.horizontalSpeed > 10.0D) && (jump.time > 0.5D)) || ((jump.horizontalSpeed > 9.0D) && (jump.time > 1.5D))) {
									HackUtils.Speed = Speed.getMensagem().replace("nick", Jogador.getDisplayName()).replace("avisos", HackUtils.AvisosSpeed.get(Jogador) + "").replace("ping", Ping + "");
									if (HackUtils.Speed != null) {
										HackUtils.AvisosSpeed.put(Jogador, HackUtils.AvisosSpeed.get(Jogador) + 1);
										HackUtils.sendAntiCheat(HackUtils.Speed);
									}
									HackUtils.Speed = null;
								}
								else if ((jump.horizontalSpeed > 11.0D) && (jump.time > 0.5D)) {
									HackUtils.Speed = Speed.getMensagem().replace("nick", Jogador.getDisplayName()).replace("avisos", HackUtils.AvisosSpeed.get(Jogador) + "").replace("ping", Ping + "");
									if (HackUtils.Speed != null) {
										HackUtils.AvisosSpeed.put(Jogador, HackUtils.AvisosSpeed.get(Jogador) + 1);
										HackUtils.sendAntiCheat(HackUtils.Speed);
									}
									HackUtils.Speed = null;
								}
							}
						}
						((ArrayList)Moves.get(Jogador)).clear();
					}
					LastLocation.put(Jogador, Jogador.getLocation());
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (Moves.keySet().size() > Bukkit.getMaxPlayers() * 3) {
			Moves.clear();
			Moves = new HashMap();
		}
	}
  
	public String GetLocationString(Location l) {
		return "(" + l.getX() + ", " + l.getY() + ", " + l.getZ() + ")";
	}
  
	public static ArrayList<HackManager> GetJumps(ArrayList<HackManager> moves) {
		int inc = 1;
    
		ArrayList<HackManager> jumps = new ArrayList<>();
		while (inc < moves.size()) {
			if (((HackManager)moves.get(inc)).isInVehicle) {
				return new ArrayList<>();
			}
			int startInc = inc;
			while ((inc < moves.size()) && (!((HackManager)moves.get(inc)).isAir)) {
				inc++;
			}
			if (inc > startInc + 5) {
				HackManager tmp79_76 = Instance;tmp79_76.getClass();HackManager jump = new HackManager((HackManager)moves.get(startInc), (HackManager)moves.get((inc + startInc - 1) / 2), (HackManager)moves.get(inc - 1));
				jump.isOnGround = true;
				jumps.add(jump);
			}
			if (inc >= moves.size()) {
				break;
			}	
			HackManager start = (HackManager)moves.get(inc - 1);
			while ((inc < moves.size()) && (((HackManager)moves.get(inc)).isAir) && (((HackManager)moves.get(inc)).location.getY() > ((HackManager)moves.get(inc - 1)).location.getY())) {
				inc++;
			}
			if (inc >= moves.size()) {
				HackManager tmp235_232 = Instance;tmp235_232.getClass();jumps.add(new HackManager(start, (HackManager)moves.get(inc - 1), (HackManager)moves.get(inc - 1)));
				break;
			}
			HackManager apex = (HackManager)moves.get(inc - 1);
			boolean isFloating = false;
			boolean isOnFire = false;
			int floatCount = 0;
			while ((inc < moves.size()) && (((HackManager)moves.get(inc)).isAir)) {
				if ((((HackManager)moves.get(inc - 1)).location.getY() == ((HackManager)moves.get(inc)).location.getY()) && (!((HackManager)moves.get(inc)).isOnLadder)) {
					floatCount++;
					if (floatCount > 3) {
						isFloating = true;
					}
				} else {
					floatCount = 0;
				}
				if (((HackManager)moves.get(inc)).isOnFire) {
					isOnFire = true;
				}
				inc++;
			}
			HackManager end;
			if (inc >= moves.size()) {
				end = (HackManager)moves.get(moves.size() - 1);
			} else {
				end = (HackManager)moves.get(inc);
			}
			HackManager tmp433_430 = Instance;
			tmp433_430.getClass();
			HackManager jump = new HackManager(start, apex, end);
			jump.isFloating = isFloating;
			jump.isOnFire = isOnFire;
			jumps.add(jump);
		}
		return jumps;
	}
	
	public Player player;
	public long time;
	public Location location;
	public Vector velocity;
	public boolean isSprinting;
	public boolean isSneaking;
	public boolean isAir;
	public boolean isOnFire;
	public boolean isInVehicle;
	public boolean isOnLadder;

	public HackManager(Player Jogador, Location Local) {
		this.player = Jogador;
		this.location = Local.clone();
		this.time = System.currentTimeMillis();
		this.velocity = Jogador.getVelocity();
		this.isSprinting = Jogador.isSprinting();
		this.isSneaking = Jogador.isSneaking();
		this.isInVehicle = Jogador.isInsideVehicle();
		this.isOnFire = (Jogador.getFireTicks() > 0);

		if (Math.abs(this.velocity.getX()) < 0.001D)
			this.velocity.setX(0);
		if (Math.abs(this.velocity.getY()) < 0.001D)
			this.velocity.setY(0);
		if (Math.abs(this.velocity.getZ()) < 0.001D) {
			this.velocity.setZ(0);
		}
		this.isOnLadder = isInBlock(this.location.getBlock().getType());
		this.isAir = isBlockAir(this.location);
	}

	public double Speed(HackManager Mover) {
		if (Mover == null) {
			return 0.0D;
		}
		long Time = Math.abs(this.time - Mover.time);
		double Distancia = this.location.distance(Mover.location);

		return Distancia / Time;
	}

	private boolean isMaterialAir(Material Tipo) {
		return (Tipo == Material.AIR) || (Tipo == Material.TORCH) || (Tipo == Material.REDSTONE_TORCH_OFF) || (Tipo == Material.REDSTONE_TORCH_ON) || (Tipo == Material.SIGN);
	}

	private boolean isInBlock(Material Tipo) {
		return (Tipo != Material.AIR) || (Tipo != Material.TORCH) || (Tipo != Material.REDSTONE_TORCH_OFF) || (Tipo != Material.REDSTONE_TORCH_ON) || (Tipo != Material.SIGN);
	}

	private boolean isBlockAir(Location Local) {
		Location Localização = Local.clone();
		double X = Localização.getX();
		double Y = Localização.getY();
    	double Z = Localização.getZ();

    	Y = Math.floor(Y) - 0.001D;

    	Material Tipo = new Location(Localização.getWorld(), X, Y, Z).getBlock().getType();

    	if (!isMaterialAir(Tipo)) {
    		return false;
    	}
    	boolean Boolean1 = false;
    	boolean Boolean2 = false;
    	boolean Boolean3 = false;
    	boolean Boolean4 = false;

    	if ((int)X != (int)(Y + 0.32D)) {
    		Boolean1 = true;
    		Tipo = new Location(Localização.getWorld(), X + 0.32D, Y, Z).getBlock().getType();

    		if (!isMaterialAir(Tipo)) {
    			return false;
    		}
    	}
    	if ((int)X != (int)(X - 0.32D)) {
    		Boolean2 = true;
    		Tipo = new Location(Localização.getWorld(), X - 0.32D, Y, Z).getBlock().getType();

    		if (!isMaterialAir(Tipo)) {
    			return false;
    		}
    	}
    	if ((int)Z != (int)(Z + 0.32D)) {
    		Boolean3 = true;
    		Tipo = new Location(Localização.getWorld(), X, Y, Z + 0.32D).getBlock().getType();

    		if (!isMaterialAir(Tipo)) {
    			return false;
    		}
    	}
    	if ((int)Z != (int)(Z - 0.32D)) {
    		Boolean4 = true;
    		Tipo = new Location(Localização.getWorld(), X, Y, Z - 0.32D).getBlock().getType();
    		
    		if (!isMaterialAir(Tipo)) {
    			return false;
    		}
    	}
    	if ((Boolean1) && (Boolean3)) {
    		Tipo = new Location(Localização.getWorld(), X + 0.32D, Y, Z + 0.32D).getBlock().getType();

    		if (!isMaterialAir(Tipo)) {
    			return false;
    		}
    	}
    	if ((Boolean1) && (Boolean4)) {
    		Tipo = new Location(Localização.getWorld(), X + 0.32D, Y, Z - 0.32D).getBlock().getType();

    		if (!isMaterialAir(Tipo)) {
    			return false;
    		}
    	}
    	if ((Boolean2) && (Boolean3)) {
    		Tipo = new Location(Localização.getWorld(), X - 0.32D, Y, Z + 0.32D).getBlock().getType();

    		if (!isMaterialAir(Tipo)) {
    			return false;
    		}
    	}
    	if ((Boolean2) && (Boolean4)) {
    		Tipo = new Location(Localização.getWorld(), X - 0.32D, Y, Z - 0.32D).getBlock().getType();

    		if (!isMaterialAir(Tipo)) {
    			return false;
    		}
    	}
    	return true;
	}
}
