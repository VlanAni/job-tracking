package ru.vk.education.job.exceptions;


public class CannotWriteToFileException extends RuntimeException {
    public CannotWriteToFileException(String message) {
        super(message);
    }
}
