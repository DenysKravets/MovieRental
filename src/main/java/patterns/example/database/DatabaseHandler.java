package patterns.example.database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class DatabaseHandler {

    private final String filename;

    public DatabaseHandler(String filename) {
        this.filename = filename;
    }

    protected String loadFile() {

        if (!new File(this.filename).exists())
            writeFile("[]");

        Path path = Paths.get(this.filename);
        try {
            byte[] bytes = Files.readAllBytes(path);
            return new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void writeFile(String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filename));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
