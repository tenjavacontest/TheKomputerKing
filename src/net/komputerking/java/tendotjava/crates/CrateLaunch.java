package net.komputerking.java.tendotjava.crates;

import net.komputerking.java.tendotjava.api.Crate;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class CrateLaunch extends Crate {
    
    public CrateLaunch() {
        this.setGood(false);
        EntityCrates.registerCrate(this);
    }
    
    @Override
    public void onActivate(Location par1, Player par2) {
        par2.setVelocity(par2.getVelocity().add(new Vector(0, 2.5, 0)));
    }
    
}
