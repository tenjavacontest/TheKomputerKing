
package net.komputerking.java.tendotjava.api;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class Crate {
    
    public APIInstance EntityCrates = new APIInstance();
    private boolean good = true;
    
    /**
     * Called on opening of the crate.
     * @param par1
     * @param par2 
     */
    public void onActivate(Location par1, Player par2){
        
    }
    
    /**
     * Sets whether this crate is good or not.
     * @param b
     */
    public void setGood(boolean b){
        good = b;
    }
    
    /**
     * A boolean on whether this crate is good.
     * @return boolean
     */
    public boolean isGood(){
        return good;
    }
    
}
