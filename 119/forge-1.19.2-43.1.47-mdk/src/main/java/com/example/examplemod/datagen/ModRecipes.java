package com.example.examplemod.datagen;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.item.ModItems;
import com.example.examplemod.util.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;

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
    }
}
