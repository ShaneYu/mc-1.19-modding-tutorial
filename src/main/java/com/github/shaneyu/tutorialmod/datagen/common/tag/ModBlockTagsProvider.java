package com.github.shaneyu.tutorialmod.datagen.common.tag;

import com.github.shaneyu.tutorialmod.TutorialMod;
import com.github.shaneyu.tutorialmod.common.block.ModBlocks;
import com.github.shaneyu.tutorialmod.common.tag.ModTags;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, TutorialMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        registerAxeMineable();
        registerPickaxeMineable();
        registerShovelMineable();
        addHarvestingRequirements();

        addOreBlocks();
        addStorageBlocks();

        checkAllRegisteredForHarvesting();
    }

    private void addHarvestingRequirements() {
        setMiningLevel(Tiers.STONE, ModBlocks.COAL_INFUSED_IRON_ORE);

        setMiningLevel(
                Tiers.IRON,
                ModBlocks.STEEL_BLOCK,
                ModBlocks.IRON_FRAME,
                ModBlocks.STEEL_FRAME
        );
    }

    @SuppressWarnings("unchecked")
    private void addOreBlocks() {
        addOresWithSingularRate(
                ModTags.Blocks.ORES_COAL_INFUSED_IRON,
                ModBlocks.COAL_INFUSED_IRON_ORE
        );

        tag(Tags.Blocks.ORES_IN_GROUND_STONE).add(ModBlocks.COAL_INFUSED_IRON_ORE.get());

        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_COAL_INFUSED_IRON);
    }

    @SuppressWarnings("unchecked")
    private void addStorageBlocks() {
        tag(ModTags.Blocks.STORAGE_BLOCKS_STEEL).add(ModBlocks.STEEL_BLOCK.get());

        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_STEEL);
    }

    private void registerAxeMineable() {
        IntrinsicTagAppender<Block> tag = tag(BlockTags.MINEABLE_WITH_AXE);
        getBlocksWithMaterial(Material.WOOD).forEach(tag::add);
    }

    private void registerPickaxeMineable() {
        IntrinsicTagAppender<Block> tag = tag(BlockTags.MINEABLE_WITH_PICKAXE);
        getBlocksWithMaterial(Material.STONE, Material.METAL).forEach(tag::add);
    }

    private void registerShovelMineable() {
        IntrinsicTagAppender<Block> tag = tag(BlockTags.MINEABLE_WITH_SHOVEL);
        getBlocksWithMaterial(Material.DIRT).forEach(tag::add);
    }

    private Stream<Block> getBlocksWithMaterial(Material ...materials) {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)
                .filter(block -> {
                    Material blockMaterial = block.defaultBlockState().getMaterial();

                    return Arrays.stream(materials).anyMatch(material -> material == blockMaterial);
                });
    }

    @SafeVarargs
    private <T extends Block> void setMiningLevel(Tiers level, Supplier<T>...blocks) {
        TagKey<Block> tag = switch(level) {
            case WOOD -> Tags.Blocks.NEEDS_WOOD_TOOL;
            case STONE -> BlockTags.NEEDS_STONE_TOOL;
            case IRON -> BlockTags.NEEDS_IRON_TOOL;
            case GOLD -> Tags.Blocks.NEEDS_GOLD_TOOL;
            case DIAMOND -> BlockTags.NEEDS_DIAMOND_TOOL;
            case NETHERITE -> Tags.Blocks.NEEDS_NETHERITE_TOOL;
            default -> throw new IllegalArgumentException("No tag available for " + level.name());
        };

        Arrays.stream(blocks).forEach(block -> tag(tag).add(block.get()));
    }

    @SafeVarargs
    private <T extends Block> void addOresWithSingularRate(TagKey<Block> blockTag, Supplier<T> ...oreBlocks) {
        Arrays.stream(oreBlocks).forEach(oreBlock -> {
            T block = oreBlock.get();
            tag(blockTag).add(block);
            tag(Tags.Blocks.ORE_RATES_SINGULAR).add(block);
        });
    }

    @SafeVarargs
    private <T extends Block> void addOresWithDenseRate(TagKey<Block> blockTag, Supplier<T> ...oreBlocks) {
        Arrays.stream(oreBlocks).forEach(oreBlock -> {
            T block = oreBlock.get();
            tag(blockTag).add(block);
            tag(Tags.Blocks.ORE_RATES_DENSE).add(block);
        });
    }

    @SafeVarargs
    private <T extends Block> void addOresWithSparseRate(TagKey<Block> blockTag, Supplier<T> ...oreBlocks) {
        Arrays.stream(oreBlocks).forEach(oreBlock -> {
            T block = oreBlock.get();
            tag(blockTag).add(block);
            tag(Tags.Blocks.ORE_RATES_SPARSE).add(block);
        });
    }

    private void checkAllRegisteredForHarvesting() {
        List<TagKey<Block>> knownHarvestTags = ImmutableList.of(
                BlockTags.MINEABLE_WITH_AXE,
                BlockTags.MINEABLE_WITH_PICKAXE,
                BlockTags.MINEABLE_WITH_SHOVEL
        );

        Set<ResourceLocation> harvestable = knownHarvestTags.stream()
                .map(this::tag)
                .map(TagAppender::getInternalBuilder)
                .flatMap(b -> b.build().stream())
                .map(Object::toString)
                .map(ResourceLocation::tryParse)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Set<ResourceLocation> registered = ModBlocks.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .map(ForgeRegistries.BLOCKS::getKey)
                .collect(Collectors.toSet());

        Set<ResourceLocation> notHarvestable = Sets.difference(registered, harvestable);

        if (!notHarvestable.isEmpty()) {
            notHarvestable.forEach(resourceLocation -> TutorialMod.LOGGER.error("Not harvestable: {}", resourceLocation));
            throw new RuntimeException();
        }
    }
}
