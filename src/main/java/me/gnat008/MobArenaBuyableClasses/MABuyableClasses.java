package me.gnat008.MobArenaBuyableClasses;

import com.sk89q.wepif.PermissionsResolverManager;
import me.gnat008.MobArenaBuyableClasses.config.ConfigurationManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.jar.JarFile;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;

/**
 * Created by Gnat008 on 5/12/2014.
 */
public class MABuyableClasses extends JavaPlugin {

    private static MABuyableClasses plugin;
    private static Logger log = Bukkit.getLogger();

    private boolean foundMA = false;

    private final ConfigurationManager configuration;

    private PluginManager pm;

    public static PluginDescriptionFile info;

    public MABuyableClasses() {
        this.configuration = new ConfigurationManager(this);
    }

    @Override
    public void onEnable() {
        // TODO: onEnable stuff
        info = getDescription();
        plugin = this;
        pm = getServer().getPluginManager();
    }

    @Override
    public void onDisable() {
        // TODO: onDisable stuff
    }

    private void setupListeners() {

    }

    /**
     * Prints a message to the console via the Minecraft logger.
     *
     * @param msg  The message to be sent to the console.
     * @param warn If the message is the warning level.
     */
    public static void printToConsole(String msg, boolean warn) {
        if (warn) {
            log.warning("[" + info.getName() + "] " + msg);
        } else {
            log.info("[" + info.getName() + "] " + msg);
        }
    }

    /**
     * Prints a message to a specified player. Message color depends on warn level.
     *
     * @param player The player to send the message to.
     * @param msg    The message to be sent to the player.
     * @param warn   If the message is a warning; RED if true, GREEN if false.
     */
    public static void printToPlayer(Player player, String msg, boolean warn) {
        String printer = "";

        if (warn) {
            printer += ChatColor.RED + "";
        } else {
            printer += ChatColor.GREEN + "";
        }

        printer += "[MABuyableClasses] " + msg;

        player.sendMessage(printer);
    }

    public boolean foundMA() {
        return foundMA;
    }

    /**
     * Checks if a player has permission.
     *
     * @param p    The player to check.
     * @param node The node to look for.
     * @return If the player has the permission node.
     */
    public boolean hasPermission(Player p, String node) {
        return p.hasPermission("mabuyableclasses." + node);
    }

    public ConfigurationManager getConfiguration() {
        return configuration;
    }

    public static MABuyableClasses getInstance() {
        return plugin;
    }

    /**
     * Get an array of groups that the player is in.
     *
     * @param p The player.
     * @return A String array of groups, or an empty array.
     */
    public String[] getGroups(Player p) {
        try {
            return PermissionsResolverManager.getInstance().getGroups(p);
        } catch (Throwable t) {
            t.printStackTrace();
            return new String[0];
        }
    }

    public void createDefaultConfiguration(File actual, String defaultName) {
        // Make parent directories.
        File parent = actual.getParentFile();
        if (!parent.exists()) {
            parent.mkdir();
        }

        if (actual.exists()) {
            return;
        }

        InputStream input = null;
        try {
            JarFile file = new JarFile(getFile());
            ZipEntry copy = file.getEntry("defaults/" + defaultName);
            if (copy == null) {
                throw new FileNotFoundException();
            }

            input = file.getInputStream(copy);
        } catch (IOException e) {
            log.severe("Unable to read default configuration: " + defaultName);
        }

        if (input != null) {
            FileOutputStream output = null;
            try {
                output = new FileOutputStream(actual);
                byte[] buf = new byte[8192];
                int length;

                while ((length = input.read(buf)) > 0) {
                    output.write(buf, 0, length);
                }

                printToConsole("Default configuration written: " + actual.getAbsolutePath(), false);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    input.close();
                } catch (IOException ignore) {

                }

                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException ignore) {

                }
            }
        }
    }
}
