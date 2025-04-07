package org.gomadango0113.tntrun.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.gomadango0113.tntrun.manager.GameManager;
import org.gomadango0113.tntrun.util.ChatUtil;

public class GameStartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tntrun_start")) {
            ChatUtil.sendMessage(send, "ゲームを開始しています。");

            int status;
            if (args.length == 0 || args[0].isEmpty()) {
                status = GameManager.startGame(true);
            }
            else {
                status = GameManager.startGame(!args[0].equalsIgnoreCase("fast"));
            }

            if (status != 0) {
                ChatUtil.sendMessage(send, "ゲームを開始するのに失敗しました。");
            }
        }
        return false;
    }

}
