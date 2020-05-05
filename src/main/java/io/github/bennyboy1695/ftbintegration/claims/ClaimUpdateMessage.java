package io.github.bennyboy1695.ftbintegration.claims;

import com.feed_the_beast.ftblib.lib.io.DataOut;
import com.feed_the_beast.ftblib.lib.net.MessageToClient;
import com.feed_the_beast.ftblib.lib.net.NetworkWrapper;
import io.github.bennyboy1695.ftbintegration.FTBIntegration;
import io.github.bennyboy1695.ftbintegration.claims.util.ClientClaimedChunks;
import it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

public class ClaimUpdateMessage extends MessageToClient {

    public int startX, startZ, claimedChunks, loadedChunks, maxClaimedChunks, maxLoadedChunks;
    public Short2ObjectOpenHashMap<ClientClaimedChunks.Team> teams;
    private FTBIntegration plugin = FTBIntegration.getInstance();

    public ClaimUpdateMessage() {
    }

    @Override
    public NetworkWrapper getWrapper() {
        return FTBClaimsManager.CLAIMS;
    }

    public ClaimUpdateMessage(int startX, int startZ, EntityPlayer player) {
        this.startX = startX;
        this.startZ = startZ;
        this.claimedChunks = 0;
        this.loadedChunks = 0;
        this.maxClaimedChunks = 69;
        this.maxLoadedChunks = 0;
        Player spongePlayer = Sponge.getServer().getPlayer(player.getUniqueID()).get();
    }

    @Override
    public void writeData(DataOut data) {
        data.writeVarInt(startX);
        data.writeVarInt(startZ);
        data.writeVarInt(claimedChunks);
        data.writeVarInt(loadedChunks);
        data.writeVarInt(maxClaimedChunks);
        data.writeVarInt(maxLoadedChunks);
        data.writeCollection(teams.values(), ClientClaimedChunks.Team.SERIALIZER);
    }

}
