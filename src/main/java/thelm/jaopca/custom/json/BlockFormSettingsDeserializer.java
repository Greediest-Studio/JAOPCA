/*     */ package thelm.jaopca.custom.json;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.ToDoubleFunction;
/*     */ import java.util.function.ToIntFunction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import thelm.jaopca.api.blocks.IBlockFormSettings;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.blocks.BlockFormType;
/*     */ import thelm.jaopca.forms.FormTypeHandler;
/*     */ import thelm.jaopca.utils.JsonHelper;
/*     */ 
/*     */ public class BlockFormSettingsDeserializer implements JsonDeserializer<IBlockFormSettings> {
/*  21 */   public static final BlockFormSettingsDeserializer INSTANCE = new BlockFormSettingsDeserializer();
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockFormSettings deserialize(JsonElement jsonElement, JsonDeserializationContext context) {
/*  26 */     return deserialize(jsonElement, IBlockFormSettings.class, context);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockFormSettings deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/*  31 */     JsonHelper jsonHelper = JsonHelper.INSTANCE;
/*  32 */     JsonObject json = jsonHelper.getJsonObject(jsonElement, "settings");
/*  33 */     IBlockFormSettings settings = BlockFormType.INSTANCE.getNewSettings();
/*  34 */     if (json.has("blockMaterial")) {
/*  35 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "blockMaterial");
/*  36 */       if (!functionJson.has("default")) {
/*  37 */         functionJson.addProperty("default", "iron");
/*     */       }
/*  39 */       settings.setMaterialFunction((Function)jsonHelper.deserializeType(json, "blockMaterial", context, BlockFormType.MATERIAL_FUNCTION_TYPE));
/*     */     } 
/*  41 */     if (json.has("soundType")) {
/*  42 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "soundType");
/*  43 */       if (!functionJson.has("default")) {
/*  44 */         functionJson.addProperty("default", "metal");
/*     */       }
/*  46 */       settings.setSoundTypeFunction((Function)jsonHelper.deserializeType(json, "soundType", context, BlockFormType.SOUND_TYPE_FUNCTION_TYPE));
/*     */     } 
/*  48 */     if (json.has("lightOpacity")) {
/*  49 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "lightOpacity");
/*  50 */       if (!functionJson.has("default")) {
/*  51 */         functionJson.addProperty("default", Integer.valueOf(0));
/*     */       }
/*  53 */       settings.setLightOpacityFunction((ToIntFunction)jsonHelper.deserializeType(json, "lightOpacity", context, FormTypeHandler.INT_FUNCTION_TYPE));
/*     */     } 
/*  55 */     if (json.has("lightValue")) {
/*  56 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "lightValue");
/*  57 */       if (!functionJson.has("default")) {
/*  58 */         functionJson.addProperty("default", Integer.valueOf(0));
/*     */       }
/*  60 */       settings.setLightValueFunction((ToIntFunction)jsonHelper.deserializeType(json, "lightValue", context, FormTypeHandler.INT_FUNCTION_TYPE));
/*     */     } 
/*  62 */     if (json.has("blockHardness")) {
/*  63 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "blockHardness");
/*  64 */       if (!functionJson.has("default")) {
/*  65 */         functionJson.addProperty("default", Integer.valueOf(5));
/*     */       }
/*  67 */       settings.setBlockHardnessFunction((ToDoubleFunction)jsonHelper.deserializeType(json, "blockHardness", context, FormTypeHandler.DOUBLE_FUNCTION_TYPE));
/*     */     } 
/*  69 */     if (json.has("explosionResistance")) {
/*  70 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "explosionResistance");
/*  71 */       if (!functionJson.has("default")) {
/*  72 */         functionJson.addProperty("default", Integer.valueOf(6));
/*     */       }
/*  74 */       settings.setExplosionResistanceFunction((ToDoubleFunction)jsonHelper.deserializeType(json, "explosionResistance", context, FormTypeHandler.DOUBLE_FUNCTION_TYPE));
/*     */     } 
/*  76 */     if (json.has("slipperiness")) {
/*  77 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "slipperiness");
/*  78 */       if (!functionJson.has("default")) {
/*  79 */         functionJson.addProperty("default", Double.valueOf(0.6D));
/*     */       }
/*  81 */       settings.setSlipperinessFunction((ToDoubleFunction)jsonHelper.deserializeType(json, "slipperiness", context, FormTypeHandler.DOUBLE_FUNCTION_TYPE));
/*     */     } 
/*  83 */     if (json.has("boundingBox")) {
/*  84 */       settings.setBoundingBox((AxisAlignedBB)jsonHelper.deserializeType(json, "boundingBox", context, AxisAlignedBB.class));
/*     */     }
/*  86 */     if (json.has("harvestTool")) {
/*  87 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "harvestTool");
/*  88 */       if (!functionJson.has("default")) {
/*  89 */         functionJson.addProperty("default", "minecraft:mineable/pickaxe");
/*     */       }
/*  91 */       settings.setHarvestToolFunction((Function)jsonHelper.deserializeType(json, "harvestTool", context, FormTypeHandler.STRING_FUNCTION_TYPE));
/*     */     } 
/*  93 */     if (json.has("harvestLevel")) {
/*  94 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "harvestLevel");
/*  95 */       if (!functionJson.has("default")) {
/*  96 */         functionJson.addProperty("default", Integer.valueOf(-1));
/*     */       }
/*  98 */       settings.setHarvestLevelFunction((ToIntFunction)jsonHelper.deserializeType(json, "harvestLevel", context, FormTypeHandler.INT_FUNCTION_TYPE));
/*     */     } 
/* 100 */     if (json.has("flammability")) {
/* 101 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "flammability");
/* 102 */       if (!functionJson.has("default")) {
/* 103 */         functionJson.addProperty("default", Integer.valueOf(0));
/*     */       }
/* 105 */       settings.setFlammabilityFunction((ToIntFunction)jsonHelper.deserializeType(json, "flammability", context, FormTypeHandler.INT_FUNCTION_TYPE));
/*     */     } 
/* 107 */     if (json.has("fireSpreadSpeed")) {
/* 108 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "fireSpreadSpeed");
/* 109 */       if (!functionJson.has("default")) {
/* 110 */         functionJson.addProperty("default", Integer.valueOf(0));
/*     */       }
/* 112 */       settings.setFireSpreadSpeedFunction((ToIntFunction)jsonHelper.deserializeType(json, "fireSpreadSpeed", context, FormTypeHandler.INT_FUNCTION_TYPE));
/*     */     } 
/* 114 */     if (json.has("isFireSource")) {
/* 115 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "isFireSource");
/* 116 */       if (!functionJson.has("default")) {
/* 117 */         functionJson.addProperty("default", Boolean.valueOf(false));
/*     */       }
/* 119 */       settings.setIsFireSourceFunction((Predicate)jsonHelper.deserializeType(json, "isFireSource", context, FormTypeHandler.PREDICATE_TYPE));
/*     */     } 
/* 121 */     if (json.has("isBeaconBase")) {
/* 122 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "isBeaconBase");
/* 123 */       if (!functionJson.has("default")) {
/* 124 */         functionJson.addProperty("default", Boolean.valueOf(false));
/*     */       }
/* 126 */       settings.setIsBeaconBaseFunction((Predicate)jsonHelper.deserializeType(json, "isBeaconBase", context, FormTypeHandler.PREDICATE_TYPE));
/*     */     } 
/* 128 */     if (json.has("itemStackLimit")) {
/* 129 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "itemStackLimit");
/* 130 */       if (!functionJson.has("default")) {
/* 131 */         functionJson.addProperty("default", Integer.valueOf(64));
/*     */       }
/* 133 */       settings.setItemStackLimitFunction((ToIntFunction)jsonHelper.deserializeType(json, "itemStackLimit", context, FormTypeHandler.INT_FUNCTION_TYPE));
/*     */     } 
/* 135 */     if (json.has("hasEffect")) {
/* 136 */       boolean hasEffect = jsonHelper.getBoolean(json, "hasEffect");
/* 137 */       settings.setHasEffectFunction(m -> (m.hasEffect() || hasEffect));
/*     */     } 
/* 139 */     if (json.has("rarity")) {
/* 140 */       EnumRarity rarity = (EnumRarity)jsonHelper.deserializeType(json, "rarity", context, EnumRarity.class);
/* 141 */       settings.setDisplayRarityFunction(m -> rarity);
/*     */     } 
/* 143 */     if (json.has("burnTime")) {
/* 144 */       JsonObject functionJson = jsonHelper.getJsonObject(json, "burnTime");
/* 145 */       if (!functionJson.has("default")) {
/* 146 */         functionJson.addProperty("default", Integer.valueOf(-1));
/*     */       }
/* 148 */       settings.setBurnTimeFunction((ToIntFunction)jsonHelper.deserializeType(json, "burnTime", context, FormTypeHandler.INT_FUNCTION_TYPE));
/*     */     } 
/* 150 */     return settings;
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\custom\json\BlockFormSettingsDeserializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */