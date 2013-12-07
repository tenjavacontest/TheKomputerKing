package net.komputerking.java.tendotjava;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class EventListeners implements Listener {

    public Main pl;

    /**
     * Constructor for the EventListener class.
     *
     * @param pl - Instance of Main class
     */
    public EventListeners(Main pl) {
        this.pl = pl;
    }

    /**
     * Returns the appropriate FireworkEffect for a crate.
     * @param good - Whether it is a good crate or bad crate.
     * @return FireworkEffect
     */
    public FireworkEffect getFW(boolean good) {
        if (good) {
            FireworkEffect temp = FireworkEffect.builder().withColor(Color.GREEN).with(Type.BALL).build();
            return temp;
        } else {
            FireworkEffect temp = FireworkEffect.builder().withColor(Color.RED).with(Type.CREEPER).build();
            return temp;
        }
    }
    
    @EventHandler
    public void onChunkUnload(ChunkUnloadEvent event){
        for (Entity e : event.getChunk().getEntities()){
            if (e instanceof FallingBlock){
                if (((FallingBlock)e).getMetadata("crste").equals(pl.getCrateMeta())){
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) throws InvocationTargetException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getLocation().equals(pl.lastChest) && !pl.found) {
            pl.found = true;
            event.setCancelled(true);
            event.getClickedBlock().setType(Material.AIR);
            Random rand = new Random();
            Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.RED + " " + event.getPlayer().getName() + ChatColor.GREEN + " has found a crate.");
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p != event.getPlayer()) {
                    p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.RED + " Better luck next time!");
                }
            }
            if (rand.nextInt(2) == 1) {
                // Something Nice
                NMS.doFirework(event.getClickedBlock().getLocation(), getFW(true));
                int seedOfEntity = rand.nextInt(2);
                if (seedOfEntity == 1) {
                    Item i = (Item) event.getClickedBlock().getLocation().getWorld().dropItemNaturally(event.getClickedBlock().getLocation(), new ItemStack(Material.DIAMOND, 3));
                    i.setVelocity(new Vector(0, 0.25, 0));
                }
                if (seedOfEntity == 0) {
                    Horse h = (Horse) event.getClickedBlock().getLocation().getWorld().spawnEntity(event.getClickedBlock().getLocation(), EntityType.HORSE);
                    h.setAdult();
                    h.setTamed(true);
                    h.setDomestication(1);
                    h.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                }
                event.getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.GREEN + " You have found a good crate.");
            } else {
                // Something Not Nice
                NMS.doFirework(event.getClickedBlock().getLocation(), getFW(false));
                event.getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.RED + " You have found a bad crate.");
                int seedOfEntity = rand.nextInt(3);
                if (seedOfEntity == 1) {
                    int amount = 0;
                    while (amount != 80) {
                        event.getClickedBlock().getLocation().getWorld().spawnEntity(event.getClickedBlock().getLocation(), EntityType.BAT);
                        amount++;
                    }
                }
                if (seedOfEntity == 0) {
                    TNTPrimed t = (TNTPrimed) event.getClickedBlock().getLocation().getWorld().spawnEntity(event.getClickedBlock().getLocation(), EntityType.PRIMED_TNT);
                    t.setVelocity(new Vector(0, 0.25, 0));
                    t.setFuseTicks(10);
                }
                if (seedOfEntity == 2) {
                    int amount = 0;
                    while (amount != 100) {
                        Sheep s = (Sheep) event.getClickedBlock().getLocation().getWorld().spawnEntity(event.getClickedBlock().getLocation(), EntityType.SHEEP);
                        int colorSeed = rand.nextInt(DyeColor.values().length);
                        s.setColor(DyeColor.values()[colorSeed]);
                        Vector velocity = new Vector(rand.nextInt(2), 0.8, rand.nextInt(2));
                        s.setVelocity(velocity);
                        amount++;
                    }
                }
            }
        }
    }

}
