package ru.vk.education.job.services;

import java.nio.file.*;
import java.util.List;

public class FileService {
    private Path path;

    public FileService() {
        try {
            Path dirPath = Paths.get("history");
            if (!Files.exists(dirPath)) {
                Files.createDirectory(dirPath);
            }
            path = Paths.get("history/hist.txt");
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (Exception ignored) {
        }
    }

    public void saveCommand(String command) {
        if (command == null) {
            throw new IllegalArgumentException("arguments must be non-null");
        }

        try {
            byte[] bytes = (command + "\n").getBytes();
            Files.write(path, bytes, StandardOpenOption.APPEND);
        } catch (Exception ignored) {
        }
    }

    public List<String> getLastCommands() {
        try {
            return Files.readAllLines(path);
        } catch (Exception ignored) {
        }
        return List.of();
    }
}
