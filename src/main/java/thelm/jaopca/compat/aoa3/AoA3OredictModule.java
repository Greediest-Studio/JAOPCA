/*    */ package thelm.jaopca.compat.aoa3;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.List;
/*    */ import net.minecraft.item.Item;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.oredict.IOredictModule;
/*    */ import thelm.jaopca.api.oredict.JAOPCAOredictModule;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ 
/*    */ 
/*    */ @JAOPCAOredictModule(modDependencies = {"aoa3"})
/*    */ public class AoA3OredictModule
/*    */   implements IOredictModule
/*    */ {
/* 18 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */ 
/*    */   
/*    */   public String getName() {
/* 22 */     return "aoa3";
/*    */   }
/*    */ 
/*    */   
/*    */   public void register() {
/*    */     try {
/* 28 */       Class<?> registerClass = Class.forName("net.tslat.aoa3.common.registration.ItemRegister");
/* 29 */       Field listField = registerClass.getDeclaredField("itemRegistryList");
/* 30 */       listField.setAccessible(true);
/* 31 */       Class<?> wrapperClass = Class.forName("net.tslat.aoa3.common.registration.ItemRegister$ItemRegistryWrapper");
/* 32 */       Field itemField = wrapperClass.getDeclaredField("item");
/* 33 */       itemField.setAccessible(true);
/* 34 */       Field oredictField = wrapperClass.getDeclaredField("oreDictEntries");
/* 35 */       oredictField.setAccessible(true);
/* 36 */       List<?> list = (List)listField.get(null);
/* 37 */       for (Object entry : list) {
/* 38 */         Item item = (Item)itemField.get(entry);
/* 39 */         String[] oredicts = (String[])oredictField.get(entry);
/* 40 */         if (oredicts != null) {
/* 41 */           for (String oredict : oredicts) {
/* 42 */             ApiImpl.INSTANCE.registerOredict(oredict, item);
/*    */           }
/*    */         }
/*    */       }
/*    */     
/* 47 */     } catch (Exception e) {
/* 48 */       LOGGER.error("Could not access AoA3 item list.", e);
/*    */     } 
/*    */     try {
/* 51 */       Class<?> registerClass = Class.forName("net.tslat.aoa3.common.registration.BlockRegister");
/* 52 */       Field listField = registerClass.getDeclaredField("blockRegistryList");
/* 53 */       listField.setAccessible(true);
/* 54 */       Class<?> wrapperClass = Class.forName("net.tslat.aoa3.common.registration.BlockRegister$BlockRegistryWrapper");
/* 55 */       Field itemField = wrapperClass.getDeclaredField("itemBlock");
/* 56 */       itemField.setAccessible(true);
/* 57 */       Field oredictField = wrapperClass.getDeclaredField("oreDictEntries");
/* 58 */       oredictField.setAccessible(true);
/* 59 */       List<?> list = (List)listField.get(null);
/* 60 */       for (Object entry : list) {
/* 61 */         Item item = (Item)itemField.get(entry);
/* 62 */         String[] oredicts = (String[])oredictField.get(entry);
/* 63 */         if (oredicts != null) {
/* 64 */           for (String oredict : oredicts) {
/* 65 */             ApiImpl.INSTANCE.registerOredict(oredict, item);
/*    */           }
/*    */         }
/*    */       }
/*    */     
/* 70 */     } catch (Exception e) {
/* 71 */       LOGGER.error("Could not access AoA3 block list.", e);
/*    */     } 
/* 73 */     ApiImpl apiImpl = ApiImpl.INSTANCE;
/* 74 */     apiImpl.registerOredict("gemBlueGemstone", "aoa3:blue_gemstones");
/* 75 */     apiImpl.registerOredict("gemGreenGemstone", "aoa3:green_gemstones");
/* 76 */     apiImpl.registerOredict("gemPurpleGemstone", "aoa3:purple_gemstones");
/* 77 */     apiImpl.registerOredict("gemRedGemstone", "aoa3:red_gemstones");
/* 78 */     apiImpl.registerOredict("gemWhiteGemstone", "aoa3:white_gemstones");
/* 79 */     apiImpl.registerOredict("gemYellowGemstone", "aoa3:yellow_gemstones");
/* 80 */     apiImpl.registerOredict("ingotRunium", "aoa3:runium_chunk");
/* 81 */     apiImpl.registerOredict("ingotChargedRunium", "aoa3:charged_runium_chunk");
/* 82 */     apiImpl.registerOredict("crystalChestboneFragments", "aoa3:chestbone_fragment");
/* 83 */     apiImpl.registerOredict("crystalFootboneFragments", "aoa3:footbone_fragment");
/* 84 */     apiImpl.registerOredict("crystalLegboneFragments", "aoa3:legbone_fragment");
/* 85 */     apiImpl.registerOredict("crystalSkullboneFragments", "aoa3:skullbone_fragment");
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\aoa3\AoA3OredictModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */