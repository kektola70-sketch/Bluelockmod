package com.example.examplemod; // Вернули старый пакет

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemTier;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

// Самое важное: ID мода вернулся на "examplemod"
@Mod("examplemod")
public class ExampleMod {

    // Создаем реестр с ID "examplemod"
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "examplemod");

    // 1. Значок
    public static final RegistryObject<Item> BLUE_LOCK_BADGE = ITEMS.register("blue_lock_badge",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));

    // 2. Бутсы Эгоиста
    public static final RegistryObject<Item> EGOIST_BOOTS = ITEMS.register("egoist_boots",
            () -> new SwordItem(ItemTier.DIAMOND, 3, -2.4F, new Item.Properties().tab(ItemGroup.TAB_COMBAT)));

    // 3. Стейк
    public static final RegistryObject<Item> WINNERS_STEAK = ITEMS.register("winners_steak",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(
                    new Food.Builder()
                            .nutrition(8)
                            .saturationMod(0.8f)
                            .effect(() -> new EffectInstance(Effects.DAMAGE_BOOST, 600, 1), 1.0f)
                            .meat()
                            .build()
            )));

    public ExampleMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }
}