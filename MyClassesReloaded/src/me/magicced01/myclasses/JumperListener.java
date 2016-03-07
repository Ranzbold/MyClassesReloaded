package me.magicced01.myclasses;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
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
					if (StatsManager.getKillstreak(e.getPlayer().getName()) >= 0) {
						Vector v = new Vector(0D, 70D, 0D);
						Player p = e.getPlayer();
						p.setVelocity(v);

						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							public void run() {
								if (!jumperlist.contains(e.getPlayer().getName())) {
									jumperlist.add(e.getPlayer().getName());
								}
								Vector v2 = new Vector(5D, 200D, 5D);
								p.setVelocity(p.getEyeLocation().getDirection().multiply(v2));

							}
						}, 34L);

					} else {
						e.getPlayer().sendMessage("$4Deine Killstreak ist noch nicht groß genug");

					}

				} else {
					e.getPlayer().sendMessage("$4Du bist kein Jumper");
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

	@EventHandler
	public void onPlayerLand(PlayerMoveEvent e) {
		 if (jumperlist.toString() != lastJumperList){
			 Bukkit.broadcastMessage(jumperlist.toString());
			 lastJumperList = jumperlist.toString();
			 
		 }

		if (MC.PlayerClassCache.get(e.getPlayer().getName()) == "jumper") {
			if (jumperlist.contains(e.getPlayer().getName())) {
				if (!((e.getFrom().getBlockY() > e.getTo().getBlockY()) && (e.getPlayer().getLocation().getWorld()
						.getBlockAt(e.getPlayer().getLocation().getBlockX(),
								e.getPlayer().getLocation().getBlockY() - 1, e.getPlayer().getLocation().getBlockZ())
						.getType().equals(Material.AIR)))) {
					Bukkit.broadcastMessage("Gelandet");


					for (Entity ent : e.getPlayer().getNearbyEntities(20D, 20D, 20D)) {
						Vector extoent = ent.getLocation().toVector().subtract(e.getPlayer().getLocation().toVector());
						double distance = ent.getLocation().distance(e.getPlayer().getLocation());
						extoent = extoent.multiply(25 / (distance * distance));
						Vector add = new Vector(0D, 2.5D, 0D);
						extoent.add(add);

						ent.setVelocity(extoent);
						generateShockwave(e.getPlayer().getLocation(), 20);

					}

					Bukkit.broadcastMessage("Ende");
					if(jumperlist.contains(e.getPlayer().getName()))
					{
						jumperlist.remove(e.getPlayer().getName());
					}
				}
			}
		}

	}

	public void generateShockwave(final Location l, final int radius) {

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
							l.getWorld().playEffect(loc, Effect.EXPLOSION_LARGE, 0);
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
