package com.github.shaneyu.tutorialmod.datagen.common.tag;

import com.github.shaneyu.tutorialmod.TutorialMod;
import com.github.shaneyu.tutorialmod.common.item.ModItems;
import com.github.shaneyu.tutorialmod.common.tag.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, BlockTagsProvider blocks, ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, blocks, TutorialMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        addOreBlocks();
        addStorageBlocks();
        addIngots();
        addRawMaterials();
    }

    private void addOreBlocks() {
        copy(ModTags.Blocks.ORES_COAL_INFUSED_IRON, ModTags.Items.ORES_COAL_INFUSED_IRON);
    }

    @SuppressWarnings("unchecked")
    private void addStorageBlocks() {
        copy(ModTags.Blocks.STORAGE_BLOCKS_STEEL, ModTags.Items.STORAGE_BLOCKS_STEEL);

        tag(Tags.Items.STORAGE_BLOCKS).addTag(ModTags.Items.STORAGE_BLOCKS_STEEL);
    }

    private void addIngots() {
        tag(ModTags.Items.INGOTS_STEEL).add(ModItems.STEEL_INGOT.get());

        tag(Tags.Items.INGOTS).addTag(ModTags.Items.INGOTS_STEEL);
    }

    private void addRawMaterials() {
        tag(ModTags.Items.RAW_COAL_INFUSED_IRON).add(ModItems.RAW_COAL_INFUSED_IRON.get());

        tag(Tags.Items.RAW_MATERIALS).addTag(ModTags.Items.RAW_COAL_INFUSED_IRON);
    }
}
