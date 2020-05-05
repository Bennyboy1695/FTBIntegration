package io.github.bennyboy1695.ftbintegration.claims;

import com.feed_the_beast.ftblib.lib.net.NetworkWrapper;
import io.github.bennyboy1695.ftbintegration.FTBIntegration;

public class FTBClaimsManager {

    private FTBIntegration plugin;
    /*    private ChannelBinding.RawDataChannel claimsChannel;*/
    static final NetworkWrapper CLAIMS = NetworkWrapper.newWrapper("ftbutilities_claims");

    public FTBClaimsManager(FTBIntegration plugin) {
        this.plugin = plugin;
    }

    public void init() {
        CLAIMS.register(new ClaimUpdateMessage());
        CLAIMS.register(new ClaimRequestMessage());
        CLAIMS.register(new ClaimModifyMessage());
/*        claimsChannel = Sponge.getChannelRegistrar().createRawChannel(plugin, "ftbutilities_claims");
        claimsChannel.addListener(new FTBClaimsListener(plugin, claimsChannel));*/
    }

/*    public ChannelBinding.RawDataChannel getClaimsChannel() {
        return claimsChannel;
    }*/
}
