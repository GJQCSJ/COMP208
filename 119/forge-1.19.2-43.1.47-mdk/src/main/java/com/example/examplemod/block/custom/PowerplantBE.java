package com.example.examplemod.block.custom;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.util.CustomEnergyContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;



public class PowerplantBE extends BlockEntity {
    //This class is the actual one generating energy from consuming fuel items
    //So this class needs another container and tick which do the processing
    //Pipes may be implemented later, if there is still time
//    public static final int MANA_CAP = 25535;
//    public static final int MANA_GEN = 63;
//    public static final int MANA_SEND = 255;

    private int counter;

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    private final CustomEnergyContainer energyStorage = createEnergy();
    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    public PowerplantBE(BlockPos pos, BlockState state){
        super(ModBlocks.MANA_EXTRACTOR_BE.get(), pos, state);
    }

    @Override
    public void setRemoved(){
        super.setRemoved();
        handler.invalidate();
        energy.invalidate();
    }

    public void tickServer(){
        //If counter > 0, then consume the fuel and generate energy
        if (counter > 0){
            energyStorage.addEnergy(PowerplantConfig.MANA_GEN.get());
            counter--;
            setChanged();
        }
        if (counter <= 0){
            ItemStack stack = itemHandler.getStackInSlot(0);
            int burn_time = ForgeHooks.getBurnTime(stack, RecipeType.SMELTING);
            if (burn_time > 0){
                itemHandler.extractItem(0, 1, false);
                counter = burn_time;
                setChanged();
            }
        }

        BlockState blockState = level.getBlockState(worldPosition);
        if (blockState.getValue(BlockStateProperties.POWERED) != counter > 0){
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, counter > 0),
                    Block.UPDATE_ALL);
        }

        sendOutPower();
    }

    private void sendOutPower(){
        AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
        if (capacity.get() > 0){
            for (Direction direction : Direction.values()){
                BlockEntity be = level.getBlockEntity(worldPosition.relative(direction));
                if (be != null){
                    boolean doContinue = be.getCapability(ForgeCapabilities.ENERGY, direction.getOpposite()).map(
                            handler -> {
                                if (handler.canReceive()){
                                    int received = handler.receiveEnergy(Math.min(capacity.get(), PowerplantConfig.MANA_SEND.get()), false);
                                    capacity.addAndGet(-received);
                                    energyStorage.consumeEnergy(received);
                                    setChanged();
                                    return capacity.get() > 0;
                                } else {
                                    return true;
                                }
                            }
                    ).orElse(true);
                    if (!doContinue){
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void load(CompoundTag tag){
        if (tag.contains("Inventory")){
            itemHandler.deserializeNBT(tag.getCompound("Inventory"));
        }
        if (tag.contains("Energy")){
            energyStorage.deserializeNBT(tag.get("Energy"));
        }
        if (tag.contains("Info")){
            counter = tag.getCompound("Info").getInt("Counter");
        }
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag){
        tag.put("Inventory", itemHandler.serializeNBT());
        tag.put("Energy", energyStorage.serializeNBT());

        CompoundTag infoTag = new CompoundTag();
        infoTag.putInt("Counter", counter);
        tag.put("Info", infoTag);
    }
    private ItemStackHandler createHandler(){
        return new ItemStackHandler(1){
            @Override
            protected void onContentsChanged(int slot){
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack){
                return ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) > 0;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate){
                if (ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) <= 0){
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    private CustomEnergyContainer createEnergy(){
        return new CustomEnergyContainer(PowerplantConfig.MANA_CAP.get(), 0){
            @Override
            protected void onEnergyChanged(){
                setChanged();
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side){
        if (cap == ForgeCapabilities.ITEM_HANDLER){
            return handler.cast();
        }
        if (cap == ForgeCapabilities.ENERGY){
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }
}
