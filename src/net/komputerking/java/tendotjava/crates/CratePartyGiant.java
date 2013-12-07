package net.komputerking.java.tendotjava.crates;

import java.util.Timer;
import java.util.TimerTask;
import net.komputerking.java.tendotjava.Main;
import net.komputerking.java.tendotjava.api.Crate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CratePartyGiant extends Crate {
    
    private Main pl;

    public CratePartyGiant(Main par1) {
        this.setGood(true);
        EntityCrates.registerCrate(this);
        pl = par1;
    }

    @Override
    public void onActivate(final Location l, Player p) {
        final Giant g = (Giant) l.getWorld().spawnEntity(l, EntityType.GIANT);
        Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable(){
            public void run(){
                g.damage(1D);
                g.setHealth(0);
                int y = (int) Math.ceil(g.getLocation().getBlockY());
                while (y != y+19){
                    y++;
                    Location loc = new Location(l.getWorld(), l.getX(), y, l.getZ());
                    loc.getWorld().createExplosion(loc, 1);
                    Item i = (Item) l.getWorld().dropItemNaturally(l, new ItemStack(Material.IRON_INGOT, 1));
                }
            }
        }, 30);
    }

}
