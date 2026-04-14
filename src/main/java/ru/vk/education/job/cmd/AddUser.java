package ru.vk.education.job.cmd;

import ru.vk.education.job.domain.Experience;
import ru.vk.education.job.domain.Skill;
import ru.vk.education.job.domain.User;
import ru.vk.education.job.storages.UsersStorage;

import java.util.*;

public class AddUser implements Command<String> {
    private final UsersStorage us;

    public AddUser(UsersStorage us) {
        this.us = us;
    }

    @Override
    public String name() {return "user";}

    @Override
    public String execute(String[] args) {
        if (args == null) {
            throw new IllegalArgumentException("must be not-null");
        }

        if (args.length != 3) {
            return ">>> incorrect usage";
        }

        us.addUser(parseUser(args));
        return "";
    }

    private User parseUser(String[] args) {
        String name = args[0];
        int expValue = 0;
        List<Skill> skills = new ArrayList<>();

        for (int i = 1; i < args.length; i++) {
            if (args[i].startsWith("--skills=")) {
                String skillsString = args[i].substring("--skills=".length());
                if (!skillsString.isEmpty()) {
                    Set<String> uniqueSkills = new TreeSet<>(Arrays.asList(skillsString.split(",")));
                    for (String skillName : uniqueSkills) {
                        skills.add(new Skill(skillName));
                    }
                }
            } else if (args[i].startsWith("--exp=")) {
                expValue = Integer.parseInt(args[i].substring("--exp=".length()));
            }
        }

        return new User(name, skills, new Experience(expValue));
    }
}
