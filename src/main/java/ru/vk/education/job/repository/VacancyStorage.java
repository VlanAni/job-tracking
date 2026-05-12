package ru.vk.education.job.repository;

import org.springframework.stereotype.Repository;
import ru.vk.education.job.domain.Vacancy;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class VacancyStorage {
    private final Map<String, Vacancy> vacancyBase;

    public VacancyStorage() {
        vacancyBase = new ConcurrentHashMap<>();
    }

    public void addVacancy(Vacancy vacancy) {
        if (vacancy == null) {
            return;
        }

        vacancyBase.putIfAbsent(vacancy.name(), vacancy);
    }

    public Collection<Vacancy> getVacancies() {
        return vacancyBase.values();
    }

    public Vacancy getVacancyByName(String name) {
        return vacancyBase.get(name);
    }

    public Collection<String> sortedVacanciesNames() {
        return vacancyBase.keySet().stream().sorted().toList();
    }
}
