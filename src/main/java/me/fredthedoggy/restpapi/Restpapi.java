package me.fredthedoggy.restpapi;

import org.bstats.bukkit.Metrics;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

import static java.util.UUID.randomUUID;

public final class Restpapi extends JavaPlugin {

    FileConfiguration config = getConfig();
    SparkWrapper webServer;

    @Override
    public void onEnable() {
        Metrics metrics = new Metrics(this, 10708);

        webServer = new SparkWrapper();
        org.eclipse.jetty.util.log.Log.setLog(new NoLogging());
        this.getCommand("restpapi").setExecutor(new RestPapiCommand(this));
        this.getCommand("rpapi").setExecutor(new RestPapiCommand(this));
        loadWebServer();

    }

    @Override
    public void onDisable() {

        System.out.println("[RestPAPI] Disabled Webserver");

    }

    public void loadWebServer() {
        config.addDefault("port", 8080);
        List<String> defaultTokens = Arrays.asList(randomUUID().toString(), randomUUID().toString());
        config.addDefault("tokens", defaultTokens);
        config.options().copyDefaults(true);
        saveConfig();
        webServer.create(config.getInt("port"), config.getStringList("tokens"));
        System.out.println("[RestPAPI] Enabled On Port " + config.getInt("port"));
    }

}
