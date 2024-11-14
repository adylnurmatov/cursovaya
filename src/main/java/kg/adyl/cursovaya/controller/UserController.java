package kg.adyl.cursovaya.controller;


import kg.adyl.cursovaya.dto.user.UserDto;
import kg.adyl.cursovaya.dto.validation.OnUpdate;
import kg.adyl.cursovaya.entity.User;
import kg.adyl.cursovaya.mappers.UserMapper;
import kg.adyl.cursovaya.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    @PutMapping
    @PreAuthorize("@SecurityExpression.canAccessUser(#userDto.id)")
    public UserDto update(@Validated(OnUpdate.class) @RequestBody UserDto userDto){
        User user = userMapper.toEntity(userDto);
        User updatedUser = userService.update(user);
        return userMapper.toDto(updatedUser);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@SecurityExpression.canAccessUser(#id)")
    public UserDto getById(@PathVariable Long id){
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("@SecurityExpression.canAccessUser(#id)")
    public void deleteById(@PathVariable Long id){
        userService.deleteById(id);
    }



}

