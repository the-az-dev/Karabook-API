package org.theaz.karabookapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "locale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Locale {
    @Id
    @Column(columnDefinition = "varchar(64)")
    private String locale;
}
