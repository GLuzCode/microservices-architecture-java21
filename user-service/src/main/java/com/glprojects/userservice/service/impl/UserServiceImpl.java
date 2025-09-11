package com.glprojects.userservice.service.impl;

import com.glprojects.userservice.dto.UserRequestDTO;
import com.glprojects.userservice.dto.UserResponseDTO;
import com.glprojects.userservice.entity.User;
import com.glprojects.userservice.exception.UserNotFoundException;
import com.glprojects.userservice.repository.UserRepository;
import com.glprojects.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public UserResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public UserResponseDTO save(UserRequestDTO dto) {
        User user = toEntity(dto);
        User saved = repository.save(user);
        return toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // 🔹 Mapeos internos
    private User toEntity(UserRequestDTO dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

    private UserResponseDTO toResponse(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}