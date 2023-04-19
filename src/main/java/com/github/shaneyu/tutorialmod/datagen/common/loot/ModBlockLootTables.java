package com.github.shaneyu.tutorialmod.datagen.common.loot;

import com.github.shaneyu.tutorialmod.common.block.ModBlocks;
import com.github.shaneyu.tutorialmod.common.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.STEEL_BLOCK.get());
        dropSelf(ModBlocks.IRON_FRAME.get());
        dropSelf(ModBlocks.STEEL_FRAME.get());

        add(ModBlocks.COAL_INFUSED_IRON_ORE.get(), (block) -> createOreDrop(block, ModItems.RAW_COAL_INFUSED_IRON.get()));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
