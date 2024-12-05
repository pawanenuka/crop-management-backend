package lk.ijse.crop.management.controller;

import lk.ijse.crop.management.dto.impl.LogDTO;
import lk.ijse.crop.management.dto.impl.VehicleDTO;
import lk.ijse.crop.management.exceptions.DataPersisException;
import lk.ijse.crop.management.exceptions.LogNotFoundException;
import lk.ijse.crop.management.exceptions.VehicleNotFoundException;
import lk.ijse.crop.management.service.VehicleService;
import lk.ijse.crop.management.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveVehicle(
            @RequestPart("plateNumber") String plateNumber,
            @RequestPart("vehicleCategory") String vehicleCategory,
            @RequestPart("fuelType") String fuelType,
            @RequestPart("status") String status,
            @RequestPart("remarks") String remarks
    ) {

        try {

            String vehicleCode = AppUtil.generateVehicleID();

            VehicleDTO vehicleDTO = new VehicleDTO();

            vehicleDTO.setVehicleCode(vehicleCode);
            vehicleDTO.setPlateNumber(plateNumber);
            vehicleDTO.setVehicleCategory(vehicleCategory);
            vehicleDTO.setFuelType(fuelType);
            vehicleDTO.setStatus(status);
            vehicleDTO.setRemarks(remarks);

            vehicleService.saveVehicle(vehicleDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (DataPersisException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{vehicleCode}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("vehicleCode") String vehicleCode) {
        try {
            vehicleService.deleteVehicle(vehicleCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (VehicleNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDTO> getAllVehicle(){
        return vehicleService.getAllVehicles();
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{vehicleCode}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateVehicle(
            @RequestPart("plateNumber") String plateNumber,
            @RequestPart("vehicleCategory") String vehicleCategory,
            @RequestPart("fuelType") String fuelType,
            @RequestPart("status") String status,
            @RequestPart("remarks") String remarks,

            @PathVariable("vehicleCode") String vehicleCode
    ){
        VehicleDTO vehicleDTO = new VehicleDTO();

        vehicleDTO.setVehicleCode(vehicleCode);
        vehicleDTO.setPlateNumber(plateNumber);
        vehicleDTO.setVehicleCategory(vehicleCategory);
        vehicleDTO.setFuelType(fuelType);
        vehicleDTO.setStatus(status);
        vehicleDTO.setRemarks(remarks);
        vehicleService.updateVehicle(vehicleCode,vehicleDTO);
    }
}

