package com.sumanta.HackFest.DTO;

import org.springframework.stereotype.Component;

@Component
public class ClientDto {
    private String clientId;
    private String clientName;
    private String email;
    private String contact;

    public ClientDto() {
    }

    public ClientDto(String clientId, String clientName, String email, String contact) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.email = email;
        this.contact = contact;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "clientId='" + clientId + '\'' +
                ", clientName='" + clientName + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
