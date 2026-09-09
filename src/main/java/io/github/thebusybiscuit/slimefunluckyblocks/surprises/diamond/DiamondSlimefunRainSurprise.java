package io.github.thebusybiscuit.slimefunluckyblocks.surprises.diamond;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefunluckyblocks.ItemHelper;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public class DiamondSlimefunRainSurprise implements Surprise {
    
    private final LuckLevel level;
    
    public DiamondSlimefunRainSurprise(LuckLevel level) {
        this.level = level;
    }

    @Override
    public String getName() {
        return "Diamond Slimefun Rain";
    }

    @Override
    public LuckLevel getLuckLevel() {
        return level;
    }
    
    private void dropSlimefunItem(Location loc, String id, int amount) {
        SlimefunItem sfItem = SlimefunItem.getById(id);
        if (sfItem != null) {
            ItemStack item = ItemHelper.toItemStack(sfItem.getItem());
            if (item != null) {
                item.setAmount(amount);
                loc.getWorld().dropItemNaturally(loc, item);
            }
        }
    }

    @Override
    public void activate(Random random, Player p, Location l) {
        Location dropLoc = p.getLocation().add(0, 5, 0);
        
        // Try BLISTERING_INGOT_3, fallback to BLISTERING_INGOT if missing
        SlimefunItem blistering = SlimefunItem.getById("BLISTERING_INGOT_3");
        if (blistering != null) {
            dropSlimefunItem(dropLoc, "BLISTERING_INGOT_3", 3);
        } else {
            dropSlimefunItem(dropLoc, "BLISTERING_INGOT", 3);
        }
        
        dropSlimefunItem(dropLoc, "REINFORCED_ALLOY_INGOT", 8);
        dropSlimefunItem(dropLoc, "URANIUM", 8);
        dropSlimefunItem(dropLoc, "COMPRESSED_CARBON", 16);
        dropSlimefunItem(dropLoc, "REDSTONE_ALLOY", 4);
    }
}

