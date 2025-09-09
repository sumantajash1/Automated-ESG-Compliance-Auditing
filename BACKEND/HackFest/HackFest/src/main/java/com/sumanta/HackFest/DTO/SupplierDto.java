package com.sumanta.HackFest.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class SupplierDto {
    private String gstNumber;
    private String supplierName;
    private String email;
    private String contactNumber;
}
