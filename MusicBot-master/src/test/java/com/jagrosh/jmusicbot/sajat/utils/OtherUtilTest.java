package com.jagrosh.jmusicbot.sajat.utils;

import com.jagrosh.jmusicbot.entities.Prompt;
import net.dv8tion.jda.api.entities.Activity;
import org.junit.Test;
import static org.junit.Assert.*;
import com.jagrosh.jmusicbot.utils.OtherUtil;

import org.junit.Before;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import net.dv8tion.jda.api.OnlineStatus;

public class OtherUtilTest {

    private Path tempDir;

    @Before
    public void setup() {
        tempDir = Paths.get(System.getProperty("java.io.tmpdir"));
    }

    @Test
    public void testGetPath() {
        Path expectedPath = tempDir.resolve("test");
        Path actualPath = OtherUtil.getPath(expectedPath.toString());
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void testImageFromUrl() throws Exception {
        // Test valid URL
        String imageUrl = "https://depositphotos.com/hu/photo/kitten-lays-isolated-2186038.html";
        InputStream inputStream = OtherUtil.imageFromUrl(imageUrl);
        assertNotNull(inputStream);

        // Test invalid URL
        String invalidUrl = "http://invalid.url";
        InputStream invalidInputStream = OtherUtil.imageFromUrl(invalidUrl);
        assertNull(invalidInputStream);
    }

    @Test
    public void testParseGame() {
        assertNull(OtherUtil.parseGame(null));
        assertNull(OtherUtil.parseGame(" "));
        assertEquals(Activity.playing("Test Game"), OtherUtil.parseGame("playing Test Game"));
        assertEquals(Activity.listening("Test Music"), OtherUtil.parseGame("listening to Test Music"));
        assertEquals(Activity.watching("Test Video"), OtherUtil.parseGame("watching Test Video"));
        assertEquals(Activity.streaming("Test Stream", "https://twitch.tv/testuser"), OtherUtil.parseGame("streaming testuser Test Stream"));
    }

    @Test
    public void testMakeNonEmpty() {
        assertEquals("\u200B", OtherUtil.makeNonEmpty(null));
        assertEquals("\u200B", OtherUtil.makeNonEmpty(""));
        assertEquals("Test String", OtherUtil.makeNonEmpty("Test String"));
    }

    @Test
    public void testParseStatus() {
        assertEquals(OnlineStatus.ONLINE, OtherUtil.parseStatus(null));
        assertEquals(OnlineStatus.ONLINE, OtherUtil.parseStatus(" "));
        assertEquals(OnlineStatus.IDLE, OtherUtil.parseStatus("idle"));
    }

    @Test
    public void testCheckJavaVersion() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));

        Prompt prompt = new Prompt(System.out.toString());
        OtherUtil.checkJavaVersion(prompt);

        System.setOut(originalOut);

        String output = baos.toString();
        if (!System.getProperty("java.vm.name").contains("64")) {
            assertTrue(output.contains("It appears that you may not be using a supported Java version. Please use 64-bit java."));
        }
    }

    @Test
    public void testGetCurrentVersion() {
        String version = OtherUtil.getCurrentVersion();
        assertNotNull(version);
    }

    @Test
    public void testGetLatestVersion() {
        String latestVersion = OtherUtil.getLatestVersion();
        // This test may fail if there are network issues or if the URL changes, handle accordingly.
        assertNotNull(latestVersion);
    }
}
