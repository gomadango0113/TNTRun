package org.gomadango0113.tntrun.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.gomadango0113.tntrun.manager.ItemManager;
import org.gomadango0113.tntrun.manager.kit.KitManager;
import org.gomadango0113.tntrun.manager.kit.Speed;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        for (ItemStack itemStack : ItemManager.getKitItemList()) {
            event.getPlayer().getInventory().addItem(itemStack);
            KitManager.addKit(event.getPlayer(), new Speed(event.getPlayer()));
        }
    }
}
