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
    private Long image_progress_id;

    @Column()
    private Long user_id;
    @Column()
    private Long image_id;
    @Column()
    private String completed_image_parts;
    @Column()
    private Boolean is_completed;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date modified_date;
}
