package ru.vk.education.job.storages;

import ru.vk.education.job.domain.User;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class UsersStorage {
    private final Map<String, User> userBase;

    public UsersStorage() {
        userBase = new LinkedHashMap<>();
    }

    public void addUser(User newUser) {
        if (newUser == null) {
            return;
        }

        userBase.putIfAbsent(newUser.name(), newUser);
    }

    public Collection<User> getUsers() {
        return userBase.values();
    }

    public User getUserByName(String username) {
        if (username == null) {
            throw new IllegalArgumentException("username must be non-null");
        }

        return userBase.get(username);
    }
}
