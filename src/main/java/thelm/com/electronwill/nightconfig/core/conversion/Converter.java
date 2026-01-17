package thelm.com.electronwill.nightconfig.core.conversion;

public interface Converter<FieldType, ConfigValueType> {
  FieldType convertToField(ConfigValueType paramConfigValueType);
  
  ConfigValueType convertFromField(FieldType paramFieldType);
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\com\electronwill\nightconfig\core\conversion\Converter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */