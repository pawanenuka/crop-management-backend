package lk.ijse.crop.management.service.impl;

import lk.ijse.crop.management.dao.FieldDAO;
import lk.ijse.crop.management.dto.impl.FieldDTO;
import lk.ijse.crop.management.entity.impl.CropEntity;
import lk.ijse.crop.management.entity.impl.FieldEntity;
import lk.ijse.crop.management.exceptions.DataPersisException;
import lk.ijse.crop.management.exceptions.FieldNotFoundException;
import lk.ijse.crop.management.service.FieldService;
import lk.ijse.crop.management.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDAO fieldDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveField(FieldDTO fieldDTO) {
        FieldEntity  saveField=fieldDAO.save(mapping.toFieldEntity(fieldDTO));
        if (saveField==null){
            throw new DataPersisException("Field not saved");
        }
    }

    @Override
    public void deleteField(String fieldID) {
        Optional<FieldEntity> crop = fieldDAO.findById(fieldID);
        if (!crop.isPresent()){
            throw new FieldNotFoundException("Field with id " + fieldID + " not found");
        }else {
            fieldDAO.deleteById(fieldID);
        }
    }
}
