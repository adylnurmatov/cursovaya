package kg.adyl.cursovaya.dto.auth;

import lombok.Data;

@Data
public class JwtResponse {
    private Long id;
    private String username;
    private String accessToken;
    private String refreshToken;


}
