package org.theaz.karabookapi.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "achivement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Achivement {
    @Id
    @Column(name = "achivement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long achivementId;

    @Column(nullable = false, columnDefinition = "varchar(64)", name = "achivement_name_key")
    private String achivementsNameKey;

    @Column(nullable = false, columnDefinition = "varchar(64)", name = "achivement_description_key")
    private String achivementsDescriptionKey;

    @Column(columnDefinition = "bool default TRUE", nullable = false, name = "enabled")
    private Boolean enabled = true;

    @Column(name = "achivement_max_points")
    private Long achivementsMaxPoints = 0L;

    @Column(name = "tipsNumber")
    private Long tipsNumber = 0L;

    @Column(nullable = true, columnDefinition = "blob default NULL", name = "image")
    private String image = null;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", name = "modified_date")
    private Date modifiedDate;
}
