package ru.vk.education.job.services;

import org.springframework.stereotype.Service;
import ru.vk.education.job.domain.Vacancy;
import ru.vk.education.job.repository.VacancyStorage;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class JobService {
    private final VacancyStorage vs;

    public JobService(VacancyStorage vs) {
        if (vs == null) {
            throw new IllegalArgumentException("must be non-null");
        }

        this.vs = vs;
    }

    public void addJob(Vacancy vacancy) {
        this.vs.addVacancy(vacancy);
    }

    public Collection<Vacancy> listVacancies() {
        Collection<Vacancy> vacancies = new ArrayList<>();

        for (String vacancyName : vs.sortedVacanciesNames()) {
            vacancies.add(vs.getVacancyByName(vacancyName));
        }

        return vacancies;
    }
}
