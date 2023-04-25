package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {
    public static final String USERS = "/users";
    @Autowired
    UserController userController;
    @Autowired
    private ObjectMapper objectMapper;
    @GetMapping
    public List<UserDto> readAll(){
        return userController.getAllUsers();
    }

    @GetMapping("{id}")
    public UserDto getUser(@PathVariable Integer id){
        return userController.getUserById(id);
    }


    @GetMapping("{id}/nombre")
    public Map getUserEmail(@PathVariable Integer id){
        return Collections.singletonMap("email",userController.getUserById(id).getEmail());
    }

    @PostMapping("")
    public void addUser ( @RequestBody UserDto userDto) {
        userController.addUser(userDto);
    }

    @DeleteMapping ("{id}/delete")

     public void deleteUser ( @PathVariable Integer id){
        userController.deleteUser(id);
    }

    @PutMapping("{id}/update")
    public UserDto putUser ( @RequestBody UserDto userDto,@PathVariable Integer id) {
        return userController.putUser(userDto, id );
    }
    // En el método patchUser
    @PatchMapping ("{id}/p")
    public UserDto patchUser (@RequestBody JsonPatch patch, @PathVariable Integer id) {
        UserDto userActual = userController.getUserById(id);
        UserDto userPatched = userController.applyPatch(patch,userActual);
        userController.updateUser(userPatched); // Actualizar en la base de datos
        return userPatched;
    }


}
