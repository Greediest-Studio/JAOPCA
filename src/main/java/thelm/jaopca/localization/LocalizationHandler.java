/*    */ package thelm.jaopca.localization;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import java.util.TreeMap;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.resources.Language;
/*    */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*    */ import thelm.jaopca.api.localization.ILocalizer;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ public class LocalizationHandler
/*    */ {
/* 14 */   private static final TreeMap<String, ILocalizer> LOCALIZERS = new TreeMap<>();
/*    */   
/*    */   public static void registerLocalizer(ILocalizer localizer, String... languages) {
/* 17 */     Objects.requireNonNull(localizer);
/* 18 */     for (String language : (String[])Objects.<String[]>requireNonNull(languages)) {
/* 19 */       LOCALIZERS.put(language, localizer);
/*    */     }
/*    */   }
/*    */   
/*    */   public static ILocalizer getCurrentLocalizer() {
/* 24 */     return LOCALIZERS.computeIfAbsent(getLanguage(), key -> LocalizerDefault.INSTANCE);
/*    */   }
/*    */   
/*    */   public static String getLanguage() {
/* 28 */     return MiscHelper.INSTANCE.conditionalSupplier(FMLCommonHandler.instance().getSide()::isClient, () -> (), () -> ())
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 38 */       .get();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\localization\LocalizationHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */