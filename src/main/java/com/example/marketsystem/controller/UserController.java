package com.example.marketsystem.controller;

import com.example.marketsystem.entity.AuthLoginDto;
import com.example.marketsystem.payload.ApiResponse;
import com.example.marketsystem.payload.UserDto;
import com.example.marketsystem.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;

    @PostMapping( "/login")
    public HttpEntity<?> login(@Valid @RequestBody AuthLoginDto loginDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.forLogin(loginDTO));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> get(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body( userService.getOneUser(userId));
    }

    @PostMapping
    public ResponseEntity<?> addUser(@Valid  @RequestBody UserDto userDto,
         @Parameter(name = "ROLE", description = "Role:  ROLE_USER", required = true, schema = @Schema(type = "string", allowableValues = {"ROLE_USER", "ROLE_SELLER" }))
         @RequestParam(name = "ROLE", required = false) String role)
    {
        ApiResponse<?> apiResponse = userService.addUser(userDto,role);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/list")
    public ResponseEntity<?> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) throws Exception {
        ApiResponse<?> apiResponse =  userService.getUsers(page,size);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PutMapping("/{userId}")
    public HttpEntity<?> edit(@PathVariable Long userId, @RequestBody UserDto reqUser) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.update(reqUser, userId));
    }
    @DeleteMapping("/{userId}")
    public HttpEntity<?> delete(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.active(userId));
    }
    @PutMapping("/reSetUser/{id}")
    public HttpEntity<?> reSetUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.reSetUser(id));
    }
}
