package mccarthy.brian.booksafe;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.ItemType;
import net.canarymod.hook.HookHandler;
import net.canarymod.hook.player.ConnectionHook;
import net.canarymod.hook.player.CraftHook;
import net.canarymod.plugin.PluginListener;

/**
 * Main listener class
 * @author Brian McCarthy
 *
 */
public class BookSafeListener implements PluginListener {

    @HookHandler
    public void onLogin(ConnectionHook hook) {
        Player p = hook.getPlayer();
        BookSafe.getInstance().getActions().removeFromInventory(p);

    }

    @HookHandler
    public void onCraft(CraftHook hook) {
        if (hook.getRecipeResult() != null) {
            if (hook.getRecipeResult().getType() == ItemType.BookAndQuill) {
                if (BookSafeSettings.banCrafting) {
                    hook.setCanceled();
                    if (BookSafeSettings.craftBanMsg) {
                        hook.getPlayer().notice("You cannot craft book and quill at this time.");
                        hook.getPlayer().notice("They have been removed to protect this server. Please contact an admin for more info. Thanks. :)");
                    }
                }
            }
        }
    }

}