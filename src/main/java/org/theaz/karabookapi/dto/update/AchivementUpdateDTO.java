package org.theaz.karabookapi.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data @AllArgsConstructor
public class AchivementUpdateDTO {
    private Long achivementId;
    private String achivementsNameKey;
    private Boolean enabled = true;
    private Long achivementsMaxPoints = 0L;
    public Date modifiedDate = new Date();
}
