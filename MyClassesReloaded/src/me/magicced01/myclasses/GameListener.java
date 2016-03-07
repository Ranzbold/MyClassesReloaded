package me.magicced01.myclasses;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class GameListener implements Listener {
	@SuppressWarnings("unused")
	private MC plugin;

	public GameListener(MC plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		Player p = (Player) e.getEntity();
		p.setFoodLevel(20);
		e.setCancelled(true);
	}

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		event.getPlayer().sendMessage("§4You may not drop Items in MyClasses PvP Mode");
		event.setCancelled(true);
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		event.setCancelled(true);
	}

	/*
	 * @EventHandler public void onDamage(EntityDamageByEntityEvent e) {
	 * 
	 * if( e.getEntity() instanceof Player) { Player p = (Player) e.getEntity();
	 * if(p.getHealth() - e.getDamage() < 1) { e.setCancelled(true);
	 * p.performCommand("myclasses " +
	 * MC.PlayerClassCache.get(e.getEntity().getName()));
	 * p.teleport(p.getWorld().getSpawnLocation());
	 * 
	 * p.sendMessage("Du wurdest von "+e.getDamager().getName()+ " getötet" );
	 * Bukkit.broadcastMessage("§6"+e.getEntity().getName()+" §fwurde von "+
	 * "§c"+e.getDamager().getName()+" §fgetötet" ); } } }
	 */
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		e.setKeepInventory(true);
		Player p = e.getEntity().getPlayer();
		if (e.getEntity().getKiller() instanceof Player  && !(p.getKiller().equals(p))) {
			Player killer = (Player) e.getEntity().getKiller();
			StatsManager.addDeaths(p.getName(), 1);
			StatsManager.addKills(killer.getName(), 1);
			Bukkit.broadcastMessage(killer.getName() + " hat nun "
					+ Integer.toString(StatsManager.getKills(killer.getName())) + " Kills");
			Bukkit.broadcastMessage(
					p.getName() + " hat nun " + Integer.toString(StatsManager.getDeaths(p.getName())) + " Tode");
			Bukkit.broadcastMessage(killer.getName() + " hat nun eine Killstreak von "
					+ Integer.toString(StatsManager.getKillstreak(killer.getName())) + " Kills");
			p.performCommand("myclasses " + MC.PlayerClassCache.get(p.getName()));
		} else {
			StatsManager.addDeaths(p.getName(), 1);
			Bukkit.broadcastMessage(
					p.getName() + " hat nun " + Integer.toString(StatsManager.getDeaths(p.getName())) + " Tode");
			p.performCommand("myclasses " + MC.PlayerClassCache.get(p.getName()));
		}

	}

}
