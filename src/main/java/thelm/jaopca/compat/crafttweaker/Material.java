/*     */ package thelm.jaopca.compat.crafttweaker;
/*     */ 
/*     */ import crafttweaker.annotations.ZenRegister;
/*     */ import crafttweaker.api.item.IItemStack;
/*     */ import crafttweaker.api.liquid.ILiquidStack;
/*     */ import crafttweaker.api.minecraft.CraftTweakerMC;
/*     */ import crafttweaker.api.oredict.IOreDictEntry;
/*     */ import java.util.TreeMap;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import stanhebben.zenscript.annotations.ZenClass;
/*     */ import stanhebben.zenscript.annotations.ZenGetter;
/*     */ import stanhebben.zenscript.annotations.ZenMethod;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ 
/*     */ @ZenRegister
/*     */ @ZenClass("mods.jaopca.Material")
/*     */ public class Material
/*     */ {
/*  23 */   private static final TreeMap<IMaterial, Material> MATERIAL_WRAPPERS = new TreeMap<>();
/*     */   private final IMaterial material;
/*     */   
/*     */   public static Material getMaterialWrapper(IMaterial material) {
/*  27 */     return MATERIAL_WRAPPERS.computeIfAbsent(material, Material::new);
/*     */   }
/*     */   
/*     */   private Material(IMaterial material) {
/*  31 */     this.material = material;
/*     */   }
/*     */   
/*     */   public IMaterial getInternal() {
/*  35 */     return this.material;
/*     */   }
/*     */   
/*     */   @ZenGetter("name")
/*     */   public String getName() {
/*  40 */     return this.material.getName();
/*     */   }
/*     */   
/*     */   @ZenGetter("type")
/*     */   public String getType() {
/*  45 */     return this.material.getType().getName();
/*     */   }
/*     */   
/*     */   @ZenGetter("alternativeNames")
/*     */   public String[] getAlternativeNames() {
/*  50 */     return (String[])this.material.getAlternativeNames().toArray((Object[])new String[0]);
/*     */   }
/*     */   
/*     */   @ZenMethod
/*     */   public Material getExtra(int index) {
/*  55 */     return new Material(this.material.getExtra(index));
/*     */   }
/*     */   
/*     */   @ZenMethod
/*     */   public boolean hasExtra(int index) {
/*  60 */     return this.material.hasExtra(index);
/*     */   }
/*     */   
/*     */   @ZenMethod
/*     */   public IOreDictEntry getOreDictEntry(String prefix) {
/*  65 */     return CraftTweakerMC.getOreDict(MiscHelper.INSTANCE.getOredictName(prefix, this.material.getName()));
/*     */   }
/*     */   
/*     */   @ZenMethod
/*     */   public IItemStack getItemStack(String prefix, int count) {
/*  70 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  71 */     ItemStack stack = miscHelper.getItemStack(miscHelper.getOredictName(prefix, this.material.getName()), count);
/*  72 */     return CraftTweakerMC.getIItemStack(stack);
/*     */   }
/*     */   
/*     */   @ZenMethod
/*     */   public IItemStack getItemStack(String prefix) {
/*  77 */     return getItemStack(prefix, 1);
/*     */   }
/*     */   
/*     */   @ZenMethod
/*     */   public ILiquidStack getLiquidStack(String prefix, int count) {
/*  82 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  83 */     FluidStack stack = miscHelper.getFluidStack(miscHelper.getOredictName(prefix, this.material.getName()), count);
/*  84 */     return CraftTweakerMC.getILiquidStack(stack);
/*     */   }
/*     */   
/*     */   @ZenMethod
/*     */   public MaterialForm getMaterialForm(Form form) {
/*  89 */     if (form.containsMaterial(this)) {
/*  90 */       return MaterialForm.getMaterialFormWrapper(form.getInternal(), this.material);
/*     */     }
/*  92 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  97 */     if (obj instanceof Material) {
/*  98 */       return (this.material == ((Material)obj).material);
/*     */     }
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 105 */     return this.material.hashCode() + 7;
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\crafttweaker\Material.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */