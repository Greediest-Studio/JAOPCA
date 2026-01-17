/*    */ package thelm.jaopca.compat.galaxyspace;
/*    */ 
/*    */ import thelm.jaopca.api.oredict.IOredictModule;
/*    */ import thelm.jaopca.api.oredict.JAOPCAOredictModule;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ 
/*    */ @JAOPCAOredictModule(modDependencies = {"galaxyspace"})
/*    */ public class GalaxySpaceOredictModule
/*    */   implements IOredictModule
/*    */ {
/*    */   public String getName() {
/* 13 */     return "galaxyspace";
/*    */   }
/*    */ 
/*    */   
/*    */   public void register() {
/* 18 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 19 */     apiImpl.registerOredict("blockCobalt", "galaxyspace:decoblocks@4");
/* 20 */     apiImpl.registerOredict("blockNickel", "galaxyspace:decoblocks@5");
/* 21 */     apiImpl.registerOredict("blockMagnesium", "galaxyspace:decoblocks@6");
/* 22 */     apiImpl.registerOredict("oreMeteoricIron", "galaxyspace:ceresblocks@3");
/* 23 */     apiImpl.registerOredict("oreIron", "galaxyspace:phobosblocks@2");
/* 24 */     apiImpl.registerOredict("oreMeteoricIron", "galaxyspace:phobosblocks@3");
/* 25 */     apiImpl.registerOredict("oreNickel", "galaxyspace:phobosblocks@4");
/* 26 */     apiImpl.registerOredict("oreDesh", "galaxyspace:phobosblocks@5");
/* 27 */     apiImpl.registerOredict("oreTitanium", "galaxyspace:ganymedeblocks@3");
/* 28 */     apiImpl.registerOredict("gemDolomite", "galaxyspace:gs_basic@3");
/* 29 */     apiImpl.registerOredict("gemVolcanic", "galaxyspace:gs_basic@12");
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\galaxyspace\GalaxySpaceOredictModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */