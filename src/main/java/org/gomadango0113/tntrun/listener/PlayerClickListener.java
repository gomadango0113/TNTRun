package org.gomadango0113.tntrun.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.gomadango0113.tntrun.TNTRun;
import org.gomadango0113.tntrun.util.ChatUtil;

public class PlayerClickListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item.getType() == Material.FEATHER) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*3, 0));
            ChatUtil.sendMessage(player, "スピードが3秒付与されました。");
        }
    }
}
