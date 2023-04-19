package com.github.shaneyu.tutorialmod.datagen;

import com.github.shaneyu.tutorialmod.TutorialMod;
import com.github.shaneyu.tutorialmod.datagen.client.ModBlockStateProvider;
import com.github.shaneyu.tutorialmod.datagen.client.ModItemModelProvider;
import com.github.shaneyu.tutorialmod.datagen.client.ModLanguageProvider;
import com.github.shaneyu.tutorialmod.datagen.common.loot.ModLootTableProvider;
import com.github.shaneyu.tutorialmod.datagen.common.tag.ModBlockTagsProvider;
import com.github.shaneyu.tutorialmod.datagen.common.tag.ModItemTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TutorialMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        final var packOutput = generator.getPackOutput();
        final var lookupProvider = event.getLookupProvider();

        if (event.includeClient()) {
            generator.addProvider(true, new ModItemModelProvider(packOutput, existingFileHelper));
            generator.addProvider(true, new ModBlockStateProvider(packOutput, existingFileHelper));
            generator.addProvider(true, new ModLanguageProvider(packOutput));
        }

        if (event.includeServer()) {
            generator.addProvider(true, ModLootTableProvider.create(packOutput));
            ModBlockTagsProvider modBlockTagsProvider = new ModBlockTagsProvider(packOutput, lookupProvider, existingFileHelper);
            generator.addProvider(true, modBlockTagsProvider);
            generator.addProvider(true, new ModItemTagsProvider(packOutput, lookupProvider, modBlockTagsProvider, existingFileHelper));
        }
    }
}
