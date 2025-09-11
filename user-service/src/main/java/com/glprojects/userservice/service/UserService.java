package com.glprojects.userservice.service;

import com.glprojects.userservice.dto.UserRequestDTO;
import com.glprojects.userservice.dto.UserResponseDTO;
import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAll();
    UserResponseDTO getById(Long id);
    UserResponseDTO save(UserRequestDTO dto);
    void delete(Long id);
}
