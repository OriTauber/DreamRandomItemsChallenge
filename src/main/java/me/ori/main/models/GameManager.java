package me.ori.main.models;

import me.ori.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class GameManager {
    public int taskID;
    public boolean isPlaying;

    public GameManager(Main main) {
        this.main = main;
        isPlaying = false;
    }

    private Main main;

    public void start(){
        isPlaying = true;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
            @Override
            public void run() {

                Bukkit.broadcastMessage(ChatColor.GOLD + "The supply drop has arrived!");
                for(Player p : Bukkit.getOnlinePlayers()){
                    Random r = new Random();
                    Material[] items = Material.values();
                    int pos = r.nextInt(items.length);
                    ItemStack randItem = new ItemStack(items[pos]);

                    while (!randItem.getType().isItem()) {
                        int helpPos = r.nextInt(items.length);
                        randItem = new ItemStack(items[helpPos]);
                    }

                    randItem.setAmount(randItem.getMaxStackSize());






                    for(int i = 0;i < main.config.getInt("number-of-item-stacks");i++) {
                        Item rand = p.getWorld().dropItemNaturally(p.getLocation(),randItem);
                        rand.setOwner(p.getUniqueId());
                        rand.setItemStack(randItem);
                    }
                }

            }
        },20 * main.config.getInt("start-delay-seconds"),20 * main.config.getInt("spawn-every-seconds"));

    }
    public void stop(){
        isPlaying = false;
        Bukkit.getScheduler().cancelTask(taskID);
    }

}
