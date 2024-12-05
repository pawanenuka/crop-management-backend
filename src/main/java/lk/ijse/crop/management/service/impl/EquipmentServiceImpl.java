package lk.ijse.crop.management.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.crop.management.dao.EquipmentDAO;
import lk.ijse.crop.management.dto.impl.EquipmentDTO;
import lk.ijse.crop.management.entity.impl.EquipmentEntity;
import lk.ijse.crop.management.entity.impl.VehicleEntity;
import lk.ijse.crop.management.exceptions.DataPersisException;
import lk.ijse.crop.management.exceptions.EquipmentNotFoundException;
import lk.ijse.crop.management.exceptions.StaffNotFoundException;
import lk.ijse.crop.management.service.EquipmentService;
import lk.ijse.crop.management.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDAO equipmentDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        EquipmentEntity saveEquipment=equipmentDAO.save(mapping.toEquipmentEntity(equipmentDTO));
        if (saveEquipment==null){
            throw new DataPersisException("Equipment not saved");
        }
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        Optional<EquipmentEntity> equipmentExists = equipmentDAO.findById(equipmentId);
        if (!equipmentExists.isPresent()) {
            throw new EquipmentNotFoundException("Vehicle with ID " + equipmentId + " Not Found");
        } else {
            equipmentDAO.deleteById(equipmentId);
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        List<EquipmentEntity> equipmentEntities = equipmentDAO.findAll();
        return mapping.asEquipmentDTOList(equipmentEntities);
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {
        Optional<EquipmentEntity> equipmentExists = equipmentDAO.findById(equipmentId);
        if (equipmentExists.isPresent()) {
            equipmentExists.get().setEquipmentName(equipmentDTO.getEquipmentName());
            equipmentExists.get().setEquipmentType(equipmentDTO.getEquipmentType());
            equipmentExists.get().setEquipmentStatus(equipmentDTO.getEquipmentStatus());
        }
    }
}
