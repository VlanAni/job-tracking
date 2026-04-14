package ru.vk.education.job.cmd;

import ru.vk.education.job.domain.Vacancy;
import ru.vk.education.job.storages.VacancyStorage;

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
        StringBuilder sb = new StringBuilder();
        for (String vacancyName : vs.sortedVacanciesNames()) {
            if (!sb.isEmpty()) {sb.append('\n');}
            sb.append(vs.getVacancyByName(vacancyName).toString());
        }

        return sb.toString();
    }
}
