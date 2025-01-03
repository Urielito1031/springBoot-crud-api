package com.firstapi.mi_primer_api_rest.Services;

import java.util.List;

import com.firstapi.mi_primer_api_rest.Models.Dto.UserDto;
import com.firstapi.mi_primer_api_rest.Models.Entity.User;

public interface IUser {

    User save(UserDto user);

    List<User> findAll();

    User findById(Long id);

    void delete(User user);

    User update(UserDto userDto);

    User convertToEntity(UserDto userDto);

    UserDto converToDto(User user);

}
