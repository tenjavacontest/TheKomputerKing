package net.komputerking.java.tendotjava.crates;

import java.util.Random;
import net.komputerking.java.tendotjava.api.Crate;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.util.Vector;

public class CrateSheep extends Crate {

    public CrateSheep() {
        this.setGood(false);
        EntityCrates.registerCrate(this);
    }

    @Override
    public void onActivate(Location l, Player p) {
        int amount = 0;
        Random rand = new Random();
        while (amount != 100) {
            Sheep s = (Sheep) l.getWorld().spawnEntity(l, EntityType.SHEEP);
            int colorSeed = rand.nextInt(DyeColor.values().length);
            s.setColor(DyeColor.values()[colorSeed]);
            Vector velocity = new Vector(0.3, 1.2, 0.3);
            s.setVelocity(s.getVelocity().add(velocity));
            amount++;
        }
    }

}
