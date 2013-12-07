package net.komputerking.java.tendotjava;

import java.lang.reflect.Field;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class NMS {

    public static void doFirework(Location par1, FireworkEffect par2) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Firework fw = (Firework) par1.getWorld().spawn(par1, Firework.class);
        FireworkMeta fwm = fw.getFireworkMeta();
        fwm.clearEffects();
        fwm.addEffect(par2);
        Field f = fwm.getClass().getDeclaredField("power");
        f.setAccessible(true);
        f.set(fwm, -2);
        fw.setFireworkMeta(fwm);
    }

}
