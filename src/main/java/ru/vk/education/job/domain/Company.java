package ru.vk.education.job.domain;

public class Company {

    private final String companyName;

    /**
     * Create Company.
     *
     * @param companyName - the name of the company.
     */
    public Company(String companyName) {

        if (companyName == null) {
            throw new IllegalArgumentException("Params must be non-null");
        }

        this.companyName = companyName;
    }

    /**
     * Get company's name.
     *
     * @return - company's name.
     */
    public String name() {
        return companyName;
    }
}
