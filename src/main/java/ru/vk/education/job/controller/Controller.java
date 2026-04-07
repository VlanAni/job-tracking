package ru.vk.education.job.controller;

import ru.vk.education.job.domain.*;
import ru.vk.education.job.services.FileService;
import ru.vk.education.job.services.Suggester;
import ru.vk.education.job.storages.UsersStorage;
import ru.vk.education.job.storages.VacancyStorage;

import java.util.*;

public class Controller {

    private final UsersStorage us;
    private final VacancyStorage vs;
    private final Suggester suggester;

    public Controller() {
        us = new UsersStorage();
        vs = new VacancyStorage();
        suggester = new Suggester(vs);
    }

    public void run() {
        FileService fs = new FileService();

            for (String command : fs.getLastCommands()) {
                if (command.startsWith("user ")) {
                    userHandler(command);
                } else if (command.startsWith("job ")) {
                    jobHandler(command);
                }
            }

            boolean isRunning = true;

            try (Scanner scanner = new Scanner(System.in)) {
                while (isRunning) {
                    String command = scanner.nextLine();

                    if (command.equals("exit")) {
                        isRunning = false;
                        continue;
                    } else if (command.startsWith("suggest")) {
                        String out = suggestHandler(command.split(" ")[1]);
                        System.out.println(out);
                    } else if (command.equals("user-list")) {
                        String out = userlistHandler();
                        System.out.println(out);
                    } else if (command.startsWith("job-list")) {
                        String out = joblistHandler();
                        System.out.println(out);
                    } else if (command.startsWith("user ")) {
                        userHandler(command);
                    } else if (command.startsWith("job ")) {
                        jobHandler(command);
                    } else if (command.equals("history")) {
                        String out = historyHandler(fs);
                        System.out.println(out);
                    } else {
                        System.out.println(">>> unsupported command");
                    }

                    fs.saveCommand(command);
                }
            }
    }

    private String suggestHandler(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Arguments must be non-null");
        }

        User user = us.getUserByName(username);

        if (user == null) {
            return "";
        }

        List<Vacancy> suggestedVacancies = suggester.suggest(user);

        StringBuilder sb = new StringBuilder();
        for (Vacancy v : suggestedVacancies) {
            if (!sb.isEmpty()) {sb.append('\n');}
            sb.append(v.toString());
        }

        return sb.toString();
    }

    private String userlistHandler() {
        Collection<User> users = us.getUsers();

        StringBuilder sb = new StringBuilder();
        for (User user : users) {
            if (!sb.isEmpty()) {sb.append('\n');}
            sb.append(user.toString());
        }

        return sb.toString();
    }

    private String joblistHandler() {
        Collection<Vacancy> vacancies = vs.getVacancies();

        StringBuilder sb = new StringBuilder();
        for (Vacancy vacancy : vacancies) {
            if (!sb.isEmpty()) {sb.append('\n');}
            sb.append(vacancy.toString());
        }

        return sb.toString();
    }

    private void userHandler(String command) {
        if (command == null) {
            throw new IllegalArgumentException("command must be non-null");
        }

        User user = parseUser(command);

        us.addUser(user);
    }

    private void jobHandler(String command) {
        if (command == null) {
            throw new IllegalArgumentException("command must be non-null");
        }

        Vacancy vacancy = parseJob(command);

        vs.addVacancy(vacancy);
    }

    private User parseUser(String command) {
        String[] parts = command.split(" ");
        String name = parts[1];
        int expValue = 0;
        List<Skill> skills = new ArrayList<>();

        for (int i = 2; i < parts.length; i++) {
            if (parts[i].startsWith("--skills=")) {
                String skillsString = parts[i].substring("--skills=".length());
                if (!skillsString.isEmpty()) {
                    Set<String> uniqueSkills = new TreeSet<>(Arrays.asList(skillsString.split(",")));
                    for (String skillName : uniqueSkills) {
                        skills.add(new Skill(skillName));
                    }
                }
            } else if (parts[i].startsWith("--exp=")) {
                expValue = Integer.parseInt(parts[i].substring("--exp=".length()));
            }
        }

        return new User(name, skills, new Experience(expValue));
    }

    private Vacancy parseJob(String command) {
        String[] parts = command.split(" ");
        String title = parts[1];
        String companyName = "";
        int expValue = 0;
        List<Skill> tags = new ArrayList<>();

        for (int i = 2; i < parts.length; i++) {
            if (parts[i].startsWith("--company=")) {
                companyName = parts[i].substring("--company=".length());
            } else if (parts[i].startsWith("--tags=")) {
                String tagsString = parts[i].substring("--tags=".length());
                if (!tagsString.isEmpty()) {
                    Set<String> uniqueTags = new TreeSet<>(Arrays.asList(tagsString.split(",")));
                    for (String tagName : uniqueTags) {
                        tags.add(new Skill(tagName));
                    }
                }
            } else if (parts[i].startsWith("--exp=")) {
                expValue = Integer.parseInt(parts[i].substring("--exp=".length()));
            }
        }

        return new Vacancy(title, new Company(companyName), tags, new Experience(expValue));
    }

    private String historyHandler(FileService fs) {
        if (fs == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder sb = new StringBuilder();

        for (String command : fs.getLastCommands()) {
            sb.append(command).append('\n');
        }

        if (!sb.isEmpty()) {
            sb.delete(sb.length() - 1, sb.length());
        }

        return sb.toString();
    }

}
