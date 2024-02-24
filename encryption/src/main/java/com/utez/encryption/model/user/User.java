package com.utez.encryption.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_user",length = 16)
    private UUID id;
    @Column(name = "email", length = 30)
    private String email;
    @Column(name = "password",length = 100)
    private String password;
    @PrePersist
    private void generateUUID() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }
}
