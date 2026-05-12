package ru.vk.education.job.cmd;

import ru.vk.education.job.domain.Experience;
import ru.vk.education.job.domain.Skill;
import ru.vk.education.job.domain.User;
import ru.vk.education.job.domain.Vacancy;
import ru.vk.education.job.services.Statistic;
import ru.vk.education.job.repository.UsersStorage;
import ru.vk.education.job.repository.VacancyStorage;

import java.util.List;

public class Stat implements Command<String> {
    private final VacancyStorage vs;
    private final UsersStorage us;

    public Stat(UsersStorage us, VacancyStorage vs) {
        this.vs = vs;
        this.us = us;
    }

    @Override
    public String name() {return "stat";}

    @Override
    public String execute(String[] args) {
        Statistic statistic = new Statistic(us, vs);

        if (args == null) {
            throw new IllegalArgumentException("must be not null");
        }

        if (args.length != 2) {
            return ">>> incorrect usage";
        }

        String flag = args[0];
        String argument = args[1];

        switch (flag) {
            case "--exp" -> {
                try {
                    int value = Integer.parseInt(argument);
                    Experience exp = new Experience(value);
                    List<Vacancy> vacancies = statistic.vacancyExpStat(exp);
                    StringBuilder sb = new StringBuilder();

                    for (Vacancy vacancy : vacancies) {
                        sb.append(vacancy.toString());
                        sb.append('\n');
                    }

                    if (!sb.isEmpty()) {
                        sb.delete(sb.length() - 1, sb.length());
                    }

                    return sb.toString();
                } catch (NumberFormatException e) {
                    return ">>> incorrect usage: " + e;
                }
            }
            case "--match" -> {
                try {
                    int n = Integer.parseInt(argument);
                    List<User> users = statistic.matchStatistic(n);
                    StringBuilder sb = new StringBuilder();

                    for (User user : users) {
                        sb.append(user.toString());
                        sb.append('\n');
                    }

                    if (!sb.isEmpty()) {
                        sb.delete(sb.length() - 1, sb.length());
                    }

                    return sb.toString();
                } catch (NumberFormatException e) {
                    return ">>> incorrect usage: " + e;
                }
            }
            case "--top-skills" -> {
                try {
                    int n = Integer.parseInt(argument);
                    List<Skill> skills = statistic.topskills(n);
                    StringBuilder sb = new StringBuilder();

                    for (Skill skill : skills) {
                        sb.append(skill.toString());
                        sb.append('\n');
                    }

                    if (!sb.isEmpty()) {
                        sb.delete(sb.length() - 1, sb.length());
                    }

                    return sb.toString();
                } catch (NumberFormatException e) {
                    return ">>> incorrect usage: " + e;
                }
            }
            default -> {
                return ">>> incorrect flag";
            }
        }
    }
}
