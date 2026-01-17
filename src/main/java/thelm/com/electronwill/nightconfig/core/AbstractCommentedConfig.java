/*     */ package thelm.com.electronwill.nightconfig.core;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.function.Supplier;
/*     */ import thelm.com.electronwill.nightconfig.core.utils.TransformingSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractCommentedConfig
/*     */   extends AbstractConfig
/*     */   implements CommentedConfig
/*     */ {
/*     */   private final Map<String, String> commentMap;
/*     */   
/*     */   public AbstractCommentedConfig(boolean concurrent) {
/*  20 */     super(concurrent);
/*  21 */     this.commentMap = getDefaultCommentMap(concurrent);
/*     */   }
/*     */   
/*     */   public AbstractCommentedConfig(Supplier<Map<String, Object>> mapCreator) {
/*  25 */     super(mapCreator);
/*  26 */     this.commentMap = AbstractConfig.<String>getWildcardMapCreator(mapCreator).get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractCommentedConfig(Map<String, Object> valuesMap) {
/*  35 */     super(valuesMap);
/*  36 */     this.commentMap = getDefaultCommentMap(valuesMap instanceof java.util.concurrent.ConcurrentMap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractCommentedConfig(UnmodifiableConfig toCopy, boolean concurrent) {
/*  45 */     super(toCopy, concurrent);
/*  46 */     this.commentMap = getDefaultCommentMap(concurrent);
/*     */   }
/*     */   
/*     */   public AbstractCommentedConfig(UnmodifiableConfig toCopy, Supplier<Map<String, Object>> mapCreator) {
/*  50 */     super(toCopy, mapCreator);
/*  51 */     this.commentMap = AbstractConfig.<String>getWildcardMapCreator(mapCreator).get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractCommentedConfig(UnmodifiableCommentedConfig toCopy, boolean concurrent) {
/*  60 */     super(toCopy, concurrent);
/*  61 */     this.commentMap = getDefaultCommentMap(concurrent);
/*  62 */     this.commentMap.putAll(toCopy.commentMap());
/*     */   }
/*     */   
/*     */   public AbstractCommentedConfig(UnmodifiableCommentedConfig toCopy, Supplier<Map<String, Object>> mapCreator) {
/*  66 */     super(toCopy, mapCreator);
/*  67 */     this.commentMap = AbstractConfig.<String>getWildcardMapCreator(mapCreator).get();
/*     */   }
/*     */   
/*     */   protected static Map<String, String> getDefaultCommentMap(boolean concurrent) {
/*  71 */     return AbstractConfig.<String>getDefaultMapCreator(concurrent).get();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getComment(List<String> path) {
/*  76 */     int lastIndex = path.size() - 1;
/*  77 */     String lastKey = path.get(lastIndex);
/*  78 */     if (lastIndex == 0) {
/*  79 */       return this.commentMap.get(lastKey);
/*     */     }
/*  81 */     Object parent = getRaw(path.subList(0, lastIndex));
/*  82 */     if (parent instanceof UnmodifiableCommentedConfig) {
/*  83 */       List<String> lastPath = Collections.singletonList(lastKey);
/*  84 */       return ((UnmodifiableCommentedConfig)parent).getComment(lastPath);
/*     */     } 
/*  86 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String setComment(List<String> path, String comment) {
/*  91 */     int lastIndex = path.size() - 1;
/*  92 */     String lastKey = path.get(lastIndex);
/*  93 */     if (lastIndex == 0) {
/*  94 */       if (comment != null) {
/*  95 */         return this.commentMap.put(lastKey, comment);
/*     */       }
/*  97 */       return this.commentMap.remove(lastKey);
/*     */     } 
/*  99 */     List<String> parentPath = path.subList(0, lastIndex);
/* 100 */     Object parent = getRaw(parentPath);
/* 101 */     List<String> lastPath = Collections.singletonList(lastKey);
/* 102 */     if (parent instanceof CommentedConfig)
/* 103 */       return ((CommentedConfig)parent).setComment(lastPath, comment); 
/* 104 */     if (parent == null) {
/* 105 */       CommentedConfig commentedParent = createSubConfig();
/* 106 */       set(parentPath, commentedParent);
/* 107 */       return commentedParent.setComment(lastPath, comment);
/*     */     } 
/* 109 */     throw new IllegalArgumentException("Cannot set a comment to path " + path + " because the parent entry is of incompatible type " + parent
/*     */ 
/*     */         
/* 112 */         .getClass());
/*     */   }
/*     */ 
/*     */   
/*     */   public String removeComment(List<String> path) {
/* 117 */     int lastIndex = path.size() - 1;
/* 118 */     String lastKey = path.get(lastIndex);
/* 119 */     if (lastIndex == 0) {
/* 120 */       return this.commentMap.remove(lastKey);
/*     */     }
/* 122 */     Object parent = getRaw(path.subList(0, lastIndex));
/* 123 */     if (parent instanceof CommentedConfig) {
/* 124 */       List<String> lastPath = Collections.singletonList(lastKey);
/* 125 */       return ((CommentedConfig)parent).removeComment(lastPath);
/*     */     } 
/* 127 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsComment(List<String> path) {
/* 132 */     int lastIndex = path.size() - 1;
/* 133 */     String lastKey = path.get(lastIndex);
/* 134 */     if (lastIndex == 0) {
/* 135 */       return this.commentMap.containsKey(lastKey);
/*     */     }
/* 137 */     Object parent = getRaw(path.subList(0, lastIndex));
/* 138 */     if (parent instanceof CommentedConfig) {
/* 139 */       List<String> lastPath = Collections.singletonList(lastKey);
/* 140 */       return ((CommentedConfig)parent).containsComment(lastPath);
/*     */     } 
/* 142 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, String> commentMap() {
/* 147 */     return this.commentMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<? extends CommentedConfig.Entry> entrySet() {
/* 152 */     return (Set<? extends CommentedConfig.Entry>)new TransformingSet(this.map.entrySet(), x$0 -> new CommentedEntryWrapper(x$0), o -> null, o -> o);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class CommentedEntryWrapper
/*     */     extends AbstractConfig.EntryWrapper
/*     */     implements CommentedConfig.Entry
/*     */   {
/* 162 */     private List<String> path = null;
/*     */     
/*     */     public CommentedEntryWrapper(Map.Entry<String, Object> mapEntry) {
/* 165 */       super(mapEntry);
/*     */     }
/*     */     
/*     */     protected List<String> getPath() {
/* 169 */       if (this.path == null) {
/* 170 */         this.path = Collections.singletonList(getKey());
/*     */       }
/* 172 */       return this.path;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getComment() {
/* 177 */       return AbstractCommentedConfig.this.getComment(getPath());
/*     */     }
/*     */ 
/*     */     
/*     */     public String setComment(String comment) {
/* 182 */       return AbstractCommentedConfig.this.setComment(getPath(), comment);
/*     */     }
/*     */ 
/*     */     
/*     */     public String removeComment() {
/* 187 */       return AbstractCommentedConfig.this.removeComment(getPath());
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object obj) {
/* 192 */       if (obj == this) {
/* 193 */         return true;
/*     */       }
/* 195 */       if (obj instanceof CommentedEntryWrapper) {
/* 196 */         CommentedEntryWrapper other = (CommentedEntryWrapper)obj;
/* 197 */         return (Objects.equals(getKey(), other.getKey()) && 
/* 198 */           Objects.equals(getValue(), other.getValue()) && 
/* 199 */           Objects.equals(getComment(), other.getComment()));
/*     */       } 
/* 201 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 206 */       int result = 1;
/* 207 */       result = 31 * result + Objects.hashCode(getKey());
/* 208 */       result = 31 * result + Objects.hashCode(getValue());
/* 209 */       result = 31 * result + Objects.hashCode(getComment());
/* 210 */       return result;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 216 */     super.clear();
/* 217 */     clearComments();
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearComments() {
/* 222 */     this.commentMap.clear();
/*     */     
/* 224 */     for (Object o : this.map.values()) {
/* 225 */       if (o instanceof CommentedConfig)
/* 226 */         ((CommentedConfig)o).clearComments(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract AbstractCommentedConfig clone();
/*     */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\AbstractCommentedConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */