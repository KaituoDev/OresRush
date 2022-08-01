package fun.kaituo.oresrush.generator;

import net.minecraft.world.level.World;
import net.minecraft.world.level.chunk.Chunk;

public class SimpleChunkGenerator {
    //public static final
    private World world;
    
    public SimpleChunkGenerator() {
        this.world = net.minecraft.server.MinecraftServer
    }
    
    public void generateChunk(int chunkX, int chunkZ) {
        generateChunk(world.getChunk(chunkX, chunkZ));
    }
    
    public void generateChunk(Chunk chunk) {
        generateNoise(chunk);
    }
    
    public void generateNoise(Chunk chunk) {}
}
