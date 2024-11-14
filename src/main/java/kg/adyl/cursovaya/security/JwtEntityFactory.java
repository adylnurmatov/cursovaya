package kg.adyl.cursovaya.security;


import kg.adyl.cursovaya.entity.User;

public class JwtEntityFactory{
    public static JwtEntity toJwtEntity(User user){
        return new JwtEntity(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getPassword(),
                user.getRole()
        );
    }
}
