package com.wandy.api.sql;

import org.bukkit.Location;

public class LocationsAPI {
	
	private String nome;
	private Location loc;

	public LocationsAPI(String nome, Location loc) {
		this.nome = nome;
		this.loc = loc;
	}

	public String getNome() {
		return this.nome;
	}

	public Location getLocation() {
		return this.loc;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setLocation(Location loc) {
		this.loc = loc;
	}
}
