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
@Table(name = "crop")
public class CropEntity implements SuperEntity {
    @Id
    private String cropCode;
    @Column(unique = true,nullable = false)
    private String cropCommonName;
    @Column(unique = true,nullable = false)
    private String cropScientificName;
    @Column(columnDefinition = "LONGTEXT",nullable = false)
    private String cropImage;
    @Column(nullable = false)
    private String cropCategory;
    @Column(nullable = false)
    private String cropSeason;
}
