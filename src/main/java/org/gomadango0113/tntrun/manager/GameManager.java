package org.gomadango0113.tntrun.manager;

import org.bukkit.scheduler.BukkitRunnable;
import org.gomadango0113.tntrun.TNTRun;
import org.gomadango0113.tntrun.util.ChatUtil;

public class GameManager {

    private static GameStatus status;
    private static int game_time;

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

            return 0;
        }

        return 1;
    }

    public GameStatus getStatus() {
        return status;
    }

    public enum GameStatus {
        WAITING,
        COUNTING,
        RUNNING,
        ENDING
    }

}
