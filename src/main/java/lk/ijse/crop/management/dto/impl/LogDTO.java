package lk.ijse.crop.management.dto.impl;

import lk.ijse.crop.management.dto.LogStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogDTO implements LogStatus {
    private String logCode;
    private String logDate;
    private String  logDetails;
    private String logImage;
}
