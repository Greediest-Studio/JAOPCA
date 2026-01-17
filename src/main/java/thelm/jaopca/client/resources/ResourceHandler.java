//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.client.resources;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ public class ResourceHandler
/*    */ {
/* 12 */   private static final List<Supplier<List<ResourceLocation>>> TEXTURE_SUPPLIERS = new ArrayList<>();
/*    */   
/*    */   public static void registerTextures(Supplier<List<ResourceLocation>> supplier) {
/* 15 */     TEXTURE_SUPPLIERS.add(supplier);
/*    */   }
/*    */   
/*    */   public static void registerTextures(TextureMap map) {
/* 19 */     for (Supplier<List<ResourceLocation>> supplier : TEXTURE_SUPPLIERS) {
/* 20 */       for (ResourceLocation location : supplier.get())
/* 21 */         map.registerSprite(location); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\client\resources\ResourceHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
