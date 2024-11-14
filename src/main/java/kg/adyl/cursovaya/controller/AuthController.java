package kg.adyl.cursovaya.controller;


import kg.adyl.cursovaya.dto.auth.JwtRequest;
import kg.adyl.cursovaya.dto.auth.JwtResponse;
import kg.adyl.cursovaya.dto.user.UserDto;
import kg.adyl.cursovaya.dto.validation.OnCreate;
import kg.adyl.cursovaya.entity.User;
import kg.adyl.cursovaya.mappers.UserMapper;
import kg.adyl.cursovaya.service.AuthService;
import kg.adyl.cursovaya.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@Validated
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;




    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest jwtRequest) {
        return authService.login(jwtRequest);
    }


    @PostMapping("/register")
    public UserDto register(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.create(user);
        return userMapper.toDto(createdUser);
    }


    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String token){
        return authService.refresh(token);
    }

}
