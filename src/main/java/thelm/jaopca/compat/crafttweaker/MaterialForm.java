//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\yxy24\Desktop\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*    */ package thelm.jaopca.compat.crafttweaker;
/*    */ 
/*    */ import com.google.common.collect.TreeBasedTable;
/*    */ import crafttweaker.annotations.ZenRegister;
/*    */ import crafttweaker.api.block.IBlockDefinition;
/*    */ import crafttweaker.api.block.IBlockState;
/*    */ import crafttweaker.api.item.IItemStack;
/*    */ import crafttweaker.api.liquid.ILiquidStack;
/*    */ import crafttweaker.api.minecraft.CraftTweakerMC;
/*    */ import crafttweaker.api.oredict.IOreDictEntry;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import stanhebben.zenscript.annotations.ZenClass;
/*    */ import stanhebben.zenscript.annotations.ZenGetter;
/*    */ import stanhebben.zenscript.annotations.ZenMethod;
/*    */ import thelm.jaopca.api.blocks.IBlockProvider;
/*    */ import thelm.jaopca.api.fluids.IFluidProvider;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.items.IItemProvider;
/*    */ import thelm.jaopca.api.materialforms.IMaterialFormInfo;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.utils.MiscHelper;
/*    */ 
/*    */ 
/*    */ @ZenRegister
/*    */ @ZenClass("mods.jaopca.MaterialForm")
/*    */ public class MaterialForm
/*    */ {
/* 29 */   private static final TreeBasedTable<IForm, IMaterial, MaterialForm> MATERIAL_FORM_WRAPPERS = TreeBasedTable.create();
/*    */   private final IMaterialFormInfo info;
/*    */   
/*    */   public static MaterialForm getMaterialFormWrapper(IForm form, IMaterial material) {
/* 33 */     MaterialForm materialForm = (MaterialForm)MATERIAL_FORM_WRAPPERS.get(form, material);
/* 34 */     if (materialForm == null) {
/* 35 */       IMaterialFormInfo info = form.getType().getMaterialFormInfo(form, material);
/* 36 */       materialForm = new MaterialForm(info);
/*    */     } 
/* 38 */     return materialForm;
/*    */   }
/*    */   
/*    */   private MaterialForm(IMaterialFormInfo info) {
/* 42 */     this.info = info;
/*    */   }
/*    */   
/*    */   public IMaterialFormInfo getInternal() {
/* 46 */     return this.info;
/*    */   }
/*    */   
/*    */   @ZenGetter("form")
/*    */   public Form getForm() {
/* 51 */     return Form.getFormWrapper(this.info.getMaterialForm().getForm());
/*    */   }
/*    */   
/*    */   @ZenGetter("material")
/*    */   public Material getMaterial() {
/* 56 */     return Material.getMaterialWrapper(this.info.getMaterialForm().getMaterial());
/*    */   }
/*    */   
/*    */   @ZenMethod
/*    */   public IOreDictEntry asOreDictEntry() {
/* 61 */     return CraftTweakerMC.getOreDict(MiscHelper.INSTANCE.getOredictName(this.info.getMaterialForm().getForm().getSecondaryName(), this.info.getMaterialForm().getMaterial().getName()));
/*    */   }
/*    */   
/*    */   @ZenMethod
/*    */   public IItemStack asItemStack(int count) {
/* 66 */     if (this.info instanceof IItemProvider) {
/* 67 */       return CraftTweakerMC.getIItemStack(new ItemStack(((IItemProvider)this.info).asItem(), count));
/*    */     }
/* 69 */     return null;
/*    */   }
/*    */   
/*    */   @ZenMethod
/*    */   public IItemStack asItemStack() {
/* 74 */     return asItemStack(1);
/*    */   }
/*    */   
/*    */   @ZenMethod
/*    */   public ILiquidStack asLiquidStack(int amount) {
/* 79 */     if (this.info instanceof IFluidProvider) {
/* 80 */       return CraftTweakerMC.getILiquidStack(new FluidStack(((IFluidProvider)this.info).asFluid(), amount));
/*    */     }
/* 82 */     return null;
/*    */   }
/*    */   
/*    */   @ZenMethod
/*    */   public IBlockDefinition asBlockDefinition() {
/* 87 */     if (this.info instanceof IBlockProvider) {
/* 88 */       return CraftTweakerMC.getBlockDefinition(((IBlockProvider)this.info).asBlock());
/*    */     }
/* 90 */     return null;
/*    */   }
/*    */   
/*    */   @ZenMethod
/*    */   public IBlockState asBlockState() {
/* 95 */     if (this.info instanceof IBlockProvider) {
/* 96 */       return CraftTweakerMC.getBlockState(((IBlockProvider)this.info).asBlock().getDefaultState());
/*    */     }
/* 98 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\crafttweaker\MaterialForm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
