/*     */ package thelm.com.electronwill.nightconfig.core.utils;
/*     */ 
/*     */ import java.util.AbstractMap;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.function.Function;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TransformingMap<K, InternalV, ExternalV>
/*     */   extends AbstractMap<K, ExternalV>
/*     */ {
/*     */   private final Function<? super InternalV, ? extends ExternalV> readTransformation;
/*     */   private final Function<? super ExternalV, ? extends InternalV> writeTransformation;
/*     */   private final Function<Object, Object> searchTransformation;
/*     */   private final Map<K, InternalV> internalMap;
/*     */   
/*     */   public TransformingMap(Map<K, InternalV> map, Function<? super InternalV, ? extends ExternalV> readTransformation, Function<? super ExternalV, ? extends InternalV> writeTransformation, Function<Object, Object> searchTransformation) {
/*  53 */     this.internalMap = map;
/*  54 */     this.readTransformation = readTransformation;
/*  55 */     this.writeTransformation = writeTransformation;
/*  56 */     this.searchTransformation = searchTransformation;
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/*  61 */     return this.internalMap.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  66 */     return this.internalMap.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsKey(Object key) {
/*  71 */     return this.internalMap.containsKey(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsValue(Object value) {
/*  76 */     return this.internalMap.containsValue(this.searchTransformation.apply(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public ExternalV get(Object key) {
/*  81 */     return this.readTransformation.apply(this.internalMap.get(key));
/*     */   }
/*     */ 
/*     */   
/*     */   public ExternalV put(K key, ExternalV value) {
/*  86 */     return this.readTransformation.apply(this.internalMap.put(key, this.writeTransformation.apply(value)));
/*     */   }
/*     */ 
/*     */   
/*     */   public ExternalV remove(Object key) {
/*  91 */     return this.readTransformation.apply(this.internalMap.remove(key));
/*     */   }
/*     */ 
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends ExternalV> m) {
/*  96 */     this.internalMap.putAll(new TransformingMap((Map)m, this.writeTransformation, o -> o, o -> o));
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 101 */     this.internalMap.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<K> keySet() {
/* 106 */     return this.internalMap.keySet();
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<ExternalV> values() {
/* 111 */     return new TransformingCollection<>(this.internalMap.values(), this.readTransformation, this.writeTransformation, this.searchTransformation);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Map.Entry<K, ExternalV>> entrySet() {
/* 117 */     Function<Map.Entry<K, InternalV>, Map.Entry<K, ExternalV>> internalToExternal = internalEntry -> new TransformingMapEntry<>(internalEntry, this.readTransformation, this.writeTransformation);
/*     */ 
/*     */     
/* 120 */     Function<Map.Entry<K, ExternalV>, Map.Entry<K, InternalV>> externalToInternal = externalEntry -> new TransformingMapEntry<>(externalEntry, this.writeTransformation, this.readTransformation);
/*     */ 
/*     */     
/* 123 */     Function<Object, Object> searchTranformation = o -> {
/*     */         if (o instanceof Map.Entry) {
/*     */           Map.Entry<K, InternalV> entry = (Map.Entry<K, InternalV>)o;
/*     */           return new TransformingMapEntry<>(entry, this.readTransformation, this.writeTransformation);
/*     */         } 
/*     */         return o;
/*     */       };
/* 130 */     return new TransformingSet<>(this.internalMap.entrySet(), internalToExternal, externalToInternal, searchTranformation);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ExternalV getOrDefault(Object key, ExternalV defaultValue) {
/* 136 */     InternalV result = this.internalMap.get(key);
/* 137 */     return (result == defaultValue) ? defaultValue : this.readTransformation.apply(result);
/*     */   }
/*     */ 
/*     */   
/*     */   public void forEach(BiConsumer<? super K, ? super ExternalV> action) {
/* 142 */     this.internalMap.forEach((k, o) -> action.accept(k, this.readTransformation.apply((InternalV)o)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void replaceAll(BiFunction<? super K, ? super ExternalV, ? extends ExternalV> function) {
/* 147 */     this.internalMap.replaceAll(transform(function));
/*     */   }
/*     */ 
/*     */   
/*     */   public ExternalV putIfAbsent(K key, ExternalV value) {
/* 152 */     return this.readTransformation.apply(this.internalMap
/* 153 */         .putIfAbsent(key, this.writeTransformation.apply(value)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(Object key, Object value) {
/* 158 */     return this.internalMap.remove(key, this.searchTransformation.apply(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean replace(K key, ExternalV oldValue, ExternalV newValue) {
/* 163 */     return this.internalMap.replace(key, this.writeTransformation.apply(oldValue), this.writeTransformation
/* 164 */         .apply(newValue));
/*     */   }
/*     */ 
/*     */   
/*     */   public ExternalV replace(K key, ExternalV value) {
/* 169 */     return this.readTransformation.apply(this.internalMap.replace(key, this.writeTransformation.apply(value)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ExternalV computeIfAbsent(K key, Function<? super K, ? extends ExternalV> mappingFunction) {
/* 175 */     Function<K, InternalV> function = k -> this.writeTransformation.apply(mappingFunction.apply(k));
/* 176 */     return this.readTransformation.apply(this.internalMap.computeIfAbsent(key, function));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ExternalV computeIfPresent(K key, BiFunction<? super K, ? super ExternalV, ? extends ExternalV> remappingFunction) {
/* 182 */     return this.readTransformation.apply(this.internalMap
/* 183 */         .computeIfPresent(key, transform(remappingFunction)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ExternalV compute(K key, BiFunction<? super K, ? super ExternalV, ? extends ExternalV> remappingFunction) {
/* 189 */     return this.readTransformation.apply(this.internalMap.compute(key, transform(remappingFunction)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ExternalV merge(K key, ExternalV value, BiFunction<? super ExternalV, ? super ExternalV, ? extends ExternalV> remappingFunction) {
/* 195 */     return this.readTransformation.apply(this.internalMap.merge(key, this.writeTransformation.apply(value), 
/* 196 */           transform2(remappingFunction)));
/*     */   }
/*     */ 
/*     */   
/*     */   private BiFunction<K, InternalV, InternalV> transform(BiFunction<? super K, ? super ExternalV, ? extends ExternalV> remappingFunction) {
/* 201 */     return (k, internalV) -> this.writeTransformation.apply((ExternalV)remappingFunction.apply(k, this.readTransformation.apply((InternalV)internalV)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private BiFunction<InternalV, InternalV, InternalV> transform2(BiFunction<? super ExternalV, ? super ExternalV, ? extends ExternalV> remappingFunction) {
/* 207 */     return (internalV1, internalV2) -> this.writeTransformation.apply((ExternalV)remappingFunction.apply(this.readTransformation.apply((InternalV)internalV1), this.readTransformation.apply((InternalV)internalV2)));
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\cor\\utils\TransformingMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */