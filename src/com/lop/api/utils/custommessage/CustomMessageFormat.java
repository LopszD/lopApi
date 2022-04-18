package com.wandy.api.utils.custommessage;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.wandy.api.utils.json.JSONObject;


public class CustomMessageFormat {

	private String message;
	private String clickValue;
	private String hoverValue;
	private MessageFormatHoverType hoverType;
	private MessageFormatClickType clickType;
	private List<JSONObject> extra = null;

	public CustomMessageFormat(String arg0) {
		this.message = arg0;
	}

	public CustomMessageFormat setClick(MessageFormatClickType arg0, String arg1) {
		this.clickType = arg0;
		this.clickValue = arg1;
		return this;
	}

	public CustomMessageFormat setHover(MessageFormatHoverType arg0, String arg1) {
		this.hoverType = arg0;
		this.hoverValue = arg1;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public String getFinalMessage() {
		if (extra == null) {
			return message;
		} else {
			String a = message;
			for (JSONObject s : extra) {
				a += s.getString("text");
			}
			return a;
		}
	}

	public void setMessage(String arg0) {
		this.message = arg0;
	}

	public MessageFormatClickType getClickType() {
		return clickType;
	}

	public String getClickValue() {
		return clickValue;
	}

	public MessageFormatHoverType getHoverType() {
		return hoverType;
	}

	public String getHoverValue() {
		return hoverValue;
	}

	public List<JSONObject> getExtra() {
		return extra;
	}

	public CustomMessageFormat send() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			IChatBaseComponent comp = ChatSerializer.a(getJsonFormat());
			sendPacket(p, new PacketPlayOutChat(comp));
		}
		return this;
	}

	public CustomMessageFormat send(Player arg0) {
		IChatBaseComponent comp = ChatSerializer.a(getJsonFormat());
		sendPacket(arg0, new PacketPlayOutChat(comp));
		return this;
	}

	@SuppressWarnings("rawtypes")
	public void sendPacket(Player p, Packet packet) {
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	}

	public CustomMessageFormat append(JSONObject arg0) {
		if (extra == null)
			extra = new ArrayList<>();
		extra.add(arg0);
		return this;
	}

	public JSONObject getJSON() {
		return new JSONObject(getJsonFormat());
	}

	public String getJsonFormat() {
		JSONObject js = new JSONObject();
		js.put("text", message);
		if (clickType != null) {
			JSONObject js2 = new JSONObject();
			js2.put("action", clickType.toString().toLowerCase());
			js2.put("value", clickValue);
			js.put("clickEvent", js2);
		}

		if (hoverType != null) {
			JSONObject js2 = new JSONObject();
			js2.put("action", hoverType.toString().toLowerCase());
			js2.put("value", hoverValue);
			js.put("hoverEvent", js2);
		}

		if (extra != null) {
			js.put("extra", extra.toArray(new JSONObject[extra.size()]));
		}
		return js.toString();
	}

}
