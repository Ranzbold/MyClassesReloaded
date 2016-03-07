package me.magicced01.myclasses;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ArcherListener implements Listener {
	private MC plugin;

	public ArcherListener(MC plugin) {
		this.plugin = plugin;
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
							Bukkit.broadcastMessage(shooter.getName()+","+victim.getName());
	
							victim.performCommand("myclasses " + MC.PlayerClassCache.get(victim.getName()));

							shooter.sendMessage("Headshot");
							victim.sendMessage("Headshot!");
							StatsManager.addKills(shooter.getName(), 1);
							StatsManager.addDeaths(victim.getName(), 1);
							Bukkit.broadcastMessage(shooter.getName() + " hat nun "+Integer.toString(StatsManager.getKills(shooter.getName())) + " Kills");
							Bukkit.broadcastMessage(victim.getName()+ " hat nun " + Integer.toString(StatsManager.getDeaths(p.getName())) + " Tode");
							Bukkit.broadcastMessage(shooter.getName()+ " hat nun eine Killstreak von " + Integer.toString(StatsManager.getKillstreak(shooter.getName())) + " Kills");
							e.setCancelled(true);
							Bukkit.broadcastMessage("Test");

							

							
						}

					}
				}
			}
		}

	}

}
