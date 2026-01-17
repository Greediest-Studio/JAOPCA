/*    */ package thelm.jaopca.custom.json;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import java.lang.reflect.ParameterizedType;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*    */ import net.minecraftforge.registries.RegistryManager;
/*    */ import thelm.jaopca.utils.JsonHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ForgeRegistryEntrySupplierDeserializer
/*    */   implements JsonDeserializer<Supplier<IForgeRegistryEntry<?>>>
/*    */ {
/* 21 */   public static final ForgeRegistryEntrySupplierDeserializer INSTANCE = new ForgeRegistryEntrySupplierDeserializer();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Supplier<IForgeRegistryEntry<?>> deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 27 */     JsonHelper jsonHelper = JsonHelper.INSTANCE;
/* 28 */     Type[] typeArguments = ((ParameterizedType)typeOfT).getActualTypeArguments();
/* 29 */     Type parameterizedType = typeArguments[0];
/* 30 */     if (parameterizedType instanceof Class && IForgeRegistryEntry.class.isAssignableFrom((Class)parameterizedType) && 
/* 31 */       jsonElement.isJsonObject()) {
/* 32 */       JsonObject jsonObj = jsonHelper.getJsonObject(jsonElement, "value");
/* 33 */       ResourceLocation typeLocation = new ResourceLocation(jsonHelper.getString(jsonObj, "type"));
/* 34 */       ResourceLocation keyLocation = new ResourceLocation(jsonHelper.getString(jsonObj, "key"));
/* 35 */       return () -> RegistryManager.ACTIVE.getRegistry(typeLocation).getValue(keyLocation);
/*    */     } 
/*    */     
/* 38 */     throw new JsonParseException("Unable to deserialize " + jsonHelper.toSimpleString(jsonElement) + " into a forge registry entry");
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\custom\json\ForgeRegistryEntrySupplierDeserializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */