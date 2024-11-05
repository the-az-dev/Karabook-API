package org.theaz.karabookapi.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "achivement_progress")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AchivementProgress {
    @Id
    @Column(name = "achivement_progress_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long achivementsProgressId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "achivement_id")
    private Long achivementId;

    @Column(columnDefinition = "bool default FALSE", nullable = false, name = "is_complete")
    private Boolean isCompleted = true;

    @Column(columnDefinition = "bool default FALSE", nullable = false, name = "is_recived")
    private Boolean isRecived = true;

    @Column(name = "completed_points")
    private Long completedPoints = 0L;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", name = "modified_date")
    private Date modifiedDate;
}
