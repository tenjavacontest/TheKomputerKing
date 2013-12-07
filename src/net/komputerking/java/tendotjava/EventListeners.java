
package net.komputerking.java.tendotjava;

import org.bukkit.entity.Creature;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EventListeners implements Listener{
    
    @EventHandler
    public void onEntityClick(PlayerInteractEntityEvent event){
        if (!Main.isPlayerInGame(event.getPlayer()) && event.getRightClicked() instanceof Creature){
            event.getPlayer().setPassenger(event.getRightClicked());
        }
    }
    
}
