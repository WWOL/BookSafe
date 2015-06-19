package mccarthy.brian.booksafe.commands;

import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.Command;
import net.canarymod.commandsys.CommandListener;

/**
 * List of all commands, used for registering with the system
 * @author Brian McCarthy
 *
 */
public class CommandList implements CommandListener {

    @Command(aliases = {"bookcount"},
                        description = "Get the stored count of books that have been removed",
                        permissions = {"booksafe.count"},
                        toolTip = "/bookcount",
                        version = 2,
                        min = 0,
                        max = 1)
    public void exampleCommand(MessageReceiver caller, String[] parameters) {
        new CountCommand().execute(caller, parameters);
    }

}