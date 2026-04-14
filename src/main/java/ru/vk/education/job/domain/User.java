package ru.vk.education.job.domain;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String name;
    private final List<Skill> skills;
    private final Experience exp;

    public User(String name,
                List<Skill> skills,
                Experience exp) {

        if (name == null ||
                skills == null ||
                exp == null) {
            throw new IllegalArgumentException("Params must be non-null");
        }

        this.name = name;
        this.skills = skills;
        this.exp = exp;
    }

    public String name() {
        return name;
    }

    public List<Skill> shareSkills() {
        return skills;
    }

    public Experience shareExp() {
        return exp;
    }

    @Override
    public String toString() {
        List<String> skillTags = new ArrayList<>();
        for (Skill s : skills) {
            skillTags.add(s.tag());
        }
        return new StringBuilder().
                append(name).
                append(' ').
                append(String.join(",", skillTags)).
                append(' ').
                append(exp.value()).
                toString();
    }

    public long checkSkillOverlap(Vacancy vacancy) {
        return vacancy.
                skills().
                stream().
                filter(this.skills::contains).
                count();
    }

    public boolean checkExpOverlap(Vacancy vacancy) {
        return vacancy.experience().checkOverlap(this.exp);
    }

}
