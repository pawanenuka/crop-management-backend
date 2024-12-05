package lk.ijse.crop.management.service;

import lk.ijse.crop.management.dto.impl.CropDTO;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO cropDTO);

    void deleteCrop(String cropID);

    List<CropDTO> getAllCrops();

    void updateCrop(String cropID, CropDTO cropDTO);
}
