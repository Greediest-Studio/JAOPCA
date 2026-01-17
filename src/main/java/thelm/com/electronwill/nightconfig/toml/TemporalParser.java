/*    */ package thelm.com.electronwill.nightconfig.toml;
/*    */ 
/*    */ import java.time.LocalDate;
/*    */ import java.time.LocalDateTime;
/*    */ import java.time.LocalTime;
/*    */ import java.time.OffsetDateTime;
/*    */ import java.time.ZoneOffset;
/*    */ import java.time.temporal.Temporal;
/*    */ import thelm.com.electronwill.nightconfig.core.io.CharsWrapper;
/*    */ import thelm.com.electronwill.nightconfig.core.io.ParsingException;
/*    */ import thelm.com.electronwill.nightconfig.core.io.Utils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class TemporalParser
/*    */ {
/* 22 */   private static final char[] ALLOWED_DT_SEPARATORS = new char[] { 'T', 't', ' ' };
/* 23 */   private static final char[] OFFSET_INDICATORS = new char[] { 'Z', '+', '-' };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static Temporal parse(CharsWrapper chars) {
/* 33 */     if (chars.get(2) == ':') {
/* 34 */       return parseTime(chars);
/*    */     }
/* 36 */     LocalDate date = parseDate(chars);
/* 37 */     if (chars.length() == 10) {
/* 38 */       return date;
/*    */     }
/* 40 */     char dateTimeSeparator = chars.get(10);
/* 41 */     if (!Utils.arrayContains(ALLOWED_DT_SEPARATORS, dateTimeSeparator)) {
/* 42 */       throw new ParsingException("Invalid separator between date and time: '" + dateTimeSeparator + "'.");
/*    */     }
/*    */     
/* 45 */     CharsWrapper afterDate = chars.subView(11);
/* 46 */     int offsetIndicatorIndex = afterDate.indexOfFirst(OFFSET_INDICATORS);
/* 47 */     if (offsetIndicatorIndex == -1) {
/* 48 */       LocalTime localTime = parseTime(afterDate);
/* 49 */       return LocalDateTime.of(date, localTime);
/*    */     } 
/* 51 */     LocalTime time = parseTime(afterDate.subView(0, offsetIndicatorIndex));
/* 52 */     ZoneOffset offset = ZoneOffset.of(afterDate.subView(offsetIndicatorIndex).trimmedView().toString());
/* 53 */     return OffsetDateTime.of(date, time, offset);
/*    */   }
/*    */   
/*    */   private static LocalDate parseDate(CharsWrapper chars) {
/* 57 */     CharsWrapper yearChars = chars.subView(0, 4);
/* 58 */     CharsWrapper monthChars = chars.subView(5, 7);
/* 59 */     CharsWrapper dayChars = chars.subView(8, 10);
/* 60 */     int year = Utils.parseInt(yearChars, 10);
/* 61 */     int month = Utils.parseInt(monthChars, 10);
/* 62 */     int day = Utils.parseInt(dayChars, 10);
/* 63 */     return LocalDate.of(year, month, day);
/*    */   }
/*    */   private static LocalTime parseTime(CharsWrapper chars) {
/*    */     int nanos;
/* 67 */     CharsWrapper hourChars = chars.subView(0, 2);
/* 68 */     CharsWrapper minuteChars = chars.subView(3, 5);
/* 69 */     CharsWrapper secondChars = chars.subView(6, 8);
/* 70 */     int hour = Utils.parseInt(hourChars, 10);
/* 71 */     int minutes = Utils.parseInt(minuteChars, 10);
/* 72 */     int seconds = Utils.parseInt(secondChars, 10);
/*    */ 
/*    */     
/* 75 */     if (chars.length() > 8) {
/* 76 */       CharsWrapper fractionChars = new CharsWrapper((CharSequence)chars.subView(9));
/* 77 */       if (fractionChars.length() > 9) {
/* 78 */         fractionChars = fractionChars.subView(0, 9);
/*    */       }
/* 80 */       int value = Utils.parseInt(fractionChars, 10);
/* 81 */       int coeff = (int)Math.pow(10.0D, (9 - fractionChars.length()));
/* 82 */       nanos = value * coeff;
/*    */     } else {
/* 84 */       nanos = 0;
/*    */     } 
/* 86 */     return LocalTime.of(hour, minutes, seconds, nanos);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\toml\TemporalParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */