package lk.ijse.crop.management.controller;

import lk.ijse.crop.management.dto.impl.EquipmentDTO;
import lk.ijse.crop.management.dto.impl.VehicleDTO;
import lk.ijse.crop.management.exceptions.DataPersisException;
import lk.ijse.crop.management.exceptions.EquipmentNotFoundException;
import lk.ijse.crop.management.exceptions.VehicleNotFoundException;
import lk.ijse.crop.management.service.EquipmentService;
import lk.ijse.crop.management.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveEquipment(
            @RequestPart("equipmentName") String equipmentName,
            @RequestPart("equipmentType") String equipmentType,
            @RequestPart("equipmentStatus") String  equipmentStatus

    ){
        try{
            String equipmentID= AppUtil.generateEquipmentID();

            EquipmentDTO equipmentDTO = new EquipmentDTO();
            equipmentDTO.setEquipmentID(equipmentID);
            equipmentDTO.setEquipmentName(equipmentName);
            equipmentDTO.setEquipmentType(equipmentType);
            equipmentDTO.setEquipmentStatus(equipmentStatus);

            equipmentService.saveEquipment(equipmentDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }catch (DataPersisException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{equipmentID}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable("equipmentID") String equipmentID) {
        try {
            equipmentService.deleteEquipment(equipmentID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EquipmentNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EquipmentDTO> getAllEquipment(){
        return equipmentService.getAllEquipment();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{equipmentID}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateEquipment(
            @RequestPart("equipmentName") String equipmentName,
            @RequestPart("equipmentType") String equipmentType,
            @RequestPart("equipmentStatus") String  equipmentStatus,

            @PathVariable("equipmentID") String equipmentID
    ){
        EquipmentDTO equipmentDTO = new EquipmentDTO();

        equipmentDTO.setEquipmentID(equipmentID);
        equipmentDTO.setEquipmentName(equipmentName);
        equipmentDTO.setEquipmentType(equipmentType);
        equipmentDTO.setEquipmentStatus(equipmentStatus);
        equipmentService.updateEquipment(equipmentID,equipmentDTO);
    }

}
