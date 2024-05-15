package org.theaz.karabookapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "image_progress")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImageProgress {
    @Id
    @Column(name = "image_progress_id")
    @SequenceGenerator(
            name = "image_progress_sequence",
            sequenceName = "image_progress_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "image_progress_sequence"
    )
    private Long imageProgressId;

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "image_id")
    private Long imageId;
    @Column(name = "completed_image_parts")
    private String completedImageParts;
    @Column(name = "is_complete")
    private Boolean isCompleted;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", name = "modified_date")
    private Date modifiedDate;
}
