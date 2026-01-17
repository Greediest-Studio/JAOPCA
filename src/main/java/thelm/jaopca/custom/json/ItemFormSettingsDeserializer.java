//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.custom.json;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.function.ToIntFunction;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import thelm.jaopca.api.items.IItemFormSettings;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.forms.FormTypeHandler;
/*    */ import thelm.jaopca.items.ItemFormType;
/*    */ import thelm.jaopca.utils.JsonHelper;
/*    */ 
/*    */ public class ItemFormSettingsDeserializer
/*    */   implements JsonDeserializer<IItemFormSettings>
/*    */ {
/* 21 */   public static final ItemFormSettingsDeserializer INSTANCE = new ItemFormSettingsDeserializer();
/*    */ 
/*    */ 
/*    */   
/*    */   public IItemFormSettings deserialize(JsonElement jsonElement, JsonDeserializationContext context) {
/* 26 */     return deserialize(jsonElement, IItemFormSettings.class, context);
/*    */   }
/*    */ 
/*    */   
/*    */   public IItemFormSettings deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 31 */     JsonHelper jsonHelper = JsonHelper.INSTANCE;
/* 32 */     JsonObject json = jsonHelper.getJsonObject(jsonElement, "settings");
/* 33 */     IItemFormSettings settings = ItemFormType.INSTANCE.getNewSettings();
/* 34 */     if (json.has("itemStackLimit")) {
/* 35 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "itemStackLimit");
/* 36 */       if (!functionJson.has("default")) {
/* 37 */         functionJson.addProperty("default", Integer.valueOf(Items.AIR.getItemStackLimit()));
/*    */       }
/* 39 */       settings.setItemStackLimitFunction((ToIntFunction)jsonHelper.deserializeType(json, "itemStackLimit", context, FormTypeHandler.INT_FUNCTION_TYPE));
/*    */     } 
/* 41 */     if (json.has("hasEffect")) {
/* 42 */       boolean hasEffect = jsonHelper.getBoolean(json, "hasEffect");
/* 43 */       settings.setHasEffectFunction(m -> (m.hasEffect() || hasEffect));
/*    */     } 
/* 45 */     if (json.has("rarity")) {
/* 46 */       EnumRarity rarity = (EnumRarity)jsonHelper.deserializeType(json, "rarity", context, EnumRarity.class);
/* 47 */       settings.setDisplayRarityFunction(m -> rarity);
/*    */     } 
/* 49 */     if (json.has("burnTime")) {
/* 50 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "burnTime");
/* 51 */       if (!functionJson.has("default")) {
/* 52 */         functionJson.addProperty("default", Integer.valueOf(-1));
/*    */       }
/* 54 */       settings.setBurnTimeFunction((ToIntFunction)jsonHelper.deserializeType(json, "burnTime", context, FormTypeHandler.INT_FUNCTION_TYPE));
/*    */     } 
/* 56 */     return settings;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\custom\json\ItemFormSettingsDeserializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
