package net.komputerking.java.tendotjava.crates;

import java.util.Random;
import net.komputerking.java.tendotjava.api.Crate;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class CratePotion extends Crate {

    public CratePotion() {
        this.setGood(false);
        EntityCrates.registerCrate(this);
    }

    @Override
    public void onActivate(Location l, Player p) {
        Location toSpawn = new Location(l.getWorld(), l.getX(), l.getY() + 3, l.getZ());
        Potion pot = (Potion) l.getWorld().spawnEntity(toSpawn, EntityType.SPLASH_POTION);
        pot.setSplash(true);
        pot.setType(PotionType.INSTANT_DAMAGE);
        p.damage(2D);
        pot.setLevel(2);
    }

}
