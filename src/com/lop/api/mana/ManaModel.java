package com.wandy.api.mana;

public class ManaModel {

	private String name;
	private int mana;

	public ManaModel(String name, int maniacs) {
		this.name = name;
		this.mana = maniacs;
	}

	public String getName() {
		return this.name;
	}

	public int getMana() {
		return this.mana;
	}

	public void addMana(Integer mana) {
		this.mana += mana;
	}

	public void withdrawMana(Integer mana) {
		this.mana -= mana;
	}
}
