package com.github.shaneyu.tutorialmod.common.item;

import com.github.shaneyu.tutorialmod.TutorialMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MODID);

    public static final RegistryObject<Item> RAW_COAL_INFUSED_IRON =
            ITEMS.register("raw_coal_infused_iron", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COAL_DUST =
            ITEMS.register("coal_dust", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> IRON_DUST =
            ITEMS.register("iron_dust", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_DUST =
            ITEMS.register("steel_dust", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_INGOT =
            ITEMS.register("steel_ingot", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
