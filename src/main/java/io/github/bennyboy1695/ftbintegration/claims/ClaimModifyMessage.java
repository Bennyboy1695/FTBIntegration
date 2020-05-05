package io.github.bennyboy1695.ftbintegration.claims;

import com.feed_the_beast.ftblib.lib.io.DataIn;
import com.feed_the_beast.ftblib.lib.io.DataOut;
import com.feed_the_beast.ftblib.lib.net.MessageToServer;
import com.feed_the_beast.ftblib.lib.net.NetworkWrapper;
import io.github.bennyboy1695.ftbintegration.FTBIntegration;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.ChunkPos;

import java.util.Collection;

public class ClaimModifyMessage extends MessageToServer {

    public static final int CLAIM = 0;
    public static final int UNCLAIM = 1;
    public static final int LOAD = 2;
    public static final int UNLOAD = 3;

    private int startX, startZ;
    private int action;
    private Collection<ChunkPos> chunks;

    private FTBIntegration plugin = FTBIntegration.getInstance();

    public ClaimModifyMessage() {

    }

    public ClaimModifyMessage(int sx, int sz, int a, Collection<ChunkPos> c) {
        this.startX = sx;
        this.startZ = sz;
        this.action = a;
        this.chunks = c;
    }

    @Override
    public NetworkWrapper getWrapper() {
        return FTBClaimsManager.CLAIMS;
    }

    @Override
    public void writeData(DataOut data) {
        data.writeVarInt(startX);
        data.writeVarInt(startZ);
        data.writeVarInt(action);
        data.writeCollection(chunks, DataOut.CHUNK_POS);
    }

    @Override
    public void readData(DataIn data) {
        startX = data.readVarInt();
        startZ = data.readVarInt();
        action = data.readVarInt();
        chunks = data.readCollection(null, DataIn.CHUNK_POS);
    }

    @Override
    public void onMessage(EntityPlayerMP player) {
        plugin.getLogger().info("Got message to modify chunks by " + player.getName());
        switch (action) {
            case CLAIM:
                plugin.getLogger().info("Action = Claim");
                break;
            case UNCLAIM:
                plugin.getLogger().info("Action = UnClaim");
                break;
            case LOAD:
                plugin.getLogger().info("Action = Load");
                break;
            case UNLOAD:
                plugin.getLogger().info("Action = UnLoad");
                break;
        }
    }

}
