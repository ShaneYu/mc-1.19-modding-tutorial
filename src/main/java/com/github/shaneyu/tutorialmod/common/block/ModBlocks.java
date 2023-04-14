package com.github.shaneyu.tutorialmod.common.block;

import com.github.shaneyu.tutorialmod.TutorialMod;
import com.github.shaneyu.tutorialmod.common.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MODID);

    public static final RegistryObject<Block> COAL_INFUSED_IRON_ORE = registerBlock("coal_infused_iron_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F)));

    public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(6f, 7f)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)));

    public static final RegistryObject<Block> IRON_FRAME = registerBlock("iron_frame",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops()
                    .strength(4.0F, 6.0F).sound(SoundType.METAL)));

    public static final RegistryObject<Block> STEEL_FRAME = registerBlock("steel_frame",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops()
                    .strength(5.0F, 7.0F).sound(SoundType.METAL)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);

        return toReturn;
    }
}
