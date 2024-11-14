package kg.adyl.cursovaya.security.expression;


import kg.adyl.cursovaya.entity.Role;
import kg.adyl.cursovaya.security.JwtEntity;
import kg.adyl.cursovaya.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("SecurityExpression")
@AllArgsConstructor
public class SecurityExpression {

    private final UserService userService;

    public boolean canAccessUser(Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtEntity user = (JwtEntity) authentication.getPrincipal();
        Long userId = user.getId();

        return userId.equals(id) || hasAnyRole(authentication, Role.ROLE_ADMIN);
    }




    private boolean hasAnyRole(Authentication authentication, Role... roles){
        for (Role role: roles) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.name());
            if(authentication.getAuthorities().contains(simpleGrantedAuthority)) return true;
        }
        return false;
    }


}
