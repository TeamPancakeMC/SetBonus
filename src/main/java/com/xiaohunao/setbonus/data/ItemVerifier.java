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

//    public boolean isValid(ResourceLocation item_Key){
//        Item item = ForgeRegistries.ITEMS.getValue(item_Key);
//        if (item == null){
//            return false;
//        }
//        if (id != null) {
//            return item_Key.toString().equals(id);
//        }
//        if (tag != null) {
//            TagKey<Item> itemTag = TagKey.create(Registries.ITEM, new ResourceLocation(tag));
//            ItemStack stack = new ItemStack(item);
//            return stack.is(itemTag);
//        }
//        if (class_name != null) {
//            try {
//                return Class.forName(class_name).isAssignableFrom(item.getClass());
//            } catch (ClassNotFoundException e) {
//                throw new RuntimeException("Json解析错误,verifier找不到类: " + class_name,e);
//            }
//        }
//        return false;
//    }
}