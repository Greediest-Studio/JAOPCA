/*     */ package thelm.jaopca.compat.mekanism.gases;
/*     */ 
/*     */ import com.google.common.collect.TreeBasedTable;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import mekanism.api.gas.Gas;
/*     */ import mekanism.api.gas.GasRegistry;
/*     */ import thelm.jaopca.api.forms.IForm;
/*     */ import thelm.jaopca.api.forms.IFormSettings;
/*     */ import thelm.jaopca.api.forms.IFormType;
/*     */ import thelm.jaopca.api.materialforms.IMaterialFormInfo;
/*     */ import thelm.jaopca.api.materials.IMaterial;
/*     */ import thelm.jaopca.compat.mekanism.api.gases.IGasFormSettings;
/*     */ import thelm.jaopca.compat.mekanism.api.gases.IGasFormType;
/*     */ import thelm.jaopca.compat.mekanism.api.gases.IGasInfo;
/*     */ import thelm.jaopca.compat.mekanism.api.gases.IMaterialFormGas;
/*     */ import thelm.jaopca.compat.mekanism.custom.json.GasFormSettingsDeserializer;
/*     */ import thelm.jaopca.forms.FormTypeHandler;
/*     */ import thelm.jaopca.utils.MiscHelper;
/*     */ 
/*     */ public class GasFormType
/*     */   implements IGasFormType
/*     */ {
/*  29 */   public static final GasFormType INSTANCE = new GasFormType();
/*  30 */   private static final TreeSet<IForm> FORMS = new TreeSet<>();
/*  31 */   private static final TreeBasedTable<IForm, IMaterial, IMaterialFormGas> GASES = TreeBasedTable.create();
/*  32 */   private static final TreeBasedTable<IForm, IMaterial, IGasInfo> GAS_INFOS = TreeBasedTable.create();
/*     */   private static boolean registered = false;
/*     */   
/*     */   public static void init() {
/*  36 */     FormTypeHandler.registerFormType((IFormType)INSTANCE);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  41 */     return "gas";
/*     */   }
/*     */ 
/*     */   
/*     */   public void addForm(IForm form) {
/*  46 */     FORMS.add(form);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<IForm> getForms() {
/*  51 */     return Collections.unmodifiableNavigableSet(FORMS);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldRegister(IForm form, IMaterial material) {
/*  56 */     String fluidName = MiscHelper.INSTANCE.getFluidName(form.getSecondaryName(), material.getName());
/*  57 */     return (GasRegistry.getGas(fluidName) == null);
/*     */   }
/*     */ 
/*     */   
/*     */   public IGasInfo getMaterialFormInfo(IForm form, IMaterial material) {
/*  62 */     IGasInfo info = (IGasInfo)GAS_INFOS.get(form, material);
/*  63 */     if (info == null && FORMS.contains(form) && form.getMaterials().contains(material)) {
/*  64 */       info = new GasInfo((IMaterialFormGas)GASES.get(form, material));
/*  65 */       GAS_INFOS.put(form, material, info);
/*     */     } 
/*  67 */     return info;
/*     */   }
/*     */ 
/*     */   
/*     */   public IGasFormSettings getNewSettings() {
/*  72 */     return new GasFormSettings();
/*     */   }
/*     */ 
/*     */   
/*     */   public GsonBuilder configureGsonBuilder(GsonBuilder builder) {
/*  77 */     return builder;
/*     */   }
/*     */ 
/*     */   
/*     */   public IGasFormSettings deserializeSettings(JsonElement jsonElement, JsonDeserializationContext context) {
/*  82 */     return GasFormSettingsDeserializer.INSTANCE.deserialize(jsonElement, context);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerMaterialForms() {
/*  87 */     if (registered) {
/*     */       return;
/*     */     }
/*  90 */     registered = true;
/*  91 */     for (IForm form : FORMS) {
/*  92 */       IGasFormSettings settings = (IGasFormSettings)form.getSettings();
/*  93 */       for (IMaterial material : form.getMaterials()) {
/*  94 */         IMaterialFormGas materialFormGas = settings.getGasCreator().create(form, material, settings);
/*  95 */         GASES.put(form, material, materialFormGas);
/*  96 */         Gas gas = materialFormGas.toGas();
/*  97 */         GasRegistry.register(gas);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Collection<IMaterialFormGas> getGases() {
/* 103 */     return GASES.values();
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\compat\mekanism\gases\GasFormType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */