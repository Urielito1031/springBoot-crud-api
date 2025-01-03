package com.firstapi.mi_primer_api_rest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.firstapi.mi_primer_api_rest.Services.IUser;
import com.firstapi.mi_primer_api_rest.Models.Dto.ApiResponse;
import com.firstapi.mi_primer_api_rest.Models.Dto.UserDto;
import com.firstapi.mi_primer_api_rest.Models.Entity.User;

@RestController
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    private IUser service;

    @PostMapping("user")
    public ResponseEntity<ApiResponse<UserDto>> create(@RequestBody UserDto userDto) {
        User userSave = service.save(userDto);
        UserDto userDtoResponse = service.converToDto(userSave);
        return new ResponseEntity<>(new ApiResponse<>("User created successfully", userDtoResponse),
                HttpStatus.CREATED);

    }

    @PutMapping("user")
    public ResponseEntity<ApiResponse<UserDto>> update(@RequestBody UserDto userDto) {
        User userUpdate = service.update(userDto);
        UserDto userDtoResponse = service.converToDto(userUpdate);
        return new ResponseEntity<>(new ApiResponse<>("User updated successfully", userDtoResponse),
                HttpStatus.OK);

    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        User userDelete = service.findById(id);
        service.delete(userDelete);
        return new ResponseEntity<>(new ApiResponse<>("User deleted successfully", null),
                HttpStatus.NO_CONTENT);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<ApiResponse<UserDto>> showById(@PathVariable Long id) {
        User user = service.findById(id);
        UserDto userDtoResponse = service.converToDto(user);
        return new ResponseEntity<>(new ApiResponse<>("User found", userDtoResponse),
                HttpStatus.OK);
    }

    @GetMapping("users")
    public ResponseEntity<?> findAll() {
        List<User> list = service.findAll();
        return new ResponseEntity<>(new ApiResponse<>("Users found", list),
                HttpStatus.OK);
    }
}
