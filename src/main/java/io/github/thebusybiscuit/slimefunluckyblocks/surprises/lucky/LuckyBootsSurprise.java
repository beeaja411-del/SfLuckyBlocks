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


public final class LuckyBootsSurprise implements Surprise {

    private final ItemStack boots;

    public LuckyBootsSurprise() {
        boots = io.github.thebusybiscuit.slimefunluckyblocks.ItemHelper.create(Material.IRON_BOOTS, "&e&lLucky Boots");
        boots.addUnsafeEnchantment(Enchantment.UNBREAKING, 3);
        boots.addUnsafeEnchantment(Enchantment.PROTECTION, 1);
    }

    @Override
    public String getName() {
        return "Lucky Boots";
    }

    @Override
    public void activate(Random random, Player p, Location l) {
        l.getWorld().dropItemNaturally(l, boots.clone());
    }

    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.LUCKY;
    }

}




