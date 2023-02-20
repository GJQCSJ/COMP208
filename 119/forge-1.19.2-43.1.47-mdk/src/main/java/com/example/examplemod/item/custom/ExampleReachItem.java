package com.example.examplemod.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.mojang.math.Vector3d;
import com.mojang.realmsclient.dto.RealmsServer;
import net.minecraft.WorldVersion;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.core.Direction;
import net.minecraft.util.datafix.fixes.ChunkPalettedStorageFix;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.WorldlyContainerHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.templatesystem.AxisAlignedLinearPosTest;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.WorldWorkerManager;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.TextTable;

import java.util.UUID;

public class ExampleReachItem extends SwordItem {
    public static final UUID REACH_MOD = UUID.fromString("dccd59ec-6391-436d-9e00-47f2e6005e20"); //A randomly generated version 4 UUID
    public static final UUID KNOCKBACK_MOD = UUID.fromString("15bf6a06-b73b-4d8e-946a-61f63e1ba01e"); //Another randomly generated version 4 UUID
    public double reach;
    public double knockBack;
    public int damage;
    public int attackSpd;

    public Lazy<? extends Multimap<Attribute, AttributeModifier>> ATTRIBUTE_LAZY_MAP = Lazy.of(() -> {
        Multimap<Attribute, AttributeModifier> map;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", damage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", attackSpd, AttributeModifier.Operation.ADDITION));
        //Checking if the Forge 'Reach' Attribute is present
        if (ForgeMod.ATTACK_RANGE.isPresent()) {
            builder.put(ForgeMod.ATTACK_RANGE.get(), new AttributeModifier(REACH_MOD, "Weapon modifier", reach, AttributeModifier.Operation.ADDITION));
        }
        builder.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(KNOCKBACK_MOD, "Weapon modifier", knockBack, AttributeModifier.Operation.ADDITION));
        map = builder.build();
        return map;
    });

    //Now you can modify the reach value and other attribute values per entry
    public ExampleReachItem(Tier tier, int dmg, float atkspd, double reach, double kb, Properties properties) {
        super(tier, dmg, atkspd, properties);
        this.damage = (int) ((float)dmg + tier.getAttackDamageBonus());
        this.attackSpd = (int) atkspd;
        this.reach = reach;
        this.knockBack = kb;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == EquipmentSlot.MAINHAND ? ATTRIBUTE_LAZY_MAP.get() : super.getAttributeModifiers(slot, stack);
    }

    //This also works on blocks btw
    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        double reach = entity.getAttributeValue(ForgeMod.ATTACK_RANGE.get());
        double reachSqr = reach * reach;
        Level world = entity.level;
        float attackDamage = (float)this.damage;


        Vec3 viewVec = entity.getViewVector(1.0F);
        Vec3 eyeVec = entity.getEyePosition(1.0F);
        Vec3 targetVec = eyeVec.add(viewVec.x * reach, viewVec.y * reach, viewVec.z * reach);

        //Expanding the attacker's bounding box by the view vector's scale, and inflating it by 4.0D (x, y, z)
        AABB viewBB = entity.getBoundingBox().expandTowards(viewVec.scale(reach)).inflate(4.0D, 4.0D, 4.0D);
        //EntityRayTraceResult
         EntityHitResult result = ProjectileUtil.getEntityHitResult(world, entity, eyeVec, targetVec, viewBB, EntitySelector.NO_CREATIVE_OR_SPECTATOR);

        if (result == null || !(result.getEntity() instanceof LivingEntity)) return false;

        LivingEntity target = (LivingEntity) result.getEntity();

        double distanceToTargetSqr = entity.distanceToSqr(target);

        boolean hitResult = (result != null ? target : null) != null;

        if (hitResult) {
            if (entity instanceof Player) {
                if (reachSqr >= distanceToTargetSqr) {
                    target.hurt(DamageSource.playerAttack((Player) entity), attackDamage);
                    //Do stuff
                }
            }
        }
        return super.onEntitySwing(stack, entity);
    }
}
