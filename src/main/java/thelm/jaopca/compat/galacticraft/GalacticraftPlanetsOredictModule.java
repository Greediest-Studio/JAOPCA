/*    */ package thelm.jaopca.compat.galacticraft;
/*    */ 
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.oredict.IOredictModule;
/*    */ import thelm.jaopca.api.oredict.JAOPCAOredictModule;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ 
/*    */ @JAOPCAOredictModule(modDependencies = {"galacticraftplanets"})
/*    */ public class GalacticraftPlanetsOredictModule
/*    */   implements IOredictModule
/*    */ {
/* 14 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */ 
/*    */   
/*    */   public String getName() {
/* 18 */     return "galacticraftplanets";
/*    */   }
/*    */ 
/*    */   
/*    */   public void register() {
/*    */     try {
/* 24 */       Class.forName("micdoodle8.mods.galacticraft.planets.mars.blocks.MarsBlocks").getMethod("oreDictRegistration", new Class[0]).invoke(null, new Object[0]);
/*    */     }
/* 26 */     catch (Exception e) {
/* 27 */       LOGGER.error("Could not invoke Galacticraft Mars oredict register method", e);
/*    */     } 
/*    */     try {
/* 30 */       Class.forName("micdoodle8.mods.galacticraft.planets.asteroids.blocks.AsteroidBlocks").getMethod("oreDictRegistration", new Class[0]).invoke(null, new Object[0]);
/*    */     }
/* 32 */     catch (Exception e) {
/* 33 */       LOGGER.error("Could not invoke Galacticraft Asteroids oredict register method", e);
/*    */     } 
/*    */     try {
/* 36 */       Class.forName("micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems").getMethod("oreDictRegistrations", new Class[0]).invoke(null, new Object[0]);
/*    */     }
/* 38 */     catch (Exception e) {
/* 39 */       LOGGER.error("Could not invoke Galacticraft Asteroids oredict register method", e);
/*    */     } 
/*    */     try {
/* 42 */       Class.forName("micdoodle8.mods.galacticraft.planets.venus.VenusBlocks").getMethod("oreDictRegistration", new Class[0]).invoke(null, new Object[0]);
/*    */     }
/* 44 */     catch (Exception e) {
/* 45 */       LOGGER.error("Could not invoke Galacticraft Venus oredict register method", e);
/*    */     } 
/* 47 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 48 */     apiImpl.registerOredict("oreTitanium", "galacticraftplanets:asteroids_block@4");
/* 49 */     apiImpl.registerOredict("dustSolar", "galacticraftplanets:basic_item_venus@4");
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\galacticraft\GalacticraftPlanetsOredictModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */