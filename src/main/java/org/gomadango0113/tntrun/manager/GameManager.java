package org.gomadango0113.tntrun.manager;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.gomadango0113.tntrun.TNTRun;
import org.gomadango0113.tntrun.util.ChatUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameManager {

    private static GameStatus status;
    private static int game_time;

    private static final Set<OfflinePlayer> tntrun_players = new HashSet<>();

    static {
        status = GameStatus.WAITING;
        game_time = Integer.MAX_VALUE;
    }

    public static int startGame(boolean count) {
        if (status == GameStatus.WAITING) {
            final int[] count_time = {10};

            if (!count) {
                count_time[0] = 0;
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (status == GameStatus.WAITING || status == GameStatus.COUNTING) {
                        status = GameStatus.COUNTING;

                        if (count_time[0] == 0) {
                            ChatUtil.sendGlobalMessage("ゲーム開始!!");
                            tntrun_players.addAll(Bukkit.getOnlinePlayers());

                            status = GameStatus.RUNNING;
                        }
                        else {
                            ChatUtil.sendGlobalMessage("ゲーム開始まであと" + count_time[0] + "秒");
                            count_time[0]--;
                        }
                    }
                    else if (status == GameStatus.RUNNING) {
                        game_time--;
                    }
                }
            }.runTaskTimer(TNTRun.getInstance(), 0L, 20L);

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (status == GameStatus.RUNNING) {
                        if (tntrun_players.isEmpty()) {
                            ChatUtil.sendGlobalMessage("ゲーム終了です。");
                            this.cancel();
                        }

                        for (Player online : Bukkit.getOnlinePlayers()) {
                            Location online_loc = online.getLocation();
                            Location online_under_loc = online_loc.subtract(0, 1, 0);
                            Block online_under_block = online_under_loc.getBlock();
                            if (online_under_block.getType() == Material.GOLD_BLOCK) {
                                tntrun_players.remove(online);
                                ChatUtil.sendMessage(online, "あなたは脱落しました。");
                                ChatUtil.sendGlobalMessage(online.getName() + "が脱落しました。" + ChatColor.GRAY + "（残り" + tntrun_players.size() + "人）");
                                online.setGameMode(GameMode.SPECTATOR);
                            }
                            else if (online_under_block.getType() == Material.TNT) {
                                online_under_block.setType(Material.AIR);
                            }
                        }
                    }
                }
            }.runTaskTimer(TNTRun.getInstance(), 0L, 2L);

            return 0;
        }

        return 1;
    }

    public static Set<OfflinePlayer> getRunPlayers() {
        return tntrun_players;
    }

    public static GameStatus getStatus() {
        return status;
    }

    public enum GameStatus {
        WAITING,
        COUNTING,
        RUNNING,
        ENDING
    }

}
