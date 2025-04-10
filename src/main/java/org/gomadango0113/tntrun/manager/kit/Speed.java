package org.gomadango0113.tntrun.manager.kit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.gomadango0113.tntrun.TNTRun;
import org.gomadango0113.tntrun.util.ItemUtil;

import java.util.Collections;

public class Speed implements Kit, Kit.CoolTimeKit {

    //プレイヤー
    private final OfflinePlayer offlineplayer;
    private final Player player;

    //クールタイム
    private int cool_time;
    private boolean can_use;

    public Speed(OfflinePlayer offlineplayer) {
        //プレイヤー
        this.offlineplayer = offlineplayer;
        this.player = offlineplayer.getPlayer();

        //クールタイム
        this.cool_time = 0;
        this.can_use = false;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (cool_time == 0) {
                    ItemUtil.setItemInventory(
                            player.getInventory(),
                            ItemUtil.ItemTag.KIT_ITEM,
                            Material.FEATHER,
                            ChatColor.GOLD + "スピードアイテム" + ChatColor.GRAY + "（利用可能です!）",
                            null);
                    can_use = true;
                }
                else {
                    ItemUtil.setItemInventory(
                            player.getInventory(),
                            ItemUtil.ItemTag.KIT_ITEM,
                            Material.FEATHER,
                            ChatColor.GOLD + "スピードアイテム" + ChatColor.GRAY + "（残りあと" + cool_time + "秒）",
                            null);
                    cool_time--;
                    can_use = false;
                }
            }
        }.runTaskTimer(TNTRun.getInstance(), 0L, 20L);
    }

    @Override
    public KitManager.KitName getName() {
        return KitManager.KitName.SPEED;
    }

    @Override
    public OfflinePlayer getOfflinePlayer() {
        return offlineplayer;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void resetItem(Material material) {
        if (material == Material.FEATHER) {
            cool_time = 60;
            can_use = false;
        }
    }


    @Override
    public int getCoolTime() {
        return cool_time;
    }

    @Override
    public boolean canUse() {
        return can_use;
    }

    public static ItemStack getItem() {
        return new ItemUtil(Material.FEATHER).enableTag(ItemUtil.ItemTag.KIT_ITEM)
                .getItemStack(ChatColor.GOLD + "スピードアイテム", Collections.singletonList("右クリックでスピードを上げることができる。"));
    }

}
