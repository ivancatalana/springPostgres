package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    public List<Usuario>findAll(){
        return userDAO.findAll();
    }
        public Usuario getUserById(Integer id) {
            Optional<Usuario> optionalUsuario;
            optionalUsuario = userDAO.findById(id);
            if (optionalUsuario.isPresent()) {
                return optionalUsuario.get();
            }
            return null;
        }

    public void addUser(Usuario usuario) {
        userDAO.save(usuario);
    }

    public void deleteUser(Integer id) {
        userDAO.deleteById(id);
    }

    public void updateUser(Integer id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = userDAO.findById(id).orElseThrow(() -> new RuntimeException("No se encontró el usuario con ID: " + id));
        usuarioExistente.setEmail(usuarioActualizado.getEmail());
        usuarioExistente.setPassword(usuarioActualizado.getPassword());
        usuarioExistente.setFullName(usuarioActualizado.getFullName());
        userDAO.save(usuarioExistente);
    }
}
