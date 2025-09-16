package com.sumanta.HackFest.Services;

import com.sumanta.HackFest.DTO.ApiResponse;
import com.sumanta.HackFest.DTO.AuthResponseDto;
import com.sumanta.HackFest.DTO.ClientDto;
import com.sumanta.HackFest.DTO.SignInRequestDto;
import com.sumanta.HackFest.Entities.Client;
import com.sumanta.HackFest.Entities.Role;
import com.sumanta.HackFest.Entities.Supplier;
import com.sumanta.HackFest.Exception.ClientAlreadyExistsException;
import com.sumanta.HackFest.Repositories.ClientDao;
import com.sumanta.HackFest.Repositories.SupplierDao;
import com.sumanta.HackFest.Utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {
    @Autowired
    ClientDao clientDao;
    @Autowired
    SupplierDao supplierDao;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public ApiResponse<AuthResponseDto> register(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        client.setClientId(GenerateClientId());
        clientDao.save(client);
        AuthResponseDto authResponseDto = new AuthResponseDto(
                client.getClientName(),
                jwtTokenUtil.GenerateToken(client.getClientId(), Role.CLIENT)
        );
        return ApiResponse.ok(authResponseDto, "Sign-up successful");
    }

    private void checkIfAlreadyExists(Client client) {
        if(clientDao.existsByClientId(client.getClientId())) {
            throw new ClientAlreadyExistsException("client Id");
        }
        if(clientDao.existsByGstNumber(client.getGstNumber())) {
            throw new ClientAlreadyExistsException("Gst Number");
        }
        if(clientDao.existsByClientName(client.getClientName())) {
            throw new ClientAlreadyExistsException("Company name");
        }
        if(clientDao.existsByContactNumber(client.getContactNumber())) {
            throw new ClientAlreadyExistsException("Contact Number");
        }
        if(clientDao.existsByEmail(client.getEmail())) {
            throw new ClientAlreadyExistsException("Email Id");
        }
    }

    public ApiResponse<AuthResponseDto> signIn(SignInRequestDto signInRequestDto) {
        var existing = clientDao.getByClientId(signInRequestDto.getId());
        if(existing.isPresent()) {
            String encodedPassword = existing.get().getPassword();
            if(passwordEncoder.matches(signInRequestDto.getPassword(), encodedPassword)) {
                Role role = existing.get().getRole();
                return jwtTokenUtil.GenerateToken(signInRequestDto.getId(), role);
                // TO DO : create proper return method for this
            }
        }
        return "Invalid Credentials";
    }

    private String GenerateClientId() {
        return "EID-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public boolean AlreadyExists(String gstNumber) {
        //System.out.println(clientDao.existsById(gstNumber));
        return clientDao.existsByGstNumber(gstNumber);

    }

    public List<Supplier> getAllSuppliers() {
        return supplierDao.findAll();
    }
}
