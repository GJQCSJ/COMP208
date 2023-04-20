package com.example.examplemod.datagenerator;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.item.ModItems;
import com.example.examplemod.util.ModTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class ModRecipes extends RecipeProvider {
    public ModRecipes(DataGenerator generatorIn){
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer){

//        ShapedRecipeBuilder.shaped(ModItems)
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModTags.SPODUMENE_ORE_ITEM_TIER),
                ModItems.SPODUMENE_IGNOT.get(), 1.0f, 100)
                .unlockedBy("has_ore", has(ModTags.SPODUMENE_ORE_ITEM_TIER))
                .save(consumer, "spodumene_ignot1");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_SPODUMENE.get()),
                ModItems.SPODUMENE_IGNOT.get(), 0.5f, 100)
                .unlockedBy("has_chunk", has(ModItems.RAW_SPODUMENE.get()))
                .save(consumer, "spodumene_ingnot2");
        ShapedRecipeBuilder.shaped(ModBlocks.MANA_EXTRACTOR_BLOCK.get())
                .pattern("XZX")
                .pattern("ZXZ")
                .pattern("YYY")
                .define('X', ModItems.STARSTONE.get())
                .define('Y', ModItems.SPODUMENE_IGNOT.get())
                .define('Z', Tags.Items.OBSIDIAN)
                .group("comp208mod")
                .unlockedBy("spodumene", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPODUMENE_IGNOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.GENERATOR.get())
                        .pattern("YYY")
                        .pattern("YXY")
                        .pattern("XXX")
                                .define('X', ModItems.STARSTONE.get())
                                        .define('Y', ModItems.SPODUMENE_IGNOT.get())
                                                .group("comp208mod")
                                                        .unlockedBy("starstone",InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STARSTONE.get()))
                                                                .save(consumer);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModTags.STARONE_ORE_ITEM_TIER),
                        ModItems.STARSTONE.get(), 1.0f, 100)
                .unlockedBy("has_ore", has(ModTags.STARONE_ORE_ITEM_TIER))
                .save(consumer, "starstone1");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.STARSTONE_RAW.get()),
                        ModItems.STARSTONE.get(), 0.7f, 100)
                .unlockedBy("has_chunk", has(ModItems.STARSTONE_RAW.get()))
                .save(consumer, "starstone2");
        ShapedRecipeBuilder.shaped(ModItems.GREAT_SWORD.get())
                .pattern("YYY")
                .pattern("YXY")
                .pattern("YZY")
                .define('X', ModItems.GENERATED_ITEM.get())
                .define('Y', ModItems.STARSTONE.get())
                .define('Z', Items.STICK)
                .group("comp208mod")
                .unlockedBy("ironsword", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPODUMENE_IGNOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.TEST_ARMOR_CHEST_PLATE.get())
                .pattern("YXY")
                .pattern("YYY")
                .pattern("YYY")
                .define('X', ModItems.STARSTONE.get())
                .define('Y', ModItems.SPODUMENE_IGNOT.get())
                .group("comp208mod")
                .unlockedBy("chest", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPODUMENE_IGNOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.TEST_ARMOR_HELMET.get())
                .pattern("YYY")
                .pattern("YXY")
                .pattern("   ")
                .define('X', ModItems.STARSTONE.get())
                .define('Y', ModItems.SPODUMENE_IGNOT.get())
                .group("comp208mod")
                .unlockedBy("helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPODUMENE_IGNOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.TEST_ARMOR_LEGGINGS.get())
                .pattern("YXY")
                .pattern("Y Y")
                .pattern("Y Y")
                .define('X', ModItems.STARSTONE.get())
                .define('Y', ModItems.SPODUMENE_IGNOT.get())
                .group("comp208mod")
                .unlockedBy("leg", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPODUMENE_IGNOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.TEST_ARMOR_BOOTS.get())
                .pattern("Y Y")
                .pattern("X X")
                .pattern("   ")
                .define('X', ModItems.STARSTONE.get())
                .define('Y', ModItems.SPODUMENE_IGNOT.get())
                .group("comp208mod")
                .unlockedBy("boot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPODUMENE_IGNOT.get()))
                .save(consumer);
    }
}
