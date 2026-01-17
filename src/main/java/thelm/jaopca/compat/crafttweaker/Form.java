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
/*     */ import thelm.jaopca.api.forms.IForm;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.api.materials.MaterialType;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ @ZenRegister
/*     */ @ZenClass("mods.jaopca.Form")
/*     */ public class Form
/*     */ {
/*  24 */   private static final TreeMap<IForm, Form> FORM_WRAPPERS = new TreeMap<>();
/*     */   private final IForm form;
/*     */   
/*     */   public static Form getFormWrapper(IForm form) {
/*  28 */     return FORM_WRAPPERS.computeIfAbsent(form, Form::new);
/*     */   }
/*     */   
/*     */   private Form(IForm form) {
/*  32 */     this.form = form;
/*     */   }
/*     */   
/*     */   public IForm getInternal() {
/*  36 */     return this.form;
/*     */   }
/*     */   
/*     */   @ZenGetter("name")
/*     */   public String getName() {
/*  41 */     return this.form.getName();
/*     */   }
/*     */   
/*     */   @ZenGetter("type")
/*     */   public String getType() {
/*  46 */     return this.form.getType().getName();
/*     */   }
/*     */   
/*     */   @ZenGetter("module")
/*     */   public Module getModule() {
/*  51 */     return Module.getModuleWrapper(this.form.getModule());
/*     */   }
/*     */   
/*     */   @ZenGetter("secondaryName")
/*     */   public String getSecondaryName() {
/*  56 */     return this.form.getSecondaryName();
/*     */   }
/*     */   
/*     */   @ZenGetter("materialTypes")
/*     */   public String[] getMaterialTypes() {
/*  61 */     return (String[])this.form.getMaterialTypes().stream().map(MaterialType::getName).toArray(x$0 -> new String[x$0]);
/*     */   }
/*     */   
/*     */   @ZenGetter("materials")
/*     */   public Material[] getMaterials() {
/*  66 */     return (Material[])this.form.getMaterials().stream().map(Material::getMaterialWrapper).toArray(x$0 -> new Material[x$0]);
/*     */   }
/*     */   
/*     */   @ZenMethod
/*     */   public boolean containsMaterial(Material material) {
/*  71 */     return this.form.getMaterials().contains(material.getInternal());
/*     */   }
/*     */   
/*     */   @ZenMethod
/*     */   public IOreDictEntry getOreDictEntry(String suffix) {
/*  76 */     return CraftTweakerMC.getOreDict(MiscHelper.INSTANCE.getOredictName(this.form.getSecondaryName(), suffix));
/*     */   }
/*     */   
/*     */   @ZenMethod
/*     */   public IItemStack getItemStack(String suffix, int count) {
/*  81 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  82 */     ItemStack stack = miscHelper.getItemStack(miscHelper.getOredictName(this.form.getSecondaryName(), suffix), count);
/*  83 */     return CraftTweakerMC.getIItemStack(stack);
/*     */   }
/*     */   
/*     */   @ZenMethod
/*     */   public IItemStack getItemStack(String suffix) {
/*  88 */     return getItemStack(suffix, 1);
/*     */   }
/*     */   
/*     */   @ZenMethod
/*     */   public ILiquidStack getLiquidStack(String suffix, int amount) {
/*  93 */     MiscHelper miscHelper = MiscHelper.INSTANCE;
/*  94 */     FluidStack stack = miscHelper.getFluidStack(miscHelper.getFluidName(this.form.getSecondaryName(), suffix), amount);
/*  95 */     return CraftTweakerMC.getILiquidStack(stack);
/*     */   }
/*     */   
/*     */   @ZenMethod
/*     */   public MaterialForm getMaterialForm(Material material) {
/* 100 */     if (containsMaterial(material)) {
/* 101 */       return MaterialForm.getMaterialFormWrapper(this.form, material.getInternal());
/*     */     }
/* 103 */     return null;
/*     */   }
/*     */   
/*     */   @ZenGetter("materialForms")
/*     */   public MaterialForm[] getMaterialForms() {
/* 108 */     return (MaterialForm[])this.form.getMaterials().stream().map(m -> MaterialForm.getMaterialFormWrapper(this.form, m)).toArray(x$0 -> new MaterialForm[x$0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 113 */     if (obj instanceof Form) {
/* 114 */       return (this.form == ((Form)obj).form);
/*     */     }
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 121 */     return this.form.hashCode() + 5;
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\crafttweaker\Form.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */