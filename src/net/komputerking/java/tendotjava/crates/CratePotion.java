package net.komputerking.java.tendotjava.crates;

import net.komputerking.java.tendotjava.api.Crate;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CratePotion extends Crate {

    public CratePotion() {
        this.setGood(false);
        EntityCrates.registerCrate(this);
    }

    @Override
    public void onActivate(Location l, Player p) {
        p.damage(2D);
        p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 15, 1));
    }

}
