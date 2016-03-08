package me.magicced01.myclasses;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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
								for (Entity ent : p.getNearbyEntities(7.0D, 7.0D, 7.0D)) {

									Vector vector = new Vector(-ent.getVelocity().getX() - 0.5D, 2.0D,
											-ent.getVelocity().getZ() - 0.5D);
									ent.setVelocity(vector);

								}
								e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENTITY_BLAZE_HURT,
										5, 1);
								e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(),
										Sound.ENTITY_LIGHTNING_THUNDER, 2, 2);
								StatsManager.resetKillstreak(p.getName());
								p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 2), true);
								p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 600, 4), true);
						

							}
						}, 4L);

					} else {
						e.getPlayer().sendMessage("$4Du bist kein Heavy");

					}

				} else {
					e.getPlayer().sendMessage("$4Deine Killstreak ist noch nicht groﬂ genug");
				}
			}
		}

	}

}
