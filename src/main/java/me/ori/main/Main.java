package me.ori.main;

import me.ori.main.models.GameCommand;
import me.ori.main.models.GameListeners;
import me.ori.main.models.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {



    public GameManager getManager() {
        return manager;
    }

    private GameManager manager;
    public FileConfiguration config;

    @Override
    public void onEnable() {
        getLogger().info("Welcome to DreamRandomItemsChallenge by NotOri_!");
        config = this.getConfig();
        makeConfig();
        PluginManager pm = Bukkit.getPluginManager();

        getLogger().warning("If something doesn't work, please check if your config is legal, if not:" +
                    " to fix possible problems please make sure that: prevent-picking-others-itmes is true or false, start-delay-seconds is a number" +
                    "and spawn-every-seconds is also a number, then reload the server.");

        pm.registerEvents(new GameListeners(this),this);
        manager = new GameManager(this);
        this.getCommand("DreamRandomItemsChallenge").setExecutor(new GameCommand(this));
        List<String> aliases = new ArrayList<>();
        aliases.add("drc");
        this.getCommand("DreamRandomItemsChallenge").setAliases(aliases);





        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public void makeConfig(){
        config.addDefault("prevent-picking-others-itmes",true);
        config.addDefault("start-delay-seconds",120);
        config.addDefault("spawn-every-seconds",120);
        config.addDefault("number-of-item-stacks",10);
        config.options().copyDefaults(true);
        saveConfig();
        reloadConfig();

    }

}
