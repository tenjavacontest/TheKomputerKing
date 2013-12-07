
package net.komputerking.java.tendotjava;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EventListeners implements Listener{
    
    public Main pl;
    
    public EventListeners(Main pl){
        this.pl = pl;
    }
    
    @EventHandler
    public void onEntityClick(final PlayerInteractEntityEvent event){
        if (Main.isPlayerInGame(event.getPlayer()) && event.getRightClicked() instanceof Creature){
            Entity lastIterated = event.getPlayer();
            boolean stillInProg = true;
            while(stillInProg){
                if (lastIterated.getPassenger() != null){
                    lastIterated = lastIterated.getPassenger();
                } else {
                    stillInProg = false;
                }
            }
            lastIterated.setPassenger(event.getRightClicked());
        }
    }
    
    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if (event.getEntity() instanceof Player){
            Player p = (Player) event.getEntity();
            if (Main.isPlayerInGame(p)){
            Entity lastIterated = p;
            boolean stillInProg = true;
            while(stillInProg){
                if (lastIterated.getPassenger() != null && lastIterated != p){
                    lastIterated.remove();
                    lastIterated = lastIterated.getPassenger();
                } else {
                    stillInProg = false;
                }
            }
            }
        }
    }
    
}
