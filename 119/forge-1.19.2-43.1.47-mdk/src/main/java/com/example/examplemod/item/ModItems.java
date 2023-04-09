package com.example.examplemod.item;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.comp208mod;
import com.example.examplemod.item.custom.GreatSwordItem;
import com.example.examplemod.item.custom.MultiPurposeToolItem;
import com.example.examplemod.item.custom.TestAdvanceItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.example.examplemod.block.ModBlocks.MANA_EXTRACTOR_BLOCK;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, comp208mod.MOD_ID);

    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ModCreativeModeTab.Test_Demo);

    // This fromBlock() will auto assign the properties of block to the item
    public static <B extends Block>RegistryObject<Item> fromBlock(RegistryObject<B> block){
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }
    public static final RegistryObject<Item> MANA_EXTRACTOR_ITEM = fromBlock(MANA_EXTRACTOR_BLOCK);


    public static final RegistryObject<Item> AUTO_TEST_ITEM = fromBlock(ModBlocks.AUTO_TEST_BLOCK);
    public static final RegistryObject<Item> SPODUMENE_IGNOT = ITEMS.register("spodumene",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.Test_Demo)));
    public static final RegistryObject<Item> RAW_SPODUMENE = ITEMS.register("raw_spodumene",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.Test_Demo)));
    public static final RegistryObject<Item> STARSTONE = ITEMS.register("starstone",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.Test_Demo)));

    public static final RegistryObject<Item> STARSTONE_RAW = ITEMS.register("starstone_raw",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.Test_Demo)));

    public static final RegistryObject<Item> TEST_ADV = ITEMS.register("test_advanced_item1",
            () -> new TestAdvanceItem(new Item.Properties().tab(ModCreativeModeTab.Test_Demo)));

    private static Item.Properties props(){
        return new Item.Properties().tab(ModCreativeModeTab.Test_Demo);
        /* A shorter way to register items to tabs */
    }
    /*
    Tools & Weapons
     */
    public static final RegistryObject<SwordItem> TEST_SWORD = ITEMS.register("test_sword",
            () -> new SwordItem(
                    ModTiers.TEST_TIER_TOOLS,
                    7,
                    4.0f,
                    props()
            )
    );
    public static final RegistryObject<PickaxeItem> TEST_PICKAXE = ITEMS.register("test_pickaxe",
            () -> new PickaxeItem(
                    ModTiers.TEST_TIER_TOOLS,
                    2,
                    3.5f,
                    props()
            )
    );

    public static final RegistryObject<AxeItem> TEST_AXE = ITEMS.register("test_axe",
            () -> new AxeItem(
                    ModTiers.TEST_TIER_TOOLS,
                    9,
                    3.0f,
                    props()
            )
    );

    public static final RegistryObject<HoeItem> TEST_HOE = ITEMS.register("test_hoe",
            () -> new HoeItem(
                    ModTiers.TEST_TIER_TOOLS,
                    2,
                    4.0f,
                    props()
            )
    );

    public static final RegistryObject<ShovelItem> TEST_SHOVEL = ITEMS.register("test_shovel",
            () -> new ShovelItem(
                    ModTiers.TEST_TIER_TOOLS,
                    2,
                    4.0f,
                    props()
            )
    );
    public static final RegistryObject<Item> TEST_BOW = ITEMS.register("test_bow",
            () -> new BowItem(props()
                    .durability(600)
            )

    );
    public static final RegistryObject<GreatSwordItem> GREAT_SWORD = ITEMS.register("great_sword",
            () -> new GreatSwordItem(ModTiers.TEST_TIER_TOOLS,
                    1,
                    2.0f,
                    10.0f,
                    2.5f,
                    props()
            )
    );
    /*
    End of Tools & Weapons
     */



    /*
    Food items & Food class
    */
    public static final RegistryObject<Item> TEST_FOOD1 = ITEMS.register("test_food1",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeModeTab.Test_Demo)
                    .food(Foods.TEST_FOOD1)
            )
    );

    public static final RegistryObject<Item> CUSTOM_BERRY_SEEDS = ITEMS.register("custom_berry_seed",
            () -> new ItemNameBlockItem(ModBlocks.BERRY_BLOCK.get(),
                    props()
            )
    );

    public static final RegistryObject<Item> CUSTOM_BERRY = ITEMS.register("custom_berry",
            () -> new Item(
                    props()
                            .food(new FoodProperties.Builder().nutrition(1)
                                    .fast()
                                    .saturationMod(2f)
                                    .build()
                            )
            )
    );
    public static class Foods{
        public static final FoodProperties TEST_FOOD1 = new FoodProperties.Builder()
                .nutrition(3)
                .meat()
                .saturationMod(1)
                .effect(() -> new MobEffectInstance(MobEffects.HEAL, 360, 1), 1.0f)
                .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1919, 1), 1.0F)
                .fast()
                .build();
        /* effect() may be applied to add potion effect when food is eaten */
        /* fast() may half the time required to eat the food */
    }
    /*
    End of Food items & Food class
    */


    /*
    ArmorSuit items
    */
    public static final RegistryObject<ArmorItem> TEST_ARMOR_HELMET = ITEMS.register("test_helmet",
            () -> new ArmorItem(
                    ModTiers.ArmorTiers.TEST_ARMOR_MATERIAL,
                    EquipmentSlot.HEAD,
                    props()
            )
    );
    public static final RegistryObject<ArmorItem> TEST_ARMOR_CHEST_PLATE = ITEMS.register("test_chest_plate",
            () -> new ArmorItem(
                    ModTiers.ArmorTiers.TEST_ARMOR_MATERIAL,
                    EquipmentSlot.CHEST,
                    props()
            )
    );
    public static final RegistryObject<ArmorItem> TEST_ARMOR_LEGGINGS = ITEMS.register("test_leggings",
            () -> new ArmorItem(
                    ModTiers.ArmorTiers.TEST_ARMOR_MATERIAL,
                    EquipmentSlot.LEGS,
                    props()
            )
    );
    public static final RegistryObject<ArmorItem> TEST_ARMOR_BOOTS = ITEMS.register("test_boots",
            () -> new ArmorItem(
                    ModTiers.ArmorTiers.TEST_ARMOR_MATERIAL,
                    EquipmentSlot.FEET,
                    props()
            )
    );

    /*
    End of ArmorSuit items
    */

    /* Register of multi-tool */
    public static final RegistryObject<MultiPurposeToolItem> MULTI_TOOL_SPODUMENE = ITEMS.register("spodumene_multi_tool",
            () -> new MultiPurposeToolItem(
                    ModTiers.TEST_TIER_TOOLS,
                    2.0f,
                    0.6f,
                    props()
            )
    );
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }


}
