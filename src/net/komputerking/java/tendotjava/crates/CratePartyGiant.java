package net.komputerking.java.tendotjava.crates;

import java.util.Timer;
import java.util.TimerTask;
import net.komputerking.java.tendotjava.api.Crate;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CratePartyGiant extends Crate {

    public CratePartyGiant() {
        this.setGood(true);
        EntityCrates.registerCrate(this);
    }

    @Override
    public void onActivate(final Location l, Player p) {
        final Giant g = (Giant) l.getWorld().spawnEntity(l, EntityType.GIANT);
        Timer t = new Timer();
        t.schedule(new TimerTask(){
            public void run(){
                g.remove();
                int y = (int) Math.ceil(g.getLocation().getBlockY());
                while (y != y+19){
                    y++;
                    Location loc = new Location(l.getWorld(), l.getX(), y, l.getZ());
                    loc.getWorld().createExplosion(loc, 1);
                    Item i = (Item) l.getWorld().dropItemNaturally(l, new ItemStack(Material.IRON_INGOT, 1));
                }
            }
        }, 700);
    }

}
