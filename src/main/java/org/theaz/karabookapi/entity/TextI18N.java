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
    @Column(columnDefinition = "varchar(64)")
    private String text_key;
    @Column(columnDefinition = "varchar(2048)")
    private String text_value;
    @Column(columnDefinition = "varchar(64)")
    private String locale;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date modified_date;
}
