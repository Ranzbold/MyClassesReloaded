package me.magicced01.myclasses;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class JumperListener implements Listener {

	public String lastJumperList = "";

	private MC plugin;
	List<String> jumperlist = new ArrayList<String>();

	public JumperListener(MC plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onFeatherRightClick(PlayerInteractEvent e) {
		if (e.hasItem()) {
			if (e.getItem().getType().equals(Material.FEATHER)) {
				if (MC.PlayerClassCache.get(e.getPlayer().getName()) == "jumper") {
					if (StatsManager.getKillstreak(e.getPlayer().getName()) >= 2) {
						Vector v = new Vector(0D, 70D, 0D);
						Player p = e.getPlayer();
						p.setVelocity(v);
						e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENTITY_WITHER_AMBIENT, 10,
								1);

						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							public void run() {
								if (!jumperlist.contains(e.getPlayer().getName())) {
									jumperlist.add(e.getPlayer().getName());
								}
								Vector v2 = new Vector(5D, 200D, 5D);
								p.setVelocity(p.getEyeLocation().getDirection().multiply(v2));
								e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(),
										Sound.ENTITY_WITHER_SPAWN, 10, 1);

							}
						}, 34L);

					} else {
						e.getPlayer().sendMessage("ß4Deine Killstreak ist noch nicht groﬂ genug");

					}

				} else {
					e.getPlayer().sendMessage("ß4Du bist kein Jumper");
				}

			}
		}

	}

	@EventHandler
	public void onJumperFallDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
				if (MC.PlayerClassCache.get(p.getName()) == "jumper") {
					e.setCancelled(true);


				}
			}

		}

	}
	

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerLand(PlayerMoveEvent e) {

		if (MC.PlayerClassCache.get(e.getPlayer().getName()) == "jumper") {
			if (jumperlist.contains(e.getPlayer().getName())) {
				if (e.getPlayer().isOnGround() == true) {
					for (Entity ent : e.getPlayer().getNearbyEntities(25D, 25D, 25D)) {
						Vector extoent = ent.getLocation().toVector().subtract(e.getPlayer().getLocation().toVector());
						double distance = ent.getLocation().distance(e.getPlayer().getLocation());
						extoent = extoent.multiply(25 / (distance * distance));
						extoent.setY(14D / distance * 0.75);
						e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 5,
								1);
						e.getPlayer().getPlayer().getWorld().playSound(e.getPlayer().getLocation(),
								Sound.ENTITY_LIGHTNING_THUNDER, 5, 1);

						ent.setVelocity(extoent);
						generateShockwave(e.getPlayer().getLocation(), 20, Effect.EXPLOSION_LARGE);

					}

					if (jumperlist.contains(e.getPlayer().getName())) {
						jumperlist.remove(e.getPlayer().getName());
					}
				}
			}
		}

	}

	public void generateShockwave(final Location l, final int radius, Effect effect) {

		// RepeatingScheduler
		final int shock = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			int dist = 1;

			public void run() {
				int posX = l.getBlockX();
				int posZ = l.getBlockZ();
				int posY = l.getBlockY();
				int distanceSquared;

				for (int x = posX - dist; x <= posX + dist; x = x + 2) {
					for (int z = posZ - dist; z <= posZ + dist; z = z + 2) {
						distanceSquared = (posX - x) * (posX - x) + (posZ - z) * (posZ - z);
						if (distanceSquared <= (dist) * (dist) && distanceSquared > (dist - 1) * (dist - 1)) {
							Location loc = new Location(l.getWorld(), x, posY, z);
							l.getWorld().playEffect(loc, effect, 0);
						}
					}
				}
				dist += 5;

			}
		}, 0L, 2L);

		// DelayScheduler
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				Bukkit.getScheduler().cancelTask(shock);
				// Bukkit.broadcastMessage("gecancelt");

			}
		}, 200);

	}

}
