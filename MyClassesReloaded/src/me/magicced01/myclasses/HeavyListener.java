package me.magicced01.myclasses;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class HeavyListener implements Listener {
	private MC plugin;

	public HeavyListener(MC plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPickRightClick(PlayerInteractEvent e) {
		if (e.hasItem()) {
			if (e.getItem().getType().equals(Material.GOLD_PICKAXE)) {
				if (StatsManager.getKillstreak(e.getPlayer().getName()) >= 0) {
					if (MC.PlayerClassCache.get(e.getPlayer().getName()) == "heavy") {
						Player p = e.getPlayer();
						Vector mult = new Vector(5D, 0D, 5D);
						p.setVelocity(p.getEyeLocation().getDirection().multiply(mult));

						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

							public void run() {
								for (Entity ent : p.getNearbyEntities(5.0D, 5.0D, 5.0D)) {

									Vector vector = new Vector(-ent.getVelocity().getX() - 0.5D, 2.0D,
											-ent.getVelocity().getZ() - 0.5D);
									ent.setVelocity(vector);

								}
								StatsManager.resetKillstreak(p.getName());

							}
						}, 4L);

					} else {
						e.getPlayer().sendMessage("$4Du bist kein Heavy");

					}

				} else {
					e.getPlayer().sendMessage("$4Deine Killstreak ist noch nicht gro� genug");
				}
			}
		}

	}

}
