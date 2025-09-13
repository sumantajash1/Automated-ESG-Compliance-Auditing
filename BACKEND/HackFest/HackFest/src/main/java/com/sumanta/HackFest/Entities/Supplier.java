package com.sumanta.HackFest.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name="suppliers")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Supplier {
    @Id
    @Column(name = "gstnumber")
    private String gstNumber;
    @Column(name = "suppliername")
    private String supplierName;
    @Column(name = "email")
    private String email;
    @Column(name = "contactnumber")
    private String contactNumber;
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role = Role.SUPPLIER;
}
