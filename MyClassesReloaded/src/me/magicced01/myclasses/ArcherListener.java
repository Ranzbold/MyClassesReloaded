package me.magicced01.myclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class ArcherListener implements Listener {

	private MC plugin;
	List<Arrow> arrows = new ArrayList<Arrow>();

	public ArcherListener(MC plugin) {
		this.plugin = plugin;
	}

	public static double random() {
		double randomNum = Math.random() * 0.5;
		return randomNum;
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {

		if (e.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow) e.getDamager();
			if (arrow.getShooter() instanceof Player) {
				Player p = (Player) arrow.getShooter();
				if (e.getEntity() instanceof Player) {
					if (MC.PlayerClassCache.get(p.getName()) == "scout") {
						Player shooter = (Player) arrow.getShooter();
						Player victim = (Player) e.getEntity();
						if (shooter.getLocation().distance(victim.getLocation()) >= 25) {
							shooter.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
							Bukkit.broadcastMessage(shooter.getName() + "," + victim.getName());

							victim.performCommand("myclasses " + MC.PlayerClassCache.get(victim.getName()));

							shooter.sendMessage("Headshot");
							victim.sendMessage("Headshot!");
							StatsManager.addKills(shooter.getName(), 1);
							StatsManager.addDeaths(victim.getName(), 1);
							Bukkit.broadcastMessage(shooter.getName() + " hat nun "
									+ Integer.toString(StatsManager.getKills(shooter.getName())) + " Kills");
							Bukkit.broadcastMessage(victim.getName() + " hat nun "
									+ Integer.toString(StatsManager.getDeaths(p.getName())) + " Tode");
							Bukkit.broadcastMessage(shooter.getName() + " hat nun eine Killstreak von "
									+ Integer.toString(StatsManager.getKillstreak(shooter.getName())) + " Kills");
							e.setCancelled(true);
							Bukkit.broadcastMessage("Test");

						}

					}
				}
			}
		}

	}

	@EventHandler
	public void onStringRightClick(PlayerInteractEvent e) {
		if (e.hasItem()) {
			if (e.getItem().getType().equals(Material.STICK)) {
				if (MC.PlayerClassCache.get(e.getPlayer().getName()) == "scout") {
					if (StatsManager.getKillstreak(e.getPlayer().getName()) >= 0) {
						Player p = e.getPlayer();
						Block block = p.getTargetBlock((Set<Material>) null, 50);
						Location loc = block.getLocation().add(0, 100, 0);
						List<Location> locs = new ArrayList<Location>();
						for (double x = -7; x <= 7; x = x + 1) {
							for (double z = -7; z <= 7; z = z + 1) {
								locs.add(loc.clone().add(x + random(), 0, z + random()));
							}
						}
						for (Location arrowSpot : locs)

						{

							Arrow a = (Arrow) arrowSpot.getWorld().spawnEntity(arrowSpot, EntityType.ARROW);
							arrows.add(a);
							a.setVelocity(new Vector(0, -1, 0));
							a.setVelocity(a.getVelocity().multiply(5));
							a.setCritical(true);
							a.setFireTicks(1000);
							
						}
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

							public void run() {
								for (Location arrowSpot : locs)

								{

									Arrow a = (Arrow) arrowSpot.getWorld().spawnEntity(arrowSpot, EntityType.ARROW);
									arrows.add(a);
									a.setVelocity(new Vector(0, -1, 0));
									a.setVelocity(a.getVelocity().multiply(5));
									a.setCritical(true);
									a.setFireTicks(1000);
									
								}
				

							}
						}, 5L);

						

					}
				}
			}

		}

	}

}
