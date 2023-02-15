package com.example.examplemod.item;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.comp208mod;
import com.example.examplemod.item.custom.TestAdvanceItem;
import com.example.examplemod.util.ModTags;
import net.minecraft.client.gui.font.glyphs.BakedGlyph;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffect;
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

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, comp208mod.MOD_ID);

    public static final RegistryObject<Item> TEST = ITEMS.register("test_item",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.Test_Demo)));
    public static final RegistryObject<Item> TEST2 = ITEMS.register("test_item2",
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
                    8,
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
    public static final RegistryObject<Item> TEST_BOW = ITEMS.register("test_bow",
            () -> new BowItem(props()
                    .durability(600)
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


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }


}
