package ru.vk.education.job.web;

import org.springframework.web.bind.annotation.*;
import ru.vk.education.job.domain.Vacancy;
import ru.vk.education.job.services.JobService;

import java.util.Collection;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService js;

    public JobController(JobService js) {
        if (js == null) {
            throw new IllegalArgumentException("must be non-null");
        }

        this.js = js;
    }

    @GetMapping
    public Collection<Vacancy> getVacancies() {
        return js.listVacancies();
    }

    @PostMapping
    public void addJob(@RequestBody Vacancy vacancy) {
        js.addJob(vacancy);
    }
}
