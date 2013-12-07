
package net.komputerking.java.tendotjava.api;

import net.komputerking.java.tendotjava.Data;
import net.komputerking.java.tendotjava.Main;

public class APIInstance {
    
    /**
     * Registers a crate into the plugin.
     * @param c 
     */
    public void registerCrate(Crate c){
        Data d = Main.getData();
        if (!d.doesExist(c)){
            d.addCrate(c);
        }
    }
    
    /**
     * Unregisters a crate from the plugin.
     * @param c 
     */
    public void unregisterCrate(Crate c){
        Data d = Main.getData();
        if (d.doesExist(c)){
            d.removeCrate(c);
        }
    }
    
}
