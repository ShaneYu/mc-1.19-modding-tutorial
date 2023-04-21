package com.github.shaneyu.tutorialmod.datagen.common.recipe;

import com.github.shaneyu.tutorialmod.common.block.ModBlocks;
import com.github.shaneyu.tutorialmod.common.item.ModItems;
import com.github.shaneyu.tutorialmod.common.tag.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends BaseRecipeProvider {
    public ModRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> pWriter) {
        addStorageBlocks(pWriter);
        addDustsToIngots(pWriter);
        addFrameBlocks(pWriter);
    }

    private void addStorageBlocks(@NotNull Consumer<FinishedRecipe> pWriter) {
        addStorageBlock(pWriter, ModItems.STEEL_INGOT.get(), ModBlocks.STEEL_BLOCK.get());
    }

    private void addDustsToIngots(@NotNull Consumer<FinishedRecipe> pWriter) {
        addRawMaterialToIngot(pWriter, ModItems.IRON_DUST.get(), Items.IRON_INGOT, 0.7f, "iron_ingot");
        addRawMaterialToIngot(pWriter, ModItems.STEEL_DUST.get(), ModItems.STEEL_INGOT.get(), 1f, "steel_ingot");
    }

    private void addFrameBlocks(@NotNull Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_FRAME.get())
                .define('I', Tags.Items.INGOTS_IRON)
                .define('B', Tags.Items.STORAGE_BLOCKS_IRON)
                .pattern("I I")
                .pattern(" B ")
                .pattern("I I")
                .unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
                .save(pWriter);

        ShapedRecipeBuilder
                .shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STEEL_FRAME.get())
                .define('I', ModTags.Items.INGOTS_STEEL)
                .define('B', ModTags.Items.STORAGE_BLOCKS_STEEL)
                .pattern("I I")
                .pattern(" B ")
                .pattern("I I")
                .unlockedBy("has_steel_ingot", has(ModTags.Items.INGOTS_STEEL))
                .save(pWriter);
    }
}
