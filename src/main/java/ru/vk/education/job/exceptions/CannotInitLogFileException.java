package ru.vk.education.job.exceptions;


public class CannotInitLogFileException extends RuntimeException {
    public CannotInitLogFileException(Exception e) {
        super(e.getMessage());
    }
}
