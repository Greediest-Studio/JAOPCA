/*     */ package thelm.com.electronwill.nightconfig.core.utils;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.function.Predicate;
/*     */ 
/*     */ public final class ObservedSet<K>
/*     */   extends AbstractObserved
/*     */   implements Set<K>
/*     */ {
/*     */   private final Set<K> set;
/*     */   
/*     */   public ObservedSet(Set<K> set, Runnable callback) {
/*  15 */     super(callback);
/*  16 */     this.set = set;
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/*  21 */     return this.set.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  26 */     return this.set.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(Object o) {
/*  31 */     return this.set.contains(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<K> iterator() {
/*  36 */     return this.set.iterator();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] toArray() {
/*  41 */     return this.set.toArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T[] toArray(T[] a) {
/*  46 */     return this.set.toArray(a);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(K k) {
/*  51 */     boolean result = this.set.add(k);
/*  52 */     this.callback.run();
/*  53 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(Object o) {
/*  58 */     boolean result = this.set.remove(o);
/*  59 */     this.callback.run();
/*  60 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/*  65 */     return this.set.containsAll(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addAll(Collection<? extends K> c) {
/*  70 */     boolean result = this.set.addAll(c);
/*  71 */     this.callback.run();
/*  72 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean retainAll(Collection<?> c) {
/*  77 */     boolean result = this.set.retainAll(c);
/*  78 */     this.callback.run();
/*  79 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeAll(Collection<?> c) {
/*  84 */     boolean result = this.set.removeAll(c);
/*  85 */     this.callback.run();
/*  86 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/*  91 */     this.set.clear();
/*  92 */     this.callback.run();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeIf(Predicate<? super K> filter) {
/*  97 */     boolean removed = this.set.removeIf(filter);
/*  98 */     if (removed) this.callback.run(); 
/*  99 */     return removed;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 104 */     return this.set.equals(obj);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 109 */     return this.set.hashCode();
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\cor\\utils\ObservedSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */