package kg.adyl.cursovaya.mappers;


import kg.adyl.cursovaya.dto.user.UserDto;
import kg.adyl.cursovaya.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
