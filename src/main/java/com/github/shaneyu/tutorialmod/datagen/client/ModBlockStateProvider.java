package com.github.shaneyu.tutorialmod.datagen.client;

import com.github.shaneyu.tutorialmod.TutorialMod;
import com.github.shaneyu.tutorialmod.common.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, TutorialMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(ModBlocks.COAL_INFUSED_IRON_ORE);
        simpleBlockWithItem(ModBlocks.STEEL_BLOCK);
        simpleBlockWithItem(ModBlocks.IRON_FRAME);
        simpleBlockWithItem(ModBlocks.STEEL_FRAME);
    }

    private void simpleBlockWithItem(RegistryObject<Block> block) {
        String blockName = getBlockName(block);

        simpleBlock(block.get());
        itemModels().withExistingParent(blockName, new ResourceLocation(TutorialMod.MODID, "block/" + blockName));
    }

    private void horizontalBlockWithItem(RegistryObject<Block> block) {
        String blockName = getBlockName(block);

        horizontalBlock(block.get(), models().getExistingFile(new ResourceLocation(TutorialMod.MODID, "block/" + blockName)));
        itemModels().withExistingParent(blockName, new ResourceLocation(TutorialMod.MODID, "block/" + blockName));
    }

    private String getBlockName(RegistryObject<Block> block) {
        return block.getId().getPath();
    }
}
