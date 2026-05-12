package ru.vk.education.job.web;

import org.springframework.web.bind.annotation.*;
import ru.vk.education.job.domain.User;
import ru.vk.education.job.services.UserService;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService us;

    public UserController(UserService us) {
        if (us == null) {
            throw new IllegalArgumentException("must be non-null");
        }

        this.us = us;
    }

    @GetMapping
    public Collection<User> getUsers() {
        return us.listUsers();
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        us.addUser(user);
    }
}
