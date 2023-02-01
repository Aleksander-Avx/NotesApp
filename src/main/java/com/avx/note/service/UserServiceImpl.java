package com.avx.note.service;

import com.avx.note.entity.Role;
import com.avx.note.entity.UserEntity;
import com.avx.note.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean addUser(String email, String password) {
        UserEntity userEntity = new UserEntity(email, passwordEncoder.encode(password));
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            userEntity.setRole(Role.USER);
            save(userEntity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean registration(String email, String password) {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null) {
            return (user.getPassword().equals(password));
        }
        return false;
    }

    @Override
    public boolean logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return true;
    }

    @Override
    public void save(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public UserEntity findById(long id) {

        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

}
