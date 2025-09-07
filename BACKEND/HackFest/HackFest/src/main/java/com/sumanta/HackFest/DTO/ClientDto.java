package com.sumanta.HackFest.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientDto {
    private String clientId;
    private String clientName;
    private String email;
    private String contact;
}
