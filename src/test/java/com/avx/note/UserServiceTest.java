package com.avx.note;

import com.avx.note.entity.UserEntity;
import com.avx.note.repository.UserRepository;
import com.avx.note.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class  UserServiceTest {

    private static final long ID = 1L;
    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void findByIdTest() {
        final UserEntity user = mock(UserEntity.class);

        when(repository.findById(ID)).thenReturn(Optional.ofNullable(user));

        final UserEntity actual = userService.findById(ID);

        assertNotNull(actual); // проверяем объект на null
        assertEquals(user, actual);  // проверяем пришедший user совпадает с результатом
        verify(repository).findById(ID); // вызов был один
    }

    @Test
    public void saveUser() {
        final UserEntity user = mock(UserEntity.class);

        userService.save(user); // через service сохраняем user

        verify(repository).save(user); // service вызывает repository, проверяем репозиторий был вызван с объектом который проинцилизировали
    }

}
