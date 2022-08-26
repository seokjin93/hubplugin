package seokjin91.hubplugin;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import seokjin91.hubplugin.commands.mainfile;
import seokjin91.hubplugin.commands.sethub;
import seokjin91.hubplugin.commands.hub;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import java.io.File;

public final class main extends JavaPlugin {
    public String Rutaconfig;
    private static String plugin_prefix;
    private static Economy econ = null;
    public void registerCommands() {
        getCommand("simplehub").setExecutor(new mainfile(this));
        getCommand("spawn").setExecutor(new hub(this));
        getCommand("sethub").setExecutor(new sethub(this));

    }
    public void registerConfig()
    {
        File config = new File(this.getDataFolder(), "config.yml");
        Rutaconfig = config.getPath();
        if(!config.exists())
        {
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    @Override
    public void onEnable() {
        registerConfig();
        plugin_prefix = "config.Pluginprefix";
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD+getConfig().getString(plugin_prefix)+ ChatColor.GREEN+" Se ha activado el plugin correctamente");
        registerCommands();
        if (!setupEconomy() ) {
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD+getConfig().getString(plugin_prefix)+ChatColor.RED+String.format(" Plugin deshabilitado, Vault no se ha encontrado!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD+getConfig().getString(plugin_prefix)+ ChatColor.RED+" Se ha desactivado el plugin!");
    }
    public static Economy getEconomy() {
        return econ;
    }
}
