package ru.vk.education.job.domain;

public class Skill {

    private final String skillTag;

    public Skill(String skillTag) {

        if (skillTag == null) {
            throw new IllegalArgumentException("Params must be non-null");
        }

        this.skillTag = skillTag;
    }

    public String tag() {
        return skillTag;
    }

    public boolean checkMatch(Skill evaluatedSkill) {

        if (evaluatedSkill == null) {
            throw new IllegalArgumentException("Arguments must be non-null");
        }

        return this.skillTag.equals(evaluatedSkill.skillTag);
    }

    @Override
    public String toString() {
        return skillTag;
    }
}
