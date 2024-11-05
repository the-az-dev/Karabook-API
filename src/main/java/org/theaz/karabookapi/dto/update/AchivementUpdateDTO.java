package org.theaz.karabookapi.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data @AllArgsConstructor
public class AchivementUpdateDTO {
    public Long achivementId;
    public String achivementsNameKey;
    public String achivementsDescriptionKey;
    public Boolean enabled = true;
    public Long achivementsMaxPoints = 0L;
    public Long tipsNumber = 0L;
    public String image = null;
    public Date modifiedDate = new Date();
}
