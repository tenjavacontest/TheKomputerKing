package net.komputerking.java.tendotjava.crates;

import net.komputerking.java.tendotjava.api.Crate;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CrateHorse extends Crate {

    public CrateHorse() {
        this.setGood(true);
        EntityCrates.registerCrate(this);
    }

    @Override
    public void onActivate(Location l, Player p) {
        Horse h = (Horse) l.getWorld().spawnEntity(l, EntityType.HORSE);
        h.setAdult();
        h.setTamed(true);
        h.setDomestication(1);
        h.getInventory().setSaddle(new ItemStack(Material.SADDLE));
    }
}
