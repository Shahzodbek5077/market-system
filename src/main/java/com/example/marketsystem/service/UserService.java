package com.example.marketsystem.service;

import com.example.marketsystem.entity.User;
import com.example.marketsystem.entity.template.RoleName;
import com.example.marketsystem.exception.GenericNotFoundException;
import com.example.marketsystem.payload.ApiResponse;
import com.example.marketsystem.payload.UserDto;
import com.example.marketsystem.repository.RoleRepository;
import com.example.marketsystem.repository.UserRepository;
import com.example.marketsystem.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public ApiResponse<?> addUser(UserDto userDto, String role) {
        userRepository.save(User.builder()
                        .name(userDto.getName())
                        .address(userDto.getAddress()!=null ? userDto.getAddress() : null)
                        .password(userDto.getPassword()!=null ? userDto.getPassword() : null)
                        .role(roleRepository.getByRoleName(RoleName.valueOf(role)))
                        .phoneNumber(userDto.getPhoneNumber())
                .build());
        return new ApiResponse<>("Success",true);
    }

    public ApiResponse<?> getUsers(int page, int size) throws Exception {
        List<UserDto> list = userRepository.getByRoleName(RoleName.ROLE_USER, CommonUtils.getPageable(page, size)).stream().map(this::getUserDto).toList();
        return ApiResponse.builder().body(list)
                .success(true).message("Success").build();
    }
    public UserDto getUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .address(user.getAddress())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public ApiResponse<?> update(UserDto reqUser, Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                GenericNotFoundException.builder().message("User not found").statusCode(404).build());
        user.setName(!reqUser.getName().isBlank() ? reqUser.getName() : user.getName());
        user.setAddress(!reqUser.getAddress().isBlank() ? reqUser.getAddress() : user.getAddress());
        user.setPassword(!reqUser.getPassword().isBlank() ? reqUser.getPassword() : user.getPassword());
        user.setPhoneNumber(!reqUser.getPhoneNumber().isBlank() ? reqUser.getPhoneNumber() : user.getPhoneNumber());
        userRepository.save(user);
        return new ApiResponse<>("Success",true);
    }

    public ApiResponse<?> active(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                GenericNotFoundException.builder().message("User not found").statusCode(404).build());
        user.setEnabled(true);
        userRepository.save(user);
        return new ApiResponse<>("Success",true);
    }

    public ApiResponse<?> getOneUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                GenericNotFoundException.builder().message("User not found").statusCode(404).build());
        UserDto userDto = getUserDto(user);
        return new ApiResponse<>(userDto,"Success",true);
    }

    public Object reSetUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                GenericNotFoundException.builder().message("User not found").statusCode(404).build());
        user.setEnabled(false);
        userRepository.save(user);
        return new ApiResponse<>("Success",true);
    }
}
