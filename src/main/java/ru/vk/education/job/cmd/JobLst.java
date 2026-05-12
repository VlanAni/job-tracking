package ru.vk.education.job.cmd;

import ru.vk.education.job.domain.Vacancy;
import ru.vk.education.job.repository.VacancyStorage;
import ru.vk.education.job.services.JobService;

public class JobLst implements Command<String> {
    private final VacancyStorage vs;

    public JobLst(VacancyStorage vs) {
        this.vs = vs;
    }

    @Override
    public String name() {
        return "job-list";
    }

    @Override
    public String execute(String[] args) {

        JobService js = new JobService(vs);

        StringBuilder sb = new StringBuilder();
        for (Vacancy vacancy : js.listVacancies()) {
            if (!sb.isEmpty()) {sb.append('\n');}
            sb.append(vacancy.toString());
        }

        return sb.toString();
    }
}
