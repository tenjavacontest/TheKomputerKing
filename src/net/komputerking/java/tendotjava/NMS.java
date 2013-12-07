package net.komputerking.java.tendotjava;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class NMS {

    private static Method worldHandle = null;
    private static Method broadcastEntityEffect = null;
    private static Method fireworkHandle = null;

    public static void doFirework(Location par1, FireworkEffect par2) throws IllegalAccessException, InvocationTargetException {
        World world = par1.getWorld();
        Firework fw = (Firework) world.spawn(par1, Firework.class);
        if (worldHandle == null) {
            worldHandle = getMethod(world.getClass(), "getHandle");
            fireworkHandle = getMethod(fw.getClass(), "getHandle");
        }
        Object nms_world = worldHandle.invoke(world, (Object[]) null);
        Object nms_fw = fireworkHandle.invoke(fw, (Object[]) null);
        if (broadcastEntityEffect == null) {
            broadcastEntityEffect = getMethod(nms_world.getClass(), "broadcastEntityEffect");
        }
        FireworkMeta data = (FireworkMeta) fw.getFireworkMeta();
        data.clearEffects();
        data.setPower(1);
        data.addEffect(par2);
        fw.setFireworkMeta(data);

        broadcastEntityEffect.invoke(nms_world, new Object[]{nms_fw, (byte) 17});
        fw.remove();
    }

    private static Method getMethod(Class<?> c, String m) {
        for (Method method : c.getMethods()) {
            if (method.getName().equals(m)) {
                return method;
            }
        }
        return null;
    }

}
