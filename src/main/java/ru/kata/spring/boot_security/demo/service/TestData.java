package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Component
public class TestData implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("Ivan", "Petrov", (byte) 32);
        user1.setUserName("user@mail.ru");
        user1.setPassword("user");
        user1.setRoleList(List.of(new Role("USER")));

        User user2 = new User("Petr", "Sidorov", (byte) 27);
        user2.setUserName("admin@mail.ru");
        user2.setPassword("admin");
        user2.setRoleList(List.of(new Role("ADMIN")));

        userService.updateUser(user1);
        userService.updateUser(user2);
    }

}
