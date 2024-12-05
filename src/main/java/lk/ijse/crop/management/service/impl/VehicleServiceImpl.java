package lk.ijse.crop.management.service.impl;

import lk.ijse.crop.management.dao.VehicleDAO;
import lk.ijse.crop.management.dto.impl.VehicleDTO;
import lk.ijse.crop.management.entity.impl.VehicleEntity;
import lk.ijse.crop.management.exceptions.DataPersisException;
import lk.ijse.crop.management.exceptions.StaffNotFoundException;
import lk.ijse.crop.management.service.VehicleService;
import lk.ijse.crop.management.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDAO vehicleDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        VehicleEntity saveVehicle=vehicleDAO.save(mapping.toVehicleEntity(vehicleDTO));
        if (saveVehicle==null){
            throw new DataPersisException("Vehicle Not Saved");
        }
    }

    @Override
    public void deleteVehicle(String vehicleID) {
        Optional<VehicleEntity> vehicleExists = vehicleDAO.findById(vehicleID);
        if (!vehicleExists.isPresent()) {
            throw new StaffNotFoundException("Vehicle with ID " + vehicleID + " Not Found");
        } else {
            vehicleDAO.deleteById(vehicleID);
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<VehicleEntity> allVehicles = vehicleDAO.findAll();
        return mapping.asVehicleDTOList(allVehicles);
    }

    @Override
    public void updateVehicle(String vehicleID, VehicleDTO vehicleDTO) {
        Optional<VehicleEntity> vehicleExists = vehicleDAO.findById(vehicleID);
        if (vehicleExists.isPresent()) {
            vehicleExists.get().setPlateNumber(vehicleDTO.getPlateNumber());
            vehicleExists.get().setVehicleCategory(vehicleDTO.getVehicleCategory());
            vehicleExists.get().setFuelType(vehicleDTO.getFuelType());
            vehicleExists.get().setStatus(vehicleDTO.getStatus());
            vehicleExists.get().setRemarks(vehicleDTO.getRemarks());
        }
    }
}
