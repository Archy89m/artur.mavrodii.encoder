package ua.javarush.encoder.io;

import ua.javarush.encoder.io.exception.FileNotFoundRuntimeException;
import ua.javarush.encoder.io.exception.InputRuntimeException;
import ua.javarush.encoder.io.exception.OutputRuntimeException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileService {

    public Path getFilePath(String fileName) {

        Path path = Path.of(fileName);
        if (Files.exists(path)) {
            return path;
        } else {
            throw new FileNotFoundRuntimeException("File " + path + " not found. Please try again.");
        }
    }

    public List<String> reading(Path path) {

        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new InputRuntimeException("File read error", e);
        }
    }

    public void writeFile(Path path, String newPartFileName, List<String> lines) {

        Path newPath = getPathForCreatingFile(path, newPartFileName);
        try {
            Files.write(newPath, lines, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            System.out.println("New file created: " + newPath);
        } catch (IOException e) {
            throw new OutputRuntimeException("File write error", e);
        }
    }

    Path getPathForCreatingFile(Path path, String newPartFileName) {

        Path originalFileName = path.getFileName();

        String[] nameAndExtension = originalFileName.toString().split("\\.(?=[^.]+$)");

        String newFileName = nameAndExtension[0] + newPartFileName + nameAndExtension[1];

        return path.resolveSibling(newFileName);
    }
}