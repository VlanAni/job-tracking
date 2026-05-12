package ru.vk.education.job.services;

import org.springframework.stereotype.Service;
import ru.vk.education.job.domain.*;
import ru.vk.education.job.repository.UsersStorage;
import ru.vk.education.job.repository.VacancyStorage;
import ru.vk.education.job.web.UserController;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Statistic {

    private final UsersStorage us;
    private final VacancyStorage vs;

    public Statistic(UsersStorage us, VacancyStorage vs) {
        if (us == null || vs == null) {
            throw new IllegalArgumentException("must be non-null");
        }

        this.vs = vs;
        this.us = us;
    }

    public List<User> matchStatistic(
            int N
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

    public List<Skill> topskills(
            int N
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

    public List<Vacancy> vacancyExpStat(
            Experience exp
    ) {
        return vs.getVacancies()
                .stream()
                .filter(v -> exp.checkOverlap(v.experience()))
                .sorted(Comparator.comparing(Vacancy::name))
                .toList();
    }
}