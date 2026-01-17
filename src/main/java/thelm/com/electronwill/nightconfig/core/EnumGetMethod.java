/*     */ package thelm.com.electronwill.nightconfig.core;
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
/*     */ public enum EnumGetMethod
/*     */ {
/*  18 */   NAME,
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
/*  29 */   NAME_IGNORECASE,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   ORDINAL_OR_NAME,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   ORDINAL_OR_NAME_IGNORECASE;
/*     */   
/*     */   public boolean isCaseSensitive() {
/*  49 */     return (this == NAME || this == ORDINAL_OR_NAME);
/*     */   }
/*     */   
/*     */   public boolean isOrdinalOk() {
/*  53 */     return (this == ORDINAL_OR_NAME || this == ORDINAL_OR_NAME_IGNORECASE);
/*     */   }
/*     */   
/*     */   public <T extends Enum<T>> T get(Object value, Class<T> enumType) {
/*  57 */     if (value == null || value == NullObject.NULL_OBJECT) {
/*  58 */       return null;
/*     */     }
/*  60 */     Class<?> cls = value.getClass();
/*  61 */     if (enumType.isAssignableFrom(cls))
/*  62 */       return (T)value; 
/*  63 */     if (cls == String.class) {
/*  64 */       String str1 = (String)value;
/*  65 */       if (isCaseSensitive()) {
/*  66 */         return Enum.valueOf(enumType, str1);
/*     */       }
/*  68 */       for (Enum enum_ : (Enum[])enumType.getEnumConstants()) {
/*  69 */         if (enum_.name().equalsIgnoreCase(str1)) {
/*  70 */           return (T)enum_;
/*     */         }
/*     */       } 
/*  73 */       String enumName = enumType.getCanonicalName();
/*  74 */       throw new IllegalArgumentException("No enum constant " + enumName + "." + str1);
/*     */     } 
/*  76 */     if (cls == Integer.class) {
/*  77 */       if (isOrdinalOk()) {
/*  78 */         return (T)((Enum[])enumType.getEnumConstants())[((Integer)value).intValue()];
/*     */       }
/*  80 */       throw new ClassCastException("Cannot convert an Integer to an Enum: disallowed by EnumGetMethod." + this);
/*     */     } 
/*     */     
/*  83 */     String name = cls.getCanonicalName();
/*  84 */     throw new ClassCastException("Cannot convert a value of type " + name + " to an Enum");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Enum<T>> boolean validate(Object value, Class<T> enumType) {
/*  90 */     if (value == null || value == NullObject.NULL_OBJECT) {
/*  91 */       return true;
/*     */     }
/*  93 */     Class<?> cls = value.getClass();
/*  94 */     if (enumType.isAssignableFrom(cls))
/*  95 */       return true; 
/*  96 */     if (cls == String.class) {
/*  97 */       String name = (String)value;
/*  98 */       if (isCaseSensitive()) {
/*  99 */         for (Enum enum_ : (Enum[])enumType.getEnumConstants()) {
/* 100 */           if (enum_.name().equals(name)) return true; 
/*     */         } 
/*     */       } else {
/* 103 */         for (Enum enum_ : (Enum[])enumType.getEnumConstants()) {
/* 104 */           if (enum_.name().equalsIgnoreCase(name)) return true; 
/*     */         } 
/*     */       } 
/* 107 */     } else if (cls == Integer.class && isOrdinalOk()) {
/* 108 */       int idx = ((Integer)value).intValue();
/* 109 */       return (idx >= 0 && idx < ((Enum[])enumType.getEnumConstants()).length);
/*     */     } 
/* 111 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\EnumGetMethod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */