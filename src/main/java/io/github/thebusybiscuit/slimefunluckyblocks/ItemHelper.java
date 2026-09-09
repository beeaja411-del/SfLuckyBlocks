package io.github.thebusybiscuit.slimefunluckyblocks;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import java.net.URL;
import java.util.UUID;
import java.lang.reflect.Method;

public class ItemHelper {
    public static ItemStack create(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColors.color(name));
            item.setItemMeta(meta);
        }
        return item;
    }

    public static ItemStack createTexturedHead(String hash, String name) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColors.color(name));
            try {
                PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());
                PlayerTextures textures = profile.getTextures();
                textures.setSkin(new URL("http://textures.minecraft.net/texture/" + hash));
                profile.setTextures(textures);
                meta.setOwnerProfile(profile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            item.setItemMeta(meta);
        }
        return item;
    }

    public static void setBlockSkin(Block b, String hash) {
        if (!(b.getState() instanceof Skull)) {
            return;
        }
        Skull skull = (Skull) b.getState();
        try {
            PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());
            PlayerTextures textures = profile.getTextures();
            textures.setSkin(new URL("http://textures.minecraft.net/texture/" + hash));
            profile.setTextures(textures);
            skull.setOwnerProfile(profile);
            skull.update(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ItemStack toItemStack(Object obj) {
        if (obj == null) return null;
        
        if (obj instanceof ItemStack) {
            return ((ItemStack) obj).clone();
        }
        
        try {
            Method m = obj.getClass().getMethod("item");
            ItemStack item = (ItemStack) m.invoke(obj);
            if (item != null) {
                return item.clone();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
