package com.wandy.api.anticheat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.event.Listener;

import com.wandy.api.Main;


public class AntiCheat implements Listener{
	
	public static Main plugin;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, String> playerData = new HashMap();

	public AntiCheat(Main instance){
		plugin = instance;
	}

	private File file;
	private Map<String, String> map;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AntiCheat(File file){
		try{
			this.file = file;
			this.map = new HashMap();
			if (!getFile().exists()) {
				this.file.createNewFile();
			}
		}
		catch (Exception erro){
			erro.printStackTrace();
		}
	}

	public Map<String, String> getMap(){
		return this.map;
	}

	public File getFile(){
		return this.file;
	}

	public void set(String key, String value){
		this.map.put(key, value);
	}

	public String get(String key){
		return (String)this.map.get(key);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void reload(){
		try{
			FileInputStream fileInputStream = new FileInputStream(getFile());
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			this.map = ((Map)objectInputStream.readObject());
			objectInputStream.close();
		}
		catch (Exception localException) {}
	}

	public void save(){
		try{
			FileOutputStream fileOutputStream = new FileOutputStream(getFile());
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(getMap());
			objectOutputStream.close();
		}
		catch (Exception erro){
			erro.printStackTrace();
		}
	}
}
