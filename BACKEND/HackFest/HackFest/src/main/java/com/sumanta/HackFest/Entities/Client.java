package com.sumanta.HackFest.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Table(name = "clients")
public class Client {
    @Id
    @Column(name="clientid")
    private String clientId;
    @Column(name = "gstnumber")
    private String gstNumber;
    @Column(name = "clientname")
    private String clientName;
    @Column(name = "email")
    private String email;
    @Column(name = "contactnumber")
    private String contactNumber;
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role = Role.CLIENT;
}
