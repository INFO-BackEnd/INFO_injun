package org.example.info_injun.domain.user.presentation;

import lombok.RequiredArgsConstructor;
import org.example.info_injun.domain.user.presentation.dto.request.UserRequestDTO;
import org.example.info_injun.domain.user.presentation.dto.response.UserResponseDTO;
import org.example.info_injun.domain.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public UserResponseDTO getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public void creatUser(@RequestBody UserRequestDTO userRequestDTO) {
        userService.creatUser(userRequestDTO);
    }
}
