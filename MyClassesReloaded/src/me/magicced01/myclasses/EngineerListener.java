package me.magicced01.myclasses;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EngineerListener implements Listener {
	private MC plugin;
	private HashMap<String, Dispenser> turrets = new HashMap<String, Dispenser>();

	public EngineerListener(MC plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerPlaceBlock(BlockPlaceEvent e) {
		Player p = (Player) e.getPlayer();
		if (MC.PlayerClassCache.get(e.getPlayer().getName()) == "engineer") {
			if (e.getItemInHand().getType().equals(Material.DISPENSER)) {
				if (!e.getBlockAgainst().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType()
						.equals(Material.AIR)) {
					p.sendMessage("$cYou can't place your turret here!");
					e.setCancelled(true);
					return;
				}
				if (!turrets.containsKey(p.getName())) {
					Block pb = e.getBlock();
					pb.setType(Material.FENCE);
					pb.getRelative(BlockFace.UP).setType(Material.AIR);
					pb.getRelative(BlockFace.UP).setType(Material.DISPENSER);
					turrets.put(p.getName(), (Dispenser) pb.getRelative(BlockFace.UP).getState());
					e.setCancelled(false);

				} else {
					p.sendMessage("§c You have already set a turret!");
					e.setCancelled(true);
				}

			}

		}

	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if ((e.getAction() == Action.LEFT_CLICK_BLOCK) || (e.getAction() == Action.LEFT_CLICK_AIR)) {
			Player p = e.getPlayer();
			if (e.getItem().getType().equals(Material.STICK)) {
				if (MC.PlayerClassCache.get(e.getPlayer().getName()) == "engineer") {
					if (turrets.containsKey(p.getName())) {
						Dispenser dispenser = (Dispenser) this.turrets.get(p.getName());
						if (p.getLocation().distance(dispenser.getLocation()) > 8.0D) {
							p.sendMessage("§cYou have to be at least 8 blocks away from your turret!");
						} else if (!dispenser.getInventory().contains(Material.ARROW)) {
							p.sendMessage("§cYour turret is out of ammo!");
						} else {
							Arrow a = dispenser.getWorld()
									.spawnArrow(
											dispenser.getLocation().add(p.getLocation().getDirection().multiply(1.4D)),
											p.getLocation().getDirection()
													.add(p.getLocation().getDirection().normalize()).multiply(10),
											0.8F, 12.0F);
							a.setShooter(p);
							a.setVelocity(a.getVelocity().multiply(3));
							a.setCritical(true);
							dispenser.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.ARROW, 1) });
						}
					}

				}

			}

		}

	}

}
