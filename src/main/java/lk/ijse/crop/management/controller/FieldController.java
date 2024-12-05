package lk.ijse.crop.management.controller;

import lk.ijse.crop.management.dto.impl.CropDTO;
import lk.ijse.crop.management.dto.impl.FieldDTO;
import lk.ijse.crop.management.exceptions.CropNotFoundException;
import lk.ijse.crop.management.exceptions.DataPersisException;
import lk.ijse.crop.management.service.FieldService;
import lk.ijse.crop.management.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/field")
public class FieldController {
    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(
            @RequestPart("fieldName") String fieldName,
            @RequestPart("fieldLocation") String fieldLocation,
            @RequestPart("fieldExtentSize") String fieldExtentSize,
            @RequestPart("fieldImage01") MultipartFile fieldImage01,
            @RequestPart("fieldImage02") MultipartFile fieldImage02

    ){
        // profilePic ----> Base64
        String base64ProPic = "";
        String base64ProPic2 = "";

        try {
            byte[] bytesProPic = fieldImage01.getBytes();
            base64ProPic = AppUtil.profilePicToBase64(bytesProPic);

            byte[] bytesProPic2 = fieldImage02.getBytes();
            base64ProPic2 = AppUtil.profilePicToBase64(bytesProPic2);

            String fieldID=AppUtil.generateFieldID();

            FieldDTO fieldDTO=new FieldDTO();

            fieldDTO.setFieldCode(fieldID);
            fieldDTO.setFieldName(fieldName);
            fieldDTO.setFieldLocation(fieldLocation);
            fieldDTO.setFieldExtentSize(fieldExtentSize);
            fieldDTO.setFieldImage01(base64ProPic);
            fieldDTO.setFieldImage02(base64ProPic2);

            fieldService.saveField(fieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (DataPersisException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{fieldCode}")
    public ResponseEntity<Void> deleteField(@PathVariable("fieldCode") String fieldCode){
        try{
            fieldService.deleteField(fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CropNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
