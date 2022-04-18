package com.wandy.api.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.wandy.api.Main;

public class MySQL {
	
	private String user;
	private String host;
	private String database;
	private String password;
	public static Connection connection;
	static Statement statement;

	public MySQL(String user, String password, String host, String database, Main plugin) {
		this.user = user;
		this.password = password;
		this.host = host;
		this.database = database;
	}

	public void startConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.database, this.user, this.password);
			statement = connection.createStatement();
			statement.execute("CREATE TABLE IF NOT EXISTS ContasAPI (Nome VARCHAR(16) NOT NULL, Nick VARCHAR(16) NOT NULL, Prefs VARCHAR(16) NOT NULL, Homes int(16) NOT NULL)");
			statement.execute("CREATE TABLE IF NOT EXISTS Punish (ID int(16) NOT NULL,Nome VARCHAR(16) NOT NULL,Nick VARCHAR(16) NOT NULL,Tipo VARCHAR(16) NOT NULL,Motivo VARCHAR(64) NOT NULL,Autor VARCHAR(16) NOT NULL,Prova VARCHAR(100) NOT NULL,Data VARCHAR(16) NOT NULL,Fim VARCHAR(100) NOT NULL,DataF VARCHAR(100) NOT NULL,Ativo VARCHAR(16) NOT NULL)");
			statement.execute("CREATE TABLE IF NOT EXISTS TempChunks (World VARCHAR(16) NOT NULL, X int(16) NOT NULL, Z int(16) NOT NULL)");
			statement.execute("CREATE TABLE IF NOT EXISTS BoosterDrop (Nome VARCHAR(16) NOT NULL, Nick VARCHAR(16) NOT NULL, Data VARCHAR(16) NOT NULL, Tipo VARCHAR(16) NOT NULL)");
			statement.execute("CREATE TABLE IF NOT EXISTS BoosterExp (Nome VARCHAR(16) NOT NULL, Nick VARCHAR(16) NOT NULL, Data VARCHAR(16) NOT NULL, Tipo VARCHAR(16) NOT NULL)");
			statement.execute("CREATE TABLE IF NOT EXISTS DuvidasAPI (Nome VARCHAR(16) NOT NULL,Nick VARCHAR(16) NOT NULL,Titulo VARCHAR(16) NOT NULL,Mensagem VARCHAR(100) NOT NULL,CDATA VARCHAR(100) NOT NULL,Respondeu VARCHAR(16) NOT NULL,Resposta VARCHAR(100) NOT NULL,RDATA VARCHAR(100) NOT NULL,Respondido VARCHAR(16) NOT NULL)");
			statement.execute("CREATE TABLE IF NOT EXISTS Wandy_VIPs (Nome VARCHAR(16) NOT NULL,VIP_ID int(16) NOT NULL,ID_PURCHASE int(16) NOT NULL,Data VARCHAR(100) NOT NULL)");
			statement.execute("CREATE TABLE IF NOT EXISTS HomesAPI (Dono VARCHAR(16) NOT NULL, Nome VARCHAR(16) NOT NULL, Nomereal VARCHAR(16) NOT NULL,Publica VARCHAR(16) NOT NULL, Location VARCHAR(100) NOT NULL)");
			statement.execute("CREATE TABLE IF NOT EXISTS ManutencaoGlobal (PORTA int(16) NOT NULL, VALOR VARCHAR(100) NOT NULL)");
			statement.execute("CREATE TABLE IF NOT EXISTS LocationsAPI (Nome VARCHAR(16) NOT NULL,Location VARCHAR(100) NOT NULL)");
			statement.execute("CREATE TABLE IF NOT EXISTS Geradores (Block VARCHAR(100) NOT NULL,Faction VARCHAR(16) NOT NULL)");
			statement.execute("CREATE TABLE IF NOT EXISTS KDR (Nome VARCHAR(16) NOT NULL, KDR Double NOT NULL, Abates int(16) NOT NULL, CivilA int(16) NOT NULL, NeutroA int(16) NOT NULL, RivalA int(16) NOT NULL, CivilM int(16) NOT NULL, NeutroM int(16) NOT NULL, RivalM int(16) NOT NULL, Mortes int(16) NOT NULL)");
			statement.execute("CREATE TABLE IF NOT EXISTS WandyEventos (Nome VARCHAR(16) NOT NULL, WGUERRA int(32) NOT NULL, AGUERRA int(32) NOT NULL, MGUERRA int(32) NOT NULL, PGUERRA int(32) NOT NULL, WX1 int(32) NOT NULL, AX1 int(32) NOT NULL, MX1 int(32) NOT NULL, PX1 int(32) NOT NULL)");
			statement.execute("CREATE TABLE IF NOT EXISTS WandyMobs (Location VARCHAR(64) NOT NULL, Data VARCHAR(64) NOT NULL)");
			statement.execute("CREATE TABLE IF NOT EXISTS WandyReports (Nome VARCHAR(16) NOT NULL, Aimbot int(16) NOT NULL, AutoArmor int(16) NOT NULL, ChestFinder int(16) NOT NULL, ClienteAlternativo int(16) NOT NULL, Critical int(16) NOT NULL, FastPlace int(16) NOT NULL, Fly int(16) NOT NULL, ForceField int(16) NOT NULL, NoFall int(16) NOT NULL, NoSlow int(16) NOT NULL, Regen int(16) NOT NULL, Repulsao int(32) NOT NULL, Speed int(16) NOT NULL, Wall int(16) NOT NULL, XRay int(16) NOT NULL, Total int(16) NOT NULL)");
			statement.execute("CREATE TABLE IF NOT EXISTS WandyHabilidades (User VARCHAR(16) NOT NULL, Active VARCHAR(16) NOT NULL, Habilidades LONGTEXT NOT NULL)");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
