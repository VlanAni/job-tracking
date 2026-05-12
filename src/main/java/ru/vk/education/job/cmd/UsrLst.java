package ru.vk.education.job.cmd;

import ru.vk.education.job.domain.User;
import ru.vk.education.job.repository.UsersStorage;
import ru.vk.education.job.services.UserService;

public class UsrLst implements Command<String> {
    private final UsersStorage us;

    public UsrLst(UsersStorage us) {
        this.us = us;
    }

    @Override
    public String name() {return "user-list";}

    @Override
    public String execute(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (User user : new UserService(us).listUsers()) {
            if (!sb.isEmpty()) {sb.append('\n');}
            sb.append(user.toString());
        }

        return sb.toString();
    }
}
