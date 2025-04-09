package org.gomadango0113.tntrun.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.gomadango0113.tntrun.TNTRun;
import org.gomadango0113.tntrun.util.ChatUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ScoreboardManager {

    private static final Scoreboard new_scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    private static Objective game_obj = new_scoreboard.getObjective("TNTRun");
    private static BukkitRunnable board_runnable;

    public static Objective getObjective() {
        if (game_obj != null) {
            createObjective();
        }

        return game_obj;
    }

    public static Scoreboard getScoreboard() {
        if (game_obj == null) {
            createObjective();
        }

        return game_obj.getScoreboard();
    }

    private static Objective createObjective() {
        if (game_obj == null) {
            game_obj = new_scoreboard.registerNewObjective("TNTRun", "dummy");
            game_obj.setDisplayName("TNTRun");

            Bukkit.getLogger().info("[ScoreboardManager] スコアボードを作成しました。");
        }

        return game_obj;
    }

    public static void setScoreboard(int type) {
        if (game_obj != null) {

            resetScore();
            getObjective().setDisplaySlot(DisplaySlot.SIDEBAR);

            if (type == 0) {
                //ゲーム開始前
                game_obj.getScore(" ").setScore(29);
                game_obj.getScore( ChatColor.GOLD + "ゲーム開始待機中...").setScore(28);
                game_obj.getScore("  ").setScore(27);
                game_obj.getScore("   ").setScore(25);
            }
            else if (type == 1) {
                //ゲーム中
                game_obj.getScore(" ").setScore(29);
                //game_obj.getScore(ChatColor.GOLD + "残り人数： " + "☑").setScore(25);
                game_obj.getScore("   ").setScore(22);
            }
            else if (type == 2) {
                //ゲーム終了後
                game_obj.getScore(" ").setScore(29);
                game_obj.getScore( ChatColor.GOLD + "ゲーム終了!!").setScore(28);
                game_obj.getScore("   ").setScore(22);
            }

            updateScoreboard();
            Bukkit.getLogger().info("[ScoreboardManager] スコアボードを設定しました。（type=" + type + ")");
        }
        else {
            createObjective();
            setScoreboard(type);
        }
    }

    private static void updateScoreboard() {
        if (board_runnable == null) {
            new BukkitRunnable() {
                String alive = null;
                @Override
                public void run() {
                    if (GameManager.getStatus() == GameManager.GameStatus.RUNNING) {
                        //残り時間
                        if (alive != null) {
                            game_obj.getScoreboard().resetScores(alive);
                        }
                        alive = (ChatColor.GOLD + "現在の人数:" + ChatColor.RESET + GameManager.getRunPlayers().size());
                        game_obj.getScore(alive).setScore(25);
                    }

                    //スコアボードセット
                    Bukkit.getOnlinePlayers().forEach(player -> player.setScoreboard(ScoreboardManager.getScoreboard()));

                    board_runnable=this;
                }
            }.runTaskTimer(TNTRun.getInstance(), 0L, 2L);
        }
    }

    public static void resetScore() {
        Set<String> scores = getScoreboard().getEntries();

        if (scores != null) {
            for (String score : scores) {
                getScoreboard().resetScores(score);
            }
        }
    }

}
