/*     */ package thelm.com.electronwill.nightconfig.core.utils;
/*     */ 
/*     */ import java.util.NoSuchElementException;
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
/*     */ public final class IntDeque
/*     */ {
/*     */   private int[] data;
/*  21 */   private int head = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  26 */   private int tail = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int mask;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntDeque() {
/*  38 */     this(4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntDeque(int initialCapacity) {
/*  48 */     if (initialCapacity <= 0) {
/*  49 */       throw new IllegalArgumentException("The capacity must be positive and non-zero.");
/*     */     }
/*  51 */     if (!isPowerOfTwo(initialCapacity)) {
/*  52 */       initialCapacity = nextPowerOfTwo(initialCapacity);
/*     */     }
/*  54 */     this.data = new int[initialCapacity];
/*  55 */     this.mask = initialCapacity - 1;
/*     */   }
/*     */   
/*     */   private boolean isPowerOfTwo(int n) {
/*  59 */     return ((n & -n) == n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int nextPowerOfTwo(int n) {
/*  66 */     return Integer.highestOneBit(n) << 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/*  73 */     this.head = 0;
/*  74 */     this.tail = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  81 */     return (this.tail == this.head);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  88 */     if (this.tail >= this.head) {
/*  89 */       return this.tail - this.head;
/*     */     }
/*  91 */     return this.data.length - this.head + this.tail;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void compact() {
/*  98 */     if (this.tail == this.head) {
/*  99 */       this.data = new int[1];
/* 100 */       this.head = 0;
/* 101 */       this.tail = 0;
/* 102 */       this.mask = 0;
/*     */       return;
/*     */     } 
/* 105 */     int size = size();
/* 106 */     int newCapacity = size + 1;
/* 107 */     if (!isPowerOfTwo(newCapacity)) {
/* 108 */       newCapacity = nextPowerOfTwo(newCapacity);
/*     */     }
/* 110 */     int[] newData = new int[newCapacity];
/* 111 */     if (this.tail > this.head) {
/* 112 */       System.arraycopy(this.data, this.head, newData, 0, this.tail - this.head);
/*     */     } else {
/* 114 */       int lenght1 = this.data.length - this.head;
/*     */       
/* 116 */       System.arraycopy(this.data, this.head, newData, 0, lenght1);
/* 117 */       System.arraycopy(this.data, 0, newData, lenght1, this.tail);
/*     */     } 
/* 119 */     this.head = 0;
/* 120 */     this.tail = size;
/* 121 */     this.data = newData;
/* 122 */     this.mask = newData.length - 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void grow() {
/* 129 */     int newSize = this.data.length << 1;
/* 130 */     if (newSize < 0) {
/* 131 */       throw new IllegalStateException("IntDeque too big");
/*     */     }
/* 133 */     int[] newData = new int[newSize];
/* 134 */     int lenght1 = this.data.length - this.head;
/* 135 */     System.arraycopy(this.data, this.head, newData, 0, lenght1);
/* 136 */     System.arraycopy(this.data, 0, newData, lenght1, this.tail);
/* 137 */     this.head = 0;
/* 138 */     this.tail = this.data.length;
/* 139 */     this.data = newData;
/* 140 */     this.mask = newData.length - 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFirst(int element) {
/* 150 */     this.head = this.head - 1 & this.mask;
/* 151 */     this.data[this.head] = element;
/* 152 */     if (this.head == this.tail) {
/* 153 */       grow();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addLast(int element) {
/* 163 */     this.data[this.tail] = element;
/* 164 */     this.tail = this.tail + 1 & this.mask;
/* 165 */     if (this.tail == this.head) {
/* 166 */       grow();
/*     */     }
/*     */   }
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
/*     */   public int get(int index) {
/* 182 */     if (index >= size()) {
/* 183 */       throw new NoSuchElementException("No element at index " + index);
/*     */     }
/* 185 */     return this.data[this.head + index & this.mask];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFirst() {
/* 196 */     if (this.tail == this.head) {
/* 197 */       throw new NoSuchElementException("Empty deque");
/*     */     }
/* 199 */     return this.data[this.head];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLast() {
/* 210 */     if (this.tail == this.head) {
/* 211 */       throw new NoSuchElementException("Empty deque");
/*     */     }
/* 213 */     return this.data[this.tail - 1 & this.mask];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int removeFirst() {
/* 224 */     if (this.tail == this.head) {
/* 225 */       throw new NoSuchElementException("Empty deque");
/*     */     }
/* 227 */     int element = this.data[this.head];
/* 228 */     this.head = this.head + 1 & this.mask;
/* 229 */     return element;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int removeLast() {
/* 240 */     if (this.tail == this.head) {
/* 241 */       throw new NoSuchElementException("Empty deque");
/*     */     }
/* 243 */     this.tail = this.tail - 1 & this.mask;
/* 244 */     return this.data[this.tail];
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\cor\\utils\IntDeque.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */