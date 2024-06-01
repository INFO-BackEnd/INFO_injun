package org.example.info_injun.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.info_injun.domain.user.entity.User;
import org.example.info_injun.exception.NotFoundException;
import org.example.info_injun.infrastructure.repository.JpaUserRepository;
import org.example.info_injun.interfaces.user.dto.request.UserRequestDTO;
import org.example.info_injun.interfaces.user.dto.response.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JpaUserRepository jpaUserRepository;

    public List<UserResponseDTO> getAllUsers(){
        List<User> users = jpaUserRepository.findAll();

        return users.stream().map(user -> UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .build()).toList();
    }

    public UserResponseDTO getUserById(int id){
        Optional<User> optionalUser = jpaUserRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw new NotFoundException("user not found with id: " + id);
        }

        return UserResponseDTO.builder()
                .id(optionalUser.get().getId())
                .name(optionalUser.get().getName()).build();
    }

    public void creatUser(UserRequestDTO userRequestDTO){
        User user = User.builder()
                .name(userRequestDTO.getName()).build();
        jpaUserRepository.save(user);
    }
}
