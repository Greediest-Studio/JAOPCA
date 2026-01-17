/*    */ package thelm.jaopca.compat.crafttweaker;
/*    */ 
/*    */ import crafttweaker.annotations.ZenRegister;
/*    */ import java.util.Arrays;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ import stanhebben.zenscript.annotations.ZenClass;
/*    */ import stanhebben.zenscript.annotations.ZenMethod;
/*    */ import thelm.jaopca.api.materials.MaterialType;
/*    */ import thelm.jaopca.materials.MaterialHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @ZenRegister
/*    */ @ZenClass("mods.jaopca.MaterialRegister")
/*    */ public class MaterialRegister
/*    */ {
/*    */   @ZenMethod
/*    */   public static boolean registerMaterial(String name, String type, @Nullable String[] alternativeNames, int color, boolean hasEffect, String rarity) {
/* 30 */     MaterialType materialType = MaterialType.fromName(type);
/* 31 */     if (materialType == null) {
/* 32 */       throw new IllegalArgumentException("未知的材料类型: " + type);
/*    */     }
/* 34 */     Set<String> altNames = (alternativeNames == null) ? new HashSet<>() : new HashSet<>(Arrays.asList(alternativeNames));
/*    */     
/*    */     try {
/* 37 */       EnumRarity enumRarity = EnumRarity.valueOf(rarity.toUpperCase());
/* 38 */     } catch (Exception e) {
/* 39 */       EnumRarity enumRarity = EnumRarity.COMMON;
/*    */     } 
/* 41 */     return MaterialHandler.addMaterial(name, materialType, altNames);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\crafttweaker\MaterialRegister.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */