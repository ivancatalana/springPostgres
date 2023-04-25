package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ObjectMapper objectMapper = null;
    public List<UserDto> getAllUsers(){
        List <Usuario> users= userService.findAll();
        List<UserDto> userDtos = users.stream().map(UserDto::new).toList();
        return userDtos;
    }
    public UserDto getUserById (Integer id){
        return new UserDto( userService.getUserById(id));
    }

    public void addUser(UserDto userDto) {
        Usuario usuario = new Usuario();
        usuario.setId(userDto.getId());
        usuario.setEmail(userDto.getEmail());
        usuario.setPassword(userDto.getPassword());
        usuario.setFullName(userDto.getNombre());
        userService.addUser(usuario);
    }
    public void deleteUser(Integer id) {
        userService.deleteUser(id);
    }
    public UserDto putUser(UserDto userDto, Integer id) {
        Usuario usuario = userService.getUserById(id);
        usuario.setId(userDto.getId());
        usuario.setFullName(userDto.getNombre());
        usuario.setEmail(userDto.getEmail());
        usuario.setPassword(userDto.getPassword());
        userService.addUser(usuario);
        return userDto;
    }
    // En el controlador
    public void updateUser(UserDto userDto) {
        Usuario usuario = userService.getUserById(userDto.getId());
        if (usuario == null) {
            throw new RuntimeException("El usuario no existe");
        }
        usuario.setEmail(userDto.getEmail());
        usuario.setPassword(userDto.getPassword());
        usuario.setFullName(userDto.getNombre());
        userService.updateUser(usuario.getId(), usuario);

    }

    public UserDto applyPatch(JsonPatch patch, UserDto userActual) {
        try {
            JsonNode patched = patch.apply(objectMapper.convertValue(userActual, JsonNode.class));
            return objectMapper.treeToValue(patched,UserDto.class);
        } catch (JsonPatchException | JsonProcessingException e){
            throw new RuntimeException("Error");
        }
    }
}
