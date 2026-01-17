//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.modules.active;
/*    */ 
/*    */ import net.minecraft.init.Items;
/*    */ import thelm.jaopca.api.oredict.IOredictModule;
/*    */ import thelm.jaopca.api.oredict.JAOPCAOredictModule;
/*    */ import thelm.jaopca.utils.ApiImpl;
/*    */ 
/*    */ @JAOPCAOredictModule
/*    */ public class MinecraftOredictModule
/*    */   implements IOredictModule
/*    */ {
/*    */   public String getName() {
/* 13 */     return "minecraft";
/*    */   }
/*    */ 
/*    */   
/*    */   public void register() {
/* 18 */     ApiImpl.INSTANCE.registerOredict("crystalCoal", Items.COAL);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\modules\active\MinecraftOredictModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
