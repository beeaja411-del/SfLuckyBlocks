package io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

import io.github.thebusybiscuit.slimefunluckyblocks.ItemHelper;

public final class WalshrusSurprise implements Surprise {

    private final ItemStack sword;

    public WalshrusSurprise() {
        sword = io.github.thebusybiscuit.slimefunluckyblocks.ItemHelper.create(Material.GOLDEN_SWORD, "&e&lLucky Sword");
        sword.addUnsafeEnchantment(Enchantment.SHARPNESS, 3);
        sword.addUnsafeEnchantment(Enchantment.LOOTING, 3);
        sword.addUnsafeEnchantment(Enchantment.UNBREAKING, 3);
        sword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
    }

    @Override
    public String getName() {
        return "Walshrus";
    }

    @Override
    public void activate(Random random, Player p, Location l) {
        Zombie zombie = (Zombie) l.getWorld().spawnEntity(l, EntityType.ZOMBIE);
        zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40D);
        zombie.setHealth(40D);

        zombie.getEquipment().setHelmet(ItemHelper.createTexturedHead("c966f0ebd77f1bcd656fa2dc3ef0303e26a6a3de498c3999d39fdcacc5f5ad", "Walshrus"));
        zombie.getEquipment().setHelmetDropChance(0F);

        zombie.getEquipment().setItemInMainHand(sword.clone());
        zombie.getEquipment().setItemInMainHandDropChance(0F);
        zombie.setCanPickupItems(false);
        zombie.setCustomName(ChatColor.translateAlternateColorCodes('&', "&4Walshrus"));
        zombie.setCustomNameVisible(true);
    }

    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.UNLUCKY;
    }

}


