//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.registries;
/*    */ 
/*    */ import com.google.common.collect.Multimap;
/*    */ import com.google.common.collect.Multimaps;
/*    */ import com.google.common.collect.TreeMultimap;
/*    */ import com.google.common.collect.UnmodifiableIterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.event.RegistryEvent;
/*    */ import net.minecraftforge.registries.ForgeRegistry;
/*    */ import net.minecraftforge.registries.RegistryManager;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.materials.Material;
/*    */ import thelm.jaopca.materials.MaterialHandler;
/*    */ import thelm.jaopca.modules.ModuleHandler;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RegistryHandler
/*    */ {
/* 29 */   private static final Logger LOGGER = LogManager.getLogger();
/* 30 */   private static final Multimap<String, String> LEGACY_REMAPS = (Multimap<String, String>)TreeMultimap.create();
/*    */   private static boolean initializedRemaps = false;
/*    */   
/*    */   public static void initializeRemaps() {
/* 34 */     initializedRemaps = true;
/* 35 */     for (IModule module : ModuleHandler.getModules()) {
/* 36 */       LEGACY_REMAPS.putAll((Multimap)Multimaps.forMap(module.getLegacyRemaps()));
/*    */     }
/*    */   }
/*    */   
/*    */   public static <T extends net.minecraftforge.registries.IForgeRegistryEntry<T>> void onMissingMappings(RegistryEvent.MissingMappings<T> event) {
/* 41 */     if (!initializedRemaps) {
/* 42 */       initializeRemaps();
/*    */     }
/* 44 */     for (UnmodifiableIterator<RegistryEvent.MissingMappings.Mapping<T>> unmodifiableIterator = event.getMappings().iterator(); unmodifiableIterator.hasNext(); ) { RegistryEvent.MissingMappings.Mapping<T> mapping = unmodifiableIterator.next();
/* 45 */       LOGGER.debug("Remapping registry entry {}", mapping.key);
/* 46 */       String[] names = mapping.key.getPath().split("_", 2);
/* 47 */       if (names.length == 2) {
/* 48 */         for (Map.Entry<String, String> remap : (Iterable<Map.Entry<String, String>>)LEGACY_REMAPS.entries()) {
/* 49 */           if (names[1].startsWith(remap.getKey())) {
/* 50 */             String materialName = names[1].substring(((String)remap.getKey()).length());
/* 51 */             LOGGER.debug("Checking material {}", materialName);
/*    */             
/* 53 */             List<IMaterial> materials = (List<IMaterial>)MaterialHandler.getMaterials().stream().filter(m -> m.getName().equalsIgnoreCase(materialName)).collect(Collectors.toList());
/* 54 */             for (IMaterial material : materials) {
/* 55 */               String path = (String)remap.getValue() + '.' + MiscHelper.INSTANCE.toLowercaseUnderscore(material.getName());
/* 56 */               ResourceLocation remapLocation = new ResourceLocation(mapping.key.getNamespace(), path);
/* 57 */               ForgeRegistry forgeRegistry = RegistryManager.ACTIVE.getRegistry(event.getName());
/* 58 */               LOGGER.debug("Checking registry entry {}", remapLocation);
/* 59 */               if (forgeRegistry.containsKey(remapLocation)) {
/* 60 */                 mapping.remap(forgeRegistry.getValue(remapLocation));
/* 61 */                 LOGGER.debug("Remapped registry entry {} to {}", mapping.key, remapLocation);
/*    */               } 
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       }
/*    */       
/* 68 */       names = mapping.key.getPath().split("\\.", 2);
/* 69 */       if (names.length == 2) {
/* 70 */         String materialName = names[1].replaceAll("_", "");
/* 71 */         LOGGER.debug("Checking material {}", materialName);
/*    */         
/* 73 */         List<IMaterial> materials = (List<IMaterial>)MaterialHandler.getMaterials().stream().filter(m -> m.getName().equalsIgnoreCase(materialName)).collect(Collectors.toList());
/* 74 */         for (IMaterial material : materials) {
/* 75 */           String path = names[0] + '.' + MiscHelper.INSTANCE.toLowercaseUnderscore(material.getName());
/* 76 */           ResourceLocation remapLocation = new ResourceLocation(mapping.key.getNamespace(), path);
/* 77 */           ForgeRegistry forgeRegistry = RegistryManager.ACTIVE.getRegistry(event.getName());
/* 78 */           LOGGER.debug("Checking registry entry {}", remapLocation);
/* 79 */           if (forgeRegistry.containsKey(remapLocation)) {
/* 80 */             mapping.remap(forgeRegistry.getValue(remapLocation));
/* 81 */             LOGGER.debug("Remapped registry entry {} to {}", mapping.key, remapLocation);
/*    */           } 
/*    */         } 
/*    */       } 
/*    */       
/* 86 */       LOGGER.debug("Could not remap registry entry {}", mapping.key); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\registries\RegistryHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
