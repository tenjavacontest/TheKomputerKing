
package net.komputerking.java.tendotjava;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventListeners implements Listener {

    public Main pl;

    public EventListeners(Main pl) {
        this.pl = pl;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getLocation().equals(pl.lastChest) && !pl.found) {
            pl.found = true;
            event.setCancelled(true);
            event.getClickedBlock().setType(Material.AIR);
            Random rand = new Random();
            Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.RED + " " + event.getPlayer().getName() + ChatColor.GREEN + " has found a crate.");
            Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.RED + " Better luck next time!");
            if (rand.nextInt(3) == 1) {
                // Something Nice
                int seedOfEntity = rand.nextInt(2);
                event.getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.GREEN + " You have found a good crate.");
            } else {
                // Something Not Nice
                event.getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.RED + " You have found a bad crate.");
                int seedOfEntity = rand.nextInt(3);
                Bukkit.broadcastMessage("ID: " + seedOfEntity);
                if (seedOfEntity == 1) {
                    int amount = 0;
                    while (amount != 80) {
                        event.getClickedBlock().getLocation().getWorld().spawnEntity(event.getClickedBlock().getLocation(), EntityType.BAT);
                        amount++;
                    }
                }
                if (seedOfEntity == 0) {
                        TNTPrimed t = (TNTPrimed) event.getClickedBlock().getLocation().getWorld().spawnEntity(event.getClickedBlock().getLocation(), EntityType.PRIMED_TNT);
                        t.setFuseTicks(20);
                }
                if (seedOfEntity == 2){
                    int amount = 0;
                    while (amount != 45){
                    Sheep s = (Sheep) event.getClickedBlock().getLocation().getWorld().spawnEntity(event.getClickedBlock().getLocation(), EntityType.SHEEP);
                    int colorSeed = rand.nextInt(DyeColor.values().length);
                    s.setColor(DyeColor.values()[colorSeed]);
                    amount++;
                    }
                }
            }
        }
    }

}
