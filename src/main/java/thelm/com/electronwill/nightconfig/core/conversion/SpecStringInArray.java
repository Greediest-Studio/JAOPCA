package thelm.com.electronwill.nightconfig.core.conversion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface SpecStringInArray {
  String[] value();
  
  boolean ignoreCase() default false;
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\conversion\SpecStringInArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */