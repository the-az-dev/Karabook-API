package org.theaz.karabookapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Image {
    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(name = "image_raw_data", columnDefinition = "LONGTEXT")
    private String imageRawData;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "sort")
    private Long sort;

    @Column(name = "is_daily")
    private Boolean isDaily;

    @Column(name = "tag")
    private String tag;

    @Column(name = "note")
    private String note;

    @Column(name = "achivements_ids")
    private String achivementsIds;
}
