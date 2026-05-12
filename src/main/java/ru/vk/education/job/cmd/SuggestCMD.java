package ru.vk.education.job.cmd;

import ru.vk.education.job.domain.User;
import ru.vk.education.job.domain.Vacancy;
import ru.vk.education.job.services.Suggester;
import ru.vk.education.job.repository.UsersStorage;
import ru.vk.education.job.repository.VacancyStorage;
import ru.vk.education.job.services.UserService;

import java.util.List;

public class SuggestCMD implements Command<String> {
    private final UserService us;
    private final Suggester suggester;

    public SuggestCMD(UsersStorage us, VacancyStorage vs) {
        this.us = new UserService(us);
        this.suggester = new Suggester(vs);
    }

    @Override
    public String name() {return "suggest";}

    @Override
    public String execute(String[] args) {
        if (args == null) {
            throw new IllegalArgumentException("Arguments must be non-null");
        }

        if (args.length != 1) {
            return ">>> incorrect usage";
        }

        User user = us.getUserByName(args[0]);

        if (user == null) {
            return "";
        }

        List<Vacancy> suggestedVacancies = suggester.suggest(user);

        StringBuilder sb = new StringBuilder();
        int amount = 0;
        for (Vacancy v : suggestedVacancies) {
            if (!sb.isEmpty()) {sb.append('\n');}
            sb.append(v.toString());
            amount++;
            if (amount == 2) {
                break;
            }
        }

        return sb.toString();
    }
}
