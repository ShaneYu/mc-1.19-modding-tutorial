package com.github.shaneyu.tutorialmod.common.tag;

import com.github.shaneyu.tutorialmod.TutorialMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static void init() {
        Items.init();
        Blocks.init();
    }

    public static class Items {
        public static void init() {}

        public static final TagKey<Item> ORES_COAL_INFUSED_IRON = forgeTag("ores/coal_infused_iron");

        public static final TagKey<Item> RAW_COAL_INFUSED_IRON = forgeTag("raw_materials/coal_infused_iron");

        public static final TagKey<Item> INGOTS_STEEL = forgeTag("ingots/steel");

        public static final TagKey<Item> STORAGE_BLOCKS_STEEL = forgeTag("storage_blocks/steel");

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(TutorialMod.MODID, name));
        }
    }

    public static class Blocks {
        public static void init() {}

        public static final TagKey<Block> STORAGE_BLOCKS_STEEL = forgeTag("storage_blocks/steel");

        public static final TagKey<Block> ORES_COAL_INFUSED_IRON = forgeTag("ores/coal_infused_iron");

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(TutorialMod.MODID, name));
        }
    }
}
