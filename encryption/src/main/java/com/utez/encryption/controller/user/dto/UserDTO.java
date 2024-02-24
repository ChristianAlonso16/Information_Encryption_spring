package com.utez.encryption.controller.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class UserDTO {
    @Email(message = "Correo electrónico no válido")
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@gmail\\.[a-zA-Z]{2,}$", message = "El correo electrónico debe ser de Gmail")
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty(message = "La contraseña no puede estar en blanco")
    @Length(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;
}
