package mate_tests;

import org.junit.Test;
import static org.junit.Assert.*;
import com.jagrosh.jmusicbot.entities.Prompt;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class PromptTest {


    @Test
    public void testIsNoGUI() {
        Prompt prompt = new Prompt("Test Title");
        assertFalse(prompt.isNoGUI());
    }

    @Test
    public void testAlert() {
        // Test alert with no GUI
        Prompt prompt = new Prompt("Test Title");
        prompt.alert(Prompt.Level.INFO, "Context", "Test message");
        // No assertion, just ensure no exceptions

        // Test alert with GUI
        prompt = new Prompt("Test Title");
        InputStream sysInBackup = System.in;
        String input = "test input";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        prompt.alert(Prompt.Level.INFO, "Context", "Test message");
        assertEquals(input, scanner.nextLine());
        System.setIn(sysInBackup);
    }

    @Test
    public void testPrompt() {
        // Test prompt with no GUI
        Prompt prompt = new Prompt("Test Title");
        assertNull(prompt.prompt("Test content"));

        // Test prompt with GUI
        prompt = new Prompt("Test Title");
        InputStream sysInBackup = System.in;
        String input = "test input";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertEquals(input, prompt.prompt("Test content"));
        System.setIn(sysInBackup);
    }
}

