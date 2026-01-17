/*     */ package thelm.com.electronwill.nightconfig.core.utils;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
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
/*     */ public final class StringUtils
/*     */ {
/*     */   public static List<String> split(String str, char sep) {
/*  37 */     List<String> list = new ArrayList<>(8);
/*  38 */     split(str, sep, list);
/*  39 */     return list;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void split(String str, char sep, List<String> list) {
/*  63 */     int pos0 = 0;
/*  64 */     for (int i = 0; i < str.length(); i++) {
/*  65 */       if (str.charAt(i) == sep) {
/*  66 */         list.add(str.substring(pos0, i));
/*  67 */         pos0 = i + 1;
/*     */       } 
/*     */     } 
/*  70 */     list.add(str.substring(pos0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> splitLines(String str) {
/*  81 */     if (str == null || str.isEmpty()) {
/*  82 */       return Collections.emptyList();
/*     */     }
/*  84 */     List<String> list = new ArrayList<>(8);
/*  85 */     splitLines(str, list);
/*  86 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void splitLines(String str, List<String> list) {
/*  96 */     int pos0 = 0;
/*  97 */     for (int i = 0; i < str.length(); i++) {
/*  98 */       char ch = str.charAt(i);
/*  99 */       if (ch == '\n') {
/* 100 */         list.add(str.substring(pos0, i));
/* 101 */         pos0 = i + 1;
/* 102 */       } else if (ch == '\r') {
/* 103 */         list.add(str.substring(pos0, i));
/* 104 */         int next = i + 1;
/* 105 */         if (next < str.length() && str.charAt(next) == '\n') {
/* 106 */           i++;
/*     */         }
/* 108 */         pos0 = i + 1;
/*     */       } 
/*     */     } 
/* 111 */     String lastPart = str.substring(pos0);
/* 112 */     list.add(lastPart);
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\cor\\utils\StringUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */