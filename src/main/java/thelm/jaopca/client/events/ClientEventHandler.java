//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.client.events;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.resources.IReloadableResourceManager;
/*    */ import net.minecraft.client.resources.IResourceManager;
/*    */ import net.minecraftforge.client.event.ColorHandlerEvent;
/*    */ import net.minecraftforge.client.event.ModelRegistryEvent;
/*    */ import net.minecraftforge.client.event.TextureStitchEvent;
/*    */ import net.minecraftforge.client.model.ICustomModelLoader;
/*    */ import net.minecraftforge.client.model.ModelLoaderRegistry;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import thelm.jaopca.client.colors.ColorHandler;
/*    */ import thelm.jaopca.client.models.ModelHandler;
/*    */ import thelm.jaopca.client.models.fluids.TexturedFluidModel;
/*    */ import thelm.jaopca.client.resources.ResourceHandler;
/*    */ import thelm.jaopca.events.CommonEventHandler;
/*    */ import thelm.jaopca.localization.LocalizationRepoHandler;
/*    */ 
/*    */ public class ClientEventHandler extends CommonEventHandler {
/*    */   static {
/* 21 */     ModelLoaderRegistry.registerLoader((ICustomModelLoader)TexturedFluidModel.Loader.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInit(FMLInitializationEvent event) {
/* 26 */     super.onInit(event);
/* 27 */     Minecraft mc = Minecraft.getMinecraft();
/* 28 */     LocalizationRepoHandler.setup(this.modConfigDir);
/* 29 */     ((IReloadableResourceManager)mc.getResourceManager()).registerReloadListener(rm -> LocalizationRepoHandler.reload());
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onModelRegistry(ModelRegistryEvent event) {
/* 34 */     ModelHandler.registerModels();
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onColorHandler(ColorHandlerEvent.Item event) {
/* 39 */     ColorHandler.setup(event);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onTextureStitchPre(TextureStitchEvent.Pre event) {
/* 44 */     ResourceHandler.registerTextures(event.getMap());
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\client\events\ClientEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
