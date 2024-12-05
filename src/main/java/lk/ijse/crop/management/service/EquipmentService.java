package lk.ijse.crop.management.service;

import lk.ijse.crop.management.dto.impl.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);
    void deleteEquipment(String equipmentId);
    List<EquipmentDTO> getAllEquipment();
    void updateEquipment(String equipmentId,EquipmentDTO equipmentDTO);

}
