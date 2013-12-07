package net.komputerking.java.tendotjava.crates;

import net.komputerking.java.tendotjava.api.Crate;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.util.Vector;

public class CrateTNT extends Crate {

    public CrateTNT() {
        this.setGood(false);
        EntityCrates.registerCrate(this);
    }

    @Override
    public void onActivate(Location l, Player p) {
        TNTPrimed t = (TNTPrimed) l.getWorld().spawnEntity(l, EntityType.PRIMED_TNT);
        t.setVelocity(new Vector(0, 0.25, 0));
        t.setFuseTicks(10);
    }

}
