package org.theaz.karabookapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category_parent")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CategoryParent {
    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "category_parent_sequence",
            sequenceName = "category_parent_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_parent_sequence"
    )
    private Long id;

    @Column(columnDefinition = "NOT NULL", name = "category_id")
    private Long categoryId;

    @Column(columnDefinition = "NOT NULL", name = "category_parent_id")
    private Long categoryParentId;
}
