package com.firstapi.mi_primer_api_rest.Services.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstapi.mi_primer_api_rest.Exceptions.InvalidUserException;
import com.firstapi.mi_primer_api_rest.Exceptions.UserDuplicatedException;
import com.firstapi.mi_primer_api_rest.Exceptions.UserNotFoundException;
import com.firstapi.mi_primer_api_rest.Models.Dto.UserDto;
import com.firstapi.mi_primer_api_rest.Models.Entity.User;
import com.firstapi.mi_primer_api_rest.Repository.UserRepository;
import com.firstapi.mi_primer_api_rest.Services.IUser;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserImpl implements IUser {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(UserDto userDto) {
        validateUserDto(userDto);
        User user = convertToEntity(userDto);
        return repository.save(user);
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    @Override
    public void delete(User user) {
        Optional<User> existingUser = repository.findById(user.getId());
        Objects.requireNonNull(existingUser.get(), "User is null");
        repository.delete(user);
    }

    @Override
    public User update(UserDto userDto) {
        validateUserDto(userDto);
        User existingUser = findById(userDto.getId());
        updateUserData(existingUser, userDto);

        return repository.save(existingUser);

    }

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    private void updateUserData(User user, UserDto userDto) {
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setBirthDate(userDto.getBirthDate());
        user.setNickname(userDto.getNickname());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());

    }

    private void validateUserDto(UserDto userDto) {
        Objects.requireNonNull(userDto, "User cannot be null");
        if (userDto.getName() == null || userDto.getEmail() == null || userDto.getNickname() == null)
            throw new InvalidUserException("Invalid data provided");

        if (repository.existsByNickname(userDto.getNickname()))
            throw new UserDuplicatedException("Nickname already exists: " + userDto.getNickname());
        if (repository.existsByEmail(userDto.getEmail()))
            throw new UserDuplicatedException("Email already exists: " + userDto.getEmail());

    }

    @Override
    public User convertToEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .lastName(userDto.getLastName())
                .birthDate(userDto.getBirthDate())
                .nickname(userDto.getNickname())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .createdAt(userDto.getCreatedAt())
                .build();
    }

    @Override
    public UserDto converToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .birthDate(user.getBirthDate())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .phone(user.getPhone())
                .createdAt(user.getCreatedAt())
                .build();
    }

}
