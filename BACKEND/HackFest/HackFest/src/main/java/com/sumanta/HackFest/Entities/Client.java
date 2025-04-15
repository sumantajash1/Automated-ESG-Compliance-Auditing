package com.sumanta.HackFest.Entities;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
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

    public Client() {
    }

    public Client(String ClientId, String gstNumber, String clientName, String email, String contactNumber, String password, Role role) {
        //ClientId = GenerateClientId();
        this.clientId = ClientId;
        this.gstNumber = gstNumber;
        this.clientName = clientName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = password;
        this.role = role;
    }

//    private String GenerateClientId() {
//        return "EID-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
//    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public String getclientName() {
        return clientName;
    }

    public void setclientName(String clientName) {
        this.clientName = clientName;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId='" + clientId + '\'' +
                ", gstNumber='" + gstNumber + '\'' +
                ", clientName='" + clientName + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
