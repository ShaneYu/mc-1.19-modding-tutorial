package com.github.shaneyu.tutorialmod.datagen.client;

import com.github.shaneyu.tutorialmod.TutorialMod;
import com.github.shaneyu.tutorialmod.common.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, TutorialMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.COAL_DUST);
        simpleItem(ModItems.IRON_DUST);
        simpleItem(ModItems.STEEL_DUST);
        simpleItem(ModItems.STEEL_INGOT);
    }

    private String getItemName(RegistryObject<Item> item) {
        return item.getId().getPath();
    }

    private ResourceLocation getItemTexture(String itemName) {
        return new ResourceLocation(TutorialMod.MODID,"item/" + itemName);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        String itemPath = getItemName(item);

        return withExistingParent(itemPath, "item/generated").texture("layer0", getItemTexture(itemPath));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        String itemPath = getItemName(item);

        return withExistingParent(itemPath, "item/handheld").texture("layer0", getItemTexture(itemPath));

    }
}
