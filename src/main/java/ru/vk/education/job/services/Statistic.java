package ru.vk.education.job.services;

import ru.vk.education.job.domain.*;
import ru.vk.education.job.storages.UsersStorage;
import ru.vk.education.job.storages.VacancyStorage;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Statistic {

    public static List<User> matchStatistic(
            int N,
            UsersStorage us,
            VacancyStorage vs
    ) {
        Collection<User> users = us.getUsers();
        Collection<Vacancy> vacancies = vs.getVacancies();

        GradeCalculator gc = new SimpleCalculator();

        User zeroUser = new User("_zero_", List.of(), new Experience(0));

        return users.stream()
                .filter(user ->
                        vacancies.stream()
                                .filter(vacancy ->
                                        gc.calcMatching(user, vacancy)
                                                .compareTo(gc.calcMatching(zeroUser, vacancy)) > 0
                                )
                                .count() >= N
                )
                .collect(Collectors.toList());
    }

    public static List<Skill> topskills(
            int N,
            UsersStorage us
    ) {
        return us.getUsers().stream()
                .flatMap(user -> user.shareSkills().stream())
                .collect(Collectors.groupingBy(Skill::tag, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue()
                        .reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(N)
                .map(entry -> new Skill(entry.getKey()))
                .collect(Collectors.toList());
    }

    public static List<Vacancy> vacancyExpStat(
            Experience exp,
            VacancyStorage vs
    ) {
        return vs.getVacancies()
                .stream()
                .filter(v -> exp.checkOverlap(v.experience()))
                .sorted(Comparator.comparing(Vacancy::name))
                .toList();
    }
}