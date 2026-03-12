package ru.vk.education.job.services;

import ru.vk.education.job.domain.Grade;
import ru.vk.education.job.domain.User;
import ru.vk.education.job.domain.Vacancy;
import ru.vk.education.job.storages.VacancyStorage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Suggester {

    private final VacancyStorage vs;

    public Suggester(VacancyStorage vs) {
        if (vs == null) {
            throw new IllegalArgumentException("Arguments must be non-null");
        }

        this.vs = vs;
    }

    public List<Vacancy> suggest(User user) {
        if (user == null) {
            return null;
        }

        Collection<Vacancy> vacancies = vs.getVacancies();

        List<Grade> grades = new ArrayList<>();

        for (Vacancy v : vacancies) {grades.add(new Grade(user, v));}

        grades.sort(null);

        List<Vacancy> bestVacancies = new ArrayList<>();
        for (int i = grades.size() - 1;
             i >= 0 && bestVacancies.size() < 2;
             --i) { bestVacancies.add(grades.get(i).showVacancy());}

        return bestVacancies;
    }

}
