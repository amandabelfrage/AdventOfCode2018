import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class HelperTest {

    @Test
    public void testReadFile_nullInput_shouldThrowException() {
        try {
            Helper.readWholeFile(null);
            fail("Expected Exception to be thrown for null input");
        } catch (Exception e) {
            assertEquals(NullPointerException.class, e.getClass());
        }
    }

    @Test
    public void testReadFile_illegalFileName_shouldThrowException() {
        try {
            Helper.readWholeFile("filename_that_does_not_exist.txt");
            fail("Expected Exception to be thrown for illegal file name input");
        } catch (Exception e) {
            assertEquals(FileNotFoundException.class, e.getClass());
        }
    }

    @Test
    public void testReadFile_illegalFile() throws IOException {
        List<String> content = Helper.readWholeFile("src/test/resources/test_input.txt");
        assertEquals(Arrays.asList("+6", "-17"), content);
    }
}