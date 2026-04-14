package ru.vk.education.job.cmd;

public interface Command<T> {
    String name();

    T execute(String[] args);
}
