package com.utez.encryption.controller.user;

import com.utez.encryption.config.TextDecrypt;
import com.utez.encryption.controller.user.dto.UserDTO;
import com.utez.encryption.services.user.UserServices;
import com.utez.encryption.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private TextDecrypt textDecrypt;

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<CustomResponse> register(@RequestBody UserDTO userDTO) {
        String emailDecrypted = textDecrypt.decrypt(userDTO.getEmail());
        String passwordDecrypted =textDecrypt.decrypt(userDTO.getPassword());

        if(emailDecrypted!=null && passwordDecrypted!=null){
            userDTO.setEmail(emailDecrypted);
            userDTO.setPassword(passwordDecrypted);
            return userServices.register(userDTO);
        }
        else
        {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new CustomResponse(null, true, HttpStatus.BAD_REQUEST.value(), "Informacion invalida"));
        }

    }

}
