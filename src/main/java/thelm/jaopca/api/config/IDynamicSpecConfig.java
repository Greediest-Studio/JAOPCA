package thelm.jaopca.api.config;

import java.util.Collection;
import java.util.List;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import thelm.com.electronwill.nightconfig.core.CommentedConfig;

public interface IDynamicSpecConfig extends CommentedConfig {
  String getDefinedString(String paramString1, String paramString2, String paramString3);
  
  String getDefinedString(List<String> paramList, String paramString1, String paramString2);
  
  String getDefinedString(String paramString1, String paramString2, Collection<String> paramCollection, String paramString3);
  
  String getDefinedString(List<String> paramList, String paramString1, Collection<String> paramCollection, String paramString2);
  
  String getDefinedString(String paramString1, String paramString2, Predicate<String> paramPredicate, String paramString3);
  
  String getDefinedString(List<String> paramList, String paramString1, Predicate<String> paramPredicate, String paramString2);
  
  List<String> getDefinedStringList(String paramString1, List<String> paramList, String paramString2);
  
  List<String> getDefinedStringList(List<String> paramList1, List<String> paramList2, String paramString);
  
  List<String> getDefinedStringList(String paramString1, List<String> paramList, Collection<String> paramCollection, String paramString2);
  
  List<String> getDefinedStringList(List<String> paramList1, List<String> paramList2, Collection<String> paramCollection, String paramString);
  
  List<String> getDefinedStringList(String paramString1, List<String> paramList, Predicate<String> paramPredicate, String paramString2);
  
  List<String> getDefinedStringList(List<String> paramList1, List<String> paramList2, Predicate<String> paramPredicate, String paramString);
  
  boolean getDefinedBoolean(String paramString1, boolean paramBoolean, String paramString2);
  
  boolean getDefinedBoolean(List<String> paramList, boolean paramBoolean, String paramString);
  
  Number getDefinedNumber(String paramString1, Number paramNumber, String paramString2);
  
  Number getDefinedNumber(List<String> paramList, Number paramNumber, String paramString);
  
  Number getDefinedNumber(String paramString1, Number paramNumber, Predicate<Number> paramPredicate, String paramString2);
  
  Number getDefinedNumber(List<String> paramList, Number paramNumber, Predicate<Number> paramPredicate, String paramString);
  
  int getDefinedInt(String paramString1, int paramInt, String paramString2);
  
  int getDefinedInt(List<String> paramList, int paramInt, String paramString);
  
  int getDefinedInt(String paramString1, int paramInt1, int paramInt2, int paramInt3, String paramString2);
  
  int getDefinedInt(List<String> paramList, int paramInt1, int paramInt2, int paramInt3, String paramString);
  
  int getDefinedInt(String paramString1, int paramInt, IntPredicate paramIntPredicate, String paramString2);
  
  int getDefinedInt(List<String> paramList, int paramInt, IntPredicate paramIntPredicate, String paramString);
  
  long getDefinedLong(String paramString1, long paramLong, String paramString2);
  
  long getDefinedLong(List<String> paramList, long paramLong, String paramString);
  
  long getDefinedLong(String paramString1, long paramLong1, long paramLong2, long paramLong3, String paramString2);
  
  long getDefinedLong(List<String> paramList, long paramLong1, long paramLong2, long paramLong3, String paramString);
  
  long getDefinedLong(String paramString1, long paramLong, LongPredicate paramLongPredicate, String paramString2);
  
  long getDefinedLong(List<String> paramList, long paramLong, LongPredicate paramLongPredicate, String paramString);
  
  float getDefinedFloat(String paramString1, float paramFloat, String paramString2);
  
  float getDefinedFloat(List<String> paramList, float paramFloat, String paramString);
  
  float getDefinedFloat(String paramString1, float paramFloat1, float paramFloat2, float paramFloat3, String paramString2);
  
  float getDefinedFloat(List<String> paramList, float paramFloat1, float paramFloat2, float paramFloat3, String paramString);
  
  float getDefinedFloat(String paramString1, float paramFloat, Predicate<Float> paramPredicate, String paramString2);
  
  float getDefinedFloat(List<String> paramList, float paramFloat, Predicate<Float> paramPredicate, String paramString);
  
  double getDefinedDouble(String paramString1, double paramDouble, String paramString2);
  
  double getDefinedDouble(List<String> paramList, double paramDouble, String paramString);
  
  double getDefinedDouble(String paramString1, double paramDouble1, double paramDouble2, double paramDouble3, String paramString2);
  
  double getDefinedDouble(List<String> paramList, double paramDouble1, double paramDouble2, double paramDouble3, String paramString);
  
  double getDefinedDouble(String paramString1, double paramDouble, DoublePredicate paramDoublePredicate, String paramString2);
  
  double getDefinedDouble(List<String> paramList, double paramDouble, DoublePredicate paramDoublePredicate, String paramString);
  
  byte getDefinedByte(String paramString1, byte paramByte, String paramString2);
  
  byte getDefinedByte(List<String> paramList, byte paramByte, String paramString);
  
  byte getDefinedByte(String paramString1, byte paramByte1, byte paramByte2, byte paramByte3, String paramString2);
  
  byte getDefinedByte(List<String> paramList, byte paramByte1, byte paramByte2, byte paramByte3, String paramString);
  
  byte getDefinedByte(String paramString1, byte paramByte, Predicate<Byte> paramPredicate, String paramString2);
  
  byte getDefinedByte(List<String> paramList, byte paramByte, Predicate<Byte> paramPredicate, String paramString);
  
  short getDefinedShort(String paramString1, short paramShort, String paramString2);
  
  short getDefinedShort(List<String> paramList, short paramShort, String paramString);
  
  short getDefinedShort(String paramString1, short paramShort1, short paramShort2, short paramShort3, String paramString2);
  
  short getDefinedShort(List<String> paramList, short paramShort1, short paramShort2, short paramShort3, String paramString);
  
  short getDefinedShort(String paramString1, short paramShort, Predicate<Short> paramPredicate, String paramString2);
  
  short getDefinedShort(List<String> paramList, short paramShort, Predicate<Short> paramPredicate, String paramString);
  
  char getDefinedChar(String paramString1, char paramChar, String paramString2);
  
  char getDefinedChar(List<String> paramList, char paramChar, String paramString);
  
  char getDefinedChar(String paramString1, char paramChar, Collection<Character> paramCollection, String paramString2);
  
  char getDefinedChar(List<String> paramList, char paramChar, Collection<Character> paramCollection, String paramString);
  
  char getDefinedChar(String paramString1, char paramChar, Predicate<Character> paramPredicate, String paramString2);
  
  char getDefinedChar(List<String> paramList, char paramChar, Predicate<Character> paramPredicate, String paramString);
  
  <T extends Enum<T>> T getDefinedEnum(List<String> paramList, Class<T> paramClass, T paramT, String paramString);
  
  <T extends Enum<T>> T getDefinedEnum(String paramString1, Class<T> paramClass, T paramT, String paramString2);
  
  <T extends Enum<T>> T getDefinedEnum(List<String> paramList, Class<T> paramClass, T paramT, Collection<T> paramCollection, String paramString);
  
  <T extends Enum<T>> T getDefinedEnum(String paramString1, Class<T> paramClass, T paramT, Collection<T> paramCollection, String paramString2);
  
  <T extends Enum<T>> T getDefinedEnum(List<String> paramList, Class<T> paramClass, T paramT, Predicate<T> paramPredicate, String paramString);
  
  <T extends Enum<T>> T getDefinedEnum(String paramString1, Class<T> paramClass, T paramT, Predicate<T> paramPredicate, String paramString2);
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\config\IDynamicSpecConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */