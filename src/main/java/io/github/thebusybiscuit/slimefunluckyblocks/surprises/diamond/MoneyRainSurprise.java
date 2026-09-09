package io.github.thebusybiscuit.slimefunluckyblocks.surprises.diamond;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public class MoneyRainSurprise implements Surprise {

    private final LuckLevel luckLevel;
    
    public MoneyRainSurprise(LuckLevel luckLevel) {
        this.luckLevel = luckLevel;
    }

    @Override
    public String getName() {
        return "Money Rain";
    }

    @Override
    public LuckLevel getLuckLevel() {
        return luckLevel;
    }

    @Override
    public void activate(Random random, Player p, Location l) {
        // Random amount between 50,000 and 300,000
        int amount = random.nextInt(250001) + 50000;
        
        // Use console command to bypass Vault and directly use ExcellentEconomy
        String command = "eco give " + p.getName() + " " + amount;
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
    }
}
