package com.utez.encryption.services.user;

import com.utez.encryption.controller.user.dto.UserDTO;
import com.utez.encryption.model.user.User;
import com.utez.encryption.model.user.UserRepository;
import com.utez.encryption.utils.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;


@Transactional
@Service
@Slf4j
public class UserServices {
@Autowired
    private UserRepository userRepository;
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<CustomResponse> register(UserDTO UserDTO) {
        try {
            Optional<User> optionalUserAccount = userRepository.findByEmail(UserDTO.getEmail());
            if (optionalUserAccount.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new CustomResponse(null, true, HttpStatus.BAD_REQUEST.value(), "El correo esta registrado"));
            }
                    User userSave = new User();
                    userSave.setEmail(UserDTO.getEmail());
                    userSave.setPassword(UserDTO.getPassword());
                    return ResponseEntity.ok()
                            .body(new CustomResponse(userRepository.save(userSave), false, HttpStatus.OK.value(), "Cuenta registrada"));

        } catch (Exception e) {
            log.error("Error al registrar",e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CustomResponse(e, true, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error intentando hacer registro"));
        }
    }
}
