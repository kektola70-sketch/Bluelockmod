package com.example.examplemod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.item.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry; // В 1.16.5 Registry находится здесь

public class ExampleMod implements ModInitializer {

    // 1. Значок Blue Lock (Вкладка Материалы)
    public static final Item BLUE_LOCK_BADGE = new Item(new Item.Settings().group(ItemGroup.MISC));

    // 2. Бутсы Эгоиста (Как меч, Вкладка Бой)
    public static final Item EGOIST_BOOTS = new SwordItem(ToolMaterials.DIAMOND, 3, -2.0f, new Item.Settings().group(ItemGroup.COMBAT));

    // 3. Стейк Победителя (Вкладка Еда)
    public static final Item WINNERS_STEAK = new Item(new Item.Settings().group(ItemGroup.FOOD).food(
            new FoodComponent.Builder()
                    .hunger(8)
                    .saturationModifier(0.8f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 600, 1), 1.0f)
                    .meat()
                    .build()
    ));

    @Override
    public void onInitialize() {
        // Регистрация предметов (Синтаксис для 1.16.5)
        Registry.register(Registry.ITEM, new Identifier("examplemod", "blue_lock_badge"), BLUE_LOCK_BADGE);
        Registry.register(Registry.ITEM, new Identifier("examplemod", "egoist_boots"), EGOIST_BOOTS);
        Registry.register(Registry.ITEM, new Identifier("examplemod", "winners_steak"), WINNERS_STEAK);
    }
}
