package me.magicced01.myclasses;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class NinjaListener implements Listener {
	@SuppressWarnings("unused")
	private MC plugin;

	public NinjaListener(MC plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onFlintClick(PlayerInteractEvent e) {
		if (e.hasItem()) {
			if (e.getItem().getType().equals(Material.FLINT)) {
				if (MC.PlayerClassCache.get(e.getPlayer().getName()) == "ninja") {
					if (StatsManager.getKillstreak(e.getPlayer().getName()) >= 0) {
					}
				}
			}
		}
	}
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e)
	{
	       if(e.getDamager().getType().equals(EntityType.ENDER_PEARL))
	       {
	       e.setCancelled(true);
	       }
	}

}
