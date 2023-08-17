package com.focamacho.ringsofascension.item.rings;

import com.focamacho.ringsofascension.config.ConfigHolder;
import com.focamacho.ringsofascension.item.ItemRingBase;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CuriosApi;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ItemRingHealth extends ItemRingBase {

    private static final UUID HEALTH_UUID = UUID.fromString("b29c34f3-1450-48ff-ab28-639647e11861");

    public ItemRingHealth(Properties properties, String tooltip) {
        super(properties, tooltip);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> curioModifiers(ItemStack stack, String identifier) {
        Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

        if (CuriosApi.getCuriosHelper().getCurioTags(stack.getItem()).contains(identifier) && isEnabled()) {
            modifiers.put(Attributes.MAX_HEALTH,
                    new AttributeModifier(HEALTH_UUID, "Max Health", ConfigHolder.ringHealthHearts * 2,
                            AttributeModifier.Operation.ADDITION));
        }

        return modifiers;
    }

    @Override
    public List<ResourceLocation> getLocations() {
        return super.getLocations(ConfigHolder.ringLocationHealth);
    }

    @Override
    public boolean isEnabled() {
        return ConfigHolder.ringHealth;
    }

    @Override
    public int getTier() {
        return ConfigHolder.ringTierHealth;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if(!isEnabled()) return;
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

}