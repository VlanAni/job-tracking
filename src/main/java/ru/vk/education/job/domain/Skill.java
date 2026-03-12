package ru.vk.education.job.domain;

public class Skill {

    private final String skillTag;

    /**
     * Create skill.
     *
     * @param skillTag - a string identifying some skill.
     */
    public Skill(String skillTag) {

        if (skillTag == null) {
            throw new IllegalArgumentException("Params must be non-null");
        }

        this.skillTag = skillTag;
    }

    /**
     * Get skill's tag
     *
     * @return - skill's tag.
     */
    public String tag() {
        return skillTag;
    }

    /**
     * Check matching between two skills.
     *
     * @param evaluatedSkill - the skill which we want to match with.
     * @return - true - tags are equal, false - tags are not equal.
     */
    public boolean checkMatch(Skill evaluatedSkill) {

        if (evaluatedSkill == null) {
            throw new IllegalArgumentException("Arguments must be non-null");
        }

        return this.skillTag.equals(evaluatedSkill.skillTag);
    }
}
