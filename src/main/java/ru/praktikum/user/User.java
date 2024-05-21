package ru.praktikum.user;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public String name;
    public String email;
    public String password;
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}