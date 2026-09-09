package io.github.thebusybiscuit.slimefunluckyblocks.surprises.diamond;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public class DiamondUnluckySurprise implements Surprise {
    @Override
    public String getName() {
        return "DiamondUnluckySurprise";
    }

    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.DIAMOND_UNLUCKY;
    }

    private int getNumberOfDrops(Random random) {
        int r = random.nextInt(100);
        if (r < 40) return 1;
        if (r < 70) return 2;
        if (r < 85) return 3;
        if (r < 95) return 4;
        return 5;
    }

    @Override
    public void activate(Random random, Player p, Location l) {
        if (random.nextInt(100) < 60) {
            new DiamondLuckySurprise().activate(random, p, l);
            return;
        }

        int numDrops = getNumberOfDrops(random);
        int entityChoice = random.nextInt(4); // 0-3
        
        for (int i = 0; i < numDrops; i++) {
            int eventType = random.nextInt(3); // 0=items, 1=entities, 2=potions
            
            if (eventType == 0) { // Items
                List<ItemStack> pool = new ArrayList<>();
                pool.add(new ItemStack(Material.ROTTEN_FLESH, 3 + random.nextInt(6)));
                pool.add(new ItemStack(Material.SPIDER_EYE, 4 + random.nextInt(4)));
                pool.add(new ItemStack(Material.PUFFERFISH, 2 + random.nextInt(5)));
                pool.add(new ItemStack(Material.STRING, 1));
                l.getWorld().dropItemNaturally(l, pool.get(random.nextInt(pool.size())));
            } else if (eventType == 1) { // Entities
                if (entityChoice == 0) {
                    for(int j=0; j<(3 + random.nextInt(4)); j++) l.getWorld().spawnEntity(l, EntityType.ZOMBIE);
                } else if (entityChoice == 1) {
                    for(int j=0; j<(3 + random.nextInt(4)); j++) l.getWorld().spawnEntity(l, EntityType.HUSK);
                } else if (entityChoice == 2) {
                    for(int j=0; j<(1 + random.nextInt(2)); j++) {
                        IronGolem golem = (IronGolem) l.getWorld().spawnEntity(l, EntityType.IRON_GOLEM);
                        golem.setTarget(p);
                    }
                } else {
                    for(int j=0; j<(1 + random.nextInt(2)); j++) l.getWorld().spawnEntity(l, EntityType.PHANTOM);
                }
            } else { // Potions
                if (random.nextBoolean()) {
                    ItemStack pot = new ItemStack(Material.SPLASH_POTION);
                    PotionMeta meta = (PotionMeta) pot.getItemMeta();
                    meta.addCustomEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE, 1, 0), true);
                    pot.setItemMeta(meta);
                    for(int j=0; j<(1 + random.nextInt(2)); j++) {
                        ThrownPotion thrown = l.getWorld().spawn(l.clone().add(0, 2, 0), ThrownPotion.class);
                        thrown.setItem(pot);
                    }
                } else {
                    ItemStack lingering = new ItemStack(Material.LINGERING_POTION);
                    PotionMeta lMeta = (PotionMeta) lingering.getItemMeta();
                    lMeta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 20 * 10, 1), true);
                    lingering.setItemMeta(lMeta);
                    ThrownPotion thrown = l.getWorld().spawn(l.clone().add(0, 2, 0), ThrownPotion.class);
                    thrown.setItem(lingering);
                }
            }
        }
    }
}
