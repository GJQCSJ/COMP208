package com.example.examplemod.block;

import com.example.examplemod.block.custom.*;
import com.example.examplemod.comp208mod;
import com.example.examplemod.item.ModCreativeModeTab;
import com.example.examplemod.world.feature.tree.BlueMapleTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.example.examplemod.item.ModItems.ITEMS;

public class ModBlocks {

    /*
    * Block inside the world -> blockstate
    * Block itself -> block class -> a type of the block */
    private static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, comp208mod.MOD_ID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, comp208mod.MOD_ID);
    private static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, comp208mod.MOD_ID);
    public static final RegistryObject<Powerplant> MANA_EXTRACTOR_BLOCK = BLOCKS.register("mana_extractor", Powerplant::new);
    public static final RegistryObject<BlockEntityType<PowerplantBE>> MANA_EXTRACTOR_BE = BLOCK_ENTITIES.register("mana_extractor",
            () -> BlockEntityType.Builder.of(PowerplantBE::new, MANA_EXTRACTOR_BLOCK.get()).build(null));
    public static final RegistryObject<MenuType<PowerplantContainer>> MANA_CONTAINER = MENU_TYPES.register("mana_extractor",
            () -> IForgeMenuType.create(((windowId, inv, data) -> new PowerplantContainer(windowId, data.readBlockPos(), inv, inv.player))));

    public static final RegistryObject<Generator> GENERATOR = BLOCKS.register("generator", Generator::new);
    public static final RegistryObject<BlockEntityType<GeneratorBE>> GENERATOR_BE = BLOCK_ENTITIES.register("generator", () -> BlockEntityType.Builder.of(GeneratorBE::new, GENERATOR.get()).build(null));
    public static final BlockBehaviour.Properties BLOCK_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(2f).requiresCorrectToolForDrops();
    public static final RegistryObject<Block> AUTO_TEST_BLOCK =
            BLOCKS.register("auto_json_testing_block", () -> new Block(BLOCK_PROPERTIES));

    public static final RegistryObject<Block> TEMPLATE_BLOCK =registerBlock("test_block_template",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1f)),
            ModCreativeModeTab.Test_Demo);
                /*Create and register a new block, properties inside brackets:
            the name of it,
             supplier and type or
              in game properties (may in hexadecimal)
              of block*/

    public static final RegistryObject<Block> SPODUMENE_BLOCK = registerBlock("spodumene_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).
                    jumpFactor(10).strength(6f, 9)
                    .requiresCorrectToolForDrops()),
            ModCreativeModeTab.Test_Demo);


    public static final RegistryObject<Block> SPODUMENE_ORE =registerBlock("spodumene_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops(),
                    UniformInt.of(3, 9)
                    /*UniformInt.of will let block drop experience when being excavated,
                           ,two values means the range of experience it would drop*/
                    ),
            ModCreativeModeTab.Test_Demo);

    public static final RegistryObject<Block> DEEPSLATE_SPODUMENE_ORE =registerBlock("deepslate_spodumene_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops(),
                    UniformInt.of(3, 7)
            ),
            ModCreativeModeTab.Test_Demo);

    public static final RegistryObject<Block> STARSTONE_BLOCK = registerBlock("starstone_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).
                    strength(10).requiresCorrectToolForDrops()),
            ModCreativeModeTab.Test_Demo);

    public static final RegistryObject<Block> STARSTONE_ORE =registerBlock("starstone_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(10f).requiresCorrectToolForDrops(),
                    UniformInt.of(9, 15)
            ),
            ModCreativeModeTab.Test_Demo);

    public static final RegistryObject<Block> DEEPSLATE_STARSTONE_ORE =registerBlock("deepslate_starstone_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(10f).requiresCorrectToolForDrops(),
                    UniformInt.of(9, 15)
            ),
            ModCreativeModeTab.Test_Demo);
    public static final RegistryObject<Block> JUMP_BLOCK = registerBlock("jump_block",
            () -> new JumpBlock(BlockBehaviour.Properties
                    .of(Material.LEAVES)
                    .strength(6F)
            ),
                    ModCreativeModeTab.Test_Demo);

    public static final RegistryObject<Block> BLUE_MAPLE_LOG = registerBlock("blue_maple_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
                    .copy(Blocks.OAK_LOG).requiresCorrectToolForDrops()), ModCreativeModeTab.Test_Demo);

    public static final RegistryObject<Block> BLUE_MAPLE_WOOD = registerBlock("blue_maple_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
                    .copy(Blocks.OAK_WOOD).requiresCorrectToolForDrops()), ModCreativeModeTab.Test_Demo);

    public static final RegistryObject<Block> STRIPPED_BLUE_MAPLE_LOG = registerBlock("stripped_blue_maple_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
                    .copy(Blocks.STRIPPED_OAK_LOG).requiresCorrectToolForDrops()), ModCreativeModeTab.Test_Demo);

    public static final RegistryObject<Block> STRIPPED_BLUE_MAPLE_WOOD = registerBlock("stripped_blue_maple_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
                    .copy(Blocks.STRIPPED_OAK_WOOD).requiresCorrectToolForDrops()), ModCreativeModeTab.Test_Demo);

    public static final RegistryObject<Block> BLUE_MAPLE_PLANKS = registerBlock("blue_maple_planks",
            () -> new Block(BlockBehaviour.Properties
                    .copy(Blocks.OAK_PLANKS).requiresCorrectToolForDrops()) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            }, ModCreativeModeTab.Test_Demo);


    public static final RegistryObject<Block> BLUE_MAPLE_LEAVES = registerBlock("blue_maple_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties
                    .copy(Blocks.OAK_LEAVES).requiresCorrectToolForDrops()) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            }, ModCreativeModeTab.Test_Demo);

    public static final RegistryObject<Block> BLUE_MAPLE_SAPLING = registerBlock("blue_maple_sapling",
            () -> new SaplingBlock(new BlueMapleTreeGrower(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), ModCreativeModeTab.Test_Demo);


    public static final RegistryObject<Block> CUSTOM_LAMP = registerBlock("block_custom_lamp",
            () -> new CustomLampBlock(BlockBehaviour.Properties
                    .of(Material.STONE)
                    .requiresCorrectToolForDrops()
                    .lightLevel(state -> state.getValue(CustomLampBlock.LIT) ? 15 : 0)
                    .strength(6F)
                    /* 15 means light level when activated, 0 means when deactivated */
            ),
            ModCreativeModeTab.Test_Demo);

    public static final RegistryObject<Block> BERRY_BLOCK = BLOCKS.register("custom_berry_crop",
            () -> new BerryCropBlock(BlockBehaviour.Properties
                    .copy(Blocks.WHEAT)
            )
    );

    public static final RegistryObject<Block> JASMINE = registerBlock("jasmine",
            () -> new FlowerBlock(MobEffects.GLOWING, 4,
                    BlockBehaviour.Properties.copy(Blocks.DANDELION)
            ), ModCreativeModeTab.Test_Demo);

    public static final RegistryObject<Block> PURPLEGRASS = registerBlock("purplegrass",
            () -> new FlowerBlock(MobEffects.GLOWING, 4,
                    BlockBehaviour.Properties.copy(Blocks.DANDELION)
            ), ModCreativeModeTab.Test_Demo);

    public static final RegistryObject<Block> POTTED_JASMINE = BLOCKS.register("potted_jasime",
            () -> new FlowerPotBlock(() ->((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.JASMINE,
                    BlockBehaviour.Properties.copy(Blocks.DANDELION)
            )
    );

    public static final RegistryObject<Block> POTTED_PURPLEGRASS = BLOCKS.register("potted_purplegrass",
            () -> new FlowerPotBlock(() ->((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.PURPLEGRASS,
                    BlockBehaviour.Properties.copy(Blocks.DANDELION)
            )
    );

    public static final RegistryObject<Block> PORTAL_BLOCK = BLOCKS.register("portal", Portal::new);



//    public static final RegistryObject<>

    private static <T extends Block> RegistryObject<T> registerBlock
            (String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
        /*register blocks*/
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(
            String name, RegistryObject<T> block, CreativeModeTab tab){
        return ITEMS.register(
                name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }




//    public static class Tags {
//        public static final TagKey<Block> NEEDS_DIAMOND_TOOL = create("mineable/needs_diamond_tool");
//
//        private static TagKey<Block> create(String location){
//            return BlockTags.create(new ResourceLocation(comp208mod.MOD_ID, location));
//        }
//        private static TagKey<Block> createForge(String location){
//            return BlockTags.create(new ResourceLocation("forge", location));
//        }
//    }


    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
        BLOCK_ENTITIES.register(eventBus);
        MENU_TYPES.register(eventBus);
    }

//    private static <T extends Block> RegistryObject<T> AdvancedRegister(String name, Supplier<T> supplier,
//                                                                      Item.Properties properties){
//        RegistryObject<T> block = BLOCKS.register(name, supplier);
//        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
//        return block;
//    }
}
///aaa
