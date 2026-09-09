package io.github.thebusybiscuit.slimefunluckyblocks.surprises.diamond;

import java.util.Random;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import io.github.thebusybiscuit.slimefunluckyblocks.SlimefunLuckyBlocks;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public class TNTRainSurprise implements Surprise {

    private final LuckLevel luckLevel;
    
    public TNTRainSurprise(LuckLevel luckLevel) {
        this.luckLevel = luckLevel;
    }

    @Override
    public String getName() {
        return "TNT Rain";
    }

    @Override
    public LuckLevel getLuckLevel() {
        return luckLevel;
    }

    @Override
    public void activate(Random random, Player p, Location l) {
        SlimefunLuckyBlocks plugin = JavaPlugin.getPlugin(SlimefunLuckyBlocks.class);
        
        for (int i = 0; i < 5; i++) {
            double offsetX = random.nextDouble() * 2 - 1; // -1 to +1
            double offsetZ = random.nextDouble() * 2 - 1;
            Location spawnLoc = p.getLocation().clone().add(offsetX, 3.0, offsetZ);
            
            TNTPrimed tnt = (TNTPrimed) spawnLoc.getWorld().spawnEntity(spawnLoc, EntityType.TNT);
            tnt.setFuseTicks(40 + random.nextInt(20));
            tnt.setMetadata("luckyblock_bypasstnt", new FixedMetadataValue(plugin, true));
        }
    }
}
