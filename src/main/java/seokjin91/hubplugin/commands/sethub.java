package seokjin91.hubplugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class sethub implements CommandExecutor {
    private static seokjin91.hubplugin.main plugin;
    public sethub(seokjin91.hubplugin.main plugin)
    {
        this.plugin = plugin;
    }
    public Location player_coords;
    public static Location hub_coords;
    private static Player player;
    private static String plugin_prefix;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        player = (Player) sender;
        plugin_prefix = "config.Pluginprefix";
        if (sender instanceof Player)
        {

            player_coords = player.getLocation();
            hub_coords = player_coords;
            plugin.getConfig().set("Spawnlocation", hub_coords);
            plugin.saveConfig();
            plugin.reloadConfig();
            player.sendMessage(ChatColor.GOLD+ plugin.getConfig().getString(plugin_prefix) +ChatColor.GREEN+" Se ha establecido el spawn correctamente");

        }
        else
        {
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD+ plugin.getConfig().getString(plugin_prefix) + ChatColor.RED+" Solo puedes ejecutar este comando como un jugador!");

        }
        return true;
    }
    public static Location getHub_coords() {return hub_coords;}
    public static Player getPlayer() {return player;}
}
