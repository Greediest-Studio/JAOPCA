package thelm.jaopca.api.oredict;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface JAOPCAOredictModule {
  String[] modDependencies() default {};
  
  String[] classDependencies() default {};
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\oredict\JAOPCAOredictModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */