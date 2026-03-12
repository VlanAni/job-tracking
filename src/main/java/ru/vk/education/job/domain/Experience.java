package ru.vk.education.job.domain;

public class Experience {

    private final int value;

    /**
     * create an experience
     *
     * @param value - the number of working years
     */
    public Experience(int value) {

        if (value < 0) {
            throw new IllegalArgumentException("Experience value must be not negative");
        }

        this.value = value;
    }

    /**
     * Get the working years.
     *
     * @return - working years
     */
    public int value() {
        return value;
    }

    /**
     * Check if there is enough experience
     *
     * @return - is enough experience
     */
    public boolean checkCompatibility(Experience evaluatedExp) {

        if (evaluatedExp == null) {
            throw new IllegalArgumentException("Arguments must be non-null");
        }

        return evaluatedExp.value >= this.value;
    }
}
