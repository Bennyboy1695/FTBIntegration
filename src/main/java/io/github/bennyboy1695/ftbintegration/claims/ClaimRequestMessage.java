package io.github.bennyboy1695.ftbintegration.claims;

import com.feed_the_beast.ftblib.lib.io.DataIn;
import com.feed_the_beast.ftblib.lib.io.DataOut;
import com.feed_the_beast.ftblib.lib.net.MessageToServer;
import com.feed_the_beast.ftblib.lib.net.NetworkWrapper;
import net.minecraft.entity.player.EntityPlayerMP;

public class ClaimRequestMessage extends MessageToServer {

    public int startX, startZ;

    public ClaimRequestMessage() {
    }

    public ClaimRequestMessage(int startX, int startZ) {
        this.startX = startX;
        this.startZ = startZ;
    }

    @Override
    public NetworkWrapper getWrapper() {
        return FTBClaimsManager.CLAIMS;
    }

    @Override
    public void readData(DataIn data) {
        startX = data.readVarInt();
        startZ = data.readVarInt();
    }

    @Override
    public void writeData(DataOut data) {
        data.writeVarInt(startX);
        data.writeVarInt(startZ);
    }

    @Override
    public void onMessage(EntityPlayerMP player) {
        /*if (ClaimedChunks.isActive()) {*/
            new ClaimUpdateMessage(startX, startZ, player).sendTo(player);
        /*}*/
    }
}
