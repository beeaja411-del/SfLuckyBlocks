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


public final class LuckyLeggingsSurprise implements Surprise {

    private final ItemStack leggings;

    public LuckyLeggingsSurprise() {
        leggings = io.github.thebusybiscuit.slimefunluckyblocks.ItemHelper.create(Material.IRON_LEGGINGS, "&e&lLucky Leggings");
        leggings.addUnsafeEnchantment(Enchantment.UNBREAKING, 3);
        leggings.addUnsafeEnchantment(Enchantment.PROTECTION, 1);
    }

    @Override
    public String getName() {
        return "Lucky Leggings";
    }

    @Override
    public void activate(Random random, Player p, Location l) {
        l.getWorld().dropItemNaturally(l, leggings.clone());
    }

    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.LUCKY;
    }

}




