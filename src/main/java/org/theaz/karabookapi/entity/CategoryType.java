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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryTypeId;

    @Column(columnDefinition = "varchar(128) NOT NULL", name = "category_type_name")
    private String categoryTypeName;

    @Column(columnDefinition = "varchar(256) NOT NULL", name = "category_type_note")
    private String categoryTypeNote;
}
