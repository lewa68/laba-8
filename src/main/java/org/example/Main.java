package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        String surname = "Oblasov";
        String name = "Alexey";

        Path directoryPath = Paths.get(surname);
        Path filePath = Paths.get(directoryPath.toString(), name);

        try {
            Files.createDirectories(directoryPath); // создаём директорию
            Files.createFile(filePath); // создаём файл

            // Создаём вложенные директории
            Path dir1 = Files.createDirectory(Paths.get(directoryPath.toString(), "dir1"));
            Path dir2 = Files.createDirectory(Paths.get(directoryPath.toString(), "dir2"));
            Path dir3 = Files.createDirectory(Paths.get(directoryPath.toString(), "dir3"));

            // Копируем файл в каждую из вложенных директорий
            Files.copy(filePath, Paths.get(dir1.toString(), name));
            Files.copy(filePath, Paths.get(dir2.toString(), name));
            Files.copy(filePath, Paths.get(dir3.toString(), name));

            // Создаём дополнительные файлы
            Files.createFile(Paths.get(dir1.toString(), "file1"));
            Files.createFile(Paths.get(dir2.toString(), "file2"));

            // Рекурсивный обход директории и вывод её содержимого
            Files.walk(directoryPath).forEach(path -> {
                if (Files.isDirectory(path)) {
                    System.out.println("D " + path.toAbsolutePath());
                } else {
                    System.out.println("F " + path.toAbsolutePath());
                }
            });

            // Удаляем директорию dir1 со всем её содержимым
            Files.walk(dir1)
                    .sorted((p1, p2) -> -p1.compareTo(p2))
                    .map(Path::toFile)
                    .forEach(File::delete);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}