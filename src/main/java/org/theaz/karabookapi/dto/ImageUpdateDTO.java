package org.theaz.karabookapi.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ImageUpdateDTO {
    public Long imageId;
    public String imageRawData;
    public Long categoryId;
    public Boolean enabled;
    public Date modifiedDate;
    public Boolean isDaily;
}
