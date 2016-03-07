package me.magicced01.myclasses;

import java.util.ConcurrentModificationException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.minecraft.server.v1_9_R1.PacketPlayInClientCommand;
import net.minecraft.server.v1_9_R1.PacketPlayInClientCommand.EnumClientCommand;


public class Equipper {
	private static void playerRespawn(Player p) {
		try {
			for (Player onlineplayers : Bukkit.getOnlinePlayers()) {
				if (onlineplayers.isDead()) {
					((CraftPlayer) p).getHandle().playerConnection
							.a(new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN));
				}
			}
		} catch (ConcurrentModificationException e1) {

		}
		p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
		p.setHealth(20);
		p.setFoodLevel(20);
		p.getInventory().clear();
		for (PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}
		p.teleport(p.getWorld().getSpawnLocation());
		p.setFireTicks(0);
		System.out.println("hallo");

	}

	public static void barbarian(Player p) {
		playerRespawn(p);
		int effectidwarrior = 16;
		int enchantmentlevelwarrior = 2;
		ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
		ItemStack helmet = new ItemStack(Material.IRON_HELMET);
		ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack leggins = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack boots = new ItemStack(Material.IRON_BOOTS);
		Enchantment myEnchantment = new EnchantmentWrapper(effectidwarrior);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§4Stinger");
		item.setItemMeta(meta);
		item.addEnchantment(myEnchantment, enchantmentlevelwarrior);
		p.getInventory().addItem(new ItemStack[] { item });
		p.getInventory().setArmorContents(new ItemStack[p.getInventory().getArmorContents().length]);
		p.getInventory().setHelmet(new ItemStack(helmet));
		p.getInventory().setChestplate(new ItemStack(chestplate));
		p.getInventory().setLeggings(new ItemStack(leggins));
		p.getInventory().setBoots(new ItemStack(boots));

	}
		
	public static void heavy(Player p) {

		playerRespawn(p);
		

		int knockbackid = 19;
		int knockbacklevel = 1;
		int unbreakingid = 34;
		int unbreakinglevel = 3;

		Enchantment knockback = new EnchantmentWrapper(knockbackid);
		Enchantment unbreaking = new EnchantmentWrapper(unbreakingid);

		p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 50000, 4), true);
		p.setHealth(40);

		ItemStack helmet = new ItemStack(Material.IRON_HELMET);
		ItemMeta metahelmet = helmet.getItemMeta();
		metahelmet.setDisplayName("§6§lHelm der Stärke");
		helmet.setItemMeta(metahelmet);

		ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta metachestplate = chestplate.getItemMeta();
		metachestplate.setDisplayName("§6§lStabiler Dornenharnisch");
		chestplate.setItemMeta(metachestplate);

		ItemStack leggins = new ItemStack(Material.IRON_LEGGINGS);
		ItemMeta metaleggins = leggins.getItemMeta();
		metaleggins.setDisplayName("§6§lRobuste Hose");
		leggins.setItemMeta(metaleggins);

		ItemStack boots = new ItemStack(Material.IRON_BOOTS);
		ItemMeta metaboots = boots.getItemMeta();
		metaboots.setDisplayName("§6§l Massive Stiefel");
		boots.setItemMeta(metaboots);

		ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
		ItemMeta metaaxe = axe.getItemMeta();
		metaaxe.setDisplayName("§6§lUrziel der Brecher");
		axe.setItemMeta(metaaxe);

		ItemStack shield = new ItemStack(Material.SHIELD);
		ItemMeta metashield = shield.getItemMeta();
		shield.setDurability((short) 10);
		metashield.setDisplayName("Mithrilschild");
		
		ItemStack goldpick = new ItemStack(Material.GOLD_PICKAXE);
		ItemMeta metapick = goldpick.getItemMeta();
		metapick.setDisplayName("§6§lCHARGE!");
		goldpick.setItemMeta(metapick);


		

		axe.addUnsafeEnchantment(knockback, knockbacklevel);
		axe.addEnchantment(unbreaking, unbreakinglevel);

		p.getInventory().setArmorContents(new ItemStack[p.getInventory().getArmorContents().length]);
		p.getInventory().setHelmet(new ItemStack(helmet));
		p.getInventory().setChestplate(new ItemStack(chestplate));
		p.getInventory().setLeggings(new ItemStack(leggins));
		p.getInventory().setBoots(new ItemStack(boots));
		p.getInventory().addItem(new ItemStack[] { axe });
		p.getInventory().setItemInOffHand(shield);
		p.getInventory().addItem(new ItemStack[] { goldpick });

		

	}

	public static void scout(Player p) {
		playerRespawn(p);
		int effectidarcher1 = 48;
		int enchlevelarcher1 = 2;

		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 50000, 0), true);
		Enchantment ench1 = new EnchantmentWrapper(effectidarcher1);
		p.sendMessage("You have chosen the Class Scout!");
		ItemStack helmet = new ItemStack(Material.CHAINMAIL_HELMET);
		ItemStack chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemStack leggins = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS);
		ItemStack arrows = new ItemStack(Material.ARROW, 32);
		ItemStack item = new ItemStack(Material.BOW);
		ItemMeta meta = item.getItemMeta();
		ItemStack sword = new ItemStack(Material.WOOD_SWORD);
		meta.setDisplayName("§4Bone Crusher");
		item.setItemMeta(meta);
		item.addEnchantment(ench1, enchlevelarcher1);
		item.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 5);
		p.getInventory().addItem(new ItemStack[] { sword });
		p.getInventory().addItem(new ItemStack[] { item });
		p.getInventory().addItem(new ItemStack[] { arrows });
		p.getInventory().setArmorContents(new ItemStack[p.getInventory().getArmorContents().length]);
		p.getInventory().setHelmet(new ItemStack(helmet));
		p.getInventory().setChestplate(new ItemStack(chestplate));
		p.getInventory().setLeggings(new ItemStack(leggins));
		p.getInventory().setBoots(new ItemStack(boots));
	}

	public static void jumper(Player p) {
		playerRespawn(p);
		p.sendMessage("You have chosen the Class Jumper!");

		int effectid = 16;
		int effectlvl = 2;
		Enchantment swordenchant = new EnchantmentWrapper(effectid);
		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		ItemStack helmet = new ItemStack(Material.IRON_HELMET);
		ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack boots = new ItemStack(Material.IRON_BOOTS);
		ItemStack feather = new ItemStack(Material.FEATHER);
		ItemMeta meta = sword.getItemMeta();
		meta.setDisplayName("§4§lSteel Sword");
		sword.setItemMeta(meta);
		sword.addEnchantment(swordenchant, effectlvl);
		ItemMeta feathermeta = feather.getItemMeta();
		feathermeta.setDisplayName("§6§lASCEND!");				
		p.getInventory().setArmorContents(new ItemStack[p.getInventory().getArmorContents().length]);
		p.getInventory().setHelmet(new ItemStack(helmet));
		p.getInventory().setChestplate(new ItemStack(chestplate));
		p.getInventory().setLeggings(new ItemStack(leggings));
		p.getInventory().setBoots(new ItemStack(boots));
		p.getInventory().addItem(new ItemStack[] { sword });
		p.getInventory().addItem(new ItemStack[] { feather });
		p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 50000, 14), true);

	}

	public static void ninja(Player p) {
		playerRespawn(p);
		p.sendMessage("You have chosen the Class Ninja!");
		int swordid = 16;
		int swordlevel = 6;
		Enchantment swordenchant = new EnchantmentWrapper(swordid);
		ItemStack sword = new ItemStack(Material.GOLD_SWORD);
		ItemStack boots = new ItemStack(Material.GOLD_BOOTS);
		ItemMeta meta = sword.getItemMeta();
		meta.setDisplayName("§bAngelic Dagger of massive Destrcution");
		sword.setItemMeta(meta);
		sword.addUnsafeEnchantment(swordenchant, swordlevel);
		ItemStack enderpearls = new ItemStack(Material.ENDER_PEARL, 4);
		p.getInventory().addItem(new ItemStack[] { sword });
		p.getInventory().setArmorContents(new ItemStack[p.getInventory().getArmorContents().length]);
		p.getInventory().setBoots(new ItemStack(boots));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 50000, 2), true);
		p.getInventory().addItem(new ItemStack[] { enderpearls });
	}

	public static void pyro(Player p) {
		playerRespawn(p);
		int swordenchantid = 20;
		int swordlvl = 2;
		int bowenchantid = 50;
		int bowlvl = 1;
		p.sendMessage("You have chosen the Class Pyro!");
		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
		ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		ItemStack arrows = new ItemStack(Material.ARROW, 32);
		ItemStack item = new ItemStack(Material.BOW);
		Enchantment swordenchant = new EnchantmentWrapper(swordenchantid);
		Enchantment bowenchant = new EnchantmentWrapper(bowenchantid);
		ItemMeta meta = sword.getItemMeta();
		meta.setDisplayName("§6Body Melter");
		sword.setItemMeta(meta);
		sword.addUnsafeEnchantment(swordenchant, swordlvl);
		ItemMeta meta2 = item.getItemMeta();
		meta2.setDisplayName("§cFiery Bow of Torture");

		item.setItemMeta(meta2);
		item.addUnsafeEnchantment(bowenchant, bowlvl);
		p.getInventory().setArmorContents(new ItemStack[p.getInventory().getArmorContents().length]);
		p.getInventory().setHelmet(new ItemStack(helmet));
		p.getInventory().setChestplate(new ItemStack(chestplate));
		p.getInventory().setLeggings(new ItemStack(leggings));
		p.getInventory().setBoots(new ItemStack(boots));
		p.getInventory().addItem(new ItemStack[] { sword });
		p.getInventory().addItem(new ItemStack[] { item });
		p.getInventory().addItem(new ItemStack[] { arrows });

	}

}
