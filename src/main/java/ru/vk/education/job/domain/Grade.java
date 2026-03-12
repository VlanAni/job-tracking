package ru.vk.education.job.domain;

public class Grade implements Comparable<Grade> {
    private final float value;
    private final Vacancy vacancy;

    public Grade(User user, Vacancy vacancy) {
        if (user == null || vacancy == null) {
            throw new IllegalArgumentException("Arguments must be non-null");
        }

        int matchedSkills = vacancy.calcUsersMatchedSkills(user);
        this.value = vacancy.checkUserExperience(user) ? matchedSkills : (float) matchedSkills / 2;
        this.vacancy = vacancy;
    }

    public Vacancy showVacancy() {
        return vacancy;
    }

    public int compareTo(Grade o) {
        return Float.compare(value, o.value);
    }
}
