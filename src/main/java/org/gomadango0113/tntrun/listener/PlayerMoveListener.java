package org.gomadango0113.tntrun.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location p_loc = player.getLocation();
        Location tnt_loc = p_loc.subtract(0, 1, 0);
        Block tnt_block = tnt_loc.getBlock();

        if (tnt_block.getType() == Material.TNT) {
            tnt_block.setType(Material.AIR);
        }
    }
}
