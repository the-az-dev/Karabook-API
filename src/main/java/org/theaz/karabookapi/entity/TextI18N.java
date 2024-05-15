package org.theaz.karabookapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "text_i18n")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TextI18N {
    @Id
    @Column(columnDefinition = "varchar(64)", name = "text_key")
    private String textKey;
    @Column(columnDefinition = "varchar(2048)", name = "text_value")
    private String textValue;
    @Column(columnDefinition = "varchar(64)", name = "locale")
    private String locale;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", name = "modified_date")
    private Date modifiedDate;
}
