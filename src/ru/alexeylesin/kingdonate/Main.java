package ru.alexeylesin.kingdonate;

import org.bukkit.plugin.java.*;
import org.bukkit.command.*;
import org.bukkit.configuration.file.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import ru.tehkode.permissions.bukkit.*;
import java.util.*;
import ru.tehkode.permissions.*;

public class Main extends JavaPlugin
{
    private static Main instance;
    public static String EMPTY_DONATE;
    public static String PERMISSION_MESSAGE;
    public static String USAGE;
    public static String PREFIX;
    public static String EXCELLENT_GIVE;
    public static boolean chat_msg;
    public static boolean title_msg;
    
    public static Main getInstance() {
        return Main.instance;
    }
    
    public void onEnable() {
        (Main.instance = this).saveDefaultConfig();
        loadConfig();
        this.getCommand("givedonate").setExecutor((CommandExecutor)new GiveCmd());
    }
    
    public static String replacer(final String what) {
        return ChatColor.translateAlternateColorCodes('&', what);
    }
    
    public static void loadConfig() {
        final FileConfiguration cfg = getInstance().getConfig();
        Main.PREFIX = cfg.getString("PREFIX");
        Main.EMPTY_DONATE = replacer(cfg.getString("EMPTY_DONATE").replace("%prefix%", Main.PREFIX));
        Main.PERMISSION_MESSAGE = replacer(cfg.getString("PERMISSION_MESSAGE").replace("%prefix%", Main.PREFIX));
        Main.USAGE = replacer(cfg.getString("USAGE").replace("%prefix%", Main.PREFIX));
        Main.EXCELLENT_GIVE = replacer(cfg.getString("EXCELLENT_GIVE").replace("%prefix%", Main.PREFIX));
        Main.chat_msg = cfg.getBoolean("chat_msg");
        Main.title_msg = cfg.getBoolean("title_msg");
    }
    
    public static boolean checkArgToGroup(final String group) {
        return getInstance().getConfig().get("groups." + group) != null;
    }
    
    public static void sendMessage(final String group, final String playerName) {
        final String TITLE = replacer(getInstance().getConfig().getString("groups." + group + ".title").replace("%player%", playerName).replace("%prefix%", Main.PREFIX));
        final String SUBTITLE = replacer(getInstance().getConfig().getString("groups." + group + ".subtitle").replace("%player%", playerName).replace("%prefix%", Main.PREFIX));
        final String message = replacer(getInstance().getConfig().getString("groups." + group + ".message").replace("%player%", playerName).replace("%prefix%", Main.PREFIX));
        for (final Player all : Bukkit.getOnlinePlayers()) {
            if (Main.chat_msg) {
                all.sendMessage(message);
            }
            if (Main.title_msg) {
                TitleAPI.sendTitle(all, 10, 100, 10, TITLE, SUBTITLE);
            }
        }
    }
    
    public static void setGroup(final String group, final String playerName) {
        final PermissionManager manager = PermissionsEx.getPermissionManager();
        final PermissionUser user = manager.getUser(playerName);
        final List<PermissionGroup> groups = new ArrayList<PermissionGroup>();
        groups.add(manager.getGroup(group));
        user.setParents((List)groups);
        sendMessage(group, playerName);
    }
}
