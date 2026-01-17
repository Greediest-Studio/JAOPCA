//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.localization;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Map;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.util.text.translation.I18n;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ import thelm.jaopca.api.localization.ILocalizer;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LocalizerDefault
/*    */   implements ILocalizer
/*    */ {
/* 18 */   public static final LocalizerDefault INSTANCE = new LocalizerDefault();
/*    */   
/*    */   public String localizeMaterialForm(String formTranslationKey, IMaterial material, String overrideKey) {
/*    */     String materialName;
/* 22 */     Map<String, String> locMap = ApiImpl.INSTANCE.currentMaterialLocalizationMap();
/* 23 */     if (I18n.canTranslate(overrideKey)) {
/* 24 */       return I18n.translateToLocal(overrideKey);
/*    */     }
/* 26 */     if (locMap.containsKey(overrideKey)) {
/* 27 */       return locMap.get(overrideKey);
/*    */     }
/*    */     
/* 30 */     String materialKey = "jaopca.material." + material.getName();
/* 31 */     if (I18n.canTranslate(materialKey)) {
/* 32 */       materialName = I18n.translateToLocal(materialKey);
/*    */     }
/* 34 */     else if (locMap.containsKey(materialKey)) {
/* 35 */       materialName = locMap.get(materialKey);
/*    */     } else {
/*    */       
/* 38 */       materialName = splitAndCapitalize(material.getName());
/*    */     } 
/* 40 */     if (I18n.canTranslate(formTranslationKey) || !locMap.containsKey(formTranslationKey)) {
/* 41 */       return I18n.translateToLocalFormatted(formTranslationKey, new Object[] { materialName });
/*    */     }
/*    */     
/* 44 */     return String.format(locMap.get(overrideKey), new Object[] { materialName });
/*    */   }
/*    */ 
/*    */   
/*    */   public static String splitAndCapitalize(String camelCase) {
/* 49 */     return Arrays.<String>stream(StringUtils.splitByCharacterTypeCamelCase(camelCase)).map(StringUtils::capitalize).collect(Collectors.joining(" "));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\localization\LocalizerDefault.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
