package com.github.shaneyu.tutorialmod.datagen.client;

import com.github.shaneyu.tutorialmod.TutorialMod;
import com.github.shaneyu.tutorialmod.common.block.ModBlocks;
import com.github.shaneyu.tutorialmod.common.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput packOutput) {
        super(packOutput, TutorialMod.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("creativemodetab.tutorialmod_tab", "Tutorial Mod");
        add(ModItems.COAL_DUST.get(), "Coal Dust");
        add(ModItems.IRON_DUST.get(), "Iron Dust");
        add(ModItems.STEEL_DUST.get(), "Steel Dust");
        add(ModItems.RAW_COAL_INFUSED_IRON.get(), "Raw Coal Infused Iron");
        add(ModItems.STEEL_INGOT.get(), "Steel Ingot");
        add(ModBlocks.COAL_INFUSED_IRON_ORE.get(), "Coal Infused Iron Ore");
        add(ModBlocks.STEEL_BLOCK.get(), "Block of Steel");
        add(ModBlocks.IRON_FRAME.get(), "Iron Frame");
        add(ModBlocks.STEEL_FRAME.get(), "Steel Frame");
    }
}
