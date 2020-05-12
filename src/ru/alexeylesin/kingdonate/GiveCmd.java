package ru.alexeylesin.kingdonate;

import org.bukkit.command.*;
import org.bukkit.entity.*;

public class GiveCmd implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player pa = (Player)sender;
            if (!pa.hasPermission("kingdonate.give")) {
                pa.sendMessage(Main.PERMISSION_MESSAGE);
                return true;
            }
        }
        switch (args.length) {
            case 0: {
                sender.sendMessage(Main.USAGE);
                break;
            }
            case 2: {
                final String group = args[1];
                if (!Main.checkArgToGroup(group)) {
                    sender.sendMessage(Main.EMPTY_DONATE);
                    break;
                }
                final String player = args[0];
                Main.setGroup(group, player);
                sender.sendMessage(Main.EXCELLENT_GIVE.replace("%player%", player).replace("%group%", group));
                break;
            }
            default: {
                sender.sendMessage(Main.USAGE);
                break;
            }
        }
        return false;
    }
}
