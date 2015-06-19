package mccarthy.brian.booksafe;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;
import net.canarymod.config.Configuration;
import net.visualillusionsent.utils.PropertiesFile;

/**
 * Actions that are used across multiple commands / listeners
 * @author Brian McCarthy
 *
 */
public class BookSafeActions {

    private PropertiesFile warnsFile;

    public BookSafeActions() {
        warnsFile = Configuration.getPluginConfig(BookSafe.getInstance(), "BookCounts");
    }

    public void addCount(String uuid, int value) {    
        int currWarns = getCount(uuid);
        warnsFile.setInt(uuid, currWarns + value);
        warnsFile.save();
    }

    public int getCount(String uuid) {
        int playerWarns = warnsFile.getInt(uuid, 0);
        return playerWarns;
    }

    public void removeCount(String uuid, int amount) {
        addCount(uuid, -amount);
    }

    public void removeFromInventory(Player p) {
        Item i = null;
        int count = 0;
        while((i = p.getInventory().getItem(ItemType.BookAndQuill)) != null) {
            count += i.getAmount();
            p.getInventory().removeItem(i);
        }
        addCount(p.getUUIDString(), count); // Add, in case they got more books since last time. (Somehow)
        if (count != 0) {
            p.notice("You have " + count + " books.");
            p.notice("They have been removed to protect this server. Please contact an admin for more info. Thanks. :)");
        }
    }
}