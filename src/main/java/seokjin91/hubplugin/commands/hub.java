package seokjin91.hubplugin.commands;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import seokjin91.hubplugin.main;
import seokjin91.hubplugin.tasks.schedulehub;
import java.util.Timer;
import static org.bukkit.Bukkit.getServer;
public class hub implements CommandExecutor {
    private static seokjin91.hubplugin.main plugin;
    public hub(seokjin91.hubplugin.main plugin)
    {
        this.plugin = plugin;
    }
    private static Location hub_coords;
    private static Timer timer;
    public static Player player;
    public static String jugador;
    private static String plugin_prefix;
    private static Double teleport_cost;
    private static Economy economy;
    private static String location_path;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        economy = main.getEconomy();
        player = (Player) sender;
        jugador = player.getName();
        location_path = "Spawnlocation";
        plugin_prefix = "config.Pluginprefix";
        teleport_cost = (Double) plugin.getConfig().get("config.teleportCost");
        Location spawnlocation =  (Location) plugin.getConfig().get(location_path);
        double playerEco = economy.getBalance((OfflinePlayer) player);
        if (sender instanceof Player)
        {
            if (playerEco >= teleport_cost && !(spawnlocation == null)) {
                economy.withdrawPlayer((OfflinePlayer) player,teleport_cost);
                player.sendMessage(ChatColor.GOLD + plugin.getConfig().getString(plugin_prefix) + ChatColor.GREEN + " Tienes dinero suficiente para el viaje, teletransportando..");
                BukkitTask teleport = new schedulehub(plugin).runTaskLater(plugin, 60);
            }
            else
            {
                player.sendMessage(ChatColor.GOLD + plugin.getConfig().getString(plugin_prefix) + ChatColor.RED + " No tienes suficiente dinero para el viaje, o el spawn no esta establecido!");
            }
        }
        else
        {
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD+plugin.getConfig().getString(plugin_prefix)+ChatColor.RED+" Solo puedes ejecutar este comando como un jugador!");
        }

        return false;

    }
    public static Location getHub_coords() {return hub_coords;}

}
