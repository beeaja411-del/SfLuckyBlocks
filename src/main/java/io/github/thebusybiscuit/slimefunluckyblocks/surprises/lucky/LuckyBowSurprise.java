package io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;


public final class LuckyBowSurprise implements Surprise {
	
	private final ItemStack bow;
	
	public LuckyBowSurprise() {
		bow = io.github.thebusybiscuit.slimefunluckyblocks.ItemHelper.create(Material.BOW, "&e&lLucky Bow");
		bow.addUnsafeEnchantment(Enchantment.POWER, 2);
		bow.addUnsafeEnchantment(Enchantment.UNBREAKING, 3);
		bow.addUnsafeEnchantment(Enchantment.PUNCH, 1);
	}
	
	@Override
	public String getName() {
		return "Lucky Bow";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		l.getWorld().dropItemNaturally(l, bow.clone());
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.LUCKY;
	}

}
