package io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;


public final class LuckyPickaxeSurprise implements Surprise {
	
	private final ItemStack pickaxe;
	
	public LuckyPickaxeSurprise() {
		pickaxe = io.github.thebusybiscuit.slimefunluckyblocks.ItemHelper.create(Material.IRON_PICKAXE, "&e&lLucky Pickaxe");
		pickaxe.addUnsafeEnchantment(Enchantment.UNBREAKING, 3);
		pickaxe.addUnsafeEnchantment(Enchantment.EFFICIENCY, 3);
	}
	
	@Override
	public String getName() {
		return "Lucky Pickaxe";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		l.getWorld().dropItemNaturally(l, pickaxe.clone());
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.LUCKY;
	}

}




