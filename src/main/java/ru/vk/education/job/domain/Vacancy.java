package ru.vk.education.job.domain;

import java.util.List;

public class Vacancy {

    private final String vacancyName;
    private final Company company;
    private final List<Skill> requiredSkills;
    private final Experience requiredExp;

    public Vacancy(String vacancyName,
                   Company company,
                   List<Skill> requiredSkills,
                   Experience requiredExp) {

        if (vacancyName == null ||
                company == null ||
                requiredSkills == null ||
                requiredExp == null) {
            throw new IllegalArgumentException("Params must be non-null");
        }

        this.vacancyName = vacancyName;
        this.company = company;
        this.requiredSkills = requiredSkills;
        this.requiredExp = requiredExp;
    }

    public String name() {
        return vacancyName;
    }

    public Experience experience() {return new Experience(requiredExp.value());}

    public List<Skill> skills() {return List.copyOf(requiredSkills);}

    @Override
    public String toString() {
        return new StringBuilder().
                append(vacancyName).
                append(" at ").
                append(company.name()).
                toString();
    }

}
