package io.github.thebusybiscuit.slimefunluckyblocks.surprises.diamond;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;
import io.github.thebusybiscuit.slimefunluckyblocks.ItemHelper;

public class DiamondVeryLuckySurprise implements Surprise {
    private final DiamondSlimefunRainSurprise rainSurprise = new DiamondSlimefunRainSurprise(LuckLevel.DIAMOND_VERY_LUCKY);

    @Override
    public String getName() {
        return "DiamondVeryLuckySurprise";
    }

    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.DIAMOND_VERY_LUCKY;
    }

    private int getNumberOfDrops(Random random) {
        int r = random.nextInt(100);
        if (r < 40) return 1; // 40%
        if (r < 70) return 2; // 30%
        if (r < 85) return 3; // 15%
        if (r < 95) return 4; // 10%
        return 5; // 5%
    }

    @Override
    public void activate(Random random, Player p, Location l) {
        int exclusiveChance = random.nextInt(100);
        if (exclusiveChance == 0) { // 1.0% chance
            rainSurprise.activate(random, p, l);
            return;
        } else if (exclusiveChance >= 1 && exclusiveChance <= 3) { // 3.0% chance
            l.getWorld().dropItemNaturally(l, new ItemStack(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1));
            Material[] trims = {
                Material.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE, Material.VEX_ARMOR_TRIM_SMITHING_TEMPLATE,
                Material.WILD_ARMOR_TRIM_SMITHING_TEMPLATE, Material.COAST_ARMOR_TRIM_SMITHING_TEMPLATE,
                Material.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE, Material.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE,
                Material.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE, Material.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE,
                Material.HOST_ARMOR_TRIM_SMITHING_TEMPLATE, Material.WARD_ARMOR_TRIM_SMITHING_TEMPLATE,
                Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE, Material.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE,
                Material.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE, Material.RIB_ARMOR_TRIM_SMITHING_TEMPLATE,
                Material.EYE_ARMOR_TRIM_SMITHING_TEMPLATE, Material.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE,
                Material.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE, Material.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE
            };
            l.getWorld().dropItemNaturally(l, new ItemStack(trims[random.nextInt(trims.length)], 1));
            return;
        }

        int numDrops = getNumberOfDrops(random);
        
        List<ItemStack> pool = new ArrayList<>();
        
        // Ores (1x)
        Material[] ores = { Material.COAL_ORE, Material.IRON_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE, Material.EMERALD_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.COPPER_ORE };
        for (Material ore : ores) pool.add(new ItemStack(ore, 1));
        
        // Gear
        pool.add(new ItemStack(Material.DIAMOND_BLOCK, 1));
        pool.add(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
        pool.add(new ItemStack(Material.DIAMOND_BOOTS, 1));
        pool.add(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
        pool.add(new ItemStack(Material.DIAMOND_HORSE_ARMOR, 1));
        pool.add(new ItemStack(Material.DIAMOND_SWORD, 1));
        
        Material[] tools = { Material.DIAMOND_PICKAXE, Material.DIAMOND_AXE, Material.DIAMOND_SHOVEL, Material.DIAMOND_HOE };
        pool.add(new ItemStack(tools[random.nextInt(tools.length)], 1));
        
        // Slimefun
        ItemStack synDiaItem = ItemHelper.toItemStack(SlimefunItems.SYNTHETIC_DIAMOND);
        if (synDiaItem != null) {
            synDiaItem.setAmount(3); // buffed to 3x
            pool.add(synDiaItem);
        }
        
        SlimefunItem lb = SlimefunItem.getById("LUCKY_BLOCK");
        if (lb != null) {
            ItemStack lbItem = ItemHelper.toItemStack(lb.getItem());
            if (lbItem != null) {
                lbItem.setAmount(1);
                pool.add(lbItem);
            }
        }
        
        SlimefunItem vlb = SlimefunItem.getById("LUCKY_BLOCK_LUCKY");
        if (vlb != null) {
            ItemStack vlbItem = ItemHelper.toItemStack(vlb.getItem());
            if (vlbItem != null) {
                vlbItem.setAmount(1);
                pool.add(vlbItem);
            }
        }
        
        // New Drops
        pool.add(new ItemStack(Material.NETHERITE_SCRAP, 1)); // 1x
        if (random.nextBoolean()) {
            // Rarer drops (50% chance to be added to pool)
            pool.add(new ItemStack(Material.NETHERITE_INGOT, 1));
            
            SlimefunItem blistering = SlimefunItem.getById("BLISTERING_INGOT_3");
            if (blistering != null) {
                ItemStack blisterItem = ItemHelper.toItemStack(blistering.getItem());
                if (blisterItem != null) {
                    blisterItem.setAmount(1);
                    pool.add(blisterItem);
                }
            } else {
                SlimefunItem fallback = SlimefunItem.getById("BLISTERING_INGOT");
                if (fallback != null) {
                    ItemStack fallbackItem = ItemHelper.toItemStack(fallback.getItem());
                    if (fallbackItem != null) {
                        fallbackItem.setAmount(1);
                        pool.add(fallbackItem);
                    }
                }
            }
        }

        // Pick items
        for (int i = 0; i < numDrops; i++) {
            ItemStack drop = pool.get(random.nextInt(pool.size()));
            l.getWorld().dropItemNaturally(l, drop);
        }
    }
}

