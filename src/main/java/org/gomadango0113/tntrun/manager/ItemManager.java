package org.gomadango0113.tntrun.manager;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.gomadango0113.tntrun.util.ItemUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ItemManager {

    public static final List<Material> KIT_ITEM_LIST;

    static {
        KIT_ITEM_LIST = Arrays.asList(Material.FEATHER);
    }

    public static ItemStack getKitItem(Material material) {
        if (material == Material.FEATHER) {
            return new ItemUtil(Material.FEATHER).getItemStack("スピードアイテム", Collections.singletonList(""));
        }
        return null;
    }

    public static List<ItemStack> getKitItemList() {
        return KIT_ITEM_LIST.stream().map(ItemManager::getKitItem).collect(Collectors.toCollection(ArrayList::new));
    }
}
