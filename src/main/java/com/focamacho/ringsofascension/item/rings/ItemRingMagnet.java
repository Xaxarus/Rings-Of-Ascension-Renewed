package com.focamacho.ringsofascension.item.rings;

import com.focamacho.ringsofascension.config.ConfigHolder;
import com.focamacho.ringsofascension.item.ItemRingBase;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.List;

public class ItemRingMagnet extends ItemRingBase {

    public ItemRingMagnet(Properties properties, String tooltip) {
        super(properties, tooltip);
    }

    @Override
    public void tickCurio(String identifier, int index, LivingEntity livingEntity) {
        if(!isEnabled()) return;
        if(livingEntity instanceof Player && !livingEntity.level.isClientSide && !livingEntity.isCrouching()) {
            int range = 5;
            BlockPos pos = new BlockPos(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
            List<ItemEntity> entities = livingEntity.level.getEntitiesOfClass(ItemEntity.class, new AABB(pos.getX() + range, pos.getY() + range, pos.getZ() + range, pos.getX() - range, pos.getY() - range, pos.getZ() - range));
            for(ItemEntity item : entities) {
                if(item.isAlive() && !item.hasPickUpDelay()) {
                    item.playerTouch((Player)livingEntity);
                }
            }
        }
    }

    @Override
    public List<ResourceLocation> getLocations() {
        return super.getLocations(ConfigHolder.ringLocationMagnetism);
    }

    @Override
    public boolean isEnabled() {
        return ConfigHolder.ringMagnetism;
    }

    @Override
    public int getTier() {
        return ConfigHolder.ringTierMagnetism;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if(!isEnabled()) return;
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

}
