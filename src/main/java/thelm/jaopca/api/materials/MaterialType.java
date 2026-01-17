/*    */ package thelm.jaopca.api.materials;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Locale;
/*    */ import org.apache.commons.lang3.ArrayUtils;
/*    */ 
/*    */ 
/*    */ public enum MaterialType
/*    */ {
/* 10 */   INGOT("ingot"),
/* 11 */   GEM("gem"),
/* 12 */   CRYSTAL("crystal"),
/* 13 */   DUST("dust"),
/* 14 */   INGOT_PLAIN("ingot"),
/* 15 */   GEM_PLAIN("gem"),
/* 16 */   CRYSTAL_PLAIN("crystal"),
/* 17 */   DUST_PLAIN("dust"); public static final MaterialType[] INGOTS; public static final MaterialType[] GEMS; public static final MaterialType[] CRYSTALS;
/*    */   static {
/* 19 */     INGOTS = new MaterialType[] { INGOT, INGOT_PLAIN };
/* 20 */     GEMS = new MaterialType[] { GEM, GEM_PLAIN };
/* 21 */     CRYSTALS = new MaterialType[] { CRYSTAL, CRYSTAL_PLAIN };
/* 22 */     DUSTS = new MaterialType[] { DUST, DUST_PLAIN };
/* 23 */     NON_DUSTS = new MaterialType[] { INGOT, GEM, CRYSTAL, INGOT_PLAIN, GEM_PLAIN, CRYSTAL_PLAIN };
/* 24 */     ORE = new MaterialType[] { INGOT, GEM, CRYSTAL, DUST };
/*    */   }
/*    */   public static final MaterialType[] DUSTS; public static final MaterialType[] NON_DUSTS; public static final MaterialType[] ORE; private final String formName;
/*    */   
/*    */   MaterialType(String formName) {
/* 29 */     this.formName = formName;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 33 */     return name().toLowerCase(Locale.US);
/*    */   }
/*    */   
/*    */   public String getFormName() {
/* 37 */     return this.formName;
/*    */   }
/*    */   
/*    */   public boolean isIngot() {
/* 41 */     return ArrayUtils.contains((Object[])INGOTS, this);
/*    */   }
/*    */   
/*    */   public boolean isGem() {
/* 45 */     return ArrayUtils.contains((Object[])GEMS, this);
/*    */   }
/*    */   
/*    */   public boolean isCrystal() {
/* 49 */     return ArrayUtils.contains((Object[])CRYSTALS, this);
/*    */   }
/*    */   
/*    */   public boolean isCrystalline() {
/* 53 */     return (isGem() || isCrystal());
/*    */   }
/*    */   
/*    */   public boolean isDust() {
/* 57 */     return ArrayUtils.contains((Object[])DUSTS, this);
/*    */   }
/*    */   
/*    */   public boolean isOre() {
/* 61 */     return ArrayUtils.contains((Object[])ORE, this);
/*    */   }
/*    */   
/*    */   public static MaterialType fromName(String name) {
/* 65 */     return Arrays.<MaterialType>stream(values()).filter(t -> t.getName().equalsIgnoreCase(name)).findAny().orElse(null);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\materials\MaterialType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */