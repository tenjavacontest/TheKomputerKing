package net.komputerking.java.tendotjava.crates;

import net.komputerking.java.tendotjava.api.Crate;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class CrateBat extends Crate {

    public CrateBat() {
        this.setGood(false);
        EntityCrates.registerCrate(this);
    }

    @Override
    public void onActivate(Location l, Player p) {
        int amount = 0;
        while (amount != 80) {
            l.getWorld().spawnEntity(l, EntityType.BAT);
            amount++;
        }
    }

}
