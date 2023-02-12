package com.example.examplemod.base;

import com.example.examplemod.comp208mod;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public record ModArmorMaterial(String name,
                               int durability,
                               int[] protection,
                               int enchantability,
                               SoundEvent equipsound,
                               float toughness,
                               float knockbackResist,
                               Supplier<Ingredient> repairMaterial) implements ArmorMaterial {
    private static final int[] DURABILITY_PER_SLOT = new int[]{13,15,16,11};

    @Override
    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return this.DURABILITY_PER_SLOT[pSlot.getIndex()] * this.durability;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot pSlot) {
        return this.protection[pSlot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return this.equipsound;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @Override
    public @NotNull String getName() {
        return comp208mod.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResist;
    }
}
