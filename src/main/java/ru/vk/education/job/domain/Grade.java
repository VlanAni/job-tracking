package ru.vk.education.job.domain;

public class Grade implements Comparable<Grade> {
    private final User user;
    private final double value;
    private final Vacancy vacancy;

    public Grade(User user, Vacancy vacancy, double value) {
        if (user == null || vacancy == null) {
            throw new IllegalArgumentException("Arguments must be non-null");
        }

        this.user = user;
        this.vacancy = vacancy;
        this.value = value;
    }

    public Vacancy vacancy() {return vacancy;}

    public User user() {return user;}

    public int compareTo(Grade o) {
        return Double.compare(value, o.value);
    }
}
