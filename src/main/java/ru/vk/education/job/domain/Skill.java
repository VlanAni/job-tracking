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

    @Override
    public boolean equals(Object o) {
        if (o == null) {return false;}

        if (o.getClass() != this.getClass()) {return false;}

        Skill comparatedSkill = (Skill) o;

        return this.skillTag.equals(comparatedSkill.skillTag);
    }

    @Override
    public String toString() {
        return skillTag;
    }
}
