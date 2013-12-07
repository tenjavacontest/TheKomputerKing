
package net.komputerking.java.tendotjava;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
    
    private static ArrayList<String> inGame = new ArrayList<String>();
   
    @Override
    public void onEnable(){
        System.out.println("======================================");
        System.out.println("Ten.java Competition Entry - MobStack");
        System.out.println("By TheKomputerKing");
        System.out.println("Theme - Entitites");
        System.out.println("======================================");
        Bukkit.getPluginManager().registerEvents(new EventListeners(this), this);
    }
    
    @Override
    public void onDisable(){
    }
    
    public static boolean isPlayerInGame(Player p){
        return true;
    //    return inGame.contains(p.getName());
    }
    
}
