package com.wandy.api.utils;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PermissionsAPI {
	
	public static String getPrefix(String name){
//		World world = Bukkit.getWorlds().get(0);
//		String prefix = Factions.getChat().getPlayerPrefix(world, name);
		return "§7" + PermissionsEx.getUser(name).getPrefix();
	}
}
