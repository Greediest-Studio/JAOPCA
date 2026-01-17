/*     */ package thelm.com.electronwill.nightconfig.core.utils;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.function.Function;
/*     */ 
/*     */ public final class ObservedMap<K, V>
/*     */   extends AbstractObserved
/*     */   implements Map<K, V>
/*     */ {
/*     */   private final Map<K, V> map;
/*     */   
/*     */   public ObservedMap(Map<K, V> map, Runnable callback) {
/*  16 */     super(callback);
/*  17 */     this.map = map;
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/*  22 */     return this.map.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  27 */     return this.map.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsKey(Object key) {
/*  32 */     return this.map.containsKey(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsValue(Object value) {
/*  37 */     return this.map.containsValue(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public V get(Object key) {
/*  42 */     return this.map.get(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public V put(K key, V value) {
/*  47 */     V result = this.map.put(key, value);
/*  48 */     this.callback.run();
/*  49 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public V remove(Object key) {
/*  54 */     V result = this.map.remove(key);
/*  55 */     this.callback.run();
/*  56 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> m) {
/*  61 */     this.map.putAll(m);
/*  62 */     this.callback.run();
/*     */   }
/*     */ 
/*     */   
/*     */   public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
/*  67 */     this.map.replaceAll(function);
/*  68 */     this.callback.run();
/*     */   }
/*     */ 
/*     */   
/*     */   public V putIfAbsent(K key, V value) {
/*  73 */     V result = this.map.putIfAbsent(key, value);
/*  74 */     if (result != value) this.callback.run(); 
/*  75 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(Object key, Object value) {
/*  80 */     boolean removed = this.map.remove(key, value);
/*  81 */     if (removed) this.callback.run(); 
/*  82 */     return removed;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean replace(K key, V oldValue, V newValue) {
/*  87 */     boolean replaced = this.map.replace(key, oldValue, newValue);
/*  88 */     if (replaced) this.callback.run(); 
/*  89 */     return replaced;
/*     */   }
/*     */ 
/*     */   
/*     */   public V replace(K key, V value) {
/*  94 */     V result = this.map.replace(key, value);
/*  95 */     if (result != value) this.callback.run(); 
/*  96 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
/* 101 */     V result = this.map.computeIfAbsent(key, mappingFunction);
/* 102 */     if (result != null) this.callback.run(); 
/* 103 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
/* 109 */     V result = this.map.computeIfPresent(key, remappingFunction);
/* 110 */     this.callback.run();
/* 111 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
/* 116 */     V result = this.map.compute(key, remappingFunction);
/* 117 */     this.callback.run();
/* 118 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
/* 124 */     V result = this.map.merge(key, value, remappingFunction);
/* 125 */     this.callback.run();
/* 126 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 131 */     this.map.clear();
/* 132 */     this.callback.run();
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<K> keySet() {
/* 137 */     return new ObservedSet<>(this.map.keySet(), this.callback);
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<V> values() {
/* 142 */     return this.map.values();
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/* 147 */     Function<Map.Entry<K, V>, ObservedEntry<K, V>> readT = e -> new ObservedEntry<>(e, this.callback);
/* 148 */     Function<ObservedEntry<K, V>, Map.Entry<K, V>> writeT = oe -> oe.entry;
/* 149 */     Function<Object, Object> searchT = o -> {
/*     */         if (o instanceof ObservedEntry) {
/*     */           ObservedEntry<?, ?> observedEntry = (ObservedEntry<?, ?>)o;
/*     */           
/*     */           return observedEntry.entry;
/*     */         } 
/*     */         return o;
/*     */       };
/* 157 */     TransformingSet<Map.Entry<K, V>, ObservedEntry<K, V>> tset = new TransformingSet<>(this.map.entrySet(), readT, writeT, searchT);
/* 158 */     return new ObservedSet<>(this.map.entrySet(), this.callback);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 163 */     return this.map.equals(obj);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 168 */     return this.map.hashCode();
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\cor\\utils\ObservedMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */