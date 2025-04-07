package org.gomadango0113.tntrun;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.gomadango0113.tntrun.command.GameStartCommand;

public final class TNTRun extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommand();
        registerListener();

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
    }

    public static TNTRun getInstance() {
        return getPlugin(TNTRun.class);
    }
}
