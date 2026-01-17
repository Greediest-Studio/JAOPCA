/*     */ package thelm.jaopca.custom.json;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.function.ToDoubleFunction;
/*     */ import java.util.function.ToIntFunction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import thelm.jaopca.api.fluids.IFluidFormSettings;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.blocks.BlockFormType;
/*     */ import thelm.jaopca.fluids.FluidFormType;
/*     */ import thelm.jaopca.forms.FormTypeHandler;
/*     */ import thelm.jaopca.utils.JsonHelper;
/*     */ 
/*     */ public class FluidFormSettingsDeserializer implements JsonDeserializer<IFluidFormSettings> {
/*  21 */   public static final FluidFormSettingsDeserializer INSTANCE = new FluidFormSettingsDeserializer();
/*     */ 
/*     */ 
/*     */   
/*     */   public IFluidFormSettings deserialize(JsonElement jsonElement, JsonDeserializationContext context) {
/*  26 */     return deserialize(jsonElement, IFluidFormSettings.class, context);
/*     */   }
/*     */ 
/*     */   
/*     */   public IFluidFormSettings deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/*  31 */     JsonHelper jsonHelper = JsonHelper.INSTANCE;
/*  32 */     JsonObject json = jsonHelper.getJsonObject(jsonElement, "settings");
/*  33 */     IFluidFormSettings settings = FluidFormType.INSTANCE.getNewSettings();
/*  34 */     if (json.has("fillSound")) {
/*  35 */       settings.setFillSoundSupplier((Supplier)jsonHelper.deserializeType(json, "fillSound", context, FluidFormType.SOUND_EVENT_SUPPLIER_TYPE));
/*     */     }
/*  37 */     if (json.has("emptySound")) {
/*  38 */       settings.setFillSoundSupplier((Supplier)jsonHelper.deserializeType(json, "emptySound", context, FluidFormType.SOUND_EVENT_SUPPLIER_TYPE));
/*     */     }
/*  40 */     if (json.has("luminosity")) {
/*  41 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "luminosity");
/*  42 */       if (!functionJson.has("default")) {
/*  43 */         functionJson.addProperty("default", Integer.valueOf(0));
/*     */       }
/*  45 */       settings.setLuminosityFunction((ToIntFunction)jsonHelper.deserializeType(json, "luminosity", context, FormTypeHandler.INT_FUNCTION_TYPE));
/*     */     } 
/*  47 */     if (json.has("density")) {
/*  48 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "density");
/*  49 */       if (!functionJson.has("default")) {
/*  50 */         functionJson.addProperty("default", Integer.valueOf(1000));
/*     */       }
/*  52 */       settings.setDensityFunction((ToIntFunction)jsonHelper.deserializeType(json, "density", context, FormTypeHandler.INT_FUNCTION_TYPE));
/*     */     } 
/*  54 */     if (json.has("temperature")) {
/*  55 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "temperature");
/*  56 */       if (!functionJson.has("default")) {
/*  57 */         functionJson.addProperty("default", Integer.valueOf(300));
/*     */       }
/*  59 */       settings.setTemperatureFunction((ToIntFunction)jsonHelper.deserializeType(json, "temperature", context, FormTypeHandler.INT_FUNCTION_TYPE));
/*     */     } 
/*  61 */     if (json.has("viscosity")) {
/*  62 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "viscosity");
/*  63 */       if (!functionJson.has("default")) {
/*  64 */         functionJson.addProperty("default", Integer.valueOf(1000));
/*     */       }
/*  66 */       settings.setViscosityFunction((ToIntFunction)jsonHelper.deserializeType(json, "viscosity", context, FormTypeHandler.INT_FUNCTION_TYPE));
/*     */     } 
/*  68 */     if (json.has("gaseous")) {
/*  69 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "viscosity");
/*  70 */       if (!functionJson.has("gaseous")) {
/*  71 */         functionJson.addProperty("gaseous", Boolean.valueOf(false));
/*     */       }
/*  73 */       settings.setIsGaseousFunction((Predicate)jsonHelper.deserializeType(json, "gaseous", context, FormTypeHandler.PREDICATE_TYPE));
/*     */     } 
/*  75 */     if (json.has("rarity")) {
/*  76 */       EnumRarity rarity = (EnumRarity)jsonHelper.deserializeType(json, "rarity", context, EnumRarity.class);
/*  77 */       settings.setDisplayRarityFunction(m -> rarity);
/*     */     } 
/*  79 */     if (json.has("maxLevel")) {
/*  80 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "maxLevel");
/*  81 */       if (!functionJson.has("default")) {
/*  82 */         functionJson.addProperty("default", Integer.valueOf(8));
/*     */       }
/*  84 */       settings.setMaxLevelFunction((ToIntFunction)jsonHelper.deserializeType(json, "maxLevel", context, FormTypeHandler.INT_FUNCTION_TYPE));
/*     */     } 
/*  86 */     if (json.has("blockMaterial")) {
/*  87 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "blockMaterial");
/*  88 */       if (!functionJson.has("default")) {
/*  89 */         functionJson.addProperty("default", "iron");
/*     */       }
/*  91 */       settings.setMaterialFunction((Function)jsonHelper.deserializeType(json, "blockMaterial", context, BlockFormType.MATERIAL_FUNCTION_TYPE));
/*     */     } 
/*  93 */     if (json.has("blockHardness")) {
/*  94 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "blockHardness");
/*  95 */       if (!functionJson.has("default")) {
/*  96 */         functionJson.addProperty("default", Integer.valueOf(5));
/*     */       }
/*  98 */       settings.setBlockHardnessFunction((ToDoubleFunction)jsonHelper.deserializeType(json, "blockHardness", context, FormTypeHandler.DOUBLE_FUNCTION_TYPE));
/*     */     } 
/* 100 */     if (json.has("explosionResistance")) {
/* 101 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "explosionResistance");
/* 102 */       if (!functionJson.has("default")) {
/* 103 */         functionJson.addProperty("default", Integer.valueOf(100));
/*     */       }
/* 105 */       settings.setExplosionResistanceFunction((ToDoubleFunction)jsonHelper.deserializeType(json, "explosionResistance", context, FormTypeHandler.DOUBLE_FUNCTION_TYPE));
/*     */     } 
/* 107 */     if (json.has("flammability")) {
/* 108 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "flammability");
/* 109 */       if (!functionJson.has("default")) {
/* 110 */         functionJson.addProperty("default", Integer.valueOf(0));
/*     */       }
/* 112 */       settings.setFlammabilityFunction((ToIntFunction)jsonHelper.deserializeType(json, "flammability", context, FormTypeHandler.INT_FUNCTION_TYPE));
/*     */     } 
/* 114 */     if (json.has("fireSpreadSpeed")) {
/* 115 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "fireSpreadSpeed");
/* 116 */       if (!functionJson.has("default")) {
/* 117 */         functionJson.addProperty("default", Integer.valueOf(0));
/*     */       }
/* 119 */       settings.setFireSpreadSpeedFunction((ToIntFunction)jsonHelper.deserializeType(json, "fireSpreadSpeed", context, FormTypeHandler.INT_FUNCTION_TYPE));
/*     */     } 
/* 121 */     if (json.has("isFireSource")) {
/* 122 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "isFireSource");
/* 123 */       if (!functionJson.has("default")) {
/* 124 */         functionJson.addProperty("default", Boolean.valueOf(false));
/*     */       }
/* 126 */       settings.setIsFireSourceFunction((Predicate)jsonHelper.deserializeType(json, "isFireSource", context, FormTypeHandler.PREDICATE_TYPE));
/*     */     } 
/* 128 */     return settings;
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\custom\json\FluidFormSettingsDeserializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */