package org.example.info_injun.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.info_injun.domain.user.domain.User;
import org.example.info_injun.domain.user.exception.UserNotFoundException;
import org.example.info_injun.domain.user.domain.repository.UserRepository;
import org.example.info_injun.domain.user.presentation.dto.request.UserRequestDTO;
import org.example.info_injun.domain.user.presentation.dto.response.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .build()).toList();
    }

    public UserResponseDTO getUserById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw UserNotFoundException.EXCEPTION;
        }

        return UserResponseDTO.builder()
                .id(optionalUser.get().getId())
                .name(optionalUser.get().getName()).build();
    }

    public void creatUser(UserRequestDTO userRequestDTO) {
        User user = User.builder()
                .name(userRequestDTO.getName()).build();
        userRepository.save(user);
    }
}
