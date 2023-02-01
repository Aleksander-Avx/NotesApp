package com.avx.note.entity;

import lombok.*;

import javax.persistence.*;


@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "account")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @Column(name = "email")
    private String email;

    @NonNull
    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

}
