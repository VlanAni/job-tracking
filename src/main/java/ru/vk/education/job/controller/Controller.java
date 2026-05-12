package ru.vk.education.job.controller;

import ru.vk.education.job.cmd.*;
import ru.vk.education.job.domain.UserInput;
import ru.vk.education.job.exceptions.UnsupportedCommandException;
import ru.vk.education.job.services.*;
import ru.vk.education.job.repository.UsersStorage;
import ru.vk.education.job.repository.VacancyStorage;

import java.util.*;

public class Controller {

    private final UsersStorage us;
    private final VacancyStorage vs;

    public Controller() {
        us = new UsersStorage();
        vs = new VacancyStorage();
    }

    public void run() {
        FileService fs = new FileService();

        // регистрируем команды
        List<Command<?>> registeredCommands = new ArrayList<>();
        registeredCommands.add(new AddJob(vs));
        registeredCommands.add(new AddUser(us));

        Map<String, Command<?>> histcmds = new HashMap<>();

        for (Command<?> cmd : registeredCommands) {
            histcmds.put(cmd.name(), cmd);
        }

        CommandRouter cr = new CommandRouter(histcmds);

        for (String command : fs.getLastCommands()) {
            UserInput ui = new UserInput(command.trim());

            try {
                cr.getCommand(ui.getCommand()).execute(ui.getArgs());
            } catch (UnsupportedCommandException ignored) {
            }
        }

        registeredCommands.add(new History(fs));
        registeredCommands.add(new JobLst(vs));
        registeredCommands.add(new Stat(us, vs));
        registeredCommands.add(new SuggestCMD(us, vs));
        registeredCommands.add(new UsrLst(us));

        Map<String, Command<?>> maincmds = new HashMap<>();

        for (Command<?> cmd : registeredCommands) {
            maincmds.put(cmd.name(), cmd);
        }

        cr = new CommandRouter(maincmds);

        boolean isRunning = true;

        try (Scanner scanner = new Scanner(System.in)) {
            while (isRunning) {
                String command = scanner.nextLine();

                UserInput ui = new UserInput(command.trim());

                if (ui.getCommand().isEmpty()) {
                    continue;
                }

                if (ui.getCommand().equals("exit")) {
                    isRunning = false;
                    continue;
                }

                try {
                    Object res = cr.getCommand(ui.getCommand()).execute(ui.getArgs());
                    if (res.getClass() == String.class) {
                        String output = (String) res;
                        if (!output.isEmpty()) {
                            System.out.println(output);
                        }
                    }
                } catch (UnsupportedCommandException e) {
                    System.out.println(">>> unsupported command");
                }

                fs.saveCommand(command);
            }
        }
    }
}
