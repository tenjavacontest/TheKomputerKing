
package net.komputerking.java.tendotjava;

import java.util.ArrayList;
import net.komputerking.java.tendotjava.api.Crate;

public class Data {
    
    private ArrayList<Crate> crates = new ArrayList<Crate>();
    
    public ArrayList<Crate> getCrates(){
        return crates;
    }
    
    public void addCrate(Crate c){
        crates.add(c);
    }
    
    public void removeCrate(Crate c){
        crates.remove(c);
    }
    
    public boolean doesExist(Crate c){
        return crates.contains(c);
    }
    
}
