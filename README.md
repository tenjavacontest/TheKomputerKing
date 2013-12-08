# EntityCrates #
Written for the ten.java plugin competition.

You can download the latest build at: [ci.kompking.info](http://ci.kompking.info/)

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
