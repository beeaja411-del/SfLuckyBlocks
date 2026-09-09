package io.github.thebusybiscuit.slimefunluckyblocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public class SlimeLuckyBlock extends SlimefunItem {

    private final String[] trashDrops = {
        "SIFTED_ORE",
        "GOLD_DUST",
        "COPPER_DUST",
        "TIN_DUST",
        "MAGNESIUM_DUST",
        "LEAD_DUST",
        "IRON_DUST",
        "SILVER_DUST",
        "ZINC_DUST",
        "LEAD_INGOT",
        "MAGNESIUM_INGOT",
        "SILVER_INGOT",
        "ZINC_INGOT"
    };
    
    private final int[] trashAmounts = {
        16, 16, 16, 16, 16, 16, 16, 16, 16, 2, 2, 2, 2
    };

    private final String[] epicDrops = {
        "PAXEL", // FluffyMachines paxel ID
        "REINFORCED_ALLOY_INGOT",
        "HARDENED_METAL_INGOT",
        "URANIUM",
        "SYNTHETIC_DIAMOND",
        "CARBONADO",
        "POWER_CRYSTAL"
    };
    
    private final int[] epicAmounts = {
        1, 8, 16, 8, 4, 8, 16
    };

    private final String[] legendaryDrops = {
        "TITANIUM",
        "REINFORCED_PLATE"
    };

    private final int[] legendaryAmounts = {
        8, 4
    };

    public SlimeLuckyBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(onBlockBreak());
    }

    private void dropSlimefunItem(Player p, Location loc, String[] itemIds, int[] amounts, Random random, String categoryName) {
        int dropIndex = random.nextInt(itemIds.length);
        String id = itemIds[dropIndex];
        int amount = amounts[dropIndex];

        if (id.equals("PAXEL")) {
            org.bukkit.Bukkit.dispatchCommand(org.bukkit.Bukkit.getConsoleSender(), "sf give " + p.getName() + " PAXEL");
        } else {
            SlimefunItem sfItem = SlimefunItem.getById(id);
            if (sfItem != null) {
                ItemStack drop = sfItem.getItem().clone();
                drop.setAmount(amount);
                loc.getWorld().dropItemNaturally(loc, drop);
            } else {
                p.sendMessage(org.bukkit.ChatColor.RED + "Error finding " + categoryName + " item: " + id);
            }
        }
    }

    private BlockBreakHandler onBlockBreak() {
        return new BlockBreakHandler(false, false) {

            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                e.setDropItems(false);
                drops.clear();

                Random random = ThreadLocalRandom.current();
                Player p = e.getPlayer();
                Location loc = e.getBlock().getLocation().add(0.5, 0.5, 0.5);

                int chance = random.nextInt(100) + 1; // 1 to 100
                
                if (chance <= 5) {
                    // Legendary Drop (5%) - 1 item from the legendary list
                    dropSlimefunItem(p, loc, legendaryDrops, legendaryAmounts, random, "legendary");
                } else if (chance <= 15) {
                    // Epic Drop (10%) - 1 item from the epic list
                    dropSlimefunItem(p, loc, epicDrops, epicAmounts, random, "epic");
                } else if (chance <= 65) {
                    // Trash Drop (50%) - 3 to 6 random drops from trash list
                    int dropCount = random.nextInt(4) + 3; // 3 to 6
                    
                    for (int i = 0; i < dropCount; i++) {
                        int dropIndex = random.nextInt(trashDrops.length);
                        SlimefunItem sfItem = SlimefunItem.getById(trashDrops[dropIndex]);
                        
                        if (sfItem != null) {
                            ItemStack drop = sfItem.getItem().clone();
                            drop.setAmount(trashAmounts[dropIndex]);
                            loc.getWorld().dropItemNaturally(loc, drop);
                        }
                    }
                } else {
                    // 35% nothing for now (reserved for Mythic / trap)
                    loc.getWorld().strikeLightning(p.getLocation());
                }
            }
        };
    }

    @Override
    public Collection<ItemStack> getDrops() {
        // Disable any default block drops
        return new ArrayList<>();
    }
}
