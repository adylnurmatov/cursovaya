package kg.adyl.cursovaya.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import kg.adyl.cursovaya.dto.validation.OnCreate;
import kg.adyl.cursovaya.dto.validation.OnUpdate;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDto {
    @NotNull(message = "id must be not null", groups = OnUpdate.class)
    private Long id;
    @NotNull(message = "name must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "name length must be less than 255 symbols")
    private String name;
    @NotNull(message = "username must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "username length must be less than 255 symbols")
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "password must be not null", groups = {OnUpdate.class, OnCreate.class})
    private String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "passwordConfirmation must be not null", groups = OnCreate.class)
    private String passwordConfirmation;
}
