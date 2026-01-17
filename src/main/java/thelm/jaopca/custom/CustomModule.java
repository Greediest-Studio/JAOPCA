/*    */ package thelm.jaopca.custom;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.gson.Gson;
/*    */ import java.io.InputStreamReader;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.function.BiConsumer;
/*    */ import java.util.function.Function;
/*    */ import java.util.stream.Stream;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thelm.jaopca.api.config.IDynamicSpecConfig;
/*    */ import thelm.jaopca.api.forms.IForm;
/*    */ import thelm.jaopca.api.forms.IFormRequest;
/*    */ import thelm.jaopca.api.materials.IMaterial;
/*    */ import thelm.jaopca.api.modules.IModule;
/*    */ import thelm.jaopca.api.modules.IModuleData;
/*    */ import thelm.jaopca.api.modules.JAOPCAModule;
/*    */ 
/*    */ 
/*    */ @JAOPCAModule
/*    */ public class CustomModule
/*    */   implements IModule
/*    */ {
/* 31 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public static CustomModule instance;
/* 34 */   private Gson gson = new Gson();
/* 35 */   private final List<BiConsumer<IMaterial, IDynamicSpecConfig>> customConfigDefiners = new ArrayList<>();
/* 36 */   private final List<IFormRequest> formRequests = new ArrayList<>();
/*    */   
/*    */   public CustomModule() {
/* 39 */     if (instance == null) {
/* 40 */       instance = this;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 46 */     return "custom";
/*    */   }
/*    */   
/*    */   public void setGson(Gson gson) {
/* 50 */     this.gson = gson;
/*    */   }
/*    */   
/*    */   public void addCustomConfigDefiner(BiConsumer<IMaterial, IDynamicSpecConfig> customConfigDefiner) {
/* 54 */     this.customConfigDefiners.add(customConfigDefiner);
/*    */   }
/*    */   
/*    */   public void setCustomFormConfigFile(Path customFormConfigFile) {
/* 58 */     this.formRequests.clear();
/* 59 */     try (InputStreamReader reader = new InputStreamReader(Files.newInputStream(customFormConfigFile, new java.nio.file.OpenOption[0]), StandardCharsets.UTF_8)) {
/* 60 */       IFormRequest[] requests = (IFormRequest[])this.gson.fromJson(reader, IFormRequest[].class);
/* 61 */       if (requests != null) {
/* 62 */         Collections.addAll(this.formRequests, requests);
/*    */       }
/*    */     }
/* 65 */     catch (Exception e) {
/* 66 */       LOGGER.error("Unable to read custom json", e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List<IFormRequest> getFormRequests() {
/* 72 */     return this.formRequests;
/*    */   }
/*    */ 
/*    */   
/*    */   public void defineMaterialConfig(IModuleData moduleData, Map<IMaterial, IDynamicSpecConfig> configs) {
/* 77 */     for (BiConsumer<IMaterial, IDynamicSpecConfig> customConfigDefiner : this.customConfigDefiners) {
/* 78 */       for (IMaterial material : moduleData.getMaterials()) {
/* 79 */         customConfigDefiner.accept(material, configs.get(material));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, String> getLegacyRemaps() {
/* 86 */     return (Map<String, String>)this.formRequests.stream()
/* 87 */       .flatMap(r -> r.getForms().stream())
/* 88 */       .map(IForm::getName)
/* 89 */       .distinct()
/* 90 */       .collect(ImmutableMap.toImmutableMap(Function.identity(), Function.identity()));
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\custom\CustomModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */