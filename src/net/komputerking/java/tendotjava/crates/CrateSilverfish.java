package net.komputerking.java.tendotjava.crates;

import net.komputerking.java.tendotjava.api.Crate;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;

public class CrateSilverfish extends Crate {
    
    public CrateSilverfish() {
        this.setGood(false);
        EntityCrates.registerCrate(this);
    }
    
    @Override
    public void onActivate(Location l, Player p) {
        int amount = 3;
        while (amount != 0) {
            amount--;
            Silverfish s = (Silverfish) l.getWorld().spawnEntity(l, EntityType.SILVERFISH);
            s.setMaxHealth(5);
        }
    }
    
}
