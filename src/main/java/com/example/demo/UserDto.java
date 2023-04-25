package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;


    @Data
    public class UserDto {
        @Id
        private Integer id;
        private String email;
        @Column(name = "fullName")
        private String nombre;
        private String password;

        public UserDto(int id,String email, String nom, String password){
            this.id=id;
            this.email=email;
            this.nombre=nom;
            this.password=password;

        }
        public UserDto(Usuario user){
            this.id= user.getId();
            this.email=user.getEmail();
            this.nombre=user.getFullName();
            this.password=user.getPassword();

        }

}
