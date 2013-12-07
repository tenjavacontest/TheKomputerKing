package net.komputerking.java.tendotjava;

import java.lang.reflect.Field;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class NMS {

    /**
     * Uses reflection to spawn an instantly exploding Firework entity.
     * @param par1 - Location of Firework
     * @param par2 - FireworkEffect
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public static void doFirework(Location par1, FireworkEffect par2) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Firework fw = (Firework) par1.getWorld().spawn(par1, Firework.class);
        FireworkMeta fwm = fw.getFireworkMeta();
        fwm.clearEffects();
        fwm.addEffect(par2);
        Field f = fwm.getClass().getDeclaredField("power");
        f.setAccessible(true);
        f.set(fwm, 0);
        fw.setFireworkMeta(fwm);
    }

}
