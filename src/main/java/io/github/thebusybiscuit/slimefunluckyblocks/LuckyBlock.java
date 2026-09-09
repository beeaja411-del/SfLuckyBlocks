package io.github.thebusybiscuit.slimefunluckyblocks;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public class LuckyBlock extends SlimefunItem {

    private Collection<Surprise> surprises;
    private Predicate<Surprise> predicate;
    private List<Surprise> cachedSurprises;

    public LuckyBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        addItemHandler(onBlockBreak());
    }

    private BlockBreakHandler onBlockBreak() {
        return new BlockBreakHandler(false, false) {

            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                e.setDropItems(false);
                drops.clear();
                
                if (cachedSurprises == null) {
                    cachedSurprises = surprises.stream().filter(predicate).collect(Collectors.toList());
                }

                Random random = ThreadLocalRandom.current();
                Player p = e.getPlayer();
                Location loc = e.getBlock().getLocation();
                Surprise surprise = cachedSurprises.get(random.nextInt(cachedSurprises.size()));
                p.sendMessage(org.bukkit.ChatColor.GOLD + "You just got an event: " + org.bukkit.ChatColor.YELLOW + surprise.getName() + org.bukkit.ChatColor.GOLD + "!");
                surprise.activate(random, p, loc);
            }
        };
    }

    @Override
    public Collection<ItemStack> getDrops() {
        // Disable any drops from Lucky blocks
        return new java.util.ArrayList<>();
    }

    public void register(SlimefunLuckyBlocks plugin, Collection<Surprise> surprises, Predicate<Surprise> predicate) {
        this.surprises = surprises;
        this.predicate = predicate;
        super.register(plugin);
    }

}
