package ru.vk.education.job.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vk.education.job.domain.User;
import ru.vk.education.job.domain.Vacancy;
import ru.vk.education.job.services.Suggester;
import ru.vk.education.job.services.UserService;

import java.util.List;

@RestController
public class SuggestController {
    private final Suggester suggester;
    private final UserService us;

    public SuggestController(Suggester suggester, UserService us) {
        if (suggester == null || us == null) {
            throw new IllegalArgumentException("must be non-null");
        }

        this.suggester = suggester;
        this.us = us;
    }

    @GetMapping("/suggest")
    public List<Vacancy> suggestByName(@RequestParam("name") String name) {
        User user = us.getUserByName(name);

        return suggester.suggest(user);
    }
}
