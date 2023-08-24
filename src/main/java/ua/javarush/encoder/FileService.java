package ua.javarush.encoder;

import ua.javarush.encoder.App.Application;
import ua.javarush.encoder.App.Command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileService {
    public static Path getFilePath(String fileName) {
        Path path = Path.of(fileName);
        if (Files.exists(path)) {
            return path;
        } else {
            throw new RuntimeException("File " + path + " not found. Please try again.");
        }
    }
    public static List<String> reading(Path path) {
        try {
            List<String> allLines = Files.readAllLines(path);
            return allLines;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public static void writeFile(Path path, Command command, List<String> lines) {

        Path newPath = getPathForCreatingFile(path, command);
        try {
            Files.write(newPath, lines, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            System.out.println("New file created: " + newPath);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public static Path getPathForCreatingFile(Path path, Command command) {

        Path originalFileName = path.getFileName();

        String[] nameAndExtension = originalFileName.toString().split("\\.(?=[^\\.]+$)");

        String newFileName = nameAndExtension[0] + "[" + command + "ED]." + nameAndExtension[1];

        return path.resolveSibling(newFileName);
    }

}
