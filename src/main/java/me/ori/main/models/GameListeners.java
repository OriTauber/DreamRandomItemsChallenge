package me.ori.main.models;

import me.ori.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;


public class GameListeners implements Listener {
    private Main main;
    public GameListeners(Main main) {
        this.main = main;
    }

    @EventHandler
    public void cancelBreak(BlockBreakEvent e){
        if(!main.getManager().isPlaying)
            return;
        e.setDropItems(false);
    }

    @EventHandler
    public void protectPickup(EntityPickupItemEvent e){
        if(!main.getManager().isPlaying)
            return;
        if(!(e.getEntity() instanceof Player))
            return;

        if(!main.config.getBoolean("prevent-picking-others-itmes"))
            return;



        Player p = (Player) e.getEntity();
        if(e.getItem().getOwner() == null)
            return;
        if(!e.getItem().getOwner().equals(p.getUniqueId())){
            e.setCancelled(true);
        }




    }
}
