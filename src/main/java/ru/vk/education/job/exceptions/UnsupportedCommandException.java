package ru.vk.education.job.exceptions;

import java.io.IOException;

public class UnsupportedCommandException extends IOException {
    public UnsupportedCommandException(String message) {
        super(message);
    }
}
