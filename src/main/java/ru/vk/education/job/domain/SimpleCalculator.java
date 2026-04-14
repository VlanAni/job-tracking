package ru.vk.education.job.domain;

public class SimpleCalculator implements GradeCalculator {
    @Override
    public Grade calcMatching(User user, Vacancy vacancy) {
        long matchedSkills = user.checkSkillOverlap(vacancy);
        return new Grade(
                user,
                vacancy,
                (double) (user.checkExpOverlap(vacancy) ? matchedSkills : matchedSkills / 2)
        );
    }
}
