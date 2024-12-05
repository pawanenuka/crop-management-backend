package lk.ijse.crop.management.service;

import lk.ijse.crop.management.dto.impl.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);
    void deleteVehicle(String vehicleID);
    List<VehicleDTO> getAllVehicles();
    void updateVehicle(String vehicleID,VehicleDTO vehicleDTO);
}
