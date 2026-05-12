package ru.vk.education.job.cmd;

import ru.vk.education.job.domain.Company;
import ru.vk.education.job.domain.Experience;
import ru.vk.education.job.domain.Skill;
import ru.vk.education.job.domain.Vacancy;
import ru.vk.education.job.repository.VacancyStorage;
import ru.vk.education.job.services.JobService;

import java.util.*;

public class AddJob implements Command<String> {
    private final VacancyStorage vs;

    public AddJob(VacancyStorage vs) {
        this.vs = vs;
    }

    @Override
    public String name() {return "job";}

    @Override
    public String execute(String[] args) {
        if (args == null) {
            throw new IllegalArgumentException("must be not-null");
        }

        if (args.length != 4) {
            return ">>> incorrect usage";
        }

        new JobService(vs).addJob(parseJob(args));
        return "";
    }

    private Vacancy parseJob(String[] args) {
        String title = args[0];
        String companyName = "";
        int expValue = 0;
        List<Skill> tags = new ArrayList<>();

        for (int i = 1; i < args.length; i++) {
            if (args[i].startsWith("--company=")) {
                companyName = args[i].substring("--company=".length());
            } else if (args[i].startsWith("--tags=")) {
                String tagsString = args[i].substring("--tags=".length());
                if (!tagsString.isEmpty()) {
                    Set<String> uniqueTags = new TreeSet<>(Arrays.asList(tagsString.split(",")));
                    for (String tagName : uniqueTags) {
                        tags.add(new Skill(tagName));
                    }
                }
            } else if (args[i].startsWith("--exp=")) {
                expValue = Integer.parseInt(args[i].substring("--exp=".length()));
            }
        }

        return new Vacancy(title, new Company(companyName), tags, new Experience(expValue));
    }
}
