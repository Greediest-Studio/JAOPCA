/*    */ package thelm.jaopca.custom.json;
/*    */ 
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import java.lang.reflect.Type;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import thelm.jaopca.utils.JsonHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AABBDeserializer
/*    */   implements JsonDeserializer<AxisAlignedBB>
/*    */ {
/* 18 */   public static final AABBDeserializer INSTANCE = new AABBDeserializer();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AxisAlignedBB deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 24 */     JsonHelper jsonHelper = JsonHelper.INSTANCE;
/* 25 */     JsonObject json = jsonHelper.getJsonObject(jsonElement, "element");
/* 26 */     JsonArray jsonArrayFrom = jsonHelper.getJsonArray(json, "from");
/* 27 */     if (jsonArrayFrom.size() != 3) {
/* 28 */       throw new JsonParseException("Expected 3 from values, found: " + jsonArrayFrom.size());
/*    */     }
/* 30 */     JsonArray jsonArrayTo = jsonHelper.getJsonArray(json, "to");
/* 31 */     if (jsonArrayTo.size() != 3) {
/* 32 */       throw new JsonParseException("Expected 3 to values, found: " + jsonArrayTo.size());
/*    */     }
/* 34 */     double xFrom = jsonHelper.getDouble(jsonArrayFrom.get(0), "xFrom");
/* 35 */     double yFrom = jsonHelper.getDouble(jsonArrayFrom.get(1), "yFrom");
/* 36 */     double zFrom = jsonHelper.getDouble(jsonArrayFrom.get(2), "zFrom");
/* 37 */     double xTo = jsonHelper.getDouble(jsonArrayFrom.get(0), "xTo");
/* 38 */     double yTo = jsonHelper.getDouble(jsonArrayFrom.get(1), "yTo");
/* 39 */     double zTo = jsonHelper.getDouble(jsonArrayFrom.get(2), "zTo");
/* 40 */     return new AxisAlignedBB(xFrom, yFrom, zFrom, xTo, yTo, zTo);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\custom\json\AABBDeserializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */