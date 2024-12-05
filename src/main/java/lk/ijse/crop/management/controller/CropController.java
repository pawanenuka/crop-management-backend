package lk.ijse.crop.management.controller;

import lk.ijse.crop.management.dto.impl.CropDTO;
import lk.ijse.crop.management.exceptions.CropNotFoundException;
import lk.ijse.crop.management.exceptions.DataPersisException;
import lk.ijse.crop.management.service.CropService;
import lk.ijse.crop.management.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/crops")
public class CropController {
    @Autowired
    private CropService cropService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(
            @RequestPart("cropCommonName") String cropCommonName,
            @RequestPart("cropScientificName") String cropScientificName,
            @RequestPart("cropImage") MultipartFile cropImage,
            @RequestPart("cropCategory") String cropCategory,
            @RequestPart("cropSeason") String cropSeason
    ) {
        // profilePic ----> Base64
        String base64ProPic = "";
        try {
            byte[] bytesProPic = cropImage.getBytes();
            base64ProPic = AppUtil.profilePicToBase64(bytesProPic);
            String cropCode=AppUtil.generateCropID();

            CropDTO cropDTO = new CropDTO();
            cropDTO.setCropCode(cropCode);
            cropDTO.setCropCommonName(cropCommonName);
            cropDTO.setCropScientificName(cropScientificName);
            cropDTO.setCropImage(base64ProPic);
            cropDTO.setCropCategory(cropCategory);
            cropDTO.setCropSeason(cropSeason);

            cropService.saveCrop(cropDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (DataPersisException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{cropCode}")
    public ResponseEntity<Void> deleteCrop(@PathVariable("cropCode") String cropCode) {
        try {
            cropService.deleteCrop(cropCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CropNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getAllCrops() {
        return cropService.getAllCrops();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{cropCode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateCrop(
            @RequestPart("cropCommonName") String cropCommonName,
            @RequestPart("cropScientificName") String cropScientificName,
            @RequestPart("cropImage") MultipartFile cropImage,
            @RequestPart("cropCategory") String cropCategory,
            @RequestPart("cropSeason") String cropSeason,
            @PathVariable("cropCode") String cropCode
    ) {
        // profilePic ----> Base64
        String base64ProPic = "";
        try {
            byte[] bytesProPic = cropImage.getBytes();
            base64ProPic = AppUtil.profilePicToBase64(bytesProPic);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CropDTO cropDTO = new CropDTO();
        cropDTO.setCropCode(cropCode);
        cropDTO.setCropCommonName(cropCommonName);
        cropDTO.setCropScientificName(cropScientificName);
        cropDTO.setCropImage(base64ProPic);
        cropDTO.setCropCategory(cropCategory);
        cropDTO.setCropSeason(cropSeason);

        cropService.updateCrop(cropCode, cropDTO);
    }
}
