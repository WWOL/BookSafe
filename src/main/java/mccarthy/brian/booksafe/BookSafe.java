package mccarthy.brian.booksafe;

import mccarthy.brian.booksafe.commands.CommandList;
import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.commandsys.CommandDependencyException;
import net.canarymod.plugin.Plugin;

/**
 * Main plugin class
 * @author Brian McCarthy
 *
 */
public class BookSafe extends Plugin {

    private static BookSafe INSTANCE;
    private BookSafeActions actions;

    public BookSafe() {
        INSTANCE = this;
    }

    @Override
    public boolean enable() {
        actions = new BookSafeActions();
        BookSafeSettings.setup();

        Canary.hooks().registerListener(new BookSafeListener(), this);
        try {
           Canary.commands().registerCommands(new CommandList(), this, false);
        } catch (CommandDependencyException e) {
            getLogman().warn("Could not load " + getName() + " because of a command dependency exception!");
            return false;
        }
        for (Player p : Canary.getServer().getPlayerList()) {
            actions.removeFromInventory(p);
        }
        return true;
    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub

    }

    public static BookSafe getInstance() {
        return INSTANCE;
    }

    public BookSafeActions getActions() {
        return actions;
    }

}