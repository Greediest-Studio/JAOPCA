//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.custom.utils;
/*    */ 
/*    */ import com.google.common.collect.HashBiMap;
/*    */ import java.util.Locale;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockDeserializationHelper
/*    */ {
/* 12 */   public static final BlockDeserializationHelper INSTANCE = new BlockDeserializationHelper();
/*    */ 
/*    */ 
/*    */   
/* 16 */   private static final HashBiMap<String, Material> BLOCK_MATERIALS = HashBiMap.create();
/* 17 */   private static final HashBiMap<String, SoundType> SOUND_TYPES = HashBiMap.create();
/*    */   
/*    */   public Material getBlockMaterial(String name) {
/* 20 */     return (Material)BLOCK_MATERIALS.get(name.toLowerCase(Locale.US));
/*    */   }
/*    */   
/*    */   public String getBlockMaterialName(Material material) {
/* 24 */     return (String)BLOCK_MATERIALS.inverse().get(material);
/*    */   }
/*    */   
/*    */   public void putBlockMaterial(String name, Material material) {
/* 28 */     BLOCK_MATERIALS.put(name.toLowerCase(Locale.US), material);
/*    */   }
/*    */   
/*    */   public SoundType getSoundType(String name) {
/* 32 */     return (SoundType)SOUND_TYPES.get(name.toLowerCase(Locale.US));
/*    */   }
/*    */   
/*    */   public String getSoundTypeName(SoundType sound) {
/* 36 */     return (String)SOUND_TYPES.inverse().get(sound);
/*    */   }
/*    */   
/*    */   public void putSoundType(String name, SoundType sound) {
/* 40 */     SOUND_TYPES.put(name.toLowerCase(Locale.US), sound);
/*    */   }
/*    */   
/*    */   static {
/* 44 */     INSTANCE.putBlockMaterial("air", Material.AIR);
/* 45 */     INSTANCE.putBlockMaterial("grass", Material.GRASS);
/* 46 */     INSTANCE.putBlockMaterial("ground", Material.GROUND);
/* 47 */     INSTANCE.putBlockMaterial("wood", Material.WOOD);
/* 48 */     INSTANCE.putBlockMaterial("rock", Material.ROCK);
/* 49 */     INSTANCE.putBlockMaterial("iron", Material.IRON);
/* 50 */     INSTANCE.putBlockMaterial("anvil", Material.ANVIL);
/* 51 */     INSTANCE.putBlockMaterial("water", Material.WATER);
/* 52 */     INSTANCE.putBlockMaterial("lava", Material.LAVA);
/* 53 */     INSTANCE.putBlockMaterial("leaves", Material.LEAVES);
/* 54 */     INSTANCE.putBlockMaterial("plants", Material.PLANTS);
/* 55 */     INSTANCE.putBlockMaterial("vine", Material.VINE);
/* 56 */     INSTANCE.putBlockMaterial("sponge", Material.SPONGE);
/* 57 */     INSTANCE.putBlockMaterial("cloth", Material.CLOTH);
/* 58 */     INSTANCE.putBlockMaterial("fire", Material.FIRE);
/* 59 */     INSTANCE.putBlockMaterial("sand", Material.SAND);
/* 60 */     INSTANCE.putBlockMaterial("circuits", Material.CIRCUITS);
/* 61 */     INSTANCE.putBlockMaterial("carpet", Material.CARPET);
/* 62 */     INSTANCE.putBlockMaterial("glass", Material.GLASS);
/* 63 */     INSTANCE.putBlockMaterial("redstone_light", Material.REDSTONE_LIGHT);
/* 64 */     INSTANCE.putBlockMaterial("tnt", Material.TNT);
/* 65 */     INSTANCE.putBlockMaterial("coral", Material.CORAL);
/* 66 */     INSTANCE.putBlockMaterial("ice", Material.ICE);
/* 67 */     INSTANCE.putBlockMaterial("packed_ice", Material.PACKED_ICE);
/* 68 */     INSTANCE.putBlockMaterial("snow", Material.SNOW);
/* 69 */     INSTANCE.putBlockMaterial("crafted_snow", Material.CRAFTED_SNOW);
/* 70 */     INSTANCE.putBlockMaterial("cactus", Material.CACTUS);
/* 71 */     INSTANCE.putBlockMaterial("clay", Material.CLAY);
/* 72 */     INSTANCE.putBlockMaterial("gourd", Material.GOURD);
/* 73 */     INSTANCE.putBlockMaterial("dragon_egg", Material.DRAGON_EGG);
/* 74 */     INSTANCE.putBlockMaterial("portal", Material.PORTAL);
/* 75 */     INSTANCE.putBlockMaterial("cake", Material.CAKE);
/* 76 */     INSTANCE.putBlockMaterial("web", Material.WEB);
/* 77 */     INSTANCE.putBlockMaterial("piston", Material.PISTON);
/* 78 */     INSTANCE.putBlockMaterial("barrier", Material.BARRIER);
/* 79 */     INSTANCE.putBlockMaterial("structure_void", Material.STRUCTURE_VOID);
/*    */     
/* 81 */     INSTANCE.putSoundType("wood", SoundType.WOOD);
/* 82 */     INSTANCE.putSoundType("ground", SoundType.GROUND);
/* 83 */     INSTANCE.putSoundType("plant", SoundType.PLANT);
/* 84 */     INSTANCE.putSoundType("stone", SoundType.STONE);
/* 85 */     INSTANCE.putSoundType("metal", SoundType.METAL);
/* 86 */     INSTANCE.putSoundType("glass", SoundType.GLASS);
/* 87 */     INSTANCE.putSoundType("cloth", SoundType.CLOTH);
/* 88 */     INSTANCE.putSoundType("sand", SoundType.SAND);
/* 89 */     INSTANCE.putSoundType("snow", SoundType.SNOW);
/* 90 */     INSTANCE.putSoundType("ladder", SoundType.LADDER);
/* 91 */     INSTANCE.putSoundType("anvil", SoundType.ANVIL);
/* 92 */     INSTANCE.putSoundType("slime", SoundType.SLIME);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\custo\\utils\BlockDeserializationHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
