package lk.ijse.crop.management.service.impl;

import lk.ijse.crop.management.dao.CropDAO;
import lk.ijse.crop.management.dto.impl.CropDTO;
import lk.ijse.crop.management.entity.impl.CropEntity;
import lk.ijse.crop.management.exceptions.DataPersisException;
import lk.ijse.crop.management.exceptions.CropNotFoundException;
import lk.ijse.crop.management.service.CropService;
import lk.ijse.crop.management.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropServiceImpl implements CropService {
    @Autowired
    private CropDAO cropDAO;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveCrop(CropDTO cropDTO) {
        CropEntity saveCrop = cropDAO.save(mapping.toCropEntity(cropDTO));
        if (saveCrop == null) {
            throw new DataPersisException("Crop not saved");
        }
    }

    @Override
    public void deleteCrop(String cropID) {
        Optional<CropEntity> crop = cropDAO.findById(cropID);
        if (!crop.isPresent()) {
            throw new CropNotFoundException("Crop with id " + cropID + " not found");
        } else {
            cropDAO.deleteById(cropID);
        }
    }

    @Override
    public List<CropDTO> getAllCrops() {
        List<CropEntity> allCrops = cropDAO.findAll();
        return mapping.asCropDTOList(allCrops);
    }

    @Override
    public void updateCrop(String cropID, CropDTO cropDTO) {
        Optional<CropEntity> crop = cropDAO.findById(cropID);
        if (crop.isPresent()) {
            crop.get().setCropCommonName(cropDTO.getCropCommonName());
            crop.get().setCropScientificName(cropDTO.getCropScientificName());
            crop.get().setCropImage(cropDTO.getCropImage());
            crop.get().setCropCategory(cropDTO.getCropCategory());
            crop.get().setCropSeason(cropDTO.getCropSeason());
        }
    }
}
