package org.gomadango0113.tntrun.manager.kit;

import org.bukkit.OfflinePlayer;

import java.util.*;

public class KitManager {

    private static final Map<OfflinePlayer, List<Kit>> has_kit = new HashMap<>();

    public static List<Kit> getHasKit(OfflinePlayer player) {
        if (!has_kit.containsKey(player)) {
            has_kit.put(player, new ArrayList<>());
        }

        return has_kit.get(player);
    }

    public static Kit getKit(OfflinePlayer player, Class<? extends Kit> kit_class) {
        if (!has_kit.containsKey(player)) {
            has_kit.put(player, new ArrayList<>());
        }

        for (Kit has_kit : has_kit.get(player)) {
            if (kit_class.equals(has_kit.getClass())) {
                return has_kit;
            }
        }
        return null;
    }

    public static void addKit(OfflinePlayer player, Kit... kit) {
        if (!has_kit.containsKey(player)) {
            has_kit.put(player, new ArrayList<>());
        }

        has_kit.get(player).addAll(Arrays.asList(kit));
    }

    public enum KitName {
        SPEED();
    }
}
