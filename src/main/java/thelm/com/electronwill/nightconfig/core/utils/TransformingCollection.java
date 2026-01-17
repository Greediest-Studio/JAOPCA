/*     */ package thelm.com.electronwill.nightconfig.core.utils;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Spliterator;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Stream;
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
/*     */ public class TransformingCollection<InternalV, ExternalV>
/*     */   implements Collection<ExternalV>
/*     */ {
/*     */   protected final Function<? super InternalV, ? extends ExternalV> readTransformation;
/*     */   protected final Function<? super ExternalV, ? extends InternalV> writeTransformation;
/*     */   protected final Function<Object, Object> searchTransformation;
/*     */   protected final Collection<InternalV> internalCollection;
/*     */   
/*     */   public TransformingCollection(Collection<InternalV> internalCollection, Function<? super InternalV, ? extends ExternalV> readTransformation, Function<? super ExternalV, ? extends InternalV> writeTransformation, Function<Object, Object> searchTransformation) {
/*  32 */     this.internalCollection = internalCollection;
/*  33 */     this.readTransformation = readTransformation;
/*  34 */     this.writeTransformation = writeTransformation;
/*  35 */     this.searchTransformation = searchTransformation;
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/*  40 */     return this.internalCollection.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  45 */     return this.internalCollection.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(Object o) {
/*  50 */     return this.internalCollection.contains(this.searchTransformation.apply(o));
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<ExternalV> iterator() {
/*  55 */     return new TransformingIterator<>(this.internalCollection.iterator(), this.readTransformation);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] toArray() {
/*  60 */     Object[] array = this.internalCollection.toArray();
/*  61 */     for (int i = 0; i < array.length; i++) {
/*  62 */       array[i] = this.readTransformation.apply((InternalV)array[i]);
/*     */     }
/*  64 */     return array;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T[] toArray(T[] a) {
/*  69 */     T[] array = this.internalCollection.toArray(a);
/*  70 */     for (int i = 0; i < array.length; i++) {
/*  71 */       array[i] = (T)this.readTransformation.apply((InternalV)array[i]);
/*     */     }
/*  73 */     return array;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(ExternalV value) {
/*  78 */     return this.internalCollection.add(this.writeTransformation.apply(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(Object o) {
/*  83 */     return this.internalCollection.remove(this.searchTransformation.apply(o));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/*  88 */     return this.internalCollection.containsAll(new TransformingCollection((Collection)c, (Function)this.searchTransformation, o -> o, this.searchTransformation));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addAll(Collection<? extends ExternalV> c) {
/*  94 */     return this.internalCollection.addAll(new TransformingCollection((Collection)c, this.writeTransformation, this.readTransformation, this.searchTransformation));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeAll(Collection<?> c) {
/* 101 */     return this.internalCollection.removeAll(new TransformingCollection((Collection)c, (Function)this.searchTransformation, o -> o, this.searchTransformation));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeIf(Predicate<? super ExternalV> filter) {
/* 107 */     return this.internalCollection.removeIf(internalV -> filter.test(this.readTransformation.apply((InternalV)internalV)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean retainAll(Collection<?> c) {
/* 113 */     return this.internalCollection.retainAll(new TransformingCollection((Collection)c, (Function)this.searchTransformation, o -> o, this.searchTransformation));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 119 */     this.internalCollection.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public Spliterator<ExternalV> spliterator() {
/* 124 */     return new TransformingSpliterator<>(this.internalCollection.spliterator(), this.readTransformation, this.writeTransformation);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Stream<ExternalV> stream() {
/* 130 */     return this.internalCollection.stream().map(this.readTransformation);
/*     */   }
/*     */ 
/*     */   
/*     */   public Stream<ExternalV> parallelStream() {
/* 135 */     return this.internalCollection.parallelStream().map(this.readTransformation);
/*     */   }
/*     */ 
/*     */   
/*     */   public void forEach(Consumer<? super ExternalV> action) {
/* 140 */     this.internalCollection.forEach(internalV -> action.accept(this.readTransformation.apply((InternalV)internalV)));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 145 */     return this.internalCollection.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 150 */     return this.internalCollection.equals(obj);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 155 */     return this.internalCollection.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\cor\\utils\TransformingCollection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */