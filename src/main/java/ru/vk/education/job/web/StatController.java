package ru.vk.education.job.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vk.education.job.domain.Experience;
import ru.vk.education.job.domain.Skill;
import ru.vk.education.job.domain.User;
import ru.vk.education.job.domain.Vacancy;
import ru.vk.education.job.services.Statistic;

import java.util.List;

@RestController
@RequestMapping("/stat")
public class StatController {
    private final Statistic statistic;

    public StatController(Statistic statistic) {
        if (statistic == null) {
            throw new IllegalArgumentException("must be non-null");
        }

        this.statistic = statistic;
    }

    @GetMapping("/match")
    public List<User> getMatchStatistic(@RequestParam("n") int n) {
        return statistic.matchStatistic(n);
    }

    @GetMapping("/top-skills")
    public List<Skill> getTopSkills(@RequestParam("n") int n) {
        return statistic.topskills(n);
    }

    @GetMapping("/vacancy-exp")
    public List<Vacancy> getVacancyExpStat(@RequestParam("exp") int expValue) {
        Experience exp = new Experience(expValue);
        return statistic.vacancyExpStat(exp);
    }
}
