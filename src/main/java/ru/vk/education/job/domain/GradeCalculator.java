package ru.vk.education.job.domain;

public interface GradeCalculator {
    Grade calcMatching(User user, Vacancy vacancy);
}
