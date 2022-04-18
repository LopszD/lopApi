package com.wandy.api.punish;

public class Punish {

	private int id;
	private String nome;
	private String nick;
	private String tipo;
	private String motivo;
	private String autor;
	private String prova;
	private String data;
	private boolean fim;
	private String dataf;
	private boolean ativo;

	public Punish(int id, String nome, String nick, String tipo, String motivo, String autor, String prova, String data, boolean fim, String dataf, boolean ativo) {
		this.id = id;
		this.nome = nome;
		this.nick = nick;
		this.tipo = tipo;
		this.motivo = motivo;
		this.autor = autor;
		this.prova = prova;
		this.data = data;
		this.fim = fim;
		this.dataf = dataf;
		this.ativo = ativo;
	}

	public int getID() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public String getNick() {
		return this.nick;
	}

	public String getTipo() {
		return this.tipo;
	}

	public String getMotivo() {
		return this.motivo;
	}

	public String getAutor() {
		return this.autor;
	}

	public String getProva() {
		return this.prova;
	}

	public String getData() {
		return this.data;
	}

	public boolean getFim() {
		return this.fim;
	}

	public String getDataF() {
		return this.dataf;
	}

	public boolean getAtivo() {
		return this.ativo;
	}

	public void setID(int valor) {
		this.id = valor;
	}

	public void setNome(String valor) {
		this.nome = valor;
	}

	public void setNick(String valor) {
		this.nick = valor;
	}

	public void setTipo(String valor) {
		this.tipo = valor;
	}

	public void setMotivo(String valor) {
		this.motivo = valor;
	}

	public void setAutor(String valor) {
		this.autor = valor;
	}

	public void setProva(String valor) {
		this.prova = valor;
	}

	public void setData(String valor) {
		this.data = valor;
	}

	public void setFim(boolean valor) {
		this.fim = valor;
	}

	public void setDataF(String valor) {
		this.dataf = valor;
	}

	public void setAtivo(boolean valor) {
		this.ativo = valor;
	}
}
