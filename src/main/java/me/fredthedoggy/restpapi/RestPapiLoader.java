package me.fredthedoggy.restpapi;

import java.util.Arrays;
import java.util.List;

import static java.util.UUID.randomUUID;

public class RestPapiLoader {
    private final Restpapi parent;

    public RestPapiLoader(Restpapi parent) {
        this.parent = parent;
    }

    public void enable() {
        this.parent.webServer = new SparkWrapper();
        org.eclipse.jetty.util.log.Log.setLog(new NoLogging());
        this.parent.getCommand("restpapi").setExecutor(new RestPapiCommand(this.parent));
        this.parent.getCommand("rpapi").setExecutor(new RestPapiCommand(this.parent));
        loadWebServer();
    }

    public void disable() {
        System.out.println("[RestPAPI] Disabled Webserver");
    }

    public void loadWebServer() {
        this.parent.config.addDefault("port", 8080);
        List<String> defaultTokens = Arrays.asList(randomUUID().toString(), randomUUID().toString());
        this.parent.config.addDefault("tokens", defaultTokens);
        this.parent.config.options().copyDefaults(true);
        this.parent.saveConfig();
        this.parent.webServer.create(this.parent.config.getInt("port"), this.parent.config.getStringList("tokens"));
        System.out.println("[RestPAPI] Enabled On Port " + this.parent.config.getInt("port"));
    }
}
