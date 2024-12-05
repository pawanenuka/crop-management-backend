package lk.ijse.crop.management.service.impl;

import lk.ijse.crop.management.dao.StaffDAO;
import lk.ijse.crop.management.dto.impl.StaffDTO;
import lk.ijse.crop.management.entity.impl.StaffEntity;
import lk.ijse.crop.management.exceptions.DataPersisException;
import lk.ijse.crop.management.exceptions.StaffNotFoundException;
import lk.ijse.crop.management.service.StaffService;
import lk.ijse.crop.management.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveStaff(StaffDTO staffDTO) {
        StaffEntity saveStaff = staffDAO.save(mapping.toStaffEntity(staffDTO));
        if (saveStaff == null) {
            throw new DataPersisException("Staff Not Saved");
        }
    }

    @Override
    public void deleteStaff(String staffID) {
        Optional<StaffEntity> staffExists = staffDAO.findById(staffID);
        if (!staffExists.isPresent()) {
            throw new StaffNotFoundException("Staff with ID " + staffID + " Not Found");
        } else {
            staffDAO.deleteById(staffID);
        }
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        List<StaffEntity> allStaff = staffDAO.findAll();
        return mapping.asStaffDTOList(allStaff);
    }

    @Override
    public void updateStaff(String staffID, StaffDTO staffDTO) {
        Optional<StaffEntity> staffExists = staffDAO.findById(staffID);
        if (staffExists.isPresent()) {
            staffExists.get().setStaffFName(staffDTO.getStaffFName());
            staffExists.get().setStaffLName(staffDTO.getStaffLName());
            staffExists.get().setDesignation(staffDTO.getDesignation());
            staffExists.get().setGender(staffDTO.getGender());
            staffExists.get().setJoinedDate(staffDTO.getJoinedDate());
            staffExists.get().setDob(staffDTO.getDob());
            staffExists.get().setAddressLine1(staffDTO.getAddressLine1());
            staffExists.get().setAddressLine2(staffDTO.getAddressLine2());
            staffExists.get().setCity(staffDTO.getCity());
            staffExists.get().setState(staffDTO.getState());
            staffExists.get().setPostalCode(staffDTO.getPostalCode());
            staffExists.get().setPhoneNumber(staffDTO.getPhoneNumber());
            staffExists.get().setEmail(staffDTO.getEmail());
            staffExists.get().setRole(staffDTO.getRole());


        }
    }
}
