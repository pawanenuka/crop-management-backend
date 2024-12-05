package lk.ijse.crop.management.dto.impl;

import lk.ijse.crop.management.dto.FieldStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements FieldStatus {
    private String fieldCode;
    private String fieldName;
    private String fieldLocation;
    private String fieldExtentSize;
    private String fieldImage01;
    private String fieldImage02;
}
