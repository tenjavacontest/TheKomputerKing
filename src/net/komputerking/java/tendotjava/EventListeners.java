
package net.komputerking.java.tendotjava;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventListeners implements Listener{
    
    public Main pl;
    
    public EventListeners(Main pl){
        this.pl = pl;
    }
    
    @EventHandler
    public void onClick(PlayerInteractEvent event){
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getLocation().equals(pl.lastChest) && !pl.found){
            pl.found = true;
            event.setCancelled(true);
            event.getClickedBlock().setType(Material.AIR);
            Random rand = new Random();
            Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.RED + " " + event.getPlayer().getName() + ChatColor.GREEN + " has found a crate.");
            Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.RED + " Better luck next time!");
            if (rand.nextBoolean()){
             // Something Nice
                event.getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.GREEN + " You have found a good crate.");
            } else {
             // Something Not Nice
                event.getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.RED + " You have found a bad crate.");
            }
        }
    }
    
}
