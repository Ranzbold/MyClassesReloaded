package me.magicced01.myclasses;

import java.util.HashMap;

import org.bukkit.plugin.Plugin;

public class StatsManager {

	static String CP = "playerstats";
	static Plugin PL = MC.plugin;
	public static HashMap<String,Integer>Killstreaks = new HashMap<String,Integer>();
	

	//Add, Set ,Get Kills
	public static void addKills(String playerid, int count){
		initializePlayerConfig(playerid);
		int kills = PL.getConfig().getInt(CP+"."+playerid+".kills");
		kills+=count;
		PL.getConfig().set(CP+"."+playerid+".kills",kills);
		
		raiseKillstreak(playerid,1);
	}
	
	public static void setKills(String playerid, int count){
		initializePlayerConfig(playerid);
		PL.getConfig().set(CP+"."+playerid+".kills",count);
	}

	public static int getKills(String playerid){
		initializePlayerConfig(playerid);
		return PL.getConfig().getInt(CP+"."+playerid+".kills");
	}
	
	
	//Add, Set, Get Deaths
	public static void addDeaths(String playerid, int count){
		initializePlayerConfig(playerid);
		int deaths = PL.getConfig().getInt(CP+"."+playerid+".deaths");
		deaths+=count;
		PL.getConfig().set(CP+"."+playerid+".deaths",deaths);
		
		resetKillstreak(playerid);
	}
	
	public static void setDeaths(String playerid, int count){
		initializePlayerConfig(playerid);
		PL.getConfig().set(CP+"."+playerid+".deaths",count);
	}

	public static int getDeaths(String playerid){
		initializePlayerConfig(playerid);
		return PL.getConfig().getInt(CP+"."+playerid+".deaths");
	}
	
	
	static //Initialization of Player's config section and killstreak entry
	void initializePlayerConfig(String playerid){
		if (!PL.getConfig().contains(CP+"."+playerid)){
			PL.getConfig().set(CP+"."+playerid+".kills",0);
			PL.getConfig().set(CP+"."+playerid+".deaths",0);
		}
	}
	
	public static void initializePlayerKillstreaks(String playerid){
		if (!Killstreaks.containsKey(playerid)){
			Killstreaks.put(playerid, 0);
		}
	}
	
	//Raise,Reset,Get Killstreaks
	public static void raiseKillstreak(String playerid, int count){
		initializePlayerKillstreaks(playerid);
		int killstreak = Killstreaks.get(playerid);
		killstreak+=count;
		Killstreaks.put(playerid, killstreak);
	}
	
	public static void resetKillstreak(String playerid){
		initializePlayerKillstreaks(playerid);
		Killstreaks.put(playerid, 0);
	}
	
	public static int getKillstreak(String playerid){
		initializePlayerKillstreaks(playerid);
		return Killstreaks.get(playerid);
	}
}
