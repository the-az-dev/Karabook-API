package org.theaz.karabookapi.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ImageProgressUpdateDTO {
    public Long imageProgressId;
    public Long imageId;
    public Long userId;
    public String completedImageParts;
    public Boolean isCompleted;
    public Date modifiedDate;
}
