package com.example.examplemod.block;

import com.example.examplemod.comp208mod;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS1 =
            DeferredRegister.create(ForgeRegistries.BLOCKS, comp208mod.MOD_ID);


    public static void register(IEventBus eventBus){
        BLOCKS1.register(eventBus);
    }
}
