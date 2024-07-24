package org.theaz.karabookapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category_parent")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CategoryParent {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "NOT NULL", name = "category_id")
    private Long categoryId;

    @Column(columnDefinition = "NOT NULL", name = "category_parent_id")
    private Long categoryParentId;
}
