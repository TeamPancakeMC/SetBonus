package com.xiaohunao.setbonus.data;

import com.google.gson.JsonObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemVerifier {

    public String type;
    public String item;

    public ItemVerifier(String type, String item) {
        this.type = type;
        this.item = item;
    }

    public static ItemVerifier read(JsonObject verifier) {
        return new ItemVerifier(GsonHelper.getAsString(verifier, "type"),
                GsonHelper.getAsString(verifier, "item"));
    }

    public boolean verify(ItemStack stack) {
        ResourceLocation key = ForgeRegistries.ITEMS.getKey(stack.getItem());
        switch (type) {
            case "id" -> {
                return key != null && key.toString().equals(item);
            }
            case "tag" -> {
                TagKey<Item> itemTag = TagKey.create(Registries.ITEM, new ResourceLocation(item));
                return stack.is(itemTag);
            }
            case "class" -> {
                try {
                    return Class.forName(item).isAssignableFrom(stack.getItem().getClass());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Class " + item + " not found");
                }
            }
            default -> throw new RuntimeException("Unknown type " + type);
        }
    }


}