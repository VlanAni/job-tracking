package ru.vk.education.job.services;

import org.springframework.stereotype.Service;
import ru.vk.education.job.domain.User;
import ru.vk.education.job.repository.UsersStorage;

import java.util.Collection;

@Service
public class UserService {
    private final UsersStorage us;

    public UserService(UsersStorage us) {
        if (us == null) {
            throw new IllegalArgumentException("must be non null");
        }

        this.us = us;
    }

    public void addUser(User user) {
        this.us.addUser(user);
    }

    public Collection<User> listUsers() {
        return us.getUsers();
    }

    public User getUserByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("must be non-null");
        }

        return us.getUserByName(name);
    }
}
