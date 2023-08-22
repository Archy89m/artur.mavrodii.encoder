package ua.javarush.encoder;

import ua.javarush.encoder.App.Application;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileService {
    public static Path getFilePath(String fileName) {
        Path path = Path.of(fileName);
        if (Files.exists(path)) {
            return path;
        } else {
            throw new RuntimeException("File " + path + " not found. Try again.");
        }
    }
}
