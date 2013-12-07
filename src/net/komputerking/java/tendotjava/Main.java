
package net.komputerking.java.tendotjava;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
   
    @Override
    public void onEnable(){
        System.out.println("======================================");
        System.out.println("Ten.java Competition Entry - EntityCrates");
        System.out.println("By TheKomputerKing");
        System.out.println("Theme - Entitites");
        System.out.println("======================================");
        Bukkit.getPluginManager().registerEvents(new EventListeners(this), this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
            public void run(){
                dropCrate();
            }
        }, 0L, 4000L);
    }
    
    @Override
    public void onDisable(){
    }
    
    public void dropCrate(){
        Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.WHITE + " A crate is dropping at the co-ordinates 0, 0!");
        Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.WHITE + " Get there to pick it up.");
        final Location loc = new Location(Bukkit.getWorld("world"), 0, 255 ,0);
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
            public void run(){
              FallingBlock fb = Bukkit.getWorld("world").spawnFallingBlock(loc, Material.PISTON_EXTENSION, (byte) 0x0);
            }
        },120L);
    }

}
