import org.example.Editor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EditorTest {

    @Test
    void mdToHtmlStringShouldConvertMarkdownToHtml() {
        String input = "**bold** _italic_ `monospace`";
        String expected = "<p><b>bold</b> <i>italic</i> <tt>monospace</tt></p>";
        try {
            String result = Editor.mdToHtmlString(input);
            assertEquals(expected, result);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void mdToHtmlStringShouldHandleEmptyInput() {
        String input = "";
        String expected = "";
        try {
            String result = Editor.mdToHtmlString(input);
            assertEquals(expected, result);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void mdToAnsiStringShouldConvertMarkdownToAnsi() {
        String input = "**bold** _italic_ `monospace`";
        String expected = "\033[0;1mbold\033[0m \033[3mitalic\033[0m \u001B[7mmonospace\033[0m";
        try {
            String result = Editor.mdToAnsiString(input);
            assertEquals(expected, result);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void mdToAnsiStringShouldHandleEmptyInput() {
        String input = "";
        String expected = "";
        try {
            String result = Editor.mdToAnsiString(input);
            assertEquals(expected, result);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void replaceBoldHtmlShouldReplaceMarkdownBoldWithHtmlBold() {
        String input = "**bold**";
        String expected = "<b>bold</b>";
        String result = Editor.replaceBoldHtml(input);
        assertEquals(expected, result);
    }

    @Test
    void replaceItalicHtmlShouldReplaceMarkdownItalicWithHtmlItalic() {
        String input = "_italic_";
        String expected = "<i>italic</i>";
        String result = Editor.replaceItalicHtml(input);
        assertEquals(expected, result);
    }

    @Test
    void replaceMonoHtmlShouldReplaceMarkdownMonospaceWithHtmlMonospace() {
        String input = "`monospace`";
        String expected = "<tt>monospace</tt>";
        String result = Editor.replaceMonoHtml(input);
        assertEquals(expected, result);
    }

    @Test
    void replaceBoldAnsiShouldReplaceMarkdownBoldWithAnsiBold() {
        String input = "**bold**";
        String expected = "\033[0;1mbold\033[0m";
        String result = Editor.replaceBoldAnsi(input);
        assertEquals(expected, result);
    }

    @Test
    void replaceItalicAnsiShouldReplaceMarkdownItalicWithAnsiItalic() {
        String input = "_italic_";
        String expected = "\033[3mitalic\033[0m";
        String result = Editor.replaceItalicAnsi(input);
        assertEquals(expected, result);
    }

    @Test
    void replaceMonoAnsiShouldReplaceMarkdownMonospaceWithAnsiMonospace() {
        String input = "`monospace`";
        String expected = "\u001B[7mmonospace\033[0m";
        String result = Editor.replaceMonoAnsi(input);
        assertEquals(expected, result);
    }
}
