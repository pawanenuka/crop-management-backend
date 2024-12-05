package lk.ijse.crop.management.dto.impl;

import lk.ijse.crop.management.dto.EquipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements EquipmentStatus {
    private String equipmentID;
    private String equipmentName;
    private String equipmentType;
    private String equipmentStatus;
}
