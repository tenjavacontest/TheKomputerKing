package net.komputerking.java.tendotjava;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import net.komputerking.java.tendotjava.api.Crate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

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
     *
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
    public void onChunkUnload(ChunkUnloadEvent event) {
        for (Entity e : event.getChunk().getEntities()) {
            if (e instanceof FallingBlock) {
                if (((FallingBlock) e).getMetadata("crste").equals(pl.getCrateMeta())) {
                    event.setCancelled(true);
                }
            }
        }
    }
    
    public void doCrate(boolean force, Player p, Block b) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
            if (!force){
            pl.found = true;
            }
            b.setType(Material.AIR);
            Random rand = new Random();
            boolean good = rand.nextBoolean();
            Crate foundAppropriate = null;
            while (foundAppropriate == null) {
                int crateSeed = rand.nextInt(pl.getDataInst().getCrates().size());
                Crate result = pl.getDataInst().getCrates().get(crateSeed);
                if (result.isGood() == good) {
                    foundAppropriate = result;
                }
            }
            if (foundAppropriate != null) {
                foundAppropriate.onActivate(b.getLocation(), p);
                Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.RED + " " + p.getName() + ChatColor.GREEN + " has found a crate.");
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    if (pl != p) {
                        pl.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.RED + " Better luck next time!");
                    }
                }
                if (good){
                    p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.GREEN + " You found a good crate!");
                    NMS.doFirework(b.getLocation(), getFW(true));
                } else {
                    p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.RED + " You found a bad crate!");
                    NMS.doFirework(b.getLocation(), getFW(false));
                }
            }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) throws InvocationTargetException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType().equals(Material.CHEST)){
            if (event.getClickedBlock().getState() instanceof Chest){
                Chest c = (Chest) event.getClickedBlock().getState();
                if (c.getInventory().getName().equalsIgnoreCase("crate")){
                    doCrate(true, event.getPlayer(), event.getClickedBlock());
                }
            }
        }
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getLocation().equals(pl.lastChest) && !pl.found) {
          event.setCancelled(true);
          doCrate(false, event.getPlayer(), event.getClickedBlock());
        }
    }

}
