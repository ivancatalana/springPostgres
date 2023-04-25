package com.example.demo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "usuaris")
public class Usuario {
    @Id
    private int id;
    private String email;
    @Column(name = "fullname")
    private String fullName;
    private String password;


    public Usuario() {
    }

    public Usuario(int id, String email, String nom, String password) {
        this.id = id;
        this.email = email;
        this.fullName = nom;
        this.password = password;

    }

    public Usuario(UserDto userDto) {
        this.id = userDto.getId();
        this.email = userDto.getEmail();
        this.fullName = userDto.getNombre();
        this.password = userDto.getPassword();
    }
}
