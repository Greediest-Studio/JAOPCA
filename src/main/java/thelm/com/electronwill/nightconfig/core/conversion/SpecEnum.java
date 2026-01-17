package thelm.com.electronwill.nightconfig.core.conversion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import thelm.com.electronwill.nightconfig.core.EnumGetMethod;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface SpecEnum {
  EnumGetMethod method();
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\conversion\SpecEnum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */