package ru.vk.education.job.cmd;

import ru.vk.education.job.services.FileService;

public class History implements Command<String> {
    private final FileService fs;

    public History(FileService fs) {
        if (fs == null) {
            throw new IllegalArgumentException();
        }

        this.fs = fs;
    }

    @Override
    public String name() {return "history";}

    @Override
    public String execute(String[] args) {
        StringBuilder sb = new StringBuilder();

        for (String command : fs.getLastCommands()) {
            sb.append(command).append('\n');
        }

        if (!sb.isEmpty()) {
            sb.delete(sb.length() - 1, sb.length());
        }

        return sb.toString();
    }
}
