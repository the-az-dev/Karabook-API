package org.theaz.karabookapi.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "category")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Category {
    @Id
    @Column(name = "category_id")
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_sequence"
    )
    private Long categoryId;

    @Column(nullable = false, columnDefinition = "varchar(64)", name = "category_name_key")
    private String categoryNameKey;

    @Column(nullable = true, columnDefinition = "varchar(64) default NULL", name = "category_description_key")
    private String categoryDescriptionKey;

    @Column(nullable = true, columnDefinition = "blob default NULL", name = "category_preview")
    private byte[] categoryPreview;

    @Column(columnDefinition = "bool default TRUE", nullable = false, name = "enabled")
    private Boolean enabled = true;

    @Column(name = "category_type_id")
    private Long categoryTypeId = 0L;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", name = "modified_date")
    private Date modifiedDate;

}
