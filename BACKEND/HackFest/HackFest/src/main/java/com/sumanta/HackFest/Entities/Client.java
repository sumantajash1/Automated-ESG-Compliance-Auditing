package com.sumanta.HackFest.Entities;


import jakarta.persistence.*;

@Entity
@Table(name = "clients")
public class Client {
    @Id
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

    public Client(String gstNumber, String clientName, String email, String contactNumber, String password, Role role) {
        this.gstNumber = gstNumber;
        this.clientName = clientName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = password;
        this.role = role;
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
        return "Business{" +
                "gstNumber='" + gstNumber + '\'' +
                ", clientName='" + clientName + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
