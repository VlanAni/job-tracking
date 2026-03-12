package ru.vk.education.job.storages;

import ru.vk.education.job.domain.Vacancy;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class VacancyStorage {
    private final Map<String, Vacancy> vacancyBase;

    public VacancyStorage() {
        vacancyBase = new LinkedHashMap<>();
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
}
