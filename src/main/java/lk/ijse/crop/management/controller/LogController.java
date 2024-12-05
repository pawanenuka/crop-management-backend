package lk.ijse.crop.management.controller;

import lk.ijse.crop.management.dto.impl.LogDTO;
import lk.ijse.crop.management.exceptions.DataPersisException;
import lk.ijse.crop.management.exceptions.LogNotFoundException;
import lk.ijse.crop.management.service.LogService;
import lk.ijse.crop.management.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/logs")
public class LogController {
    @Autowired
    private LogService logService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveLogs(
            @RequestPart("logDate") String logDate,
            @RequestPart("logDetails") String logDetails,
            @RequestPart("logImage") MultipartFile logImage
    ) {
        // profilePic ----> Base64
        String base64ProPic = "";
        try {
            byte[] bytesProPic = logImage.getBytes();
            base64ProPic = AppUtil.profilePicToBase64(bytesProPic);
            String logCode = AppUtil.generateLogID();

            LogDTO logDTO = new LogDTO();

            logDTO.setLogCode(logCode);
            logDTO.setLogDate(logDate);
            logDTO.setLogDetails(logDetails);
            logDTO.setLogImage(base64ProPic);

            logService.saveLogs(logDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (DataPersisException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{logCode}")
    public ResponseEntity<Void> deleteLogs(@PathVariable("logCode") String logID) {
        try {
            logService.deleteLogs(logID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (LogNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LogDTO> getAllLogs() {
        return logService.getAllLogs();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{logCode}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateLogs(
            @RequestPart("logDate") String logDate,
            @RequestPart("logDetails") String logDetails,
            @RequestPart("logImage") MultipartFile logImage,
            @PathVariable("logCode")String logCode
    ){
        String base64ProPic = "";

        try {
            byte [] bytesProPic = logImage.getBytes();
            base64ProPic = AppUtil.profilePicToBase64(bytesProPic);
        }catch (Exception e){
            e.printStackTrace();
        }
        LogDTO logDTO = new LogDTO();
        logDTO.setLogCode(logCode);
        logDTO.setLogDate(logDate);
        logDTO.setLogDetails(logDetails);
        logDTO.setLogImage(base64ProPic);
        logService.saveLogs(logDTO);
    }
}
