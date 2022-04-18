package com.wandy.api.punish;

public class Punicao {
	
	private String nome;
	private int id;
	private String autor;
	private String prova;
	private String dataf;
	private String data;
	private String motivo;

	public Punicao(String no, int id, String au, String pro, String df, String d, String mo) {
		this.nome = no;
		this.autor = au;
		this.prova = pro;
		this.dataf = df;
		this.data = d;
		this.motivo = mo;
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public String getAutor() {
		return this.autor;
	}

	public String getProva() {
		return this.prova;
	}

	public String getMotivo() {
		return this.motivo;
	}

	public String getDataF() {
		return this.dataf;
	}

	public String getData() {
		return this.data;
	}

	public int getID() {
		return this.id;
	}

	public boolean hasProva() {
		if (this.prova != "N") {
			return true;
		}
		return false;
	}
}
