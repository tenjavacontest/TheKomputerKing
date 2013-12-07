
package net.komputerking.java.tendotjava;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EventListeners implements Listener{
    
    @EventHandler
    public void onEntityClick(PlayerInteractEntityEvent event){
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
    
}
