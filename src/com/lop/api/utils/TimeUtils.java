package com.wandy.api.utils;

import java.util.ArrayList;
import java.util.List;

public class TimeUtils {

	static long ano = 31536000000L;
	static long mes = 2592000000L;
	static long dia = 86400000L;
	static long hora = 3600000L;
	static long minuto = 60000L;
	static long segundo = 1000L;

	static String timeExpressions = "SMHDA";

	public static String formatTimeExtense(int time) { // 1 hora 2 minuos 3 segundos
		int dia = (int) (time / (60 * 60 * 24));
		int hor = (int) ((time - (dia * 60 * 60 * 24)) / (60 * 60));
		int min = (int) ((time - (dia * 60 * 60 * 24) - (hor * 60 * 60)) / 60);
		int seg = (int) (time - (dia * 60 * 60 * 24) - (hor * 60 * 60) - (min * 60));
		String txt = "";

		if (dia > 0) {
			if (hor <= 1) {
				txt += dia + " dia ";
			} else {
				txt += dia + " dias ";
			}
		}

		if (hor > 0) {
			if (hor <= 1) {
				txt += hor + " hora ";
			} else {
				txt += hor + " horas ";
			}
		}

		if (min > 0) {
			if (min <= 1) {
				txt += min + " minuto ";
			} else {
				txt += min + " minutos ";
			}
		}

		if (seg > 0) {
			if (seg <= 1) {
				txt += seg + " segundo ";
			} else {
				txt += seg + " segundos ";
			}
		}
		txt = txt.trim();
		if (txt.isEmpty()) {
			txt = "agora";
		}
		return txt;
	}

	public static String formatTimeMinExtense(long time) {// 1h 2m 3s
		int hor = (int) (time / (60 * 60));
		int min = (int) ((time - (hor * 60 * 60)) / 60);
		int seg = (int) (time - (hor * 60 * 60) - (min * 60));
		String txt = "";

		if (hor > 0) {
			txt += hor + "h ";
		}

		if (min > 0) {
			txt += min + "m ";
		}

		if (seg > 0) {
			txt += seg + "s ";
		}
		txt = txt.trim();
		if (txt.isEmpty()) {
			txt = "agora";
		}
		return txt;
	}

	public static String formatTimeNoExtense(long time) {
		int remainder = (int) (time % 3600);
		int minutes = remainder / 60;
		int seconds = remainder % 60;
		String txt = new StringBuilder().append(minutes).append(":").append(seconds).toString();
		if (txt.contains(":") && minutes != 0) {
			if (seconds == 0) {
				txt = txt.split(":")[0] + ":00";
			} else {
				if (seconds > 10) {
					txt = txt.split(":")[0] + ":" + txt.split(":")[1];
				} else {
					txt = txt.split(":")[0] + ":0" + txt.split(":")[1];
				}
			}
		} else {
			txt = seconds + "";
		}
		return txt;
	}

	public static String secondsToMinutes(int time) {
		int minutes = time / 60;
		int seconds = time % 60;
		String disMinu = (minutes < 10 ? "0" : "") + minutes;
		String disSec = (seconds < 10 ? "0" : "") + seconds;
		String formattedTime = disMinu + ":" + disSec;
		return formattedTime;
	}

	public static String secondsToHours(int time) {
		int hours = time / 3600;
		int remainder = time % 3600;
		int minutes = remainder / 60;
		int seconds = remainder % 60;
		String disHour = (hours < 10 ? "0" : "") + hours;
		String disMinu = (minutes < 10 ? "0" : "") + minutes;
		String disSec = (seconds < 10 ? "0" : "") + seconds;
		String formattedTime = disHour + ":" + disMinu + ":" + disSec;
		return formattedTime;
	}

	public static String formalSecondsToHours(int time) {
		int hours = time / 3600;
		int remainder = time % 3600;
		int minutes = remainder / 60;
		int seconds = remainder % 60;
		String disHour = (hours < 10 ? "0" : "") + hours;
		String disMinu = (minutes < 10 ? "0" : "") + minutes;
		String disSec = (seconds < 10 ? "0" : "") + seconds;
		String formattedTime = disHour + "h" + disMinu + "m" + disSec;
		return formattedTime;
	}

	public static int getHours(int seconds) {
		int hours = seconds / 3600;
		return hours;
	}

	public static int getMinutes(int seconds) {
		int remainder = seconds % 3600;
		int minutes = remainder / 60;
		return minutes;
	}

	public static long minutesToLong(int minutes) {
		int seconds = (int) ((System.currentTimeMillis() / 1000) % 60);
		long mnt = ((System.currentTimeMillis() - seconds) / 1000) / 60;
		return mnt;
	}

	public static int longToSeconds(long time) {
		return (int) (time - System.currentTimeMillis()) / 1000;
	}

	public static long secondsToLong(int seconds) {
		return System.currentTimeMillis() + (seconds * 1000);
	}

	public static Long getServerSeconds(int seconds) {
		return new Long(seconds * 1000L);
	}

	public static long chatStringToTime(String txt) {//
		long time = 0;
		List<String> rawTimeStrings = new ArrayList<>();
		String rawTime = "";
		for (int i = 0; i < txt.length(); i++) {
			String c = txt.charAt(i) + "";
			if (c.matches("[0-9]")) {
				rawTime += c;
			} else {
				rawTime += c;
				rawTimeStrings.add(rawTime);
				rawTime = "";
			}
		}
		for (String s : rawTimeStrings) {
			if (s.length() > 1) {
				if (timeExpressions.contains(s.substring(s.length() - 1).toUpperCase())) {
					long t = Integer.parseInt(s.substring(0, s.length() - 1));
					if (s.substring(s.length() - 1).equalsIgnoreCase("s")) {
						t = t * segundo;
					} else if (s.substring(s.length() - 1).equalsIgnoreCase("m")) {
						t = t * minuto;
					} else if (s.substring(s.length() - 1).equalsIgnoreCase("h")) {
						t = t * hora;
					} else if (s.substring(s.length() - 1).equalsIgnoreCase("d")) {
						t = t * dia;
					} else if (s.substring(s.length() - 1).equalsIgnoreCase("a")) {
						t = t * ano;
					}
					time += t;
				}
			}
		}
		return time;
	}

	public static String longToTime(long tempo) {
		String msg = "";
		if (tempo >= ano) {
			int anos = (int) (tempo / ano);
			tempo -= anos * ano;
			msg += anos + " Ano(s) ";
		}
		if (tempo >= mes) {
			int meses = (int) (tempo / mes);
			tempo -= meses * mes;
			msg += meses + " Mês(es) ";
		}
		if (tempo >= dia) {
			int dias = (int) (tempo / dia);
			tempo -= dias * dia;
			msg += dias + " Dia(s) ";
		}
		if (tempo >= hora) {
			int horas = (int) (tempo / hora);
			tempo -= horas * hora;
			msg += horas + " Hora(s) ";
		}
		if (tempo >= minuto) {
			int minutos = (int) (tempo / minuto);
			tempo -= minutos * minuto;
			msg += minutos + " Minuto(s) ";
		}
		if (tempo >= segundo) {
			int segundos = (int) (tempo / segundo);
			tempo -= segundos * segundo;
			msg += segundos + " Segundo(s) ";
		}
		msg = msg.trim();
		if (msg.isEmpty()) {
			msg += "Agora";
		}
		return msg;
	}

}
