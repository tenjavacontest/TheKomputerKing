package net.komputerking.java.tendotjava.crates;

import net.komputerking.java.tendotjava.api.Crate;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class CrateEXP extends Crate {

    public CrateEXP() {
        this.setGood(true);
        EntityCrates.registerCrate(this);
    }

    @Override
    public void onActivate(Location l, Player p) {
        int amount = 20;
        while (amount != 0) {
            amount--;
            l.getWorld().spawnEntity(l, EntityType.THROWN_EXP_BOTTLE);
        }
    }

}
