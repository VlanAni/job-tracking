package ru.vk.education.job.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.vk.education.job.domain.*;
import ru.vk.education.job.repository.UsersStorage;
import ru.vk.education.job.repository.VacancyStorage;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class BestVacancy {
    private final VacancyStorage vs;
    private final UsersStorage us;

    public BestVacancy(VacancyStorage vs, UsersStorage us) {
        if (vs == null || us == null) {
            throw new IllegalArgumentException("must be not-null");
        }

        this.vs = vs;
        this.us = us;
    }

    @Scheduled(fixedRate = 5000)
    public void suggestBest() {

        GradeCalculator gc = new SimpleCalculator();

        for (User u : us.getUsers()) {
            Vacancy bestVacancy = findBest(u, gc);

            if (bestVacancy != null) {
                System.out.println(u.name() + ", лучшее предложение - " + bestVacancy.name());
            }
        }
    }

    public Vacancy findBest(User user, GradeCalculator gc) {
        List<Grade> grades = new ArrayList<>();

        for (Vacancy vacancy : vs.getVacancies()) {
            grades.add(gc.calcMatching(user, vacancy));
        }

        grades.sort(null);

        if (grades.isEmpty()) {
            return null;
        } else {
            return grades.get(grades.size() - 1).vacancy();
        }
    }
}
