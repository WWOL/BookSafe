package mccarthy.brian.booksafe;

import net.canarymod.config.Configuration;
import net.visualillusionsent.utils.PropertiesFile;

/**
 * Contain and load various properties
 * @author Brian McCarthy
 *
 */
public class BookSafeSettings {

    private static PropertiesFile props;

    public static boolean banCrafting = true;
    public static boolean craftBanMsg = true;

    public static void setup() {
        props = Configuration.getPluginConfig(BookSafe.getInstance(), "Settings");
        if (props.getBoolean("useDefaults", false)) {
            // Do not load these values from the properties file, use defaults
            return;
        }
        banCrafting = props.getBoolean("banCrafting", banCrafting);
        craftBanMsg = props.getBoolean("craftBanMsg", craftBanMsg);

        props.save();
    }

}
