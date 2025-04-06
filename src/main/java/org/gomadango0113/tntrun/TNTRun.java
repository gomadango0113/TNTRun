package org.gomadango0113.tntrun;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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

    }

    public void registerListener() {
        PluginManager plm = getServer().getPluginManager();
    }
}
