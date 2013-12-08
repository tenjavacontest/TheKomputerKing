# EntityCrates #
Written for the ten.java plugin competition.

You can download the latest build at: [ci.kompking.info](http://ci.kompking.info/)

## Usage ##

You may run on Bukkit 1.7.2 or 1.6.4, it is not version-dependent.

This plugin will drop crates in loaded chunks once in a while, and announce it in chat. After 4 minutes they explode but if somebody opens them before they self-destruct, either good or bad entities will spawn along with some cool effects.

## API ##
Include the plugin jar in your plugins build path, and create a class that extends Crate and uses the following methods:

    public class CrateTest extends Crate {
    
     public CrateTest() {
      this.setGood(true);
      EntityCrates.registerCrate(this);
     }

     @Override
     public void onActivate(Location l, Player p) {
      l.getWorld().createExplosion(l, 0);
      Item i = (Item) l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_BEEF, 32));
     }

    }

Inside of your constructor, the setGood method will let you choose whether your crate is good or bad.

Before the onActivate is called, the chest is removed, so if you wish to use the chest for inventory functions, you can use

      l.getBlock().setType(Material.CHEST);

in the onActivate method and you can go from there.


----------
Hope you enjoy the plugin :)
-Komp
