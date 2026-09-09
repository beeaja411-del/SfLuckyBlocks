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


public final class LuckyChestplateSurprise implements Surprise {

    private final ItemStack chestplate;

    public LuckyChestplateSurprise() {
        chestplate = io.github.thebusybiscuit.slimefunluckyblocks.ItemHelper.create(Material.IRON_CHESTPLATE, "&e&lLucky Chestplate");
        chestplate.addUnsafeEnchantment(Enchantment.UNBREAKING, 3);
        chestplate.addUnsafeEnchantment(Enchantment.PROTECTION, 1);
    }

    @Override
    public String getName() {
        return "Lucky Chestplate";
    }

    @Override
    public void activate(Random random, Player p, Location l) {
        l.getWorld().dropItemNaturally(l, chestplate.clone());
    }

    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.LUCKY;
    }

}




