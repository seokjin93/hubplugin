package seokjin91.hubplugin.tasks;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import seokjin91.hubplugin.commands.hub;
import seokjin91.hubplugin.commands.sethub;

import static org.bukkit.Bukkit.getConsoleSender;
import static org.bukkit.Bukkit.getServer;
import static seokjin91.hubplugin.commands.hub.player;

public class schedulehub extends BukkitRunnable {
    private seokjin91.hubplugin.main plugin;
    private static String plugin_prefix;
    public schedulehub(seokjin91.hubplugin.main plugin)
    {
        this.plugin = plugin;
    }
    @Override
    public void run() {
        try {
            plugin_prefix = "config.Pluginprefix";
            Location spawnlocation = (Location) plugin.getConfig().get("Spawnlocation");
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD + plugin.getConfig().getString(plugin_prefix) + " " + ChatColor.RED + spawnlocation);
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD + plugin.getConfig().getString(plugin_prefix) + " " + ChatColor.RED + player);
            player.teleport(spawnlocation);
        }
        catch (NullPointerException ignored){

        }

    }
}
