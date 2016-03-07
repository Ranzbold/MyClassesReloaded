package me.magicced01.myclasses;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Kit implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if ((sender instanceof Player)) {
			Player p = (Player) sender;
			if (cmd.getName().equals("myclasses")) {
				if (args.length == 0) {
					p.sendMessage("Gib /myclasses list für die Liste aller Klassen ein");
				}
				if (args.length == 1) {
					if (args[0].equals("list")) {
						p.sendMessage("===§6§"
								+ "lMYCLASSES§"
								+ "f===");
						p.sendMessage("§9/barbarian");
						p.sendMessage("§9/scout");
						p.sendMessage("§9/heavy");
						p.sendMessage("§9/pyro");
						p.sendMessage("§9/ninja");
						p.sendMessage("§9/jumper");

					}
					if (args[0].equals("barbarian")) {
						Equipper.barbarian(p);
						startClassAllocation(p, "barbarian");

					}
					if (args[0].equals("scout")) {
						Equipper.scout(p);
						startClassAllocation(p, "scout");

					}
					if (args[0].equals("heavy")) {
						Equipper.heavy(p);
						startClassAllocation(p, "heavy");

					}
					if (args[0].equals("pyro")) {
						Equipper.pyro(p);
						startClassAllocation(p, "pyro");

					}
					if (args[0].equals("ninja")) {
						Equipper.ninja(p);
						startClassAllocation(p, "ninja");

					}
					if (args[0].equals("jumper")) {
						Equipper.jumper(p);
						startClassAllocation(p, "jumper");

					}
				}
			}
		}
		return false;
	}

	public static void startClassAllocation(Player p, String classname) {
		if (MC.PlayerClassCache.containsKey(p.getName())) {
			MC.PlayerClassCache.remove(p.getName());

		}
		MC.PlayerClassCache.put(p.getName(), classname);

	}

}
