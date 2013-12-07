
package net.komputerking.java.tendotjava.api;

import net.komputerking.java.tendotjava.Data;
import net.komputerking.java.tendotjava.Main;

public class APIInstance {
    
    public void registerCrate(Crate c){
        Data d = Main.getData();
        if (!d.doesExist(c)){
            d.addCrate(c);
        }
    }
    
    public void unregisterCrate(Crate c){
        Data d = Main.getData();
        if (d.doesExist(c)){
            d.removeCrate(c);
        }
    }
    
}
