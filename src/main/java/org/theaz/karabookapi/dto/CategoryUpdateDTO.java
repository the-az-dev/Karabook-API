package org.theaz.karabookapi.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryUpdateDTO {
    public Long categoryId;
    public String categoryNameKey;
    public String categoryDescriptionKey;
    public byte[] categoryPreview;
    public Boolean enabled;
    public Long categoryTypeId;
    public Date modifiedDate;
}
