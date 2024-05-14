package org.theaz.karabookapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryType {
    @Id
    @Column(name = "category_type_id")
    @SequenceGenerator(
            name = "category_type_sequence",
            sequenceName = "category_type_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_type_sequence"
    )
    private Long categoryTypeId;

    @Column(columnDefinition = "varchar(128) NOT NULL", name = "category_type_name")
    private String categoryTypeName;

    @Column(columnDefinition = "varchar(256) NOT NULL", name = "category_type_note")
    private String categoryTypeNote;
}
