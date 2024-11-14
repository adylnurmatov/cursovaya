package kg.adyl.cursovaya.service;

import kg.adyl.cursovaya.entity.Role;
import kg.adyl.cursovaya.entity.User;
import kg.adyl.cursovaya.exception.ResourceNotFoundException;
import kg.adyl.cursovaya.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }


    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }


    @Transactional
    public User update(User user) {
        User updatedUser = userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("not found"));
        updatedUser.setName(user.getName());
        updatedUser.setUsername(user.getUsername());
        updatedUser.setRole(Role.ROLE_USER);
        updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(updatedUser);
        return updatedUser;

    }


    @Transactional
    public User create(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new IllegalStateException("user with that name already exist");
        }
        if(!user.getPassword().equals(user.getPasswordConfirmation())){
            throw new IllegalStateException("password not same");
        }
        user.setRole(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;

    }


    @Transactional
    public void insertUserRole(Long userId, Role role) {

    }



    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
