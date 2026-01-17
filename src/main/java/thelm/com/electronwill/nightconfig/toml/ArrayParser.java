/*    */ package thelm.com.electronwill.nightconfig.toml;
/*    */ 
/*    */ import java.util.List;
/*    */ import thelm.com.electronwill.nightconfig.core.io.CharacterInput;
/*    */ import thelm.com.electronwill.nightconfig.core.io.ParsingException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class ArrayParser
/*    */ {
/*    */   static List<?> parse(CharacterInput input, TomlParser parser) {
/* 16 */     List<Object> list = parser.createList();
/*    */     while (true) {
/* 18 */       char firstChar = Toml.readUsefulChar(input);
/* 19 */       if (firstChar == ']')
/* 20 */         return list; 
/* 21 */       if (firstChar == ',') {
/* 22 */         char nextChar = Toml.readUsefulChar(input);
/* 23 */         if (nextChar == ']') {
/* 24 */           return list;
/*    */         }
/* 26 */         throw new ParsingException("Unexpected character in array: '" + nextChar + "' - Expected end of array because of the leading comma.");
/*    */       } 
/*    */ 
/*    */ 
/*    */       
/* 31 */       Object value = ValueParser.parse(input, firstChar, parser);
/* 32 */       list.add(value);
/* 33 */       char after = Toml.readUsefulChar(input);
/* 34 */       if (after == ']') {
/* 35 */         return list;
/*    */       }
/* 37 */       if (after != ',')
/* 38 */         throw new ParsingException("Invalid separator '" + after + "' in array."); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\toml\ArrayParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */