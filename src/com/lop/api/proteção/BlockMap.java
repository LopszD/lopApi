package com.wandy.api.proteção;

import org.bukkit.block.Block;

class BlockMap {
	
	private Block bloco;
	private int durab;

	public BlockMap(Block bloco, int durabilidade) {
		this.bloco = bloco;
		this.durab = durabilidade;
	}

	public Block getBlock() {
		return this.bloco;
	}

	public int getDurability() {
		return this.durab;
	}

	public void setMinusDurability() {
		this.durab -= 1;
	}

	public void setMinusDurabilitySC() {
		this.durab -= 3;
	}
}
