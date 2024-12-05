package lk.ijse.crop.management.service;

import lk.ijse.crop.management.dto.impl.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);
    void deleteStaff(String staffID);
    List<StaffDTO> getAllStaff();
    void updateStaff(String staffID,StaffDTO staffDTO);
}
