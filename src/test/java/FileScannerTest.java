import org.example.FileSaver;
import org.example.FileScanner;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileScannerTest {

    @Test
    void toStringShouldReturnCorrectContent() throws IOException {
        String input = "Hello, World!";
        String filepath = "test.txt";
        FileSaver.stringToFile(input, filepath);
        String content = FileScanner.toString(filepath);
        assertEquals(input, content);
        Files.deleteIfExists(Paths.get(filepath));
    }

    @Test
    void toStringShouldThrowExceptionForInvalidPath() {
        String filepath = "\0InvalidPath.txt";
        assertThrows(IOException.class, () -> FileScanner.toString(filepath));
    }

    @Test
    void toStringShouldReturnEmptyForEmptyFile() throws IOException {
        String input = "";
        String filepath = "test.txt";
        FileSaver.stringToFile(input, filepath);
        String content = FileScanner.toString(filepath);
        assertEquals(input, content);
        Files.deleteIfExists(Paths.get(filepath));
    }
}