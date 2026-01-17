package thelm.jaopca.api.entities;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import thelm.jaopca.api.forms.IFormSettings;
import thelm.jaopca.api.forms.IFormType;

public interface IEntityEntryFormType extends IFormType {
  IEntityEntryFormSettings getNewSettings();
  
  IEntityEntryFormSettings deserializeSettings(JsonElement paramJsonElement, JsonDeserializationContext paramJsonDeserializationContext);
}


/* Location:              C:\Users\yxy24\Desktop\JAOPCA-1.12.2-2.3.13.34.jar!\thelm\jaopca\api\entities\IEntityEntryFormType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */