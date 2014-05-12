package me.gnat008.MobArenaBuyableClasses.config;

import me.gnat008.MobArenaBuyableClasses.MABuyableClasses;
import me.gnat008.MobArenaBuyableClasses.util.YAMLProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gnat008 on 5/12/2014.
 */
public class ConfigurationManager {

    private final String CONFIG_HEADER = "# InfiniteBlock's main configuration file\r\n" +
            "#\r\n" +
            "# About editing this file:\r\n" +
            "# - DO NOT USE TABS. You MUST use spaces or Bukkit will complain. If\r\n" +
            "#   you use an editor like Notepad++ (recommended for Windows users), you\r\n" +
            "#   must configure it to \"replace tabs with spaces.\" In Notepad++, this can\r\n" +
            "#   be changed in Settings > Preferences > Language Menu.\r\n" +
            "# - Don't get rid of the indents. They are indented so some entries are\r\n" +
            "#   in categories.\r\n" +
            "# - If you want to check the format of this file before putting it\r\n" +
            "#   into InfiniteBlocks, paste it into http://yaml-online-parser.appspot.com/\r\n" +
            "#   and see if it gives \"ERROR:\".\r\n" +
            "# - Lines starting with # are comments and so they are ignored.\r\n" +
            "#\r\n";

    private MABuyableClasses plugin;
    private YAMLProcessor config;

    public Map<String, String> hostKeys = new HashMap<String, String>();

    // Configuration values start:

    // Configuration values end.

    public ConfigurationManager(MABuyableClasses plugin) {
        this.plugin = plugin;
    }
}
