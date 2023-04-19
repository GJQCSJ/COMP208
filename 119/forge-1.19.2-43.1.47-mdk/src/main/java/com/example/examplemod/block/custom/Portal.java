package com.example.examplemod.block.custom;

import com.example.examplemod.world.dimensions.CustomDimension;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.util.ITeleporter;
import java.util.function.Function;



public class Portal extends Block {
    private static final VoxelShape SHAPE = Shapes.box(0, 0, 0, 1, .8, 1);
    // The shape of this portal block should be similar to a step
    public Portal(){
        super(Properties.of(Material.PORTAL)
                .sound(SoundType.STONE) // The portal blocks are invincible so cannot be destroyed
                .strength(-1f, 1214651.0f)
                .noLootTable());
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context){
        return SHAPE;
    }

    //This function will make the block able to teleport an entity if that entity is inside the block
    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos pos, Entity entity){
        if (entity instanceof ServerPlayer player){
            if (level.dimension().equals(CustomDimension.CUSTOM_DIM)){
                teleportTo(player, pos.north(), Level.OVERWORLD);
            } else {
                teleportTo(player, pos.north(), CustomDimension.CUSTOM_DIM);
            }
        }
    }

    public static void teleport(ServerPlayer entity, ServerLevel destination, BlockPos pos, boolean findTop) {
        entity.changeDimension(destination, new ITeleporter() {
            @Override
            public net.minecraft.world.entity.Entity placeEntity(net.minecraft.world.entity.Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, net.minecraft.world.entity.Entity> repositionEntity) {
                entity = repositionEntity.apply(false);
                int y = pos.getY();
                if (findTop) {
                    y = destination.getHeight(Heightmap.Types.WORLD_SURFACE_WG, pos.getX(), pos.getZ());
                }
                entity.teleportTo(pos.getX(), y, pos.getZ());
                return entity;
            }
        });
    }


    private void teleportTo(ServerPlayer player, BlockPos pos, ResourceKey<Level> id){
        ServerLevel world = player.getServer().getLevel(id);
        teleport(player, world, new BlockPos(pos.getX(), pos.getY(), pos.getZ()), true);
    }
}
