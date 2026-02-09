package com.example.examplemod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.item.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ExampleMod implements ModInitializer {

    // 1. Создаем Значок Blue Lock (Просто предмет)
    public static final Item BLUE_LOCK_BADGE = new Item(new FabricItemSettings());

    // 2. Создаем "Бутсы Эгоиста" (Работают как меч)
    // Урон как у алмазного меча, но быстрее
    public static final Item EGOIST_BOOTS = new SwordItem(ToolMaterials.DIAMOND, 3, -2.0f, new FabricItemSettings());

    // 3. Создаем "Стейк Победителя" (Еда, дает силу)
    public static final Item WINNERS_STEAK = new Item(new FabricItemSettings().food(
            new FoodComponent.Builder()
                    .hunger(8) // Восстанавливает 4 окорочка
                    .saturationModifier(0.8f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 600, 1), 1.0f) // Сила II на 30 сек
                    .meat()
                    .build()
    ));

    @Override
    public void onInitialize() {
        // Регистрация предметов в игре
        Registry.register(Registries.ITEM, new Identifier("examplemod", "blue_lock_badge"), BLUE_LOCK_BADGE);
        Registry.register(Registries.ITEM, new Identifier("examplemod", "egoist_boots"), EGOIST_BOOTS);
        Registry.register(Registries.ITEM, new Identifier("examplemod", "winners_steak"), WINNERS_STEAK);

        // Добавление предметов в творческий инвентарь
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            content.add(BLUE_LOCK_BADGE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            content.add(EGOIST_BOOTS);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
            content.add(WINNERS_STEAK);
        });
    }
}
