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

    /**
     * Evaluate a user
     *
     * @param user - user
     * @return - grade evaluating how user's experience matched with this vacancy
     */
    boolean checkUserExperience(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User must be non-null");
        }

        return requiredExp.checkCompatibility(user.shareExp());
    }

    @Override
    public String toString() {
        return new StringBuilder().
                append(vacancyName).
                append(" at ").
                append(company.name()).
                toString();
    }

    int calcUsersMatchedSkills(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Arguments must be non-null");
        }

        List<Skill> userSkills = user.shareSkills();

        int matchedSkills = 0;
        for (Skill reqSkill : requiredSkills) {
            for (Skill userSkill : userSkills) {
                if (reqSkill.checkMatch(userSkill)) {
                    matchedSkills++;
                }
            }
        }
        return matchedSkills;
    }

}
