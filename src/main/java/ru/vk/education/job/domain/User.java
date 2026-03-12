package ru.vk.education.job.domain;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String name;
    private final List<Skill> skills;
    private final Experience exp;

    /**
     * Create a user
     *
     * @param name - user's name
     * @param skills - the list of user's skills
     * @param exp - user's experience
     */
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

    /**
     * User tells his name.
     *
     * @return user's name.
     */
    public String name() {
        return name;
    }

    /**
     * User tells about his skills.
     *
     * @return user's skills list.
     */
    public List<Skill> shareSkills() {
        return skills;
    }

    /**
     * User tells about his experience.
     *
     * @return user's experience.
     */
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

}
