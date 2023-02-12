package com.example.examplemod.item;

import com.example.examplemod.base.ModArmorMaterial;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final Tier TEST_TIER_TOOLS = new ForgeTier(
            2,
            893,
            2.0f,
            5,
            114,
            null,
            () -> Ingredient.of(ModItems.TEST.get()
                    /* This ingredient means the material required to repair the tool */
            )
    );
    public static class ArmorTiers{
        public static final ArmorMaterial TEST_ARMOR_MATERIAL = new ModArmorMaterial(
                "test_material",
                514,
                new int[]{9,18,27,36},
                333,
                SoundEvents.ARMOR_EQUIP_GENERIC,
                1.2f,
                0.7f,
                () -> Ingredient.of(ModItems.TEST.get()
                )
        );
    }
}





