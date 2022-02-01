package me.fredthedoggy.restpapi;

import com.heretere.hdl.dependency.maven.annotation.MavenDependency;
import com.heretere.hdl.exception.DependencyLoadException;
import com.heretere.hdl.relocation.annotation.Relocation;
import com.heretere.hdl.spigot.DependencyPlugin;
import org.bstats.bukkit.Metrics;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@MavenDependency("org|eclipse|jetty:jetty-servlet:9.4.31.v20200723")
@MavenDependency("org|eclipse|jetty:jetty-io:9.4.31.v20200723")
@MavenDependency("org|eclipse|jetty:jetty-server:9.4.31.v20200723")
@MavenDependency("org|eclipse|jetty|websocket:websocket-common:9.4.31.v20200723")
@MavenDependency("org|bstats:bstats-bukkit:2.2.1")
@MavenDependency("org|eclipse|jetty|websocket:websocket-api:9.4.31.v20200723")
@MavenDependency("org|slf4j:slf4j-api:1.7.25")
@MavenDependency("org|eclipse|jetty:jetty-client:9.4.31.v20200723")
@MavenDependency("org|eclipse|jetty:jetty-jmx:9.4.31.v20200723")
@MavenDependency("org|jetbrains:annotations:19.0.0")
@MavenDependency("org|eclipse|jetty|websocket:websocket-server:9.4.31.v20200723")
@MavenDependency("org|eclipse|jetty:jetty-webapp:9.4.31.v20200723")
@MavenDependency("org|eclipse|jetty:jetty-xml:9.4.31.v20200723")
@MavenDependency("org|eclipse|jetty:jetty-security:9.4.31.v20200723")
@MavenDependency("com|sparkjava:spark-core:2.9.3")
@MavenDependency("org|eclipse|jetty:jetty-util:9.4.31.v20200723")
@MavenDependency("org|eclipse|jetty:jetty-http:9.4.31.v20200723")
@MavenDependency("org|bstats:bstats-base:2.2.1")
@MavenDependency("org|eclipse|jetty|websocket:websocket-servlet:9.4.31.v20200723")
@MavenDependency("org|eclipse|jetty|websocket:websocket-client:9.4.31.v20200723")
@MavenDependency("javax|servlet:javax|servlet-api:3.1.0")
//relocate 'org.bstats', 'me.fredthedoggy.restpapi'
@Relocation(from = "org|bstats", to = "me|fredthedoggy|restpapi")
public final class Restpapi extends DependencyPlugin {

    private RestPapiLoader loader;
    FileConfiguration config = getConfig();
    SparkWrapper webServer;

    @Override protected void fail(
            @NotNull Set<@NotNull Throwable> genericErrors,
            @NotNull Set<@NotNull DependencyLoadException> dependencyErrors
    ) {

    }

    @Override protected void load() {

    }

    @Override
    public void enable() {
        Metrics metrics = new Metrics(this, 10708);

        this.loader = new RestPapiLoader(this);
        this.loader.enable();
    }

    @Override
    public void disable() {
        this.loader.disable();
    }

    public RestPapiLoader getLoader() {
        return loader;
    }
}
