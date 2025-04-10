package org.gomadango0113.tntrun.manager.kit;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface Kit {

    KitManager.KitName getName();
    OfflinePlayer getOfflinePlayer();
    Player getPlayer();
    void resetItem(Material material);

    interface CoolTimeKit {
        int getCoolTime();
        boolean canUse();
    }

}
