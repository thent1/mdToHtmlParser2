import org.example.Checker;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckerTest {

    @Test
    void getEmptyLinesIndexesShouldReturnCorrectIndexes() {
        String[] input = {"line1", "", "line2", "", "line3"};
        List<Integer> expected = Arrays.asList(1, 3);
        assertEquals(expected, Checker.getEmptyLinesIndexes(input));
    }

    @Test
    void isValidShouldReturnTrueForValidInput() {
        String[] input = {"**bold**", "*italic*", "`monospace`"};
        assertDoesNotThrow(() -> assertTrue(Checker.isValid(input)));
    }

    @Test
    void isValidShouldThrowExceptionForUnclosedMarkers() {
        String[] input = {"**bold"};
        assertThrows(IllegalArgumentException.class, () -> Checker.isValid(input));
    }

    @Test
    void getPrefMarkIndexesShouldReturnCorrectIndexes() {
        String[] input = {"line1", "```", "line2", "```", "line3"};
        List<Integer> expected = Arrays.asList(1, 3);
        assertEquals(expected, Checker.getPrefMarkIndexes(input));
    }

    @Test
    void isNestedShouldReturnFalseForNonNestedMarkers() {
        assertFalse(Checker.isNested("**bold** *italic*"));
    }

    @Test
    void isClosedShouldReturnTrueForClosedMarkers() {
        assertTrue(Checker.isClosed("**bold** *italic*"));
    }

    @Test
    void isClosedShouldReturnFalseForUnclosedMarkers() {
        assertFalse(Checker.isClosed("**bold *italic*"));
    }

    @Test
    void removeElementsByIndexesShouldRemoveCorrectElements() {
        String[] input = {"line1", "line2", "line3", "line4"};
        List<Integer> indexesToRemove = Arrays.asList(1, 3);
        String[] expected = {"line1", "line3"};
        assertArrayEquals(expected, Checker.removeElementsByIndexes(input, indexesToRemove));
    }

    @Test
    void getExtraEmptyIndexesShouldReturnCorrectIndexes() {
        String[] input = {"line1", "", "", "line2", "", "", "line3"};
        List<Integer> expected = Arrays.asList(2, 5);
        assertDoesNotThrow(() -> assertEquals(expected, Checker.getExtraEmptyIndexes(input)));
    }

    @Test
    void getPreformattedLinesShouldReturnCorrectIndexes() {
        String[] input = {"line1", "```", "line2", "line3", "```", "line4"};
        List<Integer> expected = Arrays.asList(1, 2, 3, 4);
        assertDoesNotThrow(() -> assertEquals(expected, Checker.getPreformattedLines(input)));
    }

    @Test
    void getPreformattedLinesShouldThrowExceptionForUnclosedMarker() {
        String[] input = {"line1", "```", "line2", "line3", "line4"};
        assertThrows(IllegalArgumentException.class, () -> Checker.getPreformattedLines(input));
    }

}