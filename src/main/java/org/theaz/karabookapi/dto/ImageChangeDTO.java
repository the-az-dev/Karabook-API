package org.theaz.karabookapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ImageChangeDTO {
    public Long imageId;
    public Long modifiedDate;
    public Long categoryId;
    public Boolean isDaily;
    public Long sort;
}
