package lk.ijse.crop.management.service;

import lk.ijse.crop.management.dto.impl.FieldDTO;

public interface FieldService {
    void  saveField(FieldDTO fieldDTO);
    void deleteField(String fieldID);
}
