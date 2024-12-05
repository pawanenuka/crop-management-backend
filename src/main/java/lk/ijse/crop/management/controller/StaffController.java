package lk.ijse.crop.management.controller;

import lk.ijse.crop.management.dto.impl.StaffDTO;
import lk.ijse.crop.management.entity.Gender;
import lk.ijse.crop.management.entity.Role;
import lk.ijse.crop.management.exceptions.DataPersisException;
import lk.ijse.crop.management.exceptions.StaffNotFoundException;
import lk.ijse.crop.management.service.StaffService;
import lk.ijse.crop.management.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaff(
            @RequestPart("staffFName") String staffFName,
            @RequestPart("staffLName") String staffLName,
            @RequestPart("designation") String designation,
            @RequestPart("gender") String gender,
            @RequestPart("joinedDate") String joinedDate,
            @RequestPart("dob") String dob,
            @RequestPart("addressLine1") String addressLine1,
            @RequestPart("addressLine2") String addressLine2,
            @RequestPart("city") String city,
            @RequestPart("state") String state,
            @RequestPart("postalCode") String postalCode,
            @RequestPart("phoneNumber") String phoneNumber,
            @RequestPart("email") String email,
            @RequestPart("role") String role
    ){
        try{
            String staffID= AppUtil.generateStaffID();
            StaffDTO staffDTO = new StaffDTO();
            staffDTO.setStaffID(staffID);
            staffDTO.setStaffFName(staffFName);
            staffDTO.setStaffLName(staffLName);
            staffDTO.setDesignation(designation);
            staffDTO.setGender(Gender.valueOf(gender));
            staffDTO.setJoinedDate(joinedDate);
            staffDTO.setDob(dob);
            staffDTO.setAddressLine1(addressLine1);
            staffDTO.setAddressLine2(addressLine2);
            staffDTO.setCity(city);
            staffDTO.setState(state);
            staffDTO.setPostalCode(postalCode);
            staffDTO.setPhoneNumber(phoneNumber);
            staffDTO.setEmail(email);
            staffDTO.setRole(Role.valueOf(role));
            staffService.saveStaff(staffDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersisException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{staffID}")
    public ResponseEntity<Void> deleteStaff(@PathVariable("staffID") String staffID){
        try{
            staffService.deleteStaff(staffID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (StaffNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getAllStaff(){
        return staffService.getAllStaff();
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{staffID}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateStaff(
            @RequestPart("staffFName") String staffFName,
            @RequestPart("staffLName") String staffLName,
            @RequestPart("designation") String designation,
            @RequestPart("gender") String gender,
            @RequestPart("joinedDate") String joinedDate,
            @RequestPart("dob") String dob,
            @RequestPart("addressLine1") String addressLine1,
            @RequestPart("addressLine2") String addressLine2,
            @RequestPart("city") String city,
            @RequestPart("state") String state,
            @RequestPart("postalCode") String postalCode,
            @RequestPart("phoneNumber") String phoneNumber,
            @RequestPart("email") String email,
            @RequestPart("role") String role,

            @PathVariable("staffID") String staffID
    ){
        StaffDTO staffDTO = new StaffDTO();

        staffDTO.setStaffID(staffID);
        staffDTO.setStaffFName(staffFName);
        staffDTO.setStaffLName(staffLName);
        staffDTO.setDesignation(designation);
        staffDTO.setGender(Gender.valueOf(gender));
        staffDTO.setJoinedDate(joinedDate);
        staffDTO.setDob(dob);
        staffDTO.setAddressLine1(addressLine1);
        staffDTO.setAddressLine2(addressLine2);
        staffDTO.setCity(city);
        staffDTO.setState(state);
        staffDTO.setPostalCode(postalCode);
        staffDTO.setPhoneNumber(phoneNumber);
        staffDTO.setEmail(email);
        staffDTO.setRole(Role.valueOf(role));
        staffService.updateStaff(staffID, staffDTO);
    }
}

