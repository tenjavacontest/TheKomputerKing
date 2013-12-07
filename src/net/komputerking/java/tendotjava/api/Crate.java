
package net.komputerking.java.tendotjava.api;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class Crate {
    
    public APIInstance EntityCrates = new APIInstance();
    private boolean good = true;
    
    public void onActivate(Location par1, Player par2){
        
    }
    
    public void setGood(boolean b){
        good = b;
    }
    
    public boolean isGood(){
        return good;
    }
    
}
