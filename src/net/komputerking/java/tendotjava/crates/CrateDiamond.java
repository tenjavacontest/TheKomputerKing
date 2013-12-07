package net.komputerking.java.tendotjava.crates;

import net.komputerking.java.tendotjava.api.Crate;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class CrateDiamond extends Crate {

    public CrateDiamond() {
        this.setGood(true);
        EntityCrates.registerCrate(this);
    }

    @Override
    public void onActivate(Location l, Player p) {
        Item i = (Item) l.getWorld().dropItemNaturally(l, new ItemStack(Material.DIAMOND, 3));
        i.setVelocity(new Vector(0, 0.25, 0));
    }

}
