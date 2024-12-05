package lk.ijse.crop.management.entity.impl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lk.ijse.crop.management.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "field")
public class FieldEntity implements SuperEntity {
    @Id
    private String fieldCode;
    private String fieldName;
    private String fieldLocation;
    private String fieldExtentSize;
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String fieldImage01;
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String fieldImage02;
}
