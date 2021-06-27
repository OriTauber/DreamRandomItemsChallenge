package me.ori.main.models;

import me.ori.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameCommand implements CommandExecutor {
    public GameCommand(Main main) {
        this.main = main;
    }

    private Main main;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        
        switch (args.length) {
            case 0:
                sender.sendMessage(ChatColor.AQUA + "Welcome to the random drops challenge by NotOri! To start use DreamRandomItemsChallenge(drc) start / stop");
                break;
            case 1:
                if (args[0].equalsIgnoreCase("start")) {
                    if (!main.getManager().isPlaying) {
                        main.getManager().start();
                        sender.sendMessage(ChatColor.AQUA + "Started The Game!");
                        Bukkit.broadcastMessage(ChatColor.GOLD + "The game has started!");

                    } else {
                        sender.sendMessage(ChatColor.DARK_RED + "The game is already playing!");
                    }

                } else if (args[0].equalsIgnoreCase("stop")) {
                    if (main.getManager().isPlaying) {
                        main.getManager().stop();
                        sender.sendMessage(ChatColor.AQUA + "Stopped The Game!");
                        Bukkit.broadcastMessage(ChatColor.GOLD + "The game has stopped!");
                    } else {
                        sender.sendMessage(ChatColor.DARK_RED + "Couldn't stop the game, the game is currently not playing!");
                    }
                } else {
                    sender.sendMessage("Invalid usage, Please use /rdg start / stop");
                }
                break;
            default:
                sender.sendMessage(ChatColor.DARK_RED + "Invalid usage, Please use /rdg start / stop");
                break;


        }
        return false;
    }
}
