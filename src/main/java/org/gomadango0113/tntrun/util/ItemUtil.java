package org.gomadango0113.tntrun.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemUtil {

    private ItemStack itemstack;

    public ItemUtil(Material material) {
        this.itemstack = new ItemStack(material);
    }

    public ItemUtil(Material material, int amount) {
        this.itemstack = new ItemStack(material, amount);
    }

    public ItemUtil(ItemStack itemStack) {
        this.itemstack = itemStack;
    }

    public ItemStack getItemStack(String name, List<String> lore) {
        ItemMeta meta = itemstack.getItemMeta();
        List<String> get_lore = meta.getLore() == null ? new ArrayList<>() : new ArrayList<>(meta.getLore());
        if (lore != null) {
            get_lore.addAll(lore);
        }
        setNameLore(itemstack, name, get_lore, true);

        return itemstack;
    }

    public ItemStack getRawItemStack() {
        return itemstack;
    }

    public ItemUtil enableTag(ItemTag itemTag) {
        if (itemTag == ItemTag.KIT_ITEM) {
            ItemMeta meta = itemstack.getItemMeta();
            List<String> lore = meta.getLore() == null ? new ArrayList<>() : new ArrayList<>(meta.getLore());
            lore.add(ChatColor.GOLD + "キットアイテム");
            setNameLore(itemstack, meta.getDisplayName(), lore, true);

            return this;
        }

        return this;
    }

    public static boolean isItemTag(ItemStack item, ItemTag itemTag) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            List<String> lore = meta.getLore();
            if (lore != null) {
                if (itemTag == ItemTag.KIT_ITEM) {
                    return lore.contains(ChatColor.GOLD + "キットアイテム");
                }
            }
        }

        return false;
    }

    public static ItemStack setNameLore(ItemStack itemStack, String name, List<String> lore, boolean lore_add) {
        ItemMeta meta = itemStack.getItemMeta();
        if (name != null) meta.setDisplayName(name);
        if (lore_add) {
            List<String> new_lore = meta.getLore() == null ? new ArrayList<>() : new ArrayList<>(meta.getLore());
            if (lore != null) new_lore.addAll(lore);
            meta.setLore(new_lore);
        }
        else {
            if (lore != null) meta.setLore(lore);
        }
        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public static ItemStack setItemInventory(PlayerInventory inventory, ItemTag itemTag, Material search_item, String name, List<String> lore) {
        for (ItemStack itemStack : inventory.getContents()) {
            if (itemStack != null) {
                ItemMeta meta = itemStack.getItemMeta();
                if (itemStack.getType() == search_item) {
                    setNameLore(itemStack, name, lore, true);
                }
            }
        }
        return null;
    }

    public enum ItemTag {
        KIT_ITEM
    }

}
