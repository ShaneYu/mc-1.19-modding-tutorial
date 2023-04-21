package com.github.shaneyu.tutorialmod.datagen.common.recipe;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public abstract class BaseRecipeProvider extends RecipeProvider implements IConditionBuilder {
    protected BaseRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    protected static void addStorageBlock(@NotNull Consumer<FinishedRecipe> pWriter, ItemLike item, Block block) {
        addStorageBlock(pWriter, RecipeCategory.MISC, item, RecipeCategory.BUILDING_BLOCKS, block);
    }

    protected static void addStorageBlock(@NotNull Consumer<FinishedRecipe> pWriter, RecipeCategory itemCategory, ItemLike item, RecipeCategory blockCategory, Block block) {
        nineBlockStorageRecipes(pWriter, itemCategory, item, blockCategory, block);
    }

    protected static void addRawMaterialToIngot(@NotNull Consumer<FinishedRecipe> pWriter, ItemLike dustItem, ItemLike ingotItem, float experience, String group) {
        addRawMaterialToIngot(pWriter, List.of(dustItem), ingotItem, experience, group);
    }

    protected static void addRawMaterialToIngot(@NotNull Consumer<FinishedRecipe> pWriter, ItemLike dustItem, RecipeCategory category, ItemLike ingotItem, float experience, String group) {
        addRawMaterialToIngot(pWriter, List.of(dustItem), category, ingotItem, experience, group);
    }

    protected static void addRawMaterialToIngot(@NotNull Consumer<FinishedRecipe> pWriter, List<ItemLike> dustItems, ItemLike ingotItem, float experience, String group) {
        addRawMaterialToIngot(pWriter, dustItems, RecipeCategory.MISC, ingotItem, experience, group);
    }

    protected static void addRawMaterialToIngot(@NotNull Consumer<FinishedRecipe> pWriter, List<ItemLike> dustItems, RecipeCategory category, ItemLike ingotItem, float experience, String group) {
        oreSmelting(pWriter, dustItems, category, ingotItem, experience, 200, group);
        oreBlasting(pWriter, dustItems, category, ingotItem, experience, 100, group);
    }
}
