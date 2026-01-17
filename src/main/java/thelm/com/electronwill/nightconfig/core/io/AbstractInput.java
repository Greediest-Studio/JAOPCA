/*     */ package thelm.com.electronwill.nightconfig.core.io;
/*     */ 
/*     */ import thelm.com.electronwill.nightconfig.core.utils.IntDeque;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractInput
/*     */   implements CharacterInput
/*     */ {
/*  14 */   protected final IntDeque deque = new IntDeque();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract int directRead();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract char directReadChar();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() {
/*  34 */     if (!this.deque.isEmpty()) {
/*  35 */       return this.deque.removeFirst();
/*     */     }
/*  37 */     return directRead();
/*     */   }
/*     */ 
/*     */   
/*     */   public char readChar() {
/*  42 */     if (!this.deque.isEmpty()) {
/*  43 */       int next = this.deque.removeFirst();
/*  44 */       if (next == -1) {
/*  45 */         throw ParsingException.notEnoughData();
/*     */       }
/*  47 */       return (char)next;
/*     */     } 
/*  49 */     return directReadChar();
/*     */   }
/*     */ 
/*     */   
/*     */   public int peek() {
/*  54 */     if (this.deque.isEmpty()) {
/*  55 */       int read = directRead();
/*  56 */       this.deque.addLast(read);
/*  57 */       return read;
/*     */     } 
/*  59 */     return this.deque.getFirst();
/*     */   }
/*     */ 
/*     */   
/*     */   public int peek(int n) {
/*  64 */     int diff = n - this.deque.size();
/*  65 */     if (diff >= 0) {
/*  66 */       for (int i = 0; i <= diff; i++) {
/*  67 */         int read = directRead();
/*  68 */         this.deque.addLast(read);
/*  69 */         if (read == -1) {
/*  70 */           return -1;
/*     */         }
/*     */       } 
/*     */     }
/*  74 */     return this.deque.get(n);
/*     */   }
/*     */ 
/*     */   
/*     */   public char peekChar() {
/*  79 */     int c = peek();
/*  80 */     if (c == -1) {
/*  81 */       throw ParsingException.notEnoughData();
/*     */     }
/*  83 */     return (char)c;
/*     */   }
/*     */ 
/*     */   
/*     */   public char peekChar(int n) {
/*  88 */     int c = peek(n);
/*  89 */     if (c == -1) {
/*  90 */       throw ParsingException.notEnoughData();
/*     */     }
/*  92 */     return (char)c;
/*     */   }
/*     */ 
/*     */   
/*     */   public void skipPeeks() {
/*  97 */     this.deque.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void pushBack(char c) {
/* 102 */     this.deque.addFirst(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public CharsWrapper readUntil(char[] stop) {
/* 107 */     CharsWrapper.Builder builder = new CharsWrapper.Builder(10);
/* 108 */     int c = read();
/* 109 */     while (c != -1 && !Utils.arrayContains(stop, (char)c)) {
/* 110 */       builder.append((char)c);
/* 111 */       c = read();
/*     */     } 
/* 113 */     this.deque.addFirst(c);
/* 114 */     return builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public CharsWrapper readCharsUntil(char[] stop) {
/* 119 */     CharsWrapper.Builder builder = new CharsWrapper.Builder(10);
/* 120 */     char c = readChar();
/* 121 */     while (!Utils.arrayContains(stop, c)) {
/* 122 */       builder.append(c);
/* 123 */       c = readChar();
/*     */     } 
/* 125 */     this.deque.addFirst(c);
/* 126 */     return builder.build();
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
/*     */   protected CharsWrapper consumeDeque(char[] array, int offset, boolean mustReadAll) {
/* 139 */     for (int i = 0; i < offset; i++) {
/* 140 */       int next = this.deque.removeFirst();
/* 141 */       if (next == -1) {
/* 142 */         if (mustReadAll) {
/* 143 */           throw ParsingException.notEnoughData();
/*     */         }
/* 145 */         return new CharsWrapper(array, 0, i);
/*     */       } 
/* 147 */       array[i] = (char)next;
/*     */     } 
/* 149 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\io\AbstractInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */