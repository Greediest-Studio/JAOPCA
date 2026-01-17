/*    */ package thelm.jaopca.compat.nuclearcraft;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.stream.Stream;
/*    */ import thelm.jaopca.api.oredict.IOredictModule;
/*    */ import thelm.jaopca.api.oredict.JAOPCAOredictModule;
/*    */ import thelm.jaopca.utils.ApiImpl;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ @JAOPCAOredictModule(modDependencies = {"nuclearcraft"})
/*    */ public class NuclearCraftOredictModule
/*    */   implements IOredictModule
/*    */ {
/*    */   public NuclearCraftOredictModule() {
/* 28 */     String[] names = (String[])Stream.<String>of(new String[] { "Americium241", "Americium242", "Americium243", "Berkelium247", "Berkelium248", "Californium249", "Californium250", "Californium251", "Californium252", "Copernicium291", "Curium243", "Curium245", "Curium246", "Curium247", "Neptunium236", "Neptunium237", "Plutonium238", "Plutonium239", "Plutonium241", "Plutonium242", "Thorium230", "Thorium232", "Uranium233", "Uranium235", "Uranium238" }).flatMap(name -> Stream.of(new String[] { name + "All", name + "Base" })).toArray(x$0 -> new String[x$0]);
/* 29 */     ApiImpl.INSTANCE.registerBlacklistedMaterialNames(names);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 34 */     return "nuclearcraft";
/*    */   }
/*    */ 
/*    */   
/*    */   public void register() {
/* 39 */     List<String> formats = Arrays.asList(new String[] { "%s", "%sCarbide", "%sNitride", "%sOxide", "%sTRISO", "%sZA", "Depleted%sNitride", "Depleted%sOxide", "Depleted%sTRISO", "Depleted%sZA" });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 48 */     String[] names = (String[])Stream.<String>of(new String[] { "HEA242", "HEB248", "HECf249", "HECf251", "HECm243", "HECm245", "HECm247", "HEN236", "HEP239", "HEP241", "HEU233", "HEU235", "LEA242", "LEB248", "LECf249", "LECf251", "LECm243", "LECm245", "LECm247", "LEN236", "LEP239", "LEP241", "LEU233", "LEU235", "MIX239", "MIX241", "MIX291", "TBU" }).flatMap(name -> formats.stream().map(())).toArray(x$0 -> new String[x$0]);
/* 49 */     ApiImpl.INSTANCE.registerBlacklistedMaterialNames(names);
/* 50 */     ApiImpl.INSTANCE.registerBlacklistedMaterialNames(new String[] { "TBP" });
/* 51 */     NuclearCraftRecipePatcher.registerRecipes();
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\nuclearcraft\NuclearCraftOredictModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */