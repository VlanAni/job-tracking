package ru.vk.education.job.domain;

public class Experience {

    private final int value;

    public Experience(int value) {

        if (value < 0) {
            throw new IllegalArgumentException("Experience value must be not negative");
        }

        this.value = value;
    }

    public int value() {
        return value;
    }

    public boolean checkOverlap(Experience evaluatedExp) {
        if (evaluatedExp == null) {
            throw new IllegalArgumentException("Arguments must be non-null");
        }

        return evaluatedExp.value >= this.value;
    }
}
