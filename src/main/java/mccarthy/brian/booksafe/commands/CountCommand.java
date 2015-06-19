package mccarthy.brian.booksafe.commands;

import mccarthy.brian.booksafe.BookSafe;
import net.canarymod.ToolBox;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;

/**
 * example command
 * @author Brian McCarthy
 *
 */
public class CountCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (parameters.length == 0) {
            if (!(caller instanceof Player)) {
                caller.notice("Can not use without a player!");
                if (caller.hasPermission("booksafe.count.other")) {
                    caller.notice("Try /bookcount <player> instead");
                }
                return;
            }
            Player p = (Player) caller;
            int count = BookSafe.getInstance().getActions().getCount(p.getUUIDString());
            caller.message("You have " + count + " books.");
        } else {
            if (!caller.hasPermission("booksafe.count.other")) {
                return;
            }
            String uuid = ToolBox.usernameToUUID(parameters[0]);
            if (uuid == null) {
                caller.notice("Unknown username!");
                return;
            }
            int count = BookSafe.getInstance().getActions().getCount(uuid);
            String suffix = count == 0 ? " (or is invalid)." : ".";
            caller.message(parameters[0] + " has " + count + " books" + suffix);
        }
    }

}