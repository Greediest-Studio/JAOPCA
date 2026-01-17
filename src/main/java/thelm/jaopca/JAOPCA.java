/*    */ package thelm.jaopca;
/*    */ 
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fluids.FluidRegistry;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventHandler;
/*    */ import net.minecraftforge.fml.common.SidedProxy;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
/*    */ import thelm.jaopca.events.CommonEventHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mod(modid = "jaopca", name = "JAOPCA", version = "1.12.2-2.3.13.34", dependencies = "required-before:wrapup")
/*    */ public class JAOPCA
/*    */ {
/*    */   public static final String MOD_ID = "jaopca";
/*    */   public static final String NAME = "JAOPCA";
/*    */   public static final String VERSION = "1.12.2-2.3.13.34";
/*    */   public static final String DEPENDENCIES = "required-before:wrapup";
/*    */   @SidedProxy(clientSide = "thelm.jaopca.client.events.ClientEventHandler", serverSide = "thelm.jaopca.events.CommonEventHandler", modId = "jaopca")
/*    */   public static CommonEventHandler eventHandler;
/*    */   
/*    */   static {
/* 31 */     FluidRegistry.enableUniversalBucket();
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPreInit(FMLPreInitializationEvent event) {
/* 36 */     MinecraftForge.EVENT_BUS.register(eventHandler);
/* 37 */     eventHandler.onPreInit(event);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onInit(FMLInitializationEvent event) {
/* 42 */     eventHandler.onInit(event);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\JAOPCA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */