/*    */ package thelm.com.electronwill.nightconfig.toml;
/*    */ 
/*    */ import java.time.ZoneOffset;
/*    */ import java.time.temporal.ChronoField;
/*    */ import java.time.temporal.Temporal;
/*    */ import thelm.com.electronwill.nightconfig.core.io.CharacterOutput;
/*    */ 
/*    */ 
/*    */ 
/*    */ final class TemporalWriter
/*    */ {
/*    */   static void write(Temporal temporal, CharacterOutput output) {
/* 13 */     if (temporal.isSupported(ChronoField.YEAR)) {
/* 14 */       writeDate(temporal, output);
/* 15 */       if (temporal.isSupported(ChronoField.HOUR_OF_DAY)) {
/* 16 */         output.write('T');
/* 17 */         writeHour(temporal, output);
/* 18 */         if (temporal.isSupported(ChronoField.OFFSET_SECONDS)) {
/* 19 */           int offsetSeconds = temporal.get(ChronoField.OFFSET_SECONDS);
/* 20 */           ZoneOffset offset = ZoneOffset.ofTotalSeconds(offsetSeconds);
/* 21 */           output.write(offset.getId());
/*    */         } 
/*    */       } 
/* 24 */     } else if (temporal.isSupported(ChronoField.HOUR_OF_DAY)) {
/* 25 */       writeHour(temporal, output);
/*    */     } 
/*    */   }
/*    */   
/*    */   private static void writeDate(Temporal temporal, CharacterOutput output) {
/* 30 */     int year = temporal.get(ChronoField.YEAR);
/* 31 */     int month = temporal.get(ChronoField.MONTH_OF_YEAR);
/* 32 */     int day = temporal.get(ChronoField.DAY_OF_MONTH);
/* 33 */     writePadded(year, 4, output);
/* 34 */     output.write('-');
/* 35 */     writePadded(month, 2, output);
/* 36 */     output.write('-');
/* 37 */     writePadded(day, 2, output);
/*    */   }
/*    */   
/*    */   private static void writeHour(Temporal temporal, CharacterOutput output) {
/* 41 */     int hours = temporal.get(ChronoField.HOUR_OF_DAY);
/* 42 */     int minutes = temporal.get(ChronoField.MINUTE_OF_HOUR);
/* 43 */     int seconds = temporal.get(ChronoField.SECOND_OF_MINUTE);
/* 44 */     writePadded(hours, 2, output);
/* 45 */     output.write(':');
/* 46 */     writePadded(minutes, 2, output);
/* 47 */     output.write(':');
/* 48 */     writePadded(seconds, 2, output);
/* 49 */     if (temporal.isSupported(ChronoField.NANO_OF_SECOND)) {
/* 50 */       int nanos = temporal.get(ChronoField.NANO_OF_SECOND);
/* 51 */       if (nanos != 0) {
/* 52 */         output.write('.');
/* 53 */         writePaddedAndTrimmed(nanos, 9, output);
/*    */       } 
/* 55 */     } else if (temporal.isSupported(ChronoField.MILLI_OF_SECOND)) {
/* 56 */       int millis = temporal.get(ChronoField.MILLI_OF_SECOND);
/* 57 */       if (millis != 0) {
/* 58 */         output.write('.');
/* 59 */         writePaddedAndTrimmed(millis, 6, output);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private static void writePadded(int value, int numberOfDigits, CharacterOutput output) {
/* 65 */     String str = Integer.toString(value);
/* 66 */     for (int i = str.length(); i < numberOfDigits; i++) {
/* 67 */       output.write('0');
/*    */     }
/* 69 */     output.write(str);
/*    */   }
/*    */   
/*    */   private static void writePaddedAndTrimmed(int value, int numberOfDigits, CharacterOutput output) {
/* 73 */     String str = Integer.toString(value);
/* 74 */     int length = str.length(); int i;
/* 75 */     for (i = length; i < numberOfDigits; i++) {
/* 76 */       output.write('0');
/*    */     }
/* 78 */     for (i = length - 1; i >= 1; i--) {
/* 79 */       if (str.charAt(i) == '0') {
/* 80 */         length--;
/*    */       }
/*    */     } 
/* 83 */     output.write(str, 0, length);
/*    */   }
/*    */ }


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\toml\TemporalWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */