package org.theaz.karabookapi.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.*;

@Data @AllArgsConstructor
public class AchivementsProgressUpdateDTO {
    public Long userId;
    public Long achivementId;
    public Boolean isCompleted;
    public Long completedPoints;
    public Date modifiedDate = new Date();
}
