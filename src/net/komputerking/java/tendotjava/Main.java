package net.komputerking.java.tendotjava;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.FallingBlock;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public boolean found = true;
    public Location lastChest = null;

    @Override
    public void onEnable() {
        System.out.println("======================================");
        System.out.println("Ten.java Competition Entry - EntityCrates");
        System.out.println("By TheKomputerKing");
        System.out.println("Theme - Entitites");
        System.out.println("======================================");
        Bukkit.getPluginManager().registerEvents(new EventListeners(this), this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                dropCrate();
            }
        }, 0L, 10000L);
    }

    @Override
    public void onDisable() {
    }

    /**
     * Drops a crate at a random location in a loaded chunk.
     */
    public void dropCrate() {
        if (found) {
            Random rand = new Random();
            found = false;
            final World w = Bukkit.getWorld("world");
            if (w.getLoadedChunks() != null){
            int chunkSeed = rand.nextInt(w.getLoadedChunks().length);
            Chunk c = w.getLoadedChunks()[chunkSeed];
            c.load();
            final Location loc = new Location(w, c.getX(), 255, c.getZ());
            Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.WHITE + " A crate is dropping at the highest point at " + (int) Math.ceil(loc.getX()) + ", " + (int) Math.ceil(loc.getZ()));
            Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.WHITE + " Get there to pick it up.");
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                public void run() {
                    FallingBlock fb = Bukkit.getWorld("world").spawnFallingBlock(loc, Material.CHEST, (byte) 0x0);
                    fb.setMetadata("crate", getCrateMeta());
                    lastChest = fb.getLocation().getWorld().getHighestBlockAt(fb.getLocation()).getLocation();
                    lastChest.getBlock().setType(Material.AIR);
                    if (lastChest.getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.AIR)) {
                        lastChest.getBlock().getRelative(BlockFace.DOWN).setType(Material.GRASS);
                    }
                }
            }, 120L);
           Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                public void run() {
                    if (!found){
                        lastChest.getWorld().createExplosion(lastChest, 0);
                        lastChest.getBlock().setType(Material.AIR);
                        Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "EntityCrates" + ChatColor.GRAY + "]" + ChatColor.RED + " The crate self-destructed as nobody found it.");
                    }
                }
            }, 6*60*20L);
        }
        }
    }
    
    /**
     * Gets the metadata for a FallingSand crate.
     * @return MetadataValue
     */
    public MetadataValue getCrateMeta(){
        final Plugin pl = this;
        return new MetadataValue() {
            @Override
            public Object value() {
                return "isCrate";
            }

            @Override
            public int asInt() {
                return 1;
            }

            @Override
            public float asFloat() {
                return 1;
            }

            @Override
            public double asDouble() {
                return 1;
            }

            @Override
            public long asLong() {
                return 1;
            }

            @Override
            public short asShort() {
                return 1;
            }

            @Override
            public byte asByte() {
                return (byte) 0x0;
            }

            @Override
            public boolean asBoolean() {
                return true;
            }

            @Override
            public String asString() {
                return "isCrate";
            }

            @Override
            public Plugin getOwningPlugin() {
                return pl;
            }

            @Override
            public void invalidate() {
            }
        };
    }

}
