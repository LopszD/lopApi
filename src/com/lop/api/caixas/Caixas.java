package com.wandy.api.caixas;

public class Caixas {
	
	//BÁSICA
	//ÉPICA
	//LENDÁRIA
	//SPAWNERS
	//ASTRAL
	
  /*double x = p.getEyeLocation().getX();
	double y = p.getEyeLocation().getY() - 2;
	double z = p.getEyeLocation().getZ();
	float pi = p.getLocation().getPitch();
	float ya = p.getLocation().getPitch();
	ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
	Location locp = new Location(p.getWorld(), x, y, z, pi, ya);
	ArmorStand localArmorStand = (ArmorStand) p.getWorld().spawn(locp, ArmorStand.class);
	localArmorStand.setCustomName("§3§l§kSORTEANDO");
	localArmorStand.setCustomNameVisible(true);
	localArmorStand.setGravity(false);
	localArmorStand.setVisible(false);
	Item ii = p.getWorld().dropItem(p.getLocation(), item);
	ii.setPickupDelay(999999999);
	localArmorStand.setPassenger(ii);
	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
		public void run() {
			ItemStack itemm = inv.getItem(13);
			localArmorStand.setCustomName("§3" + itemm.getAmount() + "x " + itemm.getType().name());
			for (double i = 0; i <= Math.PI; i += Math.PI / 10) {
				double radius = Math.sin(i);
				double y = Math.cos(i);
				for (double a = 0; a < Math.PI * 5; a += Math.PI / 10) {
					double xis = Math.cos(a) * radius;
					double z = Math.sin(a) * radius;
					p.getLocation().add(xis, y, z);
					p.getLocation().getWorld().playEffect(new Location(localArmorStand.getWorld(), localArmorStand.getLocation().getX(), localArmorStand.getLocation().getY() + 1.8, localArmorStand.getLocation().getZ()), Effect.COLOURED_DUST, 5);
					p.getLocation().subtract(xis, y, z);
				}
			}
		}
	}, 200L);
	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
		public void run() {
			localArmorStand.remove();
			ii.remove();
		}
	}, 250L);*/
}
