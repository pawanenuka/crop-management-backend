package lk.ijse.crop.management.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String profilePicToBase64(byte[] profilePic) {
        return Base64.getEncoder().encodeToString(profilePic);
    }
    public static String generateCropID(){
        return "CROP-"+ UUID.randomUUID();
    }
    public static String generateStaffID(){
        return "STAFF-"+ UUID.randomUUID();
    }
    public static String generateLogID(){
        return "LOG-"+ UUID.randomUUID();
    }
    public static String generateVehicleID(){
        return "VEHICLE-"+ UUID.randomUUID();
    }
    public static String generateEquipmentID(){
        return "EQUIPMENT-"+ UUID.randomUUID();
    }
    public static String generateFieldID(){
        return "FIELD-"+ UUID.randomUUID();
    }
}
