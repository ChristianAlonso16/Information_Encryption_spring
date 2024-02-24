package com.utez.encryption.controller.user;

import com.utez.encryption.controller.user.dto.UserDTO;
import com.utez.encryption.services.user.UserServices;
import com.utez.encryption.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"*"})
public class UserController {
    @Autowired
    private UserServices userServices;
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<CustomResponse> login(@RequestBody UserDTO userDTO) {
        String secretKey = "claveSecreta12345";
        String decryptedData = decryptData(userDTO.getEmail(), secretKey);
        return userServices.register(userDTO);
    }
    private String decryptData(String encryptedData, String secretKey) {
       return"";
    }


}
