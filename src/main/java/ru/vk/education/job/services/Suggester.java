package ru.vk.education.job.services;

import org.springframework.stereotype.Service;
import ru.vk.education.job.domain.*;
import ru.vk.education.job.repository.VacancyStorage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class Suggester {

    private final VacancyStorage vs;
    private final GradeCalculator gc;

    public Suggester(VacancyStorage vs) {
        if (vs == null) {
            throw new IllegalArgumentException("Arguments must be non-null");
        }

        this.vs = vs;
        this.gc = new SimpleCalculator();
    }

    public List<Vacancy> suggest(User user) {
        if (user == null) {
            return null;
        }

        Collection<Vacancy> vacancies = vs.getVacancies();

        List<Grade> grades = new ArrayList<>();

        for (Vacancy v : vacancies) {grades.add(gc.calcMatching(user, v));}

        grades.sort(null);

        List<Vacancy> bestVacancies = new ArrayList<>();

        for (int i = grades.size() - 1; i >= 0; --i) {
            bestVacancies.add(grades.get(i).vacancy());
        }

        return bestVacancies;
    }

}
