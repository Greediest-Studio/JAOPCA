/*     */ package thelm.com.electronwill.nightconfig.core.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CharsWrapper
/*     */   implements CharSequence, Cloneable, Iterable<Character>
/*     */ {
/*     */   final char[] chars;
/*     */   final int offset;
/*     */   final int limit;
/*     */   
/*     */   public CharsWrapper(char... chars) {
/*  26 */     this(chars, 0, chars.length);
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
/*     */   public CharsWrapper(char[] chars, int offset, int limit) {
/*  38 */     if (limit < offset) {
/*  39 */       throw new IllegalArgumentException("limit must be bigger than offset");
/*     */     }
/*  41 */     this.chars = Objects.<char[]>requireNonNull(chars, "chars must not be null");
/*  42 */     this.offset = offset;
/*  43 */     this.limit = limit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharsWrapper(String str) {
/*  53 */     this(str, 0, str.length());
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
/*     */   public CharsWrapper(String str, int begin, int end) {
/*  65 */     this.offset = 0;
/*  66 */     this.limit = end - begin;
/*  67 */     this.chars = new char[this.limit];
/*  68 */     str.getChars(begin, end, this.chars, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharsWrapper(CharSequence csq) {
/*  78 */     this(csq, 0, csq.length());
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
/*     */   public CharsWrapper(CharSequence csq, int begin, int end) {
/*  90 */     this.offset = 0;
/*  91 */     this.limit = end - begin;
/*  92 */     this.chars = new char[this.limit];
/*  93 */     for (int i = begin; i < end; i++) {
/*  94 */       this.chars[i - begin] = csq.charAt(i);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 104 */     return (this.limit == this.offset);
/*     */   }
/*     */ 
/*     */   
/*     */   public int length() {
/* 109 */     return this.limit - this.offset;
/*     */   }
/*     */ 
/*     */   
/*     */   public char charAt(int index) {
/* 114 */     return this.chars[this.offset + index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char get(int index) {
/* 122 */     return this.chars[this.offset + index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(int index, char ch) {
/* 132 */     this.chars[this.offset + index] = ch;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void replaceAll(char ch, char replacement) {
/* 142 */     for (int i = this.offset; i < this.limit; i++) {
/* 143 */       if (this.chars[i] == ch) {
/* 144 */         this.chars[i] = replacement;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(char c) {
/* 156 */     return (indexOf(c) != -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int indexOf(char c) {
/* 167 */     for (int i = this.offset; i < this.limit; i++) {
/* 168 */       if (this.chars[i] == c) {
/* 169 */         return i - this.offset;
/*     */       }
/*     */     } 
/* 172 */     return -1;
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
/*     */   public int indexOfFirst(char... ch) {
/* 184 */     for (int i = this.offset; i < this.limit; i++) {
/* 185 */       if (Utils.arrayContains(ch, this.chars[i])) {
/* 186 */         return i - this.offset;
/*     */       }
/*     */     } 
/* 189 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 194 */     if (obj == this) return true; 
/* 195 */     if (!(obj instanceof CharsWrapper)) return false;
/*     */     
/* 197 */     CharsWrapper other = (CharsWrapper)obj;
/* 198 */     int l = other.length();
/* 199 */     if (length() != l) {
/* 200 */       return false;
/*     */     }
/* 202 */     for (int i = 0; i < l; i++) {
/* 203 */       char c = this.chars[this.offset + i];
/* 204 */       char co = other.chars[other.offset + i];
/* 205 */       if (c != co) return false; 
/*     */     } 
/* 207 */     return true;
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
/*     */   public boolean equalsIgnoreCase(CharSequence cs) {
/* 220 */     if (cs == this) return true; 
/* 221 */     if (cs == null || cs.length() != length()) return false;
/*     */     
/* 223 */     for (int i = 0; i < this.limit; i++) {
/* 224 */       char u1 = Character.toUpperCase(this.chars[this.offset + i]);
/* 225 */       char u2 = Character.toUpperCase(cs.charAt(i));
/* 226 */       if (u1 != u2) return false; 
/*     */     } 
/* 228 */     return true;
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
/*     */   public boolean contentEquals(CharSequence cs) {
/* 240 */     int l = length();
/* 241 */     if (cs == null || cs.length() != l) {
/* 242 */       return false;
/*     */     }
/* 244 */     for (int i = 0; i < l; i++) {
/* 245 */       if (this.chars[this.offset + i] != cs.charAt(i)) {
/* 246 */         return false;
/*     */       }
/*     */     } 
/* 249 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contentEquals(char[] array) {
/* 259 */     int l = length();
/* 260 */     if (array == null || array.length != l) {
/* 261 */       return false;
/*     */     }
/* 263 */     for (int i = 0; i < l; i++) {
/* 264 */       if (this.chars[this.offset + i] != array[i]) {
/* 265 */         return false;
/*     */       }
/*     */     } 
/* 268 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean startsWith(CharSequence cs) {
/* 278 */     if (cs == null) {
/* 279 */       return false;
/*     */     }
/* 281 */     int l = cs.length();
/* 282 */     if (l > length()) {
/* 283 */       return false;
/*     */     }
/* 285 */     for (int i = 0; i < l; i++) {
/* 286 */       if (this.chars[this.offset + i] != cs.charAt(i)) {
/* 287 */         return false;
/*     */       }
/*     */     } 
/* 290 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharsWrapper subSequence(int start, int end) {
/* 301 */     return new CharsWrapper(Arrays.copyOfRange(this.chars, start + this.offset, end + this.offset));
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
/*     */   public CharsWrapper subView(int start, int end) {
/* 313 */     return new CharsWrapper(this.chars, start + this.offset, end + this.offset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharsWrapper subView(int start) {
/* 324 */     return new CharsWrapper(this.chars, start + this.offset, this.limit);
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
/*     */   public CharsWrapper trimmedView() {
/* 337 */     int offset = this.offset, limit = this.limit;
/* 338 */     while (offset < limit && this.chars[offset] <= ' ') {
/* 339 */       offset++;
/*     */     }
/* 341 */     while (limit > offset && this.chars[limit - 1] <= ' ') {
/* 342 */       limit--;
/*     */     }
/* 344 */     return new CharsWrapper(this.chars, offset, limit);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 349 */     return new String(this.chars, this.offset, length());
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
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 366 */     int h = 0;
/* 367 */     for (int i = this.offset; i < this.limit; i++) {
/* 368 */       h = 31 * h + this.chars[i];
/*     */     }
/* 370 */     return h;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharsWrapper clone() {
/* 381 */     return new CharsWrapper(Arrays.copyOf(this.chars, this.chars.length));
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<Character> iterator() {
/* 386 */     return new Iterator<Character>() {
/* 387 */         private int index = CharsWrapper.this.offset;
/*     */ 
/*     */         
/*     */         public boolean hasNext() {
/* 391 */           return (this.index < CharsWrapper.this.limit);
/*     */         }
/*     */ 
/*     */         
/*     */         public Character next() {
/* 396 */           if (this.index >= CharsWrapper.this.limit) {
/* 397 */             throw new NoSuchElementException("Index beyond limit: " + this.index);
/*     */           }
/* 399 */           return Character.valueOf(CharsWrapper.this.chars[this.index++]);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static final class Builder
/*     */     extends Writer
/*     */     implements CharacterOutput
/*     */   {
/* 408 */     private static final char[] NULL = new char[] { 'n', 'u', 'l', 'l' };
/*     */     private char[] data;
/* 410 */     private int cursor = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder(int initialCapacity) {
/* 419 */       this.data = new char[Math.min(2, initialCapacity)];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void ensureCapacity(int capacity) {
/* 428 */       if (this.data.length < capacity) {
/* 429 */         int newCapacity = Math.max(capacity, this.data.length * 2);
/* 430 */         this.data = Arrays.copyOf(this.data, newCapacity);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder append(char c) {
/* 436 */       write(c);
/* 437 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder append(CharSequence csq) {
/* 442 */       if (csq == null)
/* 443 */         return append(NULL); 
/* 444 */       if (csq instanceof String) {
/* 445 */         return append((String)csq);
/*     */       }
/* 447 */       return append(csq, 0, csq.length());
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder append(CharSequence csq, int start, int end) {
/* 452 */       if (csq == null)
/* 453 */         return append(NULL, start, end); 
/* 454 */       if (csq instanceof String) {
/* 455 */         return append((String)csq, start, end);
/*     */       }
/* 457 */       int length = end - start;
/* 458 */       int newCursor = this.cursor + length;
/* 459 */       ensureCapacity(newCursor);
/* 460 */       for (int i = start; i < end; i++) {
/* 461 */         this.data[this.cursor + i] = csq.charAt(i);
/*     */       }
/* 463 */       this.cursor = newCursor;
/* 464 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder append(char... chars) {
/* 474 */       write(chars);
/* 475 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder append(char[] chars, int begin, int end) {
/* 487 */       int length = end - begin;
/* 488 */       write(chars, begin, length);
/* 489 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder append(String str) {
/* 499 */       write(str);
/* 500 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder append(String str, int begin, int end) {
/* 512 */       int length = end - begin;
/* 513 */       write(str, begin, length);
/* 514 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder append(CharsWrapper cw) {
/* 524 */       write(cw);
/* 525 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder append(Object o) {
/* 536 */       if (o == null) {
/* 537 */         return append(NULL);
/*     */       }
/* 539 */       return append(o.toString());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder append(Object... objects) {
/* 550 */       for (Object o : objects) {
/* 551 */         append(o);
/*     */       }
/* 553 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public void flush() {}
/*     */ 
/*     */     
/*     */     public void close() {}
/*     */ 
/*     */     
/*     */     public void write(int c) {
/* 564 */       write((char)c);
/*     */     }
/*     */ 
/*     */     
/*     */     public void write(char c) {
/* 569 */       int newCursor = this.cursor + 1;
/* 570 */       ensureCapacity(newCursor);
/* 571 */       this.data[this.cursor] = c;
/* 572 */       this.cursor = newCursor;
/*     */     }
/*     */ 
/*     */     
/*     */     public void write(char... cbuf) {
/* 577 */       super.write(cbuf);
/*     */     }
/*     */ 
/*     */     
/*     */     public void write(char[] chars, int offset, int length) {
/* 582 */       int newCursor = this.cursor + length;
/* 583 */       ensureCapacity(newCursor);
/* 584 */       System.arraycopy(chars, offset, this.data, this.cursor, length);
/* 585 */       this.cursor = newCursor;
/*     */     }
/*     */ 
/*     */     
/*     */     public void write(String str) {
/* 590 */       super.write(str);
/*     */     }
/*     */ 
/*     */     
/*     */     public void write(String s, int offset, int length) {
/* 595 */       int end = offset + length;
/* 596 */       int newCursor = this.cursor + length;
/* 597 */       ensureCapacity(newCursor);
/* 598 */       s.getChars(offset, end, this.data, this.cursor);
/* 599 */       this.cursor = newCursor;
/*     */     }
/*     */ 
/*     */     
/*     */     public void write(CharsWrapper cw) {
/* 604 */       super.write(cw);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int length() {
/* 613 */       return this.cursor;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public char[] getChars() {
/* 623 */       return this.data;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public char get(int index) {
/* 631 */       return this.data[index];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void set(int index, char ch) {
/* 641 */       if (index >= this.cursor) {
/* 642 */         throw new IndexOutOfBoundsException("Index must not be larger than the builder's length");
/*     */       }
/*     */       
/* 645 */       this.data[index] = ch;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void compact() {
/* 652 */       if (this.cursor != this.data.length) {
/* 653 */         this.data = Arrays.copyOf(this.data, this.cursor);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public CharsWrapper build() {
/* 664 */       return build(0);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public CharsWrapper build(int start) {
/* 675 */       return new CharsWrapper(this.data, start, this.cursor);
/*     */     }
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
/*     */     public CharsWrapper build(int start, int end) {
/* 688 */       if (end > this.cursor) {
/* 689 */         throw new IndexOutOfBoundsException("Specified end index is larger than the builder's length!");
/*     */       }
/*     */       
/* 692 */       return new CharsWrapper(this.data, start, end);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public CharsWrapper copyAndBuild() {
/* 701 */       return build(0);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public CharsWrapper copyAndBuild(int start) {
/* 711 */       return new CharsWrapper(Arrays.copyOfRange(this.data, start, this.cursor));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public CharsWrapper copyAndBuild(int start, int end) {
/* 723 */       if (end > this.cursor) {
/* 724 */         throw new IndexOutOfBoundsException("Specified end index is larger than the builder's length!");
/*     */       }
/*     */       
/* 727 */       return new CharsWrapper(Arrays.copyOfRange(this.data, start, end));
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 732 */       return toString(0);
/*     */     }
/*     */     
/*     */     public String toString(int start) {
/* 736 */       return new String(this.data, start, this.cursor - start);
/*     */     }
/*     */     
/*     */     public String toString(int start, int end) {
/* 740 */       if (end > this.cursor) {
/* 741 */         throw new IndexOutOfBoundsException("Specified end index is larger than the builder's length!");
/*     */       }
/*     */       
/* 744 */       return new String(this.data, start, end - start);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\io\CharsWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */