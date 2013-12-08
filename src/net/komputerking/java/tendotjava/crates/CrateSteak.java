package net.komputerking.java.tendotjava.crates;

import net.komputerking.java.tendotjava.api.Crate;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class CrateSteak extends Crate {

    public CrateSteak() {
        this.setGood(true);
        EntityCrates.registerCrate(this);
    }

    @Override
    public void onActivate(Location l, Player p) {
        Item i = (Item) l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_BEEF, 32));
        i.setVelocity(new Vector(0, 0.25, 0));
    }

}
