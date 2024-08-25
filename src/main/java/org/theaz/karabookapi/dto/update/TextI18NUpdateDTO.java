package org.theaz.karabookapi.dto.update;

import lombok.Data;

import java.util.Date;

@Data
public class TextI18NUpdateDTO {
    public String textKey;
    public String textValue;
    public String locale;
    public Date modifiedDate = new Date();
}
