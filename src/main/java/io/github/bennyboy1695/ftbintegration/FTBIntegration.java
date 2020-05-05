package io.github.bennyboy1695.ftbintegration;

import com.google.inject.Inject;
import io.github.bennyboy1695.ftbintegration.claims.FTBClaimsManager;
import me.ryanhamshire.griefprevention.GriefPrevention;
import me.ryanhamshire.griefprevention.api.GriefPreventionApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = "ftbintegration", dependencies = {@Dependency(id = "griefprevention")})
public class FTBIntegration {

    @Inject
    private Logger logger = LoggerFactory.getLogger("FTBIntegration");

    private static FTBIntegration instance;

    private GriefPreventionApi griefPreventionApi;

    @Listener
    public void onPreInit(GamePreInitializationEvent event) {
        instance = this;
        new FTBClaimsManager(this).init();
    }

    @Listener
    public void onServerStarting(GameStartingServerEvent event) {
        griefPreventionApi = GriefPrevention.getApi();
    }

    public Logger getLogger() {
        return logger;
    }

    public GriefPreventionApi getGriefPreventionApi() {
        return griefPreventionApi;
    }

    public static FTBIntegration getInstance() {
        return instance;
    }
}
