package com.avx.note.service;

import com.avx.note.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    boolean addUser(String email, String password);

    boolean registration(String email, String password);

    boolean logout(HttpServletRequest request);

    void save(UserEntity user);

    UserEntity findById(long id);

    List<UserEntity> findAll();

    void deleteById(long id);
}
