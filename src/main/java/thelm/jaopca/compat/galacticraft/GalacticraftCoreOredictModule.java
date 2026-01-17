/*    */ package thelm.jaopca.compat.galacticraft;
/*    */ 
/*    */ import thelm.jaopca.api.oredict.IOredictModule;
/*    */ import thelm.jaopca.api.oredict.JAOPCAOredictModule;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ 
/*    */ @JAOPCAOredictModule(modDependencies = {"galacticraftcore"})
/*    */ public class GalacticraftCoreOredictModule
/*    */   implements IOredictModule
/*    */ {
/*    */   public String getName() {
/* 13 */     return "galacticraftcore";
/*    */   }
/*    */ 
/*    */   
/*    */   public void register() {
/* 18 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 19 */     apiImpl.registerOredict("oreMeteoricIron", "galacticraftcore:fallen_meteor");
/* 20 */     apiImpl.registerOredict("blockMeteoricIron", "galacticraftcore:basic_block_core@12");
/* 21 */     apiImpl.registerOredict("plateMeteoricIron", "galacticraftcore:item_basic_moon@1");
/* 22 */     apiImpl.registerOredict("oreLunarSapphire", "galacticraftcore:basic_block_moon@6");
/* 23 */     apiImpl.registerOredict("gemLunarSapphire", "galacticraftcore:item_basic_moon@2");
/* 24 */     apiImpl.registerOredict("gemSilicon", "galacticraftcore:basic_item@2");
/* 25 */     apiImpl.registerOredict("crystalCheese", "galacticraftcore:cheese_curd");
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\galacticraft\GalacticraftCoreOredictModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */