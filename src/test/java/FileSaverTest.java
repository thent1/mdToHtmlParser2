import org.example.FileSaver;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileSaverTest {
    @Test
    void stringToFileShouldThrowExceptionForInvalidPath() {
        String input = "Hello, World!";
        String filepath = "\0InvalidPath.txt";
        assertThrows(IOException.class, () -> FileSaver.stringToFile(input, filepath));
    }

    @Test
    void arrayToFileShouldThrowExceptionForInvalidPath() {
        String[] input = {"Hello,", "World!"};
        String filepath = "\0InvalidPath.txt";
        assertThrows(IOException.class, () -> FileSaver.arrayToFile(input, filepath));
    }
}