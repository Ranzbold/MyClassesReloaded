package me.magicced01.myclasses;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;



public class MC extends JavaPlugin {
	public static Map<String, String> PlayerClassCache = new HashMap<String, String>();
	public static Map<String, Boolean> JumperMap = new HashMap<String, Boolean>();

    public static MC plugin;

	public void onEnable() {
	    plugin = this;
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new ArcherListener(this), this);
		pm.registerEvents(new GameListener(this), this);
		pm.registerEvents(new HeavyListener(this), this);
		pm.registerEvents(new JumperListener(this), this);

		Bukkit.getConsoleSender().sendMessage("[MyClasses] Plugin successfully enabled");
		getCommand("myclasses").setExecutor((CommandExecutor) new Command_Kit());
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				saveConfig();			
			}
			
		}, 0, 6000L);
		
		
		
	}

	public void onDisable() {
		saveConfig();
		plugin= null;

	}

}
