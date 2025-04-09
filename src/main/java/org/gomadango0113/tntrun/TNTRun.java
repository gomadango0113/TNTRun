package org.gomadango0113.tntrun;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.gomadango0113.tntrun.command.GameStartCommand;
import org.gomadango0113.tntrun.listener.PlayerClickListener;
import org.gomadango0113.tntrun.listener.PlayerJoinListener;
import org.gomadango0113.tntrun.manager.ScoreboardManager;

public final class TNTRun extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommand();
        registerListener();
        ScoreboardManager.setScoreboard(0);

        getLogger().info("プラグインが有効になりました。");
    }

    @Override
    public void onDisable() {

    }

    public void registerCommand() {
        getCommand("tntrun_start").setExecutor(new GameStartCommand());
    }

    public void registerListener() {
        PluginManager plm = getServer().getPluginManager();

        plm.registerEvents(new PlayerClickListener(), this);
        plm.registerEvents(new PlayerJoinListener(), this);
    }

    public static TNTRun getInstance() {
        return getPlugin(TNTRun.class);
    }
}
