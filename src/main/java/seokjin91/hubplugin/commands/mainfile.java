package seokjin91.hubplugin.commands;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import seokjin91.hubplugin.main;

import static org.bukkit.Bukkit.getServer;

public class mainfile implements CommandExecutor {
    private seokjin91.hubplugin.main plugin;
    public mainfile(seokjin91.hubplugin.main plugin)
    {
        this.plugin = plugin;
    }
    private static String plugin_prefix;
    private Economy econ;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        plugin_prefix = "config.Pluginprefix";
        econ = main.getEconomy();
            if (args.length > 0)
            {
                if(args[0].equalsIgnoreCase("reload"))
                {
                    plugin.reloadConfig();
                    sender.sendMessage(ChatColor.GOLD+plugin.getConfig().getString(plugin_prefix)+ChatColor.GREEN+" El plugin ha sido recargado correctamente!");
                }
            }
        return false;
    }
}
